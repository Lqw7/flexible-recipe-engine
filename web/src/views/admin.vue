<template>
  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <p>
        <a-form layout="inline" :model="param">
          <a-form-item>
            <a-input v-model:value="param.category" placeholder="Category">
            </a-input>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="handleQuery({page: 1, size: pagination.pageSize})">
              Query
            </a-button>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="add()">
              Add
            </a-button>
          </a-form-item>
        </a-form>
      </p>
      <a-table
          :columns="columns"
          :row-key="record => record.name"
          :data-source="ingredients"
          :loading="loading"
          :pagination="pagination"
          @change="handleTableChange"
      >
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
              Edit
            </a-button>
            <a-popconfirm
                title="Deleted and unrecoverable, confirm deletion?"
                ok-text="Yes"
                cancel-text="No"
                @confirm="handleDelete(record.name)"
            >
              <a-button type="danger" >
                Delete
              </a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>
  <a-modal
      title="substitution ingredient"
      v-model:visible="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="ingredient" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="name">
        <a-input v-model:value="ingredient.name" />
      </a-form-item>
      <a-form-item label="category">
        <a-input v-model:value="ingredient.category" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>


<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import axios from 'axios';
import {message} from 'ant-design-vue'
import {Tool} from "@/util/tool";

export default defineComponent({
  name: 'AdminSubstitution',
  setup() {
    const param = ref();
    param.value = {};
    const ingredients = ref();
    const loading = ref(false);
    const pagination = ref({
      current: 1,
      pageSize: 10,
      total: 0
    });
    const columns = [
      {
        title: 'Name',
        dataIndex: 'name',
        slots: { customRender: 'cover' }
      },
      {
        title: 'Category',
        dataIndex: 'category'
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' }
      }
    ];

    /**
     * data query
     **/
    const handleQuery = (params: any) => {
      loading.value = true;
      axios.get("/ingredient/getAllSubstitutionList", {
        params:{
          page: params.page,
          size: params.size,
          category: param.value.category
        }
      }).then((response) => {
        loading.value = false;
        const data = response.data;
        if(data.success){
          ingredients.value = data.content.list;

          // 重置分页按钮
          pagination.value.current = params.page;
          pagination.value.total = data.content.total;
        } else {
          message.error(data.message)
        }

      });
    };

    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination: any) => {
      console.log("看看自带的分页参数都有啥：" + pagination);
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      });
    };

    // -------- Form ---------
    const ingredient = ref({});
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      modalLoading.value = true;
      axios.post("/ingredient/save", ingredient.value).then((response) => {
        modalLoading.value = false;
        const data = response.data;
        if(data.success){
          modalVisible.value = false;
          //重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        }else {
          message.error(data.message)
        }
      });
    };

    /**
     * edit
     */
    const edit = (record: any) => {
      modalVisible.value = true;
      ingredient.value = Tool.copy(record)
    };

    /**
     * add
     */
    const add = () => {
      modalVisible.value = true;
      ingredient.value = {};
    };

    /**
     * delete
     */
    const handleDelete = (name: string) => {
      axios.delete("/ingredient/delete/" + name).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {
          // 重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        }
      });
    };

    onMounted(() => {
      handleQuery({
        page: 1,
        size: pagination.value.pageSize,
      });
    });

    return {
      param,
      ingredients,
      pagination,
      columns,
      loading,
      ingredient,
      modalVisible,
      modalLoading,
      handleTableChange,
      handleQuery,
      handleModalOk,
      handleDelete,
      add,
      edit
    }
  }
});
</script>
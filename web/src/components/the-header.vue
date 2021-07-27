<template>
  <a-layout-header>
    <div class="logo" />
    <a-menu
        theme="dark"
        mode="horizontal"
        v-model:selectedKeys="selectedKeys"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/">
        <router-link to="/">Home</router-link>
      </a-menu-item>
      <a-menu-item key="/search">
        <router-link to="/search">Search</router-link>
      </a-menu-item>
      <a-menu-item key="/about">
        <router-link to="/about">Substitution</router-link>
      </a-menu-item>
      <a-menu-item key="/admin">
        <router-link to="/admin">Manage Substitution</router-link>
      </a-menu-item>
      <a class="register-menu" @click="showRegisterModal">
        <span>Register</span>
      </a>
      <a class="login-menu" @click="showLoginModal">
        <span>Login</span>
      </a>
    </a-menu>
  </a-layout-header>
  <a-modal
      title="Register"
      v-model:visible="registerModalVisible"
      :confirm-loading="registerModalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="user" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="Loginname">
        <a-input v-model:value="user.loginName" />
      </a-form-item>
      <a-form-item label="Nickname">
        <a-input v-model:value="user.name" />
      </a-form-item>
      <a-form-item label="Password">
        <a-input v-model:value="user.password" />
      </a-form-item>
    </a-form>
  </a-modal>

  <a-modal
      title="Login"
      v-model:visible="loginModalVisible"
      :confirm-loading="loginModalLoading"
      @ok="login"
  >
    <a-form :model="loginUser" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="LoginName">
        <a-input v-model:value="loginUser.loginName" />
      </a-form-item>
      <a-form-item label="Password">
        <a-input v-model:value="loginUser.password" type="password" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
import { LeftCircleOutlined, RightCircleOutlined } from '@ant-design/icons-vue';
import { defineComponent,ref } from 'vue';
import axios from 'axios';
import { message } from 'ant-design-vue';
declare let hexMd5: any;
declare let KEY: any;
export default defineComponent({
  components: {
    LeftCircleOutlined,
    RightCircleOutlined,
  },

  setup(){

    const loginUser = ref({
      loginName: "test",
      password: "test"
    });

    // -------- Register Form ---------
    const user = ref();
    const registerModalVisible = ref(false);
    const registerModalLoading = ref(false);
    const handleModalOk = () => {
      registerModalLoading.value = true;
        user.value.password = hexMd5(user.value.password + KEY);

        axios.post("/user/save", user.value).then((response) => {
          registerModalLoading.value = false;
          const data = response.data; // data = commonResp
          if (data.success) {
            registerModalVisible.value = false;
            message.success("Registration successful");
          } else {
            console.log(data.message);
            message.error(data.message);
          }
        });

    };

    /**
     * Register
     */
    const showRegisterModal = () => {
      registerModalVisible.value = true;
      registerModalLoading.value = false;
      user.value = {};
    };

    // -------- Login Form ---------
    const loginModalVisible = ref(false);
    const loginModalLoading = ref(false);
    const showLoginModal = () => {
      loginModalVisible.value = true;
    };

    // Login
    const login = () => {
      console.log("Start Login")
    };

    return {
      handleModalOk,
      showRegisterModal,
      registerModalVisible,
      registerModalLoading,
      loginModalVisible,
      loginModalLoading,
      showLoginModal,
      user,
      loginUser,
      login
    }
  },

});

</script>
<style>
.login-menu {
  float: right;
  color: white;
  margin-right: 1%;
}
.register-menu{
  float: right;
  color: white;
  margin-right: 2%;
}
</style>
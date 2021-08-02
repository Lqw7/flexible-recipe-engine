<template>
  <a-layout-footer style="text-align: center">
    {{ user.name }} © Flexible Recipe Engine
  </a-layout-footer>
</template>

<script lang="ts">
import { defineComponent, computed, onMounted } from 'vue';
import store from "@/store";
import {Tool} from "@/util/tool";
import { notification } from 'ant-design-vue';
export default defineComponent({
  name: 'the-footer',
  setup() {
    const user = computed(() => store.state.user);
    let websocket: any;
    let token: any;
    const onOpen = () => {
      console.log('WebSocket connection successful, status code:', websocket.readyState)
    };
    const onMessage = (event: any) => {
      console.log('WebSocket receives the message:', event.data);
      notification['info']({
        message: 'Message received',
        description: event.data,
      });
    };
    const onError = () => {
      console.log('WebSocket connection error, status code:', websocket.readyState)
    };
    const onClose = () => {
      console.log('WebSocket connection closed, status code:', websocket.readyState)
    };
    const initWebSocket = () => {
      // Connection successful
      websocket.onopen = onOpen;
      // Callbacks for received messages
      websocket.onmessage = onMessage;
      // Connection error
      websocket.onerror = onError;
      // Callback for connection closure
      websocket.onclose = onClose;
    };

    onMounted(() => {
      // WebSocket
      if ('WebSocket' in window) {
        token = Tool.uuid(10);
        websocket = new WebSocket('ws://127.0.0.1:8889/ws/' + token);
        initWebSocket()

       // websocket.close();
      } else {
        alert('Current browser Not supported')
      }
    });

    return {
      user
    }
  }

});
</script>
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from "ant-design-vue";
import "ant-design-vue/dist/antd.css";
import * as Icons from '@ant-design/icons-vue';
import axios from "axios";
import {Tool} from "@/util/tool";


const app = createApp(App);
app.use(store).use(router).use(Antd).mount('#app');

axios.defaults.baseURL = process.env.VUE_APP_SERVER;


/**
 * axios interceptors
 */
axios.interceptors.request.use(function (config) {
    console.log('Request parameters:', config);
    const token = store.state.user.token;
    if (Tool.isNotEmpty(token)) {
        config.headers.token = token;
        console.log("Request headers to add a token:", token);
    }
    return config;
}, error => {
    return Promise.reject(error);
});
axios.interceptors.response.use(function (response) {
    console.log('Return result:', response);
    return response;
}, error => {
    console.log('Return error:', error);
    return Promise.reject(error);
});


// Global use of icons
const icons: any = Icons;
for (const i in icons) {
    app.component(i, icons[i]);
}

console.log('Environment', process.env.NODE_ENV);
console.log('Servers', process.env.VUE_APP_SERVER);
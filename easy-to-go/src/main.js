/*
 * @Date: 2023-12-12 08:31:32
 * @LastEditTime: 2023-12-24 16:51:48
 * @FilePath: \EasyToGo\easy-to-go\src\main.js
 */
import './assets/style/main.css';
import './assets/style/public.css';

import { createApp } from 'vue';

import App from './App.vue';
import router from './router';

const app = createApp(App)

app.use(router)

app.mount('#app')

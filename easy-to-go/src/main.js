/*
 * @Date: 2023-12-12 08:31:32
 * @LastEditTime: 2023-12-31 00:38:36
 * @FilePath: \easy-to-go\src\main.js
 */
import './assets/style/main.css';
import './assets/style/public.css';
import 'element-plus/dist/index.css';

import { createApp } from 'vue';

import ElementPlus from 'element-plus';

import * as ElementPlusIconsVue from '@element-plus/icons-vue';

import App from './App.vue';
import router from './router';

const app = createApp(App);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
}
app.use(router).use(ElementPlus);

app.mount('#app');

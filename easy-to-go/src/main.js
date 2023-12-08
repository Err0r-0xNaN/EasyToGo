import './assets/style/main.css';
import './assets/style/public.css';

import { createApp } from 'vue';

import App from './App.vue';
import router from './router';

const app = createApp(App)

app.use(router)

app.mount('#app')

router.beforeEach((to, from, next) => {
    if (to.meta.title) { //拦截后设置标题
      document.title = to.meta.title
    }
    next()
})

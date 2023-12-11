import {
  createRouter,
  createWebHistory,
} from 'vue-router';

import LoginView from '../views/Log/LogIn.vue';
import RegisterView from '../views/Log/Register.vue';
import IndexView from '../views/public/index.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'index',
      component: IndexView,
      meta: {
        title: 'E通达 - 通达人的信息共享平台'
      }
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
      meta: {
        title: 'E通达 - 登录'
      }
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView,
      meta: {
        title: 'E通达 - 注册'
      }
    }
  ]
})

export default router

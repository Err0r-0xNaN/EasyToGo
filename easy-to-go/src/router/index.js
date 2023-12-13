/*
 * @Date: 2023-12-12 08:31:32
 * @LastEditTime: 2023-12-13 21:11:09
 * @FilePath: \easy-to-go\src\router\index.js
 */
import {
  createRouter,
  createWebHistory,
} from 'vue-router';

import LoginView from '@/views/Log/LogIn.vue';
import RegisterView from '@/views/Log/Register.vue';
import IndexView from '@/views/public/index.vue';
import MeView from '@/views/public/Me.vue';

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
    },
    {
      path: '/user/me',
      name: 'me',
      component: MeView,
      meta: {
        title: 'E通达 - 个人中心'
      }
    }
  ]
})

export default router

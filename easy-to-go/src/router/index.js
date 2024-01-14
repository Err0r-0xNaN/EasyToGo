/*
 * @Date: 2023-12-12 08:31:32
 * @LastEditTime: 2024-01-06 17:39:01
 * @FilePath: \easy-to-go\src\router\index.js
 */
import {
  createRouter,
  createWebHistory,
} from 'vue-router';

import IndexConsole from '../views/console/index.vue';
import ConsoleLogin from '../views/console/login.vue';
import UndefinedView from '../views/public/404.vue';
import IndexView from '../views/public/index.vue';
import LogInView from '../views/public/Log/LogIn.vue';
import test from '../views/public/test.vue';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/test',
            name: 'test',
            component: test,
            meta: {
                title: '测试页面',
                characterAuthLevel: 0,
            },
        },
        {
            path: '/',
            name: 'index',
            component: IndexView,
            meta: {
                title: 'E通达 - 通达人的信息共享平台',
                characterAuthLevel: 0,
            },
        },
        {
            path: '/login',
            name: 'login',
            component: LogInView,
            meta: {
                title: 'E通达 - 登录',
                characterAuthLevel: 0,
            },
        },
        {
            path: '/user/me',
            name: 'me',
            component: () => import('../views/public/user/me.vue'),
            meta: {
                title: 'E通达 - 个人中心',
                characterAuthLevel: 2,
            }
        },
        {
            path: '/console',
            name: 'console',
            component: IndexConsole,
            meta: {
                title: 'E通达后台管理面板',
                characterAuthLevel: 5,
            } ,
            children: [
                {
                    path: '',
                    name: 'dashboard',
                    component: () => import('../views/console/dashboard.vue')
                }, {
                    path: '/userInfoController',
                    name: 'userInfoController',
                    component: () => import('../views/console/userInfo/userInfoController.vue')
                },
            ],
        },
        {
            path: '/consoleLogin',
            name: 'consoleLogin',
            component: ConsoleLogin,
            meta: {
                title: 'E通达后台管理面板 - 登录',
                characterAuthLevel: 0,
            },
        },
        {
            path: '/404',
            name: '404',
            component: UndefinedView,
            meta: {
                title: 'E通达 404 - 没有找到此页面',
                characterAuthLevel: 0,
            },
        },
    ],
});

/* 路由守卫、跳转鉴权 */
router.beforeEach((to, from, next) => {
    /* console.log(to.meta.characterAuthLevel); */
    //获取token
    const tokenStr = window.localStorage.getItem('user-data');
    const character = window.localStorage.getItem('user-character');
    /* console.log(tokenStr) */
    //一般用户
    if (to.meta.characterAuthLevel > 0 && to.meta.characterAuthLevel < 5 && !tokenStr) {
        document.title = 'E通达 - 登录';
        return next('/login');
    } /* else if (to.meta.characterAuthLevel > 0 && to.meta.characterAuthLevel >= 5 && (!tokenStr || !character)) {
        document.title = 'E通达后台管理面板 - 登录';
        return next('/consoleLogin');
    } */
    //TODO:感觉还是有点问题，留意一下，以后可能会修
    else if (to.path === '/login' && tokenStr) {
        return next('/user/me');
    } else if (to.path === '/consoleLogin' && tokenStr && character) {
        return next('/console');
    }
    //页面不存在
    else {
        if (to.meta.characterAuthLevel == undefined) {
            console.log('页面不存在哦~\n0xNaNFlag{UndefinedThisPage!^!}');
            return next('/404');
        } else {
            return next();
        }
    }
});

export default router;

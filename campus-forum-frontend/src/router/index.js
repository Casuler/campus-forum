import { createRouter, createWebHistory } from 'vue-router'
import {useStore} from "@/stores/store.js";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'welcome',
            component: () => import('@/views/welcomeView.vue'),
            children: [
                {
                    path: '',
                    name: 'welcome-login',
                    component: () => import('@/views/welcome/loginPage.vue')
                }, {
                    path: 'register',
                    name: 'welcome-register',
                    component: () => import('@/views/welcome/registerPage.vue')
                }, {
                    path: 'forget',
                    name: 'welcome-forget',
                    component: () => import('@/views/welcome/forgetPage.vue')
                }
            ]
        }, {
            path: '/index',
            name: 'index',
            component: () => import('@/views/indexView.vue'),
            children: [
                {
                    path: '',
                    name: 'index-list',
                    component: () => import('@/views/index/postList.vue')
                },
                {
                    path: 'settings',
                    name: 'index-settings',
                    component: () => import('@/views/index/Settings.vue')
                }
            ]
        }
    ]
})

router.beforeEach((to, from, next) => {
    const store = useStore()
    if(store.auth.user != null && to.name.startsWith('welcome-')) {
        next('/index')
    } else if(store.auth.user == null && to.fullPath.startsWith('/index')) {
        next('/')
    } else if(to.matched.length === 0){
        next('/index')
    } else {
        next()
    }
})

export default router

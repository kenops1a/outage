import Vue from 'vue'
import VueRouter from 'vue-router'

// 全局使用vueRouter
Vue.use(VueRouter)

export default new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'index',
            component: () => import('@/components/test/RouterTest')
        }, {
            path: '/home',
            name: 'home',
            component: () => import('@/components/home/Home')
        }, {
            path: '/login',
            name: 'login',
            component: () => import('@/components/account/login/Login'),
            meta: {
                keep: false
            }
        }, {
            path: '/register',
            name: 'register',
            component: () => import('@/components/account/register/Register'),
            meta: {
                keep: false
            }
        },
        // 测试路由组件
        {
            path: '/textTest',
            name: 'textTest',
            component: () => import('@/components/test/TextTest')
        }, {
            path: '/validTest',
            name: 'validTest',
            component: () => import('@/components/test/ValidTest')
        }
    ]
})

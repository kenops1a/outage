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
            component: () => import('@/views/index/Index')
        },
        // 首页暂时路由到index页
        {
            path: '/home',
            name: 'home',
            component: () => import('@/views/index/Index')
        }, {
            path: '/login',
            name: 'login',
            component: () => import('@/components/account/login/Login'),
        }, {
            path: '/register',
            name: 'register',
            component: () => import('@/components/account/register/Register'),
        }, {
            path: '/host',
            name: 'home',
            component: () => import('@/views/host/Host')
        }, {
            path: '/message',
            name: 'message',
            component: () => import('@/views/message/Message')
        }, {
            path: '/weblog',
            name: 'weblog',
            component: () => import('@/views/weblog/Weblog')
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
        }, {
            path: '/paramShowTest',
            name: 'paramShowTest',
            component: () => import('@/components/test/componentParam/ParamShowTest')
        }, {
            path: '/vuexTest',
            name: 'vuexTest',
            component: () => import('@/components/test/vuexTest/VuexTest')
        }, {
            path: '/changeTest',
            name: 'changeTest',
            component: () => import('@/components/test/vuexTest/ChangeByNick')
        }
    ]
})

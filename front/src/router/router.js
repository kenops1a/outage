import Vue from 'vue'
import VueRouter from 'vue-router'

// 全局使用vueRouter
Vue.use(VueRouter)

// 未知代码
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

export default new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'index',
            component: () => import('@/views/index/Index'),
            meta: {
                keep: true
            }

        },
        // 首页暂时路由到index页
        {
            path: '/home',
            name: 'home',
            component: () => import('@/views/index/Index'),
            meta: {
                keep: true
            }
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
        }, {
            path: '/host',
            name: 'host',
            component: () => import('@/views/host/Host'),
            meta: {
                keep: true
            }
        },
        // 主持人详情页
        {
            path: '/hostDetail',
            name: 'hostDetail',
            component: () => import('@/views/host/HostDetail'),
            meta: {
                keep: true,
                auth: true
            }
        }, {
            path: '/message',
            name: 'message',
            component: () => import('@/views/message/Message'),
            meta: {
                keep: true,
                auth: true
            }
        }, {
            path: '/weblog',
            name: 'weblog',
            component: () => import('@/views/weblog/Weblog'),
            meta: {
                keep: true
            }
        }, {
            path: '/weblogDetail',
            name: 'weblogDetail',
            meta: {
                keep: true
            }
        }, {
            path: '/userMenu',
            component: () => import('@/views/user/UserMenu'),
            children: [
                {
                    path: '/',
                    name: 'userMenuIndex',
                    component: () => import('@/views/user/info/UserInfo')
                },
                {
                    path: '/userInfo',
                    name: 'userInfo',
                    component: () => import('@/views/user/info/UserInfo')
                }, {
                    path: '/orderForm',
                    name: 'orderForm',
                    component: () => import('@/views/user/info/orderForm')
                }, {
                    path: '/accountInfo',
                    name: 'accountInfo',
                    component: () => import('@/views/user/info/accountInfo')
                }
            ],
            meta: {
                auth: true
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
            component: () => import('@/components/test/validTest/ValidTest')
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
        }, {
            path: '/menuTest',
            name: 'menuTest',
            component: () => import('@/components/test/vuexTest/MenuTest')
        }, {
            path: '/getHostList',
            name: 'getHostList',
            component: () => import('@/components/test/requestTest/GetHostList')
        }, {
            path: '/colRowTest',
            name: 'colRowTest',
            component: () => import('@/components/test/layoutTest/ColRowTest')
        }, {
            path: '/floatTest',
            name: 'floatTest',
            component: () => import('@/components/test/layoutTest/floatTest')
        }, {
            path: '/manyParamsTest',
            name: 'manyParamsTest',
            component: () => import('@/components/test/requestTest/ManyParamsTest')
        }, {
            path: '/pageTest',
            name: 'pageTest',
            component: () => import('@/components/test/pageTest/PageTest')
        }, {
            path: '/stringToDate',
            name: 'stringToDate',
            component: () => import('@/components/test/stringForDate/StringToDate')
        }, {
            path: '/msgListTest',
            name: 'msgListTest',
            component: () => import('@/components/test/msgListTest/MsgListTest')
        }, {
            path: '/objectTest',
            name: 'objectTest',
            component: () => import('@/components/test/objectTest/ObjectTest')
        }
    ]
})

// 引入vuex

import Vue from 'vue'
import Vuex from 'vuex'

// 全局使用vuex插件
Vue.use(Vuex)

// 全局管理的数据储存
const state = {
    // 当前登录用户昵称
    userNick: '',
    // vuex测试标题
    title: 'Hello World!',
    // 登录状态，0-未登录，1-已登录
    loginStatus: 0,
    ser: null,
    // 如果token存在，则赋值为token，不存在则赋值为''
    token: localStorage.getItem('token') ? localStorage.getItem('token'):''
}

// 创建vuex对象
const store = new Vuex.Store({
    state,
    // 监听数据变化
    getters: {
        getStorage (state) {
            // 判断token是否为空，如果为空则将本地token反序列化后赋值给state.token
            if (!state.token) {
                state.token = JSON.parse(localStorage.getItem('token'))
            }
            return state.token
        }
    },
    // 操作state中的数据
    mutations: {
        // 操作token
        $_setToken(state, value) {
            state.token = value
            localStorage.setItem('token', value)
        },
        $_removeToken() {
            localStorage.removeItem('token')
        },
        // 修改登录状态
        $_updateLoginStatus(state, value) {
            state.loginStatus = value
        },
        // 测试vuex全局变量修改
        $_updateTitle(state, title) {
            state.title = title
        },
        // 设置当前登录用户昵称
        $_setUserNick(state, nick) {
            state.userNick = nick
        }
    }
})

// 提供接口
export default store

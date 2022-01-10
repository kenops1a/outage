// 引入vuex

import Vue from 'vue'
import Vuex from 'vuex'

// 全局使用vuex插件
// 暂时用不到了
// Vue.use(Vuex)

// 全局管理的数据储存
const state = {
    isLogin: '0',
    ser: null,
    // 如果token存在，则赋值为token，不存在则赋值为''
    token: localStorage.getItem('token') ? localStorage.getItem('token'):''
}

export default new Vuex.Store({
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
        $_setToken(state, value) {
            state.token = value
            localStorage.setItem('token', value)
        },
        $_removeToken() {
            localStorage.removeItem('token')
        }
    }
})
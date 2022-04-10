// 引入vuex

import Vue from 'vue'
import Vuex from 'vuex'

// 全局使用vuex插件
Vue.use(Vuex)

// 全局管理的数据储存
const state = {
    // 当前登录用户
    userNow: localStorage.getItem('userNow') ? JSON.parse(localStorage.getItem('userNow')) : null,
    // 主持人对象
    host: localStorage.getItem('host') ? JSON.parse(localStorage.getItem('host')) : null ,
    // 主持人通信列表
    hostList: localStorage.getItem('hostList') ? JSON.parse(localStorage.getItem('hostList')) : [],
    // 登录状态，0-未登录，1-已登录
    loginStatus: localStorage.getItem('userNow') ? 1 : 0,
    // 如果token存在，则赋值为token，不存在则赋值为''
    token: localStorage.getItem('token') ? localStorage.getItem('token'):null,
    // 聊天消息列表
    msgList: [],
    list: [],
    errorSnackBarShow: false,
    errorSnackBarMessage: '',
    successSnackBarShow: false,
    successSnackBarMessage: '',
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
        },
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
        $_updateLoginStatus(state, status) {
            state.loginStatus = status
        },
        // 设置主持人对象
        $_setHost(state, host) {
            state.host = host
        },
        // 设置正在登录的用户
        $_setUserNow(state, userNow) {
            state.userNow = userNow
        },
        // 添加已联系的主持人
        $_setHostList(state, host) {
            state.hostList.push(host)
        },
        // 删除已添加的主持人
        $_delHostListItem(state, hostId) {
            // for
            // for (let i = 0; i < state.hostList.length; i++) {
            //     if (state.hostList[i].id === hostId) {
            //         state.hostList.replace(i,1)
            //     }
            // }

            // foreach
            state.hostList.forEach(function (item, index, array) {
                if (item.id === hostId) {
                    array.splice(index, 1)
                }
            })
        },
        // 向消息列表中添加元素
        $_addMessage(state, message) {
            state.msgList.push(message)
        },
        // vuex数组push测试
        $_addMsg(state, msg) {
            let list = state.list
            list.push(msg)
            state.list = list
        },

        /*  成功提示  */
        $_successBarSwitch(state) {
            state.successSnackBarShow = !state.successSnackBarShow
        },
        $_setSuccessBarMessage(state, message) {
            state.successSnackBarMessage = message
        },

        /*  失败提示  */
        $_errorBarSwitch(state) {
            state.errorSnackBarShow = !state.errorSnackBarShow
        },
        $_setErrorBarMessage(state, message) {
            state.errorSnackBarMessage = message
        }
    }
})

// 提供接口
export default store

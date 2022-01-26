import store from "@/store/store";

let websocket = null
let userId = store.state.userNow.id
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://localhost:8089/message/socket" + userId)
    // 连接错误回调
    websocket.onerror = function () {
        console.log('连接失败')
    }
    // 连接成功回调
    websocket.onopen = function (event) {
        console.log('连接成功')
    }
    // 接收消息回调方法
    websocket.onmessage = function (event) {
        console.log(event.data)
    }
    // 连接关闭的回调方法
    websocket.onclose = function () {
        console.log('连接关闭')
    }
    // 页面关闭前，主动断开websocket连接
    window.onbeforeunload = function () {
        websocket.close();
    }
} else {
    alert('Not support websocket')
}

export default websocket

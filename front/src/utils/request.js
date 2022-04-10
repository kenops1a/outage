import axios from 'axios'
import {error} from "@/utils/snackBar";

const request = new axios.create({
    baseURL: "http://localhost:8089",
    timeout: 60000
})

// request请求拦截处理
request.interceptors.request.use(config => {
    const token = localStorage.getItem('token')
    // 在js中，if除了能够判断bool的真假外，还能判断一个变量是否有值
    if (token) {
        // 向请求中添加token
        config.headers.common['token'] = token
    }
    return config
})

// response 响应拦截处理
request.interceptors.response.use(res => {
    if (res.data.errorCode === 999) {
        error(res.data.record)
        return Promise.reject(new Error(res.data.record))
    }
    return res.data
})
export default request

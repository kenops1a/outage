import Index from '@/components/Index/Index'
import Host from "@/components/content/Host";
import Message from "@/components/content/Message";
import WebBlock from "@/components/content/WebBlock";
import Login from "@/components/content/Login";
import Register from "@/components/content/Register";

// 基础路由
const routers = [
    {
        path: '/',
        component: Index // 首页
    }, {
        path: '/index',
        name: 'index',
        component: Index // 首页
    }, {
        path: '/host',
        name: 'host',
        component: Host // 主持人菜单
    }, {
        path: '/message',
        name: 'message',
        component: Message // 消息
    }, {
        path: '/webBlock',
        name: 'webBlock',
        component: WebBlock // 动态
    }, {
        path: '/login',
        name: 'login',
        component: Login
    }, {
        path: '/register',
        name: 'register',
        component: Register
    }
]

export default routers

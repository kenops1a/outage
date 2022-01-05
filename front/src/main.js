import Vue from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
// 引入ant-design依赖

// 使用vue开发模式
Vue.config.productionTip = true

// 全局使用AntDesign插件
Vue.use(Antd)

new Vue({
  render: h => h(App),
}).$mount('#app')

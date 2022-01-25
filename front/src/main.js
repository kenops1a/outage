import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from '@/router/router'
import store from '@/store/store'

// 使用vue开发模式
Vue.config.productionTip = true

// 路由跳转之后，滚动条复位
router.afterEach((to,from,next) => {
  window.scrollTo(0,0);
});

// 登录拦截
router.beforeEach((to, from, next) => {
  if (to.meta.auth) {  // 判断该路由是否需要登录权限
    if (store.state.loginStatus === 1) {  // 通过vuex state获取当前的token是否存在
      next();
    }
    else {
      next({
        path: '/login',
        query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
      })
    }
  }
  else {
    next();
  }
})

new Vue({
  vuetify,
  router,
  // 跟router一样，将我们创建的vuex实例挂载到vue实例中
  store,
  render: h => h(App)
}).$mount('#app')

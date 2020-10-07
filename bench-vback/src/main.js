// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
//自己导入的组件
//import axios from 'axios'
//import VueAxios from 'vue-axios'
import elementui from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueResource from 'vue-resource'
import store from './store/index'



// 设置反向代理，前端请求默认发送到 http://localhost:8006/api
const axios = require('axios');
axios.defaults.baseURL="http://localhost:8006/api"

// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios=axios

Vue.config.productionTip = false

//Vue.use(VueAxios, axios)
Vue.use(elementui)
Vue.use(VueResource);
/**
 * 这个的逻辑很简单，首先判断访问的路径是否需要登录，
 * 如果需要，判断 store 里有没有存储 user 的信息，
 * 如果存在，则放行，
 * 否则跳转到登录页面，并存储访问的页面路径（以便在登录后跳转到访问页）。
 */
router.beforeEach((to,from,next)=>{
  if(to.meta.requireAuth){
    if(store.state.muser.maccount){
      next()
    }else {
      next({
        path:'login',
        query:{redirect:to.fullPath}
      })
    }
  }else {
    next()
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>'
})

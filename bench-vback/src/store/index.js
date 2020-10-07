import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state={
  //左侧菜单折叠状态
  collapsed:false,
  topNavState: 'home',
  leftNavState: 'dispatch'
}
/**
 * 这里用到了 localStorage，即本地存储，在项目打开的时候会判断本地存储中是否有 user 这个对象存在，
 * 如果存在就取出来并获得 username 的值，否则则把 username 设置为空。
 * 这样我们只要不清除缓存，登录的状态就会一直保存。
 */
export default new Vuex.Store({
  state:{
    muser:{
      maccount:window.localStorage.getItem("muser"||'[]')==null?
      '': JSON.parse(window.localStorage.getItem("muser"||'[]')).maccount
    }
  },
  mutations:{
    login(state,muser){
      state.muser=muser
      window.localStorage.setItem("muser",JSON.stringify(muser))
    }
  }
})

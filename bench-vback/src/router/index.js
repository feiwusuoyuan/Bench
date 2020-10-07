import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import element from 'element-ui'
// 导入自己编写的组件
import Login from "../components/Login";//管理员登录
import Appindex from "../components/home/Appindex";//首页
import Home from "../components/Home";//主
import LeftNavMenu from "../components/common/LeftNavMenu";//左侧导航栏
import TopNavMenu from "../components/common/TopNavMenu";//顶部导航栏
import Article from "../components/views/article/Article";//贴子
import ModeratePost from "../components/views/article/ModeratePost";//审核帖子
import PostManage from "../components/views/article/PostManage";//帖子管理
import ViewComments from "../components/views/article/ViewComments";//查看评论
import User from "../components/views/user/User";//用户
import UserManage from "../components/views/user/UserManage";//用户管理
import CertificationAudit from "../components/views/user/CertificationAudit";//认证审核
import profile from "../components/views/user/profile";//管理员个人信息
import changepwd from "../components/views/user/changepwd";//修改管理员密码
import Publish from "../components/views/Publish/Publish";//发表
import Announcement from "../components/views/Publish/Announcement";//发表公告
import Advertise from "../components/views/Publish/Advertise";//发表广告

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    /*登录的路由*/
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    /*主页路由配置*/
    {
      path: '/',
      // 根据type区分不同模块（顶部导航）
      type:'Home',
      // 根据name区分不同子模块（左侧导航）
      name: 'Home',
      component: Home,
      //暂不需要访问Home页面,重定向index页面
      redirect:'/index',
      children: [
        /*展示主页*/
        {
          path: '/index',
          name: '首页',
          components:{
           /* //右侧内容区域*/
            default:Appindex,
            //头部导航区域
            top:TopNavMenu,
            //左侧导航区域
            left:LeftNavMenu,
          },
          //只有一个节点
          leaf:true,
          /*icon样式配置*/
          iconCls:'el-icon-s-home',
          menuShow:true,
          //是否需要检测
          meta: {
            requireAuth: true
          }
        },
        /*用户的相关操作*/
        {
          path:'/user',
          components: {
            default:User,
            top:TopNavMenu,
            left:LeftNavMenu,
          },
          name:'用户',
          iconCls:'el-icon-s-custom',
          menuShow:true,
          children:[
            {path:'/user/manger',component:UserManage,name:'用户管理',menuShow:true},
            {path:'/user/caudit',component:CertificationAudit,name:'认证审核',menuShow:true},
          ],
        },
        /*贴子的相关管理*/
        {
          path: '/post',
          name:'贴子',
          components: {
            default:Article,
            top:TopNavMenu,
            left:LeftNavMenu,
          },
          iconCls:'el-icon-document-remove',
          menuShow:true,
          children:[
            {path:'/post/moderate',component:ModeratePost,name:'审核帖子',menuShow:true},
            {path:'/post/manger',component:PostManage,name:'贴子管理',menuShow:true},
            {path:'/post/comment',component:ViewComments,name:'展示评论',menuShow:true},
          ],
        },
        /*发表公告*/
        {
          path: '/publish',
          name:'公告',
          components: {
            default:Publish,
            top:TopNavMenu,
            left:LeftNavMenu,
          },
          iconCls:'el-icon-edit',
          menuShow:true,
          children:[
            {path:'/announ/Advertise',component:Advertise,name:'推送广告',menuShow:true},
            {path:'/announ/Announcement',component:Announcement,name:'发表公告',menuShow:true}
          ]
        },
        /*管理员个人中心*/
        {
          path: '/user/profile',
          name:'管理员个人中心',
          components: {
            default:profile,
            top:TopNavMenu,
            left:LeftNavMenu,
          },
          iconCls:'el-icon-setting',
          leaf:true,
          menuShow:true,
        },
        /*管理员密码修改*/
        {
          path: '/user/chagepwd',
          name:'管理员密码修改',
          components: {
            default:changepwd,
            top:TopNavMenu,
            left:LeftNavMenu,
          },
          iconCls:'el-icon-warning-outline',
          leaf:true,
          menuShow:true,
        },
      ]
    },
  ]
})

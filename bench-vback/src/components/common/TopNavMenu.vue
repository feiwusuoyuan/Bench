<!--头部导航栏-->
<template>
  <el-row class="top-container" style="border:gold 2px solid;height: 65px; margin-left: -28px;
    margin-right: -28px;
    margin-top: -8px;">
    <!--头部-->
    <el-col :span="24" class="top-content" >
      <!--头部logo区域-->
      <div class="top-icon" style="width: 200px;height: 60px;float: left">
        <div class="top-logoicon" style="float: left">
          <a href="/"><img src="../../assets/bench.png" style="padding-left:8px;width: 50px"></a>
        </div>
        <div class="top-logotitle" style="float: left;margin-top: 15px;font-size: 20px">
          <a href="/" style="color: #000000;">Bench社区管理</a>
        </div>
      </div>

      <div class="top-main" style="
      width: 300px;
    height: 61px;
    float: left;
    display: block;
    border: 1px red solid;">主题
        <!-- 注意：这里就是topNavState作用之处，根据当前路由所在根路由的type值判断显示不同顶部导航菜单 -->
        <!--<el-row v-show="$store.state.topNavState==='home'">
          <el-col :span="24">
            <el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab">
              <el-tab-pane
                v-for="(item, index) in editableTabs"
                :key="item.name"
                :label="item.title"
                :name="item.name" >
                {{item.content}}
              </el-tab-pane>
            </el-tabs>
            <div style="margin-bottom: 20px;">
              <el-button size="small" @click="addTab(editableTabsValue)">add tab</el-button>
            </div>
          </el-col>
        </el-row>-->
      </div>

      <div class="top-user" style="float: right; width: 200px; height: 65px; ">
        <el-dropdown trigger="click" style="margin-top: 21px;font-size: 24px;margin-right: -64px;"  >
          <span class="el-dropdown-link" >
            <i class="el-icon-user-solid"></i> admin
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <!--修改管理员个人信息-->
            <el-dropdown-item>
              <div @click="jumpTo('/user/profile')"><span style="color: #555;font-size: 14px;">个人信息</span></div>
            </el-dropdown-item>
            <!--修改管理员密码-->
            <el-dropdown-item>
              <div @click="jumpTo('/user/chagepwd')"><span style="color: #555;font-size: 14px;">修改密码</span></div>
            </el-dropdown-item>
            <!--管理员退出系统-->
            <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>

    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "TopNavMenu",
  data(){
    return {
      musername:'',
      loading: false,
      companyName: '',
      nickname: '',
      defaultActiveIndex: '/',
      homeMenu: false,
      messageCount: 5
     }
  },
  created() {
    // 组件创建完后获取数据
    this.fetchNavData();
  },
  methods: {
    addTab(targetName) {
      let newTabName = ++this.tabIndex + '';
      this.editableTabs.push({
        title: 'New Tab',
        name: newTabName,
        content: 'New Tab content'
      });
      this.editableTabsValue = newTabName;
    },
    removeTab(targetName) {
      let tabs = this.editableTabs;
      let activeName = this.editableTabsValue;
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
            }
          }
        });
      }

      this.editableTabsValue = activeName;
      this.editableTabs = tabs.filter(tab => tab.name !== targetName);
    },
    jumpTo(url){
      this.$router.push(url); //用go刷新
    },
    handleSelect(index){
      this.defaultActiveIndex = index;
    },
    fetchNavData () { // 初始化菜单激活项
      let cur_path = this.$route.path; //获取当前路由
      let routers = this.$router.options.routes; // 获取路由对象
      let nav_type = "", nav_name = "";
      for (var i = 0; i < routers.length; i++) {
        let children = routers[i].children;
        if(children){
          for (let j = 0; j < children.length; j++) {
            if (children[j].path === cur_path) {
              nav_type = routers[i].type;
              nav_name = routers[i].name;
              break;
            }
            // 如果该菜单下还有子菜单
            if(children[j].children) {
              let grandChildren = children[j].children;
              for(let z=0; z<grandChildren.length; z++) {
                if(grandChildren[z].path === cur_path) {
                  nav_type = routers[i].type;
                  nav_name = routers[i].name;
                  break;
                }
              }
            }
          }
        }
      }
      this.$store.state.topNavState = nav_type;
      this.$store.state.leftNavState = nav_name;
      if(nav_type == "home"){
        this.defaultActiveIndex = "/";
      } else {
        this.defaultActiveIndex = "/" + nav_name + "Manager";
      }
    },
    logout(){
      //logout
      this.$confirm('确认退出吗?', '提示', {
        confirmButtonClass: 'el-button--warning'
      }).then(() => {
        //确认
        localStorage.removeItem('access-user');
        road.$emit('goto', '/login');
      }).catch(() => {});
    }
  },
  mounted() {
    let user = window.localStorage.getItem('access-user');
    if (user) {
      user = JSON.parse(user);
      this.nickname = user.nickname || '';
      this.companyName = user.companyName || '';
    }
  },
  watch: {
    '$route': function(to, from){ // 路由改变时执行
      //console.info("to.path:" + to.path);
      this.fetchNavData();
    }
  }
}
</script>

<style  scoped>
*{
  background-color: #c0caea
}
a{
  text-decoration: none;
}
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>

<template>
  <el-aside :width="iscollapsed?'64px':'200px'">
    <!--左侧导航-->
    <aside :class="{showSidebar:!iscollapsed}"
           style="margin-left: -8px;margin-top: -1px;height: 850px">
      <!--展开折叠开关-->
      <div class="menu-toggle" @click.prevent="iscollapse" >
        <i class="el-icon-s-fold" v-show="!iscollapsed" title="收起"></i>
        <i class="el-icon-s-unfold" v-show="iscollapsed" title="展开"></i>
      </div>
      <!--导航菜单--><!--unique-opened 只能开启一个二级菜单-->
      <el-menu  :default-active="defaultUrl"
                router
                :collapse="iscollapsed"
                ref="leftNavigation" >
        <template v-for="(issue,index) in $router.options.routes">
          <!-- 注意：这里就是leftNavState状态作用之处，当该值与router的根路由的name相等时加载相应菜单组 -->
          <template v-if="issue.name === $store.state.leftNavState">
            <template v-for="(item,index) in issue.children">
              <el-submenu v-if="!item.leaf" :index="index+''" v-show="item.menuShow">

                <template slot="title">
                  <i :class="item.iconCls"></i><span slot="title" style="color: #000000">{{item.name}}</span>
                </template>

                <el-menu-item v-for="term in item.children" :key="term.path" :index="term.path" v-if="term.menuShow"
                              :class="$route.path==term.path?'is-active':''" style="background: #000000;color: #000000">
                  <i :class="term.iconCls"></i><span slot="title" >{{term.name}}</span>
                </el-menu-item>

              </el-submenu>

              <el-menu-item v-else-if="item.leaf" :index="item.path"
                            :class="$route.path==item.path?'is-active':''" v-show="item.menuShow">
                <i :class="item.iconCls"></i><span slot="title" style="color: #000000">{{item.name}}</span>
              </el-menu-item>

            </template>
          </template>
        </template>
      </el-menu>
    </aside>
  </el-aside>
</template>

<script>
export default {
  name: "LeftNavMenu",
  data () {
    return {
      loading: false,
      iscollapsed: this.$store.state.iscollapsed,
      defaultUrl:'/index',
    }
  },
  methods: {
    //折叠导航栏
    iscollapse: function () {
      this.iscollapsed = !this.iscollapsed;
      this.$store.state.iscollapsed = this.iscollapsed;
    },
    // 左侧导航栏根据当前路径默认打开子菜单（如果当前是二级菜单，则父级子菜单默认打开）
    defaultLeftNavOpened () {
      let cur_path = this.$route.path; //获取当前路由
      let routers = this.$router.options.routes; // 获取路由对象
      let subMenuIndex = '', needOpenSubmenu = false;
      for (let i = 0; i < routers.length; i++) {
        let children = routers[i].children;
        if(children){
          for (let j = 0; j < children.length; j++) {
            if(children[j].path === cur_path) {
              break;
            }
            // 如果该菜单下还有子菜单
            if(children[j].children && !children[j].leaf) {
              let grandChildren = children[j].children;
              for(let z=0; z<grandChildren.length; z++) {
                if(grandChildren[z].path === cur_path) {
                  subMenuIndex = j;
                  needOpenSubmenu = true;
                  break;
                }
              }
            }
          }
        }
      }
      if(this.$refs['leftNavigation'] && needOpenSubmenu) {
        this.$refs['leftNavigation'].open(subMenuIndex); // 打开子菜单
      }
    },
    //获取当前路径
    getPath(){
      this.defaultUrl = this.$route.path;
    }
  },
  watch: {
    '$route': 'getPath'
      /*function(to, from){ // 路由改变时执行
      //console.info("to.path:" + to.path);
    }*/
  },
  mounted() {
    this.defaultLeftNavOpened();
  },

}
</script>

<style scoped>
*{
  background-color: #c0caea
}
</style>

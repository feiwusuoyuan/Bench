<template>
  <div>
    <el-container>
      <!--头部导航区-->
      <el-header>
        <router-view name="top"></router-view>
      </el-header>
      <el-container style="margin-left: -7px; margin-top: -2px;">
        <!--左侧导航区-->
          <router-view name="left"></router-view>
        <!--右侧内容区-->
        <el-main class="commain" style="width: 100%">
          <router-view/>
        </el-main>

      </el-container>
    </el-container>

  </div>
</template>

<script>
import TopNavMenu from "./common/TopNavMenu";

export default {
  name: "Home",
}
</script>

<style scoped>
/*.commain{
  background: url("../assets/mbackg.jpg") no-repeat center;
  background-size:80% 100%;
  position: fixed;
  margin-top: -8px;
  margin-left: -8px;
}*/
</style>

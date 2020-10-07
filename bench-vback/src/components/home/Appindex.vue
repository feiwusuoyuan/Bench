<template>
<div>
  <div class="show-articles"
       style="
              box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
              width: 800px;height: 800px">
    <h2 style="color: white">文章总览</h2>
    <div class="show-article" v-for="a in article" style="color: black;font-family: Arial;font-size: 24px">
     <el-divider></el-divider >
        <h3 style="color: #fc0707">标题：{{a.atitle}}</h3>
       <!-- 作者：<span>{{a.user.account}}</span>-->
        发表时间：<span style="color: gold"> {{dateFormat(a.sendtime)}}</span>
    </div>
  </div>
  <div style="float: right;width: 800px">
  <el-calendar v-model="value"> </el-calendar>
  </div>
</div>
</template>

<script>
export default {
  name: "Appindex",
  data() {
    return {
      value: new Date(),
      username:'',
      article: [],
    };
  },
  created() {
    this.query();
  },
  methods: {
    /* 日期格式化 */
    dateFormat(time) {
      var date=new Date(time);
      var year=date.getFullYear();
      /* 在日期格式中，月份是从0开始的，因此要加0
       * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
       * */
      var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
      var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
      var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
      var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
      var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
      // 拼接
      return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    },
    query(){
      this.$axios.get('/articles').then(res=>{
          console.info(res.data.content);
          this.article=res.data.content;
      })
    },
  }

}
</script>

<style scoped>
.show-articles{
  background: url("../../assets/mbackg.jpg") no-repeat center;
  background-size:100% 100%;
  position: fixed;
}
</style>

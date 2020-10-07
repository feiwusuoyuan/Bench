<!--发表公告：管理员在此页面进行对社区的公告发表。如有遗漏，及时补充-->
<template>
  <div style="text-align: center">
    <h1 >发表公告</h1>
    <el-input v-model="content" placeholder="请输入内容" style="width: 400px;" label="公告内容：" type="textarea"  :rows="8"></el-input><br>
    <el-input v-model="url" placeholder="请输入链接" style="width: 400px;margin-top: 10px"label="公告链接："></el-input><br>
    <el-button style="margin-top: 10px" type="success" icon="el-icon-edit" circle>发表</el-button>
  </div>
</template>

<script>
export default {
  name: "Announcement",
  data(){
    return{
      content:'',
      url:'',
    }
  },
  created() {

  },
  methods:{
    save(){
      var url="";
      var params=new URLSearchParams();
      params.append("content",this.content);
      params.append("url",this.url);
      this.$axios.get({url:url,params}).then(response=>{
        const res = response.data;
        this.tableData = res.content; //记录的内容
      });
    }
  }
}
</script>

<style scoped>

</style>

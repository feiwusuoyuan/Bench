<!--认证审核：用户的认证信息审核-->
<template>
<div>
  <el-table :data="tableData" height="800"  border  style="width: 100% ;text-align: center">
    <el-table-column prop="id"  label="序号" width="180" align="center">  </el-table-column>
    <el-table-column prop="vname"  label="认证名称" width="240" align="center">  </el-table-column>
    <el-table-column prop="vinfo"  label="认证信息" width="240" align="center">  </el-table-column>
    <el-table-column prop="uid"  label="用户id" width="240" align="center">  </el-table-column>
    <el-table-column prop="status"  label="状态" width="180" align="center">  </el-table-column>
    <el-table-column prop="regtime"  label="申请时间" width="280" align="center" :formatter="carTimeFilter"></el-table-column>
    <el-table-column prop="name"  label="真实姓名" width="180" align="center">  </el-table-column>
    <el-table-column label="操作" fixed="right" width="120" align="center">
      <el-button circle size="mini" type="success" icon="el-icon-success" @click=""></el-button>
      <el-button circle size="mini" type="danger" icon="el-icon-error" @click=""></el-button>
    </el-table-column>
  </el-table>
</div>
</template>

<script>
import moment from 'moment';
export default {
  name: "CertificationAudit",
  data() {
    return {
      tableData: [{}]
    }
  },
  created() {
    this.query();
  },
  methods:{
    query(){
      this.$axios.get("/findcert").then(response=>{
        const res = response.data;
        this.tableData = res.content; //记录的内容
      })
    },
    //表格中日期过滤 YYYY-MM-DD
    carTimeFilter(row, column, cellValue, index) {
      const daterc = row[column.property]
      if(daterc !=null){
        return moment(daterc).format("YYYY-MM-DD hh:mm:ss")
      }
    }
  }

}
</script>

<style scoped>

</style>

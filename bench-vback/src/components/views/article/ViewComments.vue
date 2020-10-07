<!--查看评论：对用户的评论进行管理，如果有用户id即查看某用户的评论。如有遗漏，及时补充-->
<template>
  <div>
    <el-table :data="tableData" height="800"  border  style="width: 100% ;text-align: center">
      <el-table-column prop="id"  label="序号" width="180" align="center">  </el-table-column>
      <el-table-column prop="conent"  label="评论内容" width="700" align="center">  </el-table-column>
      <el-table-column prop="uid"  label="用户id" width="120" align="center">  </el-table-column>
      <el-table-column prop="aid"  label="文章id" width="120" align="center">  </el-table-column>
      <el-table-column prop="isadoption"  label="是否问题" width="120" align="center">  </el-table-column>
      <el-table-column prop="regtime"  label="评论时间" width="240" align="center" :formatter="carTimeFilter"></el-table-column>
      <el-table-column label="操作" fixed="right" width="160" align="center">
        <el-button circle size="mini" type="danger" icon="el-icon-error" @click="">删除</el-button>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import moment from 'moment';
export default {
  name: "ViewComments",
  data() {
    return {
      tableData: [{}]
    }
  },
  created() {
    this.query();
  },
  methods: {
    query() {
      this.$axios.get("/findcomm").then(response => {
        const res = response.data;
        this.tableData = res.content; //记录的内容
      })
    },
    //表格中日期过滤 YYYY-MM-DD
    carTimeFilter(row, column, cellValue, index) {
      const daterc = row[column.property]
      if (daterc != null) {
        return moment(daterc).format("YYYY-MM-DD hh:mm:ss")
      }
    }
  }
}
</script>

<style scoped>

</style>

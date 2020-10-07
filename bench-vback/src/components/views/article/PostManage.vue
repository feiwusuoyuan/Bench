<!--帖子管理：对已经审核通过的帖子进行管理，可置顶，加精，删除帖子等。如有遗漏，及时补充-->
<template>
  <div>
    <el-table :data="tableData"
              style="width: 100%;height:100%;font-size: 18px" height="800"
              fit
              :header-cell-style="{background:'#F2F9FF', 'text-align':'center'}"
              :cell-style="{'text-align':'center'}">
      <el-table-column   type="index"  label="序号"  width="80"  align="center" ></el-table-column>
      <el-table-column label="文章标题"  width="200" align="center">
        <template slot-scope="scope">
          <span >{{ scope.row.atitle}}</span>
        </template>
      </el-table-column>
      <el-table-column label="文章内容"  width="280" align="center">
        <template slot-scope="scope">
          <span >{{ (scope.row.acontent).substring(0,50) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发表时间"  width="180" align="center">
        <template slot-scope="scope">
          <span >{{ dateFormat(scope.row.sendtime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否问题"  width="100" align="center">
        <template slot-scope="scope">
          <span >{{ scope.row.isproblem }}</span>
        </template>
      </el-table-column>
      <el-table-column label="积分悬赏"  width="120" align="center">
        <template slot-scope="scope">
          <span >{{ (scope.row.money)==null?"暂无悬赏":(scope.row.money)}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否加精"  width="120" align="center">
        <template slot-scope="scope">
          <el-select v-model="value" placeholder="请选择" :default-value="scope.row.boutique">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="是否置顶"  width="120" align="center">
        <template slot-scope="scope">
          <el-select v-model="value" placeholder="请选择" :default-value="scope.row.top">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="是否解决"  width="120" align="center">
        <template slot-scope="scope">
          <span >{{ scope.row.solve }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核状态"  width="100" align="center">
        <template slot-scope="scope">
          <span >{{ scope.row.status }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" width="120" align="center">
        <template slot-scope="scope">
          <el-button circle size="mini" type="success" icon="el-icon-edit" @click="save(scope.$index, scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "PostManage",
  data() {
    return {
      tableData: [{}],
      options: [{
        value: '0',
        label: '是'
      }, {
        value: '1',
        label: '否'
      }],
      value: ''
    }
  },
  created() {
    this.query();
  },
  methods: {
    query() {
      this.$axios.get("/findarticle").then(response => {
        const res = response.data;
        this.tableData = res.content; //记录的内容
      })
    },
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
  }
}
</script>

<style scoped>

</style>

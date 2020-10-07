<!--用户管理：对用户进行管理，查看用户信息，对用户进行操作，查看用户的评论（跳转至"查看评论页面"）。如有遗漏，及时补充-->
<template>
  <div>
    <div>
      <el-table :data="List"
                style="width: 100%;height:100%;font-size: 24px" height="800"
                fit
                :header-cell-style="{background:'#F2F9FF', 'text-align':'center'}"
                :cell-style="{'text-align':'center'}"
                @sort-change="changeUsertableSort">
        <el-table-column   type="index"  label="序号"  width="80"  align="center" ></el-table-column>
        <el-table-column label="用户ID"  width="140"  align="center" prop="id" :sortable="'custom'">
          <template slot-scope="scope">
            <span >{{ scope.row.id }}</span>
          </template>
        </el-table-column>
        <el-table-column label="用户账户"  width="120" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.account }}</span>
          </template>
        </el-table-column>
        <el-table-column label="用户邮箱"  width="120" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.email }}</span>
          </template>
        </el-table-column>
        <el-table-column label="用户地址"  width="120" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.address==null?"火星":scope.row.address }}</span>
          </template>
        </el-table-column>
        <el-table-column label="用户电话"  width="120" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.phone}}</span>
          </template>
        </el-table-column>
        <el-table-column label="用户等级"  width="120" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.ulevel }}</span>
          </template>
        </el-table-column>
        <el-table-column label="注册时间"  width="140" align="center">
          <template slot-scope="scope">
            <span >{{ dateFormat(scope.row.regtime) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="个性签名"  width="150" align="center">
          <template slot-scope="scope">
            <el-input v-show="scope.row.edit" v-model="scope.row.signature" size="small"/>
            <span v-show="!scope.row.edit">{{ scope.row.signature==null?"这家伙很懒，什么都没留下":scope.row.signature }}</span>
          </template>
        </el-table-column>
        <el-table-column label="用户财富"  width="120" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.urich }}</span>
          </template>
        </el-table-column>
        <el-table-column label="vip等级"  width="120" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.uviplevel==null?"普通用户":scope.row.uviplevel }}</span>
          </template>
        </el-table-column>
        <el-table-column label="认证信息"  width="120" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.certificainfo==null?"暂无认证":scope.row.certificainfo }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="180" align="center">
          <template slot-scope="scope">
            <el-button circle v-show="!scope.row.edit" size="mini" type="primary" icon="el-icon-edit"  @click="changebutton"></el-button>
            <el-button circle v-show="scope.row.edit" size="mini" type="success" icon="el-icon-edit"  @click="changebutton"></el-button>
            <el-button circle size="mini" type="danger" icon="el-icon-delete" @click="del(scope.$index, scope.row)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="divpagenation">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage4"
        :page-sizes="[10, 15, 20, 50]"
        :page-size="sortjsontableData.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserManage",
  data(){
   return {
     total: null,
     currentPage4: 1,
     // 正序逆序get请求提交的字段
     sortjsontableData: {
       page: 1, // 第几页
       size: 10, // 每页显示多少条
       sortType: 'ascending', //正序ascending逆序descending
       sortableFields: 'id', //需要排序的字段
     },
     List:[],
   }
  },
  created() {
    this.query();
  },
  methods:{
    query(){
      this.$axios.get('/finduser/1/10').then(response=>{
        const res = response.data;
        this.List = res.content; //记录的内容
        this.total = res.totalElements; //总记录数
        this.sortjsontableData.size = res.size; //每页条数
      })
    },
    //改变·每页显示的条数
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`);
      this.sortjsontableData.size = val; //赋值显示条数给处理分页handleCurrentChange()使用
      this.$axios.get( '/findandsortPageable/'
        +this.sortjsontableData.page+'/'
        +this.sortjsontableData.size+'/'
        +this.sortjsontableData.sortType+'/'
        +this.sortjsontableData.sortableFields)
        .then(response => {
        const res = response.data;
        this.Lsit = res.content; //记录的内容
        // this.total = res.totalElements; //总记录数
      })
    },
    // 处理分页
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`);
      this.sortjsontableData.page = val; //赋值第几页给处理当前页handleSizeChange()使用
      this.$axios.get( '/findandsortPageable/'
        +this.sortjsontableData.page+'/'
        +this.sortjsontableData.size+'/'
        +this.sortjsontableData.sortType+'/'
        +this.sortjsontableData.sortableFields)
        .then(response => {
        const res = response.data;
        this.List = res.content; //记录的内容
        // this.total = res.totalElements; //总记录数
      })
    },
    // 与后端通讯进行逆序排序
    changeUsertableSort(column) {
      // this.$message.info(column);
      // console.log("这是column对象"+column);
      //获取字段名称和排序类型
      let columnName = column.prop;
      let sortingType = column.order;
      // this.sortjsontableData.page = this.whichpage;
      // this.sortjsontableData.size = this.currentpagesize;
      this.sortjsontableData.sortType = sortingType;
      this.sortjsontableData.sortableFields = columnName;
      this.$message.info("排序类型为："+sortingType+",排序列字段为:"+columnName);
      this.$axios.get( '/findandsortPageable/'
        +this.sortjsontableData.page+'/'
        +this.sortjsontableData.size+'/'
        +this.sortjsontableData.sortType+'/'
        +this.sortjsontableData.sortableFields)
        .then(response => {
        // '/userfindAll/' + this.whichpage + '/'+val+''
        const res = response.data;
        this.UsertableData = res.content; //记录的内容
        // this.total = res.totalElements; //总记录数
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
    changebutton(){
      this.scope.row.edit=!this.scope.row.edit;
    }
  }
}
</script>

<style scoped>

</style>

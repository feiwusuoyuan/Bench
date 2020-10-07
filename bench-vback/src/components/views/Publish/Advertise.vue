<!--发表广告-->
<template>
  <div>
    <el-form :model="tableData"   label-width="100px" class="demo-ruleForm" id="fi">
      <el-form-item label-width="80px" label="广告图片">
        <el-upload
          style="float: left;"
          class="avatar-uploader"
          action=""
          :limit=1
          :auto-upload=false
          :on-change="onchange"
          :on-remove="handleRemove"
          drag >
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="投放天数" prop="level">
        <el-input-number style="float:left;"
          v-model="num" @change="handleChange" :min="1" :max="90" label="描述文字"></el-input-number>
      </el-form-item>
      <el-form-item label="广告链接" prop="NUM">
        <el-input style="width: 400px;float: left" placeholder="请输入链接"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button circle style="float: left" type="primary" @click="add('')">推送</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Advertise",
  data(){
    return {
      tableData: [{}],
      num: 1
    }
  },
  methods:{
    /* 添加广告 */
    add(){
      var url="../../Addp.do?op=addmr";
      var params=new URLSearchParams();
      params.append("name",this.ruleForm.name);
      params.append("price",this.ruleForm.price);
      params.append("level",this.ruleForm.level);
      params.append("image",this.imagename);
      axios({
        url:url,
        params
      }).then(res=>{
        alert(res.data.msg);
        if(res.data.msg=="添加成功"){
          this.querycid();
          console.info("1");
        }
      })
    },
    /* 图片上传，将图片先用临时路径保存，并展示在文件框中 */
    onchange(file,filesList) {
      var windowURL = window.URL || window.webkitURL;
      this.imageUrl=windowURL.createObjectURL(file.raw);
      this.param = new FormData();
      this.param.append('file', file.raw, file.name);
      this.onSubmit();
    },
    /* 图片上传至服务器 */
    onSubmit(){//表单提交的事件
      var names = this.ruleForm.NAME;
      //下面append的东西就会到form表单数据的fields中；
      this.param.append('message', names);
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      };
      //然后通过下面的方式把内容通过axios来传到后台
      //下面的this.$reqs 是在主js中通过Vue.prototype.$reqs = axios 来把axios赋给它;
      axios.post("api/imgup", this.param, config).then(res=> {
        this.imagename=res.data.data;
      })
    },
    /* 重置输入信息 */
    handleRemove(file,filesList){
      this.param.delete('file')
    },
    handleChange(value) {
      console.log(value);
    },
  }
}

</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;

  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

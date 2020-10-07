<!--Benchs社区管理员登录页面，已基本完善，待以后全部完善。如有遗漏，及时补充-->
<template>
  <body id="poster">
    <el-form class="login-container" label-position="left"
             :model="muser" status-icon :rules="rules" ref="muser" label-width="100px" >
      <h3 class="login_title">管理员登录</h3>
      <div class="login-con">
       <!--管理员账号 -->
        <div class="login-user">
          <div class="icon1"><img src="src/assets/user_icon_copy.png" alt=""></div>
          <el-form-item  prop="maccount" >
            <el-input type="text" v-model="muser.maccount" autocomplete="off" placeholder="管理员账户"
                      style="margin-left: -95px"></el-input>
          </el-form-item>
        </div>
      <!--密码-->
        <div class="login-pwd">
          <div class="icon2"><img src="src/assets/lock_icon_copy.png" alt=""></div>
          <el-form-item  prop="pass">
            <el-input type="password" v-model="muser.pass" autocomplete="off" placeholder="密码"
                      style="margin-left:-95px;margin-top: 30px"></el-input>
          </el-form-item>
        </div>
      <!--管理员登录验证是否本人登录=》通过手机号发送短信-->
        <div class="login-yan">
          <div class="icon3"><img src="src/assets/key.png" alt=""></div>
          <el-form-item  prop="vcode">
            <el-input type="text" v-model="muser.vcode" autocomplete="off" placeholder="验证码"
                      style="margin-left:-95px;margin-top: 30px"></el-input>
          </el-form-item>
        </div>
        <!--按钮部分-->
        <div class="login-btn">
          <el-form-item style="width: 100%;margin-left:-45px">
            <el-button type="primary" @click="login()">登录</el-button>
            <!--<div class="removebtn">
            <el-button  @click="resetForm('muser')">重置</el-button>
            </div>-->
          </el-form-item>
        </div>
      </div>
    </el-form>
  </body>
</template>

<script>
export default {
  name: "Login",
  data(){
    /*账号输入检测*/
    var checkMaccount = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('账户不能为空'));
      } else {
          if (this.muser.maccount !== '') {
            this.$refs.ruleForm.validateField('checkMaccount');
          }
          callback();
        }
    };
    /*密码输入检测*/
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.muser.pass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    /*验证码检测*/
    var validatevcode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      } else if (value !== this.muser.pass) {
        callback(new Error('验证码不一致!'));
      } else {
        callback();
      }
    };
    return{
      muser: {
        maccount: '',
        pass: '',
        vcode: '',
      },
      rules: {
        maccount: [
          { validator: checkMaccount, trigger: 'blur' }
        ],
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        vcode: [
          { validator: validatevcode, trigger: 'blur' }
        ],
      }
    }
  },
  methods:{
    /*登录*/
    login(){
      var _this=this
      console.log(this.$store.state)
      this.$axios.post('/login',{
        maccount:this.muser.maccount,
        pwd:this.muser.pass,
      }).then(res=>{
        if(res.data.code===1){
          //this.submitForm(formName);
          _this.$store.commit('login',_this.muser)
          var path=this.$route.query.redirect
          this.$router.replace({path: path ==='/'|| path===undefined?'/index':path})
        }
      }).catch({})
    },
    //提交
    /*submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },*/
    /*清空输入框*/
   /* resetForm(formName) {
      this.$refs[formName].resetFields();
    }*/
  }
}
</script>

<style scoped>
*{
  margin: 0;
  padding: 0;
  color: #ffffff;
}
body {
  background: url("../assets/bg.jpg") no-repeat center;
  height: 100%;
  width: 100%;
  background-size:100% 100%;
  position: fixed;
  margin-top: -8px;
  margin-left: -8px;
}
.login-container{
  background: linear-gradient(230deg, rgba(53, 57, 74, 0) 0%, rgb(0, 0, 0) 100%);
  width: 540px;
  height: 420px;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  padding: 100px 40px 40px 40px;
  box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
}
.login_title {
  margin: -20px auto 40px auto;
  text-align: center;
  color: #f10a2c;
  font-size:40px;
  font-family: Arial;
  font-style: normal;
}
.login-con{
  height: 208px;
  position: absolute;
  left: 0;
  width: 80%;
  margin:0 10%;
}
.login-user{
  position: relative;
}
.icon1{
  position: absolute;
  z-index: 1;
  left: 36px;
  top: 8px;
  opacity: 1;
}
.icon2{
  position: absolute;
  z-index: 1;
  left: 36px;
  top: 8px;
  opacity: 1;
  margin-top: 35px;
}
.icon3{
  position: absolute;
  z-index: 1;
  left: 36px;
  top: 8px;
  opacity: 1;
  margin-top: 35px;
}
/*.login-con input{
  width: calc(100% - 130px);
  margin-top: 20px;
  margin-bottom: 30px;
  background: rgba(57, 61, 82, 0);
  left: 0;
  padding: 10px 65px;
  border-top: 2px solid rgba(57, 61, 82, 0);
  border-bottom: 2px solid rgba(57, 61, 82, 0);
  border-right: none;
  border-left: none;
  outline: none;
  font-family: 'Gudea', sans-serif;
  box-shadow: none;
  color: #61BFFF !important;
}*/
.login-pwd, .login-yan{
  position: relative;
}
.login-btn{
  width: 100%;
  margin: 0 auto;
  position: relative;
}
.login-btn button{
  border-radius: 3px;
  background: transparent;
  border: 2px solid #4FA1D9;
  color: #4FA1D9;
  text-transform: uppercase;
  font-size: 11px;
  cursor: pointer;
  position: absolute;
  top: 50px;
  left: 0;
  right: 0;
  margin: auto;
  width: 60%;
  height: 35px;
  transition-property: background,color;
  -webkit-transition-duration: .2s;
  transition-duration: .2s;
}
.login-btn button:hover{
  color: white !important;
  background: #4FA1D9;
  cursor: pointer;
  -webkit-transition-property: background,color;
  transition-property: background,color;
  -webkit-transition-duration: .2s;
  transition-duration: .2s;
}
</style>

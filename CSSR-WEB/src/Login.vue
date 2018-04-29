<template>
  <div class="main">
  <div id="login">
    <div class="login_header">
      <span>欢迎登录</span>
    </div>
    <div class="login_body">
      <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名:" prop="name">
          <el-input type="text" v-model="ruleForm2.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="密 码:" prop="password">
          <el-input type="password" v-model="ruleForm2.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="验证码:" prop="code">
          <el-input v-model="ruleForm2.code" style="width: 150px"></el-input>
          <img :src="imgUrl" alt="" class="login_img" @click="reflushImg">
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm2')">登录</el-button>
          <el-button @click="resetForm('ruleForm2')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
  </div>
</template>
0

<script>
    export default {
        name: "login",
        data(){
          var checkCode = (rule, value, callback) => {
            if (!value) {
              return callback(new Error('验证码不能为空'));
            }

            setTimeout(() => {
              this.$http.get('/api/get-random').then((res)=> {
                let code = res.body.code;
                if (code === '200' && res.body.data === value) {
                  callback();
                } else if(code === '201'){
                  callback(new Error("验证码过时"));
                }
                  else{
                  callback(new Error("验证码错误"));
                }
              });
            }, 1000);
          };
          var validateName = (rule, value, callback) => {
            if (value === '') {
              return callback(new Error('请输入用户名'));
            } else {
              return callback();
            }
          };
          var validatePassword = (rule, value, callback) => {
            if (value === '') {
              return callback(new Error('请输入密码'));
            } else {
              return callback();
            }
          };
          return {
            imgUrl:'/api/get-code',
            ruleForm2: {
              name: '',
              password: '',
              code: ''
            },
            rules2: {
              name: [
                { validator: validateName, trigger: 'blur' }
              ],
              password: [
                { validator: validatePassword, trigger: 'blur' }
              ],
              code: [
                { validator: checkCode, trigger: 'blur' }
              ]
            }
          };
        },
      methods: {
          submitForm(formName) {
            this.$refs[formName].validate((valid) => {
              if (valid) {
                let sendData = {
                  manageName: this.ruleForm2.name,
                  managePwd:this.ruleForm2.password
                }
                this.$http.post('/api/manage/login',sendData).then((res)=>{
                  if(res.body.code == "200"){
                    let manage = res.body.data;
                    if(manage.manageId != ""){
                      this.$emit('manageSignIn', manage);
                      this.$router.push('/home')
                    }
                  }else{
                    this.$message.error(res.body.msg);
                  }
                });
              } else {
                console.log('error submit!!');
                return false;
              }
            });
          },
          resetForm(formName) {
            this.$refs[formName].resetFields();
          },
          reflushImg(){
            this.imgUrl += ("?d="+new Date());
          }
        }


    }
</script>

<style scoped>
  *{
    font-family: 微软雅黑;
  }
  #login{
    width: 600px;
    height: 450px;
    position:absolute;
    left:50%;
    top:50%;
    margin-left:-300px;
    margin-top:-225px;
    background: white;
  }
  .main{
    width: 100%;
    height: 1200px;
    background:url(assets/back.jpg) repeat center ;
  }
  .login_header{
    height: 80px;
    background: chocolate;
    width: 100%;
    text-align: center;
  }
  .login_header span{
    line-height: 80px;
    color: white;
    font-size: 30px;

  }
  .login_body{
    width: 70%;
    margin: 0 auto;
    padding-top: 50px;
  }
  .login_img{

  }
</style>

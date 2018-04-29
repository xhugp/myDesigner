<template>
  <div class="my-work">
  <el-form :model="newManage" :rules="rules" ref="newManage" label-width="100px" class="demo-ruleForm">
    <el-form-item label="登录名">
      <el-input v-model="newManage.manageName" disabled></el-input>
    </el-form-item>
    <el-form-item label="您的姓名" prop="manageMc" required>
      <el-input v-model="newManage.manageMc"></el-input>
    </el-form-item>
    <el-form-item label="您的邮箱" prop="manageEmail" required>
      <el-input v-model="newManage.manageEmail"></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="managePhone" required>
      <el-input v-model="newManage.managePhone"></el-input>
    </el-form-item>
    <el-form-item label="注册时间">
      <el-input v-model="newManage.createTime" disabled></el-input>
    </el-form-item>
    <el-form-item label="所在公司">
      <el-input v-model="newManage.companyName" disabled></el-input>
    </el-form-item>
    <el-form-item label="上次登录">
      <el-input  v-model="newManage.lastLoginTime" disabled></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('newManage')">提交修改</el-button>
      <el-button type="primary" @click="editPwd">修改密码</el-button>
    </el-form-item>
  </el-form>

    <el-dialog
      title="修改密码"
      :visible.sync="dialogVisible"
      width="30%">
    <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="ruleForm2.pass" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitPwd('ruleForm2')">提交</el-button>
        <el-button @click="resetPwd('ruleForm2')">重置</el-button>
      </el-form-item>
    </el-form>
    </el-dialog>

  </div>
</template>

<script>
    export default {
        name: "my-info",

      data() {
        var validatePhone = (rule, value, callback) => {
          if (!this.isPoneAvailable(value)) {
            callback(new Error('请输入正确的手机号'));
          } else {
            callback();
          }
        };
        var validatePass = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入密码'));
          } else {
            if (this.ruleForm2.checkPass !== '') {
              this.$refs.ruleForm2.validateField('checkPass');
            }
            callback();
          }
        };
        var validatePass2 = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请再次输入密码'));
          } else if (value !== this.ruleForm2.pass) {
            callback(new Error('两次输入密码不一致!'));
          } else {
            callback();
          }
        };
        return {
          manageId:sessionStorage.getItem("manageId"),
          dialogVisible:false,
          newManage:{
            manageMc:'',
            manageEmail:'',
            managePhone:''
          },
          rules: {
            manageMc: [
              { required: true, message: '请输入您的姓名', trigger: 'blur' },
              { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur,change' }
            ],
            manageEmail: [
              { required: true, message: '请输入您的邮箱地址', trigger: 'blur' },
              { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur,change' }
            ],
            managePhone: [
              { required: true, message: '请输入您的电话', trigger: 'blur' },
              { validator: validatePhone , trigger: 'blur,change' }
            ]
          },
          ruleForm2: {
            pass: '',
            checkPass: ''
          },
          rules2: {
            pass: [
              { validator: validatePass, trigger: 'blur' }
            ],
            checkPass: [
              { validator: validatePass2, trigger: 'blur' }
            ]
          }
        };
      },
      methods: {
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if(valid){
              let sendData = {
                manageId:this.newManage.manageId,
                manageMc:this.newManage.manageMc,
                managePhone:this.newManage.managePhone,
                manageEmail:this.newManage.manageEmail
              };
              this.$http.post('/api/manage/update',sendData ).then((res)=>{
                if(res.body.code == "200"){
                  this.$message.success("修改成功");                
                }else {
                  console.log(res);
                }
              });
            }
          });
        },
        submitPwd(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              let sendData = {
                manageId:this.newManage.manageId,
                managePwd:this.ruleForm2.pass
              };
              console.log(sendData);

              this.$http.post("/api/manage/update",sendData).then((res)=>{
                if(res.body.code == "200"){
                  this.$message.success("修改密码成功");
                  this.dialogVisible = false;
                }else {
                  console.log(res);
                }
              })
            } else {        
              return false;
            }
          });
        },

        resetPwd(formName) {
          this.$refs[formName].resetFields();
        },

        isPoneAvailable(str) {
          let myreg= /^[1][3,4,5,7,8][0-9]{9}$/;
          if (!myreg.test(str)) {
            return false;
          } else {
            return true;
          }
        },
        editPwd(){
          this.dialogVisible = true;
        }
      },
      mounted(){
          this.$http.get("/api/manage/get-by-id/"+this.manageId).then((res)=>{
            if(res.body.code == "200"){
              this.newManage = res.body.data;
            }else{
              console.log(res);
            }
          });
      }
    }
</script>

<style scoped>
  *{
    font-family: 微软雅黑;
  }
.my-work{
  width : 60%;
  margin: 0 auto;
}
</style>

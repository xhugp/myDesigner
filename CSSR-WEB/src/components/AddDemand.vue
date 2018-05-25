<template>
  <div class="add-demand">
    <el-form :model="newDemand" :rules="rules" ref="newDemand" label-width="100px" class="demo-ruleForm">
      <el-form-item label="需求标题" prop="demandTitle">
        <el-input v-model="newDemand.demandTitle" placeholder="请填写需求标题" required></el-input>
      </el-form-item>
      <el-form-item label="需求类型" prop="demandType">
        <el-select v-model="newDemand.demandType" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="薪酬" prop="demandRepay">
          <el-input-number v-model="newDemand.demandRepay" placeholder="请填写薪酬"></el-input-number>
      </el-form-item>
      <el-form-item label="需求内容" prop="demandContent">
        <el-input type="textarea" :rows='12' v-model="newDemand.demandContent"  placeholder="请输入需求内容（500以内）" required></el-input>
      </el-form-item>

      <el-form-item label="需求图片"  prop="demandImg">
        <el-upload
          action="http://upload.qiniu.com"
          :on-success="handleSuccess"
          :on-error="handleError"
          :on-remove="handleRemove"
          :data="postData"
          accept="image/gif,image/jpeg,image/jpg,image/bmp,image/png"
          :on-exceed="handleExceed"
          :limit="1"
          :on-change="handleChange"
          list-type="picture"
          :file-list="fileList"
          multiple>
          <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
          <div class="el-upload__tip" slot="tip">只能上传图片文件，且不超过2M</div>
        </el-upload>
      </el-form-item>

      <el-form-item label="备注" prop="demandRemark">
        <el-input type="textarea" :rows='4' v-model="newDemand.demandRemark"  placeholder="请输入备注（100以内）"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('newDemand')">立即创建</el-button>
        <el-button @click="resetForm('newDemand')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
      name: "add-demand",
      data() {
        return {
          options:[],
          fileList:[],
          newDemand: {
            demandTitle:'',
            demandType:'',
            demandRepay:'',
            demandContent:'',
            demandRemark:'',
            companyId:'',
            demandImg:''
          },
          imageUrl:'',
          postData:{
            token:''
          },
          rules: {
            demandTitle: [
              {required: true, message: '请输入需求标题', trigger: 'blur'},
              {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'change'}
            ],
            demandRepay: [
              {type : 'number',required: true, message: '请正确输入薪酬', trigger: 'change'}
            ],
            demandType:[
              {required: true, message: '请选择需求类型', trigger: 'blur,change'},
            ],
            demandImg:[
              {required: false, message: '请上传图片', trigger: 'blur,change'},
            ],
            demandContent: [
              {required: true, message: '请输入需求内容', trigger: 'blur'},
              {min: 1, max: 500, message: '长度在 1 到 500 个字符', trigger: 'change'}
            ],
            demandRemark: [
              {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'change'}
            ]
          }
        };
      },
      methods: {
        submitForm(newDemand) {
          this.$refs[newDemand].validate((valid) => {
            if (valid) {
              this.newDemand.demandImg = this.imageUrl;
              this.$http.post("/api/demand/add",this.newDemand).then((res)=>{
                if(res.body.code=='200'){
                  this.$message({
                    type:'success',
                    message:'创建成功'
                  });
                  this.resetForm(newDemand);
                }else{
                  this.$message.error('添加失败，稍后再试');
                  console.log(res);
                }
              });
            } else {
              console.log('error submit!!');
              return false;
            }
          });
        },
        resetForm(newDemand) {
          this.$refs[newDemand].resetFields();
        },
        handleSuccess(res, file, fileList){
          this.imageUrl = 'http://p6i3f0nn3.bkt.clouddn.com/'+ res.key;
        },
        handleError(res){
          console.log(res);
        },
        handleExceed(file, fileList){
          this.$message.error("只能上传一张图片，请删除再传");
        },
        handleChange(file, fileList) {
          const isLt2M = file.size / 1024 / 1024 < 2;
          if (!isLt2M) {
            this.$message.error('上传图片大小不能超过 2MB!');
            this.fileList = [];
            return false;
          }
          this.newDemand.demandImg = file.name;
        },
        handleRemove(file, fileList){
          this.newDemand.demandImg = '';
        }
      },
      mounted(){
        this.newDemand.companyId = sessionStorage.getItem("companyId");
        this.$http.get("/api/get-dtype").then((res)=> {
          this.options = res.body.data;
        });
        this.$http.get("/api/get-uptoken").then((res)=>{
          this.postData.token = res.body.data;
        });
      }
    }
</script>

<style scoped>
  *{
    font-family: 微软雅黑;
  }
  .add-demand{
    width : 50%;
    margin-left: 50px;
    margin-top: 20px;
  }
</style>

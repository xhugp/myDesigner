<template>
    <div class="factory-info">
      <el-container style="width: 100%;height: 100%">
        <el-aside width="40%">
          <el-form :model="newCompany" :rules="rules" ref="newCompany" style="width: 80%;margin: 0 auto;" class="demo-ruleForm">
            <el-form-item label="公司名称" prop="companyName">
              <el-input v-model="newCompany.companyName" @change="isChange"  required></el-input>
            </el-form-item>
            <el-form-item label="公司地址" prop="companyAddress">
              <el-input v-model="newCompany.companyAddress" @change="isChange"  required></el-input>
            </el-form-item>

            <el-form-item label="公司电话" prop="companyPhone">
              <el-input v-model="newCompany.companyPhone" @change="isChange"  required></el-input>
            </el-form-item>
            <el-form-item label="公司邮箱" prop="companyEmail">
              <el-input v-model="newCompany.companyEmail" @change="isChange"  required></el-input>
            </el-form-item>

            <el-form-item>
              <el-button @click="submitForm('newCompany')" type="primary" :disabled="isDisable">提交修改</el-button>
              <el-button @click="dialogFormVisible=true" type="primary" v-if="manageRole==0">添加管理员</el-button>
              <el-button @click="dialogTypeVisible=true" type="primary" v-if="manageRole==0">添加类别</el-button>
            </el-form-item>
          </el-form>
        </el-aside>

        <el-dialog title="添加管理员" :visible.sync="dialogFormVisible" width="500px">
          <el-form :model="newManage" ref="newManage" class="demo-ruleForm">
            <el-form-item label="管理员登录名" :label-width="formLabelWidth" prop="toolName">
              <el-input v-model="newManage.manageName" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="管理员角色" :label-width="formLabelWidth" prop="toolType">
              <el-select v-model="newManage.manageRole" placeholder="选择类别搜索">
                <el-option
                  v-for="item in roles"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="addManage">添 加</el-button>
          </div>
        </el-dialog>

        <el-dialog title="添加类别" :visible.sync="dialogTypeVisible" width="500px">
          <el-form :model="newType" ref="newType" class="demo-ruleForm">
            <el-form-item label="添加选择" :label-width="formLabelWidth">
              <el-select v-model="newType.type" placeholder="选择类别添加">
                <el-option
                  v-for="item in types"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="类型名称" :label-width="formLabelWidth" prop="name">
              <el-input v-model="newType.name" auto-complete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="addType">添 加</el-button>
          </div>
        </el-dialog>


        <el-main>
          <el-amap vid="amapDemo"
                   :center="center"
                   :zoom="zoom"
                   class="amap-demo"
                   :events="events"
                   :plugin="plugin">
          </el-amap>
        </el-main>
      </el-container>
    </div>
</template>

<script>
  export default {
        name: "factory-info",
  data() {
    let self = this;
    var validatePhone = (rule, value, callback) => {
      if (!this.isPoneAvailable(value)) {
        callback(new Error('请输入正确的手机号'));
      } else {
        callback();
      }
    };
    return {
      markers: [],
      newType:{
        type:'',
        name:''
      },
      types:[{
        id:1,
        name:'管理员角色'
      },{
        id:2,
        name:'需求类型'
      },{
        id:3,
        name:'工具类型'
      },{
        id:4,
        name:'招聘类型'
      }],
      isDisable:true,
      zoom: 12,
      center: [116.39,39.9],
      address: '',
      roles:[],
      manageRole:sessionStorage.getItem("manageRole"),
      formLabelWidth:'100px',
      newCompany:{
        companyName:'',
        companyAddress:'',
        companyPhone:'',
        companyEmail:''
      },
      newManage:{
        manageName:'',
        manageRole:0,
        companyId:sessionStorage.getItem("companyId")
      },
      dialogFormVisible:false,
      dialogTypeVisible:false,
      plugin: ['ToolBar', {
        pName: 'MapType',
        defaultType: 0,
        events: {
          init(o) {
            console.log(o);
          }
        }
      }],
      events: {
        click(e) {
          let { lng, lat } = e.lnglat;
          self.lng = lng;
          self.lat = lat;
          self.newCompany.companyLocation = (lng + ',' + lat);
          // 这里通过高德 SDK 完成。
          var geocoder = new AMap.Geocoder({
            radius: 1000,
            extensions: "all"
          });
          geocoder.getAddress([lng ,lat], function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
              if (result && result.regeocode) {
                self.newCompany.companyAddress = result.regeocode.formattedAddress;
                self.$nextTick();
              }
            }
          });
          self.isDisable = false;
        }
      },
      lng: 0,
      lat: 0,
      rules:{
        companyName: [
          {required: true, message: '请输入公司名称', trigger: 'blur'},
          {min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur,change'}
        ],
        companyAddress: [
          {required: true, message: '请输入公司地址', trigger: 'blur'},
          {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur,change'}
        ],
        companyPhone: [
          {validator:validatePhone, trigger: 'blur,change'}
        ],
        companyEmail: [
          {type:'email', message: '输入正确的邮箱', trigger: 'blur,change'}
        ]
      }
    };
  },
  mounted() {
      let companyId = sessionStorage.getItem("companyId");
      this.$http.get('/api/get-company/'+ companyId).then((res)=>{
        if(res.body.code == '200'){
          if(res.body.data.companyLocation != null) {
            this.center = res.body.data.companyLocation.split(",");
          }if(res.body.data.companyAddress != null) {
            this.address = res.body.data.companyAddress;
          }
          this.newCompany = res.body.data;
        }
      });

    this.$http.get('/api/get-role').then((res)=>{
      if(res.body.code == '200'){
        this.roles = res.body.data;
        console.log(res);
      }
    });
  },
    methods:{
      submitForm(newCompany) {
        this.$refs[newCompany].validate((valid) => {
          if (valid) {
            this.$http.post("/api/company/update",this.newCompany).then((res)=>{
              if(res.body.code=='200'){
                this.$message({
                  type:'success',
                  message:'修改成功'
                });
                this.isDisable = true;
              }else{
                this.$message.error('修改失败，稍后再试');
                console.log(res);
              }
            });
          } else {
            return false;
          }
        });
      },
      isChange(){
        this.isDisable = false;
      },
      isPoneAvailable(str) {
        let myreg= /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(str)) {
          return false;
        } else {
          return true;
        }
      },
      addType(){
        let url = '';
        if(this.newType.type == 1){
          url = '/api/add-role-type'
        }else if(this.newType.type == 2){
          url = '/api/add-dtype'
        }else if(this.newType.type == 3){
          url = '/api/add-ttype'
        }else if(this.newType.type == 4){
          url = '/api/add-rtype'
        }else {
          this.$message.error("请选择添加类别");
          return;
        }
        this.$http.post(url,this.newType).then((res)=>{
          if(res.body.code=='200'){
            this.$message({
              type:'success',
              message:'添加成功'
            });
            this.dialogTypeVisible = false;
          }else{
            this.$message.error(res.body.msg);
            console.log(res);
          }
        });
      },
      addManage(){
        if(this.newManage.manageName<3){
          this.$message({
            type:'warning',
            message:'账号名不少于三位'
          });
          return;
        }
        console.log(this.newManage);
        this.$http.post("/api/manage/add",this.newManage).then((res)=>{
          if(res.body.code=='200'){
            this.$message({
              type:'success',
              message:'添加成功'
            });
            this.dialogFormVisible = false;
          }else{
            this.$message.error(res.body.msg);
            console.log(res);
          }
        });

      }
    }


}
</script>

<style scoped>
  *{
    font-family: 微软雅黑;
  }
  .factory-info{
    width: 100%;
    height: 100%;
  }
  .amap-demo{
    height: 100%;
  }
</style>

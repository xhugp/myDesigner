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
            </el-form-item>
          </el-form>
        </el-aside>
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
      isDisable:true,
      zoom: 12,
      center: [116.39,39.9],
      address: '',
      newCompany:{
        companyName:'',
        companyAddress:'',
        companyPhone:'',
        companyEmail:''
      },
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

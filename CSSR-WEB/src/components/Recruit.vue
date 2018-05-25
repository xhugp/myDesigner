<template>
    <div id="recruit">
      <!--搜索栏-->
      <el-form :inline="true" :model="searchForm" ref="searchForm" class="demo-form-inline" >
        <el-select v-model="searchForm.searchType" placeholder="选择类别搜索">
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
        <el-button type="primary" @click="submitForm" icon="el-icon-search" style="margin-left: 20px">搜索</el-button>
        <el-button type="primary" @click="resetForm" icon="el-icon-refresh">重置</el-button>
        <el-button type="success" @click="dialogFormVisible=true" icon="el-icon-circle-plus-outline">添加</el-button>
      </el-form>

      <!--招聘信息展示-->
      <el-table
        :data="recruits"
        style="width: 100%">
        <el-table-column
          type="index"
          width="50">
        </el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="内容：">
                <span>{{ props.row.recruitRemark}}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>


        <el-table-column
          label="招聘职位"
          prop="typeName">
        </el-table-column>
        <el-table-column
          label="招聘数量"
          prop="recruitNumber">
        </el-table-column>
        <el-table-column
          label="开始时间"
          prop="createTime">
        </el-table-column>
        <el-table-column
          label="结束时间"
          prop="endTime">
        </el-table-column>
        <el-table-column label="操作" width="300px">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)" icon="el-icon-delete">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--添加招聘信息弹出框-->
      <el-dialog title="添加招聘" :visible.sync="dialogFormVisible">
        <el-form :model="newRecruit" :rules="rules" ref="newRecruit" class="demo-ruleForm">
          <el-form-item label="招聘职位" :label-width="formLabelWidth" prop="recruitType">
            <el-select v-model="newRecruit.recruitType" placeholder="选择职位">
              <el-option
                v-for="item in options"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="招聘数量" prop="recruitNumber" :label-width="formLabelWidth">
            <el-input-number v-model="newRecruit.recruitNumber" placeholder="请填写数量" :min="1" :max="100"></el-input-number>
          </el-form-item>

          <el-form-item label="招聘日期" :label-width="formLabelWidth" prop="recruitTime">
          <el-date-picker
            v-model="newRecruit.recruitTime"
            type="datetimerange"
            align="right"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            :default-time="['12:00:00', '08:00:00']">
          </el-date-picker>
          </el-form-item>

          <el-form-item label="招聘内容" :label-width="formLabelWidth" prop="recruitRemark">
            <el-input type="textarea" v-model="newRecruit.recruitRemark" auto-complete="off" placeholder="1-200字"></el-input>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button  @click="resetAddForm('newRecruit')">重置</el-button>
          <el-button type="primary" @click="addRecruit('newRecruit')">添 加</el-button>
        </div>
      </el-dialog>

      <!--分页-->
      <div class="block" style="position:absolute;right: 20px;bottom: 20px;" background="blue">
        <el-pagination
          layout="total,prev, pager, next"
          @current-change="handleCurrentChange"
          :total="totalSize">
        </el-pagination>
      </div>
    </div>
</template>

<script>
    export default {
        name: "recruit",
        data(){
          return{
            dialogFormVisible:false,
            options:[],
            recruitTime:[],
            formLabelWidth:'120px',
            recruits:[],
            newRecruit:{
              recruitType:'',
              recruitNumber:1,
              companyId:sessionStorage.getItem("companyId"),
              recruitRemark:'',
              createTime:'',
              endTime:'',
              recruitTime:[]
            },
            searchForm:{
              searchType:''
            },
            totalSize:0,
            sendData:{
              currentPage:1,
              pageSize:10,
              recruit:{}
            },
            rules: {
              recruitType: [
                {required: true, message: '请选择招聘职位', trigger: 'blur,change'},
              ],
              recruitNumber: [
                {type : 'number',required: true, message: '请输入招聘人数', trigger: 'change'}
              ],
              recruitTime:[
                { type: 'array', required: true, message: '请选择时间', trigger: 'change' }
              ],
              recruitRemark: [
                {min: 1, max: 200, message: '长度在 1 到 200 个字符', trigger: 'change'}
              ]
            }
          }
        },
        methods:{
          submitForm(){
            this.sendData.recruit.companyId = sessionStorage.getItem("companyId");
            if(this.searchForm.searchType>-1){
              this.sendData.recruit.recruitType = this.searchForm.searchType;
            }
            this.$http.post('/api/recruit/list',this.sendData).then((res)=>{
                if(res.body.code =="200") {
                  this.totalSize = res.body.data.totalSize;
                  this.recruits = res.body.data.datas;
                }else{
                console.log(res);
              }
            });
          },
          handleCurrentChange(val){
            this.sendData.currentPage = val;
            this.submitForm();
          },
          resetAddForm(newRecruit){
            this.$refs[newRecruit].resetFields();
          },
          addRecruit(newRecruit){
            this.$refs[newRecruit].validate((valid) => {
              if (valid) {
                this.newRecruit.createTime = this.newRecruit.recruitTime[0].getTime();
                this.newRecruit.endTime = this.newRecruit.recruitTime[1].getTime();
                this.$http.post("/api/recruit/add",this.newRecruit).then((res)=>{
                  if(res.body.code=='200'){
                    this.$message({
                      type:'success',
                      message:'添加成功'
                    });
                    this.submitForm();
                    this.resetAddForm(newRecruit);
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
          resetForm() {
            this.searchForm.searchType='';
            this.submitForm();
          },
          handleDelete(index,row){
            this.$confirm('此操作将永久删除该招聘信息, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              //请求删除招聘信息
              this.$http.delete('/api/recruit/delete/'+row.recruitId).then((res)=>{
                if(res.body.code == '200'){
                  this.$message({
                    type: 'success',
                    message: '删除成功!'
                  });
                  this.recruits.pop(row);
                }else{
                  console.log(res);
                  this.$message.error("删除失败");
                }
              });
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消删除'
              });
            });
          }
        },
        mounted(){
          this.$http.get("/api/get-rtype").then((res)=> {
            this.options = res.body.data;
          });
          this.submitForm();
        }
    }
</script>

<style scoped>
  .demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  font-size: 30px;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.el-form-item span{
  color: #99a9bf;
}
</style>

<template>
    <div id="tool-manage" class="tool-manage">
      <el-form :inline="true" :model="searchForm" ref="searchForm" class="demo-form-inline" >
        <el-select v-model="searchForm.searchType" placeholder="选择类别搜索">
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
        <el-select v-model="searchForm.searchState" placeholder="选择状态搜索">
          <el-option
            v-for="item in optionState"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
        <el-button type="primary" @click="submitForm" icon="el-icon-search" style="margin-left: 20px">搜索</el-button>
        <el-button type="primary" @click="resetForm" icon="el-icon-refresh">重置</el-button>
        <el-button type="success" @click="handlerAdd" icon="el-icon-circle-plus-outline
">添加</el-button>
      </el-form>
      <el-table
        :data="tools"
        style="width: 100%"
        :row-style="tableRowStyle">
        <el-table-column
          type="index"
          width="50">
        </el-table-column>

        <el-table-column
          prop="toolName"
          label="名称">
        </el-table-column>
        <el-table-column
          prop="stateName"
          label="状态">
        </el-table-column>
        <el-table-column
          prop="typeName"
          label="类别">
        </el-table-column>
        <el-table-column
          prop="toolDes"
          label="描述">
        </el-table-column>

        <el-table-column label="操作" width="300px">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleImg(scope.$index, scope.row)"  icon="el-icon-picture-outline" type="primary">图片</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)" icon="el-icon-delete">删除</el-button>
            <el-button
              size="mini"
              v-if="scope.row.toolState == 2"
              type="primary"
              @click="handleShowLease(scope.$index, scope.row)" icon="el-icon-view">租赁信息</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--添加工具弹出框-->
      <el-dialog title="添加工具" :visible.sync="dialogFormVisible">
        <el-form :model="newTool" :rules="rules" ref="newTool" class="demo-ruleForm">
          <el-form-item label="工具名称" :label-width="formLabelWidth" prop="toolName">
            <el-input v-model="newTool.toolName" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="选择类别" :label-width="formLabelWidth" prop="toolType">
            <el-select v-model="newTool.toolType" placeholder="选择类别搜索">
              <el-option
                v-for="item in options"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="工具图片" :label-width="formLabelWidth" prop="toolImg">
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

          <el-form-item label="工具描述" :label-width="formLabelWidth" prop="toolDes">
            <el-input type="textarea" v-model="newTool.toolDes" auto-complete="off" placeholder="1-200字"></el-input>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button  @click="resetAddForm('newTool')">重置</el-button>
          <el-button type="primary" @click="addTool('newTool')">添 加</el-button>
        </div>
      </el-dialog>


      <el-dialog
        :visible.sync="imgDialog"
        width="30%">
        <img :src="img"/>
      </el-dialog>


      <el-dialog title="租赁信息" :visible.sync="dialogLeaseVisible">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="租赁人：">
                <span>{{ lease.userMc }}</span>
              </el-form-item>
              <el-form-item label="租赁价格：">
                <span>{{ lease.leaseRepay | formatMoney }}</span>
              </el-form-item>
              <el-form-item label="租赁天数：">
                <span>{{ lease.leaseTerm }}</span>
              </el-form-item>
              <el-form-item label="起租日期：">
                <span>{{ lease.createTime }}</span>
              </el-form-item>
              <el-form-item label="租赁内容：">
                <span>{{ lease.leaseMes }}</span>
              </el-form-item>
            </el-form>
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
        name: "tool-manage",
        data(){
          return{
            dialogLeaseVisible:false,
            imgDialog:false,
            fileList:[],
            searchForm:{
              searchType:'',
              searchState:''
            },
            img:'',
            lease:{},
            optionState:[{
              id:'1',
              name:'闲置中'
            },{
              id:'2',
              name:'出租中'
            }],
            options:[],
            tools:[],
            sendData:{
              currentPage:1,
              pageSize:10,
              tool:{}
            },
            totalSize:0,
            newTool:{
              companyId:'',
              toolName:'',
              toolDes:'',
              toolType:'',
              toolImg:''
            },
            dialogFormVisible:false,
            formLabelWidth:'120px',
            postData:{
              token:''
            },
            imageUrl:'',
            rules: {
              toolName: [
                {required: true, message: '请输入工具名称', trigger: 'blur'},
                {min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'change'}
              ],
              toolType: [
                {required: true, message: '请选择工具类型', trigger: 'blur,change'}
              ],
              toolImg:[
                {required: true, message: '请上传图片', trigger: 'blur,change'},
              ],
              toolDes: [
                {min: 1, max: 200, message: '长度在 1 到 200 个字符', trigger: 'change'}
              ]
            }
          }
        },

      mounted(){
        this.$http.get("/api/get-ttype").then((res)=> {
          this.options = res.body.data;
        });

        this.submitForm();
      },
      filters:{
        formatMoney:function(val){
          if(val){
            return val + " 元/日";
          }else{
            return '';
          }
        }
      },
      methods:{
        submitForm(){
          this.sendData.tool.companyId = sessionStorage.getItem("companyId");
          if(this.searchForm.searchType>-1){
            this.sendData.tool.toolType = this.searchForm.searchType;
          }
          if(parseInt(this.searchForm.searchState) > 0){
            this.sendData.tool.toolState = this.searchForm.searchState;
          }
          this.$http.post('/api/tool/list',this.sendData).then((res)=>{
            if(res.body.code =="200") {
              this.totalSize = res.body.data.totalSize;
              this.tools = res.body.data.datas;
            }else{
              console.log(res);
            }
          });
        },
        resetForm(){
          this.searchForm.searchType='';
          this.searchForm.searchState='';
          this.sendData.tool = {};
          this.submitForm();
        },
        addTool(newTool){
          this.$refs[newTool].validate((valid) => {
            if (valid) {
              this.newTool.companyId = sessionStorage.getItem("companyId");
              this.newTool.toolImg = this.imageUrl;
              this.$http.post("/api/tool/add",this.newTool).then((res)=>{
                if(res.body.code=='200'){
                  this.$message({
                    type:'success',
                    message:'添加成功'
                  });
                  this.dialogFormVisible = false;
                  this.$refs[newTool].resetFields();
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
        resetAddForm(newTool){
          this.$refs[newTool].resetFields();
          this.fileList = [];
        },
        handlerAdd(){
          this.dialogFormVisible = true;
          this.$http.get("/api/get-uptoken").then((res)=>{
            this.postData.token = res.body.data;
          });
        },
        handleCurrentChange(val){
          this.sendData.currentPage = val;
          this.submitForm();
        },
        tableRowStyle(row,index){
          if (row.row.toolState == '2') {
            return 'background:oldlace';
          }
          return '';
        },
        handleImg(index, row){
          console.log(row);
          this.img = row.toolImg;
           this.imgDialog = true;
        },
        handleDelete(index, row){
          if(row.toolState != '1'){
            this.$message.warning("该工具出租中，无法删除");
            return;
          }
          this.$confirm('将永久删除该工具, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            //请求删除需求
            this.$http.delete('/api/tool/delete/'+row.toolId).then((res)=>{
              if(res.body.code == '200'){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                this.tools.pop(row);
              }else{
                console.log(res);
                this.$message.error("删除需求失败");
              }
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        handleShowLease(index, row){
          this.$http.get('/api/lease/get-by-tool/'+row.toolId).then((res)=>{
            if(res.body.code == '200'){
              this.lease = res.body.data;
              this.dialogLeaseVisible = true;
            }else{
              console.log(res);
              this.$message.error("查看租赁信息失败");
            }
          })
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
          this.newTool.toolImg = file.name;
        },
        handleRemove(file, fileList){
          this.newTool.toolImg = '';
        }
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
  .imgDiv{
    width: 100%;
    height: 100%;
    text-align: center;
  }
  img{
    max-width: 100%;
    max-height: 100%;
  }
</style>

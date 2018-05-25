<template>
    <div id="appointment">
      <el-form :inline="true" :model="searchForm" ref="searchForm" class="demo-form-inline" >
        <el-select v-model="searchForm.searchResult" placeholder="选择状态搜索">
          <el-option
            v-for="item in optionResult"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
        <el-date-picker
          v-model="searchForm.searchTime"
          type="datetimerange"
          align="right"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="['12:00:00', '08:00:00']">
        </el-date-picker>
        <el-button type="primary" @click="submitForm" icon="el-icon-search" style="margin-left: 20px">搜索</el-button>
        <el-button type="primary" @click="resetForm" icon="el-icon-refresh">重置</el-button>
      </el-form>

      <el-table
        :data="rreqs"
        style="width: 100%">
        <el-table-column
          type="index"
          width="50">
        </el-table-column>
        <el-table-column
          prop="userMc"
          label="用户名称">
        </el-table-column>

        <el-table-column
          prop="recruitName"
          label="应聘职位">
        </el-table-column>

        <el-table-column
          prop="state"
          label="应聘状态">
        </el-table-column>

        <el-table-column
          prop="createTime"
          label="申请日期">
        </el-table-column>

        <el-table-column label="操作" width="300px">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="primary"
              @click="handleShowUser(scope.$index, scope.row)" icon="el-icon-view">查看用户</el-button>
            <el-button
              size="mini"
              type="primary"
              v-if="scope.row.rreqState==0"
              @click="handleRreq(scope.$index, scope.row)" icon="el-icon-edit-outline">签约</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)" icon="el-icon-delete">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--用户弹出框-->
      <el-dialog title="用户信息" :visible.sync="dialogUserVisible">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="用户姓名：">
            <span>{{ user.userMc }}</span>
          </el-form-item>
          <el-form-item label="性别：">
            <span>{{ user.userSex }}</span>
          </el-form-item>
          <el-form-item label="电话：">
            <span>{{ user.userPhone }}</span>
          </el-form-item>
          <el-form-item label="邮箱：">
            <span>{{ user.userEmail }}</span>
          </el-form-item>
          <el-form-item label="评分：">
              <span>
              <el-rate
                v-model="user.userScore"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value}"
                style="margin-top: 10px">
              </el-rate>
                </span>
          </el-form-item>
        </el-form>
      </el-dialog>
      <!--请求签约弹出框-->
      <el-dialog  :visible.sync="dialogSreqVisible" width="20%">
        <el-form :model="newSreq" ref="newSreq" class="demo-ruleForm">
          <el-form-item label="签约年限:" :label-width="formLabelWidth" prop="srTime" style="margin-left: -40px">
            <el-input-number v-model="newSreq.srTime" auto-complete="off" :min="1" :max="3" style="width: 60%"></el-input-number>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="addSreq">提交</el-button>
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
        name: "appointment",
        data(){
          return{
            dialogUserVisible:false,
            dialogSreqVisible:false,
            formLabelWidth:'120px',
            user:{},
            newSreq:{
              srTime : 1,
              companyId : sessionStorage.getItem("companyId")
            },
            totalSize:0,
            searchForm:{
              searchTime:[],
              searchResult:0
            },
            optionResult:[{
              id:0,
              name:'申请中'
            },{
              id:1,
              name:'已处理'
            }],
            rreqs:[],
            rreqData:{},
            sendData:{
              currentPage:1,
              pageSize:10,
              rreq:{

              }
            }

          }
        },
      methods:{
        submitForm(){
          if(this.searchForm.searchTime.length > 0){
            this.sendData.rreq.fromTime = this.searchForm.searchTime[0].getTime();
            this.sendData.rreq.toTime = this.searchForm.searchTime[1].getTime();
          }
          this.sendData.rreq.rreqState = this.searchForm.searchResult;
          this.sendData.rreq.companyId = sessionStorage.getItem("companyId");
          this.$http.post('/api/rreq/list',this.sendData).then((res)=>{
            if(res.body.code =="200") {
              this.totalSize = res.body.data.totalSize;
              this.rreqs = res.body.data.datas;
            }else{
              console.log(res);
            }
          });
        },
        resetForm(){
          this.searchForm.searchTime=[];
          this.searchForm.searchResult = 0
          this.submitForm();
        },
        handleShowUser(index,row){
          this.$http.get('/api/user/'+row.userId).then((res)=>{
            if(res.body.code =="200") {
              this.user = res.body.data;
              this.dialogUserVisible = true;
            }else{
              this.$message.error("查看错误");
              console.log(res);
            }
          });
        },
        handleRreq(index,row){
          this.newSreq.userId = row.userId;
          this.rreqData.rreqId = row.rreqId;
          this.dialogSreqVisible = true;
        },
        addSreq(){
          this.$http.post("/api/sreq/add",this.newSreq).then((res)=>{
            if(res.body.code == "200"){
              this.$message.success("请求发送成功");
              this.sendUpdate();
            }else if(res.body.code == "41605"){
              this.$message.error("已经有一个请求待该技师处理");
            }else if(res.body.code == "41606"){
              this.$message.error("用户已经签约");
            }else{
              console.log(res);
              this.$message.error("请求发送失败");
            }
            this.dialogSreqVisible = false;
          });
        },
        sendUpdate(){
          this.$http.post("/api/rreq/update",this.rreqData).then((res)=>{
            if(res.body.code != "200") {
              console.log(res);
            }
          });
        },
        handleCurrentChange(val){
          this.sendData.currentPage = val;
          this.submitForm();
        },
        handleDelete(index,row){
          this.$http.delete("/api/rreq/"+row.rreqId).then((res)=>{
            if(res.body.code == "200"){
              this.$message.success("删除成功");
              this.rreqs.pop(row);
            }else{
              console.log(res);
              this.$message.error("删除错误");
            }
          });
        }
      },
      mounted(){
        this.submitForm();
      }
    }
</script>

<style scoped>
  *{
    font-family: 微软雅黑;
  }
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

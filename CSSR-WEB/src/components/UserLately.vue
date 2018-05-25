<template>
    <div class="user-lately">
      <!--员工信息主体-->
      <el-table
        :data="users"
        style="width: 100%"
        :default-sort = "{prop: 'userMc', order: 'descending'}">
        <el-table-column
          type="index"
          width="50">
        </el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="用户名称：">
                <span>{{ props.row.userMc }}</span>
              </el-form-item>
              <el-form-item label="用户账户：">
                <span>{{ props.row.userName }}</span>
              </el-form-item>
              <el-form-item label="用户性别：">
                <span>{{ props.row.userSex}}</span>
              </el-form-item>

              <el-form-item label="电话：">
                <span>{{ props.row.userPhone }}</span>
              </el-form-item>

              <el-form-item label="邮箱：">
                <span>{{ props.row.userEmail}}</span>
              </el-form-item>
              <el-form-item label="住址：">
                <span>{{ props.row.userAddress }}</span>
              </el-form-item>
              <el-form-item label="评分：">
              <span>
              <el-rate
                v-model="props.row.userScore"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value}"
                style="margin-top: 10px">
              </el-rate>
                </span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>

        <el-table-column
          label="用户账号"
          prop="userName">
        </el-table-column>
        <el-table-column
          label="用户名称"
          prop="userMc">
        </el-table-column>
        <el-table-column
          label="性别"
          prop="userSex">
        </el-table-column>
        <el-table-column
          label="电话"
          prop="userPhone">
        </el-table-column>
        <el-table-column label="操作" width="300px">
          <template slot-scope="scope">

            <el-button
              size="mini"
              type="primary"
              @click="handleShowOrder(scope.$index, scope.row)" icon="el-icon-view">最近订单</el-button>

            <el-button
              size="mini"
              type="primary"
              @click="handleSreq(scope.$index, scope.row)" icon="el-icon-edit-outline">签约</el-button>

          </template>
        </el-table-column>
      </el-table>

      <!--订单信息弹出框-->
      <el-dialog title="需求信息" :visible.sync="dialogOrderVisible">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="用户名称：">
            <span>{{ order.userMc }}</span>
          </el-form-item>
          <el-form-item label="订单状态：">
            <span>{{ order.state }}</span>
          </el-form-item>
          <el-form-item label="订单评分：">
            <span>
              <el-rate
                v-model="order.orderScore"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value}"
                style="margin-top: 10px">
              </el-rate>
            </span>
          </el-form-item>
          <el-form-item label="创建时间：">
            <span>{{ order.createTime }}</span>
          </el-form-item>
          <el-form-item label="结束时间：">
            <span>{{ order.lastUpdateTime }}</span>
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

    </div>
</template>

<script>
    export default {
        name: "user-lately",
        data(){
          return{
            users:[],
            dialogOrderVisible:false,
            dialogSreqVisible:false,
            formLabelWidth:'100px',
            sendData:{
              currentPage:1,
              pageSize:10,
              companyId:sessionStorage.getItem("companyId")
            },
            order:{},
            newSreq:{
              srTime : 1,
              companyId : sessionStorage.getItem("companyId")
            }

          }
        },
        mounted(){
          this.submitForm();
        },
        methods:{
          handleSreq(index,row){
            this.newSreq.userId = row.userId;
            this.dialogSreqVisible = true;
          },
          handleShowOrder(index,row){
            let sendOrder = {
              userId : row.userId,
              companyId:sessionStorage.getItem("companyId")
            }
            console.log(sendOrder);
            this.$http.post("/api/order/lately",sendOrder).then((res)=>{
              if(res.body.code == "200"){
                this.order = res.body.data;
                this.dialogOrderVisible = true;
              }else{
                console.log(res);
              }
            });
          },
          submitForm(){

            this.$http.post("/api/user/list-lately",this.sendData).then((res)=>{
              if(res.body.code == "200"){
                this.users = res.body.data.datas;
                this.totalSize = res.body.data.totalSize;
              }else{
                console.log(res);
              }
            });
          },
          addSreq(){
            this.$http.post("/api/sreq/add",this.newSreq).then((res)=>{
              if(res.body.code == "200"){
                this.$message.success("请求发送成功");
              }else if(res.body.code == "41605"){
                this.$message.error("已经有一个请求待该技师处理");
              }else{
                console.log(res);
                this.$message.error("请求发送失败");
              }
              this.dialogSreqVisible = false;
            });
          }
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

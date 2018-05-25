<template>
  <div style="width: 100%">
    <!--搜索栏-->
    <el-form :inline="true" :model="searchForm" ref="searchForm" class="demo-form-inline" >
      <el-input v-model="userMc" placeholder="输入用户名查询" clearable @change="submitForm" style="width: 10%"></el-input>
    </el-form>

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
            @click="handleShowSign(scope.$index, scope.row)" icon="el-icon-view">签约信息</el-button>
        </template>
      </el-table-column>
    </el-table>


    <!--签约信息弹出框-->
    <el-dialog title="签约信息" :visible.sync="dialogDemandVisible">
      <el-form label-position="left" inline class="demo-table-expand">
        <el-form-item label="用户名称：">
          <span>{{ sign.userMc }}</span>
        </el-form-item>
        <el-form-item label="公司名称：">
          <span>{{ sign.companyName}}</span>
        </el-form-item>
        <el-form-item label="签约年限：">
          <span>{{ sign.signTime }} 年</span>
        </el-form-item>
        <el-form-item label="签约日期：">
          <span>{{ sign.signDate }}</span>
        </el-form-item>
        <el-form-item label="到期日期：">
          <span>{{ sign.endDate }}</span>
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
        name: "user-info",
        data(){
          return{
            totalSize : 0,
            searchForm:{

            },
            options : [
              {
              id : "1",
              name : "签约技师"
              },
              {
                id : "2",
                name : "最近合作"
              }],
            users:[],
            sign:{},
            dialogDemandVisible:false,
            sendData:{
              currentPage:1,
              pageSize:10,
              user:{}
            },
            userMc:''
          }
        },
      mounted(){
          this.submitForm();
      },
      methods : {
        handleCurrentChange(val){
          this.sendData.currentPage = val;
          this.submitForm();
        },
        handleShowSign(index,row){
          let sign = {
            userId : row.userId,
            companyId:sessionStorage.getItem("companyId")
          };
          this.$http.post("/api/sign/get-sign",sign).then((res)=>{
            if(res.body.code == "200"){
              this.sign = res.body.data;
              this.dialogDemandVisible = true;
            }else {
              this.$message.error("未找到该签约信息");
              console.log(res);
            }
          });
        },
        submitForm(){
          this.sendData.user = {};
          this.sendData.user.companyId = sessionStorage.getItem("companyId");
          if(this.userMc.length > 0){
            this.sendData.user.userMc = this.userMc;
          }

          this.$http.post("/api/user/list",this.sendData).then((res)=>{
              if(res.body.code == "200"){
                this.users = res.body.data.datas;
                this.totalSize = res.body.data.totalSize;
              }else{
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

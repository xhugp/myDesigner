<template>
    <div id="order-request">
      <el-form :inline="true" :model="searchForm" ref="searchForm" class="demo-form-inline" >
        <el-select v-model="searchForm.searchResult" placeholder="选择状态搜索">
          <el-option
            v-for="item in optionResult"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
        <el-select v-model="searchForm.searchType" placeholder="选择类型搜索">
          <el-option
            v-for="item in optionType"
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
        :data="oreqs"
        style="width: 100%"
        :default-sort = "{prop: 'oreqCreateTime', order: 'descending'}"
      >
        <el-table-column
          type="index"
          width="50">
        </el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="申请内容：">
                <span>{{ props.row.oreqContent }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          label="申请时间"
          prop="oreqCreateTime">
        </el-table-column>
        <el-table-column
          label="申请人"
          prop="userMc">
        </el-table-column>
        <el-table-column
          label="申请类型"
          prop="type">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="primary"
              @click="handleShowDemand(scope.$index, scope.row)" icon="el-icon-view">查看需求</el-button>
            <el-button
              size="mini"
              type="success"
              v-if="scope.row.oreqResult == '0'"
              @click="handleAgree(scope.$index, scope.row)">同意</el-button>
            <el-button
              size="mini"
              type="danger"
              v-if="scope.row.oreqResult == '0'"
              @click="handleRefuse(scope.$index, scope.row)">拒绝</el-button>
            <el-button
              size="mini"
              type="danger"
              v-if="scope.row.oreqResult != '0'"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>

        </el-table-column>
      </el-table>

      <!--需求信息弹出框-->
      <el-dialog title="需求信息" :visible.sync="dialogDemandVisible">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="需求标题：">
            <span>{{ demand.demandTitle }}</span>
          </el-form-item>
          <el-form-item label="需求报酬：">
            <span>{{ demand.demandRepay | formatMoney}}</span>
          </el-form-item>
          <el-form-item label="需求类型：">
            <span>{{ demand.typeName }}</span>
          </el-form-item>
          <el-form-item label="需求备注：">
            <span>{{ demand.demandRemark }}</span>
          </el-form-item>
          <el-form-item label="图片：">
            <div class="imgDiv" style="width: 50%">
              <img :src="demand.demandImg" style="max-width: 100%;max-height: 100%"/>
            </div>
          </el-form-item>
          <el-form-item label="创建时间：">
            <span>{{ demand.createTime }}</span>
          </el-form-item>
          <el-form-item label="上次更新：">
            <span>{{ demand.lastUpdateTime}}</span>
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
        name: "order-request",
        mounted(){
            this.submitForm();
        },
        data(){
          return{
            searchForm:{
              searchTime:[],
              searchResult:'0',
              searchType:''
            },
            optionResult:[{
              id:'0',
              name:'待处理'
            },{
              id:'1',
              name:'已同意'
            },{
              id:'2',
              name:'已拒绝'
            }],
            optionType:[{
              id:'2',
              name:'中断请求'
            },{
              id:'1',
              name:'结束请求'
            }],
            oreqs:[],
            totalSize:0,
            sendData:{
              currentPage:1,
              pageSize:10,
              oreq:{
                oreqResult : "0"
              }
            },
            dialogDemandVisible:false,
            demand:{}
          }
        },
        filters:{
          formatMoney:function(val){
            return val + " 元";
          }
        },
        methods:{
          submitForm(){
            if(this.searchForm.searchTime.length > 0){
              this.sendData.oreq.fromTime = this.searchForm.searchTime[0].getTime();
              this.sendData.oreq.toTime = this.searchForm.searchTime[1].getTime();
            }
            if(parseInt(this.searchForm.searchType) > 0){
              this.sendData.oreq.oreqType = this.searchForm.searchType;
            }
            this.sendData.oreq.oreqResult = this.searchForm.searchResult;
            this.sendData.oreq.companyId = sessionStorage.getItem("companyId");
            this.$http.post('/api/oreq/list',this.sendData).then((res)=>{
              if(res.body.code =="200") {
                this.totalSize = res.body.data.totalSize;
                this.oreqs = res.body.data.datas;
              }else{
                console.log(res);
              }
            });
          },
          handleAgree(index,row){
            this.$confirm('确认同意该请求？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'info'
            }).then(() => {
              let oreq = row;
              oreq.oreqResult = "1";
              let url = '';
              if (row.oreqType == "1") {
                url = '/api/oreq/handle/over';
              }
              else if (row.oreqType == "2") {
                url = '/api/oreq/handle';
              } else {
                  return;
              }
              this.$http.post(url,oreq).then((res)=>{
                if(res.body.code == "200"){
                  this.$message({
                    type: 'success',
                    message: '请求已处理'
                  });
                  this.oreqs.pop(row);
                }else{
                  console.log(res);
                  this.$message.error("处理失败");
                }
              });
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '取消处理'
              });
            });
          },
          handleRefuse(index,row){
            this.$confirm('确认拒绝该请求？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'info'
            }).then(() => {
              let oreq = row;
              oreq.oreqResult = "2";
              let url = '';
              if (oreq.oreqType == "1") {
                url = '/api/oreq/handle/over';
              } else if (oreq.oreqType == "2") {
                url = '/api/oreq/handle';
              } else {
                return;
              }

              this.$http.post(url,oreq).then((res)=>{
                if(res.body.code == "200"){
                  this.$message({
                    type: 'success',
                    message: '请求已处理'
                  });
                  this.oreqs.pop(row);
                }else{
                  console.log(res);
                  this.$message.error("处理失败");
                }
              });
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '取消处理'
              });
            });
          },
          handleDelete(index,row){
            this.$confirm('确定删除请求记录?不影响订单状态', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              //请求删除订单请求
              this.$http.delete('/api/oreq/'+row.oreqId).then((res)=>{
                if(res.body.code == '200'){
                  this.$message({
                    type: 'success',
                    message: '删除成功!'
                  });
                  this.oreqs.pop(row);
                }else{
                  console.log(res);
                  this.$message.error("删除失败");
                }
              });
            }).catch(() => {

            });
          },
          handleCurrentChange(val){
            this.sendData.currentPage = val;
            this.submitForm();
          },
          resetForm() {
            this.searchForm.searchTime=[];
            this.searchForm.searchType='';
            this.searchForm.searchResult='0';
            this.sendData.oreq = {oreqResult : "0"};
            this.submitForm();
          },
          handleShowDemand(index,row) {
            this.$http.get("/api/order/get-demand/" + row.orderId).then((res)=>{
              if(res.body.code == 200){
                console.log(res);
                this.demand = res.body.data;
                this.dialogDemandVisible = true;
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

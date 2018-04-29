<template>
  <div id="lease-request">
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
    </el-form>

    <el-table
      :data="lReqs"
      style="width: 100%"
      :default-sort = "{prop: 'lReqCreateTime', order: 'descending'}"
    >
      <el-table-column
        type="index"
        width="50">
      </el-table-column>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="工具名称：">
              <span>{{ props.row.toolName }}</span>
            </el-form-item>
            <el-form-item label="申请人：">
              <span>{{ props.row.userMc }}</span>
            </el-form-item>
            <el-form-item label="申请天数：">
              <span>{{ props.row.lReqTerm }} 天</span>
            </el-form-item>
            <el-form-item label="申请金额：">
              <span>{{ props.row.lReqMoney | formatMoney}}</span>
            </el-form-item>
            <el-form-item label="申请时间：">
              <span>{{ props.row.lReqCreateTime}}</span>
            </el-form-item>
            <el-form-item label="处理时间：" v-if="props.row.lReqHandleTime != null">
              <span>{{ props.row.lReqHandleTime}}</span>
            </el-form-item>
            <el-form-item label="申请内容：">
              <span>{{ props.row.lReqContent }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="工具"
        prop="toolName">
      </el-table-column>
      <el-table-column
        label="申请人"
        prop="userMc"
        sortable>
      </el-table-column>
      <el-table-column
        label="申请天数"
        sortable
        prop="lReqTerm">
      </el-table-column>
      <el-table-column
        label="日租价格（元）"
        sortable
        prop="lReqMoney">
      </el-table-column>
      <el-table-column
        label="申请时间"
        sortable
        prop="lReqCreateTime">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            v-if="scope.row.lReqResult == '0'"
            @click="handleAgree(scope.$index, scope.row)">同意</el-button>
          <el-button
            size="mini"
            type="danger"
            v-if="scope.row.lReqResult == '0'"
            @click="handleRefuse(scope.$index, scope.row)">拒绝</el-button>
          <el-button
            size="mini"
            type="danger"
            v-if="scope.row.lReqResult != '0'"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>

      </el-table-column>
    </el-table>
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
        name: "lease-request",
        data(){
          return{
            searchForm:{
              searchTime:[],
              searchResult:'0'
            },
            totalSize:0,
            sendData:{
              currentPage:1,
              pageSize:10,
              lReq:{
                lReqResult : "0"
              }
            },
            lReqs:[],
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
          }
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
        handleAgree(index,row){
          this.$confirm('确认出租工具' + row.toolName + "？", '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'info'
          }).then(() => {
            let sendLreq = row;
            sendLreq.lReqResult = '1';
            this.$http.post('/api/lreq/handle',sendLreq).then((res)=>{
              if(res.body.code == '200'){
                this.$message({
                  type: 'success',
                  message: '商品已出租'
                });
                this.lReqs.pop(row);
              }else{
                console.log(res);
                sendLreq.lReqResult = '0';
                this.$message.error("出租失败");
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
            type: 'warning'
          }).then(() => {
            let sendLreq = {
              'lReqId' : row.lReqId,
              'toolId' : row.toolId,
              'lReqResult':"2"
            };
            this.$http.post('/api/lreq/handle',sendLreq).then((res)=>{
              if(res.body.code == '200'){
                this.$message({
                  type: 'success',
                  message: '已拒绝该请求'
                });
                this.lReqs.pop(row);
              }else{
                console.log(res);
                this.$message.error("操作失败");
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
          this.$confirm('确定删除该请求信息？此操作不影响被出租的工具', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.delete('/api/lreq/' + row.lReqId).then((res)=>{
              if(res.body.code == '200'){
                this.$message({
                  type: 'success',
                  message: '成功删除'
                });
                this.lReqs.pop(row);
              }else{
                console.log(res);
                this.$message.error("删除失败");
              }
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消操作'
            });
          });
        },
        submitForm(){
          if(this.searchForm.searchTime.length > 0){
            this.sendData.lReq.fromTime = this.searchForm.searchTime[0].getTime();
            this.sendData.lReq.toTime = this.searchForm.searchTime[1].getTime();
          }
          this.sendData.lReq.lReqResult = this.searchForm.searchResult;
          this.sendData.lReq.companyId = sessionStorage.getItem("companyId");
          this.$http.post('/api/lreq/list',this.sendData).then((res)=>{
            if(res.body.code =="200") {
              this.totalSize = res.body.data.totalSize;
              this.lReqs = res.body.data.datas;
            }else{
              console.log(res);
            }
          });
        },
        handleCurrentChange(val){
          this.sendData.currentPage = val;
          this.submitForm();
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

<template>
  <div id="sign-request">
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
      :data="sreqs"
      style="width: 100%"
      :row-style="tableRowStyle">
      <el-table-column
        type="index"
        width="50">
      </el-table-column>
      <el-table-column
        prop="userMc"
        label="用户名称">
      </el-table-column>
      <el-table-column
        prop="srTime"
        label="合作年限">
      </el-table-column>
      <el-table-column
        prop="state"
        label="请求状态">
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建日期">
      </el-table-column>
      <el-table-column
        prop="handleTime"
        label="处理日期">
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
        name: "sign-request",
        data(){
          return{
            searchForm:{
              searchTime:[],
              searchResult:0
            },
            optionResult:[{
              id:0,
              name:'待处理'
            },{
              id:1,
              name:'已同意'
            },{
              id:2,
              name:'已拒绝'
            }],
            sendData:{
              currentPage:1,
              pageSize:10,
              sreq:{
                srState:0
              }
            },
            sreqs:[],
            totalSize:0
          }
        },
        mounted(){
          this.submitForm();
        },
        methods:{
          submitForm(){
            if(this.searchForm.searchTime.length > 0){
              this.sendData.sreq.fromTime = this.searchForm.searchTime[0].getTime();
              this.sendData.sreq.toTime = this.searchForm.searchTime[1].getTime();
            }
            this.sendData.sreq.srState = this.searchForm.searchResult;
            this.sendData.sreq.companyId = sessionStorage.getItem("companyId");
            this.$http.post('/api/sreq/list',this.sendData).then((res)=>{
              if(res.body.code =="200") {
                this.totalSize = res.body.data.totalSize;
                this.sreqs = res.body.data.datas;
              }else{
                console.log(res);
              }
            });
          },
          tableRowStyle(row,index){
            if (row.row.srState == 2) {
              return 'background:oldlace';
            }else if (row.row.srState == 1) {
              return 'background:#f0f9eb';
            }
            return '';
          },
          handleCurrentChange(val){
            this.sendData.currentPage = val;
            this.submitForm();
          },
          resetForm(){
            this.searchForm.searchTime=[];
            this.searchForm.searchResult = 0
            this.submitForm();
          }
        }
    }
</script>

<style scoped>

</style>

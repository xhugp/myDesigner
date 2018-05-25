<template>
  <div id = "lease-info">
    <!--搜索栏-->
    <el-form :inline="true" :model="searchForm" ref="searchForm" class="demo-form-inline" >
      <el-select v-model="searchForm.searchState" placeholder="选择状态搜索">
        <el-option
          v-for="item in optionState"
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
      <el-button type="primary" @click="submitForm" style="margin-left: 20px" icon="el-icon-search">搜索</el-button>
      <el-button type="primary" @click="resetForm" icon="el-icon-refresh">重置</el-button>
    </el-form>

    <!--表格-->
    <el-table
      :data="leases"
      style="width: 100%"
      :default-sort = "{prop: 'createTime', order: 'descending'}"
      :row-style="tableRowStyle">

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
            <el-form-item label="租赁人：">
              <span>{{ props.row.userMc }}</span>
            </el-form-item>

            <el-form-item label="日租金额：">
              <span>{{ props.row.leaseRepay }} 元/日</span>
            </el-form-item>

            <el-form-item label="租赁天数：">
              <span>{{ props.row.leaseTerm }} 天</span>
            </el-form-item>

            <el-form-item label="总金额：">
              <span>{{ props.row.totalMoney }} 元</span>
            </el-form-item>

            <el-form-item label="开始日期：">
              <span>{{ props.row.createTime }}</span>
            </el-form-item>
            <el-form-item label="结束日期：">
              <span>{{ props.row.endTime }}</span>
            </el-form-item>
            <el-form-item label="租赁信息：">
              <span>{{ props.row.leaseMes }}</span>
            </el-form-item>

          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="工具"
        prop="toolName">
      </el-table-column>
      <el-table-column
        label="租赁人"
        prop="userMc"
        sortable>
      </el-table-column>
      <el-table-column
        label="状态"
        sortable
        prop="state">
      </el-table-column>
      <el-table-column
        label="开始日期"
        sortable
        prop="createTime">
      </el-table-column>
      <el-table-column
          label="结束日期"
          sortable
          prop="endTime">
      </el-table-column>
      <el-table-column label="操作" width="300px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleShowTool(scope.$index, scope.row)" icon="el-icon-view">查看工具</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--工具信息-->
    <el-dialog title="工具信息" :visible.sync="dialogToolVisible">
      <el-form label-position="left" inline class="demo-table-expand">
        <el-form-item label="工具名称：">
          <span>{{ tool.toolName }}</span>
        </el-form-item>
        <el-form-item label="工具类型：">
          <span>{{ tool.typeName }}</span>
        </el-form-item>
        <el-form-item label="描述：">
          <span>{{ tool.toolDes }}</span>
        </el-form-item>
        <el-form-item label="图片：">
          <div class="imgDiv" style="width: 50%">
            <img :src="imgUrl"/>
          </div>
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
        name: "lease-info",
        data(){
          return{
            searchForm:{
              searchState:'1',
              searchTime:[]
            },
            optionState:[{
              id:"1",
              name:"进行中"
            },{
              id:"2",
              name:"已结束"
            }],
            leases:[],
            totalSize:0,
            sendData:{
              currentPage:1,
              pageSize:10,
              lease:{}
            },
            tool:{},
            dialogToolVisible:false,
            imgUrl:''
          }
        },
      mounted(){
          this.submitForm();
      },
      methods:{
        handleShowTool(index,row){
          this.$http.get("/api/tool/"+row.toolId).then((res)=>{
              if(res.body.code == "200"){
                this.tool = res.body.data;
                this.imgUrl = this.tool.toolImg;
                this.dialogToolVisible = true;
              }else{
                console.log(res);
              }
          });
        },
        handleCurrentChange(val){
          this.sendData.currentPage = val;
          this.submitForm();
        },
        submitForm(){
          if(parseInt(this.searchForm.searchState) > 0){
            this.sendData.lease.leaseState = this.searchForm.searchState;
          }
          if(this.searchForm.searchTime.length > 0){
            this.sendData.lease.fromTime = this.searchForm.searchTime[0].getTime();
            this.sendData.lease.toTime = this.searchForm.searchTime[1].getTime();
          }
          this.sendData.lease.companyId = sessionStorage.getItem("companyId");
          this.$http.post('/api/lease/list',this.sendData).then((res)=>{
            if(res.body.code =="200") {
              this.totalSize = res.body.data.totalSize;
              this.leases = res.body.data.datas;
            }else{
              console.log(res);
            }
            //this.loading = false;
          });
        },
        tableRowStyle(row,index){
          if (row.row.leaseState == '2') {
            return 'background:oldlace';
          }
          return '';
        },
        resetForm(){
          this.searchForm.searchTime=[];
          this.searchForm.searchState='';
          this.sendData.lease = {};
          this.submitForm();
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
  img{
    max-width: 100%;
    max-height: 100%;
  }
</style>

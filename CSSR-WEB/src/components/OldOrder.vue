<template>
    <div class="old-order">
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


      <el-table
        :data="orders"
        style="width: 100%"
        :default-sort = "{prop: 'createTime', order: 'descending'}"
        :row-style="tableRowStyle">
        <el-table-column
          type="index"
          width="50">
        </el-table-column>

        <el-table-column
          prop="orderTitle"
          label="订单标题">
        </el-table-column>

        <el-table-column
          prop="userMc"
          label="用户">
        </el-table-column>

        <el-table-column
          prop="state"
          label="订单状态">
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="创建日期">
        </el-table-column>
        <el-table-column
          prop="lastUpdateTime"
          label="最近更新">
        </el-table-column>

        <el-table-column label="操作" width="300px">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="primary"
              @click="handleShowDemand(scope.$index, scope.row)" icon="el-icon-view">需求信息</el-button>

            <el-tooltip placement="top" v-if="scope.row.orderState == '2'" effect="light">
              <div slot="content">
                <div class="block" v-if="scope.row.orderState == '2' && scope.row.orderScore<=0">
                  <el-rate
                    v-model="starValue"
                    :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
                    show-text
                  ></el-rate>
                  <el-button
                    size="mini"
                    type="primary"
                    @click="handleStar(scope.$index, scope.row)">提交评分</el-button>
                </div>

                <div class="block" v-if="scope.row.orderState == '2' && scope.row.orderScore>0">
                  <el-rate
                    v-model="scope.row.orderScore"
                    :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
                    show-text
                    disabled
                  ></el-rate>
                </div>
              </div>
              <el-button
                size="mini"
                type="primary"
                v-if="scope.row.orderState == '2' && scope.row.orderScore<=0"
                icon="el-icon-star-off">评分</el-button>

              <el-button
                size="mini"
                type="primary"
                v-if="scope.row.orderState == '2' && scope.row.orderScore>0"
                icon="el-icon-star-on">查看评分</el-button>

              <el-button></el-button>
            </el-tooltip>


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
          <el-form-item label="图片：">
            <div class="imgDiv" style="width: 50%">
              <img :src="demand.demandImg" style="max-width: 100%;max-height: 100%"/>
            </div>
          </el-form-item>
          <el-form-item label="需求备注：">
            <span>{{ demand.demandRemark }}</span>
          </el-form-item>
          <el-form-item label="创建时间：">
            <span>{{ demand.createTime }}</span>
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
        name: "old-order",
        data(){
          return{
            dialogDemandVisible:false,
            searchForm:{
              searchState:'',
              searchTime:[]
            },
            orders:[],
            options:[],
            optionState:[{
              id:'1',
              name:'处理中'
            },{
              id:'2',
              name:'已结束'
            },{
              id:'3',
              name:'中断'
            },{
              id:'4',
              name:'取消'
            }],
            sendData:{
              currentPage:1,
              pageSize:10,
              order:{
              }
            },
            totalSize:0,
            demand:{},
            starValue:null
          }
        },
        mounted(){
          this.submitForm();
        },
        filters:{
          formatMoney:function(val){
            if(val){
              return val + " 元";
            }else{
              return '';
            }
          }
        },
        methods:{
          submitForm(){

            if(parseInt(this.searchForm.searchState) > 0){
              this.sendData.order.orderState = this.searchForm.searchState;
            }
            if(this.searchForm.searchTime.length > 0){
              this.sendData.order.fromTime = this.searchForm.searchTime[0].getTime();
              this.sendData.order.toTime = this.searchForm.searchTime[1].getTime();
            }
            this.sendData.order.companyId = sessionStorage.getItem("companyId");
            this.$http.post('/api/order/list',this.sendData).then((res)=>{
              if(res.body.code =="200") {
                this.totalSize = res.body.data.totalSize;
                this.orders = res.body.data.datas;
              }else{
                console.log(res);
              }
              //this.loading = false;
            });
          },
          resetForm(){
            this.searchForm.searchTime=[];
            this.searchForm.searchState='';
            this.sendData.order = {};
            this.submitForm();
          },
          handleShowDemand(index,row) {
            this.$http.get("/api/demand/get/"+row.demandId).then((res)=>{
              if(res.body.code == 200){
                this.demand = res.body.data;
                this.dialogDemandVisible = true;
              }else{
                console.log(res);
              }
            });
          },
          handleCurrentChange(val){
            this.sendData.currentPage = val;
            this.submitForm();
          },
          tableRowStyle(row,index){
            if (row.row.orderState == '2') {
              return 'background:#f0f9eb';
            }else if(row.row.orderState == '3'){
              return 'background:oldlace';
            }else if(row.row.orderState == '4'){
              return 'background:#FFCCCC';
            }
            return '';
          },
          handleStar(index,row){
            if(this.starValue == null){
              this.$message.warning("请打分");
              return;
            }
            let send = {
              orderId : row.orderId,
              userId : row.userId,
              orderScore : this.starValue
            };
            this.$http.post("/api/order/star",send).then((res)=>{
              if(res.body.code == "200"){
                this.$message.success("评分成功");
                this.submitForm();
              }else{
                this.$message.success("评分失败");
                console.log(res);
              }
            });
          },
          clear(){
            this.starValue = null;
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

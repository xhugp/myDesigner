<template>
  <div class="demand" id="demand"
       v-loading="loading"
       element-loading-text="加载中"
       element-loading-spinner="el-icon-loading"
  >
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
      <el-button type="warning" @click="deleteBatch" icon="el-icon-delete">批量删除</el-button>
    </el-form>
    <br/>
    <el-table
      :data="demands"
      style="width: 100%"
      @selection-change="handleSelectionChange"
      :default-sort = "{prop: 'createTime', order: 'descending'}"
      :row-style="tableRowStyle">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">

            <el-steps :space="1000" :active="parseInt(props.row.demandState)" finish-status="success">
              <el-step title="已发布"></el-step>
              <el-step title="处理中"></el-step>
              <el-step title="结束" v-if="parseInt(props.row.demandState) == 3" status="success"></el-step>
              <el-step title="中断" v-if="parseInt(props.row.demandState) == 4" status="wait"></el-step>
              <el-step title="撤销" v-if="parseInt(props.row.demandState) == 5" status="error"></el-step>
            </el-steps>

            <el-form-item label="标题：">
              <span>{{ props.row.demandTitle }}</span>
            </el-form-item>
            <el-form-item label="类别：">
              <span>{{ props.row.typeName }}</span>
            </el-form-item>

            <el-form-item label="内容：">
              <span>{{ props.row.demandContent }}</span>
            </el-form-item>

            <el-form-item label="薪酬：">
              <span>{{ props.row.demandRepay | formatMoney}}</span>
            </el-form-item>
            <el-form-item label="发布日期：">
              <span>{{ props.row.createTime }}</span>
            </el-form-item>
            <el-form-item label="最近更新：">
              <span>{{ props.row.lastUpdateTime }}</span>
            </el-form-item>
            <el-form-item label="图片：">
              <div class="imgDiv" style="width: 50%">
                <img :src="props.row.demandImg"/>
              </div>
            </el-form-item>
            <el-form-item label="备注：">
              <span>{{ props.row.demandRemark }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="标题"
        prop="demandTitle">
      </el-table-column>
      <el-table-column
        label="薪酬（元/日）"
        prop="demandRepay"
        sortable>
      </el-table-column>
      <el-table-column
        label="发布日期"
        sortable
        prop="createTime">
      </el-table-column>
      <el-table-column
        label="最近更新"
        sortable
        prop="lastUpdateTime">
      </el-table-column>
      <el-table-column label="操作" width="300px">
        <template slot-scope="scope">

          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)" icon="el-icon-edit">编辑</el-button>
          <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)" icon="el-icon-delete">删除</el-button>

          <el-popover
            placement="bottom"
            width="400"
            v-if="scope.row.demandState == '1'"
            trigger="click">
            <el-select v-model="seluser" placeholder="请选择">
              <el-option
                v-for="item in users"
                :key="item.userId"
                :label="item.userMc"
                :value="item.userId">
              </el-option>
            </el-select>
            <el-button
            size="mini"
            type="primary"
            @click="submitSelect(scope.$index, scope.row)"
            >确定</el-button>
            <el-button
              size="mini"
              type="primary"
              slot="reference">派工</el-button>
          </el-popover>



          <el-button
            size="mini"
            type="primary"
            v-if="scope.row.demandState == '5'"
            @click="handleReUpdate(scope.$index, scope.row)" icon="el-icon-upload2">重新发布</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="需求编辑" :visible.sync="dialogFormVisible"
               :before-close="handleClose" modal>
      <el-form :model="newDemand">
        <el-form-item label="需求类型">
          <el-select v-model="newDemand.demandType" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>

        </el-form-item>
        <el-form-item label="需求标题">
          <el-input v-model="newDemand.demandTitle" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="需求薪酬">
          <el-input-number v-model="newDemand.demandRepay" auto-complete="off"></el-input-number>
        </el-form-item>
        <el-form-item label="需求内容">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="newDemand.demandContent">
          </el-input>
        </el-form-item>

        <el-form-item label="需求备注">
        <el-input
          type="textarea"
          :rows="2"
          placeholder="请输入备注"
          v-model="newDemand.demandRemark">
        </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateDemand">确 定</el-button>
      </div>
    </el-dialog>
    <div class="block" style="position:absolute;right: 20px;bottom: 20px;" background="blue">
    <el-pagination
      layout="total,prev, pager, next"
      @current-change="handleCurrentChange"
      :total="totalSize">
    </el-pagination>
    </div>
  </div>
</template>

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
  .demand{
    width: 100%;
    height: 100%;
  }
  img{
    max-width: 100%;
    max-height: 100%;
  }
</style>

<script>
    export default {
        name: "demand",
      data(){
        return {
          demands : [],
          users:[{
            userId:1,
            userMc:'asi'
          }],
          seluser:'',
          newDemand:{},
          deleteList:[],
          dialogFormVisible: false,
          options:[],
          loading: true,
          totalSize:0,
          currentPage:1,
          searchForm:{
            searchType:'',
            searchState:'',
            searchTime:[]
          },
          optionState:[{
            id:'1',
            name:'发布中'
          },{
            id:'2',
            name:'处理中'
          },{
            id:'3',
            name:'已结束'
          },{
            id:'4',
            name:'中断'
          },{
            id:'5',
            name:'撤销'
          }],
          sendData:{
            currentPage:1,
            pageSize:10,
            demand:{}
          }
        }
      },
      mounted(){
        this.sendData.demand.companyId = sessionStorage.getItem("companyId");
        this.loading = true;
        this.$http.post("/api/demand/list",this.sendData).then((res)=> {
          if(res.body.code =="200") {
            this.totalSize = res.body.data.totalSize;
            this.demands = res.body.data.datas;
          }else{
            console.log(res);
          }
        });
        this.$http.get("/api/get-dtype").then((res)=> {
          this.options = res.body.data;
        });
        this.loading = false;

        this.$http.get("/api/user/simple-list/"+sessionStorage.getItem("companyId")).then((res)=> {
          this.users = res.body.data;
        });
      },
      filters:{
        formatMoney:function(val){
          return val + " 元";
        },
        formatState:function (val) {
          if(val=='1'){
            return '发布中';
          }else if(val == '2'){
            return '处理中';
          }else if(val == '3'){
            return '已结束';
          }else {
            return '';
          }
        }

      },
      methods: {
        handleEdit(index, row) {
          if(row.demandState != "1" && row.demandState != "5"){
            this.$message({
              type:'warning',
              message:'该需求已经被受理，无法修改'
            });
            return;
          }
          this.$http.get('/api/demand/get/'+ row.demandId).then((res)=>{
            if(res.body.code == '200') {
              this.newDemand = res.body.data;
              this.dialogFormVisible = true;
            }else{
              console.log(res);
              this.$message.error("暂时无法编辑");
            }
          })
        },
        handleReUpdate(index, row){
          if(row.demandState != '5'){
            this.$message.warning("该需求无法被重新提起");
            return;
          }
          this.$confirm('确认重提该需求？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            //请求重提需求
            this.$http.post('/api/demand/reupdate',row).then((res)=>{
              if(res.body.code == '200'){
                this.$message({
                  type: 'success',
                  message: '重提成功!'
                });
                this.submitForm();
              }else{
                console.log(res);
                this.$message.error("重提失败");
              }
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消操作'
            });
          });
        },
        handleDelete(index, row) {
          if(row.demandState != '1' && row.demandState != '5'){
            this.$message.warning("该需求已经被受理，无法删除");
            return;
          }
          this.$confirm('此操作将永久删除该需求, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            //请求删除需求
            this.$http.delete('/api/demand/delete/'+row.demandId).then((res)=>{
              if(res.body.code == '200'){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                this.demands.pop(row);
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
        handleSelectionChange(val){
          this.deleteList = val;
        },
        tableRowStyle(row,index){
          if (row.row.demandState == '3') {
            return 'background:#f0f9eb';
          } else if (row.row.demandState == '4') {
            return 'background:oldlace';
          } else if (row.row.demandState == '5') {
            return 'background:#FFCCCC';
          }
          return '';
        },
        handleClose(done){
            this.$confirm('放弃修改？')
              .then(_ => {
                done();
              })
              .catch(_ => {});

        },
        updateDemand(){//提交更改
          this.$http.post("/api/demand/update",this.newDemand).then((res)=>{
            if(res.body.code == "200"){
              this.dialogFormVisible = false;
              this.$message({
                type: 'success',
                message: '修改成功!'
              });
              this.submitForm();
            }else{
              console.log(res);
            }
          });
        },
        handleCurrentChange(val){
          this.sendData.currentPage = val;
          this.submitForm();
        },
        resetForm() {
          this.searchForm.searchTime=[];
          this.searchForm.searchType='';
          this.searchForm.searchState='';
          this.sendData.demand = {};
          this.submitForm();
        },
        submitForm(){
          //console.log(this.searchForm.searchState);
          //this.loading = true;
          if(this.searchForm.searchType>-1){
            this.sendData.demand.demandType = this.searchForm.searchType;
          }
          if(parseInt(this.searchForm.searchState) > 0){
            this.sendData.demand.demandState = this.searchForm.searchState;
          }
          if(this.searchForm.searchTime.length > 0){
            this.sendData.demand.fromTime = this.searchForm.searchTime[0].getTime();
            this.sendData.demand.toTime = this.searchForm.searchTime[1].getTime();
          }
          this.sendData.demand.companyId = sessionStorage.getItem("companyId");
          this.$http.post('/api/demand/list',this.sendData).then((res)=>{
            if(res.body.code =="200") {
              console.log(res);
              this.totalSize = res.body.data.totalSize;
              this.demands = res.body.data.datas;
            }else{
              console.log(res);
            }
            //this.loading = false;
          });
        },

        deleteBatch(){
          let list = this.deleteList;
          if(list.length<1){
            this.$message.warning("请选择需要删除的需求");
            return;
          }
          let demandArr=[];
          for(let i = 0;i<list.length;i++){
            if(list[i].demandState != "1"){
              this.$message.warning("无法删除处理中的需求");
              return;
            }
            demandArr.push(list[i].demandId);
          }
          this.$confirm('将永久删除选中需求, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            //请求删除需求
            this.$http.delete('/api/demand/delete-batch/'+ demandArr.toString()).then((res)=>{
              //console.log(res);
              if(res.body.code == '200'){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                this.deleteList=[];
                this.submitForm();
              }else{
                console.log(res);
                this.$message.error("删除失败，稍后再试");
              }
            });

          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        submitSelect(index, row){
          let data = {
            demandId : row.demandId,
            userId : this.seluser
          };
          this.$http.post('/api/order/add',data).then((res)=>{
            if(res.body.code =="200") {
              this.$message.success("派工成功");
              this.submitForm();
            }else{
              console.log(res);
            }
          });
        }

      }
    }
</script>



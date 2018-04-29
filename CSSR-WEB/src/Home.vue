<template>
  <div id="home">
    <el-container>
      <el-header height="80px">
        <div class="logo">
          <h1>汽修协作系统·客户端</h1>
        </div>
        <div class="userinfo">
          <span>欢迎您：{{manageMc}}</span>
          <span>上次登录：{{lastTime}}</span>
        </div>
      </el-header>
      <el-container style="width: 100%;
    position: absolute;
    top: 80px;
    bottom: 0px;
    left: 0px;">
        <el-aside width="201px" style="height:100%;">
          <el-row class="tac" style="height:100%;">
            <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
              background-color="#545c64"
              text-color="#fff"
              unique-opened
              router
              active-text-color="#ffd04b" style="height:100%;">
              <el-menu-item index="/home/my-work">
                <i class="el-icon-menu"></i>
                <span slot="title">工作台</span>
              </el-menu-item>

              <el-submenu index="3">
                <template slot="title">
                  <i class="el-icon-bell"></i>
                  <span>请求处理</span>
                </template>
                <el-menu-item-group>
                  <template slot="title">订单</template>
                  <el-menu-item index="/home/order-request">订单请求</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group>
                  <template slot="title">租赁</template>
                  <el-menu-item index="/home/lease-request">租赁请求</el-menu-item>
                </el-menu-item-group>
              </el-submenu>

              <el-submenu index="1">
                <template slot="title">
                  <i class="el-icon-message"></i>
                  <span>需求管理</span>
                </template>
                <el-menu-item-group>
                  <template slot="title">信息</template>
                  <el-menu-item index="/home/demand">需求管理</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group>
                  <!--
                  <template slot="title">订单</template>
                  <el-menu-item index="/home/old-order">已发订单</el-menu-item>

                  <el-menu-item index="/home/flow-tracing">流程追踪</el-menu-item>
                  -->
                </el-menu-item-group>
                <el-menu-item-group title="发布">
                  <el-menu-item index="/home/add-demand">需求发布</el-menu-item>
                  <!--
                  <el-menu-item index="/home/message-release">消息发布</el-menu-item>
                  -->
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="2">
                <template slot="title">
                  <i class="el-icon-star-off"></i>
                  <span>技师管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/home/user-info">基本信息</el-menu-item>
                  <el-menu-item index="/home/user-lately">最近合作</el-menu-item>
                  <el-menu-item index="/home/sign-request">签约请求</el-menu-item>
                </el-menu-item-group>
              </el-submenu>

              <el-menu-item index="/home/old-order">
                <i class="el-icon-document"></i>
                <span slot="title">订单管理</span>
              </el-menu-item>
              <!--
              <el-menu-item index="/home/history-order">
                <i class="el-icon-document"></i>
                <span slot="title">历史订单</span>
              </el-menu-item>
              -->

              <el-submenu index="8">
                <template slot="title">
                  <i class="el-icon-goods"></i>
                  <span>物品租赁</span>
                </template>

                <el-menu-item-group title="工具">
                  <el-menu-item index="/home/tool-manage">工具管理</el-menu-item>
                </el-menu-item-group>

                <el-menu-item-group title="租赁">
                  <el-menu-item index="/home/lease-info">当前租赁</el-menu-item>
                  <!--
                  <el-menu-item index="/home/lease-history">历史租赁</el-menu-item>
                  -->
                </el-menu-item-group>

              </el-submenu>

              <el-submenu index="6">
                <template slot="title">
                  <i class="el-icon-info"></i>
                  <span>工厂管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/home/factory-info">工厂管理</el-menu-item>
                </el-menu-item-group>
              </el-submenu>

              <el-submenu index="4">
                <template slot="title">
                  <i class="el-icon-setting"></i>
                  <span>个人管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/home/my-info">个人信息</el-menu-item>
                </el-menu-item-group>
              </el-submenu>

              <el-menu-item index="/home/version">
                <i class="el-icon-more-outline"></i>
                <span slot="title">关 于</span>
              </el-menu-item>
            </el-menu>
          </el-row>
        </el-aside>
        <el-main style="height:100%">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import Test from './components/Test'
  import Users from './components/Users'
  import { Notification } from 'element-ui';
  export default {
    name: 'home',
    components:{
      Test,Users
    },
    data(){
      return{
        students : [],
        isCollapse: true,
        manageName:sessionStorage.getItem("manageName"),
        lastTime : sessionStorage.getItem("lastTime"),
        manageMc:sessionStorage.getItem("manageMc")
      }
    },
    methods:{
      getStu(){
        this.$http.get('/api/student/list').then((res)=>{
          console.log(res);
          this.students = res.body.data;
        },(res)=>{
          alert("请求失败");
        });
      },
      handleOpen(key, keyPath) {
        //console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        //console.log(key, keyPath);
      }
    },
    mounted(){
      var goEasy = new GoEasy({ appkey: "BC-b904a79209154acf92c19edfcd2df323"});
      goEasy.subscribe({
        channel: "cssr",
        onMessage: function (message) {
          if(message.content == "401"){
            Notification.info({
              title: '提示',
              dangerouslyUseHTMLString: true,
              offset: 50,
              message: '<span>您有新的租赁请求，请前去处理！</span>',
              onClick : function () {
                //this.$router.push({ name: 'lease-request' });
                Notification.close();
              },
              duration:30000
            });
          }
          if(message.content == "402"){
            Notification.info({
              title: '提示',
              dangerouslyUseHTMLString: true,
              offset: 50,
              message: '<span>您有新的订单请求，请前去处理！</span>',
              onClick : function () {
                Notification.close();
              },
              duration:30000
            });
          }
        }
      });
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }
  *{
    font-family: 微软雅黑;
  }
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }

  .el-header{
    padding:0px;
    background: #545c64;
  }
  .logo{
    float: left;
    margin-left: 20px;
    color: white;
  }
  .userinfo{
    float: right;
    margin-right: 20px;
  }
  .userinfo span{
    line-height: 80px;
    color: white;
    margin-left: 5px;
  }
  .el-main{
    padding: 20px 10px 10px 10px;
    background: white;
  }


</style>

<template>
  <div class="my-work">
    <el-container direction="vertical" style="width: 100%;height: 100%">
      <el-header style="height: 50%;background: ivory;">
          <div id="myChart" style="height: 100%;padding-top: 20px;margin: 0 auto"></div>
      </el-header>
      <el-main style="width: 100%;background: ivory;height: 50%;padding: 40px 10px 0px 10px;overflow: hidden">
        <div id="myChart2" style="width: 100%;height: 100%"></div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
    import ElHeader from "element-ui/packages/header/src/main";
    import ElMain from "element-ui/packages/main/src/main";

    export default {
      components: {
        ElMain,
        ElHeader},
      name: "my-work",
      data() {
        return {
          allCount: []
        }
      },
      mounted() {
        var echarts = require('echarts');
        let companyId = sessionStorage.getItem("companyId");
        this.$http.get('/api/get-all-count/'+companyId).then((res) => {
          if (res.body.code == '200') {
            let myChart = echarts.init(document.getElementById('myChart'));
            myChart.setOption({
              title: {text: '信息总数'},
              tooltip: {},
              xAxis: {
                data: ["需求", "订单", "租赁", "员工", "工具", "待处理订单请求","待处理租赁请求"]
              },
              yAxis: {},
              series: [{
                name: '数量',
                type: 'bar',
                data: res.body.data
              }]
            });
          } else {
            console.log(res);
          }
        });

        this.$http.get('/api/get-create-mes/'+companyId).then((res) => {
          if (res.body.code == '200') {
            let myChart2 = echarts.init(document.getElementById('myChart2'));
            // 绘制图表
            myChart2.setOption({
              title: {
                text: '最近七天数据更新',
              },
              tooltip: {
                trigger: 'axis'
              },
              legend: {
                data: ['需求创建', '订单创建', '租赁创建', '签约']
              },
              toolbox: {
                show: true,
                feature: {
                  magicType: {show: true, type: ['stack', 'tiled']},
                  saveAsImage: {show: true}
                }
              },
              xAxis: {
                type: 'category',
                boundaryGap: false,
                data: res.body.data.days
              },
              yAxis: {
                type: 'value'
              },
              series: [{
                name: '需求创建',
                type: 'line',
                smooth: true,
                data: res.body.data.demands
              },
                {
                  name: '订单创建',
                  type: 'line',
                  smooth: true,
                  data: res.body.data.orders
                },
                {
                  name: '租赁创建',
                  type: 'line',
                  smooth: true,
                  data: res.body.data.leases
                }, {
                  name: '签约',
                  type: 'line',
                  smooth: true,
                  data: res.body.data.signs
                }]
            });
          } else {
            console.log(res);
          }
        });

      },
      methods: {

      }
    }
</script>

<style scoped>
  *{
    font-family: 微软雅黑;
  }
  .my-work{
    width: 100%;
    height: 100%;
  }
</style>

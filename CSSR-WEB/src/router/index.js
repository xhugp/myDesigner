import Vue from 'vue'
import Router from 'vue-router'
import VueResource from 'vue-resource'
import AMap from 'vue-amap'
import ElementUI from 'element-ui'
import UserInfo from '@/components/UserInfo'
import MyWork from '@/components/MyWork'
import OldOrder from '@/components/OldOrder'
import FlowTracing from '@/components/FlowTracing'
import MessageRelease from '@/components/MessageRelease'
import FactoryInfo from '@/components/FactoryInfo'
import UserLately from '@/components/UserLately'
import HistoryOrder from '@/components/HistoryOrder'
import MyInfo from '@/components/MyInfo'
import LeaseHistory from '@/components/LeaseHistory'
import LeaseInfo from '@/components/LeaseInfo'
import LeaseRequest from '@/components/LeaseRequest'
import OrderRequest from '@/components/OrderRequest'
import Version from '@/components/Version'
import AddDemand from '@/components/AddDemand'
import ToolManage from '@/components/ToolManage'
import SignRequest from '@/components/SignRequest'
import Demand from '@/components/Demand'
import Home from '@/Home'
import Login from '@/Login'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(Router)
Vue.use(VueResource)
Vue.use(ElementUI)
Vue.use(AMap)


AMap.initAMapApiLoader({
  // 申请的高德key
  key: '2e8a50e7a7274de80855a7f2ae6da699',
  // 插件集合
  plugin: ['Autocomplete', 'PlaceSearch', 'Scale', 'OverView', 'ToolBar', 'MapType', 'Poly','Geocoder','Geolocation']
});

const router =  new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      name: 'login',
      redirect:"/login"
    },{
      path:'/home',
      name:'home',
      component:Home,
      redirect:"/home/my-work",
      children:[
        {
          path:'user-info',
          name:'user-info',
          component:UserInfo
        },{
          path:'my-work',
          name:'my-work',
          component:MyWork
        },{
          path:'old-order',
          name:'old-order',
          component:OldOrder
        },{
          path:'flow-tracing',
          name:'flow-tracing',
          component:FlowTracing
        },{
          path:'message-release',
          name:'message-release',
          component:MessageRelease
        },{
          path:'factory-info',
          name:'factory-info',
          component:FactoryInfo
        },{
          path:'user-lately',
          name:'user-lately',
          component:UserLately
        },{
          path:'sign-request',
          name:'sign-request',
          component:SignRequest
        },{
          path:'history-order',
          name:'history-order',
          component:HistoryOrder
        },{
          path:'my-info',
          name:'my-info',
          component:MyInfo
        },{
          path:'lease-history',
          name:'lease-history',
          component:LeaseHistory
        },{
          path:'lease-info',
          name:'lease-info',
          component:LeaseInfo
        },{
          path:'lease-request',
          name:'lease-request',
          component:LeaseRequest
        },{
          path:'order-request',
          name:'order-request',
          component:OrderRequest
        },{
          path:'version',
          name:'version',
          component:Version
        },{
          path:'add-demand',
          name:'add-demand',
          component:AddDemand
        },{
          path:'demand',
          name:'demand',
          component:Demand
        },{
          path:'tool-manage',
          name:'tool-manage',
          component:ToolManage
        }
      ]
    },
    {
      path:'/login',
      name:'login',
      component:Login
    }
  ]

})
router.beforeEach((to, from, next) => {
  if(to.path == "/login" || to.path == "/" ){
    next();
  }else if(sessionStorage.getItem('manageName') == undefined){
    next("/login");
  }else{
    next();
  }
})

export default router

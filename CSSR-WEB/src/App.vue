<template>
  <router-view @manageSignIn="manageSignIn" :manage="manage"></router-view>
</template>

<script>

export default {
  name: 'App',
  data(){
    return{
      manage : {}
    }
  },
  methods:{
    manageSignIn(manage){
      this.manage = manage;
      sessionStorage.setItem('manageName', manage.manageName);
      sessionStorage.setItem('manageMc', manage.manageMc);
      sessionStorage.setItem('manageId', manage.manageId);
      sessionStorage.setItem('companyId', manage.companyId);
      sessionStorage.setItem('manageRole', manage.manageRole);
      sessionStorage.setItem('lastTime', manage.lastLoginTime);
    }
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

</style>

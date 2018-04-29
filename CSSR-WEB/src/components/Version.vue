<template>
    <div id="version">
      <div style="text-align: center;width: 100%;position:absolute;margin:0 auto;bottom: 10px;">
        <span>Version-Number：{{version.number}}</span><br/>
        <span>Author ：{{version.developer}}</span><br/>
        <span>UpTime ：{{version.uptime}}</span>
      </div>
    </div>
</template>

<script>
    export default {
        name: "version",
        data(){
          return{
            version:{}
          }
        },
        mounted(){
          this.$http.get("/api/get-version").then((res)=>{
            if(res.body.code = "200"){
              this.version = res.body.data;
            }
          })
        }
    }
</script>

<style scoped>

</style>

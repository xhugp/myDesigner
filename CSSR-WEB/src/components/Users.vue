<template>
    <div class="users">
      <table>
        <tr v-for="user in users">
          <td v-text="user.name"></td>
          <td v-text="user.sex"></td>
          <td v-text="user.age"></td>
        </tr>
      </table>
      <form @submit="addUser">
        <input type="text" v-model="newUser.name" placeholder="enter name">
        <input type="text" v-model="newUser.sex" placeholder="enter sex">
        <input type="text" v-model="newUser.age" placeholder="enter age">
        <input type="submit" value="提交"/>
      </form>
    </div>


</template>

<script>
    export default {
        name: "users",
        methods:{
          addUser(e){
            this.users.push({
              name:this.newUser.name,
              sex:this.newUser.sex,
              age:this.newUser.age
            });

            this.newUser={};
            e.preventDefault();
          }
        },
        data(){
          return {
            users: [
              {
                name: '张三',
                sex: '男',
                age: 18
              },
              {
                name: '李四',
                sex: '女',
                age: 11
              },
              {
                name: '王五',
                sex: '男',
                age: 23
              }
            ],
            newUser:{}
         };
        }
    }
</script>

<style scoped>
  *{
    font-family: 微软雅黑;
  }
</style>

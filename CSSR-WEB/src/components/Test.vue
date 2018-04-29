<template>
    <div id="test">
      <p>Test</p>
      <h1>{{titile}}</h1>
      <h1>{{user.name}}</h1>

      <h1 v-if="showname" v-text="user.sex.sex1"></h1>
      <h2 v-else>Nobody</h2>
      <ul>
        <li v-for="item in items" v-text="item.titile"></li>
      </ul>
      <button @click="hello">点击我试试看</button>
      <input type="text" @keyup="presskey" @keyup.enter="pressenter">
      <h1>{{fullname}}</h1>
      <h1>{{msg}}</h1>
    </div>
</template>

<script>
    export default {
        name: "test",//当前组件的名称
      props:{
          msg:{
            type:String,
            default:'这是默认值'
          }
      },
      data(){//数据
          return{
            titile:'hello vue.js',
            user:{
              name:'张三',
              sex:{
                sex1:'男'
              }

            },
            showname:true,
            items:[

              {titile:"item1"},
              {titile:"item2"},
              {titile:"item3"}

            ]
          }
      },
      methods:{//方法
          hello(){
            alert("hello");
          } ,
        presskey(){
          console.log("ahnua");
        },
        pressenter(){
            alert('点击enter')
        }
      },
      computed:{//计算属性
          fullname(){
            return this.titile + " iusa";
          }
      }
    }
</script>

<style scoped>

</style>

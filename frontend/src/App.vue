<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld v-bind:msg=welcomeMessage />
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  data() {
    return {
      welcomeMessage: 'loading'
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData(){
      fetch("/welcomeMessage")
        .then((r)=>{
          return r.json();
        })
        .then((r)=>{
          console.log(r.welcomeMessage);
          this.welcomeMessage = r.welcomeMessage;
        })
        .catch((e)=>{
          console.log(e);
          this.welcomeMessage = "connection error";
        })
    }
  },
  components: {
    HelloWorld
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

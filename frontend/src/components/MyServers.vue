<template>
  <div class="main-container">
    <div class="main-header">
      <div class="title">
          Your server list
      </div>
    </div>

    <div class="content">
        <div class="content-header">
            <div class="single-property">Server name</div>
            <div class="single-property">Game mode</div>
            <div class="single-property">Mods</div>
        </div>

        <div class="content-data" ref="serverList">

        </div>
    </div>
  </div>
</template>

<script>
import MyServer from "./Elements/Components/MyServer";
import Vue from "vue";
import {api} from "../api";

export default {
  name: "MyServers",
  components: {},
  data() {
    return {
    }
  },
  mounted() {
    this.loadServers();
  },
  methods: {
    loadServers(){
      console.log("wtf");
      api.getMyServers()
          .then(response => this.processResponse(response))
          .catch(()=>this.showError());
    },
    processResponse(response){
      if(response.status === 200)
        this.loadServersFromJson(response.jsonBody);
      else
        this.showError()
    },
    loadServersFromJson(json){
      for(let serverEntity of json.servers) {
        this.addServer(serverEntity.id, serverEntity.name, serverEntity.gameMode,
            serverEntity.mods, serverEntity.token);
      }
    },
    addServer(id, name, gameMode, mods, token){
      let newElem = new (Vue.extend(MyServer));
      newElem.$props.id = id;
      newElem.$props.serverName = name;
      newElem.$props.gameMode = gameMode;
      newElem.$props.mods = mods;
      newElem.$props.token = token;

      newElem.$mount();
      this.$refs.serverList.appendChild(newElem.$el);
    },
    showError() {
      this.$refs.serverList.innerText = "load server list error ;/";
    }
  }
}
</script>

<style scoped lang="less">
@import "~@/theme.less";


.main-container{
  font-family: @font-family-primary;
  background-image: url("./../assets/bg.jpg");
  background-size: cover;
  display: flex;
  flex-direction: column;
  align-items: center;

  .main-header{
      width: 80%;
      margin-top: 2vh;
      height: 21vh;
      background-color: rgba(0, 0, 0, 0.7);
      border-radius: 20px;
      display: flex;
      flex-direction: column;

      .title{
        margin-left: 3vh;
        display: flex;
        color: @theme-secondary-dark;
        font-size: 10vh;
      }

      .header-data{
        align-items: center;
        font-size: 3vh;
        margin: 0 3vh;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
      }
  }

  .content{
    width: 80%;
    height: 67vh;
    background-color: rgba(0, 0, 0, 0.7);
    border-radius: 20px;
    display: flex;
    flex-direction: column;
    margin-top: 5%;

    *::-webkit-scrollbar {
      width: 8px;
    }

    *::-webkit-scrollbar-track {
      margin-bottom: 12px;
    }

    *::-webkit-scrollbar-thumb {
      background-color: @theme-secondary-dark;
      border-radius: 20px;
      border: 2px solid @theme-primary;
    }


      .content-header{
        height: 10%;
        background-color: rgba(0, 0, 0, 0.9);
        border-radius: 25px;
        display: grid;
        align-content: center;
        grid-template-columns: @my-server-list-grid-template-columns;
        white-space: nowrap;



        font-size: 1.4em;

        .single-property{
          align-self: stretch;
          padding-left: 1em;
        }
        &>div{
          text-align: center;
        }
        &>div:nth-child(1){
          text-align: left;
        }

      }

      .content-data {
        overflow-y: auto;
        overflow-x: hidden;
        max-height: 62vh;



      }
  }
}



</style>
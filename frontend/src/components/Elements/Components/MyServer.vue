<template>
  <div class="server-container">
    <div id="serverName">{{serverName}}</div>
    <div>{{gameMode}}</div>
    <div v-if="mods" class="textGreen">Yes</div>
    <div v-else class="textRed">No</div>
    <div class="regenerateToken" @click="renewToken"><span class="mdi mdi-autorenew"></span></div>
    <div class="tooltip">{{token}}</div>
  </div>
</template>

<script>
import {api} from "../../../api";

export default {
  name: "Server",
  props: {
    serverName: String,
    gameMode: String,
    mods: Boolean,
    token: String,
    id: String
  },
  data() {
    return {
      activeColor: "white"
    }
  },
  methods: {
    renewToken(){
      api.renewToken(this.id)
          .then(response => this.processResponse(response))
          .catch(()=>this.showError());
    },
    processResponse(response){
      if(response.status === 200)
        this.token = response.jsonBody.token;
      else
        this.showError();
    },
    showError(){
      alert("Cant renew token");
    }
  }
}
</script>

<style scoped lang="less">
@import "~@/theme.less";


.server-container {
  position: relative;
  margin: 0.3em 0;
  font-size: 1.3em;

  background-color: rgba(0, 0, 0, 0);
  display: grid;
  grid-template-columns: @my-server-list-grid-template-columns;

  &>div{
    text-align: center;
  }
  &>div:nth-child(1){
    text-align: left;
  }

  #serverName {
    padding-left: 1em;
  }

  .textGreen{
    color: green;
  }

  .textYellow{
    color: yellow;
  }

  .textRed{
    color: red;
  }
  .tooltip {
    opacity: 0;
    box-sizing: border-box;
    transition: opacity 0.5s;
    background-color: rgba(0,0,0, 0.9);
    padding-top: 0.2em;
    position: absolute;
    top: -0.2em;
    bottom: -0.2em;
    left: 0;
    right: 2em;
  }
  &:hover {
    .tooltip {
      opacity: 1;
      transition: opacity 0.5s;
    }
  }
}

</style>
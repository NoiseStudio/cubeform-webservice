<template>
  <div class="server-container">
    <div id="serverName">{{serverName}}</div>
    <div>{{gameMode}}</div>
    <div v-if="mods" class="textGreen">Yes</div>
    <div v-else class="textRed">No</div>
    <div
    v-bind:class="{
    textGreen: (this.playersCount/this.maxPlayersCount*100)<60,
    textYellow: (this.playersCount/this.maxPlayersCount*100)>=60,
    textRed: (this.playersCount/this.maxPlayersCount*100)>=90}"
    >{{playersCount}} / {{maxPlayersCount}}</div>
    <div v-bind:class="{textGreen: ping<50, textYellow: (ping>=50&&ping<100), textRed: ping>=100}">{{ping}}</div>
  </div>
</template>

<script>
export default {
  name: "Server",
  props: {
    serverName: String,
    gameMode: String,
    mods: Boolean,
    playersCount: Number,
    maxPlayersCount: Number,
    ping: Number
  },
  data() {
    return {
      activeColor: "white"
    }
  }
}
</script>

<style scoped lang="less">
@import "~@/theme.less";


.server-container {
  margin: 0.3em 0;
  font-size: 1.3em;

  background-color: rgba(0, 0, 0, 0);
  display: grid;
  grid-template-columns: @server-list-grid-template-columns;

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
}

</style>
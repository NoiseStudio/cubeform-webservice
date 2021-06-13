<template>
  <div class="main-container">
    <div class="main-header">
      <div class="title">
          Server list
      </div>
      <div class="header-data-container">
        <div class="header-data">
          <div class="loremipsum">
            loremipsum dolor
          </div>
          <div class="numbers-container">
            Servers: {{this.servers}}
            <br>
            Players: {{this.players}}
          </div>
        </div>
      </div>
    </div>

    <div class="content">
        <div class="content-header">
            <div class="single-property">Server name</div>
            <div class="single-property">Game mode</div>
            <div class="single-property">Mods</div>
            <div class="single-property">Players</div>
            <div class="single-property">Ping</div>
        </div>

        <div class="content-data">

          <Server
            v-bind:server-name='new String("server1")'
            v-bind:game-mode ='new String("server1")'
            v-bind:mods="false"
            v-bind:players-count=25
            v-bind:max-players-count="522"
            v-bind:ping=10>
          </Server>
          <Server
            v-bind:server-name='new String("innyserver")'
            v-bind:game-mode ='new String("mode1")'
            v-bind:mods="true"
            v-bind:players-count=421
            v-bind:max-players-count="6565"
            v-bind:ping=111>
          </Server>
          <Server
            v-bind:server-name='new String("costam")'
            v-bind:game-mode ='new String("dsdsds")'
            v-bind:mods="false"
            v-bind:players-count=341
            v-bind:max-players-count="556"
            v-bind:ping=77>
          </Server>
          <Server
            v-bind:server-name='new String("532asfd")'
            v-bind:game-mode ='new String("asd")'
            v-bind:mods="true"
            v-bind:players-count=0
            v-bind:max-players-count="434"
            v-bind:ping=54>
          </Server>
          <Server
            v-bind:server-name='new String("das364g")'
            v-bind:game-mode ='new String("adsad")'
            v-bind:mods="true"
            v-bind:players-count=231
            v-bind:max-players-count="45120"
            v-bind:ping=10>
          </Server>
          <Server
            v-bind:server-name='new String("wredasq324eadew")'
            v-bind:game-mode ='new String("server1")'
            v-bind:mods="false"
            v-bind:players-count=332
            v-bind:max-players-count="344"
            v-bind:ping=10>
          </Server>
          <Server
            v-bind:server-name='new String("asdasdads")'
            v-bind:game-mode ='new String("dsa")'
            v-bind:mods="true"
            v-bind:players-count=21
            v-bind:max-players-count="67"
            v-bind:ping=45>
          </Server>
          <Server
            v-bind:server-name='new String("7i7kk")'
            v-bind:game-mode ='new String("hgkkkj")'
            v-bind:mods="true"
            v-bind:players-count=25
            v-bind:max-players-count="256"
            v-bind:ping=555>
          </Server>
          <Server
            v-bind:server-name='new String("server1")'
            v-bind:game-mode ='new String("server1")'
            v-bind:mods="false"
            v-bind:players-count=512
            v-bind:max-players-count="3232"
            v-bind:ping=96>
          </Server>
          <Server
            v-bind:server-name='new String("server1")'
            v-bind:game-mode ='new String("server1")'
            v-bind:mods="false"
            v-bind:players-count=2
            v-bind:max-players-count="51"
            v-bind:ping=10>
          </Server>
          <Server
            v-bind:server-name='new String("server1")'
            v-bind:game-mode ='new String("server1")'
            v-bind:mods="false"
            v-bind:players-count=12
            v-bind:max-players-count="21"
            v-bind:ping=10>
          </Server>
          <Server
            v-bind:server-name='new String("innyserver")'
            v-bind:game-mode ='new String("mode1")'
            v-bind:mods="true"
            v-bind:players-count=21
            v-bind:max-players-count="37"
            v-bind:ping=111>
          </Server>
          <Server
            v-bind:server-name='new String("costam")'
            v-bind:game-mode ='new String("dsdsds")'
            v-bind:mods="false"
            v-bind:players-count=2223
            v-bind:max-players-count="231231"
            v-bind:ping=77>
          </Server>
          <Server
            v-bind:server-name='new String("532asfd")'
            v-bind:game-mode ='new String("asd")'
            v-bind:mods="true"
            v-bind:players-count=2
            v-bind:max-players-count="23"
            v-bind:ping=54>
          </Server>
          <Server
            v-bind:server-name='new String("das364g")'
            v-bind:game-mode ='new String("adsad")'
            v-bind:mods="true"
            v-bind:players-count=23
            v-bind:max-players-count="444"
            v-bind:ping=10>
          </Server>
          <Server
            v-bind:server-name='new String("wredasq324eadew")'
            v-bind:game-mode ='new String("server1")'
            v-bind:mods="false"
            v-bind:players-count=55
            v-bind:max-players-count="512"
            v-bind:ping=10>
          </Server>
          <Server
            v-bind:server-name='new String("asdasdads")'
            v-bind:game-mode ='new String("dsa")'
            v-bind:mods="true"
            v-bind:players-count=33
            v-bind:max-players-count="645"
            v-bind:ping=45>
          </Server>
          <Server
            v-bind:server-name='new String("7i7kk")'
            v-bind:game-mode ='new String("hgkkkj")'
            v-bind:mods="true"
            v-bind:players-count=33
            v-bind:max-players-count="221"
            v-bind:ping=555>
          </Server>
          <Server
            v-bind:server-name='new String("server1")'
            v-bind:game-mode ='new String("server1")'
            v-bind:mods="false"
            v-bind:players-count=332
            v-bind:max-players-count="2332"
            v-bind:ping=96>
          </Server>
          <Server
            v-bind:server-name='new String("server1")'
            v-bind:game-mode ='new String("server1")'
            v-bind:mods="false"
            v-bind:players-count=26
            v-bind:max-players-count="552"
            v-bind:ping=10>
          </Server>




        </div>
    </div>
  </div>
</template>

<script>
import Server from "@/components/Elements/Components/Server";
export default {
  name: "Servers",
  components: {Server},
  data() {
    return {
      servers: 21,
      players: 37
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
        grid-template-columns: @server-list-grid-template-columns;
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
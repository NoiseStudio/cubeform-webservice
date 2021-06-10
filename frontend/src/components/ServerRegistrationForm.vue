<template>
  <div class="mainRegisterContainer">
    <ValidationObserver tag="form" name="form" @submit.prevent="handleServerRegistration" v-slot="{invalid}" class="registerServerForm">
      <label class="registerServerHeader">
        Register your server!
      </label>
      <ValidationProvider name="name" rules="required|min:5" v-slot="{errors}" class="singleField">
        <div class="nameInput">
          <label class="formLabel">Server name</label>
          <input class="formInput" v-model="server.name">
        </div>
        <span class="errorSpan">{{errors[0]}}</span>
      </ValidationProvider>
      <ValidationProvider name="gameMode" rules="required|min:5" v-slot="{errors}" class="singleField">
        <div class="nameInput">
          <label class="formLabel">Game mode</label>
          <input class="formInput" v-model="server.mode">
        </div>
        <span class="errorSpan">{{errors[0]}}</span>
      </ValidationProvider>
      <ValidationProvider name="mods" class="singleField">
        <div class="nameInput">
          <label class="formLabel">Mods</label>
          <input class="formInput checkBox" v-model="server.maxPlayers" type="checkbox">
        </div>
      </ValidationProvider>
      <div class="buttonContainer">
        <button :disabled="invalid" class="submitButton">submit</button>
      </div>
    </ValidationObserver>
  </div>
</template>

<script>
import Server from "@/models/Server";

export default {
  name: "ServerRegistrationForm",
  methods: {
    handleServerRegistration(){
      console.log("server registered!")
    }
  },
  data(){
    return {
      server: new Server('','',false,0)
    }
  }
}
</script>

<style scoped lang="less">
@import "~@/theme.less";

.mainRegisterContainer{
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;

  .registerServerForm{
    display: flex;
    flex-direction: column;


    width: 60%;
    margin: 2em;

    .registerServerHeader{
      font-size: 3em;
      font-family: @font-family-primary;
    }

    .singleField{
      margin: 1em;
      display: flex;
      flex-direction: column;
      justify-content: space-between;

      .nameInput {
        display: flex;
        flex-direction: column;

        .formLabel{
          text-align: left;
          margin-bottom: 0.5em;
          font-size: 1.5em;
        }

        .formInput{
          background: lighten(@theme-primary-light, 7%);
          border: none;
          color: @theme-primary-foreground;
          border-bottom: lighten(@theme-secondary-dark,5%) 3px solid;
          outline: none;
          -moz-border-radius-bottomleft: 5px;
          -moz-border-radius-bottomright: 5px;

          //box-shadow: 5px 10px 12px black;
        }

        .checkBox {
          margin-top: 0.5em;
          margin-left: 1em;
          -webkit-transform: scale(3); /* Safari and Chrome */
        }


        .formInput:hover{
          outline: none;
        }

      }
      .errorSpan {
        margin: 1em 0.3em 0.3em;
        color: #adadad;
      }
    }

    .submitButton {
      width: 6em;
      color: @theme-primary-foreground;
      background-color: lighten(@theme-primary-light, 7%);
      height: 2em;
      cursor: pointer;
      font-size: 28px;
      padding: 10px;
      text-align: center;
      -webkit-transition-duration: 0.4s; /* Safari */
      transition-duration: 0.4s;
      text-decoration: none;
      overflow: hidden;

    }

    .submitButton:after {
      content: "";
      background: lighten(@theme-primary-light, 20%);
      display: block;
      padding-top: 300%;
      padding-left: 350%;
      margin-left: -20px!important;
      margin-top: -120%;
      opacity: 0;
      transition: all 0.8s
    }

    .submitButton:active:after {
      padding: 0;
      margin: 0;
      opacity: 1;
      transition: 0s
    }



  }
}





</style>

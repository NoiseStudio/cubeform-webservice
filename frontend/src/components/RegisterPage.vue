<template>
  <div class="mainRegisterContainer">
    <ValidationObserver tag="form" name="form" @submit.prevent="handleSubmit" v-slot="{invalid}" class="registerServerForm">
      <label class="registerServerHeader">
        Login
      </label>
      <ValidationProvider name="username" rules="required|max:255" v-slot="{errors}" class="singleField">
        <div class="nameInput">
          <label class="formLabel">Username</label>
          <input class="formInput" v-model="username">
        </div>
        <span class="errorSpan">{{errors[0]}}</span>
      </ValidationProvider>
      <ValidationProvider name="email" rules="required|max:255" v-slot="{errors}" class="singleField">
        <div class="nameInput">
          <label class="formLabel">Email</label>
          <input class="formInput" v-model="email" type="email">
        </div>
        <span class="errorSpan">{{errors[0]}}</span>
      </ValidationProvider>
      <ValidationProvider name="password" rules="required|min:8|max:255" v-slot="{errors}" class="singleField" ref="password">
        <div class="nameInput">
          <label class="formLabel">Password</label>
          <input class="formInput" v-model="password" type="password">
        </div>
        <span class="errorSpan">{{errors[0]}}</span>
      </ValidationProvider>
      <ValidationProvider name="passwordRepeat" rules="required|max:255|confirmed:password" v-slot="{errors}" class="singleField">
        <div class="nameInput">
          <label class="formLabel">Repeat password</label>
          <input class="formInput" v-model="passwordR" type="password">
        </div>
        <span class="errorSpan">{{errors[0]}}</span>
      </ValidationProvider>
      <div class="buttonContainer">
        <button :disabled="invalid" class="submitButton">submit</button>
      </div>
    </ValidationObserver>
    <span class="errorSpan">{{serverError}}</span>
  </div>
</template>

<script>
import {api} from "@/api";
import {getErrorFromResponseBody} from "@/Utils";

export default {
  name: "RegisterPage",
  methods: {
    handleSubmit(){
      api.register(this.username, this.password, this.email)
        .then((r)=>this.handleServerResponse(r))
        .catch(()=>this.showError({}))
    },
    handleServerResponse(response){
      if(response.status === 200) {
        this.$router.push("/login");
      }
      if(response.status >= 400 && response.status <= 499){
        this.showError(response.jsonBody)
      }
    },
    showError(responseBody){
      this.serverError = getErrorFromResponseBody(responseBody);
    }
  },
  data(){
    return {
      username: "",
      email: "",
      password: "",
      passwordR: "",
      serverError: ""
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

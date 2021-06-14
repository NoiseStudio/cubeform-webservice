<template>
  <div class="app-container">

    <div class="app-menu-toggle-button" :class="menuVisible?'app-menu-show':''" v-on:click="toggleMenu">
      <span class="mdi mdi-menu" />
    </div>

    <ElHeader class="app-menu" :class="menuVisible?'app-menu-show':''">
      <img class="app-menu-item" src="@/assets/cubeform-logo-vertical.svg" alt="logo">

      <MenuList class="app-menu-item" icon="mdi-account" title="Profile">
        <IconButtonLink to="/registerServer" icon="mdi-laptop" text="Register server" />
        <IconButtonLink to="/myServers" icon="mdi-server" text="My servers" />
      </MenuList>

      <MenuList class="app-menu-item" icon="mdi-controller-classic" title="Game">
        <IconButtonLink to="/news" icon="mdi-new-box" text="News" />
        <IconButtonLink to="/servers" icon="mdi-server-network" text="Server list" />
        <IconButtonLink to="/logout" icon="mdi-star" text="Logout" />
      </MenuList>

    </ElHeader>
    <router-view class="app-content"></router-view>
  </div>

</template>

<script>
import MenuList from "@/components/MenuList";
import IconButtonLink from "./components/Elements/Components/IconButtonLink";
export default {
  name: 'App',
  components: {IconButtonLink, MenuList},
  data: () => ({
    menuVisible: false
  }),
  created() {
  },
  methods: {
    toggleMenu(){
      this.menuVisible = !this.menuVisible;
    },
    hideMenu(){
      this.menuVisible = false;
    },
    goBack(){
      window.history.length > 1 ? this.$router.go(-1) : this.$router.push('/')
    },
    isLogged(){
      return this.$store.state.User.isLogged;
    }
  },
}
</script>

<style lang="less" scoped>
@import "theme.less";

@menu-min-width: 15.62em;

.app-container {
  display: grid;
  grid-template-columns: 1fr 4fr;
  min-height: 100vh;
}

.app-menu {
  padding-top: 2em;
  grid-column: 1 / 2;
  background-color: @theme-primary-dark;
  color: @theme-primary-foreground;
  //max-width: @menu-min-width;

  .app-menu-item {
    width: 80%;
  }
}

.app-content {
  grid-column: 2 / 3;
}
.app-menu-toggle-button {
  background-color: @theme-primary-dark;
  color: @theme-primary-foreground;
  position: fixed;
  top: 0;
  left: 0;
  display: none;
  font-size: 2em;
  padding: 0.1em;
  border-radius: 0 0 0.5em 0;
}
@media screen and (max-width: 950px) {
  .app-container {
    grid-template-columns: 1fr;
  }
  .app-content {
    grid-column: 1 / 2;
  }
  .app-menu-toggle-button {
    display: block;
    transition: transform 0.5s;

    &.app-menu-show {
      transform: translateX(@menu-min-width * 0.5);
    }
  }
  .app-menu {
    transition: transform 0.5s;
    grid-column: unset;
    position: fixed;
    left: 0;
    top: 0;
    width: @menu-min-width;
    bottom: 0;
    transform: translateX(-100%);

    &.app-menu-show {
      transform: translateX(0);
    }
  }
}

</style>

<style lang="less">
@import "theme";

.app-container {
  font-family: @font-family-primary;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  background-color: @theme-primary-light;
  color: @theme-primary-foreground;
}

.menu-list {
  margin-top: 2em;
}
</style>

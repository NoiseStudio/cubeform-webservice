<template>
  <md-app id="app">
    <md-app-toolbar class="md-primary">
      <md-button class="md-icon-button" @click="toggleMenu">
        <span class="mdi mdi-menu" />
      </md-button>
      <span class="md-title">CubeForm</span>
    </md-app-toolbar>

    <md-app-drawer
        :md-active.sync="menuVisible"
        md-swipeable
        id="main-drawer" >
      <md-toolbar class="md-transparent" md-elevation="0">
        <span>Navigation</span>

        <div class="md-toolbar-section-end">
          <md-button class="md-icon-button md-dense" @click="toggleMenu">
            <span class="mdi mdi-backburger" />
          </md-button>
        </div>
      </md-toolbar>



      <md-list class="drawer-list" @click="hideMenu">
        <LinkListItem to="/" icon="mdi-inbox" text="Yeee" />
        <LinkListItem to="/test" icon="mdi-test-tube" text="Test" />
<!--        <router-link to="/test" @click="hideMenu">-->
<!--          <md-list-item>-->
<!--            <span class="mdi mdi-inbox" />-->
<!--            <span class="md-list-item-text">Inbox</span>-->
<!--          </md-list-item>-->
<!--        </router-link>-->

      </md-list>
    </md-app-drawer>

    <md-app-content id="app-main">
      <router-view></router-view>
    </md-app-content>
  </md-app>

</template>

<script>
import LinkListItem from "@/components/LinkListItem";

export default {
  name: 'App',
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
    }
  },
  components: {
    LinkListItem
  }
}
</script>

<style lang="less">
#app {
  font-family: 'Ubuntu', 'Roboto', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  height: 100vh;
  color: white;

  a .md-disabled {
    color: white;
  }
}

#main-drawer {
  max-width: 20em;
}
#main-drawer .drawer-list .mdi {
  margin-right: 0.5em;
}



</style>

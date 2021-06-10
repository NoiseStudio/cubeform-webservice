<template>
  <div class="news-container">
    <div class="newsHeader">
      <span class="title">{{title}}</span>
      <div class="rightSide">
        <span class="date">{{date}}</span>
        <span v-if="visibility === true" class="mdi-arrow-up-bold-box-outline mdi" @click="changeContentVisibility"></span>
        <span v-else class="mdi-arrow-down-bold-box-outline mdi" @click="changeContentVisibility"></span>
      </div>
    </div>
    <div v-if="visibility === true" class="newsData">
      {{content}}
    </div>
    <div v-else class="newsData">{{content.substring(0,65)}}<span v-if="content.length>65">...</span></div>
  </div>
</template>

<script>
export default {
  name: "News",
  props: {
    title: String,
    date: String,
    content: String
  },
  data(){
    return {
      visibility: false,
      firstLineOfContent: ""
    };
  },
  methods: {
    changeContentVisibility() {
      this.visibility = !this.visibility;
      console.log(this.visibility)
    }
  }
}
</script>

<style scoped lang="less">
@import "~@/theme.less";

.news-container{
  width: 70%;
  display: flex;
  flex-direction: column;
  height: fit-content;
  margin-top: 1em;

  background: lighten(@theme-primary-light, 7%);
  border-radius: 10px;

  .newsHeader{
    background: darken(@theme-secondary-dark,10%);
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    border-radius: 10px 10px 5px 5px;
    border: 3px darken(@theme-secondary-dark,30%) solid;

    .title {
      font-size: 2em;
      padding: 5px 10px
    }
    .date {
      font-size: 1em;
      padding-right: 10px;
    }
  }

  .newsData {
    text-align: left;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 15px;
    white-space: normal;

  }
}

</style>
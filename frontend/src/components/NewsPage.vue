<template>
 <div class="newsContainer" ref="newsContainer">

 </div>
</template>

<script>
import Vue from "vue";
import News from "@/components/Elements/Components/News";
import {api} from "@/api";

export default {
  name: "NewsPage",
  components: {} ,
  mounted() {
    this.loadNews();
  },
  methods: {
    loadNews(){
      api.getNews()
        .then(response => this.processResponse(response))
        .catch(()=>this.showError());
    },
    processResponse(response){
      if(response.status === 200)
        this.loadNewsFromJson(response.jsonBody);
      else
        this.showError()
    },
    loadNewsFromJson(json){
      for(let newsEntity of json.newsEntities) {
        this.addNews(newsEntity.title, newsEntity.date, newsEntity.content);
      }
    },
    addNews(title, timestamp, content) {
      let newElem = new (Vue.extend(News));
      newElem.$props.title = title;
      newElem.$props.date = new Date(timestamp).toUTCString().split("T")[0];
      newElem.$props.content = content;
      newElem.$mount();
      this.$refs.newsContainer.appendChild(newElem.$el);
    },
    showError(){
      this.$refs.newsContainer.innerText = "Cannot load news ;(";
    }
  }
}
</script>

<style scoped lang="less">
.newsContainer{
  display: flex;
  flex-direction: column;
  align-items: center;
  //justify-content: center;

}


</style>

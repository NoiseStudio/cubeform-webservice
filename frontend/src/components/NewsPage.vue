<template>
 <div class="newsContainer">
    <news
    v-bind:title='new String("pierwszy")'
    v-bind:date='new String("10.06-2021")'
    v-bind:content='new String("jakis tam news i jego tresc")'></news>
   <news
    v-bind:title='new String("drugi")'
    v-bind:date='new String("23.11-2020")'
    v-bind:content='new String("jakisdsaas dasdddddd dddddd ddddddd ddddd dddddddd ddddd dddddddddd ddddddd dddd ddddddddddd dddddddd vddddd ddddddsdasdasd tam news i jego tresc")'></news>
 </div>
</template>

<script>
import News from "@/components/Elements/Components/News";

export default {
  name: "NewsPage",
  components: {News}
}
</script>
jakisdsaas dasdddddd dddddd ddddddd ddddd dddddddd ddddd dddddddddd
<style scoped lang="less">
.newsContainer{
  display: flex;
  flex-direction: column;
  align-items: center;
  //justify-content: center;

}


</style>
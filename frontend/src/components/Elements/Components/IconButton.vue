<template>
  <div class="icon-button">
    <span class="icon-button_icon mdi" :class="icon" />
    <span class="icon-button_text" v-text="text" />
  </div>
</template>

<script>
export default {
  name: "IconButton",
  props: {
    icon: String,
    text: String
  }
}
</script>

<style lang="less" scoped>
.icon-button {
  display: flex;
  flex-direction: row;
  text-align: left;

  .icon-button_icon {
    margin-right: 0.2em;
  }
}
</style>
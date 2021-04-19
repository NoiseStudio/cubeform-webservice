<template>
  <div class="el-header-button" v-text="text" />
</template>

<script>
export default {
  name: "ElHeaderButton",
  props: {
    text: String
  }
}
</script>

<style scoped>
.el-header-button {
  text-align: center;
  line-height: 2em;
  padding: 0 0.5em 0 0.5em;
  background-color: rgba(0,0,0,0);
  cursor: pointer;
}
.el-header-button:hover {
  background-color: rgba(0,0,0, 0.25);
}
</style>
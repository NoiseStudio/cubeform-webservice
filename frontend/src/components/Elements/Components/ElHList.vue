<template>
  <div class="el-list" :class="this.reverseClass ? 'el-list_'+this.reverseClass : ''">
    <slot />
  </div>
</template>

<script>
export default {
  name: "ElHList",
  props: {
    reverse: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    reverseClass(){
      return this.reverse ? "reverse" : "";
    }
  }
}
</script>

<style scoped>
  .el-list {
    display: flex;
    flex-direction: row;
  }
  .el-list_reverse {
    flex-direction: row-reverse;
  }
</style>
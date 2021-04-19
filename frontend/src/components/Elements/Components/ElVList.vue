<template>
  <div class="el-list" :class="this.reverseClass ? 'el-list_'+this.reverseClass : ''">
    <slot />
  </div>
</template>

<script>
export default {
  name: "ElVList",
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
    flex-direction: column;
  }
  .el-list_reverse {
    flex-direction: column-reverse;
  }
</style>
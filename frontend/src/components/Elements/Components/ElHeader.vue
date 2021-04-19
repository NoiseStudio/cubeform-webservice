<template>
  <div class="el-header-bar">
    <ElVList>
      <slot />
    </ElVList>
  </div>
</template>

<script>
export default {
  name: "ElHeader"
}
</script>

<style lang="less">
  .el-header-bar {

  }
  .el-list {
    align-items: center;
  }
</style>

<style>

</style>
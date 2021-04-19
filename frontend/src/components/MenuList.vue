<template>
  <ElVList class="menu-list">
    <IconButton class="menu-list_first" :icon="icon" :text="title" />
    <slot />
  </ElVList>

</template>

<script>
export default {
  name: "MenuList",
  props: {
    title: {
      type: String,
      required: true
    },
    icon: String
  }
}
</script>

<style lang="less" scoped>
@import "~@/theme.less";

.menu-list {
  background-color: fade(@theme-secondary-dark, 50%);
  color: @theme-secondary-foreground;
  border-radius: 0.5em;
  text-align: center;
  font-size: 0.9em;

  .menu-list_first {
    background-color: @theme-secondary-dark;
    border-radius: 0.5em;
    width: 100%;
    line-height: 1.5em;
    margin-bottom: 0.5em;
  }
}
</style>
<style lang="less">
.menu-list {
  white-space: nowrap;

  .menu-list_first {
    display: flex;

    .icon-button_text {
      flex: 1;
      text-align: center;
    }
  }

  & > * {
    width: 100%;
    line-height: 2.25em;
    padding-left: 0.2em;
    box-sizing: border-box;
  }
}
</style>
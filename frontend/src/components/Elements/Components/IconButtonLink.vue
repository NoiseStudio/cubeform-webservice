<template>
  <router-link :to="to" class="icon-button-link_router-link">
    <IconButton :icon="icon" :text="text" />
  </router-link>
</template>

<script>

export default {
  name: "IconButtonLink",
  props: {
    icon: String,
    text: String,
    to: String
  }
}
</script>

<style scoped>
.icon-button-link_router-link {
  text-decoration: none;
  color: inherit;
}
</style>
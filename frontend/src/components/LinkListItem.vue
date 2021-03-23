<template>
  <router-link v-bind:to="to">
    <md-list-item>
      <span class="mdi" v-bind:class="icon" />
      <span class="md-list-item-text">{{ text }}</span>
    </md-list-item>
  </router-link>
</template>

<script>
export default {
  name: "LinkListItem",
  props: ['icon', 'text', 'to']
}
</script>

<style scoped>

</style>
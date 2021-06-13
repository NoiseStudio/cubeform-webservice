<template>
  <div></div>
</template>

<script>
export default {
  name: "Logout",
  mounted() {
    this.$store.dispatch("User/logout")
    .then(()=>{
      this.$router.push("/login");
      console.log("Logout");
    })
    .catch(()=>{
      this.$router.push("/login");
      console.warn("Logout error");
    });
  }
}
</script>

<style scoped>

</style>
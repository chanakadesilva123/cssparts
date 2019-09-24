<template>
 <v-app-bar app color="black" style="color:white">
      <v-toolbar-title class="headline text-uppercase">
        <v-img src="@/assets/logo.png" max-width="300" max-height="50"></v-img>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon @click="doReload">
        <v-icon color="white">mdi-reload</v-icon>
      </v-btn>

      <v-btn icon @click="doLogout">
        <v-icon color="white">mdi-logout</v-icon>
      </v-btn>
    </v-app-bar>
</template>

<script>
export default {
  methods: {
    doLogout() {
      this.$router.push({ name: "logout" });
      this.$router.go();
    },
    doReload() {
      this.$router.go();
    }
  }
};
</script>

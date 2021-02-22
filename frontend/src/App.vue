<template>
  <v-app>
    <v-app-bar app dark>
      <router-link
        to="/"
        tag="h4"
        style="font-size: 1.6rem; font-weight: 300; line-height: 1.2"
        >Literarno udruženje</router-link
      >
      <v-spacer></v-spacer>
      <v-btn icon to="/search"  v-if="user === null">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <v-btn icon to="/index"  v-if="user === null">
        <v-icon>mdi-alpha-i-circle-outline</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";

export default {
  data() {
    return {
      drawer: null,
    };
  },
  computed: {
    ...mapGetters(["user"]),
  },
  methods: {
    ...mapMutations(['setUser']),
    logout(){
      this.setUser(null);
      this.$router.push("/")
    }
  }
};
</script>

<style scoped>
div[data-app="true"] {
  width: 100%;
  height: 100%;
  background: url("https://www.muralunique.com/wp-content/uploads/2009/06/products-1586R_Bookshelf.jpg")
    no-repeat center center fixed !important;
  background-size: cover;
}

a:hover {
  text-decoration: none;
}
h4:hover {
  cursor: pointer;
}

#logout:hover {
  cursor: pointer;
}
</style>

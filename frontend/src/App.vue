<template>
  <v-app>
    <v-app-bar app dark>
      <router-link
        to="/books"
        tag="h4"
        style="font-size: 1.6rem; font-weight: 300; line-height: 1.2"
        >Literarno udruženje</router-link
      >
      <v-spacer></v-spacer>
      <!-- <v-btn icon to="/cart">
        <v-icon>mdi-cart</v-icon>
      </v-btn> -->
      <v-btn icon to="/login" v-if="user === null">
        <v-icon>mdi-account</v-icon>
      </v-btn>
      <v-btn icon to="/register/reader"  v-if="user === null">
        <v-icon>mdi-account-plus</v-icon>
      </v-btn>

      <v-app-bar-nav-icon
        @click.stop="drawer = !drawer"
        v-if="user !== null"
      ></v-app-bar-nav-icon>

      <template>
        <v-navigation-drawer
          v-model="drawer"
          absolute
          temporary
          right
          style="height: 1000px"
          v-if="user !== null"
        >
          <v-list-item>
            <v-list-item-avatar>
              <v-icon>mdi-account</v-icon>
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title
                style="font-size: 1.2rem; font-weight: 300; line-height: 1.2"
                >{{user.username}}</v-list-item-title
              >
            </v-list-item-content>
          </v-list-item>

          <v-divider></v-divider>

          <v-list dense v-if="user.userType === 'WRITER'">
            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-book</v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>
                  <router-link
                    to="/booksByWriter"
                    style="
                      font-size: 1.2rem;
                      font-weight: 300;
                      line-height: 1.2;
                      color: white;
                    "
                    >Moje knjige</router-link
                  >
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>

          <v-list dense v-if="user.userType === 'WRITER'">
            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-plus</v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>
                  <router-link
                    to="/addBook"
                    style="
                      font-size: 1.2rem;
                      font-weight: 300;
                      line-height: 1.2;
                      color: white;
                    "
                    >Dodaj knjigu</router-link
                  >
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>

          <v-list dense v-if="user.userType === 'WRITER'">
            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-plus</v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>
                  <router-link
                    to="/addGenre"
                    style="
                      font-size: 1.2rem;
                      font-weight: 300;
                      line-height: 1.2;
                      color: white;
                    "
                    >Dodaj žanr</router-link
                  >
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>

          <v-list dense>
            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-movie</v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>
                  <router-link
                    to="/genres"
                    style="
                      font-size: 1.2rem;
                      font-weight: 300;
                      line-height: 1.2;
                      color: white;
                    "
                    >Žanrovi</router-link
                  >
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>

          <v-list dense>
            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-pen</v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>
                  <router-link
                    to="/writers"
                    style="
                      font-size: 1.2rem;
                      font-weight: 300;
                      line-height: 1.2;
                      color: white;
                    "
                    >Pisci</router-link
                  >
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>

          <v-list dense>
            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-glasses</v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>
                  <router-link
                    to="/readers"
                    style="
                      font-size: 1.2rem;
                      font-weight: 300;
                      line-height: 1.2;
                      color: white;
                    "
                    >Čitaoci</router-link
                  >
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>

          <v-divider></v-divider>

          <v-list-item>
            <v-list-item-avatar>
              <v-icon>mdi-logout</v-icon>
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title id="logout" @click="logout"
                style="font-size: 1.2rem; font-weight: 300; line-height: 1.2"
                >Izloguj se</v-list-item-title
              >
            </v-list-item-content>
          </v-list-item>
        </v-navigation-drawer>
      </template>
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

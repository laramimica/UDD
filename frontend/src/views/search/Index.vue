<template>
  <v-row>
    <v-col align="center" justify="center">
      <v-card dark style="width: 40%; margin-top: 2.5%">
        <v-card-title justify="center">
          <v-col>
            <h3>Indeksiranje</h3>
          </v-col>
        </v-card-title>
        <form>
          <v-text-field
            v-model="form.title"
            label="Naslov"
            name="title"
            style="width: 60%"
          ></v-text-field>

          <v-text-field
            v-model="form.username"
            label="Pisac"
            name="username"
            style="width: 60%"
          ></v-text-field>

          <v-text-field
            v-model="form.keywords"
            label="Ključne reči"
            name="keywords"
            style="width: 60%"
          ></v-text-field>

          <v-text-field
            v-model="form.genres"
            label="Žanr"
            name="genre"
            style="width: 60%"
          ></v-text-field>

          <v-file-input
            v-model="form.file"
            accept=".pdf"
            name="file"
            label="Izaberi dokument"
            style="width: 65.5%; margin-left: -5%"
          ></v-file-input>

          <br />

          <v-btn class="mr-4" @click="submit"> Potvrdi </v-btn>

          <br />
          <br />
        </form>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      form: {
        title: "",
        username: "",
        genres: "",
        file: "",
        keywords: ""
      }
    };
  },
  methods: {
    submit() {
      let fd = new FormData();
      fd.append("title", this.form.title);
      fd.append("username", this.form.username);
      fd.append("genres", this.form.genres);
      fd.append("file", this.form.file);
      fd.append("keywords", this.form.keywords);
      axios
        .post("/index/add", fd)
        .then((response) => {
          alert(response.data);
          this.$router.push("/");
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style>
</style>
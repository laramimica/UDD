<template>
<v-container style="width:50%">
  <v-row>
    <v-col>
      <v-row>
        <v-col>
          <v-simple-table  dark style="color:beige">
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">Naziv</th>
                  <th class="text-left"></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="genre in genres" :key="genre.name">
                  <td>{{ genre.name }}</td>
                  <td>
                    <button
                      type="button"
                      style="width:30%"
                      class="btn btn-outline-danger btn-block z-depth-2"
                      @click="del(genre.name)"
                      >Obriši</button>
                  </td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-col>
      </v-row>
    </v-col>
  </v-row>
</v-container>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      genres: []
    }
  },
  methods: {
    del(id) {
      axios
      .delete("api/genres/" + id)
      .then(() => {
        axios
      .get("api/genres")
      .then(genres => {
        this.genres = genres.data;
        
      })
      .catch(error => {
        console.log(error);
      });
      })
    }
  },
  mounted() {
    axios
      .get("api/genres")
      .then(genres => {
        this.genres = genres.data;
        console.log(genres)
      })
      .catch(error => {
        console.log(error);
      });
  }
};
</script>
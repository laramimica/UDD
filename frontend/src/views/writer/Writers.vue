<template>
<v-container>
  <v-row>
    <v-col>
      <v-row>
        <v-col>
          <v-simple-table  dark style="color:beige">
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">Korisničko ime</th>
                  <th class="text-left">Ime i prezime</th>
                  <th class="text-left">E-mail</th>
                  <th class="text-left">Grad</th>
                  <th class="text-left">Država</th>
                  <!-- <th class="text-left"></th> -->
                  <th class="text-left"></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="writer in writers" :key="writer.id">
                  <td>{{ writer.username }}</td>
                  <td>{{ writer.firstName }} {{writer.lastName}}</td>
                  <td>{{ writer.email }}</td>
                  <td>{{ writer.city }}</td>
                  <td>{{ writer.country }}</td>
                  <!-- <td>
                      <router-link to="/updateBook"
                      type="button"
                      style="width:40%"
                      class="btn btn-outline-primary btn-block z-depth-2"
                      @click="update(writer.id)">Ažuriraj</router-link>
                  </td> -->
                  <td>
                    <button
                      type="button"
                      style="width:60%"
                      class="btn btn-outline-danger btn-block z-depth-2"
                      @click="del(writer.username)"
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
      writers: []
    }
  },
  methods: {
    del(id) {
      axios
      .delete("api/writers/" + id)
      .then(() => {
        axios
      .get("api/writers")
      .then(writers => {
        this.writers = writers.data;
        
      })
      .catch(error => {
        console.log(error);
      });
      })
    }
  },
  mounted() {
    axios
      .get("api/writers")
      .then(writers => {
        this.writers = writers.data;
        
      })
      .catch(error => {
        console.log(error);
      });
  }
};
</script>
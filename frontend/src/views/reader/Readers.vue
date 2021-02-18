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
                <tr v-for="reader in readers" :key="reader.id">
                  <td>{{ reader.username }}</td>
                  <td>{{ reader.firstName }} {{reader.lastName}}</td>
                  <td>{{ reader.email }}</td>
                  <td>{{ reader.city }}</td>
                  <td>{{ reader.country }}</td>
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
                      @click="del(reader.username)"
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
      readers: []
    }
  },
  methods: {
    del(id) {
      axios
      .delete("api/readers/" + id)
      .then(() => {
        axios
      .get("api/readers")
      .then(readers => {
        this.readers = readers.data;
        
      })
      .catch(error => {
        console.log(error);
      });
      })
    }
  },
  mounted() {
    axios
      .get("api/readers")
      .then(readers => {
        this.readers = readers.data;
        
      })
      .catch(error => {
        console.log(error);
      });
  }
};
</script>
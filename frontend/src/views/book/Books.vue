<template>
<v-container>
  <v-row>
    <v-col>
      <v-row>
        <v-col>
          <v-simple-table dark style="color:beige">
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">Naslov</th>
                  <th class="text-left">Pisac</th>
                  <th class="text-left">Žanr</th>
                  <th class="text-left">Ključne reči</th>
                  <th class="text-left">Cena (EUR)</th>
                  <th class="text-left"></th>
                  <th class="text-left"></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="book in books" :key="book.id">
                  <td>{{ book.title }}</td>
                  <td>{{ book.writer_id }}</td>
                  <td>{{ book.genre }}</td>
                  <td>{{ book.keyWords }}</td>
                  <td>{{ book.price }}</td>
                  <td v-if="user !== null && user.userType === 'WRITER'">
                      <router-link :to="'/updateBook/'+ book.id"
                      type="button"
                      style="width:65%;"
                      class="btn btn-outline-secondary btn-block z-depth-2"
                      >Ažuriraj</router-link>
                  </td>
                  <td v-if=" user !== null && user.userType === 'WRITER'">
                    <button
                      type="button"
                      style="width:65%"
                      class="btn btn-outline-danger btn-block z-depth-2"
                      @click="del(book.id)"
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
import { mapGetters, mapMutations } from "vuex";
import axios from "axios";
export default {
  data() {
    return {
      books: []
    }
  },
  computed: {
    ...mapGetters(['user']),
  },
  methods: {
    del(id) {
      axios
      .delete("api/books/" + id)
      .then(() => {
        axios
      .get("api/books")
      .then(books => {
        this.books = books.data;
        
      })
      .catch(error => {
        console.log(error);
      });
      })
    }
  },
  mounted() {
    axios
      .get("api/books")
      .then(books => {
        this.books = books.data;
        
      })
      .catch(error => {
        console.log(error);
      });
  }
};
</script>
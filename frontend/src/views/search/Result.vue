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
                  <th class="text-left">Sažetak</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="book in books" :key="book.id">
                  <td>{{ book.title }}</td>
                  <td>{{ book.writer }}</td>
                  <td>{{ book.genre }}</td>
                  <td>{{ book.keyWords }}</td>
                  <td>{{ book.highlight }}</td>
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
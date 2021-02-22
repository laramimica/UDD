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
                  <th class="text-left">Ključne reči</th>
                  <th class="text-left">Pisac</th>
                  <th class="text-left">Žanr</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="book in search" :key="book.title">
                  <td>{{ book.title }}</td>
                  <td>{{ book.keywords }}</td>
                  <td>{{ book.writer }}</td>
                  <td>{{ book.genres }}</td>
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
import { mapGetters, mapMutations } from "vuex";
export default {
  data() {
    return {
      books: []
    }
  },
  computed: {
    ...mapGetters(["search"])
  }
};
</script>
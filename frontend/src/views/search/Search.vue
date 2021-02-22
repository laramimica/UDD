<template>
<v-row>
    <v-col align="center" justify="center">
        <v-card dark style="width: 40%; margin-top: 2.5%; color: beige">
            <v-card-title justify="center">
                <v-col>
                    <h3>Pretraživanje</h3>
                </v-col>
            </v-card-title>
            <form>
                <v-text-field
                v-model="form.title"
                label="Naslov"
                style="width:60%"
                ></v-text-field>

                <v-text-field
                v-model="form.keywords"
                label="Ključne reči"
                style="width:60%"
                ></v-text-field>

                <v-text-field
                v-model="form.genres"
                label="Žanr"
                style="width:60%"
                ></v-text-field>

                <v-text-field
                v-model="form.writer"
                label="Ime i prezime pisca"
                style="width:60%"
                ></v-text-field>

                <v-text-field
                v-model="form.text"
                label="Sadržaj"
                style="width:60%"
                ></v-text-field>

                <v-select
                v-model="form.operation"
                :items="operations"
                item-text="name"
                item-value="value"
                label="Operacija"
                data-vv-name="select"
                persistent-hint
                :menu-props="{ maxHeight: '400' }"
                style="width:28%; margin-left: -32%;"
                ></v-select>

                <v-select
                v-model="form.searchType"
                :items="items"
                item-text="name"
                item-value="value"
                label="Tip pretrage"
                data-vv-name="select"
                persistent-hint
                :menu-props="{ maxHeight: '400' }"
                style="width:28%; margin-left: 32%; margin-top:-10.9%"
                ></v-select>

                <br>

                <v-btn
                class="mr-4"
                @click="submit()"
                >
                Pretraži
                </v-btn>
                
                <br>
                <br>
            </form>
        </v-card>
    </v-col>
</v-row>
</template>

<script>
import axios from "axios";
import { mapGetters, mapMutations } from "vuex";
export default {
  data() {
    return {
      operations: [
          'AND',
          'OR'
      ],
      items: [
          'phrase',
          'regular'
      ],
      form: {
          title: '',
          writer: '',
          genres: '',
          keywords: '',
          text: '',
          operation: '',
          searchType: ''
      }
    }
  },
  methods: {
    ...mapMutations(["setSearch"]),
    submit() {
        let fd = new FormData();
        fd.append("title", this.form.title);
        fd.append("writer", this.form.writer);
        fd.append("genres", this.form.genres);
        fd.append("keywords", this.form.keywords);
        fd.append("text", this.form.text);
        fd.append("operation", this.form.operation);
        fd.append("searchType", this.form.searchType);
        axios
        .post("/search/advanced", fd)
        .then((response) => {
          this.setSearch(response.data);
          this.$router.push("/result");
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }
}
</script>

<style>

</style>
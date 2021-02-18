<template>
  <v-row>
    <v-col align="center" justify="center">
      <v-card dark style="width: 40%; margin-top: 2.5%; color: beige">
        <v-card-title justify="center">
          <v-col>
            <h3>Ažuriranje knjige</h3>
            <v-btn
            small
            @click="changeClick()"
            >Ažuriraj</v-btn>
          </v-col>
        </v-card-title>
        <v-form>
            <validation-provider
            v-slot="{ errors }"
            name="Naslov"
            rules="required|max:20"
        >
            <v-text-field
            style="width:400px"
            v-model="form.title"
            :counter="20"
            :error-messages="errors"
            label="Naslov"
            required
            :disabled="!isEditing"
            ></v-text-field>
        </validation-provider>

        <validation-provider
            v-slot="{ errors }"
            name="Cena (EUR)"
            :rules="{
            required: true,
            regex: '^[1-9][0-9]*$'
            }"
        >
            <v-text-field
            style="width:400px"
            v-model="form.price"
            :counter="8"
            :error-messages="errors"
            label="Cena (EUR)"
            required
            :disabled="!isEditing"
            ></v-text-field>
        </validation-provider>

          <v-text-field
          style="width:400px"
          v-model="form.genre"
          label="Žanr"
          required
          disabled
          ></v-text-field>

          <v-text-field
          style="width:400px"
          v-model="form.writer_id"
          label="Pisac"
          required
          disabled
          ></v-text-field>

          <v-text-field
          style="width:400px"
          v-model="form.keyWords"
          label="Ključne reči"
          required
          disabled
          ></v-text-field>

        <v-btn
            class="btn btn-outline-primary z-depth-2"
            @click="update()"
            :disabled="!isEditing"
        >
            Sačuvaj
        </v-btn>

        <v-btn style="margin-left:1%;" class="btn btn-outline-danger z-depth-2">
            <router-link to="/" 
            style="color:white"
            @click="cancelClick">Odustani</router-link>
        </v-btn>

        <br>
        <br>

        </v-form>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
  import { required, max, regex, min } from 'vee-validate/dist/rules'
  import { extend, ValidationProvider, setInteractionMode } from 'vee-validate'
  import axios from "axios";

setInteractionMode('eager')

  extend('required', {
    ...required,
    message: 'Polje ({_field_}) ne sme ostati nepopunjeno.',
  })

  extend('max', {
    ...max,
    message: '{_field_} ne sme sadržati više od {length} karaktera.',
  })

  extend('regex', {
    ...regex,
    message: '{_field_} mora biti veća od nule i sme sadržati samo cifre.',
  })

  export default {
    components: {
      ValidationProvider
    },
    data() {
      return {
        form : {},
        isEditing: false
      };
    },

    methods: {
      changeClick() {
          this.isEditing = true;
      },
      cancelClick() {
          this.isEditing = false;
          axios
          .get("api/books/" + this.$route.params.id)
          .then(form => {
              this.form = form.data;
              console.log(this.$route.params.id)
          })
      },
      update() {
        axios
        .put("api/books/" + this.$route.params.id, this.form)
        .then(book => {
            this.form = book.data;
            this.isEditing = false;
            console.log(this.form.price)
        })
        .catch(error => {
            console.log(error);
        });
      }
    },
    mounted() {
      axios
        .get("api/books/" + this.$route.params.id)
        .then(book => {
          this.form = book.data;
          this.isEditing = false;
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
</script>

<style>

</style>
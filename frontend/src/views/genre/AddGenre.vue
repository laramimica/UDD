<template>
  <validation-observer
    ref="observer"
    v-slot="{ invalid }"
  >
  <v-row>
    <v-col align="center" justify="center">
      <v-card dark style="width: 40%; margin-top: 2.5%; color:beige">
        <v-card-title justify="center">
          <v-col>
            <h3>Novi žanr</h3>
          </v-col>
        </v-card-title>
        <form @submit.prevent="submit">
        <validation-provider
            v-slot="{ errors }"
            name="Naziv"
            rules="required|max:20"
        >
            <v-text-field
            style="width:400px"
            v-model="form.name"
            :counter="20"
            :error-messages="errors"
            label="Naziv"
            required
            ></v-text-field>
        </validation-provider>

        <v-btn
            class="btn btn-outline-primary z-depth-2"
            type="submit"
            :disabled="invalid"
        >
            Kreiraj
        </v-btn>
        <v-btn @click="clear" class="btn btn-outline-danger z-depth-2" style="margin-left:1%">
            Odustani
        </v-btn>

        <br>
        <br>

        </form>
      </v-card>
    </v-col>
  </v-row>
  </validation-observer>
</template>

<script>
  import { required, max } from 'vee-validate/dist/rules'
  import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'
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

  export default {
    components: {
      ValidationProvider,
      ValidationObserver,
    },
    data() {
      return {
        form : {
          name: ''
        }
      };
    },

    methods: {
      submit () {
        this.$refs.observer.validate()
        axios
        .post("api/genres", this.form)
        .then(() => {
            this.clear();
        })
      },
      clear () {
        this.form.name = ''
        this.$refs.observer.reset()
      },
    },
    // mounted() {

    // }
  }
</script>

<style>

</style>
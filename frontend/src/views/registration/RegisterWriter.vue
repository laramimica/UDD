<template>
<div>
  <b-container v-if="error">
      <b-alert show variant="danger" class="d-flex justify-content-center">{{errormessage}}</b-alert>
  </b-container>
  
  <validation-observer
    ref="observer"
    v-slot="{ invalid }"
  >
  <h1>Pisac</h1>
    <form @submit.prevent="submit">
      <validation-provider
        v-slot="{ errors }"
        name="Ime"
        rules="required|max:20"
      >
        <v-text-field
          v-model="form.firstName"
          :counter="20"
          :error-messages="errors"
          label="Ime"
          required
        ></v-text-field>
      </validation-provider>

      <validation-provider
        v-slot="{ errors }"
        name="Prezime"
        rules="required|max:20"
      >
        <v-text-field
          v-model="form.lastName"
          :counter="20"
          :error-messages="errors"
          label="Prezime"
          required
        ></v-text-field>
      </validation-provider>

      <validation-provider
        v-slot="{ errors }"
        name="Email"
        rules="required|email"
      >
        <v-text-field
          v-model="form.email"
          :error-messages="errors"
          label="E-mail"
          required
        ></v-text-field>
      </validation-provider>

      <validation-provider
        v-slot="{ errors }"
        name="Grad"
        rules="required|max:20"
      >
        <v-text-field
          v-model="form.city"
          :counter="20"
          :error-messages="errors"
          label="Grad"
          required
        ></v-text-field>
      </validation-provider>

      <validation-provider
        v-slot="{ errors }"
        name="Država"
        rules="required|max:20"
      >
        <v-text-field
          v-model="form.country"
          :counter="20"
          :error-messages="errors"
          label="Država"
          required
        ></v-text-field>
      </validation-provider>

      <validation-provider
        v-slot="{ errors }"
        name="Korisničko ime"
        rules="required|max:20"
      >
        <v-text-field
          v-model="form.username"
          :counter="20"
          :error-messages="errors"
          label="Korisničko ime"
          required
        ></v-text-field>
      </validation-provider>

      <validation-provider
        v-slot="{ errors }"
        name="Lozinka"
        rules="required|max:20"
      >

        <v-text-field
            v-model="form.password"
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :type="show1 ? 'text' : 'password'"
            :error-messages="errors"
            :rules="[rules.min]"
            name="password"
            label="Lozinka"
            required
            hint="Minimum 8 karaktera."
            counter
            @click:append="show1 = !show1"
          ></v-text-field>
      </validation-provider>

      <v-btn
        class="btn btn-outline-primary z-depth-2"
        type="submit"
        :disabled="invalid"
      >
        Potvrdi
      </v-btn>
      <v-btn @click="clear" class="btn btn-outline-danger z-depth-2" style="margin-left:1%">
        Odustani
      </v-btn>
    </form>
  </validation-observer>
</div>
</template>

<script>
  import { required, digits, email, max, regex, min } from 'vee-validate/dist/rules'
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

  extend('email', {
    ...email,
    message: 'Email mora biti validan.',
  })

  export default {
    components: {
      ValidationProvider,
      ValidationObserver,
    },
    data() {
      return {
        form : {
          firstName: '',
          lastName: '',
          email: '',
          city: '',
          country: '',
          password: '',
          username: '',
          isWriter: true
        },
        show1: false,
        rules: {
          min: v => v.length >= 8 || 'Minimum 8 karaktera.'
        },
        error: false,
        errormessage: ""
      };
    },

    methods: {
      submit () {
        this.$refs.observer.validate()
        axios
        .post("api/auth/register", this.form)
        .then(() => {
            this.clear();
        })
        .catch(error => {
        console.log(error);
        this.error = true;
        this.errormessage = "Korisničko ime i email adresa moraju biti jedinstveni.";
        });
      },
      clear () {
        this.form.firstName = '',
        this.form.lastName = '',
        this.form.email = '',
        this.form.city = '',
        this.form.country = '',
        this.form.password = '',
        this.form.username = '',
        this.isWriter = null;
        this.$refs.observer.reset();
        this.error = false;
      },
    }
  }
</script>

<style>

</style>
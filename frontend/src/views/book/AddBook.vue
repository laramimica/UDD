<template>
  <validation-observer
    ref="observer"
    v-slot="{ invalid }"
  >
  <v-row>
    <v-col align="center" justify="center">
      <v-card dark style="width: 40%; margin-top: 2.5%; color: beige">
        <v-card-title justify="center">
          <v-col>
            <h3>Nova knjiga</h3>
          </v-col>
        </v-card-title>
        <form @submit.prevent="submit">
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
            ></v-text-field>
        </validation-provider>

        <validation-provider
            v-slot="{ errors }"
            name="Žanr"
        >
            <v-select
            style="width:400px"
            v-model="form.genre"
            :items="items"
            item-text="name"
            item-value="name"
            :error-messages="errors"
            label="Žanrovi"
            data-vv-name="select"
            hint="Odaberite žanr"
            persistent-hint
            :menu-props="{ maxHeight: '400' }"
            ></v-select>
        </validation-provider>

        <validation-provider
            v-slot="{ errors }"
            name="Ključne reči"
            rules="required|max:20"
        >
            <v-text-field
            style="width:400px"
            v-model="form.keyWords"
            :counter="20"
            :error-messages="errors"
            label="Ključne reči"
            required
            ></v-text-field>
        </validation-provider>

        <!-- <validation-provider
            v-slot="{ errors }"
            name="Pisac"
        >
            <v-select
            style="width:400px"
            v-model="form.writer_id"
            :items="items2"
            item-text="username"
            item-value="username"
            :error-messages="errors"
            label="Pisci"
            data-vv-name="select"
            hint="Odaberite pisca"
            persistent-hint
            :menu-props="{ maxHeight: '400' }"
            ></v-select>
        </validation-provider> -->

        <br>

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
  import { required, max, regex} from 'vee-validate/dist/rules'
  import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'
  import axios from "axios";
  import { mapGetters, mapMutations } from "vuex";

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
      ValidationProvider,
      ValidationObserver,
    },
    data() {
      return {
        form : {
          title: '',
          price: '',
          genre: '',
          keyWords: ''
        },
        items: [],
        items2: []
      };
    },
    computed: {
    ...mapGetters(['user',]),
    },
    methods: {
      submit () {
        this.$refs.observer.validate()
        const body = {
          ...this.form, writer_id : this.user.username
        }
        axios
        .post("api/books", body)
        .then(() => {
            this.clear();
        })
      },
      clear () {
        this.form.title = '',
        this.form.price = '',
        this.form.genre = '',
        this.form.keyWords = '',
        this.form.writer_id = '',
        this.$refs.observer.reset()
      },
    },
    mounted() {
      axios
        .get("api/genres")
        .then(item => {
            this.items = item.data;
            console.log(this.items);
        })
      axios
        .get("api/auth/writers")
        .then(item => {
            this.items2 = item.data;
            console.log(this.items2);
        })
    }
  }
</script>

<style>

</style>
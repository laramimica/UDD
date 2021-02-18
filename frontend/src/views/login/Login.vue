<template>
  <validation-observer ref="observer" v-slot="{ invalid }">
    <div>
      <v-row>
        <v-col align="center" justify="center">
          <v-card dark style="width: 40%; margin-top: 2.5%; color: beige">
            <v-card-title justify="center">
              <v-col>
                <h3>Prijava</h3>
              </v-col>
            </v-card-title>

            <b-container v-if="error">
              <b-alert
                show
                variant="danger"
                class="d-flex justify-content-center"
                >{{ errormessage }}</b-alert
              >
            </b-container>

            <form @submit.prevent="submit">
              <validation-provider v-slot="{ errors }" name="Korisničko ime">
                <v-text-field
                  style="width: 400px"
                  v-model="form.username"
                  label="Korisničko ime"
                  :error-messages="errors"
                  required
                ></v-text-field>
              </validation-provider>

              <validation-provider v-slot="{ errors }" name="Lozinka">
                <v-text-field
                  v-model="form.password"
                  style="width: 400px"
                  :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                  :type="show1 ? 'text' : 'password'"
                  :error-messages="errors"
                  name="password"
                  label="Lozinka"
                  required
                  @click:append="show1 = !show1"
                ></v-text-field>
              </validation-provider>

              <br />

              <v-btn
                class="btn btn-outline-primary btn-block z-depth-2"
                style="width: 400px; color: primary"
                type="submit"
                :disabled="invalid"
              >
                Prijavi se
              </v-btn>

              <br />
              <br />
            </form>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </validation-observer>
</template>

<script>
import { required } from "vee-validate/dist/rules";
import {
  extend,
  ValidationObserver,
  ValidationProvider,
  setInteractionMode,
} from "vee-validate";
import axios from "axios";
import { mapGetters, mapMutations } from "vuex";

setInteractionMode("eager");

extend("required", {
  ...required,
  message: "Polje ({_field_}) ne sme ostati nepopunjeno.",
});

export default {
  components: {
    ValidationProvider,
    ValidationObserver,
  },
  data() {
    return {
      form: {
        password: "",
        username: "",
      },
      show1: false,
      rules: {
        min: (v) => v.length >= 8 || "Minimum 8 karaktera.",
      },
      error: false,
      errormessage: ""
    };
  },
  methods: {
    ...mapMutations(["setUser"]),
    submit() {
      this.$refs.observer.validate();
      axios
        .post("api/auth/login", this.form)
        .then((user) => {
          this.setUser(user.data);
          this.$router.push("/books");
        })
        .catch((error) => {
          console.log(error);
          this.error = true;
          this.errormessage = "Loše akreditive.";
        });
    },
  },
};
</script>

<style>
</style>
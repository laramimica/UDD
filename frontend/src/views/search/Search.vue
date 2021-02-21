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
                v-model="title"
                label="Naslov"
                style="width:60%"
                ></v-text-field>

                <v-text-field
                v-model="keyWords"
                label="Ključne reči"
                style="width:60%"
                ></v-text-field>

                <v-text-field
                v-model="genre"
                label="Žanr"
                style="width:60%"
                ></v-text-field>

                <v-text-field
                v-model="writer"
                label="Ime i prezime pisca"
                style="width:60%"
                ></v-text-field>

                <br>

                <v-btn
                class="mr-4"
                @click="submit()"
                >
                Pretraži
                </v-btn>
                <v-btn @click="clear">
                Odustani
                </v-btn>
                
                <br>
                <br>
            </form>
        </v-card>
    </v-col>
</v-row>
</template>

<script>
export default {
  methods: {
    submit() {
        this.$router.push("/result");
    }
  }
}
</script>

<style>

</style>
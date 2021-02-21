<template>
<v-row>
    <v-col align="center" justify="center">
        <v-card dark style="width: 40%; margin-top: 2.5%; color: beige">
            <v-card-title justify="center">
                <v-col>
                    <h3>Indeksiranje</h3>
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

                <v-file-input
                accept="image/*"
                label="Izaberi dokument"
                style="width: 65.5%;
                margin-left: -5%;"
                ></v-file-input>

                <br>

                <v-btn
                class="mr-4"
                @click="submit"
                >
                Potvrdi
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

}
</script>

<style>

</style>
<template>
  <v-app>
    <v-card>
      <v-navigation-drawer permanent expand-on-hover app>
        <v-list>
          <v-list-item class="px-2">
            <v-list-item-avatar>
              <v-img
                src="https://randomuser.me/api/portraits/women/85.jpg"
              ></v-img>
            </v-list-item-avatar>
          </v-list-item>

          <v-list-item link>
            <v-list-item-content>
              <v-list-item-title class="title">
                Sandra Adams
              </v-list-item-title>
              <v-list-item-subtitle>sandra_a88@gmail.com</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list>

        <v-divider></v-divider>

        <v-list nav dense>
          <v-list-item to="/">
            <v-list-item-icon>
              <v-icon>mdi-layers</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Übersicht</v-list-item-title>
          </v-list-item>
          
            <v-list-group
              :value="true"
              prepend-icon="mdi-card-account-details"
            >
              <template v-slot:activator>
                <v-list-item-title>Familien</v-list-item-title>
              </template>
              <v-list-item>
                <v-list-item-title>Alle</v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title>Neu</v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title>Angemeldet</v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title>Eingeteilt</v-list-item-title>
              </v-list-item>
            </v-list-group>
            <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-account-group</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Gruppen</v-list-item-title>
          </v-list-item>
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-account-heart</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Helferinen & Helfer</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
    </v-card>
    <v-main>
      <v-container>Hello </v-container>
    </v-main>
  </v-app>
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>

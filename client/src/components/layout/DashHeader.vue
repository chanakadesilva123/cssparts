<template>
 <v-app-bar app color="black" style="color:white">
      <v-toolbar-title class="headline text-uppercase">
        <v-img src="@/assets/logo.png" max-width="300" max-height="50"></v-img>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon @click="goToHome">
        <v-icon color="white">mdi-home</v-icon>
      </v-btn>
      
      <div class="text-center">
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          icon
          v-bind="attrs"
          v-on="on"
        >
         <v-icon color="white">mdi-file-chart-outline</v-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-item
          v-for="(item, index) in dropDownMenuItems"
          :key="index"
          @click="goToCharts(item.link)"
        >
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </div>
      <v-btn icon @click="doReload">
        <v-icon color="white">mdi-reload</v-icon>
      </v-btn>

      <v-btn icon @click="doLogout">
        <v-icon color="white">mdi-logout</v-icon>
      </v-btn>
    </v-app-bar>
</template>

<script>
/* eslint-disable no-console */
export default {
data () {
	return {
    polling: null,
    dropDownMenuItems: [
        { title: 'Today',link: 'chartToday' },
        { title: 'This Month',link: 'chartThisMonth'  },
        { title: 'Last Month',link: 'chartLastMonth'  },
        { title: 'Last 3 Months',link: 'chartLast3Months'  },
      ]
  }
},
  methods: {
    pollData () {
		this.polling = setInterval(() => {
      this.$store.dispatch("setLastUpdatedTime");
			this.$router.go();
		}, 3600000)
	},
    doLogout() {
      this.$router.push({ name: "logout" });
      this.$router.go();
    },
    doReload() {
      this.$router.go();
    },
    goToHome() {
      this.$router.push({ name: "home" });
      this.$router.go();
    },
    goToCharts(where) {
      this.$router.push({ name: where });
      this.$router.go();
    }

  },
  beforeDestroy () {
    console.log('this.polling',this.polling);
    clearInterval(this.polling)
  },
  created () {
	this.pollData()
  }
};
</script>

<template>
  <v-app id="app">
    <dash-header />
    <v-content>
      <router-view />
    </v-content>
 <dash-footer />
  </v-app>
</template>

<script>
import DashFooter from '@/components/layout/DashFooter';
import DashHeader from '@/components/layout/DashHeader';

export default {
  name: 'App',
  components: {
    DashFooter,
    DashHeader
  },
  data() {
    return {}
  }
};
</script>


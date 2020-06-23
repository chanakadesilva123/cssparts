<template>
  <v-footer color="black" style="color:white">
      
      <v-btn
      style="color:white"
        text
        href="#"
        target="http://oneSysConsulting.com.au"
      >
        <span class="mr-2">OneSysConsulting</span>
      </v-btn>
      <v-spacer></v-spacer>
      <span class="mr-2">Copyright &copy; {{ year }}</span>
      
      
    </v-footer>
</template>

<script>
export default {
  name: 'DashFooter',
  data: function() {
    return {
      year: new Date().getFullYear()
    };
  }
};
</script>

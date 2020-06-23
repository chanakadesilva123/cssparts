/* eslint-disable vue/no-side-effects-in-computed-properties */
<template>
<v-container>
      <div>
     <v-row>
      <v-col cols="12" md="6" lg="6">
     <span class="headline">DASHBOARD</span><span class="outline"> Quotes , Orders & Invoices -  Today ({{this.$store.state.thisDay}})</span>
      </v-col>
      <v-col cols="12" md="6" lg="6" class="text-right">
     <span class="overline text-right">LAST UPDATED : {{this.$store.state.lastUpdated}}</span>
     </v-col>
    </v-row>   
   <v-row>
      
  <v-card
    class="mx-auto"
    max-width="400"
  >
  <v-card-title>Quotes</v-card-title>
  <v-card-text>
      <v-chip class="error mr-5">Total : ${{salesQuotesDaily}}</v-chip>
      <v-chip class="success">Target : ${{targetQuotes}}</v-chip>
    </v-card-text>
     <GChart
    :settings="{ packages: ['corechart', 'gauge'] }"
    type="Gauge"
    :data="chartDataQuotes"
    :options="chartOptionsQuotes"
    class="align-self-center"
    style="margin:35px"
  />
      
  </v-card>
      <v-card
    class="mx-auto"
    max-width="400"
  >
  <v-card-title>Orders</v-card-title>
  <v-card-text>
      <v-chip class="warning mr-5">Total : ${{salesEnteredDaily}}</v-chip>
      <v-chip class="success">Target : ${{targetOrders}}</v-chip>
    </v-card-text>
    <GChart
    :settings="{ packages: ['corechart', 'gauge'] }"
    type="Gauge"
    :data="chartDataOrders"
    :options="chartOptionsOrders"
    class="align-self-center"
    style="margin:35px"
  />
      
  </v-card>
        
        <v-card
    class="mx-auto"
    max-width="400"
  >
  <v-card-title>Invoices</v-card-title>
  <v-card-text>
      <v-chip class="primary mr-5">Total : ${{salesInvoicedDaily}}</v-chip>
      <v-chip class="success">Target : ${{targetInvoices}}</v-chip>
    </v-card-text>
     <GChart
    :settings="{ packages: ['corechart', 'gauge'] }"
    type="Gauge"
    :data="chartDataInvoices"
    :options="chartOptionsInvoices"
    class="align-self-center"
    style="margin:35px"
  />
      
  </v-card>
       
      
    </v-row>
      </div>
<v-overlay :value="overlay">
      <v-progress-circular indeterminate size="64">Please wait... We are retrieving data....</v-progress-circular>
    </v-overlay>
</v-container>
</template>
<script>
import { GChart } from 'vue-google-charts'
/* eslint-disable no-console */
export default {
  name: 'GoogleCharts',
  components: {
    GChart
  },
  data() {
    return {
      overlay:false,
      
      salesOrdersQtyDaily:0,
      
      salesEnteredDaily:0,
      targetOrders:3000,

      salesQuotesDaily:0,
      targetQuotes:3000,

      salesInvoicedDaily:0,
      targetInvoices:3000,

      chartDataQuotes: [
          ['Label', 'Value'],
          ['Quotes', {v: 0, f: '0%'}]
      ],
      chartOptionsQuotes: {
          width: 500,
            height: 240,
            greenFrom: 0,
            greenTo: 100,
            redFrom:0,
            redTo: 0,
            minorTicks: 5

    },
      chartDataOrders: [
          ['Label', 'Value'],
          ['Orders', {v: 0, f: '0%'}]
      ],
      chartOptionsOrders: {
          width: 500,
            height: 240,
            greenFrom: 5,
            greenTo: 100,
            yellowFrom:0,
            yellowTo: 0,
            minorTicks: 5

    },
      chartDataInvoices: [
          ['Label', 'Value'],
          ['Invoices', {v: 0, f: '0%'}]
      ],
      chartOptionsInvoices: {
          width: 500,
            height: 240,
            yellowColor:'#227BD4',
            yellowFrom: 0,
            yellowTo: 0,
            greenFrom:0,
            greenTo: 100,
            minorTicks: 5

    }
  }
  },
  computed: {
    targetAchievedOrders() {
      return  Number((this.targetOrders?(((this.salesEnteredDaily/this.targetOrders) * 100).toFixed(2)):0));
    },
    targetAchievedQuotes() {
      return  Number((this.targetQuotes?(((this.salesQuotesDaily/this.targetQuotes) * 100).toFixed(2)):0));
    },
    targetAchievedInvoices() {
      return  Number((this.targetInvoices?(((this.salesInvoicedDaily/this.targetInvoices) * 100).toFixed(2)):0));
    }
  },
  mounted() {
    //this.initialise();
    this.$nextTick(() => {
     setTimeout(() => {  
       this.chartDataOrders = [
          ['Label', 'Value'],
          ['Orders', {v:this.targetAchievedOrders,f:this.targetAchievedOrders+'%'}]
      ];
      this.chartOptionsOrders= {
          width: 500,
            height: 240,
            greenFrom: this.targetAchievedOrders,
            greenTo: 100,
            yellowFrom:0,
            yellowTo: this.targetAchievedOrders,
            minorTicks: 5

    };
    this.chartDataQuotes= [
          ['Label', 'Value'],
          ['Quotes', {v:this.targetAchievedQuotes,f:this.targetAchievedQuotes+'%'}]
      ];
      this.chartOptionsQuotes= {
          width: 500,
            height: 240,
            greenFrom: this.targetAchievedQuotes,
            greenTo: 100,
            redFrom:0,
            redTo: this.targetAchievedQuotes,
            minorTicks: 5

    };
    this.chartDataInvoices= [
          ['Label', 'Value'],
          ['Invoices', {v:this.targetAchievedInvoices,f:this.targetAchievedInvoices+'%'}]
      ];
      this.chartOptionsInvoices= {
          width: 500,
            height: 240,
            greenFrom: this.targetAchievedInvoices,
            greenTo: 100,
            redColor:'#227BD4',
            redFrom:0,
            redTo: this.targetAchievedInvoices,
            minorTicks: 5

    };
     }, 20000);
    });
  },
  methods: {
    async initialise () {
      this.overlay=true;
      await this.$store
          .dispatch("getCurrencyValue", 'salesInvoicedDaily')
          .then(({ data }) => {
            console.log("salesInvoicedDaily data", data);
            this.salesInvoicedDaily = data.value
          })
          .catch(err => {
            console.log("salesInvoicedDaily Error", err);
          });
        await this.$store
          .dispatch("getCurrencyValue", 'salesEnteredDaily')
          .then(({ data }) => {
            console.log("salesEnteredDaily data", data);
            this.salesEnteredDaily = data.value
          })
          .catch(err => {
            console.log("salesEnteredDaily Error", err);
          });
          await this.$store
          .dispatch("getCurrencyValue", 'salesQuotesDaily')
          .then(({ data }) => {
            console.log("salesQuotesDaily data", data);
            this.salesQuotesDaily = data.value
          })
          .catch(err => {
            console.log("salesQuotesDaily Error", err);
          });
          await this.$store
          .dispatch("getQuntityValue", 'salesOrdersQtyDaily')
          .then(({ data }) => {
            console.log("salesOrdersQtyDaily data", data);
            this.salesOrdersQtyDaily = data.value
          })
          .catch(err => {
            console.log("salesOrdersQtyDaily Error", err);
          });
          this.overlay=false;
  }
  }
}
</script>
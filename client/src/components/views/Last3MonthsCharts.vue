/* eslint-disable vue/no-side-effects-in-computed-properties */
<template>
<v-container>
      <div>
     <v-row>
      <v-col cols="12" md="6" lg="6">
     <span class="headline">DASHBOARD</span><span class="outline"> Quotes , Orders & Invoices - Last 3 Months</span>
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
      <v-chip class="error mr-5">Total : ${{salesQuotesMTD}}</v-chip>
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
      <v-chip class="warning mr-5">Total : ${{salesEnteredMTD}}</v-chip>
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
      <v-chip class="primary mr-5">Total : ${{salesInvoicedMTD}}</v-chip>
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
      salesOrdersQtyMTD:0,
      
      salesEnteredMTD:0,
      targetOrders:0,

      salesQuotesMTD:0,
      targetQuotes:0,

      salesInvoicedMTD:0,
      targetInvoices:0,

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
            greenFrom: 0,
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
      let salesTargetMTDData =[{month:0,period:'',target:0,sales:0}];
      salesTargetMTDData = this.salesTarget.filter(st => st.month === 0);
      console.log("targetAchievedOrders salesTargetMTDData", salesTargetMTDData);
      if(salesTargetMTDData && salesTargetMTDData.length>0)
      {
        if(salesTargetMTDData[0]!=null && salesTargetMTDData[0].target>0)
        {
          // eslint-disable-next-line vue/no-side-effects-in-computed-properties
          this.targetOrders = salesTargetMTDData[0].target;
        }
      }
      return  Number((this.targetOrders?(((this.salesEnteredMTD/this.targetOrders) * 100).toFixed(2)):0));
    },
    targetAchievedQuotes() {
      let salesTargetMTDData =[{month:0,period:'',target:0,sales:0}];
      salesTargetMTDData = this.salesTarget.filter(st => st.month === 0);
      console.log("targetAchievedOrders salesTargetMTDData", salesTargetMTDData);
      if(salesTargetMTDData && salesTargetMTDData.length>0)
      {
        if(salesTargetMTDData[0]!=null && salesTargetMTDData[0].target>0)
        {
          // eslint-disable-next-line vue/no-side-effects-in-computed-properties
          this.targetQuotes = salesTargetMTDData[0].target;
        }
      }
      return  Number((this.targetQuotes?(((this.salesQuotesMTD/this.targetQuotes) * 100).toFixed(2)):0));
    },
    targetAchievedInvoices() {
      let salesTargetMTDData =[{month:0,period:'',target:0,sales:0}];
      salesTargetMTDData = this.salesTarget.filter(st => st.month === 0);
      console.log("targetAchievedOrders salesTargetMTDData", salesTargetMTDData);
      if(salesTargetMTDData && salesTargetMTDData.length>0)
      {
        if(salesTargetMTDData[0]!=null && salesTargetMTDData[0].target>0)
        {
          // eslint-disable-next-line vue/no-side-effects-in-computed-properties
          this.targetInvoices = salesTargetMTDData[0].target;
        }
      }
      return  Number((this.targetInvoices?(((this.salesInvoicedMTD/this.targetInvoices) * 100).toFixed(2)):0));
    }
  },
  mounted() {
    this.initialise();
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
     }, 2000);
    });
  },
  methods: {
    async initialise () {
      this.overlay=true;
      await this.$store
          .dispatch("getCurrencyValue", 'salesInvoicedMTD')
          .then(({ data }) => {
            console.log("salesInvoicedMTD data", data);
            this.salesInvoicedMTD = data.value
          })
          .catch(err => {
            console.log("getCurrencyValue Error", err);
          });
      await this.$store
          .dispatch("getCurrencyValue", 'salesEnteredMTD')
          .then(({ data }) => {
            console.log("salesEnteredMTD data", data);
            this.salesEnteredMTD = data.value
          })
          .catch(err => {
            console.log("salesEnteredMTD Error", err);
          });
          await this.$store
          .dispatch("getQuntityValue", 'salesOrdersQtyMTD')
          .then(({ data }) => {
            console.log("salesOrdersQtyMTD data", data);
            this.salesOrdersQtyMTD = data.value
          })
          .catch(err => {
            console.log("salesOrdersQtyMTD Error", err);
          });
          await this.$store
          .dispatch("getCurrencyValue", 'salesQuotesMTD')
          .then(({ data }) => {
            console.log("salesQuotesMTD data", data);
            this.salesQuotesMTD = data.value
          })
          .catch(err => {
            console.log("salesQuotesMTD Error", err);
          });
          await this.$store
          .dispatch("getSalesTarget")
          .then(({ data }) => {
            console.log("getSalesTarget data", data);
            this.salesTarget = data.sort((a, b) => b.month - a.month);
            console.log("getSalesTarget salesTarget", this.salesTarget);
          })
          .catch(err => {
            console.log("getSalesTarget Error", err);
          });
          this.overlay=false;
  }
  }
}
</script>
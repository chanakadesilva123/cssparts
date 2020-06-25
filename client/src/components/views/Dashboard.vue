/* eslint-disable vue/no-side-effects-in-computed-properties */
<template>
<v-container>
      <div>
    <v-row>
      <v-col cols="12" md="6" lg="6">
     <span class="headline">DASHBOARD</span><span class="outline"> Overview of Sales, Products & Targets</span>
      </v-col>
      <v-col cols="12" md="6" lg="6" class="text-right">
     <span class="overline text-right">LAST UPDATED : {{this.$store.state.lastUpdated}}</span>
     </v-col>
    </v-row>
     <v-row>
      <v-col cols="12" md="4" lg="2">
        <v-card color="info" dark class="mt-50" height="50">
            <v-list-item class="text-center">
              <v-list-item-content>
                <v-list-item-title class="caption">Sales Invoiced <br/>(Daily)</v-list-item-title>
              </v-list-item-content>
              <v-list-item-chip class="mb-3">
                <v-chip color="error" label text-color="white" >${{salesInvoicedDaily}}</v-chip>
              </v-list-item-chip>
            </v-list-item>
        </v-card>
      </v-col>
      <v-col cols="12" md="4" lg="2">
        <v-card color="error" dark class="mt-50" height="50">
            <v-list-item class="text-center">
              <v-list-item-content class="align-self-center">
                <v-list-item-title class="caption">Sales Entered <br/>(Daily)</v-list-item-title>
              </v-list-item-content>
              <v-list-item-chip class="mb-3">
                <v-chip color="info" label text-color="white" >${{salesEnteredDaily}}</v-chip>
              </v-list-item-chip>
            </v-list-item>
        </v-card>
      </v-col>
      <v-col cols="12" md="4" lg="2">
        <v-card color="teal" dark class="mt-50" height="50">
            <v-list-item class="text-center">
              <v-list-item-content class="align-self-center">
                <v-list-item-title class="caption">Sales Orders QTY <br/>(Daily)</v-list-item-title>
              </v-list-item-content>
              <v-list-item-chip class="mb-3">
                <v-chip color="orange" label text-color="white" >{{salesOrdersQtyDaily}}</v-chip>
              </v-list-item-chip>
            </v-list-item>
        </v-card>
      </v-col>
      <v-col cols="12" md="4" lg="2">
        <v-card color="teal" dark class="mt-50" height="50">
            <v-list-item class="text-center">
              <v-list-item-content class="align-self-center">
                <v-list-item-title class="caption">Sales Invoiced <br/>(MTD)</v-list-item-title>
              </v-list-item-content>
              <v-list-item-chip class="mb-3">
                <v-chip color="orange" label text-color="white">${{salesInvoicedMTD}}</v-chip>
              </v-list-item-chip>
            </v-list-item>
        </v-card>
      </v-col>
      <v-col cols="12" md="4" lg="2">
       <v-card color="success" dark class="mt-50" height="50">
            <v-list-item class="text-center">
              <v-list-item-content class="align-self-center">
                <v-list-item-title class="caption">Sales Entered <br/>(MTD)</v-list-item-title>
              </v-list-item-content>
              <v-list-item-chip class="mb-3">
                <v-chip color="primary" label text-color="white" >${{salesEnteredMTD}}</v-chip>
              </v-list-item-chip>
            </v-list-item>
        </v-card>
      </v-col>
      <v-col cols="12" md="4" lg="2">
        <v-card color="orange" dark class="mt-50" height="50">
            <v-list-item class="text-center">
              <v-list-item-content class="align-self-center">
                <v-list-item-title class="caption">Sales Orders QTY <br/>(MTD)</v-list-item-title>
              </v-list-item-content>
              <v-list-item-chip class="mb-3">
                <v-chip color="error" label text-color="white" >{{salesOrdersQtyMTD}}</v-chip>
              </v-list-item-chip>
            </v-list-item>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="6" lg="6">
       <v-card tile>
              <v-card-title>
                  <p class="subtitle-2">Sales/Target Overview</p>
              </v-card-title>
              <v-card-text>
                <canvas id="salesTargetBar"></canvas>
              </v-card-text>
       </v-card>
      </v-col>
       <v-col cols="12" md="6" lg="6">
         <v-card tile>
              <v-card-title>
                  <span class="subtitle-2">Sales/Target Overview(MTD)</span>
                  <v-spacer/>
                  <span class="overline">{{targetAchieved}}% Achieved - {{remainingDays}} Days Remaining.</span>
              </v-card-title>
              <v-card-text>
                <canvas id="salesTargetDoughnut"></canvas>
              </v-card-text>
         </v-card>
       </v-col>
    </v-row>
<v-row>
      <v-col cols="12">
       <v-card tile>
              <v-card-title>
                  <p class="subtitle-2">Sales/Target Overview</p>
              </v-card-title>
              <v-card-text>
                <v-simple-table dense>
    <template v-slot:default>
      <thead>
        <tr style="background-color:skyblue">
          <th class="text-left">Month/Year</th>
          <th class="text-left" v-for="period in salesTargetPeriod" :key="period">{{period}}</th>
        </tr>
      </thead>
      <tbody>
        <tr style="background-color:green;color:white">
          <td>Sales($)</td>
          <td v-for="sales in salesNumbers" :key="sales">{{sales}}</td>
        </tr>
        <tr style="background-color:orange;color:white">
          <td>Target($)</td>
          <td v-for="target in targetNumbers" :key="target">{{target}}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
              </v-card-text>
       </v-card>
      </v-col>
</v-row>
<!--<v-row>
      <v-col cols="12" md="6" lg="6">
       <v-card tile>
              <v-card-title>
                  <strong>Sales By Stock Group (MTD)</strong>
              </v-card-title>
              <v-card-text>
                <v-simple-table dense>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">Stock Group</th>
          <th class="text-left">Actual Sales ($)</th>
          <th class="text-left">Target ($)</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in salesActualTarget" :key="item.group">
          <td>{{ item.group }}</td>
          <td>{{ item.actual }}</td>
          <td>{{ item.target }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
              </v-card-text>
       </v-card>
      </v-col>
<v-col cols="12" md="6" lg="6">
       <v-card tile>
              <v-card-title>
                  <strong>Sales By Stock Group (MTD)</strong>
              </v-card-title>
              <v-card-text>
      <canvas id="salesStockGroupPie"></canvas>
              </v-card-text>
       </v-card>
</v-col>
</v-row>-->
      </div>
<v-overlay :value="overlay">
      <v-progress-circular indeterminate size="64">Please wait... We are retrieving data....</v-progress-circular>
    </v-overlay>
</v-container>
</template>
<script>
import Chart from 'chart.js';
/* eslint-disable no-console */
export default {
  name: 'Dashboard',
  data() {
    return {
      overlay:false,
      salesInvoicedMTD:0,
      salesInvoicedDaily:0,
      salesEnteredMTD:0,
      salesEnteredDaily:0,
      salesOrdersQtyMTD:0,
      salesOrdersQtyDaily:0,
      salesTarget:[{month:0,period:'',target:0,sales:0}],
      targetAchieved:0
    };
  },
  computed: {
    remainingDays() {
      var date = new Date();
      var time = new Date(date.getTime());
      time.setMonth(date.getMonth() + 1);
      time.setDate(0);
      var days =time.getDate() > date.getDate() ? time.getDate() - date.getDate() : 0;
      return days;
    }
    ,salesTargetPeriod(){
      return this.salesTarget.map(salesTarget => salesTarget.period);
    },
    salesNumbers() {
      return this.salesTarget.map(salesTarget => salesTarget.sales?salesTarget.sales.toFixed(2):0);
    },
    targetNumbers() {
      return this.salesTarget.map(salesTarget => salesTarget.target?salesTarget.target.toFixed(2):0);
    },
    salesTargetMTD() {
      let arraySalesTargetMTD = [];
      let salesTargetMTDData =[{month:0,period:'',target:0,sales:0}];
      salesTargetMTDData = this.salesTarget.filter(st => st.month === 0);
      console.log("getSalesTarget salesTargetMTDData", salesTargetMTDData);
      if(salesTargetMTDData && salesTargetMTDData.length>0)
      {
        if(salesTargetMTDData[0]!=null && salesTargetMTDData[0].sales>0 && salesTargetMTDData[0]!=null && salesTargetMTDData[0].target>0)
        {
        let pecentage=((salesTargetMTDData[0].sales/salesTargetMTDData[0].target) * 100).toFixed(2);
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.targetAchieved = pecentage;
        console.log("getSalesTarget pecentage="+pecentage);
        arraySalesTargetMTD.push(pecentage,(100 - pecentage).toFixed(2));
        }
      }
      console.log("getSalesTarget arraySalesTargetMTD", arraySalesTargetMTD);
      return arraySalesTargetMTD
    },
    isMobile() {
      return window.innerWidth <= 800 && window.innerHeight <= 600;
    },
    salesActualTarget(){
    return [
                    {group:'TRUCK, BUS & MACHINERY EXHAUST PARTS',actual:'23236.66',target:'30000.00'},
                    {group:'CAR PERFORMANCE EXHAUST PARTS',actual:'45343.43',target:'75000.00'},
                    {group:'FLOWTECH ADVANTAGE',actual:'7811.73',target:'10000.00'},
                    {group:'SILVERBACK CHROME',actual:'23236.66',target:'10000.00'}
                    ];
    }
  },
  mounted() {
    this.initialise();
     this.$nextTick(() => {
     setTimeout(() => {  
     var ctx = document.getElementById('salesTargetBar').getContext('2d');
      var config = {
        type: 'bar',
        data: {
          labels: this.salesTargetPeriod,
          datasets: [
            {
              label: 'Target',
              borderColor: 'red',
              pointBackgroundColor: 'orange',
              backgroundColor: 'orange',
              data: this.targetNumbers,
              fill: false,
              type: 'line'
            },
            {
              label: 'Sales',
              fill: true,
              borderColor: '#284184',
              pointBackgroundColor: '#284184',
              backgroundColor: 'green',
              data: this.salesNumbers
            }
          ]
        },
        options: {
          responsive: true,
          maintainAspectRatio: !this.isMobile,
          legend: {
            position: 'bottom',
            display: true
          },
          tooltips: {
            mode: 'label',
            xPadding: 10,
            yPadding: 10,
            bodySpacing: 10
          },

          scales: {
            yAxes: [
              {
                ticks: {
                  beginAtZero: true
                }
              }
            ]
          }
        }
      };

      new Chart(ctx, config); // eslint-disable-line no-new

      var doughnutChartCanvas = document
        .getElementById('salesTargetDoughnut')
        .getContext('2d');
      var doughnutConfig = {
        type: 'doughnut',
        data: {
          labels: ['Sales', 'Target'],
          datasets: [
            {
              data: this.salesTargetMTD,
              backgroundColor: ['#00a65a', '#EEEEEE'],
              hoverBackgroundColor: ['#00a65a', '#EEEEEE'],
              borderColor: ['#00a65a', '#EEEEEE']
            }
          ]
        },
        options: {
          responsive: true,
          maintainAspectRatio: !this.isMobile,
          legend: {
            position: 'bottom',
            display: true
          },
          percentageInnerCutout: 80,
          animationEasing: 'easeInQuint'
        }
      };

      new Chart(doughnutChartCanvas, doughnutConfig); // eslint-disable-line no-new

      var pieChartCanvas = document
        .getElementById('salesStockGroupPie')
        .getContext('2d');
      var pieConfig = {
        type: 'pie',
        data: {
          labels: [
            'TRUCK, BUS & MACHINERY EXHAUST PARTS',
            'CAR PERFORMANCE EXHAUST PARTS',
            'FLOWTECH ADVANTAGE',
            'SILVERBACK CHROME'
          ],
          datasets: [
            {
              data: [23236.66, 45343.43, 8868.33, 7811.73],
              backgroundColor: ['#00a65a', 'red', 'orange', 'blue'],
              hoverBackgroundColor: ['#00a65a', 'red', 'orange', 'blue'],
              borderColor: ['#00a65a', 'red', 'orange', 'blue']
            }
          ]
        },
        options: {
          responsive: true,
          maintainAspectRatio: !this.isMobile,
          legend: {
            position: 'bottom',
            display: true
          },
          percentageInnerCutout: 80,
          animationEasing: 'easeInQuint'
        }
      };

      new Chart(pieChartCanvas, pieConfig); // eslint-disable-line no-new

      Chart.pluginService.register({
        beforeDraw: function(chart) {
          if (chart.chart.canvas.id === 'salesTargetDoughnut') {
            var width = chart.chart.width;
            var height = chart.chart.height;
            var ctx = chart.chart.ctx;

            ctx.restore();
            var fontSize = (height / 114).toFixed(2);
            ctx.font = fontSize + 'em sans-serif';
            ctx.textBaseline = 'middle';
            // console.log(chart.data.datasets[0].data[0]);
            var text = chart.data.datasets[0].data[0] + '%';
            var textX = Math.round((width - ctx.measureText(text).width) / 2);
            var textY = height / 2;

            ctx.fillText(text, textX, textY);
            ctx.save();
          }
        }
      });
      }, 20000);
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
          .dispatch("getCurrencyValue", 'salesInvoicedDaily')
          .then(({ data }) => {
            console.log("salesInvoicedDaily data", data);
            this.salesInvoicedDaily = data.value
          })
          .catch(err => {
            console.log("salesInvoicedDaily Error", err);
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
          .dispatch("getCurrencyValue", 'salesEnteredDaily')
          .then(({ data }) => {
            console.log("salesEnteredDaily data", data);
            this.salesEnteredDaily = data.value
          })
          .catch(err => {
            console.log("salesEnteredDaily Error", err);
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
          .dispatch("getQuntityValue", 'salesOrdersQtyDaily')
          .then(({ data }) => {
            console.log("salesOrdersQtyDaily data", data);
            this.salesOrdersQtyDaily = data.value
          })
          .catch(err => {
            console.log("salesOrdersQtyDaily Error", err);
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
};
</script>
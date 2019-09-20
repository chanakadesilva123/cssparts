<template>
  <v-app>
    <dash-header></dash-header>

    <v-content>
      <template>
  <!-- Main content -->
  <section class="content">
    <!-- Main row -->
    <div class="row">
      <!-- /.col -->
      <div class="col-md-2 col-sm-6 col-xs-12">
        <div class="box box-solid box-primary">
          <div class="box-header with-border">
            <h5 class="box-title">Sales Invoiced (MTD)</h5>
            <div class="box-tools">
              <!-- Buttons, labels, and many other things can be placed here! -->
              <button type="button" class="btn bg-yellow">$92,050</button>
            </div>
            <!-- /.box-tools -->
          </div>
        </div>
      </div>
      <div class="col-md-2 col-sm-6 col-xs-12">
        <div class="box box-solid box-success">
          <div class="box-header with-border">
            <h5 class="box-title">Sales Invoiced (Daily)</h5>
            <div class="box-tools">
              <!-- Buttons, labels, and many other things can be placed here! -->
              <button type="button" class="btn bg-red">$2,050</button>
            </div>
            <!-- /.box-tools -->
          </div>
        </div>
      </div>
      <!-- /.col -->

      <div class="col-md-2 col-sm-6 col-xs-12">
        <div class="box box-solid box-info">
          <div class="box-header with-border">
            <h5 class="box-title">Sales Entered (MTD)</h5>
            <div class="box-tools">
              <!-- Buttons, labels, and many other things can be placed here! -->
              <button type="button" class="btn bg-primary">$42,050</button>
            </div>
            <!-- /.box-tools -->
          </div>
        </div>
      </div>
      <!-- /.col -->
      <div class="col-md-2 col-sm-6 col-xs-12">
        <div class="box box-solid box-danger">
          <div class="box-header with-border">
            <h5 class="box-title">Sales Entered (Daily)</h5>
            <div class="box-tools">
              <!-- Buttons, labels, and many other things can be placed here! -->
              <button type="button" class="btn bg-aqua">$12,050</button>
            </div>
            <!-- /.box-tools -->
          </div>
        </div>
      </div>
      <!-- /.col -->
      <div class="col-md-2 col-sm-6 col-xs-12">
        <div class="box box-solid box-warning">
          <div class="box-header with-border">
            <h5 class="box-title">Sales Orders QTY (MTD)</h5>
            <div class="box-tools">
              <!-- Buttons, labels, and many other things can be placed here! -->
              <button type="button" class="btn bg-red">581</button>
            </div>
            <!-- /.box-tools -->
          </div>
        </div>
      </div>
      <div class="col-md-2 col-sm-6 col-xs-12">
        <div class="box box-solid box-primary">
          <div class="box-header with-border">
            <span class="box-title">Sales Orders QTY (Daily)</span>
            <div class="box-tools">
              <!-- Buttons, labels, and many other things can be placed here! -->
              <button type="button" class="btn bg-yellow">58</button>
            </div>
            <!-- /.box-tools -->
          </div>
        </div>
      </div>
    </div>
    <!-- /.row -->
    <div class="row">
      <div class="col-xs-12">
        <div class="box">
          <div class="box-header with-border">
            <h3 class="box-title"></h3>
            <div class="box-body">
              <div class="col-sm-6 col-xs-12">
                <p class="text-center">
                  <strong>Sales/Target Overview</strong>
                </p>
                <canvas id="salesTargetBar"></canvas>
              </div>
              <hr class="visible-xs-block" />
              <div class="col-sm-6 col-xs-12">
                <p class="text-center">
                  <strong>Sales/Target Overview(MTD)</strong>
                </p>
                <canvas id="salesTargetDoughnut"></canvas>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-xs-12">
        <div class="box">
          <div class="box-header with-border">
            <h3 class="box-title"></h3>
            <div class="box-body">
              <div class="col-sm-6 col-xs-12">
                <p class="text-center">
                  <strong>Sales By Stock Group (MTD)</strong>
                </p>
                <table class="table table-sm table-responsive">
                  <thead class="bg-info">
                    <tr class="bg-info">
                      <th scope="col" class="bg-info">
                        <button type="button" class="btn btn-danger">Stock Group</button>
                      </th>
                      <th scope="col">
                        <button type="button" class="btn btn-danger">Actual Sales ($)</button>
                      </th>
                      <th scope="col">
                        <button type="button" class="btn btn-danger">Target ($)</button>
                      </th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr class="bg-success">
                      <th scope="row">TRUCK, BUS & MACHINERY EXHAUST PARTS</th>
                      <td>23236.66</td>
                      <td>30000.00</td>
                    </tr>
                    <tr class="bg-danger">
                      <th scope="row">CAR PERFORMANCE EXHAUST PARTS</th>
                      <td>45343.43</td>
                      <td>75000.00</td>
                    </tr>
                    <tr class="bg-warning">
                      <th scope="row">FLOWTECH ADVANTAGE</th>
                      <td>8868.33</td>
                      <td>10000.00</td>
                    </tr>
                    <tr class="bg-primary">
                      <th scope="row">SILVERBACK CHROME</th>
                      <td>7811.73</td>
                      <td>10000.00</td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <hr class="visible-xs-block" />
              <div class="col-sm-6 col-xs-12">
                <p class="text-center">
                  <strong>Sales By Stock Group(MTD)</strong>
                </p>
                <canvas id="salesStockGroupPie"></canvas>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <!-- Info boxes -->
      <div class="col-md-3 col-sm-6 col-xs-12">
        <info-box
          color-class="bg-aqua"
          :icon-classes="['ion', 'ion-android-clipboard']"
          text="Today Orders"
          number="15"
        ></info-box>
      </div>
      <!-- /.col -->
      <div class="col-md-3 col-sm-6 col-xs-12">
        <info-box
          color-class="bg-red"
          :icon-classes="['ion', 'ion-cash']"
          text="Today Sales"
          number="$1,410"
        ></info-box>
      </div>
      <!-- /.col -->

      <!-- fix for small devices only -->
      <div class="clearfix visible-sm-block"></div>

      <div class="col-md-3 col-sm-6 col-xs-12">
        <info-box
          color-class="bg-green"
          :icon-classes="['ion', 'ion-ios-cart-outline']"
          text="Sales (YTD)"
          number="$1,760,053"
        ></info-box>
      </div>
      <!-- /.col -->
      <div class="col-md-3 col-sm-6 col-xs-12">
        <info-box
          color-class="bg-yellow"
          :icon-classes="['ion', 'ion-ios-people-outline']"
          text="Customers (YTD)"
          number="2,234"
        ></info-box>
      </div>
      <!-- /.col -->
    </div>
    <!-- /.row -->
  </section>
  <!-- /.content -->
</template>
   </v-content>
    <dash-footer></dash-footer>
  </v-app>
</template>

<script>
import Chart from 'chart.js';
import InfoBox from '@/components/widgets/InfoBox';
import DashFooter from '@/components/layout/DashFooter';
import DashHeader from '@/components/layout/DashHeader';

export default {
  name: 'Dashboard',
  components: {
    InfoBox,
    DashFooter,
    DashHeader
  },
  data() {
    return {
      generateRandomNumbers(numbers, max, min) {
        var a = [];
        for (var i = 0; i < numbers; i++) {
          a.push(Math.floor(Math.random() * (max - min + 1)) + max);
        }
        return a;
      }
    };
  },
  computed: {
    salesNumbers() {
      // return this.generateRandomNumbers(12, 1000000, 10000);
      return [
        800110,
        850120,
        952330,
        965340,
        954150,
        932550,
        921470,
        932570,
        952490,
        945100,
        932110,
        953120
      ];
    },
    targetNumbers() {
      return [
        900090,
        900190,
        860090,
        950090,
        900590,
        923190,
        932590,
        912090,
        900090,
        932590,
        815290,
        930090
      ];
    },
    isMobile() {
      return window.innerWidth <= 800 && window.innerHeight <= 600;
    }
  },
  mounted() {
    this.$nextTick(() => {
      var ctx = document.getElementById('salesTargetBar').getContext('2d');
      var config = {
        type: 'bar',
        data: {
          labels: [
            'January',
            'February',
            'March',
            'April',
            'May',
            'June',
            'July',
            'August',
            'September',
            'October',
            'November',
            'December'
          ],
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
              data: [56.6, 43.4],
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
    });
  }
};
</script>
<style>
.info-box {
  cursor: pointer;
}
.info-box-content {
  text-align: center;
  vertical-align: middle;
  display: inherit;
}
.fullCanvas {
  width: 100%;
}
</style>

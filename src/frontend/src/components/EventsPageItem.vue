<template>
<main-header>{{event.title}}</main-header>
<div class="event" id="invoice">
  <div class="event__title">
    {{event.title}}
  </div>
  <div class="event__item">
    <span>Дата проведения:</span> {{event.date}}
  </div>
  <div class="event__item">
    <span>Кандидат:</span> {{event.name}}
  </div>
  <div class="event__item">
    <span>Всего голосующих:</span> {{event.total}}
  </div>
  <div class="event__item">
    <span>Проголосовало "ЗА":</span> {{voteFor}}%
  </div>
  <div class="event__item">
    <span>Проголосовало "ПРОТИВ":</span> {{voteAgainst}}%
  </div>
</div>
<div class="chart">
  <ag-charts-vue  id="invoice2" :options="options"></ag-charts-vue>
</div>
<div class="event__button">
 <down-load-button dom="#invoice" dom2="#invoice2" :nameFile="NameFile + '.pdf'"/>
</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import { AgChartsVue } from 'ag-charts-vue3';

export default {
  components: {
    AgChartsVue,
  },

 data:() => ({
  options: null,
  event: {

  },
 }),

 methods: {
  ...mapActions([
    'GET_EVENTS_FROM_API'
  ]),

  downloadPDF() {
    this.$refs.html2Pdf.generatePdf()
  },

  getEvent() {
    let vm = this;
    let id = this.$route.params['id']

    const wait = setInterval(() => {
      if (this.EVENTS.length > 0) {
        clearInterval(wait)
        this.EVENTS.forEach(element => {
          if (id == element.id.toString()) {
            vm.event = element;
            vm.createChart()
          }
        })
      }
    }, 100)
  },

  createChart() {
    // let getEvent = JSON.parse(JSON.stringify(this.event));
    this.options = {
      data: [
        { label: 'За', value: 12},
        { label: 'Против', value: 18},
      ],
      series: [
        {
          type: 'pie',
          angleKey: 'value',
          calloutLabelKey: 'label',
          sectorLabelKey: 'value',
          sectorLabel: {
            color: 'white',
            fontWeight: 'bold',
            fontSize: 17,
          },
          fills: ['#FF0000', '#0000FF'],
          highlightStyle: {
            item: {
              fillOpacity: 0,
              stroke: '#000',
              strokeWidth: 3,
            },
          },
        },
      ],
      legend: {
        enabled: false,
      },
    };
  },
 },

 computed: {
   ...mapGetters([
     'EVENTS',
   ]),

   NameFile() {
    return this.event.title
   },

   voteFor() {
    if (this.event.voteFor == 0) {
      return 'Голосов пока нет'
    } else {
      let num = this.event.voteFor * 100 / this.event.total
      return num.toFixed(2)
    }

   },

   voteAgainst() {
    if (this.event.voteAgainst == 0) {
      return 'Голосов пока нет'
    } else {
      let num = this.event.voteAgainst * 100 / this.event.total
      return num.toFixed(2)
    }

   }
 },

 created() {
    this.createChart(this.event)
  },

  mounted() {
    this.GET_EVENTS_FROM_API()
    this.getEvent()
    this.createChart()
  },

}
</script>

<style lang="scss" scoped>
.event {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  max-width: 1000px;
  gap: 10px;
  padding: 20px 0 0 20px;
}
.event__title {
  font-size: 18px;
  font-weight: 600;
}
.event__item {
  span {
    font-weight: 600;
  }
}

.event__button {
  padding: 0 0 0 20px;
}

</style>
<template>
<main-header>Редактирование ивента</main-header>
<div class="container">
 <div class="event">
  <div class="event__header">
   <div class="event__header_title">Бюллетень</div>
  </div>
  <div v-html="event.description" class="event__description"></div>

  <table class="event__table">
   <tr>
    <th>ФИО соискателя</th>
    <th>Достоин ученой степени</th>
    <th>Результаты голосования</th>
   </tr>
   <tr>
    <td>{{event.name}}</td>
    <td>кандидата геолого-минералогических наук</td>
    <td>
     <div class="buttons">
      <button class="btn">ЗА</button>
      <button class="btn">ПРОТИВ</button>
     </div>
    </td>
   </tr>
   <tr>
    <td colspan="3">
     • В графе "Результаты голосования" выбрать нужное.
    </td>
   </tr>
  </table>
</div>
<div class="event__header">
 <div class="event__header_title">Редактирование</div>
</div>
<form class="event__form">
 <div class="form__inputs">
  <label for="">
  Название ивента
  <input class="form__input" v-model="event.title" type="text" name="" id="">
  </label>
  <label for="">
   Дата проведения
   <input class="form__input" v-model="event.date" type="text" name="" id="">
  </label>
  <label for="">
   ФИО кандидата
   <input class="form__input" v-model="event.name" type="text" name="" id="">
  </label>
  <label for="">
   Количество голосующих
   <input class="form__input" v-model="event.total" type="number" name="" id="">
  </label>
 </div>
 <div class="form__textarea">
  <label for="">
   Описание ивента
   <textarea class="form__input" v-model="event.description" name="" id="" cols="30" rows="10"></textarea>
  </label>
 </div>
</form>

<div class="event__header">
 <div class="event__header_title">Данные ивента</div>
</div>

<div class="event__data">
 <div class="event__data_passwords">
  <div class="event__data_passwords-title">
   Коды доступа:
  </div>
 <div v-for="password in event.passwords" :key="password.index" class="password">{{password.code}}</div>
 </div>

 <div class="event__data_link">
  <div class="event__data_link-title">
   Ссылка на голосование
  </div>
  {{ event.link }}
 </div>
</div>

<div class="event__buttons">
 <button class="btn" @click="checkInputs(event)">Сохранить изменения</button>
 <button class="btn" @click="deleteEvent(event)">Удалить ивент</button>
 <button class="btn" @click="$router.push(`/admin/event/${event.id}`)">Посмотреть отчет</button>
</div>
</div>

</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import axios from 'axios'
export default {
 name: 'edit-event-page',

 data:() => ({
  event: {}
 }),

 methods: {
  ...mapActions([
     'PUT_EVENT_FROM_API',
     'DELETE_EVENT_BY_API',
     'GET_EVENTS_FROM_API'
   ]),

   checkInputs(event) {
    const formInputs = document.querySelectorAll('.form__input')
    for (let input of formInputs) {
      input.addEventListener('input', function() {
        input.classList.remove('empty')
      })
      if (input.value == '') {
        input.classList.add('empty')
        alert('Заполните, пожалуйста все поля')
      }
    }
    this.putEvent(event)
  },

  putEvent(event) {
    const newEvent = {
      title: event.title,
      name: event.name,
      date: event.date,
      description: event.description,
      total: event.total
    }

    axios.put(`https://voiting-server.cfapps.us10-001.hana.ondemand.com/api/voiting/update/${event.id}`, newEvent)
      .then(this.$router.push('/admin/events'))
      .then(this.GET_EVENTS_FROM_API())

      .catch((error) => {
        console.log(error)
        return error;
      })
  },

  deleteEvent(event) {
    axios.delete(`https://voiting-server.cfapps.us10-001.hana.ondemand.com/api/voiting/delete/${event.id}`)
      .then(this.$router.push('/admin/events'))
      .then(this.GET_EVENTS_FROM_API())

      .catch((error) => {
        console.log(error)
        return error;
      })
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

          }
        })
      }
    }, 100)
  },
 },

 computed: {
  ...mapGetters ([
    'EVENTS',
  ]),
 },

 mounted() {
  this.GET_EVENTS_FROM_API()
  this.getEvent()
 }
}
</script>

<style lang="scss" scoped>
.event {
 text-align: center;
 max-width: 800px;
 margin: 0 auto;
}
.event__header {
 font-size: 20px;
 font-weight: 900;
 text-align: center;
 width: fit-content;
 margin: 50px auto 20px auto;
 padding: 12px 20px;
 border-radius: 5px;
 background: rgb(230, 230, 230);
}

.event__description {
 margin-bottom: 30px;
}

.event__table {
 margin: 0 auto;
 border-collapse: collapse;
 border: 1px solid #000;

 th, td {
  padding: 13px;
  width: 300px;
  max-width: 300px;
  border: 1px solid #000;
 }

 td {
  font-size: 17px;
  font-weight: 500;
 }

 .buttons {
  display: flex;
  flex-direction: column;
  gap: 5px;
  width: fit-content;
  margin: 0 auto;
 }
}

.event__form {
 display: flex;
 gap: 15px;
 display: flex;
 gap: 20px;

 label {
  display: flex;
  flex-direction: column;
  gap: 10px;
  font-size: 18px;
 }

 input, textarea {
  border: 1px solid #000;
  padding: 12px 8px 12px 12px;
  font-size: 17px;
  max-width: 100%;

  &.empty {
    border: 1px solid red;
  }
 }

 @media(max-width: 830px) {
  justify-content: center;
  flex-direction: column;
 }
}

.form__inputs {
 display: flex;
 flex-direction: column;
 gap: 10px;
 width: 50%;

 @media(max-width: 830px) {
  width: 100%;
 }
}

.form__textarea {
 width: 50%;

 @media(max-width: 830px) {
  width: 100%;
 }
}
.event__buttons {
 display: flex;
 justify-content: center;
 gap: 10px;
 padding: 40px 0;
}
.event__data {
 display: flex;
 gap: 20px;
}

.event__data_passwords {
 padding: 20px;
 border-radius: 10px;
 background: rgb(228, 228, 228);
 display: flex;
 flex-direction: column;
 max-height: 400px;
 overflow: auto;

 div {
  padding: 5px 0;
 }
}

.event__data_passwords-title {
 font-size: 17px;
 font-weight: 700;
}

.event__data_link-title {
 font-size: 17px;
 font-weight: 700;
 padding-bottom: 10px;
}
</style>
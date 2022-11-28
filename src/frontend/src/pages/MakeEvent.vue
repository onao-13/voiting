<template>
<main-header>Создание ивента</main-header>

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

<div class="event__buttons">
 <button class="btn" @click="checkInputs(event)">Создать ивент</button>
</div>
</div>
</template>

<script>
import { mapActions } from 'vuex';
import axios from 'axios'
export default {
 name: 'make-event',

 data:() => ({
  event: {
   title: '',
   name: '',
   description: '',
   date: '',
   total: null
  }
 }),

 methods: {
  ...mapActions([
    'CREATE_EVENT_BY_API',
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
      }
    }

    for (var key in event) {
      if (event[key] == "") {
        alert('Заполните, пожалуйста все поля')
        return false
      }
    }

    this.createEvent(event)
  },

  createEvent(event) {
    return axios
      .post("https://voiting-server.cfapps.us10-001.hana.ondemand.com/api/voiting/create", event)
      .then(this.GET_EVENTS_FROM_API())
      .then(this.$router.push('/admin/events'))
      .catch((error) => {
        console.log(error)
        return error;
      })
  }
 },

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
  outline: none;

  &.empty {
    border: 1px solid red;
  }
 }
}

.form__inputs {
 display: flex;
 flex-direction: column;
 gap: 10px;
 width: 50%;
}

.form__textarea {
 width: 50%;
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
<template>
<header class="header">
 <div class="container">
  <div class="header__wrapper">
   <div class="header__title">
    <slot>

    </slot>
   </div>
   <div class="nav">
    <router-link router-link-active class="nav__link" :to="'/'">Главная</router-link>
    <router-link class="nav__link" :to="'/events'">Список ивентов</router-link>
   </div>
  </div>
 </div>
</header>
</template>

<script>
export default {
 name: 'main-header'
}
</script>

<style lang="scss" scoped>

.header {
 width: 100%;
 height: 80px;
 background: #55555A;
 display: flex;
 align-items: center;
}

.header__wrapper {
 display: flex;
 justify-content: space-between;
 align-items: center;
}

.header__title {
 font-size: 24px;
 font-weight: 700;
 color: #fff;
}
.nav {
 display: flex;
 align-items: center;
 gap: 15px;
}
.nav__link {
 position: relative;
 font-size: 18px;
 text-decoration: none;
 font-weight: 700;
 color: #fff;

 &.router-link-active {
  &::after {
   transform: scaleX(1);
  }
 }

 &::after {
  position: absolute;
  content: '';
  background: #fff;
  left: 0;
  bottom: -3px;
  width: 100%;
  height: 1.5px;
  transform: scaleX(0);
  transition: all .2s ease-in-out;
 }

 &:hover {
  &::after {
   transform: scaleX(1);
  }
 }
}
</style>
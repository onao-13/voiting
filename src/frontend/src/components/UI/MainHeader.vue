<template>
<header class="header">
 <div class="container">
  <div class="header__wrapper">
   <div class="header__title">
    <slot>
    </slot>
   </div>

   <div class="nav" :class="{ 'active' : isNavOpen }">
    <router-link router-link-active class="nav__link" :to="'/admin/'">Главная</router-link>
    <router-link class="nav__link" :to="'/admin/events'">Список ивентов</router-link>
   </div>

   <button class="menu" type="button" id="navToggle">
    <div id="burger"
     :class="{ 'active' : isNavOpen }"
     @click.prevent="toggle">
     <button type="button" class="burger-button" title="Menu">
      <span class="burger-bar burger-bar--1"></span>
      <span class="burger-bar burger-bar--2"></span>
      <span class="burger-bar burger-bar--3"></span>
     </button>
    </div>
   </button>
  </div>
 </div>
</header>
</template>

<script>
export default {
 name: 'main-header',

 data:() => ({
  isNavOpen: false
 }),

 methods: {
  toggle() {
   this.isNavOpen = !this.isNavOpen
  }
 },
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

 @media (max-width: 670px) {
  min-width: 100%;
  min-height: 20%;
  max-height: 50%;
  overflow-y: auto;
  padding: 20px;
  position: fixed;
  right: 0;
  top: -100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  gap: 20px;
  background-color: #55555A;
  transition: all .3s ease;
  z-index: 1;

  &.active {
   top: 0;
  }
 }
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

.burger-button {
 position: relative;
 height: 33px;
 width: 36px;
 display: block;
 z-index: 60;
 border: 0;
 border-radius: 0;
 background-color: transparent;
 pointer-events: all;
 background: none;
 transition: transform .6s cubic-bezier(.165,.84,.44,1);
}

.burger-bar {
 background-color: #fff;
 position: absolute;
 top: 50%;
 right: 6px;
 left: 6px;
 height: 2px;
 width: auto;
 margin-top: -1px;
 transition: transform .6s cubic-bezier(.165,.84,.44,1),opacity .3s cubic-bezier(.165,.84,.44,1),background-color .6s cubic-bezier(.165,.84,.44,1);
}

.burger-bar--1 {
 -webkit-transform: translateY(-7px);
 transform: translateY(-7px);
}

.burger-bar--2 {
 transform-origin: 100% 50%;
 transform: scaleX(.8);
}

.burger-button:hover .burger-bar--2 {
 transform: scaleX(1);
}

.no-touchevents .burger-bar--2:hover {
 transform: scaleX(1);
}

.burger-bar--3 {
 transform: translateY(7px);
}

#burger.active .burger-button {
 transform: rotate(-180deg);
}

#burger.active .burger-bar--1 {
 transform: rotate(45deg)
}

#burger.active .burger-bar--2 {
 opacity: 0;
}

#burger.active .burger-bar--3 {
 transform: rotate(-45deg)
}

.menu {
 height: 22px;
 width: 30px;
 position: relative;
 text-decoration: none;
 background: none;
 display: none;
 cursor: pointer;
 outline: none;
 border: none;
 padding: 0;
 margin: 0;

 @media (max-width: 670px) {
  display: block;
 }
}

.barre, .barre:before, .barre:after {
 height: 3px;
 width: 27px;
 border-radius: 5px;
 background: #fff;
 position: absolute;
 top: 9px;
 left: 0;
 -webkit-transform-origin: 50%;
 -moz-transform-origin: 50%;
 -ms-transform-origin: 50%;
 -o-transform-origin: 50%;
 transform-origin: 50%;
 transition: .1s linear all;
}

.barre:before {
 content: "";
 top: -9px;
}

.barre:after {
 content: "";
 top: 9px;
}

.menu.active .barre {
 background: transparent;
}

.menu.active .barre:before {
 top: 0;
 -webkit-transform: rotate(45deg);
 -moz-transform: rotate(45deg);
 -ms-transform: rotate(45deg);
 -o-transform: rotate(45deg);
 transform: rotate(45deg);
}

.menu.active .barre:after {
 top: 0;
 -webkit-transform: rotate(-45deg);
 -moz-transform: rotate(-45deg);
 -ms-transform: rotate(-45deg);
 -o-transform: rotate(-45deg);
 transform: rotate(-45deg);
 opacity: 1;
}

</style>
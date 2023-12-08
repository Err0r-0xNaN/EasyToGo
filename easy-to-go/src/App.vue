<script setup>
import {
  RouterLink,
  RouterView,
} from 'vue-router';

import headNav from './components/nav.vue';

window.onscroll = () => { /* 页面滚动检测 */
    /* console.log(document.documentElement.scrollTop) */
    if (document.documentElement.scrollTop >= 64) {
      document.getElementsByClassName('navFixed')[0].style.transform = 'translateY(0)';
      
    } else {
      document.getElementsByClassName('navFixed')[0].style.transform = 'translateY(-64px)';
    }
    /* console.log(document.getElementsByClassName('navFixed')[0].style.transform) */
};

</script>

<template>
  
  <div class="nav">
    <headNav></headNav>
  </div>

  <div class="navFixed">
    <headNav></headNav>
  </div>
  
  <div class="mainBody">
    <RouterView></RouterView>
  </div>
  
</template>
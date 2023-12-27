<!--
 * @Date: 2023-12-12 08:31:32
 * @LastEditTime: 2023-12-23 17:30:18
 * @FilePath: \EasyToGo\easy-to-go\src\App.vue
-->

<script setup>
import {
  RouterLink,
  RouterView,
} from 'vue-router';

import headNav from './components/nav.vue';

window.onscroll = () => { /* 页面滚动检测 */
    /* console.log(document.documentElement.scrollTop) */
    /* if (document.documentElement.scrollTop >= 64) {
      document.getElementsByClassName('navFixed')[0].style.transform = 'translateY(0)';
      
    } else {
      document.getElementsByClassName('navFixed')[0].style.transform = 'translateY(-64px)';
    } */
    /* console.log(document.getElementsByClassName('navFixed')[0].style.transform) */
};
/* console.clear(); */
console.log("本网站含有%c10%c个Flag\r\n（形如：%c0xNaNFlag{xxx}%c）\r\n",'color: red','color: black', 'color: blue', 'color: black')
</script>
  
<template>  
  <RouterView></RouterView>
</template>
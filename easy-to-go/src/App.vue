
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
/* console.clear(); */
console.log("本网站含有%c10%c个Flag\r\n（形如：%c0xNaNFlag{xxx}%c）\r\n找到后可凭截图和发现过程联系网站管理员兑换奖励（仅第一个找到的可兑换）",'color: red','color: black', 'color: blue', 'color: black')
</script>
  
<template>
  
  <div class="nav">
    <headNav></headNav>
  </div>

  <div class="navFixed">
    <headNav></headNav>
  </div>
  
  <div id="MainBody" class="mainBody">
    <RouterView></RouterView>
  </div>
  
</template>
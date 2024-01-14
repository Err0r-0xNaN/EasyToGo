<!--
 * @Date: 2024-01-01 15:06:21
 * @LastEditTime: 2024-01-06 22:44:34
 * @FilePath: \easy-to-go\src\views\public\test.vue
-->
<template>
    <button @click="webclick">给后台发送消息</button>
</template>
​
<script>
export default {
    data() {
        return {
            ws: null,
        };
    },
    created() {
        //连接WebSocket服务端，然后初始化监听事件
        this.ws = new WebSocket('ws://localhost:9090/notice/test');
        this.wsInit();
    },
    methods: {
      wsInit() {
          this.ws.onopen = () => {
            this.ws.send('服务已连接');
            console.log(this.ws.readyState);
        };
        this.ws.onclose = () => {
            console.log('服务器关闭');
            console.log(this.ws.readyState);
        };
        this.ws.onmessage = message => {
            console.log('收到服务器消息');
            console.log(message);
            console.log(this.ws.readyState);
        };
        this.ws.onerror = error => {
            console.log('报错了');
            console.log(error);
            console.log(this.ws.readyState);
        };
      },
    },
};
</script>
​

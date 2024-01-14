<!--
 * @Date: 2023-12-30 22:54:20
 * @LastEditTime: 2023-12-31 15:50:24
 * @FilePath: \easy-to-go\src\components\consoleNav.vue
-->
<script setup>

</script>
<template>
    <div class="navPanel">
        <div class="head" style="display: flex;align-items: center;">
            <div class="headImg" style="width: 40px;height: 40px;"><img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" alt="头像" style="width: 100%;height: 100%;border-radius: 50%;"/></div>
            <div class="userFunctionPanel">
                <div class="functionItem" @click="logOut()">登出</div>
            </div>
        </div>
        
    </div>
</template>

<script>
import Message from '@/components/tips/tips';

export default {
    data(){
        return{

        }
    },
    methods: {
        logOut(){
            localStorage.clear()
            Message({type: 'success', text: '已登出'})
            window.location.reload();
            
        }
    }
}
</script>

<style scoped>
.navPanel{
    border-radius: 0;
    display: flex;
    justify-content: end;
    padding: 0 15px 0 5px;

    position: relative;
    z-index: 1;
}
.userFunctionPanel{
    position: absolute;
    min-width: 50px;
    /* min-height: 70px; */
    
    right: 10px;
    top: 55px;
    visibility: hidden;
    
}
.head:hover .userFunctionPanel{
    visibility: visible;  
    background-color: rgba(255, 255, 255, 0.4);
    text-align: center;
    border-radius: 5px;
    box-shadow: var(--shadow-Panel);
    backdrop-filter: blur(3px);
    
}

.functionItem{
    padding: 5px 10px;
    cursor: pointer;
    line-height: 28px;
}
</style>
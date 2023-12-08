<template>
    <!-- <div class="panelBox">
        <div class="panel">
            <div class="logo">
                
            </div>
            <div class="form" v-bind="formLogin">
                <input name="username" type="text"/>
                <input name="password" type="password"/>
            </div>
        </div>
    </div> -->
</template>

<script>
import '@/assets/style/Login.css';

export default{
    data(){
        return{

        }
    },
    methods(){

    }
}
</script>

<style scoped>

/* LogIn */
.panelBox{
    height: 100vh;
    width: 100%;
    position: relative;
    
    background: url('../../assets/img/LoginBackground.jpg') no-repeat;
    background-position: center;
    background-size: cover;
}

.panel{
    position: relative;
    top: 100px;
    margin: 0 auto;
    width: 750px;
    min-height: 600px;
    border-radius: 7px;
    background-color: rgba(240, 248, 255, var(--Alpha-LoginPanel));

    box-shadow: var(--shadow-Panel);

    overflow: hidden;

}

.logo{
    /* position: absolute; */

}

</style>
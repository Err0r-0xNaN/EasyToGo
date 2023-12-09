<template>
    <div class="login-page">
        <div class="background-image"></div>
        <div class="form-container">
            <div id="ErrorBox" :class="errorBox" v-show="errorShow">
                <img  />
                {{ FormErrorInfo }}
            </div>
            <form>
                <div class="form-group">
                    <label for="username">用户名：</label>
                    <input type="text" id="username" v-model="username" />
                </div>
                <div class="form-group">
                    <label for="password">密码：</label>
                    <input type="password" id="password" v-model="password" />
                </div>
                <button @click.prevent="submitForm()" type="submit">Login</button>
                <p :style="{ paddingTop: '10px' }">
                    没有账号?
                    <a href="/register">去注册</a>
                </p>
            </form>
        </div>
    </div>
</template>

<script>
import '@/assets/style/Login.css';

import axios from 'axios';

export default {
    data() {
        return {
            errorShow: false,
            FormErrorInfo: '',
            errorBox: 'errorBox'
        };
    },
    methods: {
        submitForm: function() {
            console.log(this.username == '',this.username == undefined && this.password == undefined,this.username == undefined,this.password == undefined);
            console.log(this.username,this.password);
            if ((this.username == undefined || this.username == '') && (this.password == undefined || this.password == '')) {
                this.errorShow = true;
                this.FormErrorInfo = "账号密码不能为空！";
                var elem = document.getElementById('ErrorBox');
                if (elem) {
                    elem.classList.add('shake');
                    setTimeout(() => {
                        elem.classList.remove('shake');
                        this.errorShow = false;
                    }, 2800);
                }
            } else if (this.username == undefined || this.username == '') {
                this.errorShow = true;
                this.FormErrorInfo = "账号不能为空！";
                var elem = document.getElementById('username');
                if (elem) {
                    elem.classList.add('shake');
                    setTimeout(() => {
                        elem.classList.remove('shake');
                        this.errorShow = false;
                    }, 2800);
                }
            } else if (this.password == undefined || this.password == '') {
                this.errorShow = true;
                this.FormErrorInfo = '密码不能为空！';
                var elem = document.getElementById('password');
                if (elem) {
                    elem.classList.add('shake');
                    setTimeout(() => {
                        elem.classList.remove('shake');
                        this.errorShow = false;
                    }, 2800);
                }
            }
            else if(this.username != undefined && this.password != undefined){
                /* if(this.username != ) */
                console.log('ok');
            }
            
        },
    },
};
</script>

<style scoped>
/* LogIn */
.login-page {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.background-image {
    width: 100%;
    height: 100%;
    background-image: url('../../assets/img/LoginBackground.jpg');
    background-size: cover;
    background-position: center;
    position: absolute;
}

.form-container {
    position: relative;
    width: 300px;
    padding: 50px;
    background-color: rgba(255, 255, 255, 0.7);
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.errorBox {
    padding: 10px 0;
    border: 1px solid red;
    background-color: rgba(254, 79, 79, 0.617);
    font-size: 16px;
    font-weight: 800;

    transition: all 0.8s;
}

.shake {
    animation: shake 800ms ease-in-out;
}

@keyframes shake {
    10%,
    90% {
        transform: translate3d(-1px, 0, 0);
    }
    20%,
    80% {
        transform: translate3d(+2px, -2px, 0);
    }
    30%,
    70% {
        transform: translate3d(-4px, +1px, 0);
    }
    40%,
    60% {
        transform: translate3d(+4px, -2px, 0);
    }
    50% {
        transform: translate3d(-4px, 0, 0);
    }
}

.form-group {
    margin-bottom: 15px;
}

label {
    display: block;
    margin-bottom: 5px;
}

input[type='text'],
input[type='password'] {
    width: 100%;
    padding: 10px 0;
    border: 1px solid #ccc;
    border-radius: 4px;
}

button {
    width: 100%;
    padding: 10px;
    background-color: #4caf50;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
</style>

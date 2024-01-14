<!--
 * @Date: 2023-12-20 15:20:02
 * @LastEditTime: 2023-12-31 13:56:07
 * @FilePath: \easy-to-go\src\views\console\login.vue
-->
<template>
    <div class="layout">
        <div id="MainBody" class="mainBody">
            <div class="loginPanel">
                <h1 style="text-align: center">管理员登录</h1>

                <div class="box">
                    <div style="text-align: left; text-indent: 1em" class="tipBox" v-show="error_info === '' ? 0 : 1">⚠：{{ error_info }}</div>
                    <input v-model="data.email" type="text" class="input_type_default" placeholder="请输入邮箱" style="" />
                    <input v-model="data.password" type="password" class="input_type_default" placeholder="请输入密码" style="width: 100%" />
                    <div style="overflow: hidden; margin: 20px 0"></div>
                    <button style="margin: auto" type="button" class="btn_type_default" @click="submitLogin()">登 录</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Message from '../../components/tips/tips';
import request from '../../utils/request.js';

export default {
    data() {
        return {
            data: {
                email: '',
                password: '',
            },
            error_info: '',
        };
    },
    methods: {
        //数据验证
        checkIsLegal() {
            let email = this.data.email;
            let password = this.data.password;

            /* console.log(email,password,isConsentClause) */
            /* 邮箱正则 */
            const regex = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

            if (!email) {
                //邮箱为空
                this.error_info = '邮箱不能为空！';
                this.shake();
                return false;
            }
            if (!regex.test(email)) {
                //邮箱格式有误
                this.error_info = '邮箱格式有误！';
                this.shake();
                return false;
            }
            if (this.type == 'login' || this.verifycode != '') {
                if (!password) {
                    //密码为空
                    this.error_info = '密码不能为空！';
                    this.shake();
                    return false;
                }
                if (password.length < 6) {
                    this.error_info = '密码不得少于 6 位';
                    this.shake();
                    return false;
                }
                if (password.length > 18) {
                    this.error_info = '密码不得多于 18 位';
                    this.shake();
                    return false;
                }
            }

            if (!isConsentClause) {
                //未同意协议
                this.error_info = '请先同意协议！';
                this.shake();
                return false;
            }

            return true;
        },
        submitLogin() {
            request.post('/adminLogin', { email: this.data.email, password: this.data.password }).then(res => {
                console.log(res.data);
                this.error_info = '';
                localStorage.setItem('user-character', JSON.stringify(res.data.data.character));
                if (localStorage.getItem('user-data') && localStorage.getItem('user-character')) {
                    Message({ type: 'success', text: '登录成功' });
                    window.location.reload();
                    /* this.$router.push('/console'); */
                }
            });
            request.post('/login', { email: this.data.email, password: this.data.password }).then(res => {
                if (res.data.statusCode != '200') {
                    this.error_info = res.data.msg;
                } else if (res.data.data.token) {
                    localStorage.setItem('user-data', JSON.stringify(res.data.data));
                    this.error_info = '';
                } else {
                    console.log('未知的状态类型,statusCode：', res.data.statusCode);
                }
            });
        },
    },
};
</script>

<style scoped>
.mainBody {
    background: url('@/assets/img/ConsoleLoginBG.webp');
    /* background-position: center; */
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    height: 100vh;
}

.loginPanel {
    border-radius: 6px;
    padding: 15px;
    backdrop-filter: blur(3px);
    box-shadow: var(--shadow-Panel);
}

@media (min-width: 753px) {
    .loginPanel {
        position: relative;
        top: 200px;
        margin: 0 auto;
        width: 750px;
        min-height: 400px;
        background-color: rgba(255, 255, 255, 0.8);
    }
}

@media (max-width: 752px) {
    .loginPanel {
        /* position: relative;
        top: 100px; */
        margin: 0 auto;
        width: 100%;
        height: 100vh;
        background-color: rgba(255, 255, 255, 0.8);
    }

    .box {
        width: fit-content;
        margin: 50px auto 0 0;
    }

    .layout {
        overflow: hidden;
    }
}

.box {
    width: fit-content;
    margin: 20px auto;

    text-align: center;
}

.box input {
    padding: 18px 0 12px 0;
    width: 100%;
    text-indent: 1em;
    margin: 20px 0 0 0;
}
</style>

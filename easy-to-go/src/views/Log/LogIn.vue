<!--
 * @Date: 2023-12-12 08:31:33
 * @LastEditTime: 2023-12-27 11:03:46
 * @FilePath: \EasyToGo\easy-to-go\src\views\Log\LogIn.vue
-->
<script setup>
import navVue from '../../components/nav.vue';
</script>

<template>
    <div id="MainBody" class="mainBody">
        <navVue></navVue>
        <div class="marginCenter contain">
            <div class="topBlock"></div>
            <div class="flex center" style="justify-content: space-between">
                <div class="flex_l" style="text-align: center"></div>
                <div class="flex_r">
                    <div class="loginPanel">
                        <h2 style="text-align: left; margin-bottom: 15px">{{ TypeTitle }}</h2>
                        <div class="tipBox" v-show="error_info === '' ? 0 : 1">⚠：{{ error_info }}</div>
                        <input v-model="data.email" type="text" class="input_type_default" placeholder="请输入邮箱" />
                        <div v-show="type === 'login' ? 1 : 0" style="70%">
                            <input v-model="data.password" type="password" class="input_type_default" placeholder="请输入密码" />
                            <div>
                                <input v-model="isConsentClause" type="checkbox" />
                                我已阅读并同意
                                <a href="#">用户隐私条款</a>
                                和
                                <a href="#">用户规范</a>
                            </div>
                            <div style="overflow: hidden; margin: 10px 0"></div>
                            <button type="button" class="btn_type_default" @click="submitLogin()">登 录</button>
                            <div style="text-align: right; width: 80%; margin: 4px auto 0 auto">
                                <a href="#">忘记密码</a>
                            </div>
                            <div style="overflow: hidden; margin: 10px 0"></div>
                            <div>
                                已有帐号？
                                <span
                                    @click="
                                        changeType('register');
                                        TypeTitle = '注册';
                                    ">
                                    <a href="#">去注册</a>
                                </span>
                            </div>
                        </div>
                        <div v-show="type === 'register' ? 1 : 0" style="70%">
                            <div class="inline-input-panel">
                                <input v-model="data.verifycode" type="text" class="input_type_inline" placeholder="请输入验证码" style="width: 60%" />
                                <button type="button" class="btn_type_inline" @click="checkEmail()" style="width: 20%">{{ btn_verifycode }}</button>
                            </div>
                            <div v-show="data.verifycode != ''">
                                <input v-model="data.password" type="password" class="input_type_default" placeholder="请设置密码(6-18位)" />
                            </div>

                            <div>
                                <input v-model="isConsentClause" type="checkbox" />
                                我已阅读并同意
                                <a href="#">用户隐私条款</a>
                                和
                                <a href="#">用户规范</a>
                            </div>
                            <div style="overflow: hidden; margin: 10px 0"></div>
                            <button type="button" class="btn_type_default" @click="submitRegister()">注册</button>
                            <div style="overflow: hidden; margin: 10px 0"></div>
                            <div>
                                已有帐号？
                                <span
                                    @click="
                                        changeType('login');
                                        TypeTitle = '登录';
                                    ">
                                    <a href="#">去登陆</a>
                                </span>
                            </div>
                        </div>
                        <!-- <div style="position: absolute;bottom: 0px;right: 20px;height: 160px;">
                            <img height="160px" src="https://www.yuanshen.com/images/ys.96a55539.png">
                        </div> -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { requestGet, requestPost } from '@/utils/request.js';
import Message from '../../components/tips/tips.js';

export default {
    data() {
        return {
            type: 'login',
            TypeTitle: '登录',
            data: {
                email: '',
                password: '',
                verifycode: '',
            },
            isConsentClause: false,
            error_info: '',
            btn_verifycode: '发送验证码',
            btn_cd: 60,
        };
    },
    methods: {
        shake() {
            document.getElementsByClassName('tipBox')[0].classList.add('shake');
            let timeSet = setTimeout(() => {
                document.getElementsByClassName('tipBox')[0].classList.remove('shake');
            }, 2000);
        },
        //数据验证
        checkIsLegal() {
            let email = this.data.email;
            let password = this.data.password;
            let isConsentClause = this.isConsentClause;

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
        //登录
        submitLogin() {
            if (this.checkIsLegal()) {
                requestPost('/login', { email: this.data.email, password: this.data.password }).then(res => {
                    console.log(res.data);
                    if (res.data.statusCode != '200') {
                        this.error_info = res.data.msg;
                        this.shake();
                    } else if (res.data.data.token) {
                        Message({ type: 'success', text: '登录成功' });
                        localStorage.setItem("token", res.data.data.token);
                        this.error_info = '';
                        this.$router.push('/');
                    } else {
                        console.log('未知的状态类型,statusCode：', res.data.statusCode);
                    }
                });
            } else {
                console.log('WARNING!');
            }
        },
        //检查邮箱
        checkEmail() {    
            if (this.checkIsLegal()) {
                document.getElementsByClassName('btn_type_inline')[0].disabled = true;
                let Timer = setInterval(() => {
                    if (this.btn_cd > 0) {
                        this.btn_verifycode = "重试请等待" + this.btn_cd + "秒";
                        this.btn_cd = this.btn_cd - 1;
                        console.log("cd：",this.btn_cd)         
                    } else {
                        //TODO:BUG待修
                        this.btn_verifycode = '发送验证码';
                        document.getElementsByClassName('btn_type_inline')[0].disabled = false;
                        clearInterval(Timer);
                    } 
                },1000);
                requestPost('/checkEmail', { email: this.data.email }).then(res => {
                    console.log(res.data);
                    if (res.data.statusCode != '200') {
                        this.error_info = res.data.msg;
                        this.shake();
                        Message({ type: 'error', text: res.data.msg });
                        this.btn_cd = 0;
                    } else if (res.data.statusCode == '200') {
                        this.error_info = '';
                        /* this.$router.push('/'); */
                    } else {
                        console.log('未知的状态类型,statusCode：', res.data.statusCode);
                    }
                });
            } else {
                console.log('WARNING!');
            }
        },
        //注册
        submitRegister() {
            if (this.data.verifycode == '') {
                this.error_info = '验证码不能为空!';
                this.shake();
            } else if (this.checkIsLegal()) {
                requestPost('/register', { email: this.data.email, password: this.data.password, emailVerifyCode: this.data.verifycode }).then(res => {
                    console.log(res.data);
                    if (res.data.statusCode != '200') {
                        this.error_info = res.data.msg;
                        this.shake();
                        Message({ type: 'error', text: res.data.msg });
                    } else if (res.data.statusCode == '200') {
                        console.log(res.data);
                        this.error_info = '';
                        Message({ type: 'success', text: '注册成功' });
                        /* this.$router.push('/'); */
                    } else {
                        console.log('未知的状态类型,statusCode：', res.data.statusCode);
                    }
                });
            } else {
                console.log(this.changeType());
                /* Message({type: 'error', text: this.type}) */
            }
        },
        changeType(ToType, fromType) {
            this.type = ToType;
        },
    },
};
</script>

<style scoped>
input {
    margin: 15px 0 0 0;
    font-size: 18px;
}
input::placeholder {
    font-size: 18px;
}
#MainBody {
    overflow: hidden;
}
.mainBody {
    background: url('../../assets/img/Login_BackgroundImg.png');
    /* background-position: center; */
    background-repeat: no-repeat;
    background-size: cover;
}
.flex_r {
    width: 450px;
    transition: right 0.3s, width 0.2s;
}
@media (min-width: 1331px) {
    .flex_r {
    }
}
@media (min-width: 576px) and (max-width: 1330px) {
    .flex_l {
        display: none;
    }
    .flex_r {
        position: fixed;
        right: 30px;
    }
}
@media (min-width: 471px) and (max-width: 575px) {
    .flex_l {
        display: none;
    }
    .flex_r {
        position: fixed;
        right: 10px;
    }
}
@media (max-width: 470px) {
    .contain {
        width: 100%;
    }
    .flex_l {
        display: none;
    }
    .flex_r {
        min-width: 340px;
        width: 100%;
    }
}

.loginPanel {
    position: relative;
    min-height: 500px;
    border-radius: 17px;
    background-color: rgba(255, 255, 255, 0.7);
    backdrop-filter: blur(5px);
    box-shadow: var(--shadow-Panel);
    padding: 25px;
}
</style>

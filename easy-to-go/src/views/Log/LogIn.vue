<template>
    <div class="background">
        <div class="log_in_mainbox">
            <!-- LOGO栏 -->

            <div class="log_in_logobox">
                <img src="./暂定logo.png" alt="" />
            </div>
        </div>

        <!-- 内容栏 -->
        <div class="log_in_content">
            <!-- 标题 -->
            <h1>注册您的易通达账号</h1>
            <!-- 转换登陆 -->
            <div class="log_in_switchbuttonbox">
                <span>已有账户?</span>
                <a href="/login" style="text-decoration: none" target="">
                    <div class="log_in_switchbutton">
                        前去登陆
                        <img src="./转换登陆.png" alt="" />
                    </div>
                </a>
            </div>
            <!-- 注册方式选择 -->
            <div class="log_in_logintype">
                <ul>
                    <li class="log_in_logintype_email active">邮箱注册</li>
                    <li class="log_in_logintype_Account">账号密码注册</li>
                </ul>
                <div style="flex: 0.5"></div>
            </div>
            <!-- 主注册输入框 -->
            <div class="log_in_input">
                <div class="log_in_input_inputbox" id="border_change">
                    <div class="log_in_input_email active">
                        <div style="flex: 0.5; max-width: 50px">
                            <img src="./邮箱.png" alt="" />
                        </div>
                        <div style="flex: 1">
                            <input type="email" placeholder="请输入邮箱" name="email" id="email" />
                        </div>
                    </div>
                    <div class="log_in_input_Account">
                        <div style="flex: 0.5; max-width: 50px">
                            <img src="./账号密码.png" alt="" />
                        </div>
                        <div style="flex: 1; display: flex; flex-direction: column; justify-content: space-between">
                            <div style="flex: 1; overflow: hidden; display: flex; align-items: center">
                                <div style="min-width: 64px">账号</div>
                                <input type="text" placeholder="请输入您的账号" style="flex: 1" name="username" id="username" maxlength="10" />
                            </div>
                            <div style="flex: 1; overflow: hidden; display: flex; align-items: center">
                                <div style="min-width: 64px">密码</div>
                                <input type="password" placeholder="请输入您的密码" style="flex: 1" name="password" id="password" maxlength="10" />
                            </div>
                            <div style="flex: 1; overflow: hidden; display: flex; align-items: center">
                                <div style="min-width: 64px">验证密码</div>
                                <input type="password" placeholder="请再次确认密码" style="flex: 1" name="passwordcheck" id="passwordcheck" maxlength="10" />
                            </div>
                        </div>
                    </div>
                </div>
                <span id="tip_text"></span>
            </div>
            <!-- 注册提交按钮 -->
            <div class="log_in_nextstep">
                <button class="log_in_nextbtn" id="log_in_button">注册</button>
            </div>
            <!-- 隐私协议栏 -->
            <div class="log_in_Privacy_agreement">
                <input type="checkbox" id="personcheck" />
                <span style="margin-left: 9px; user-select: none">
                    已阅读并同意以下协议
                    <a href="#" target="_blank" style="text-decoration: none; color: rgb(0, 157, 255); margin-left: 3px">服务条款</a>
                    ,
                    <a href="#" target="_blank" style="text-decoration: none; color: rgb(0, 157, 255); margin-left: 3px">隐私协议</a>
                </span>
            </div>
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
            errorBox: 'errorBox',
        };
    },
    methods: {
        submitForm() {
            console.log(this.username == '', this.username == undefined && this.password == undefined, this.username == undefined, this.password == undefined);
            console.log(this.username, this.password);
            if ((this.username == undefined || this.username == '') && (this.password == undefined || this.password == '')) {
                this.errorShow = true;
                this.FormErrorInfo = '账号密码不能为空！';
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
                this.FormErrorInfo = '账号不能为空！';
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
            } else if (this.username != undefined && this.password != undefined) {
                /* if(this.username != ) */
                /* console.log('ok'); */

                // 在Vue组件中定义一个方法，使用axios发送GET请求
                axios
                    .post(
                        'http://localhost:9090/login',
                        {
                            username: this.username,
                            password: this.password,
                        },
                        { 'Content-Type': 'application/x-www-form-urlencoded' }
                    )
                    .then(response => {
                        console.log(response.data);
                    })
                    .catch(error => {
                        console.log(error);
                    });
            }
        },
    },
};
</script>

<style scoped>
/* 背景图片 */
.background{
    background-image: url('./Easy-Go_log_in_bg.jpg');
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    height: 100vh;
    /* margin-top: -64px; */
}
/* 主要内容 */
.log_in_mainbox {
    width: 100%;
    /* height: 550px; */
    padding-top: 110px;
    margin-bottom: 80px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
/* LOGO */
.log_in_logobox {
    width: 29%;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    min-width: 300px;
}
.log_in_logobox img {
    max-height: 80px;
    min-width: 300px;
    max-width: 300px;
}
/* 登录框 */
.log_in_content {
    background-color: white;
    min-height: 300px;
    min-width: 300px;
    flex: 1;
    width: 29%;
    margin-left: auto;
    margin-right: auto;
    border-radius: 24px;
    box-sizing: border-box;
    padding: 60px;
    display: flex;
    flex-direction: column;
}
/* 标题栏 */
.h1 {
    font-size: 2.857rem;
    font-weight: 700;
    line-height: 2.857rem;
}
/* 已有账户切换栏 */
.log_in_switchbuttonbox span {
    opacity: 0.7;
}
.log_in_switchbuttonbox {
    display: flex;
    align-items: center;
    margin-top: 10px;
    margin-bottom: 10px;
    flex: 0.5;
}
.log_in_switchbutton {
    font-weight: 500;
    color: #0298f5;
    margin-left: 10px;
    transition: all 0.5s;
}
.log_in_switchbutton img {
    top: 2px;
    width: 16px;
    height: 15px;
    margin-left: 4px;
    transition: all 0.5s;
    position: relative;
}
.log_in_switchbutton:hover img {
    opacity: 0.7px;
    filter: brightness(100%) !important;
}
.log_in_switchbutton:hover {
    filter: brightness(130%);
}
/* 注册方式选择栏 */
.log_in_logintype {
    flex: 0.4;
    display: flex;
    position: relative;
}
.log_in_logintype ul {
    list-style: none;
    background-color: #f3f7fb;
    flex: 0.8;
    display: flex;
    align-items: center;
    border-radius: 7px;
    overflow: hidden;
}
.log_in_logintype > ul > li:hover {
    color: white;
    background-color: #37d651;
}
.log_in_logintype_email {
    min-width: 40px;
    margin: 5px;
    height: 40px;
    line-height: 40px;
    flex: 1;
    border-radius: 7px;
    text-align: center;
    font: 400 'PingFang SC', 'Microsoft YaHei', 'Microsoft JhengHei', '黑体', arial, 'STHeiti', '\5b8b\4f53';
    transition: all 0.5s;
}
.log_in_logintype_Account {
    margin: 5px;
    height: 40px;
    line-height: 40px;
    min-width: 40px;
    flex: 1;
    border-radius: 7px;
    color: #243042;
    opacity: 0.8;
    text-align: center;
    font: 400 'PingFang SC', 'Microsoft YaHei', 'Microsoft JhengHei', '黑体', arial, 'STHeiti', '\5b8b\4f53';
    transition: all 0.5s;
}
/* 注册输入框 */
.log_in_input {
    flex: 1.5;
    width: 100%;
    display: flex;
}
/* 正则提示字 */
.log_in_input span {
    align-items: center;
    flex: auto;
    display: flex;
    margin-left: 5px;
    font-size: 13px;
    color: rgb(255, 87, 51);
    width: 100px;
}
.log_in_input_inputbox {
    width: 80%;
    height: 70px;
    min-width: 160px;
    padding: 6px;
    border-radius: 0.85714286rem;
    background-color: white;
    margin-top: 10px;
    margin-bottom: 10px;
    border: 1px solid rgb(226, 229, 240);
    display: flex;
    font-size: 16px;
    color: #646464;
    transition: all 0.5s;
    border: 2px solid #d3d1d1;
}
.log_in_input_inputbox img {
    width: 35px;
    margin: 10px;
}
.log_in_input_inputbox input {
    width: 80%;
    height: 35px;
    padding: 3px;
    border-radius: 0.85714286rem;
    background-color: white;
    margin-top: 7px;
    margin-bottom: 5px;
    border: 0px;
    outline: none;
    min-width: 20px;
}
.log_in_input_email {
    display: none;
    flex: 1;
}
.log_in_input_Account {
    display: flex;
    flex: 1;
}
.log_in_input_Account img {
    width: 30px;
    margin: 12px;
}
.log_in_input_Account input {
    width: 80%;
    height: 30px;
    line-height: 30px;
    padding: 1px;
    border-radius: 0.85714286rem;
    background-color: white;
    margin-top: 1px;
    margin-bottom: 1px;
    margin-left: 5px;
    border: 0px;
    outline: none;
    min-width: 12px;
    font-size: 14px;
}
/* 注册提交按钮栏 */
.log_in_nextstep {
    flex: 1;
}
.log_in_nextbtn {
    background-color: #1772f6;
    width: 30%;
    height: 50px;
    min-width: 50px;
    border-radius: 4px;
    border: 0px;
    color: white;
    transition: all 0.3s;
}
.log_in_nextbtn:hover {
    background-color: #37d651;
    color: white;
}
/* 隐私协议栏 */
.log_in_Privacy_agreement {
    flex: 0.5;
    font-size: 14px;
    color: #243042;
}
/* 注册方式切换总class */
.active {
    background-color: white;
}
.input_active {
    display: flex;
}
.log_in_input_email.active {
    display: flex;
}
.log_in_input_Account.active {
    display: flex;
}
.log_in_input_email:not(.active) {
    display: none;
}
.log_in_input_Account:not(.active) {
    display: none;
}
.log_in_input_inputbox_active {
    border: 2px solid #1772f6;
}
.log_in_input_inputbox_active2 {
    border: 2px solid red;
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
</style>

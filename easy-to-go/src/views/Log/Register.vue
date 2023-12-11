<template>
    <div class="background">
        <div class="logo_box">
            <img src="@/assets/img/暂定logo.png" alt="" />
        </div>
        <div class="log_panel">
            <!-- 标题 -->
            <h1>注册您的易通达账号</h1>

            <!-- 转换登陆 -->
            <div class="line_group">
                已有帐号？
                <a href="/login">
                    立即登录
                    <img src="@/assets/icons/Log/changeToLogIn.png" style="height: 16px; margin: auto 2px; position: relative; top: 2px" />
                </a>
            </div>

            <!-- 切换注册方式 -->
            <div class="change_btn_box">
                <div class="active" id="reg_username" @click="changeRegisterType('reg_username')">账号注册</div>
                <div class="" id="reg_email" @click="changeRegisterType('reg_email')">邮箱注册</div>
            </div>

            <div class="error_panel" v-show="errorTips">⚠错误：{{ error_info }}</div>

            <!-- 账号+密码注册 -->
            <div class="form_panel" v-if="regis_type == 'reg_username' ? 1 : 0">
                <div class="left_panel">
                    <img src="@/assets/icons/Log/usernameANDpassword.png" />
                </div>
                <div class="right_panel">
                    <div class="line_group flex">
                        <div class="input_title">账号：</div>
                        <input class="input_type_default" type="text" :placeholder="placeholder.username" name="username" v-model="username" maxlength="18" />
                    </div>

                    <div class="line_group flex">
                        <div class="input_title">密码：</div>
                        <input class="input_type_default" type="text" :placeholder="placeholder.password" name="password" v-model="password" maxlength="18" />
                    </div>

                    <div class="line_group flex">
                        <div class="input_title">确认密码：</div>
                        <input class="input_type_default" type="text" :placeholder="placeholder.ispasswordok" name="username" v-model="ispasswordok" maxlength="18" />
                    </div>
                </div>
            </div>
            <!-- 邮箱+密码注册 -->
            <div class="form_panel" v-if="regis_type == 'reg_email' ? 1 : 0">
                <div class="left_panel">
                    <img src="@/assets/icons/Log/email.png" />
                </div>
                <div class="right_panel">
                    <div class="line_group flex">
                        <div class="input_title">邮箱：</div>
                        <input class="input_type_default" type="text" :placeholder="placeholder.useremail" name="useremail" v-model="useremail" maxlength="18" />
                    </div>

                    <div class="line_group flex">
                        <div class="input_title">密码：</div>
                        <input class="input_type_default" type="text" :placeholder="placeholder.password" name="password" v-model="password" maxlength="18" />
                    </div>

                    <div class="line_group flex">
                        <div class="input_title">确认密码：</div>
                        <input class="input_type_default" type="text" :placeholder="placeholder.ispasswordok" name="username" v-model="ispasswordok" maxlength="18" />
                    </div>
                </div>
            </div>
            <!-- 隐私协议栏 -->
            <div id="law" class="law">
                <input type="checkbox" v-model="personcheck" />
                <span style="margin-left: 9px; user-select: none">
                    已阅读、理解并完全同意
                    <a href="#" target="_blank" style="text-decoration: none; color: rgb(0, 157, 255)">服务条款</a>
                    和
                    <a href="#" target="_blank" style="text-decoration: none; color: rgb(0, 157, 255)">隐私协议</a>
                </span>
            </div>
            <div class="flex flexCenter">
                <div class="nextStep">
                    <button @click="CheckAndRegister()" class="btn_type_default" id="log_in_button">注册</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            placeholder: {
                username: '请输入您的账号',
                password: '请输入您的密码',
                ispasswordok: '请确认您的密码',
                useremail: '请输入邮箱地址',
            },
            errorTips: false,
            error_info: '并没有错误,但不知道为什么显示出来了...',
            regis_type: 'reg_username',
        };
    },
    methods: {
        CheckAndRegister() {
            let lawCheck = this.personcheck;
            var userName = this.username;
            var userEmail = this.useremail;
            var passWord = this.password;

            var emailRegE = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;

            var elem;
            if (this.regis_type == 'reg_username') {
                /* console.log(); */
                if (userName == '' || userName == undefined) {
                    elem = document.getElementsByClassName('form_panel')[0];
                    this.error_info = '账号不能为空!';
                    this.errorTips = true;
                    elem.classList.add('shake');
                    let ShowErrorInfo = setTimeout(() => {
                        elem.classList.remove('shake');
                        /* this.errorTips = false; */
                    }, 2000);
                } else if (userName.length < 4) {
                    elem = document.getElementsByClassName('form_panel')[0];
                    this.error_info = '账号至少四位!';
                    this.errorTips = true;
                    elem.classList.add('shake');
                    let ShowErrorInfo = setTimeout(() => {
                        elem.classList.remove('shake');
                        /* this.errorTips = false; */
                    }, 2000);
                } else if ((userName != '' || userName != undefined || userEmail != '' || userEmail != undefined) && (passWord == '' || passWord == undefined)) {
                    elem = document.getElementsByClassName('form_panel')[0];
                    this.error_info = '密码不能为空!';
                    this.errorTips = true;
                    elem.classList.add('shake');
                    let ShowErrorInfo = setTimeout(() => {
                        elem.classList.remove('shake');
                        /* this.errorTips = false; */
                    }, 2000);
                } else if (passWord.length < 6) {
                    elem = document.getElementsByClassName('form_panel')[0];
                    this.error_info = '密码至少六位!';
                    this.errorTips = true;
                    elem.classList.add('shake');
                    let ShowErrorInfo = setTimeout(() => {
                        elem.classList.remove('shake');
                        /* this.errorTips = false; */
                    }, 2000);
                } else if (this.ispasswordok != passWord) {
                    elem = document.getElementsByClassName('form_panel')[0];
                    this.error_info = '两次密码不一致!';
                    this.errorTips = true;
                    elem.classList.add('shake');
                    let ShowErrorInfo = setTimeout(() => {
                        elem.classList.remove('shake');
                        /* this.errorTips = false; */
                    }, 2000);
                } else if (!lawCheck) {
                    this.error_info = '请先阅读并同意各项条款!';
                    this.errorTips = true;
                    document.getElementById('law').classList.add('shake');
                    let ShowErrorInfo = setTimeout(() => {
                        document.getElementById('law').classList.remove('shake');
                        /* this.errorTips = false; */
                    }, 2000);
                } else {
                    /* 注册功能 */
                    /* console.log(userName,passWord); */
                }
            } else if (this.regis_type == 'reg_email') {
                /* console.log(userEmail) */
                if (userEmail == '' || userEmail == undefined) {
                    elem = document.getElementsByClassName('form_panel')[0];
                    this.error_info = '邮箱地址不能为空!';
                    this.errorTips = true;
                    elem.classList.add('shake');
                    let ShowErrorInfo = setTimeout(() => {
                        elem.classList.remove('shake');
                        /* this.errorTips = false; */
                    }, 2000);
                } else if(!emailRegE.test(userEmail)){
                    elem = document.getElementsByClassName('form_panel')[0];
                    this.error_info = '邮箱格式不正确!';
                    this.errorTips = true;
                    elem.classList.add('shake');
                    let ShowErrorInfo = setTimeout(() => {
                        elem.classList.remove('shake');
                        /* this.errorTips = false; */
                    }, 2000);
                } else if ((userName != '' || userName != undefined || userEmail != '' || userEmail != undefined) && (passWord == '' || passWord == undefined)) {
                    elem = document.getElementsByClassName('form_panel')[0];
                    this.error_info = '密码不能为空!';
                    this.errorTips = true;
                    elem.classList.add('shake');
                    let ShowErrorInfo = setTimeout(() => {
                        elem.classList.remove('shake');
                        /* this.errorTips = false; */
                    }, 2000);
                } else if (passWord.length < 6) {
                    elem = document.getElementsByClassName('form_panel')[0];
                    this.error_info = '密码至少六位!';
                    this.errorTips = true;
                    elem.classList.add('shake');
                    let ShowErrorInfo = setTimeout(() => {
                        elem.classList.remove('shake');
                        /* this.errorTips = false; */
                    }, 2000);
                } else if (this.ispasswordok != passWord) {
                    elem = document.getElementsByClassName('form_panel')[0];
                    this.error_info = '两次密码不一致!';
                    this.errorTips = true;
                    elem.classList.add('shake');
                    let ShowErrorInfo = setTimeout(() => {
                        elem.classList.remove('shake');
                        /* this.errorTips = false; */
                    }, 2000);
                } else if (!lawCheck) {
                    this.error_info = '请先阅读并同意各项条款!';
                    this.errorTips = true;
                    document.getElementById('law').classList.add('shake');
                    let ShowErrorInfo = setTimeout(() => {
                        document.getElementById('law').classList.remove('shake');
                        /* this.errorTips = false; */
                    }, 2000);
                } else {
                    /* 注册功能 */
                    /* console.log(userEmail,passWord); */
                }
            }
        },
        changeRegisterType(elemId) {
            var elem = document.getElementById(elemId);
            /* console.log(elemId,elem.children); */
            if (elem.getAttribute('class').indexOf('active') > -1) {
                //已存在，不变化
            } else {
                for (var i = 0; i < document.getElementsByClassName('change_btn_box')[0].children.length; i++) {
                    document.getElementsByClassName('change_btn_box')[0].children[i].className = '';
                }
                elem.className = 'active';
                this.regis_type = elemId;
            }
        },
    },
};
</script>

<style scoped>
.background {
    background-image: url('@/assets/img/EasyToGoRegistrBG.jpg');
    background-position: center;
    background-size: cover;
    height: 100vh;
}
.logo_box {
    width: 100%;
    height: 80px;
    /* margin-top: 110px; */
    margin-bottom: 50px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.logo_box img {
    height: 100%;
}
.log_panel {
    background-color: white;
    min-height: 300px;
    min-width: 300px;
    width: calc(450px + (8px * 2));
    margin-left: auto;
    margin-right: auto;
    border-radius: 24px;
    box-sizing: border-box;
    padding: 40px;
    display: flex;
    flex-direction: column;
    text-align: center;
}

.change_btn_box {
    margin: 10px 8px 0;
    height: 40px;
    line-height: 40px;
    /* justify-content: center; */
    flex-direction: row;
    display: flex;
    background-color: var(--Background-type-small);
    border-radius: 8px;
    padding: 5px 5px;
}
.change_btn_box > div {
    width: 50%;
    height: 100%;
    border-radius: 7px;
    cursor: pointer;
}
.change_btn_box > .active {
    background-color: white;
}

.error_panel {
    margin: 0 10px;
    border: 1px solid red;
    border-radius: 6px;
    padding: 5px 0;
    font-weight: 700;
}

.form_panel {
    display: flex;
    margin: 10px;
    border-radius: 0.85rem;
    border: 2px solid #d3d1d1;

    align-items: center;
}
.left_panel {
    width: 35px;
    margin: 0 8px;
}
.left_panel img {
    width: 35px;
}
.right_panel {
    /* width: 100%; */
}
.line_group {
    padding: 10px 0px 5px;
    align-items: center;
}

.input_title {
    text-align: left;
    min-width: 100px;
}

.law {
    margin: 2px 0px 15px;
}
</style>

<!--
 * @Date: 2023-12-31 15:09:14
 * @LastEditTime: 2023-12-31 15:52:26
 * @FilePath: \easy-to-go\src\views\console\userInfo\userInfoController.vue
-->
<script setup>
import request from '../../../utils/request';
</script>

<template>
    <div class="panel">
        <el-table :data="userInfo" style="width: 100%">
            <el-table-column prop="uuid" label="ID" width="180" />
            <el-table-column prop="username" label="用户名" width="180" />
            <el-table-column prop="registerTime" label="注册时间" />
            <el-table-column prop="email" label="邮箱" />
            <el-table-column prop="password" label="密码" />
            <el-table-column prop="emailActived" label="激活状态" />
        </el-table>
    </div>
</template>

<script>
export default {
    data() {
        return {
            userInfo: '',
        }
    },
    mounted() {
        request.post("/selectAllUserInfo").then(res => {
            this.userInfo = res.data.data
        })
    },
};
</script>

<style scoped>
.panel{
    padding: 40px 20px 20px 20px;
}
</style>

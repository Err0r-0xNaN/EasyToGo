<!--
 * @Date: 2023-12-31 14:49:19
 * @LastEditTime: 2023-12-31 15:06:40
 * @FilePath: \easy-to-go\src\views\console\dashboard.vue
-->
<script setup>
import request from '../../utils/request';
</script>

<template>
    <div class="panel">
        <el-row :gutter="12">
            <el-col :span="6">
                <el-card shadow="always">
                    <template #header>
                    <div class="card-header">
                        <h3>用户人数</h3>
                        <el-button class="button" text>查看详情</el-button>
                    </div>
                </template>
                <h2>{{ userSum }}</h2>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
    data(){
        return{
            userSum: '-',
        }
    },
    mounted(){
        /* request.get('/selectUserSum').then(res => {
            this.userSum = res.data.data;
        }) */
    }
}
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.panel {
    padding: 20px;
}
</style>

<!--
 * @Date: 2023-12-31 17:30:11
 * @LastEditTime: 2023-12-31 17:42:33
 * @FilePath: \easy-to-go\src\views\public\user\me.vue
-->
<script setup>
import navVue from '../../../components/nav.vue';
import request from '../../../utils/request';
</script>

<template>
    <navVue></navVue>
    <div class="panel">
        
    </div>
</template>

<script>
export default {
    data() {
        return {
            
        }
    },
    methods: {

    },
    mounted() {
        request.get()
    },
};
</script>

<style scoped>

</style>

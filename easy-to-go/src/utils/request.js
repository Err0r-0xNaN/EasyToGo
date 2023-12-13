/*
 * @Date: 2023-12-12 08:31:33
 * @LastEditTime: 2023-12-13 20:31:12
 * @FilePath: \easy-to-go\src\utils\request.js
 */
import axios from 'axios';

/* const { default: axios } = require('axios'); */

const request = axios.create({
    baseURL: 'http://localhost:9090/',
    timeout: 30000,
});

request.interceptors.request.use(
    config => {
        config.headers['Content-Type'] = 'application/json;charset=utf-8';
        return config;
    },
    error => {
        console.error('request error: ' + error);
        return Promise.reject(error);
    }
);

request.interceptors.response.use(
    response => {
        let res = response.data;

        if (typeof res == 'string') {
            res = res ? JSON.parse(res) : res;
        }
        return res;
    },
    error => {
        console.error('request error: ' + error);
        return Promise.reject(error);
    }
);

export default request

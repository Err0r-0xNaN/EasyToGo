/*
 * @Date: 2023-12-15 22:42:42
 * @LastEditTime: 2023-12-31 11:28:37
 * @FilePath: \easy-to-go\src\utils\request.js
 */
import axios from 'axios';

import router from '@/router';

import Message from '../components/tips/tips';

/* const baseURL = 'http://localhost:9090' */

const request = axios.create({
    baseURL: 'http://localhost:9090',
    timeout: 30000
})

request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=UTF-8';
    let user = JSON.parse(localStorage.getItem("user-data") || '{}');
    config.headers['token'] = user.token;

    return config
}, error => {
    console.error('request-error: ' + error)
    return Promise.reject(error)
})

request.interceptors.response.use( response => {
    let res = response;
    if(typeof res === 'string'){
        res = res ? JSON.parse(res) : res
    }
    if(res.data.statusCode === '401'){
        Message({type: 'warn', text: "请先登录!"});
        router.push('/login');
    }
    return res;
}, error => {
    Message({ type: 'error', text: error });
    console.error('request-error: ' + error)
    return Promise.reject(error)
})

export default request

/* export function requestGet(url, params = {}){
    return axios.get(baseURL + url, {
        params,
        headers:{
            'Content-Type': 'application/json',
            'token': JSON.parse(localStorage.getItem("user-data").token || '{}')
        }
    })
}

export function requestPost(url, data = {}){
    return axios.post(baseURL + url, data, {
        headers: {
            'Content-Type': 'application/json',
            'token': JSON.parse(localStorage.getItem("user-data").token || '{}')
        }
    })
}
 */
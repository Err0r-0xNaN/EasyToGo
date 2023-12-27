import axios from 'axios';

const baseURL = 'http://localhost:9090'

export function requestGet(url, params = {}){
    return axios.get(baseURL + url, {
        params,
        headers:{
            'Content-Type': 'application/json'
        }
    })
}

export function requestPost(url, data = {}){
    return axios.post(baseURL + url, data, {
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

import axios from 'axios';

export const client = axios.create({
    // baseUrl: "http://localhost:8085"
    baseURL: 'http://localhost:8085'
})
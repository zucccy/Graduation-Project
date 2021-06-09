import axios from 'axios';

const service = axios.create({
    // process.env.NODE_ENV === 'development' 来判断是否开发环境
    // easy-mock服务挂了，暂时不使用了
    // baseURL: 'https://www.easy-mock.com/mock/592501a391470c0ac1fab128',
    timeout: 5000,
    baseURL: 'http://localhost:8088',
});

service.interceptors.request.use(
    config => {
        return config;
    },
    error => {
        console.log(error);
        return null;
    }
);

service.interceptors.response.use(
    response => {
        if (response.data.code === 200) {
            return response.data;
        } else {
            return response.data;
        }
    },
    error => {
        console.log(error);
        return null;
    }
);

export default service;

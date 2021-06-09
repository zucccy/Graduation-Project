import request from '../utils/request';

export const fetchData = query => {
    return request({
        url: '/hospital/getAllHospitals/',
        method: 'get',
        params: query
    });
};

export const deleteData = query1 => {
    return request({
        url: '/hospital/delete',
        method: 'delete',
        params: query1
    });
};

export const updateData = query2 => {
    return request({
        url: '/hospital/update',
        method: 'post',
        data: query2,
    });
};

export const insertData = query3 => {
    return request({
        url: '/hospital/insert',
        method: 'post',
        data: query3,
    });
};

export const login = loginForm => {
    return request({
        url: '/user/adminLogin',
        method: 'post',
        data: loginForm,
    });
};

export const fetchOfficeData = query => {
    return request({
        url: '/office/getAllOffices/',
        method: 'get',
        params: query
    });
};

export const deleteOfficeData = query1 => {
    return request({
        url: '/office/delete',
        method: 'delete',
        params: query1
    });
};

export const updateOfficeData = query2 => {
    return request({
        url: '/office/update',
        method: 'post',
        data: query2,
    });
};

export const insertOfficeData = query3 => {
    return request({
        url: '/office/insert',
        method: 'post',
        data: query3,
    });
};

export const fetchDoctorData = query => {
    return request({
        url: '/doctor/getAllDoctors/',
        method: 'get',
        params: query
    });
};

export const deleteDoctorData = query1 => {
    return request({
        url: '/doctor/delete',
        method: 'delete',
        params: query1
    });
};

export const updateDoctorData = query2 => {
    return request({
        url: '/doctor/update',
        method: 'post',
        data: query2,
    });
};

export const insertDoctorData = query3 => {
    return request({
        url: '/doctor/insert',
        method: 'post',
        data: query3,
    });
};

export const fetchUserData = query => {
    return request({
        url: '/user/getAllUsers',
        method: 'get',
        params: query
    });
};

export const deleteUserData = query1 => {
    return request({
        url: '/user/delete',
        method: 'delete',
        params: query1
    });
};

export const updateUserData = query2 => {
    return request({
        url: '/user/update',
        method: 'post',
        data: query2,
    });
};

export const insertUserData = query3 => {
    return request({
        url: '/user/insert',
        method: 'post',
        data: query3,
    });
};

export const updateStatus = query4 => {
    return request({
        url: '/user/disable',
        method: 'get',
        params: query4,
    });
};
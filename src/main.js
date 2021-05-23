// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import BaiduMap from 'vue-baidu-map'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import axios from "axios"
import VueChatScroll from 'vue-chat-scroll'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VueChatScroll)
Vue.prototype.$axios = axios;
// axios.defaults.baseURL = "http://localhost:8088/"
//添加请求拦截器
axios.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  if(localStorage.getItem('token')){
      config.headers.common['Authorization'] = localStorage.getItem('token');
  }
  return config;
}, function (error) {
  return Promise.reject(error);
});
//添加响应拦截器
axios.interceptors.response.use(function (response) {
  if(401==response.data.code){
    router.push({
      path:"/Login"
    })
  } 
  return response;
}, function (error) {
  return Promise.reject(error);
}); 
Vue.use(BaiduMap, {
  // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
  ak: '1Ca6jd5UsljhMG8mp0GbXgbAp15plGYh'
})


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})

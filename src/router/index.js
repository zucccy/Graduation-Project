import Vue from 'vue'
import Router from 'vue-router'
import Index from '../components/Index'
import Appointment from "../components/PersonalInfo"
import Login from "../components/Login"
import Register from "../components/Register"
import DoctorInfo from "../components/DoctorInfo"
import HospitalInfo from "../components/HospitalInfo"
import DoctorAppointment from "../components/DoctorAppointment"
import DoctorWork from "../components/DoctorWork"
import SubmitAppointment from "../components/SubmitAppointment"
import test from "../components/test"
import AddPatient from "../components/AddPatient.vue"
import DeletePatient from "../components/DeletePatient.vue"
import UpdatePatient from "../components/UpdatePatient.vue"
import AppointmentInfo from "../components/AppointmentInfo.vue"

Vue.use(Router)


//获取原型对象上的push函数
const originalPush = Router.prototype.push
//修改原型对象中的push方法
Router.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,
    },   
    {
      path:"/Appointment",
      name:"Appointment",
      component:Appointment,
    },
    {
      path:"/AppointmentInfo",
      name:"AppointmentInfo",
      component:AppointmentInfo,
    },
    {
      path:"/login",
      name:"login",
      component:Login,
    },
    {
      path:"/register",
      name:"register",
      component:Register,
    },
    {
      path:"/doctorInfo",
      name:"doctorInfo",
      component:DoctorInfo,
    },
    {
      path:"/hospitalInfo",
      name:"hospitalInfo",
      component:HospitalInfo,
    },
    {
      path:"/doctorAppointment",
      name:"doctorAppointment",
      component:DoctorAppointment,
    },
    {
      path:"/doctorWork",
      name:"doctorWork",
      component:DoctorWork,
    },
    {
      path:"/submitAppointment",
      name:"submitAppointment",
      component:SubmitAppointment,
    },
    {
      path:"/test",
      name:"test",
      component:test,
    },
    {
      path:"/addPatient",
      name:"addPatient",
      component:AddPatient,
    },
    {
      path:"/deletePatient",
      name:"deletePatient",
      component:DeletePatient,
    },
    {
      path:"/updatePatient",
      name:"updatePatient",
      component:UpdatePatient,
    },
  ]
})

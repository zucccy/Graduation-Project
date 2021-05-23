<template>
  <div>
    <section class="topBar">
      <div class="wrap">
        <div class="topLogo">
          <img src="../assets/hospital.png" alt="" />
          <a @click="toIndex()">智慧医疗系统</a>
        </div>
        <div class="top">
          <div class="topLogin" :style="token === null ? '' : 'display:none'">
            <a href="" @click="login()">登录</a>
            <span>/</span>
            <a href="" @click="register()">注册</a>
          </div>
          <div class="topUser" :style="token !== null ? '' : 'display:none'">
            <img src="../assets/user.png" />
            <el-dropdown size="medium">
              <span class="el-dropdown-link"
                >{{ userName }}<i class="el-icon-arrow-down el-icon--right"></i
              ></span>
              <el-dropdown-menu class="for-user" slot="dropdown">
                <el-dropdown-item
                  :style="roleType === 1 ? '' : 'display:none'"
                  index="/Appointment"
                  @click.native="setMenuClicked()"
                  >我的预约</el-dropdown-item
                >
                <el-dropdown-item
                  index="/Appointment"
                  :style="roleType === 2 ? '' : 'display:none'"
                  @click.native="ToDoctorWork()"
                  >预约就诊人管理</el-dropdown-item
                >
                <el-dropdown-item @click.native="exit()">退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
        <div class="topSearch">
          <el-input
            placeholder="请输入医院名/医生名/科室"
            v-model="content"
            class="input-with-select"
          >
            <el-button slot="append" icon="el-icon-search" @click="search()">搜索</el-button>
          </el-input>
        </div>
        <div class="topLink">
          <a href="" @click="doctorFind()">找医生</a>
          <a href="" @click="hospitalFind()">找医院</a>
        </div>
      </div>
    </section>
    <div class="wrapsmall">
      <!-- <section class="serviceInfo"></section> -->
      <section class="block myNav">
        <div class="user">
          <img src="../assets/user.png" alt="" />
          <p>{{ userInfo.userName }}</p>
        </div>
        <div class="menu">
          <el-row class="tac">
            <el-col :span="18">
              <el-menu
                :default-active="menuItem"
                class="setMenu"
                active-text-color="#ffd04b"
                @select="selectMenu"
              >
                <el-menu-item index="1">
                  <i class="el-icon-location"></i>
                  <span slot="title">我的预约</span>
                </el-menu-item>
                <el-menu-item index="2">
                  <i class="el-icon-menu"></i>
                  <span slot="title">就诊人管理</span>
                </el-menu-item>
                <el-menu-item index="3">
                  <i class="el-icon-setting"></i>
                  <span slot="title">账号设置</span>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-row>
        </div>
        <div class="tips">
          <span>就诊小贴士</span>
          <p>
            请带上相关就诊资料（身份证、医保卡、就诊卡、通知短信等），按就诊时间到医院咨询处领号就诊
          </p>
        </div>
      </section>
      <section class="block myContent" v-if="menuItem == 1">
        <div class="title">我的预约</div>
        <table class="appointmenttable">
          <tbody>
            <tr v-for="(item, index) in appointmentList" :key="index">
              <td class="status refuse">
                预约状态：<i class="appStatus"
                  >{{ visitStatus.boolean[item.visitStatus] }}
                </i>
                <el-button
                  :style="
                    visitStatus.boolean[item.visitStatus] === '已退号' 
                    || visitStatus.boolean[item.visitStatus] === '已完成'
                      ? 'display:none'
                      : ''
                  "
                  type="danger"
                  plain=""
                  @click="withdrawal(item.id)"
                  >退号</el-button
                >
                <br />
                <i>就诊时间：{{ item.timePeriod }}</i>
              </td>
              <td class="address">
                <a class="hospital" href="">{{ item.hospitalName }}</a>
                <!-- <i
                  class="el-icon-map-location"
                  @click="ToHospitalInfo(item.hospitalId)"
                  >查看地图</i
                > -->
                <br />
                <a href="">{{ item.officeName }}</a>
                <a href="">{{ item.doctorName }} 医生</a>
              </td>
              <td class="patient">
                <i
                  >就诊人：{{ item.patientName }}<br />手机号：{{
                    item.phone
                  }}</i
                >
              </td>
              <td class="operation">
                <el-button type="success" plain="" @click="toInfo(item.id)"
                  >查看详情</el-button
                >
              </td>
            </tr>
          </tbody>
        </table>
      </section>
      <section class="block myContent" v-if="menuItem == 2">
        <div class="title">就诊人管理</div>
        <div class="myTips">最多可保存十个就诊人信息</div>
        <div>
          <el-radio-group v-model="radio1" fill="rgba(217, 217, 217, 0.4)">
            <el-radio-button
              :label="index"
              v-for="(item, index) in patientList"
              :key="index"
            >
              <div class="pybutton">
                <span>
                  <strong>{{ item.patientName }}</strong>
                  <em class="edit" @click="updatePatient(item)">修改</em>
                </span>
                <p>{{ item.idCard }}</p>
                <p>{{ item.phone }}</p>
                <em class="delete" @click="deletePatient(item)">删除</em>
              </div>
            </el-radio-button>
          </el-radio-group>
          <AddPatient
            :title="'新增就诊人'"
            :visible="addPatientVisible"
            @close="addPatientVisible = false"
            @rereload="updateList()"
          ></AddPatient>
          <UpdatePatient
            :title="'修改就诊人'"
            :visible="UpdatePatientVisible"
            @close="UpdatePatientVisible = false"
            @rereload="updateList()"
            :id="this.patientId"
            :idCard="this.idCard"
            :patientName="this.patientName"
            :phone="this.phone"
            v-if="update"
          ></UpdatePatient>
          <DeletePatient
            :title="'删除就诊人'"
            :visible="DeletePatientVisible"
            @close="DeletePatientVisible = false"
            @rereload="updateList()"
            :patientName="this.patientName"
            :id="this.patientId"
          ></DeletePatient>
          <div class="add" @click="addPatientVisible = true">新增就诊人</div>
        </div>
      </section>
      <section class="block myContent" v-if="menuItem == 3">
        <div class="title">账号设置</div>
        <div class="tabnav onlytab">
          <el-tabs v-model="activeName">
            <el-tab-pane
              label="基本资料"
              name="basic"
              class="pane"
              style="color: green"
            ></el-tab-pane>
            <el-tab-pane
              label="修改密码"
              name="updatePassword"
              class="pane"
            ></el-tab-pane>
          </el-tabs>
        </div>
        <div class="content" v-if="activeName === 'basic'">
          <div class="inline">
            <span>用户名：</span
            ><el-input v-model="userName" placeholder="请输入用户名"></el-input>
          </div>
          <div class="inline">
            <span>手机号：</span>
            <el-input v-model="userPhone" placeholder="请输入手机号"></el-input>
          </div>
          <div class="inline">
            <span>邮箱：</span>
            <el-input v-model="email" placeholder="请输入邮箱"></el-input>
          </div>
          <div class="save">
            <el-button type="success" @click="updateUser()">保存</el-button>
          </div>
        </div>
        <div class="content" v-if="activeName === 'updatePassword'">
          <div class="inline">
            <span>当前密码：</span
            ><el-input
              v-model="rowPassword"
              placeholder="请输入当前密码"
              show-password
            ></el-input>
          </div>
          <div class="inline">
            <span>新密码：</span>
            <el-input v-model="newPassword" placeholder="请输入新密码" show-password></el-input>
          </div>
          <div class="save">
            <el-button type="success" @click="updatePassword()">保存</el-button>
          </div>
        </div>
      </section>
      <div class="clear"></div>
      <div class="space"></div>
    </div>
  </div>
</template>

<script>
import MyAppointment from "./MyAppointment";
import AddPatient from "./AddPatient";
import UpdatePatient from "./UpdatePatient";
import DeletePatient from "./DeletePatient";

export default {
  inject: ["reload"],
  name: "Appointment",
  components: {
    MyAppointment,
    AddPatient,
    UpdatePatient,
    DeletePatient,
  },
  data() {
    return {
      newPassword:"",
      rowPassword:"",
      update: true,
      phone: "",
      idCard: "",
      patientId: "",
      patientName: "",
      token: localStorage.getItem("token"),
      activeName: "basic",
      userPhone: "",
      email: "",
      radio1: "上海",
      content: "",
      visitStatus: { boolean: { 1: "已预约", 2: "已退号", 3: "已完成" } },
      menuItem: 1,
      tableData: [],
      sex: "1",
      birthday: "",
      addPatientVisible: false,
      UpdatePatientVisible: false,
      DeletePatientVisible: false,
      patientList: [],
      sPatient: "",
      status: {
        doctor: "doctor",
        hospital: "hospital",
      },
      appointmentList: [],
      userInfo: "",
      userName: "",
      roleType: "",
    };
  },
  methods: {
    search() {
      this.$router.push({
        name:"doctorInfo",
        query:{
          flag: 1,
          hospitalName: this.content,
          doctorName: this.content,
          officeName: this.content,
          status: "doctor",
        }
      })
    },
    updatePassword() {
      if (this.rowPassword != "" && this.newPassword != "") {
        var param = {
          rowPassword: this.rowPassword,
          newPassword: this.newPassword,
        };
        this.$axios
          .post("http://localhost:8088/user/password", param)
          .then((res) => {
            if (200 == res.data.code) {
              console.log(res);
              this.$message.success("保存成功！");
            } else if (404 == res.data.code) {
              this.$alert(res.data.msg);
            } else if (400 == res.data.code) {
              this.$alert(res.data.msg);
            }
          });
      } else {
        this.$message.warning("必填信息未填写完毕！");
      }
    },
    updateUser(){
      if (this.userName != "") {
        var param = {
          userName: this.userName,
          email: this.email,
          phone: this.userPhone,
        };
        this.$axios
          .post("http://localhost:8088/user/updateUserInfo", param)
          .then((res) => {
            if (200 == res.data.code) {
              console.log(res);
              this.$message.success("保存成功！");
            } else if (404 == res.data.code) {
              this.$alert(res.data.msg);
            } else if (400 == res.data.code) {
              this.$alert(res.data.msg);
            }
          });
      } else {
        this.$message.warning("必填信息未填写完毕！");
      }
    },
    updatePatient(val) {
      console.log(val);
      this.UpdatePatientVisible = true;
      this.patientName = val.patientName;
      this.patientId = val.id;
      this.idCard = val.idCard;
      this.phone = val.phone;
      this.update = false;
      // 在组件移除后，重新渲染组件
      // this.$nextTick可实现在DOM 状态更新后，执行传入的方法。
      this.$nextTick(() => {
        this.update = true;
      });
    },
    deletePatient(val) {
      console.log(val);
      this.DeletePatientVisible = true;
      this.patientId = val.id;
      this.patientName = val.patientName;
    },
    ToHospitalInfo(val) {
      console.log(val);
      this.$router.push({
        name: "hospitalInfo",
        query: {
          id: val,
          activeName: "address",
        },
      });
    },
    rereload() {
      this.menuItem = "2";
      this.reload();
    },
    updateList() {
      this.getPatient();
    },
    exit() {
      localStorage.removeItem("token");
      localStorage.removeItem("userInfo");
      this.reload();
      this.$router.push("/");
    },
    selectMenu(key, keyPath) {
      this.menuItem = key;
    },
    toInfo(val) {
      this.$router.push({
        name: "AppointmentInfo",
        query: { id: val },
      });
    },
    withdrawal(val) {
      var param = {
        id: val,
        visitStatus: 2,
      };
      this.$axios.post("http://localhost:8088/appointment/updateVisitStatus/", param).then((res) => {
        if (200 == res.status) {
          console.log(res);
          this.getAppointmentList();
        }
      });
    },
    getPatient() {
      // var param = { address: "杭州", hospitalName: null, pageNum: 1, pageSize: 10};
      this.$axios
        .get("http://localhost:8088/user/getMyPatients", {
          params: {},
        })
        .then((res) => {
          if (200 == res.status) {
            console.log(res);
            this.patientList = res.data.data;
            console.log(this.patientList);
          }
        });
    },
    toIndex() {
      this.$router.push("/");
    },
    login() {
      //保存当前路由
      localStorage.setItem("preRoute", this.$route.fullPath);
      this.$router.push("/Login");
    },
    register() {
      this.$router.push("Register");
    },
    hospitalFind() {
      var activeName = this.status.hospital;
      this.$router.push({
        name: "doctorInfo",
        query: { status: activeName },
      });
    },
    doctorFind() {
      var activeName = this.status.doctor;
      this.$router.push({
        name: "doctorInfo",
        query: { status: activeName },
      });
    },
    getAppointmentList() {
      this.$axios
        .get("http://localhost:8088/user/getMyAppointments", {
          params: {},
        })
        .then((res) => {
          if (200 == res.status) {
            console.log(res);
            this.appointmentList = res.data.data;
            console.log(this.appointmentList);
          }
        });
    },
    setMenuClicked() {
      this.$router.push("/Appointment");
    },
    ToDoctorWork() {
      this.$router.push("/DoctorWork");
    },
  },
  mounted() {
    this.$nextTick(() => {
      this.getAppointmentList();
      this.getPatient();
      this.userInfo = JSON.parse(localStorage.getItem("userInfo"));
      if (this.userInfo != null) {
        this.userName = JSON.parse(localStorage.getItem("userInfo")).userName;
        this.roleType = JSON.parse(localStorage.getItem("userInfo")).roleType;
        this.userPhone = JSON.parse(localStorage.getItem("userInfo")).phone;
        this.email = JSON.parse(localStorage.getItem("userInfo")).email;
      }
      console.log(this.userInfo);
    });
  },
  watch: {
    patientList: function () {
      console.log("changed");
    },
  },
};
</script>

<style scoped>
.topBar {
  width: 100%;
  min-width: 1200px;
  height: 55px;
  line-height: 55px;
  color: #fff;
  background: #74bc31;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 900;
}
.wrap {
  width: 1200px;
  margin: 0 auto;
}
.appStatus {
  margin-right: 10px;
}
.wrapsmall {
  width: 1200px;
  margin: 0 auto;
  margin-top: 45px;
}
.topBar .topSevers {
  float: left;
  width: 80px;
  position: relative;
  cursor: pointer;
}
.el-dropdown-link {
  cursor: pointer;
  color: #ffffff;
}
.el-dropdown {
  bottom: 56px;
  left: 40px;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.el-avatar {
  margin-top: 7px;
}
.topLogo {
  float: left;
  width: 250px;
  height: 55px;
}
.topLogo img {
  height: 50px;
  float: left;
}
.topLogo a {
  float: left;
  padding-left: 20px;
  font-weight: 700;
  font-size: 25px;
  color: white;
  text-decoration: none;
  cursor: pointer;
}
.topLogin {
  float: right;
  width: 120px;
  text-align: right;
}
.topLogin a {
  text-decoration: none;
  color: white;
}
.topLogin a:hover {
  border-bottom: white 1px solid;
}
.topUser {
  float: right;
  width: 100px;
  height: 55px;
  padding-right: 5px;
  position: relative;
  cursor: pointer;
}
.topUser img {
  width: 29px;
  height: 29px;
  vertical-align: middle;
  margin-right: 5px;
  border-radius: 100%;
}

.el-avatar {
  margin-top: 14px;
  margin-right: 5px;
  vertical-align: top;
}
.topSearch {
  float: right;
  margin-right: 20px;
  position: relative;
  width: 500px;
}
.topLink {
  float: right;
  font-size: 20px;
  color: #f0f0f0;
  margin-right: 25px;
}
.topLink a {
  color: #fff;
  margin-right: 40px;
  text-decoration: none;
}
.pybutton span {
  display: block;
  line-height: 55px;
  padding: 0 10px;
  margin-bottom: 10px;
  border-bottom: #f0f0f0 1px solid;
}
.pybutton strong {
  font-size: 16px;
  font-weight: 400;
  color: #333;
  display: inline-block;
  vertical-align: top;
  max-width: 65px;
  margin-right: 12px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.pybutton p {
  padding: 0 10px;
  color: #333;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.topLink a:hover {
  border-bottom: white 1px solid;
}

.serviceInfo {
  line-height: 30px;
  margin-top: 20px;
  color: #666;
}
.block {
  background: #fff;
  border-radius: 5px;
  margin-top: 20px;
  padding: 0 20px 40px;
  position: relative;
}
.title {
  font-size: 24px;
  font-weight: 700;
  color: #74bc31;
  height: 64px;
  line-height: 64px;
  border-bottom: #f0f0f0 1px solid;
}
.title .tabnav {
  display: inline-block;
  vertical-align: top;
  font-size: 16px;
  color: #333;
}
.title .tabnav .myAttention {
  padding-left: 15px;
  margin-left: 20px;
}
.title .tabnav .pane {
  background-color: orange;
}
.el-tabs__item {
  font-size: 30px;
}
.title span {
  float: left;
}
.myNav {
  float: left;
  width: 200px;
  height: 600px;
  padding: 0;
  position: relative;
}
.user {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.myNav .user img {
  width: 80px;
  height: 80px;
  border-radius: 100%;
  margin-top: 30px;
}
.myNav .user p {
  height: 20px;
  font-size: 16px;
  color: #333;
  line-height: 10px;
  padding: 12px 20px 0;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.myNav .tips {
  padding: 0 20px;
  line-height: 24px;
  width: 160px;
  position: absolute;
  left: 0;
  bottom: 34px;
  color: #999;
}
.myNav .tips span {
  display: block;
  margin-bottom: 8px;
  font-weight: 700;
  font-size: 18px;
}
.myNav .tips p {
  font-size: 14px;
}
.myContent {
  float: right;
  width: 940px;
  min-height: 750px;
}
.menu {
  line-height: 59px;
  margin-top: 45px;
  border-top: #f0f0f0 1px solid;
}
.appointmenttable {
  width: 100%;
  line-height: 34px;
  color: #333;
  font-size: 14px;
}
tbody {
  display: table-row-group;
  vertical-align: middle;
  border-color: inherit;
}
tr {
  display: table-row;
  vertical-align: inherit;
  border-color: inherit;
}
.appointmenttable .status {
  width: 325px;
}
.appointmenttable td {
  padding: 29px 0 30px;
  border-bottom: #f0f0f0 1px solid;
}
.appointmenttable a {
  text-decoration: none;
  color: #333;
}
.appointmenttable i {
  color: #333;
  font-weight: 400;
  font-style: normal;
}
.appointmenttable .address .hospital {
  display: inline-block;
  max-width: 240px;
  vertical-align: top;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.appointmenttable .address .el-icon-map-location {
  display: inline-block;
  color: #74bc31;
  vertical-align: center;
  padding-left: 20px;
  margin-left: 10px;
  cursor: pointer;
}
.appointmenttable .address .el-icon-map-location:hover {
  text-decoration: underline;
  color: orange;
}
.appointmenttable .patient {
  width: 200px;
}
.appointmenttable .operation {
  width: 80px;
}
.space {
  margin-top: 20px;
  overflow: hidden;
  position: relative;
}
.myTips {
  color: #666;
  background: #f8f8f8;
  border-radius: 5px;
  padding: 0 10px;
  margin-top: 20px;
  margin-bottom: 10px;
}
.patient {
  display: flex;
  flex-flow: row;
  padding-top: 20px;
  overflow: hidden;
  justify-content: flex-start;
}
.single {
  display: inline-block;
  width: 220px;
  margin: 0 20px 40px 0;
  height: 138px;
  border: #f0f0f0 1px solid;
  border-radius: 5px;
  cursor: pointer;
}
.single span {
  display: block;
  line-height: 55px;
  padding: 0 10px;
  margin-bottom: 10px;
  border-bottom: #f0f0f0 1px solid;
}
.single strong {
  font-size: 16px;
  font-weight: 400;
  color: #333;
  display: inline-block;
  vertical-align: top;
  max-width: 65px;
  margin-right: 12px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.edit {
  font-style: normal;
  float: right;
  color: #74bc31;
}
.edit:hover {
  color: #ff9023;
}
.single p {
  padding: 0 10px;
  color: #333;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.delete {
  display: block;
  font-style: normal;
  font-size: 16px;
  color: #666666;
  width: 78px;
  height: 28px;
  line-height: 28px;
  text-align: center;
  margin: 10px auto 0;
  border: #f0f0f0 1px solid;
  border-radius: 28px;
  font-family: "黑体";
}
.delete:hover {
  color: white;
  background-color: #ff9023;
}
.add {
  display: inline-block;
  vertical-align: middle;
  width: 208px;
  height: 90px;
  padding-top: 90px;
  font-size: 16px;
  line-height: 16px;
  color: #333;
  text-align: center;
  background: url(https://image.39.net/dist/yyk/v3/pc/images/icon_add.png)
    center 25px no-repeat;
  border: #f0f0f0 1px solid;
  border-radius: 5px;
  cursor: pointer;
}
.content {
  display: block;
  width: 100%;
}

.basic {
  height: 40px;
  line-height: 40px;
  margin: 20px 0 20px 0;
  float: left;
  font-size: 18px;
  color: #74bc31;
  width: 75px;
  border-bottom: #74bc31 2px solid;
}

.inline {
  display: inline-block;
  height: 40px;
  width: 100%;
  font-size: 16px;
  color: #666666;
  padding-top: 20px;
}

.inline span {
  width: 120px;
  position: relative;
  float: left;
}

.inline .userid {
  width: 120px;
  font-size: 16px;
  float: left;
  padding-left: 30px;
}

.inline .el-input {
  position: relative;
  float: left;
  padding-left: 30px;
  width: 300px;
}

.el-radio {
  padding-left: 30px;
}

.save {
  padding: 30px 0 0 0;
  float: left;
}
.el-col {
  width: 100%;
}
.footer {
  margin-top: 20px;
  width: 100%;
  min-width: 1200px;
  padding-bottom: 20px;
  background: #fff;
}
.footer .aboutus {
  padding: 10px 0;
  margin-bottom: 10px;
  border-bottom: #f0f0f0 1px solid;
  overflow: hidden;
}
.footer .aboutus .contact {
  display: block;
  float: left;
  padding: 0 20px;
  width: 370px;
}
.footer .aboutus .contact span {
  display: block;
  line-height: 14px;
  margin-top: 20px;
}
.footer .aboutus .contact p {
  color: #333;
  line-height: 32px;
  margin-top: 20px;
  padding-left: 37px;
  background: url(https://image.39.net/dist/yyk/v3/pc/images/icon_footerphone.png)
    left center no-repeat;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.footer .aboutus .qrCode {
  float: right;
  width: 393px;
  height: 155px;
  border-left: #f0f0f0 1px solid;
}
.footer .qrCode img {
  float: left;
  width: 116px;
  height: 116px;
  margin: 20px 20px 0 30px;
}

.footer .aboutus .qrCode strong {
  display: block;
  font-size: 16px;
  color: #333;
  font-weight: 400;
  line-height: 16px;
  margin-top: 30px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.footer .aboutus .qrCode span {
  display: block;
  line-height: 14px;
  margin-top: 20px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.footer .aboutus .qrCode p {
  line-height: 14px;
  color: #333;
  margin-top: 35px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.footer .bottomInfo {
  text-align: center;
}
.footer .bottomInfo a {
  text-decoration: none;
  color: #333;
  cursor: pointer;
}
.footer .bottomInfo a:hover {
  color: orange;
  cursor: pointer;
}
.el-tabs__item {
  color: orange !important;
}
</style>

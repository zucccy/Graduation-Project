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
            <el-button slot="append" icon="el-icon-search" @click="search()"
              >搜索</el-button
            >
          </el-input>
        </div>
        <div class="topLink">
          <a href="" @click="doctorFind()">找医生</a>
          <a href="" @click="hospitalFind()">找医院</a>
        </div>
      </div>
    </section>
    <div class="wrapsmall">
      <section class="block myNav">
        <div class="user">
          <img src="../assets/doctor.png" alt="" />
          <p>{{ userInfo.userName }}医生</p>
        </div>
      </section>
      <section class="block myContent" v-if="menuItem == 1">
        <div class="title">预约就诊人管理</div>
        <table class="appointmenttable">
          <tbody>
            <tr v-for="(item, index) in appointmentList" :key="index">
              <td class="status refuse">
                预约状态：<i class="appStatus"
                  >{{ visitStatus.boolean[item.visitStatus] }}
                </i>
                <br />
                <i>就诊时间：{{ item.timePeriod }}</i>
              </td>
              <td class="address">
                <a class="hospital" href="">{{ item.hospitalName }}</a>
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
                <el-button
                  type="primary"
                  :style="
                    item.visitStatus != 1 || item.flag == 1
                      ? 'display:none'
                      : 'display:block'
                  "
                  plain=""
                  @click="setState(index, item.id)"
                  >确定完成</el-button
                >
              </td>
            </tr>
          </tbody>
        </table>
      </section>
      <div class="clear"></div>
      <div class="space"></div>
    </div>
  </div>
</template>

<script>
import MyAppointment from "./MyAppointment";
export default {
  inject: ["reload"],
  name: "Appointment",
  comments: {
    MyAppointment,
  },
  data() {
    return {
      token: localStorage.getItem("token"),
      src: "",
      content: "",
      menuItem: "1",
      tableData: [],
      visitStatus: { boolean: { 1: "已预约", 2: "已退号", 3: "已完成" } },
      activeName: "医生",
      appointmentList: [],
      status: {
        doctor: "doctor",
        hospital: "hospital",
      },
      userName: "",
      roleType: "",
      userInfo: "",
    };
  },
  methods: {
    search() {
      this.$router.push({
        name: "doctorInfo",
        query: {
          flag: 1,
          hospitalName: this.content,
          doctorName: this.content,
          officeName: this.content,
          status: "doctor",
        },
      });
    },
    ToDoctorWork() {
      this.$router.push("/DoctorWork");
    },
    ToHospitalInfo(val) {
      this.$router.push({
        name: "hospitalInfo",
        query: {
          id: val,
          activeName: "address",
        },
      });
    },
    setMenuClicked() {
      this.$router.push("/Appointment");
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
    setState(index, id) {
      this.$confirm("是否确定完成？", "提示", {}).then(() => {
        var param = {
          id: id,
          visitStatus: 3,
        };
        this.$axios
          .post("http://localhost:8088/doctor/updateAppointmentStatus/", param)
          .then((res) => {
            if (200 == res.status) {
              console.log(res);
            }
          });
        this.appointmentList[index].visitStatus = 3;
        console.log(this.appointmentList[index].visitStatus);
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
        .get("http://localhost:8088/doctor/getAppointmentList/", {
          params: {
            doctorId: 1,
          },
        })
        .then((res) => {
          if (200 == res.status) {
            console.log(res);
            this.appointmentList = res.data.data;
            console.log(this.appointmentList);
          }
        });
    },
  },
  mounted() {
    this.$nextTick(() => {
      this.getAppointmentList();
      this.userInfo = JSON.parse(localStorage.getItem("userInfo"));
      if (this.userInfo != null) {
        this.userName = JSON.parse(localStorage.getItem("userInfo")).userName;
        this.roleType = JSON.parse(localStorage.getItem("userInfo")).roleType;
      }
    });
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
.el-dropdown {
  bottom: 56px;
  left: 40px;
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
.el-tabs__item {
  font-size: 30px;
}
.title span {
  float: left;
}
.myNav {
  float: left;
  width: 200px;
  height: 790px;
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
  font-size: 16px;
  color: #333;
  line-height: 16px;
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
  width: 195px;
}
.appointmenttable .patient i {
  width: 100px;
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
  float: right;
  color: #38abbb;
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
  width: 218px;
  height: 48px;
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
</style>

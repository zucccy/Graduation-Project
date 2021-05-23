<template>
  <div class="main">
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
              <img src="../assets/user.png">
              <el-dropdown size="medium">
                <span class="el-dropdown-link"
                  >{{userName}}<i class="el-icon-arrow-down el-icon--right"></i
                ></span>
                <el-dropdown-menu class="for-user" slot="dropdown">
                <el-dropdown-item :style="roleType === 1 ? '' : 'display:none'"
                  index="/Appointment"
                  @click.native="setMenuClicked()"
                  >我的预约</el-dropdown-item
                >
                <el-dropdown-item
                  index="/Appointment" :style="roleType === 2 ? '' : 'display:none'"
                  @click.native="ToDoctorWork()"
                  >预约就诊人管理</el-dropdown-item
                >
                  <el-dropdown-item @click.native="exit()"
                    >退出</el-dropdown-item
                  >
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
    <div class="wrap" style="margin-top: 55px">
      <section class="block">
        <div class="doctorinfo">
          <div class="doctorpic">
            <img :src="doctor.src" alt="" />
          </div>
          <div class="doctordetail">
            <h1>
              {{ doctor.doctorName }} <i>{{ doctor.position }}</i>
            </h1>
            <p class="address">
              <em>出诊地点：</em>
              <span class="foldcontent">
                <a href="">{{ doctor.hospitalName }}</a>
                <a href="">{{ doctor.officeName }}</a>
              </span>
            </p>
            <p class="goodat">
              <em>擅长领域：</em>
              <span class="foldcontent folded">
                <a class="loadmore extend" href="">[ 展开全部 ]</a
                >{{ doctor.description }}
                <br />
              </span>
            </p>
          </div>
        </div>
        <!-- <div class="tabnav onlytab">
          <el-tabs v-model="activeName">
            <el-tab-pane label="首页" name="index"></el-tab-pane>
            <el-tab-pane label="就诊评价" name="comments"></el-tab-pane>
          </el-tabs>
        </div> -->
      </section>
      <div class="mainframe">
        <section class="block doctorcalendar">
          <div class="hospitalselect">
            <div class="swiper-container swiper-container-horizontal">
              <div
                class="swiper-wrapper"
                style="
                  transition-duration: 0ms;
                  transform: translate3d(313.101px, 0px, 0px);
                "
              >
                <strong>出诊计划</strong>
              </div>
            </div>
          </div>
          <div class="calendartable">
            <div class="ampm">
              <em>时段</em>
              <em>上午</em>
              <em>下午</em>
              <em>夜诊</em>
            </div>
            <div class="muticalendar">
              <div class="dateselect">
                <div class="swiper-container swiper-container-horizontal">
                  <table cellpadding="0" cellspacing="0" border="0">
                    <thead>
                      <tr v-for="(item, index) in dateArray" :key="index">
                        <th>
                          {{ item.date }}<i>{{ item.weekday }}</i>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="(item, index) in visitPlan" :key="index">
                        <td
                          v-for="(child, index) in item.visitPlanVOAmList"
                          :key="index"
                        >
                          <i v-if="child.id != null" class="orange">
                            <!-- <el-button :plain="true" @click="open2()">预约</el-button> -->
                            <a href="" @click="toSubmitAppointment(child.id,child.doctorId,child.hospitalId)">预约</a>
                          </i>
                        </td>
                      </tr>
                      <tr v-for="(item, index) in visitPlan" :key="index">
                        <td
                          v-for="(child, index) in item.visitPlanVOPmList"
                          :key="index"
                        >
                          <i v-if="child.doctorId != null" class="orange">
                            <a href="" @click="toSubmitAppointment(child.id,child.doctorId,child.hospitalId)">预约</a>
                          </i>
                        </td>
                      </tr>
                      <tr v-for="(item, index) in visitPlan" :key="index">
                        <td
                          v-for="(child, index) in item.visitPlanVOEveList"
                          :key="index"
                        >
                          <i v-if="child.doctorId != null" class="orange">
                            <a href="" @click="toSubmitAppointment(child.id,child.doctorId,child.hospitalId)">预约</a>
                          </i>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
          <div class="explain"></div>
        </section>
      </div>
      <div class="sideframe">
        <div class="block">
          <div class="title2">
            同科室其他医生
          </div>
          <div class="sidedoctorlist" v-for="(item,index) in otherOfficeDoctor" :key="index">
            <div>
              <strong class="gua">
                <a href=""><img :src="item.src" alt=""></a>
                <span><a href="" @click="toOther(item.id, item.officeId, item.hospitalId)">{{item.doctorName}}</a></span>
                <span>{{item.position}}</span>
                <span>{{item.officeName}}</span>
                <span><p>{{item.description}}</p></span>
              </strong>
            </div>
          </div>
        </div>
      </div>
      <div class="mainframe" style="height: auto">
        <section class="block">
          <div class="title">
            <span>执业经历</span>
          </div>
          <div class="content">
            <p>
              鼻科各类疾病的诊治，特别是鼻息肉、鼻窦炎、鼻腔鼻窦良性肿瘤切除，小儿先天性鼻腔疾病，小儿鼾症等，开展的手术有：鼻内镜下视神经减压术、鼻内镜下鼻前颅底手术、鼻内镜下脑脊液鼻漏修补术、鼻内镜下脑膜脑膨出切除及颅底修补手术、鼻内镜下鼻咽闭锁成形术，鼻内镜下球后肿瘤切除术，鼻内镜下垂体肿瘤切除等鼻眼、鼻颅底相关手术。
            </p>
          </div>
        </section>
      </div>
      <div class="clear"></div>
    <section class="footer">
      <div class="aboutus">
        <div class="wrap">
          <div class="contact">
            <span>关注或联系我</span>
            <p>欢迎致电：15706774278</p>
            <p>地址：浙大城市学院</p>
          </div>
          <div class="qrCode">
            <img src="../assets/qrcode.jpg" alt="" />
            <strong>☁☁</strong>
            <span>制作者</span>
            <p>扫一扫关注制作者微信</p>
          </div>
        </div>
      </div>
    </section>
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
      userName:"",
      roleType:"",
      userInfo:"",
      token:localStorage.getItem('token'),
      otherOfficeDoctor: [],
      dateArray: [],
      doctor: "",
      content: "",
      menuItem: "1",
      visitPlan: "",
      tableData: [],
      patientList: [],
      activeName: "index",
      status: {
        doctor: "doctor",
        hospital: "hospital",
      },
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
    toOther(val1, val2, val3) {
      this.$router.push({
        name: "doctorAppointment",
        query: { 
          id: val1,
          officeId: val2,
          hospitalId: val3,
         },
      });
    },
    login() {
      //保存当前路由
      localStorage.setItem("preRoute", this.$route.fullPath)
      this.$router.push('/Login')
    },
    register() {
      this.$router.push("Register");
    },
    ToDoctorWork() {
      this.$router.push("/DoctorWork");
    },
    exit() {
      localStorage.removeItem("token");
      localStorage.removeItem("userInfo");
      this.reload();
    },
    initData() {
      //当前日期的后七天
      var myDate = new Date();
      // myDate.setDate(myDate.getDate() - 7);  //后7天就把该行代码注释
      var dateArray = [];
      var dateTemp;
      var flag = 1;
      var weekDay = ["周日", "周一", "周二", "周三", "周四", "周五", "周六"];
      for (var i = 0; i < 7; i++) {
        var object = new Object();
        var month = myDate.getMonth() + 1;
        var date = myDate.getDate();
        month < 10 ? (month = "0" + month) : month;
        date < 10 ? (date = "0" + date) : date;
        dateTemp = month + "-" + date;
        object.date = dateTemp;
        object.weekday = weekDay[myDate.getDay()];
        dateArray.push(object);
        myDate.setDate(myDate.getDate() + flag);
      }
      this.dateArray = dateArray;
      console.log(dateArray);
    },

    selectMenu(key, keyPath) {
      this.menuItem = key;
    },
    toIndex() {
      this.$router.push("/");
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
    setMenuClicked() {
      this.$router.push("/Appointment");
    },
    toSubmitAppointment(val1,val2,val3) {
      if (this.userInfo == null) {
        this.$message({
        message: '请先登录!',
      });
      //保存当前路由
      localStorage.setItem("preRoute", this.$route.fullPath)
      this.$router.push('/Login')
      } else {
          this.$router.push({path: "/SubmitAppointment", query: {id: val1, doctorId: val2, hospitalId: val3}});
      }
    },
    getDoctor() {
      // var param = { address: "杭州", hospitalName: null, pageNum: 1, pageSize: 10};
      this.$axios
        .get("http://localhost:8088/doctor/id", {
          params: {
            id: this.$route.query.id,
          },
        })
        .then((res) => {
          if (200 == res.status) {
            console.log(res);
            this.doctor = res.data.data;
            console.log(this.doctor);
          }
        });
    },
    getOtherDoctor() {
      // var param = { address: "杭州", hospitalName: null, pageNum: 1, pageSize: 10};
      this.$axios
        .get("http://localhost:8088/doctor/getDoctorsByOfficeId/", {
          params: {
            officeId: this.$route.query.officeId,
            id: this.$route.query.id,
            hospitalId: this.$route.query.hospitalId,
          },
        })
        .then((res) => {
          if (200 == res.status) {
            console.log(res);
            this.otherOfficeDoctor = res.data.data;
            console.log(this.otherOfficeDoctor);
          }
        });
    },
    getVisitPlan() {
      // var param = { address: "杭州", hospitalName: null, pageNum: 1, pageSize: 10};
      this.$axios
        .get("http://localhost:8088/doctor/getDoctorPlan/", {
          params: {
            doctorId: this.$route.query.id,
          },
        })
        .then((res) => {
          if (200 == res.status) {
            console.log(res);
            this.visitPlan = res.data.data;
            console.log(this.visitPlan);
          }
        });
    },
  },
  mounted() {
    this.$nextTick(() => {
      this.getDoctor();
      this.getVisitPlan();
      this.initData();
      this.getOtherDoctor();
      console.log(this.$route.query.id);
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
.main {
  width: 100%;
  height: auto;
}
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
.topBar .topSevers {
  float: left;
  width: 80px;
  position: relative;
  cursor: pointer;
}
.el-dropdown {
  bottom: 56px;
  left: 40px;
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
.foldcontent a{
  margin-right: 5px;
}
.el-avatar {
  margin-top: 14px;
  margin-right: 5px;
  vertical-align:top;
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
.block {
  background: white;
  border-radius: 5px;
  margin-top: 20px;
  padding: 0 20px;
  position: relative;
  top: 15px;
  margin-bottom: 15px;
}
.doctorinfo {
  padding: 20px;
  overflow: hidden;
}
.mainframe {
  height: 400px;
}
.doctorinfo .doctorpic {
  width: 140px;
  float: left;
}
.doctorinfo .doctorpic img {
  width: 140px;
  height: 175px;
  border-radius: 5px;
}
.doctorinfo .doctordetail {
  width: 955px;
  float: right;
  line-height: 24px;
}
.follow {
  float: right;
}
.feedback {
  padding-left: 16px;
  background: url(https://image.39.net/dist/yyk/v3/pc/images/icon_infopencil.png)
    left center no-repeat;
}
.unfollow {
  float: right;
  margin-left: 10px;
  width: 78px;
  height: 28px;
}
.followbutton {
  padding: 0;
  margin: 0;
  width: 78px;
  height: 28px;
  line-height: 26px;
  text-align: center;
  background-color: white;
  border: #ff9023 1px solid;
  color: #ff9023;
  border-radius: 26px;
  overflow: hidden;
}
.doctordetail h1 {
  font-size: 24px;
  font-weight: 400;
  color: #333;
  margin: 10px 0 5px;
}
.doctorinfo .doctordetail h1 i {
  font-size: 16px;
  color: #999;
  margin-left: 20px;
}
.doctorinfo .doctordetail p {
  margin-top: 10px;
  overflow: hidden;
}
.doctorinfo .doctordetail p em {
  color: #666;
  padding-left: 25px;
  background: url(https://image.39.net/dist/yyk/v3/pc/images/icon_hospitalintro.png)
    left center no-repeat;
}
.doctorinfo .doctordetail p.address em {
  background-image: url(https://image.39.net/dist/yyk/v3/pc/images/icon_hospitallocation.png);
}

.doctordetail p.experience em {
  background-image: url(https://image.39.net/dist/yyk/v3/pc/images/icon_infocertificate.png);
}
.doctorinfo .doctordetail p span {
  color: #333;
  float: right;
  width: 840px;
}
a {
  text-decoration: none;
  color: #333;
}
.doctorinfo .doctordetail p span i {
  color: #999;
  margin-left: 20px;
}
.doctorinfo .doctordetail p span i a {
  color: #999;
}
.doctorinfo .doctordetail p.goodat .folded {
  max-height: 24px;
  overflow: hidden;
  text-overflow: ellipsis;
}
.doctorinfo .doctordetail p.experience .folded {
  max-height: 24px;
  overflow: hidden;
  text-overflow: ellipsis;
}
.doctorinfo .doctordetail p.goodat .extend {
  float: right;
}
.doctorinfo .doctordetail p.experience .extend {
  float: right;
}
.doctorinfo .doctordetail p span a.loadmore {
  color: #74bc31;
  margin-left: 20px;
}

.mainframe {
  display: inline-block;
  width: 880px;
}
.sideframe {
  float: right;
  width: 300px;
}
.yyk_show {
  margin-top: 20px;
  overflow: hidden;
  position: relative;
}
.sideframe .block {
  padding-bottom: 20px;
  overflow: hidden;
}
.title2 {
  font-size: 18px;
  color: #333;
  line-height: 64px;
  border-bottom: #f0f0f0 1px solid;
}
.title > a,
.title2 > a {
  float: right;
  font-size: 14px;
  font-weight: 400;
  color: #999;
  padding-right: 10px;
  background: url(https://image.39.net/dist/yyk/v3/pc/images/arrow_more.png)
    right center no-repeat;
}
.sidedoctorlist {
  padding: 18px 0;
}
.sidedoctorlist img{
  height: 150px;
  width: 150px;
  margin-left: 20px;
}
.sidedoctorlist strong {
  display: block;
  font-size: 16px;
  line-height: 18px;
  font-weight: 400;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.sidedoctorlist span {
  display: block;
  line-height: 24px;
  color: #999999;
  margin-top: 12px;
  height: 24px;
  overflow: hidden;
  text-overflow: ellipsis;
  margin-left: 50px;
}
.sidedoctorlist span a{
  color: #999999;
}
.sidedoctorlist span a:hover{
  color: orange;
  text-decoration: underline;
}
.sidedoctorlist p {
  display: block;
  line-height: 24px;
  color: #999999;
  margin: 0 0 20px 0;
  height: 24px;
  overflow: hidden;
  text-overflow: ellipsis;
}
.clear {
  clear: both;
}
.block {
  background: #fff;
  border-radius: 5px;
  margin-top: 20px;
  padding: 0 20px 20px;
  position: relative;
}
.doctorcalendar {
  padding: 0;
}
.hospitalselect {
  line-height: 20px;
  height: 20px;
  padding: 20px 0;
  color: #333;
  position: relative;
  left: 60px;
}
.hospitalselect strong {
  display: inline-block;
  border-right: #f0f0f0 1px solid;
  margin: 0 auto;
  padding: 0 20px;
  font-size: 23px;
  font-weight: 700;
  color: #74bc31;
}
.doctorcalendar .calendartable {
  border: #f0f0f0 1px solid;
  border-left: 0;
  border-right: 0;
  overflow: hidden;
}
.doctorcalendar .calendartable .ampm {
  float: left;
  width: 95px;
  font-size: 16px;
  color: #666;
  line-height: 64px;
  text-align: center;
  border-right: #f0f0f0 1px solid;
}
.doctorcalendar .calendartable .ampm em {
  display: block;
  border-bottom: #f0f0f0 1px solid;
}
.muticalendar {
  float: right;
  width: 784px;
}
.dateselect {
  position: relative;
  padding: 0 25px 30px 45px;
}
.dateselect table {
  width: 100%;
}
thead {
  display: table-header-group;
  vertical-align: middle;
  border-color: inherit;
}
tr {
  display: inline-block;
  vertical-align: inherit;
  border-color: inherit;
}
.dateselect table th {
  line-height: 26px;
  padding: 0;
  border-top: 0;
}
.dateselect table th,
.dateselect table td {
  border: #f0f0f0 1px solid;
  text-align: center;
  font-size: 16px;
  font-weight: 400;
  color: #666;
  width: 98px;
  height: 64px;
}
.dateselect table td i.orange,
.dateselect table td i.orange a {
  display: block;
  text-align: center;
  line-height: 66px;
  background: #fee7b2;
  color: #ff9023;
  width: 98px;
  height: 66px;
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
.block .title {
  font-size: 24px;
  font-weight: 700;
  color: #74bc31;
  height: 64px;
  line-height: 64px;
  border-bottom: #f0f0f0 1px solid;
}
.content p {
  color: #333333;
  font-size: 15px;
  margin-top: 24px;
  text-indent: 2em;
}
.el-tabs__item .is-active {
  color: #74bc31;
}
</style>

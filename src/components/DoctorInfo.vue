<template>
  <div>
    <div class="wrap">
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
      <!-- <section class="block">
        <div class="filter nav">
          <div class="tab bydep">
            <div class="tabnav">
              <span>科室内容：</span>
              <span v-for="(item, index) in officeList" :key="index">
                <em>{{ item.officeName }}</em>
                <em
                  v-for="(child, index) in item.childOfficeList"
                  :key="index"
                  >{{ child.officeName }}</em
                >
              </span>
            </div>
          </div>
        </div>
      </section> -->
      <section class="block">
        <div class="title">
          <div class="tabnav onlytab">
            <el-tabs v-model="activeName">
              <el-tab-pane label="医院" name="hospital"></el-tab-pane>
              <el-tab-pane label="医生" name="doctor"></el-tab-pane>
            </el-tabs>
          </div>
          <div class="sort">
            <a href="" class="current"
              >综合排序 <i class="el-icon-bottom"></i
            ></a>
          </div>
        </div>
        <div class="hospital" v-if="activeName === 'hospital'">
          <div
            class="single"
            v-for="(item, index) in hospitalList"
            :key="index"
          >
            <img :src="item.src" alt="" />
            <strong
              ><a @click="toHospitalInfo(item.id)">{{
                item.hospitalName
              }}</a></strong
            >
            <p>{{ item.description }}</p>
            <p>{{ item.phone }}</p>
            <p>{{ item.address }}</p>
            <div class="comment">
              <el-button type="primary" @click="toHospitalInfo(item.id)">
                查看详情
              </el-button>
              <span></span>
            </div>
          </div>
        </div>
        <div class="hospital" v-else-if="activeName === 'doctor'">
          <div class="single" v-for="(item, index) in doctorList" :key="index">
            <img :src="item.src" alt="" />
            <strong
              ><a
                @click="
                  toDoctorAppointment(item.id, item.officeId, item.hospitalId)
                "
                >{{ item.doctorName }}</a
              ></strong
            >
            <p>{{ item.position }}</p>
            <p>{{ item.hospitalName }}</p>
            <p>{{ item.officeName }}</p>
            <p>{{ item.description }}</p>
            <div class="comment">
              <el-button
                type="primary"
                @click="
                  toDoctorAppointment(item.id, item.officeId, item.hospitalId)
                "
                >预约挂号</el-button
              >
              <span></span>
            </div>
          </div>
        </div>
        <div class="page"></div>
      </section>
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
import test from "../components/test";

export default {
  inject: ["reload"],
  name: "Appointment",
  component: {
    test,
  },
  data() {
    return {
      userName:"",
      roleType:"",
      userInfo:"",
      src:"../assets/user.png",
      token: localStorage.getItem("token"),
      activeName: "doctor",
      checked: true,
      content: "",
      hospitalList: [],
      doctorList: [],
      officeList: [],
      departmentList: [],
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
    ToDoctorWork() {
      this.$router.push("/DoctorWork");
    },
    setMenuClicked() {
      this.$router.push("/Appointment");
    },
    exit() {
      localStorage.removeItem("token");
      localStorage.removeItem("userInfo");
      this.reload();
    },
    toDoctorAppointment(val1, val2, val3) {
      this.$router.push({
        path: "DoctorAppointment",
        query: {
          id: val1,
          officeId: val2,
          hospitalId: val3,
        },
      });
    },
    toHospitalInfo(val) {
      this.$router.push({ path: "HospitalInfo", query: { hospitalId: val } });
    },
    toIndex() {
      this.$router.push("/");
    },
    login() {
      //保存当前路由
      localStorage.setItem("preRoute", this.$route.fullPath)
      this.$router.push('/Login')
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
    getHospitalList() {
      var flag = 0
      if (this.$route.query.flag != null) {
        flag = this.$route.query.flag
      }
      //不是搜索
      if (flag == 0) {
        this.$axios
        .get("http://localhost:8088/hospital/getAllHospitals/", {
          params: {
          },
        })
        .then((res) => {
          if (200 == res.status) {
            console.log(res);
            this.hospitalList = res.data.data;
            console.log(this.hospitalList);
          }
        });
      }else {
        this.$axios
        .get("http://localhost:8088/hospital/list", {
          params: {
            hospitalName: this.$route.query.hospitalName,
          },
        })
        .then((res) => {
          if (200 == res.status) {
            console.log(res);
            this.hospitalList = res.data.data;
            console.log(this.hospitalList);
          }
        });
      }
    },
    getDoctorList() {
      var flag = 0
      if (this.$route.query.flag != null) {
        flag = this.$route.query.flag
      }
      //若科室id为空
      if (this.$route.query.officeId == null) {
        //不是搜索
        if (flag == 0) {
          this.$axios
          .get("http://localhost:8088/doctor/getAllDoctors/", {
            params: {
            },
          })
          .then((res) => {
            if (200 == res.status) {
              console.log(res);
              this.doctorList = res.data.data;
              console.log(this.doctorList);
            }
          });
        } else {
          this.$axios
          .get("http://localhost:8088/doctor/getDoctorList", {
            params: {
              doctorName: this.$route.query.doctorName,
            },
          })
          .then((res) => {
            if (200 == res.status) {
              console.log(res);
              this.doctorList = res.data.data;
              console.log(this.doctorList);
            }
          });
        }
      } else {
        this.$axios
          .get("http://localhost:8088/doctor/getDoctorListByOfficeId/", {
            params: {
              officeId: this.$route.query.officeId
            },
          })
          .then((res) => {
            if (200 == res.status) {
              console.log(res);
              this.doctorList = res.data.data;
              console.log(this.doctorList);
            }
          });
      }
    },
    getAllOfficeList() {
      // var param = {id:1};
      this.$axios
        .get("http://localhost:8088/office/getOfficeNameAll", {
          params: {},
        })
        .then((res) => {
          if (200 == res.status) {
            console.log(res);
            this.officeList = res.data.data;
            console.log(this.officeList);
          }
        });
    },
  },
  mounted() {
    this.activeName = this.$route.query.status;
    this.$nextTick(() => {
      this.getHospitalList();
      this.getAllOfficeList();
      this.getDoctorList();
      this.userInfo = JSON.parse(localStorage.getItem("userInfo"));
      if (this.userInfo != null) {
          this.userName = JSON.parse(localStorage.getItem("userInfo")).userName;
          this.roleType = JSON.parse(localStorage.getItem("userInfo")).roleType;
      }
      console.log(this.$route.query.flag)
      console.log(this.$route.query.hospitalName)
    });
  },
};
</script>

<style scoped>
.wrap {
  width: 1200px;
  margin: 0 auto;
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

.topLink a:hover {
  border-bottom: white 1px solid;
}

.block {
  background: #fff;
  border-radius: 5px;
  margin-top: 25px;
  padding: 0 20px 20px;
  position: relative;
  top: 45px;
}

.filter {
  padding-top: 12px;
  padding-bottom: 24px;
  color: #999;
}

.filter .bydep {
  padding-top: 35px;
  margin-bottom: 15px;
  overflow: hidden;
}
.filter .bydep .tabnav {
  height: 175px;
}
.filter .bydep .tabnav span {
  display: block;
  float: left;
  height: 40px;
  width: 100px;
  margin: 0 28px;
}
.filter .bydep .tabnav em {
  display: inline-block;
  padding: 0 0 10px 0;
  color: #333;
  vertical-align: middle;
  margin: 0 12px;
  width: 80px;
}

.filter .bydep .tabnav em:hover {
  color: orange;
  text-decoration: underline;
}

em,
i,
cite {
  font-weight: 400;
  font-style: normal;
}

.tab .tabnav > * {
  cursor: pointer;
}

.nav {
  background: #fff;
  border-radius: 5px;
  padding: 0 20px 5px;
  position: relative;
}

.title {
  font-size: 24px;
  font-weight: 700;
  color: #1e9eb9;
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

.title span .totalinfo {
  padding-left: 15px;
}

.withlocation {
  padding-left: 15px;
  margin-left: 20px;
}

.title span.totalinfo {
  font-size: 14px;
}

.title span b {
  font-weight: 400;
  color: #2ca1bb;
}

.title .bookable,
.sort {
  float: right;
  font-size: 14px;
  color: #666;
  font-weight: 400;
}

.title .sort a {
  display: inline-block;
  color: #666;
  padding-right: 11px;
  margin-left: 20px;
  text-decoration: none;
}

.title .sort a:hover {
  color: #409eff;
}

.hospital .single {
  height: 120px;
  padding: 20px 0;
  border-bottom: #f0f0f0 1px dashed;
  overflow: hidden;
  position: relative;
}

.hospital .single img {
  float: left;
  width: 150px;
  height: 110px;
  margin-right: 20px;
  border-radius: 5px;
}

.hospital .single strong {
  font-size: 18px;
  line-height: 20px;
  font-weight: 400;
  display: block;
  margin-bottom: 8px;
}

.hospital .single strong a:hover {
  font-size: 18px;
  line-height: 20px;
  font-weight: 400;
  display: block;
  margin-bottom: 8px;
  cursor: pointer;
  color: coral;
  text-decoration: underline;
}

.hospital .single p {
  font-size: 14px;
  color: #666666;
  margin: 0;
  width: 560px;
  line-height: 24px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.hospital .single .comment {
  position: absolute;
  right: 0;
  bottom: 20px;
}

.hospital .single .comment span {
  display: block;
  line-height: 14px;
  margin-top: 40px;
  color: #666666;
}

.hospital .single .comment b {
  color: #2ca1bb;
  font-weight: 400;
  margin-right: 5px;
}

.hospital .detail p {
  color: #999999;
}

.hospital .detail span {
  color: #333;
  padding-left: 20px;
}

.hospital .introduction {
  width: 100%;
  height: auto;
}

.hospital .introduction .title .border {
  width: 120px;
  height: 30px;
  color: #333333;
  line-height: 30px;
  border-left: #2ca1bb 5px solid;
  font-weight: 400;
}

.hospital .introduction .title .border p {
  padding-left: 20px;
  margin: 0;
}

.content p {
  color: #333333;
  font-size: 15px;
  margin-top: 24px;
  text-indent: 2em;
}

.el-icon-map-location {
  display: inline-block;
  color: #1e9eb9;
  vertical-align: center;
  padding-left: 20px;
  margin-left: 10px;
}

.hospital .map {
  width: 100%;
  height: 500px;
  padding: 10px;
}
.hospital .dep {
  width: 100%;
  height: 100%;
}
.depline {
  overflow: hidden;
  padding: 10px 0 1px 0;
  border-bottom: #999999 dashed 1px;
}
.dep .depline .departmentname {
  width: 100px;
  height: 40px;
  float: left;
  font-size: 15px;
  font-weight: 500;
  color: #333333;
}
.dep .depline .rightside {
  float: right;
  width: 960px;
}
.dep .depline .rightside .singledep {
  display: inline-block;
  width: 200px;
  height: 40px;
  font-size: 15px;
  font-weight: 400;
  color: #666666;
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

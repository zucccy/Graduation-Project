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
              <el-button slot="append" icon="el-icon-search">搜索</el-button>
            </el-input>
          </div>
          <div class="topLink">
            <a href="" @click="doctorFind()">找医生</a>
            <a href="" @click="hospitalFind()">找医院</a>
          </div>
        </div>
      </section>
    <IndexContent></IndexContent>
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
</template>

<script>
import IndexContent from "./IndexContent";
import HospitalInfo from "./HospitalInfo";
export default {
  inject: ["reload"],
  name: "Index",
  components: {
    IndexContent,
    HospitalInfo,
  },
  data() {
    return {
      token:localStorage.getItem('token'),
      hospitalVisible: false,
      indexContentVisible: true,
      content: "",
      status: {
        doctor: "doctor",
        hospital: "hospital",
      },
      userInfo:"",
      userName:"",
      roleType:"",
    };
  },
  methods: {
    exit() {
      localStorage.removeItem("token");
      localStorage.removeItem("userInfo");
      this.reload();
    },
    setMenuClicked() {
      this.$router.push("/Appointment");
    },

    toIndex() {
      this.$router.push("/");
    },
    login() {
      this.$router.push("Login");
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
    ToDoctorWork() {
      this.$router.push("/DoctorWork");
    },
  },
    mounted() {
    this.activeName = this.$route.query.status;
    this.$nextTick(() => {
      this.userInfo = JSON.parse(localStorage.getItem("userInfo"));
      if (this.userInfo != null) {
          this.userName = JSON.parse(localStorage.getItem("userInfo")).userName;
          this.roleType = JSON.parse(localStorage.getItem("userInfo")).roleType;
      }
    });
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
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
.quickSearch {
  float: left;
  width: 400px;
  height: 330px;
  background: #fff;
  border-radius: 5px;
  margin: 20px auto;
  padding: 0 20px 0;
  position: relative;
}
.quickSearch .title {
  font-size: 18px;
  color: #333;
  line-height: 64px;
  border-bottom: #f0f0f0 1px solid;
  cursor: pointer;
}
.quickSearch .inlinePut {
  font-size: 16px;
  color: #333;
  line-height: 32px;
  margin-top: 20px;
  margin-right: 10px;
}
.quickSearch .searchButton {
  margin-top: 20px;
  font-size: 16px;
  line-height: 40px;
  color: #fff;
  text-align: center;
  background: #ff9023;
  border-radius: 5px;
  cursor: pointer;
}
.quickSearch .totalData {
  padding-top: 10px;
  text-align: center;
}
.quickSearch .totalData b {
  color: deepskyblue;
}
.indexSlide {
  display: block;
  width: 100%;
  height: 320px;
  background: #38abbb;
  border-radius: 5px;
  margin: 20px auto;
  overflow: hidden;
}
.indexSlide img {
  width: 100%;
  height: 100%;
}
.clear {
  clear: both;
  height: 0;
  line-height: 0;
  font-size: 0;
}
.tab {
  background: #fff;
  border-radius: 5px;
  margin-top: 20px;
  padding: 0 20px 40px;
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
.title span {
  cursor: pointer;
}
.inactive {
  display: inline-block;
  color: darkgrey;
  font-size: medium;
  font-family: "Adobe Hebrew";
  cursor: pointer;
}
.inactive:hover {
  color: #000000;
}
.active {
  display: inline-block;
  color: #000000;
  border-bottom: green 2px solid;
  font-size: medium;
  font-family: "Adobe Hebrew";
  cursor: pointer;
}
.channels {
  font-size: 16px;
  color: #999;
  line-height: 36px;
}
.channels a {
  display: inline-block;
  float: left;
  margin: 20px 10px 0 0;
  padding: 0 15px;
  background: #f1f1f1;
  border-radius: 36px;
  color: #666;
  text-decoration: none;
}
.channels a:hover {
  background-color: #1e9eb9;
  color: white;
  text-decoration: none;
}
.diseaselist {
  margin-top: 120px;
}
.diseaselist li {
  display: inline-block;
  width: 480px;
  height: 60px;
  font-size: 16px;
  line-height: 16px;
  overflow: hidden;
  padding-left: 60px;
  margin-top: 20px;
  background: url(https://image.39.net/dist/yyk/v3/pc/images/avatar_woman.png)
    left center no-repeat;
}
.diseaselist li a {
  display: inline-block;
  margin: 5px 0 15px 20px;
  color: black;
  text-decoration: none;
}
.diseaselist li a:hover {
  color: orange;
}
.diseaselist li:nth-child(2) {
  background-image: url(https://image.39.net/dist/yyk/v3/pc/images/avatar_children.png);
}
.diseaselist li:nth-child(3) {
  background-image: url(https://image.39.net/dist/yyk/v3/pc/images/avatar_man.png);
}
.diseaselist li:nth-child(4) {
  background-image: url(https://image.39.net/dist/yyk/v3/pc/images/avatar_oldman.png);
}
.deplist {
  margin-bottom: -18px;
}
.deplist div {
  /*display: flex;*/
  border-bottom: #f0f0f0 1px dashed;
  overflow: hidden;
  /*flex-wrap: nowrap;*/
}
.deplist ul {
  float: left;
  width: 370px;
  margin: 15px 15px 15px 0;
  line-height: 34px;
  overflow: hidden;
  padding-left: 0;
}
.deplist dt {
  float: left;
  width: 70px;
  height: 90px;
  font-size: 18px;
  font-weight: 700;
  color: #333;
}
.deplist li {
  list-style: none;
  float: left;
  width: 90px;
  margin-right: 10px;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.deplist a {
  cursor: pointer;
  text-decoration: none;
  color: #333;
}
.deplist a:hover {
  color: orange;
}
.mainframe {
  display: inline-block;
  width: 860px;
}
.block {
  background: #fff;
  border-radius: 5px;
  margin-top: 20px;
  padding: 0 20px 40px;
  position: relative;
}
.title .tabNav {
  display: inline-block;
  vertical-align: top;
  font-size: 16px;
  color: #333;
}
.title .tabNav em {
  display: inline-block;
  line-height: 63px;
  margin-left: 20px;
}
.tab .tabNav > * {
  cursor: pointer;
}
.title a {
  float: right;
  font-size: 14px;
  font-weight: 400;
  color: #999;
  padding: 0 10px 0 30px;
  background: url(https://image.39.net/dist/yyk/v3/pc/images/arrow_more.png)
    right center no-repeat;
}
.content {
  display: inline-block;
  width: 322px;
  float: right;
}
.newslist {
  line-height: 40px;
  margin-top: 7px;
  overflow: hidden;
  margin-bottom: 2px;
}
.newslist li {
  list-style: none;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.newslist span {
  color: #333333;
  font-size: 14px;
}
.tabcontent {
  display: block;
}
.tabcontent b {
  font-family: "Adobe Caslon Pro";
  font-size: 14px;
  cursor: pointer;
}
.tabcontent b:hover {
  color: orange;
}
.single {
  display: inline-block;
  width: 260px;
  height: 58px;
  margin: 19px 9px 0 0;
}
.single img {
  float: left;
  width: 78px;
  height: 56px;
  border: #e1e1e1 1px solid;
  border-radius: 5px;
  margin-right: 10px;
}
.single strong:hover {
  color: orange;
}
.single b:hover {
  color: orange;
}
.single .pic {
  width: 30px;
  height: 30px;
  border-radius: 100%;
  margin-right: 10px;
}
.about {
  display: block;
  width: 160px;
  text-overflow: ellipsis;
  font-size: 14px;
  white-space: nowrap;
  overflow: hidden;
}
.person {
  display: inline-block;
  width: 185px;
  height: 248px;
  text-align: center;
  margin: 22px 20px 0 0;
  border-radius: 5px;
  overflow: hidden;
  box-shadow: 0 1px 3px 0;
}
.person a {
  color: #999;
  display: block;
  height: 100%;
  padding: 0 9px;
  cursor: pointer;
}
.person img {
  width: 78px;
  height: 78px;
  margin-top: 19px;
  border: #e4e4e4 1px solid;
  border-radius: 100%;
}
.person strong {
  display: block;
  font-size: 16px;
  font-weight: 400;
  color: #333;
  line-height: 16px;
  margin-top: 10px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  cursor: pointer;
}
.person strong b {
  font-size: 14px;
  font-weight: 400;
  color: #999;
  margin-left: 5px;
  cursor: pointer;
}
.person p {
  line-height: 14px;
  margin-top: 11px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  cursor: pointer;
}
.person span {
  line-height: 24px;
  padding-top: 14px;
  margin-top: 20px;
  border-top: #f0f0f0 1px solid;
  height: 48px;
  overflow: hidden;
  text-overflow: ellipsis;
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

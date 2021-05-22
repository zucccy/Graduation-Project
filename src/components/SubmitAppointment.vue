<template>
  <div class="big">
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
    <section class="block myContent">
      <div class="title">确认预约信息</div>
      <div class="doctorinfo">
        <div class="doctorpic">
          <img :src="doctor.src" alt="" />
        </div>
        <div class="doctordetail">
          <h1>
            {{ doctor.doctorName }} <i>{{ doctor.position }}</i>
          </h1>
          <p>
            就诊医院：<i>{{ doctor.hospitalName }}</i>
          </p>
          <p>
            门诊类型：<i>{{ visitType.boolean[visitPlan.visitType] }}</i>
          </p>
        </div>
      </div>
      <div class="appointmentinfo">
        <div class="time">
          就诊时间: <span>{{ visitPlan.timePeriod }}</span>
        </div>
        <div class="address">
          医院地址：<span>{{ hospitalInfo.address }}</span>
        </div>
      </div>
    </section>
    <section class="block myContent">
      <div class="title">选择就诊人</div>
      <div>
        <el-radio-group v-model="radio1" fill="rgba(217, 217, 217, 0.4)">
          <el-radio-button
            :label="index"
            v-for="(item, index) in patientList"
            :key="index"
          >
            <div class="pybutton" @click="selectPatient(index)">
              <span>
                <strong>{{ item.patientName }}</strong>
                <em class="edit" @click="UpdatePatientVisible = true">修改</em>
              </span>
              <p>{{ item.idCard }}</p>
              <p>{{ item.phone }}</p>
              <em class="delete" @click="DeletePatientVisible = true">删除</em>
            </div>
          </el-radio-button>
        </el-radio-group>
        <div class="add" @click="addPatientVisible = true">新增就诊人</div>
      </div>
      <div class="clear"></div>
    </section>
    <div class="button" @click="createAppointment()">提交预约</div>
    <AddPatient
      :title="'新增就诊人'"
      :visible="addPatientVisible"
      @close="addPatientVisible = false"
    ></AddPatient>
    <UpdatePatient
      :title="'修改就诊人'"
      :visible="UpdatePatientVisible"
      @close="UpdatePatientVisible = false"
    ></UpdatePatient>
    <DeletePatient
      :title="'删除就诊人'"
      :visible="DeletePatientVisible"
      @close="DeletePatientVisible = false"
    ></DeletePatient>
  </div>
</template>

<script>
import AddPatient from "./AddPatient";
import UpdatePatient from "./UpdatePatient";
import DeletePatient from "./DeletePatient";
export default {
  inject: ["reload"],
  name: "Index",
  components: {
    AddPatient,
    UpdatePatient,
    DeletePatient,
  },
  data() {
    return {
      token: localStorage.getItem("token"),
      radio1: "上海",
      visitType: { boolean: { 0: "普通门诊", 1: "专家门诊" } },
      patientList: [],
      doctor: "",
      hospitalVisible: false,
      indexContentVisible: true,
      addPatientVisible: false,
      UpdatePatientVisible: false,
      DeletePatientVisible: false,
      content: "",
      status: {
        doctor: "doctor",
        hospital: "hospital",
      },
      visitPlan: "",
      hospitalInfo: [],
      sPatient: "",
      userInfo: "",
      userName:"",
      roleType:"",
    };
  },
  methods: {
    exit() {
      localStorage.removeItem("token");
      localStorage.removeItem("userInfo");
      this.reload();
      this.$router.push('/');
    },
    setMenuClicked() {
      this.$router.push("/Appointment");
    },
    createAppointment() {
      var param = {
        doctorId: this.$route.query.doctorId,
        patientId: this.sPatient.id,
        visitId: this.$route.query.id,
        visitStatus: 1,
      };
      this.$axios.post("/appointment/createAppointment", param).then((res) => {
        if (200 == res.status) {
          console.log(res);
          this.$message({
            message: "预约成功！",
            type: "success",
          });
          this.$router.push("/Appointment");
        }
      });
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
    selectPatient(val) {
      this.sPatient = this.patientList[val];
      console.log(this.sPatient);
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
    getHospital() {
      // var param = { address: "杭州", hospitalName: null, pageNum: 1, pageSize: 10};
      this.$axios
        .get("/hospital/id", {
          params: {
            id: this.$route.query.hospitalId,
          },
        })
        .then((res) => {
          if (200 == res.status) {
            console.log(res);
            this.hospitalInfo = res.data.data;
            console.log(this.hospitalInfo);
          }
        });
    },
    getVisitPlan() {
      // var param = { address: "杭州", hospitalName: null, pageNum: 1, pageSize: 10};
      this.$axios
        .get("/doctor/getVisitPlan/", {
          params: {
            visitPlanId: this.$route.query.id,
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
    getDoctor() {
      // var param = { address: "杭州", hospitalName: null, pageNum: 1, pageSize: 10};
      this.$axios
        .get("/doctor/id", {
          params: {
            id: this.$route.query.doctorId,
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
    setMenuClicked() {
      this.$router.push("/Appointment");
    },
    ToDoctorWork() {
      this.$router.push("/DoctorWork");
    },
    getPatient() {
      // var param = { address: "杭州", hospitalName: null, pageNum: 1, pageSize: 10};
      this.$axios
        .get("/user/getMyPatients", {
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
  },
  mounted() {
    this.$nextTick(() => {
      this.getPatient();
      this.getDoctor();
      this.getVisitPlan();
      this.getHospital();
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
.big {
  height: 910px;
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
  height: auto;
  margin: 0 auto;
}
.topBar .topSevers {
  float: left;
  width: 80px;
  position: relative;
  cursor: pointer;
}
.el-dropdown-link {
  cursor: pointer;
  color: #fff;
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
.el-dropdown {
  bottom: 56px;
  left: 40px;
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
.title {
  font-size: 24px;
  font-weight: 700;
  color: #74bc31;
  height: 64px;
  line-height: 64px;
  border-bottom: #f0f0f0 1px solid;
}
.title span {
  cursor: pointer;
}
.block {
  background: #fff;
  border-radius: 5px;
  margin: 55px auto 20px;
  padding: 0 20px 25px;
  position: relative;
  top: 15px;
}
.myContent {
  display: block;
  width: 940px;
  height: auto;
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
.edit {
  font-style: normal;
  float: right;
  color: #74bc31;
}
.edit:hover {
  color: #ff9023;
}
.pybutton p {
  padding: 0 10px;
  color: #333;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.delete {
  font-style: normal;
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
.box:hover {
  background-color: white !important;
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
.doctorinfo {
  padding: 20px 20px 0 20px;
  overflow: hidden;
}
.doctorinfo .doctorpic {
  width: 80px;
  float: left;
}
.doctorinfo .doctorpic img {
  width: 80px;
  height: 90px;
  border-radius: 5px;
}
.doctorinfo .doctordetail {
  width: 500px;
  float: left;
  padding-left: 20px;
  line-height: 24px;
}
.doctorinfo .doctordetail h1 {
  margin: 0 0 10px 0;
}
.doctorinfo .doctordetail p {
  margin: 10px 0;
}
.doctorinfo .doctordetail i {
  padding-left: 10px;
  color: #999999;
  font-size: 18px;
  font-style: normal;
}
.appointmentinfo {
  padding: 10px 0 20px 20px;
  color: #666666;
  height: 80px;
  width: 100%;
}
.time {
  line-height: 40px;
}
.time span {
  color: red;
}
.address {
  line-height: 40px;
}
.button {
  background-color: #ff9023;
  margin: 64px auto 80px;
  text-align: center;
  line-height: 60px;
  color: white;
  width: 210px;
  height: 60px;
  cursor: pointer;
  border-radius: 10px;
  font-size: 25px;
}
</style>

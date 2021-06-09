<template>
  <div class="wrap">
    <section class="indexSlide">
      <Banner :banners="banners" :duration="3000" />
      <!--            <img src="../assets/sky.jpg" alt="">-->
    </section>

    <div class="clear"></div>
    <section class="block tab">
      <div class="title">
        快速找医生
        <div class="active" style="margin: 0 0 0px 21px">科室内容</div>
      </div>
      <div class="officelist" v-show="value == 1">
        <ul>
          <li v-for="(item, index) in officeList" :key="index">
            <span class="title">{{ item.officeName }}</span>
            <a
              @click="toDoctorInfo(child.id)"
              v-for="(child, index) in item.childOfficeList"
              :key="index"
              >{{ child.officeName }}</a
            >
          </li>
        </ul>
      </div>
    </section>
    <div class="mainframe">
      <section class="block tab">
        <div class="title">
          <span class="recommend">推荐医院</span>
          <el-menu
            :default-active="activeIndex_menu1"
            class="el-menu-demo"
            mode="horizontal"
            style="display: inline-block"
            @select="handleSelect_menu1"
            active-text-color="#74bc31"
          >
            <el-menu-item index="1">内科</el-menu-item>
            <el-menu-item index="2">外科</el-menu-item>
            <el-menu-item index="3">妇产科</el-menu-item>
            <el-menu-item index="4">儿科</el-menu-item>
            <el-menu-item index="5">耳鼻喉科</el-menu-item>
            <el-menu-item index="6">皮肤科</el-menu-item>
            <el-menu-item index="7">肿瘤科</el-menu-item>
            <el-menu-item index="8">男科</el-menu-item>
          </el-menu>
          <a class="more" @click="hospitalFind()">更多</a>
        </div>
        <div class="tabcontent" v-show="activeIndex_menu1 == 1">
          <div
            class="single"
            v-for="(item, index) in hospitalList"
            :key="index"
          >
            <img :src="item.src" alt="" />
            <b class="about" @click="toHospitalInfo(item.id)">{{
              item.hospitalName
            }}</b>
            <span class="about">{{ item.description }}</span>
            <span class="about">{{ item.phone }}</span>
          </div>
        </div>
      </section>
    </div>
    <div class="content">
      <section class="block">
        <div class="title">医院资讯</div>
        <div class="newslist">
          <li v-for="(item, index) in newslist" :key="index">
            <!-- <span @click="test(item.newsUrl)">{{item.newsTitle}}</span> -->
            <a :href="item.newsUrl" target="_blank">{{ item.newsTitle }}</a>
          </li>
        </div>
      </section>
      <section class="block">
        <div class="title">智能问诊</div>
        <div class="box">
          <div class="header">
            <h3>{{ list[0].name }}</h3>
          </div>
          <div class="main">
            <div class="talkshow">
              <div
                v-for="(item, index) in list"
                :key="index"
                :class="item.id == '2' ? 'atalk' : 'btalk'"
              >
                <span>{{ item.content }}</span>
              </div>
            </div>
          </div>
          <div class="sendbox">
            <el-input
              v-model="wordone"
              placeholder="请输入消息"
              @keyup.enter="sendmsg1()"
            ></el-input>
            <el-button type="success" round @click="sendmsg1()">发送</el-button>
          </div>
        </div>
      </section>
    </div>
    <div class="mainframe">
      <section class="block tab">
        <div class="title">
          <span class="recommend">推荐医生</span>
          <el-menu
            :default-active="activeIndex_menu2"
            class="el-menu-demo"
            mode="horizontal"
            style="display: inline-block"
            @select="handleSelect_menu2"
            active-text-color="#74bc31"
          >
            <el-menu-item index="1">内科</el-menu-item>
            <el-menu-item index="2">外科</el-menu-item>
            <el-menu-item index="3">妇产科</el-menu-item>
            <el-menu-item index="4">儿科</el-menu-item>
            <el-menu-item index="5">耳鼻喉科</el-menu-item>
            <el-menu-item index="6">皮肤科</el-menu-item>
            <el-menu-item index="7">肿瘤科</el-menu-item>
            <el-menu-item index="8">男科</el-menu-item>
          </el-menu>
          <a class="more" @click="doctorFind()">更多</a>
        </div>
        <div class="tabcontent" v-show="activeIndex_menu2 == 1">
          <div
            class="person"
            v-for="(item, index) in doctorVOList"
            :key="index"
          >
            <a
              href=""
              @click="
                toDoctorAppointment(item.id, item.officeId, item.hospitalId)
              "
            >
              <img :src="item.src" alt="" />
              <strong
                ><b>{{ item.doctorName }}</b></strong
              >
              <p>{{ item.position }}</p>
              <span class="des">{{ item.description }}</span>
            </a>
          </div>
        </div>
      </section>
    </div>
    <div class="clear"></div>
    <section class="block tab">
      <section class="smallblock">
        <div class="title">
          <span class="recommend">权威文章</span>
          <el-menu
            :default-active="activeIndex_menu3"
            class="el-menu-demo"
            mode="horizontal"
            style="display: inline-block"
            @select="handleSelect_menu3"
            active-text-color="#74bc31"
          >
            <el-menu-item index="1">内科</el-menu-item>
            <el-menu-item index="2">外科</el-menu-item>
            <el-menu-item index="3">妇科</el-menu-item>
            <el-menu-item index="4">男科</el-menu-item>
            <el-menu-item index="5">儿科</el-menu-item>
            <el-menu-item index="6">肿瘤科</el-menu-item>
            <el-menu-item index="7">不孕不育</el-menu-item>
          </el-menu>
        </div>
        <div class="tabcontent" v-show="activeIndex_menu3 == 1">
          <div class="single" v-for="(item, index) in essayList" :key="index">
            <strong><a :href="item.essayUrl" target="_blank">{{ item.essayTitle }}</a></strong>
            <p>
                <img class="pic" src="../assets/doctor2.png" alt="">
                <b>{{ item.essayAuthor }}</b>
            </p>
          </div>
        </div>
      </section>
    </section>
  </div>
</template>

<script>
import Banner from "../components/Banner";
import Login from "../components/Login";
export default {
  name: "IndexContent",
  components: {
    Banner,
    Login,
  },
  mounted() {
    this.$nextTick(() => {
      this.getNewsList();
      this.getHospitalList();
      this.getAllOfficeList();
      this.getEssayList();
      this.userInfo = JSON.parse(localStorage.getItem("userInfo"));
    });
  },
  data() {
    return {
      list: [
        {
          id: 2,
          name: "智能分诊机器人",
          content: "您好，智能分诊机器人为您服务。"
        }
      ],
      replyList:"",
      wordone: "",
      wordtwo: "",
      userInfo: "",
      doctorVOList: [],
      newslist: [],
      banners: [
        {
          link: "https://hd.guahao.com/u/28822?gytx2&P-1.1.1",
          url: require("../assets/Chinahospital.png"),
        },
        {
          link: "localhost:8080",
          url: require("../assets/pic1.png"),
        },
        {
          link: "localhost:8080",
          url: require("../assets/bg.jpg"),
        },
      ],
      content: "",
      src:
        "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      province: "",
      city: "",
      department: "",
      channellist: [
        "肝病",
        "不孕不育",
        "腰椎间盘突出",
        "颈椎病",
        "胆结石",
        "鼻炎",
        "白癜风",
        "甲亢",
        "肾结石",
        "性病",
        "抑郁症",
        "癫痫",
        "胃病",
        "头疼",
        "口臭",
        "便秘",
        "狐臭",
        "皮肤病",
      ],
      officeList: [],
      hospitalList: [],
      cityNumber: "189",
      hospitalNumber: "2000",
      value: "1",
      activeIndex_menu1: "1",
      activeIndex_menu2: "1",
      activeIndex_menu3: "1",
      essayList: [],
    };
  },
  methods: {
    sendmsg1() {
      var param = { 
          perception: {
            inputText: {
              text:this.wordone,
        },
      },
           userInfo: {
            apiKey:"32293c6faadb42499fa94fe37ac1ba7a",
            userId:"714515",
      }, 
    };
    this.list.push({
          id: 1,
          name: "小陈",
          content: this.wordone,
    })
    this.wordone = "";
      this.$axios.post("/openapi/api/v2", param).then(res => {
        if (200 == res.status) {
          console.log(res);
          this.replyList = res.data.results;
          console.log(this.replyList);
          this.list.push({
            id:2,
            name: "智能分诊机器人",
            content: this.replyList[0].values.text,
          });
          console.log(this.replyList[0].values.text);
          console.log(this.list);
        }
      });
    },
    toDoctorInfo(val) {
      this.$router.push({
        path: "DoctorInfo",
        query: {
          officeId: val,
          status: "doctor",
        },
      });
    },
    toHospitalInfo(val) {
      this.$router.push({
        path: "HospitalInfo",
        query: {
          hospitalId: val,
        },
      });
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
    getNewsList() {
      var param = {};
      this.$axios.get("http://localhost:8088/hospital/getHospitalNews", param).then((res) => {
        if (200 == res.status) {
          console.log(res);
          this.newslist = res.data.data;
          console.log(this.newslist);
        }
      });
    },
    doctorFind() {
      var activeName = "doctor";
      this.$router.push({
        name: "doctorInfo",
        query: { status: activeName },
      });
    },
    hospitalFind() {
      var activeName = "hospital";
      this.$router.push({
        name: "doctorInfo",
        query: { status: activeName },
      });
    },
    getEssayList() {
      this.$axios.get("http://localhost:8088/hospital/getEssayList").then((res) => {
        if (200 == res.status) {
          console.log(res);
          this.essayList = res.data.data;
          console.log(this.essayList);
        }
      });
    },
    getHospitalList() {
      var param = { distance: 10, latitudes: 30.29133, longitudes: 120.212997 };
      this.$axios.post("http://localhost:8088/hospital/getAdviceHospitalList", param).then((res) => {
        if (200 == res.status) {
          console.log(res);
          this.hospitalList = res.data.data;
          console.log(this.hospitalList);
          for (var i = 0; i < this.hospitalList.length; i++) {
            this.doctorVOList.push(this.hospitalList[i].doctorVOList[0]);
            if (this.hospitalList[i].doctorVOList[1] != null) {
              this.doctorVOList.push(this.hospitalList[i].doctorVOList[1]);
            }
          }
          console.log(this.doctorVOList);
        }
      });
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
    handleSelect_menu1(key) {
      this.activeIndex_menu1 = key;
    },
    handleSelect_menu2(key) {
      this.activeIndex_menu2 = key;
    },
    handleSelect_menu3(key) {
      this.activeIndex_menu3 = key;
    },
    test(val) {
      this.$router.push({ path: "Login", query: { id: val } });
    },
  },
};
</script>

<style scoped>
.wrap {
  width: 1200px;
  margin: 0 auto;
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
.more {
  cursor: pointer;
}
.more:hover {
  color: orange;
  text-decoration: underline;
}
.quickSearch .totalData b {
  color: deepskyblue;
}
.indexSlide {
  display: block;
  width: 100%;
  height: 333px;
  /*background: #38abbb;*/
  border-radius: 5px;
  margin: 55px auto 20px;
  overflow: hidden;
}
/*.indexSlide img {*/
/*  width: 100%;*/
/*  height: 100%;*/
/*}*/
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
  color: #74bc31;
  height: 64px;
  line-height: 64px;
  border-bottom: #f0f0f0 1px solid;
  margin-bottom: 6px;
}
.recommend {
  float: left;
  margin-right: 10px;
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
  height: 50px;
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
.officelist {
  margin-top: 20px;
}
.officelist li {
  display: inline-block;
  width: 480px;
  height: 60px;
  font-size: 16px;
  line-height: 16px;
  overflow: hidden;
  padding-left: 60px;
  margin-top: 20px;
  background: url("../assets/neike.png") left top no-repeat;
  background-size: 50px 50px;
}
.officelist li .title {
  float: left;
  color: black;
  font-weight: 400;
  line-height: 40px;
}
.officelist li a {
  display: inline-block;
  margin: 5px 0 15px 20px;
  color: black;
  text-decoration: none;
  cursor: pointer;
}
.officelist li a:hover {
  color: orange;
  text-decoration: underline;
}
.officelist li:nth-child(2) {
  background-image: url("../assets/waike.png");
}
.officelist li:nth-child(3) {
  background-image: url("../assets/fuchanke.png");
}
.officelist li:nth-child(4) {
  background-image: url("../assets/erke.png");
}
.officelist li:nth-child(5) {
  background-image: url("../assets/guke.png");
}
.officelist li:nth-child(6) {
  background-image: url("../assets/wuguanke.png");
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
  padding: 0 20px 20px;
  position: relative;
}
.smallblock {
  background: #fff;
  border-radius: 5px;
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
  height: 35px;
}
.newslist a {
  color: #333333;
  text-decoration: none;
  font-size: 15px;
}
.newslist a:hover {
  color: orange;
  text-decoration: underline;
}
.el-input {
  width: 75%;
}
.box {
  width: 282px;
  height: 385px;
  border-radius: 15px;
  /* border: 1px solid grey; */
}
.header {
  text-align: center;
  border: 1px solid grey;
  background-color: #fff;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
}

h3 {
  margin: 2px;
  padding: 2px;
  color: #000;
}
.main {
  height: 85%;
  border-left: 1px solid grey;
  border-right: 1px solid grey; 
  overflow: auto;
  background-color: #ddd;
}
/* div::-webkit-scrollbar {
  display: none;
} */
.atalk {
  margin: 10px;
}
.atalk span {
  display: inline-block;
  padding: 6px 10px;
  background-color: #fff;
  border-radius: 5px;
}
.btalk {
  text-align: right;
  margin: 10px;
}
.btalk span {
  display: inline-block;
  padding: 6px 10px;
  background-color: #A0EC70;
  border-radius: 5px;
}
.sendbox {
  height: 50px;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
}
.el-button--success {
  width: 25%;
  float: right;
  border-radius: 3px;
}
.tabcontent {
  display: block;
}
.tabcontent b {
  font-family: "Adobe Caslon Pro";
  font-size: 13px;
  cursor: pointer;
}
.tabcontent b:hover {
  color: orange;
  text-decoration: underline;
}
.single {
  display: inline-block;
  width: 264px;
  height: 60px;
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
.single strong a{
  text-decoration: none;
  cursor: pointer;
  color: #333333;
}
.single strong:hover {
  color: orange;
  text-decoration: underline;
}
.single b:hover {
  color: orange;
  text-decoration: underline;
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
  text-decoration: none;
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
  line-height: 28px;
  margin-top: 11px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  cursor: pointer;
}
.person span {
  line-height: 24px;
  width: 185px;
  padding-top: 14px;
  margin-top: 20px;
  border-top: #f0f0f0 1px solid;
  height: 48px;
  overflow: hidden;
  text-overflow: ellipsis;
}
.el-menu-item {
  font-size: 15px;
}
</style>

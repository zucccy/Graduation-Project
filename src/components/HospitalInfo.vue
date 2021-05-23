<template>
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
    <section class="block">
      <div class="doctorinfo">
        <div class="doctorpic">
          <img :src="hospitalInfo.src" alt="" />
        </div>
        <div class="doctordetail">
          <h1>{{ hospitalInfo.hospitalName }}</h1>
          <p>{{ hospitalInfo.description }}</p>
        </div>
      </div>
    </section>
    <section class="block">
      <div class="title">
        <div class="tabnav onlytab">
          <el-tabs v-model="activeName">
            <el-tab-pane label="医院概况" name="detail"></el-tab-pane>
            <el-tab-pane label="医院地址" name="address"></el-tab-pane>
            <el-tab-pane label="科室列表" name="department"></el-tab-pane>
            <el-tab-pane label="医院医生" name="doctor"></el-tab-pane>
          </el-tabs>
        </div>
        <div class="sort" v-show="activeName === 'doctor'">
          <a href="" class="current">综合排序 <i class="el-icon-bottom"></i></a>
        </div>
      </div>
      <div class="hospital" v-if="activeName === 'detail'">
        <div class="detail">
          <p>
            电话:<span>{{ hospitalInfo.phone }}</span>
          </p>
          <p>
            地址:<span>{{ hospitalInfo.address }}</span>
            <i class="el-icon-map-location" @click="toBaiDuMap()">查看地图</i>
          </p>
        </div>
        <div class="introduction">
          <div class="titlesmall">
            <div class="border"><p>简介</p></div>
          </div>
          <div class="content">
            <p>
              浙江大学附属第一医院系三级甲等医院，全国百佳医院，是我省医疗、教学、科研、健康保健指导中心。医院由浙江大学老校长竺可桢教授亲手创建于
              1947年11月1日，原名浙江大学医学院附属医院。医院现有职工4000余人，其中高级职称专家483人，拥有中国工程院院士2人、国家“千人计划”入选者2人，教育部“长江学者”奖励计划特聘教授1人、讲座教授2人，“百千万人才工程”国家级人选1人，973首席科学家2人，国家自然科学基金创新研究群体1个、杰出青年获得者1人，教育部长江学者和创新团队发展计划团队1个、教育部新世纪青年人才获得者4人。占地面积150余亩，核定床位2500张。
            </p>
            <p>
              医院积极探索公立医院改革之路，是我国最早开展信息化建设的单位，在医院数字化建设综合评分名列第一，荣获“卫生部数字化试点示范医院”称号。医院以网络医疗服务平台为纽带，促进优质医疗资源下基层，推动优质医疗资源城乡合理分布，创建了“大院带县院”、“县院带乡镇”、“乡镇带村庄”的“省、县、乡三级医疗服务”新体系，受到卫生部的高度评价。目前，医院已拥有托管、合作协作医院72家，100余家网络医疗服务成员单位，2家对口支援单位、1家结对帮扶单位，提供了数千次的远程会诊服务。
            </p>
          </div>
          <div class="titlesmall">
            <div class="border"><p>获奖荣誉</p></div>
          </div>
          <div class="content">
            <p>
              1988年获浙江省文明医院称号、1995年获全国卫生系统先进集体称号、1997年获全国卫生文化建设先进集体、1998年获省级综合治理先进单位、1999年获全国百佳医院称号、1999年获卫生部抗洪抢险救灾防病先进集体称号、1999-2000年获省级综合治理先进单位称号、1999年获全省卫生系统思想政治工作先进集体称号、2003年获中国科教文卫体工会系统抗击非典先进集体称号、
            </p>
          </div>
          <div class="titlesmall">
            <div class="border"><p>先进设备</p></div>
          </div>
          <div class="content">
            <p>
              医院设备先进，拥有伽马刀、PET/CT、ICT、回旋加速器、直线加速器、多排螺旋CT、ECT、3.0TMRI、DSA、彩色多普勒超声仪、超声手术刀、高能聚集超声系统、全自动生化分析仪、四合一YAG激光、中央监护系统、人工肝治疗支持系统、射频消融仪、各种型号的血液净化机、流式细胞仪以及超高液相质谱仪等总价值近8亿元的医疗设备。
            </p>
          </div>
        </div>
      </div>
      <div class="hospital" v-else-if="activeName === 'address'">
        <div class="detail">
          <p>
            电话:<span>{{ hospitalInfo.phone }}</span>
          </p>
          <p>
            地址:<span>{{ hospitalInfo.address }}</span>
          </p>
        </div>
        <div class="map">
          <baidu-map
            class="bm-view"
            :center="center"
            :zoom="zoom"
            @ready="handler"
            :scroll-wheel-zoom="true"
          >
       <bm-marker
       :position="center"
       :dragging="true"
       animation="BMAP_ANIMATION_BOUNCE"
       :icon="{url: 'http://developer.baidu.com/map/jsdemo/img/fox.gif', size: {width: 300, height: 157}}"
     >
       </bm-marker>
       <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" :autoLocation="true"></bm-geolocation>
    </baidu-map>
        </div>
      </div>
      <div class="hospital" v-else-if="activeName === 'department'">
        <div class="dep">
          <div class="title">
            <div class="border"><p>全部科室</p></div>
          </div>
          <div class="depline" v-for="(item,index) in hospitalInfo.officeVOList" :key="index">
            <div class="departmentname">{{item.officeName}}</div>
            <div class="rightside" v-for="(child,index) in item.childOfficeList" :key="index">
              <div class="singledep">{{child.officeName}}</div>
            </div>
          </div>
        </div>
      </div>
      <div class="hospital" v-else-if="activeName === 'doctor'">
        <div class="single" v-for="(item,index) in hospitalInfo.doctorVOList" :key="index">
          <img :src="item.src" alt="" />
          <strong>{{ item.doctorName }}</strong>
          <p>{{ item.position }}</p>
          <p>{{ item.hospitalName }}</p>
          <p>{{ item.officeName }}</p>
          <p>{{ item.description }}</p>
          <div class="comment">
            <el-button type="primary" @click="toDoctorInfo(item.id,item.officeId,item.hospitalId)"
              >预约挂号</el-button
            >
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
</template>

<script>
export default {
  inject: ["reload"],
  name: "HospitalInfo",
  components: {},
  data() {
    return {
      userName:"",
      roleType:"",
      userInfo:"",
      src: "",
      token: localStorage.getItem("token"),
      center: { lng: 0, lat: 0 },
      zoom: 3,
      activeName: "detail",
      checked: true,
      content: "",
      hospitalList: [],
      doctorList: [],
      departmentList: [],
      hospitalInfo: "",
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
    toBaiDuMap(){
        this.activeName = "address"
    },
    exit() {
      localStorage.removeItem("token");
      localStorage.removeItem("userInfo");
      this.reload();
    },
    handler({ BMap, map }) {
      console.log(BMap, map);
      (this.center.lng = this.hospitalInfo.longitudes),
        (this.center.lat = this.hospitalInfo.latitudes),
        (this.zoom = 15);
    },
    toDoctorInfo(val1, val2, val3) {
      this.$router.push({ path: "/doctorAppointment", query: { 
        id: val1,
        officeId: val2,
        hospitalId: val3, 
        } });
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
    ToDoctorWork() {
      this.$router.push("/DoctorWork");
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
        .get("http://localhost:8088/hospital/id", {
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
    setMenuClicked() {
      this.$router.push("/Appointment");
    },
  },
  mounted() {
    this.$nextTick(() => {
      this.getHospital();
      console.log(this.$route.query.hospitalId);
      this.userInfo = JSON.parse(localStorage.getItem("userInfo"));
      if (this.userInfo != null) {
          this.userName = JSON.parse(localStorage.getItem("userInfo")).userName;
          this.roleType = JSON.parse(localStorage.getItem("userInfo")).roleType;
      }
      if (this.$route.query.activeName != null) {
          this.activeName = this.$route.query.activeName  
      }
    });
  },
};
</script>

<style scoped>
.bm-view {
  width: 1160px;
  height: 590px;
  border: yellow 1px solid;
}
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
  top: 55px;
}
.doctorinfo {
  padding: 20px;
  overflow: hidden;
}
.doctorinfo .doctorpic {
  width: 100px;
}
.doctorinfo .doctorpic img {
  height: 66px;
  float: left;
  margin-right: 20px;
}
.doctorinfo .doctordetail {
  width: 955px;
  float: left;
  line-height: 24px;
}
.doctordetail h1 {
  margin-top: 0;
  font-size: 24px;
  line-height: 26px;
  font-weight: 400;
  color: #333;
}
.doctorinfo .doctordetail p {
  margin-top: 10px;
  color: #666666;
  font-size: 16px;
  overflow: hidden;
}

.doctorinfo .doctordetail p span {
  color: #333;
  float: right;
  width: 840px;
}
.status {
  display: flex;
  justify-content: flex-start;
  width: 100%;
  height: 42px;
  background-color: #e4e4e4;
}
.status .single {
  line-height: 42px;
  text-align: center;
  color: #000000;
  width: 142px;
}
.status .single:hover {
  background-color: #3ecada;
  color: white;
}
.filter {
  padding-top: 12px;
  padding-bottom: 24px;
  color: #999;
}
.el-dropdown {
  bottom: 56px;
  left: 40px;
}
.filter .bydep {
  padding-top: 20px;
  margin-bottom: 15px;
  overflow: hidden;
}

.filter .bydep .tabnav em {
  color: #333;
  display: inline-block;
  vertical-align: middle;
  margin: 0 12px;
}

.filter .bydep .tabnav em:hover {
  color: orange;
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
  margin-top: 65px;
  padding: 0 20px 20px;
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
.titlesmall {
  font-size: 22px;
  font-weight: 700;
  color: #000;
  height: 45px;
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
  height: 120px;
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
  bottom: 50px;
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

.hospital .introduction .titlesmall .border {
  width: 120px;
  height: 30px;
  color: #333333;
  line-height: 30px;
  border-left: #74bc31 5px solid;
  font-weight: 400;
}

.hospital .introduction .titlesmall .border p {
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
    color: #74BC31;
    vertical-align: center;
    padding-left: 20px;
    margin-left: 10px;
    text-decoration: none;
    cursor: pointer;
  }
  .el-icon-map-location:hover{
    text-decoration: underline;
    color: orange;
  }

.hospital .map {
  width: 1160px;
  height: 590px;
  padding: 10px;
}

.hospital .dep {
  width: 100%;
  height: 100%;
}

.depline {
  overflow: hidden;
  padding: 10px 0 1px 0;
  border-bottom: rgba(20, 81, 154, 0.2) dashed 1px;
}
.dep .depline .departmentname {
  width: 100px;
  height: 40px;
  float: left;
  font-size: 20px;
  font-weight: 600;
  color: #333333;
}
.dep .depline .rightside {
  float: right;
  width: 300px;
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
  margin-top: 50px;
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
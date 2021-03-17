<template>
  <div>
    <section class="topBar">
      <div class="wrap">
        <div class="topLogo">
          <a href=""></a>
        </div>
        <div class="topSevers">
          <el-dropdown size="medium">
            <span class="el-dropdown-link">网站服务<i class="el-icon-arrow-down el-icon--right"></i></span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-plus">新闻</el-dropdown-item>
              <el-dropdown-item icon="el-icon-circle-plus">诊疗</el-dropdown-item>
              <el-dropdown-item icon="el-icon-circle-plus-outline">药品</el-dropdown-item>
              <el-dropdown-item icon="el-icon-check">名医在线</el-dropdown-item>
              <el-dropdown-item icon="el-icon-circle-check">特色医院</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        <div class="topLogin">
          <a href="">登陆</a>
          <a href="">注册</a>
        </div>
        <div class="topUser">
          <el-avatar :src="src" size="small"></el-avatar>
          <el-dropdown szie="medium">
            <span class="el-dropdown-link">Cxx<i class="el-icon-arrow-down el-icon--right"></i></span>
            <el-dropdown-menu class="for-user" slot="dropdown">
              <el-dropdown-item @click.native="">我的预约</el-dropdown-item>
              <el-dropdown-item @click.native="">就诊人管理</el-dropdown-item>
              <el-dropdown-item @click.native="">我的关注</el-dropdown-item>
              <el-dropdown-item @click.native="exit">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        <div class="topSearch">
          <el-input class="input-with-select" placeholder="请输入医院名/医生名/疾病/科室" v-model="content">
            <el-button icon="el-icon-search" slot="append">搜索</el-button>
          </el-input>
        </div>
        <div class="topLink">
          <a href="">找医生</a>
          <a href="">找医院</a>
        </div>
      </div>
    </section>
    <div class="wrap">
      <div style="margin-top: 20px"></div>
      <section class="quickSearch">
        <div class="title">快速查找</div>
        <div class="inlinePut">城市
          <el-select placeholder="请选择" size="small" style="width: 90px;border-radius: 20px;padding-left: 20px"
                     v-model="province">
            <el-option
              :key="item.value"
              :label="item.label"
              :value="item.value"
              v-for="item in provinces">
            </el-option>
          </el-select>
          <el-select placeholder="请选择" size="small" style="width: 90px;border-radius: 20px;padding-left: 20px"
                     v-model="city">
            <el-option
              :key="item.value"
              :label="item.label"
              :value="item.value"
              v-for="item in cities">
            </el-option>
          </el-select>
        </div>
        <div class="inlinePut">医院
          <el-select placeholder="请选择" size="small" style="width: 200px;border-radius: 20px;padding-left: 20px"
                     v-model="city">
            <el-option
              :key="item.value"
              :label="item.label"
              :value="item.value"
              v-for="item in cities">
            </el-option>
          </el-select>
        </div>
        <div class="inlinePut">科室
          <el-select placeholder="请选择" size="small" style="width: 200px;border-radius: 20px;padding-left: 20px"
                     v-model="city">
            <el-option
              :key="item.value"
              :label="item.label"
              :value="item.value"
              v-for="item in cities">
            </el-option>
          </el-select>
        </div>
        <div class="searchButton">立即查找</div>
        <div class="totalData">
          已收录全国<b>{{cityNumber}}</b>个城市，<b>{{hospitalNumber}}</b>家医院。
        </div>
      </section>
      <section class="indexSlide">
        <img alt="" src="../assets/logo.png">
      </section>
      <div class="clear"></div>
      <section class="block tab">
        <div class="title">快速找医生
          <div :class="{ active: value==1 }" @click="value = 1"
               class="inactive" style="margin:0 0 0px 21px">按疾病找
          </div>
          <div :class="{ active: value==2 }" @click="value = 2"
               class="inactive" style="margin:0 0 0px 21px">按科室找
          </div>
        </div>
        <div :key="item" class="channels"
             v-for="item in channellist"
             v-if="value ==1"
        >
          <a>{{item}}</a>
        </div>
        <div class="diseaselist" v-show="value==1">
          <ul>
            <li>
              <a href="">乳腺癌</a>
              <a href="">阴道炎</a>
              <a href="">卵巢囊肿</a>
              <a href="">子宫肌瘤</a>
              <a href="">乳腺增生</a>
              <a href="">月经失调</a>
              <a href="">人流</a>
            </li>
            <li>
              <a href="">新生儿黄疸</a>
              <a href="">小儿支气管炎</a>
              <a href="">小儿支原体肺炎</a>
              <a href="">小儿遗尿</a>
              <a href="">小儿疝气</a>
              <a href="">小儿癫痫</a>
              <a href="">小儿哮喘</a>
            </li>
            <li>
              <a href="">前列腺炎</a>
              <a href="">男性不育</a>
              <a href="">包皮过长</a>
              <a href="">早泄</a>
              <a href="">阳痿</a>
              <a href="">精索静脉曲张</a>
            </li>
            <li>
              <a href="">高血压</a>
              <a href="">糖尿病</a>
              <a href="">冠心病</a>
              <a href="">通风</a>
              <a href="">心肌梗塞</a>
              <a href="">骨质疏松</a>
              <a href="">白内障</a>
            </li>
          </ul>
        </div>

        <div class="deplist" v-if="value ==2">
          <div>
            <ul>
              <dt>内科</dt>
              <li><a href="">呼吸内科</a></li>
              <li><a href="">心血管内科</a></li>
              <li><a href="">神经内科</a></li>
              <li><a href="">血液内科</a></li>
              <li><a href="">内分泌科</a></li>
              <li><a href="">风湿内科</a></li>
            </ul>
            <ul>
              <dt>外科</dt>
              <li><a href="">泌尿外科</a></li>
              <li><a href="">乳腺外科</a></li>
              <li><a href="">神经外科</a></li>
              <li><a href="">心胸外科</a></li>
              <li><a href="">肝胆外科</a></li>
              <li><a href="">肛肠外科</a></li>
              <li><a href="">甲状腺外科</a></li>
              <li><a href="">胃肠外科</a></li>
              <li><a href="">整形外科</a></li>
            </ul>
            <ul>
              <dt>妇产科</dt>
              <li><a href="">妇科</a></li>
              <li><a href="">产科</a></li>
              <li><a href="">计划生育科</a></li>
              <li><a href="">产前检查科</a></li>
              <li><a href="">遗传科</a></li>
            </ul>
          </div>
          <div>
            <ul>
              <dt>儿科</dt>
              <li><a href="">小儿内科</a></li>
              <li><a href="">小儿外科</a></li>
              <li><a href="">新生儿科</a></li>
              <li><a href="">小儿消化科</a></li>
              <li><a href="">小儿呼吸科</a></li>
              <li><a href="">小儿免疫科</a></li>
            </ul>
            <ul>
              <dt>骨科</dt>
              <li><a href="">骨外科</a></li>
              <li><a href="">脊柱外科</a></li>
              <li><a href="">关节外科</a></li>
              <li><a href="">手外科</a></li>
            </ul>
            <ul>
              <dt>五官科</dt>
              <li><a href="">眼科</a></li>
              <li><a href="">头颈外科</a></li>
              <li><a href="">耳鼻喉科</a></li>
              <li><a href="">口腔科</a></li>
            </ul>
          </div>
        </div>
      </section>
      <div class="mainframe">
        <section class="block tab">
          <div class="title">
            <span>推荐医院</span>
            <el-menu :default-active="activeIndex_menu1" @select="handleSelect_menu1"
                     class="el-menu-demo" mode="horizontal"
                     style="display: inline-block"
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
            <a href="">更多</a>
          </div>
          <div class="tabcontent" v-show="activeIndex_menu1==1">
            <div :key="item.name" class="single" v-for="item in hoslist">
              <img :src="item.src" alt="">
              <b>{{item.name}}</b>
              <span class="about">{{item.introduction}}</span>
              <span class="about">{{item.tel}}</span>
            </div>
          </div>
        </section>
      </div>
      <div class="content">
        <section class="block">
          <div class="title">
            医院资讯
            <a href="">更多</a>
          </div>
          <div class="newslist">
            <li><span>臻宝暴睡20小时送往中心医院抢救，仍未苏醒迹象</span></li>
            <li><span>某校发生集体食物中毒事件</span></li>
            <li><span>健康的秘诀是什么？</span></li>
            <li><span>臻宝暴睡20小时送往中心医院抢救，仍未苏醒迹象</span></li>
            <li><span>某校发生集体食物中毒事件</span></li>
            <li><span>健康的秘诀是什么？</span></li>
            <li><span>臻宝暴睡20小时送往中心医院抢救，仍未苏醒迹象</span></li>
            <li><span>某校发生集体食物中毒事件</span></li>
            <li><span>健康的秘诀是什么？</span></li>
          </div>
        </section>
        <section class="block">
          <div class="title">
            就诊评价
            <a href="">更多</a>
          </div>
          <div class="newslist">
            <li><span>臻宝暴睡20小时送往中心医院抢救，仍未苏醒迹象</span></li>
            <li><span>某校发生集体食物中毒事件</span></li>
            <li><span>健康的秘诀是什么？</span></li>
            <li><span>臻宝暴睡20小时送往中心医院抢救，仍未苏醒迹象</span></li>
            <li><span>某校发生集体食物中毒事件</span></li>
            <li><span>健康的秘诀是什么？</span></li>
            <li><span>臻宝暴睡20小时送往中心医院抢救，仍未苏醒迹象</span></li>
            <li><span>某校发生集体食物中毒事件</span></li>
            <li><span>健康的秘诀是什么？</span></li>
          </div>
        </section>
      </div>
      <div class="mainframe">
        <section class="block tab">
          <div class="title">
            <span>推荐医生</span>
            <el-menu :default-active="activeIndex_menu2" @select="handleSelect_menu2"
                     class="el-menu-demo" mode="horizontal"
                     style="display: inline-block"
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
            <a href="">更多</a>
          </div>
          <div class="tabcontent" v-show="activeIndex_menu2==1">
            <div :key="item.name" class="person" v-for="item in hoslist">
              <a href=""><img alt="" src="http://pimg.39.net//PictureLib/A/f3/c4/20170104/g/872054.jpg">
                <strong><b>XXX</b></strong>
                <p>XXX</p>
                <span>XXXX</span>
              </a>
            </div>
          </div>
        </section>
      </div>
      <div class="clear"></div>
      <section class="block tab">
        <section class="block tab">
          <div class="title">
            <span>权威文章</span>
            <el-menu :default-active="activeIndex_menu3" @select="handleSelect_menu3"
                     class="el-menu-demo" mode="horizontal"
                     style="display: inline-block"
            >
              <el-menu-item index="1">内科</el-menu-item>
              <el-menu-item index="2">外科</el-menu-item>
              <el-menu-item index="3">妇科</el-menu-item>
              <el-menu-item index="4">男科</el-menu-item>
              <el-menu-item index="5">儿科</el-menu-item>
              <el-menu-item index="6">肿瘤科</el-menu-item>
              <el-menu-item index="7">不孕不育</el-menu-item>
            </el-menu>
            <a href="">更多</a>
          </div>
          <div class="tabcontent" v-show="activeIndex_menu3==1">
            <div :key="item.name" class="single" v-for="item in hoslist">
              <strong>XXXX</strong>
              <p>
                <a href=""><img alt="" class="pic" src=""></a>
                <b>XXXXX</b>
                XXXXX
              </p>
            </div>
          </div>
        </section>
      </section>
    </div>
    <section class="footer">
      <div class="aboutus">
        <div class="wrap">
          <div class="contact">
            <span>关注或联系我们</span>
            <p>欢迎致电：020-85501999</p>
            <p>医院入驻合作伙伴：XXXXXXXXX有限公司</p>
          </div>
          <div class="qrCode">
            <img alt="" src="../assets/logo.png">
            <strong>XXXX网</strong>
            <span>XXXXXX</span>
            <p>扫一扫关注官方微信</p>
          </div>
          <div class="qrCode">
            <img alt="" src="../assets/logo.png">
            <strong>XXXX网</strong>
            <span>XXXXXX</span>
            <p>扫一扫关注官方微信</p>
          </div>
        </div>
      </div>
      <div class="aboutus">
        <div class="wrap">
          <div class="bottomInfo">
            <a href="">网站简介</a>
            <a href="">媒体报道</a>
            <a href="">网络营销</a>
            <a href="">产品中心</a>
            <a href="">联系方式</a>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
  export default {
    name: 'Index',
    data() {
      return {
        content: '',
        src: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
        province: '',
        city: '',
        department: "",
        provinces: [{
          value: "zhejiang",
          label: "浙江省"
        }],
        cities: [{
          value: 'hangzhou',
          label: "杭州市",
        }, {
          value: 'jinhua',
          label: "金华市",
        }, {
          value: 'ningbo',
          label: "宁波",
        },
          {
            value: 'wenzhou',
            label: "温州市"
          },
        ],
        departments: [{
          value: "",
          label: "",
        }, {
          value: "",
          label: "",
        }, {
          value: "",
          label: "",
        }, {
          value: "",
          label: "",
        }, {
          value: "",
          label: "",
        }, {
          value: "",
          label: "",
        }, {
          value: "",
          label: "",
        },
        ],
        channellist: [
          "肝病", "不孕不育", "腰椎间盘突出", "颈椎病", "胆结石", "鼻炎", "白癜风", "甲亢", "肾结石", "性病", "抑郁症", "癫痫",
          "胃病", "头疼", "口臭", "便秘", "狐臭", "皮肤病",
        ],
        hoslist: [
          {
            name: "浙江大学附属第一医院",
            introduction: "三级甲等，综合经营",
            tel: "电话：0571-87236352(23212)",
            src: "https://pimg.39.net/PictureLib/A/f4/c3/20181212/org/7430482.jpg",
          },
          {
            name: "浙江大学附属第二医院",
            introduction: "三级甲等，综合经营",
            tel: "电话：0571-87236352",
            src: "https://pimg.39.net/PictureLib/A/f4/c3/20181212/org/7430482.jpg",
          },
          {
            name: "浙江大学附属第三医院",
            introduction: "三级甲等，综合经营",
            tel: "电话：0571-87236352",
            src: "https://pimg.39.net/PictureLib/A/f4/c3/20181212/org/7430482.jpg",
          },
          {
            name: "浙江大学附属第四医院",
            introduction: "三级甲等，综合经营",
            tel: "电话：0571-87236352",
            src: "https://pimg.39.net/PictureLib/A/f4/c3/20181212/org/7430482.jpg",
          },
          {
            name: "浙江大学附属第五医院",
            introduction: "三级甲等，综合经营",
            tel: "电话：0571-87236352",
            src: "https://pimg.39.net/PictureLib/A/f4/c3/20181212/org/7430482.jpg",
          },
        ],
        cityNumber: "189",
        hospitalNumber: "2000",
        value: "1",
        activeIndex_menu1: '1',
        activeIndex_menu2: '1',
        activeIndex_menu3: "1",
      }
    },
    methods: {
      handleSelect_menu1(key) {
        this.activeIndex_menu1 = key;
      },
      handleSelect_menu2(key) {
        this.activeIndex_menu2 = key;
      },
      handleSelect_menu3(key) {
        this.activeIndex_menu3 = key;
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

  .topBar {
    width: 100%;
    min-width: 1200px;
    height: 55px;
    line-height: 55px;
    color: #fff;
    background: #38abbb;
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
    width: 130px;
    height: 55px;
  }

  .topLogin {
    float: right;
    width: 80px;
    text-align: right;
  }

  .topLogin a {
    text-decoration: none;
    color: white;
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
    margin-right: 20px;
    text-decoration: none;
    color: white;
  }

  .quickSearch {
    float: right;
    width: 282px;
    height: 330px;
    background: #fff;
    border-radius: 5px;
    margin-top: 20px;
    padding: 0 20px 0px;
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
    width: 858px;
    height: 330px;
    background: #38abbb;
    border-radius: 5px;
    margin-top: 20px;
    float: left;
    overflow: hidden;
    position: relative;
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
    background: url(https://image.39.net/dist/yyk/v3/pc/images/avatar_woman.png) left center no-repeat;
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
    background: url(https://image.39.net/dist/yyk/v3/pc/images/arrow_more.png) right center no-repeat;
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
    float: left;
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
    background: url(https://image.39.net/dist/yyk/v3/pc/images/icon_footerphone.png) left center no-repeat;
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

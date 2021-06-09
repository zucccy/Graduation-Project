import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";

const routes = [
  {
    // 重定向，用来指向一打开网页就跳转到哪个路由
    path: "/",
    redirect: "/hospitalTable",
  },
  {
    // 首页
    path: "/",
    name: "Home",
    component: Home,
    children: [// 开始嵌套路由，这下面的所有路由都是Home路由的子路由
      {
        path: "/hospitalTable",
        name: "hospitaltable",
        meta: {
          title: "医院表格",
        },
        component: () =>
          import(
            /* webpackChunkName: "dashboard" */
            //引入Dashboard.vue组件
            "../views/HospitalTable.vue"
          ),
      },
      {
        path: "/officeTable",
        name: "officetable",
        meta: {
          title: "科室表格",
        },
        component: () =>
          import(
            /* webpackChunkName: "table" */
            "../views/OfficeTable.vue"
          ),
      },
      {
        path: "/form",
        name: "baseform",
        meta: {
          title: "表单",
        },
        component: () =>
          import(
            /* webpackChunkName: "form" */
            "../views/BaseForm.vue"
          ),
      },
      {
        path: "/doctorTable",
        name: "doctortable",
        meta: {
          title: "医生表格",
        },
        component: () =>
          import(
            /* webpackChunkName: "tabs" */
            "../views/DoctorTable.vue"
          ),
      },
      {
        path: "/userTable",
        name: "usertable",
        meta: {
          title: "用户表格",
        },
        component: () =>
          import(
            /* webpackChunkName: "icon" */
            "../views/UserTable.vue"
          ),
      },
      // {
      //   path: "/charts",
      //   name: "basecharts",
      //   meta: {
      //     title: "图表",
      //   },
      //   component: () =>
      //     import(
      //       /* webpackChunkName: "charts" */
      //       "../views/BaseCharts.vue"
      //     ),
      // },
      // {
      //   path: "/donate",
      //   name: "donate",
      //   meta: {
      //     title: "鼓励作者",
      //   },
      //   component: () =>
      //     import(
      //       /* webpackChunkName: "donate" */
      //       "../views/Donate.vue"
      //     ),
      // },
      // {
      //   path: "/permission",
      //   name: "permission",
      //   meta: {
      //     title: "权限管理",
      //     permission: true,
      //   },
      //   component: () =>
      //     import(
      //       /* webpackChunkName: "permission" */
      //       "../views/Permission.vue"
      //     ),
      // },
      // {
      //   path: "/i18n",
      //   name: "i18n",
      //   meta: {
      //     title: "国际化语言",
      //   },
      //   component: () =>
      //     import(
      //       /* webpackChunkName: "i18n" */
      //       "../views/I18n.vue"
      //     ),
      // },
      // {
      //   path: "/upload",
      //   name: "upload",
      //   meta: {
      //     title: "上传插件",
      //   },
      //   component: () =>
      //     import(
      //       /* webpackChunkName: "upload" */
      //       "../views/Upload.vue"
      //     ),
      // },
      {
        path: "/404",
        name: "404",
        meta: {
          title: "找不到页面",
        },
        component: () =>
          import(
            /* webpackChunkName: "404" */
            "../views/404.vue"
          ),
      },
      {
        path: "/403",
        name: "403",
        meta: {
          title: "没有权限",
        },
        component: () =>
          import(
            /* webpackChunkName: "403" */
            "../views/403.vue"
          ),
      },
    ],
  },
  {
    path: "/login",
    name: "Login",
    meta: {
      title: "登录",
    },
    component: () =>
      import(
        /* webpackChunkName: "login" */
        "../views/Login.vue"
      ),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
/*
meta元数据，在设置前端路由时给出
就在本文件上面，权限测试中meta里有permission
*/
//全局前置守卫
//根据不同的条件让这个链接跳转到不同路由
//next()方法是成功进入页面，不另外跳转的意思
router.beforeEach((to, from, next) => {
  document.title = `${to.meta.title} | 后台管理系统`;
  const role = localStorage.getItem("ms_username");
  if (!role && to.path !== "/login") {
    next("/login");
  } else if (to.meta.permission) {
    // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
    role === "admin" ? next() : next("/403");
  } else {
    next();
  }
});

export default router;

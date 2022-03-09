import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  routes: [
    {
      path: "/",
      alias: "/profiles",
      name: "profiles",
      component: () => import("../components/ProfileList")
    },
    {
      path: "/profiles/:id",
      name: "profile-details",
      component: () => import("../components/Profile")
    },
    {
      path: "/add",
      name: "add",
      component: () => import("../components/AddProfile")
    }
  ],
  history: createWebHistory()
})

export default router

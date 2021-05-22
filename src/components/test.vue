<template>
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
    >
    </bm-marker>
  </baidu-map>
</template>
<script>
export default {
  data() {
    return {
      center: { lng: 120, lat: 120 },
      zoom: 3,
    };
  },
  methods: {
    // handler({ BMap, map }) {
    //   console.log(BMap, map);
    //   this.center.lng = 120;
    //   this.center.lat = 30;
    //   this.zoom = 15;
    // },
  },
};
</script>
<style>
.bm-view {
  width: 1160px;
  height: 590px;
  border: yellow 1px solid;
}
</style>
<script setup>
import {ref} from "vue";

const isActive = ref(false)

const switchButton = () => {
  isActive.value=!isActive.value
}
</script>

<template>
  <div id="g-btn" @click="switchButton" :class="{'active' : isActive}"></div>
</template>

<style lang="less" scoped>
body {
  background: #d9deea;
}
div {
  position: relative;
  width: 120px;
  height: 40px;
  background:
      radial-gradient(circle at 5% 15px, #fff, #fff 6px, transparent 7px, transparent),
      radial-gradient(circle at 10% 15px, #fff, #fff 1px, transparent 2px, transparent),
      radial-gradient(circle at 15% 18px, #fff, #fff 2.5px, transparent 3.5px, transparent),
      radial-gradient(circle at 10% 18px, #fff, #fff 2.5px, transparent 3.5px, transparent),
      radial-gradient(circle at 20% 10px, #fff, #fff 0.5px, transparent 1.5px, transparent),
      radial-gradient(circle at 7% 30px, #fff, #fff 0.5px, transparent 1.5px, transparent),
      linear-gradient(90deg, #2b303e, #2b303e 50%, #5a81b4 50%, #5a81b4);
  background-repeat: no-repeat;
  background-size: 200% 100%;
  background-position: 100% 0;
  border-radius: 90px;
  box-shadow:
        0 -3px 4px #999,
      inset 0 3px 5px #333,
      0 4px 4px #ffe,
      inset 0 -3px 5px #ddd;
  cursor: pointer;
  overflow: hidden;
  transition: .5s all;
  &::before,
  &::after {
    content: "";
    position: absolute;
    transition: .5s all;
  }
  &::before {
    width: 27.5px;
    height: 27.5px;
    border-radius: 50%;
    background: #e9cb50;
    inset: 7.5px;
    box-shadow:
        0 0 5px #333,
        inset 2px 2px 3px #f8f4e4,
          inset -2px -2px 3px #665613;
    z-index: 1;
  }
  &::after {
    width: 27.5px;
    height: 27.5px;
    inset: 7px;
    border-radius: 50%;
    box-shadow:
        10px 50px 0 10px #fff,
        65px 30px 0 5px #fff,
        95px 20px 0 10px #fff,
        135px 45px 0 5px #fff,
        170px 35px 0 10px #fff,
          195px -5px 0 10px #fff,
        -10px 0 0 50px rgba(255, 255, 255, .2),
        15px 0 0 50px rgba(255, 255, 255, .15),
        40px 0 0 50px rgba(255, 255, 255, .21),
        10px 10px 0 10px #abc1d9,
        70px 25px 0 10px #abc1d9,
        95px 10px 0 10px #abc1d9,
        135px 10px 0 10px #abc1d9,
        155px 15px 0 10px #abc1d9,
          190px -20px 0 10px #abc1d9;
  }
}
div:hover::before {
  filter: contrast(90%) brightness(110%);
  scale: 1.05;
}
div.active {
  background-position: 0 0;

  &::before {
    translate: 80px;
    background:
        radial-gradient(circle at 50% 20px, #939aa5, #939aa5 65px, transparent 70px, transparent),
        radial-gradient(circle at 35% 45px, #939aa5, #939aa5 115px, transparent 120px, transparent),
        radial-gradient(circle at 72% 50px, #939aa5, #939aa5 85px, transparent 90px, transparent),
        radial-gradient(#cbcdda, #cbcdda);
  }
  &::after {
    transform: translate(80px);
    box-shadow:
        10x 3px 0 10px transparent,
        35px 2px 0 5px transparent,
        95px 3px 0 10px transparent,
        135px 5px 0 5px transparent,
        170px 15px 0 10px transparent,
          195px -5px 0 10px transparent,
        10px 0 0 50px rgba(255, 255, 255, .2),
        -15px 0 0 50px rgba(255, 255, 255, .15),
        -40px 0 0 50px rgba(255, 255, 255, .21),
        10px 40px 0 10px transparent,
        70px 35px 0 10px transparent,
        95px 40px 0 10px transparent,
        135px 20px 0 10px transparent,
        155px 15px 0 10px transparent,
          190px -20px 0 10px transparent;
  }
}
</style>
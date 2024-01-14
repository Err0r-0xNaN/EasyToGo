<!--
 * @Date: 2023-12-20 14:12:57
 * @LastEditTime: 2024-01-06 23:47:00
 * @FilePath: \easy-to-go\src\views\console\index.vue
-->
<script setup>
import navVue from '../../components/consoleNav.vue';
import router from '../../router';
</script>
<!--
// 本来想用框架的，但是实在没我想要的样式效果😡，就只能自己写了
//TODO: 完善后台管理系统 //TODO: 完成仪表盘首页
-->
<template>
    <div class="layout">
        <div class="asidePanel" :class="collapsedStyleAside">
            <div class="collapsedSelectPanel" @click="changeCollapsed()">
                <el-icon style="width: 100%; height: 100%; font-size: 2em">
                    <Fold v-show="!isCollapsed" />
                    <Expand v-show="isCollapsed" />
                </el-icon>
            </div>

            <div class="asideBox">
                <!-- LOGO -->
                <div class="logo">
                    <div class="logo-image image" v-show="!isCollapsed && logoType == 'image'">
                        <img src="#" />
                    </div>
                    <div class="logo-image image" v-show="isCollapsed && logoType == 'image'">
                        <img src="#" />
                    </div>
                    <div class="logo-text text" v-show="!isCollapsed && logoType == 'text'">
                        <span>E通达后台管理系统</span>
                    </div>
                    <div class="logo-text text" v-show="isCollapsed && logoType == 'text'">
                        <span>E后台</span>
                    </div>
                </div>

                <!-- Menu -->
                <div class="sidebar">
                    <ul>
                        <li v-for="item in menuItems" :key="item.id" @click="toggleSubMenu(item.id)">
                            {{ item.label }}
                            <ul v-if="item.isExpanded">
                                <li v-for="child in item.children" :key="child.id">
                                    {{ child.label }}
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div class="menuPanel">
                    <div class="menuList">
                        <div class="menuItem">
                            <a class="menuLink" href="#home">
                                <div class="icon">
                                    <el-icon><Odometer /></el-icon>
                                </div>
                                <span v-show="!isCollapsed">首页(仪表盘)</span>
                            </a>
                        </div>
                        <!-- 二级菜单测试 -->
                        <div class="menuItem collapse">
                            <div class="menuLink" href="#" @click="">
                                <div class="icon">
                                    <el-icon><Odometer /></el-icon>
                                </div>
                                <span v-show="!isCollapsed">首页(仪表盘)</span>
                            </div>
                            <div class="childpanel">
                                <div class="menuItem">a</div>
                                <div class="menuItem">b</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="mainContentPanel" :class="styleMainContentPanel">
            <navVue></navVue>
            <div class="main-content">
                <RouterView />
            </div>
        </div>
    </div>
</template>

<script>
import '../../assets/style/console.css';

import request from '../../utils/request';

export default {
    data() {
        return {
            Title: '后台管理面板',
            isCollapsed: false,
            collapsedStyle: '',
            collapsedStyleAside: '',
            styleMainContentPanel: '',
            logoType: 'text', //logo类型：'image'图片 - 'text'文本
            menuItems: [
                {
                    id: 1,
                    label: 'Menu 1',
                    children: [
                        { id: 11, label: 'Submenu 1-1' },
                        { id: 12, label: 'Submenu 1-2' },
                    ],
                },
                {
                    id: 2,
                    label: 'Menu 2',
                },
                // Add other menu items and submenus as needed...
            ],
        };
    },
    methods: {
        changeCollapsed() {
            this.isCollapsed = !this.isCollapsed;
            //console.log(this.isCollapsed);
            this.checkAllStyle();
        },
        checkAllStyle() {
            if (this.isCollapsed) {
                //状态为折叠
                document.documentElement.getElementsByClassName('layout')[0].style.setProperty('--var-AsidePanelWidth', 80 + 'px');
                for (var i = 0; i < document.getElementsByClassName('menuItem')[0].children.length; i++) {
                    document.getElementsByClassName('menuItem')[0].getElementsByClassName('icon')[i].style.margin = '0 auto';
                }
            } else {
                document.documentElement.getElementsByClassName('layout')[0].style.setProperty('--var-AsidePanelWidth', 200 + 'px');
                for (var i = 0; i < document.getElementsByClassName('menuItem')[0].children.length; i++) {
                    document.getElementsByClassName('menuItem')[0].getElementsByClassName('icon')[i].style.margin = '0';
                }
            }
        },
        toggleSubMenu(id) {
            this.menuItems = this.menuItems.map(item => {
                if (item.id === id) {
                    item.isExpanded = !item.isExpanded;
                } else {
                    item.isExpanded = false;
                }
                return item;
            });
        },
    },
    mounted() {},
    beforeMount() {},
};
</script>

<style scoped>
.layout {
    display: flex;
}
.mainContentPanel {
    width: 100%;
    min-height: 100vh;
    z-index: 0;
}
.layout {
    --var-AsidePanelWidth: 200px;
}

.main-content {
    margin-left: var(--var-AsidePanelWidth);
}

.asidePanel {
    position: absolute;
    width: var(--var-AsidePanelWidth);
    height: 100vh;
    z-index: 1;

    box-shadow: var(--shadow-Nav);
    display: block;
    background-color: #fff;

    transition: all 0.3s ease;
}
.collapsedSelectPanel {
    position: absolute;
    right: -57px;
    top: 7px;
    width: 50px;
    height: 50px;
}

.asideBox {
    display: flex;
    flex-direction: column;
}
.logo {
    margin: 10px;
}

.logo-text {
    font: 700 18px '';
    text-align: center;
    white-space: nowrap;
    overflow: hidden;
}

.logo-image img {
    width: 70px;
    height: 70px;
}

.menuPanel {
    white-space: nowrap;
    overflow: hidden;
}

/* TEST===================================================================== */
.menuItem {
    height: 50px;
    display: flex;
    line-height: 50px;
    width: 100%;
}
.menuItem a {
    margin: 0 auto;
}
a.menuLink {
    font-size: 20px;
    overflow: hidden;
    width: 100%;
    display: flex;
}
.menuItem .icon {
    padding: 0 10px;
    font-size: 1.8em;
    display: flex;
    align-items: center;
}

.menuItem.collapse {
    height: auto;
    display: flex;
    flex-direction: column;
}
/* =================================== */
.sidebar {
  background-color: #f4f4f4;
  padding: 20px;
}

ul:hover {
  list-style: none;
  padding: 0;
}

li {
  margin-bottom: 10px;
}

li > ul {
  padding-left: 20px;
  border-left: 2px solid #ccc;
}
</style>

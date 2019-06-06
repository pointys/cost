<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>kicol</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="${pageContext.request.contextPath}/resource/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/ace-skins.min.css"/>
    <script src="${pageContext.request.contextPath}/resource/assets/js/ace-extra.min.js"></script>

    <script src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {



        })
    </script>
</head>
<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small><i
                        class="icon-leaf">helo world</i> kicol的管理系统
                </small>
            </a>
        </div>
        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="green"><a data-toggle="dropdown"
                                     class="dropdown-toggle" href="#"> <i
                        class="icon-envelope icon-animated-vertical"></i> <span
                        class="badge badge-success">5</span>
                </a>

                    <ul
                            class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header"><i class="icon-envelope-alt"></i>
                            5条消息
                        </li>
                    </ul>
                </li>

                <li class="light-blue"><a data-toggle="dropdown" href="#"
                                          class="dropdown-toggle"> <img class="nav-user-photo"
                                                                        src="${pageContext.request.contextPath}/resource/assets/avatars/user.jpg"
                                                                        alt="Jason's Photo"/> <span
                        class="user-info"> <small>欢迎光临,${userInfo.userName}</small>
						</span> <i class="icon-caret-down"></i>
                </a>
                    <ul
                            class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

                        <li><a href="${pageContext.request.contextPath}/system/myuserInfo"
                               target="mainframe"> <i class="icon-user"></i> 个人资料
                        </a></li>

                        <li class="divider"></li>

                        <li><a href="${pageContext.request.contextPath}/system/login" onClick="if(!confirm('是否确认退出？'))return false;"> <i
                                class="icon-off"></i>
                            退出
                        </a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#"> <span
                class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                    <button class="btn btn-success">
                        <i class="icon-signal"></i>
                    </button>

                    <button class="btn btn-info">
                        <i class="icon-pencil"></i>
                    </button>

                    <button class="btn btn-warning">
                        <i class="icon-group"></i>
                    </button>

                    <button class="btn btn-danger">
                        <i class="icon-cogs"></i>
                    </button>
                </div>

                <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                    <span class="btn btn-success"></span> <span class="btn btn-info"></span>

                    <span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
                </div>
            </div>
            <!-- #sidebar-shortcuts -->

            <ul class="nav nav-list">
                <li class="active"><a href="${pageContext.request.contextPath}/system/main" target="mainframe">
                    <i class="icon-dashboard"></i> <span class="menu-text">
								控制台 </span>
                </a></li>
                <!--循环所有的功能菜单-->
                <c:forEach var="parent" items="${menuList }">

                    <!-- 判断循环的菜单是否为一级菜单 -->
                    <c:if test="${parent.pMenuId==0 }">
                        <!-- 一级菜单样式 -->
                        <li><a href="javascript:void(0)" target="mainframe"
                               class="dropdown-toggle"> <i class="icon-desktop"></i> <span
                                class="menu-text"> ${parent.menuName } </span> <b
                                class="arrow icon-angle-down"></b>
                        </a> <!-- 二级菜单样式 -->
                            <ul class="submenu">
                                <!-- 循环判断二级菜单 -->
                                <c:forEach var="child" items="${menuList }">
                                    <!-- 判断该子菜单是否是该一级菜单的子菜单 -->
                                    <c:if test="${child.pMenuId==parent.menuId }">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/${child.menuUrl }"
                                               target="mainframe">
                                                <i class="icon-double-angle-right"></i>
                                                    ${child.menuName}
                                            </a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-double-angle-left"
                   data-icon1="icon-double-angle-left"
                   data-icon2="icon-double-angle-right"></i>
            </div>

            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'collapsed')
                } catch (e) {
                }
            </script>
        </div>

        <div class="main-content" id="mains">
            <iframe id="mainframe" name="mainframe" src="${pageContext.request.contextPath}/system/main"
                    style="width: 100%; border: 0px;"></iframe>

        </div>

        <script type="text/javascript">
            var height = jQuery(window).height() - 50;
            jQuery("#mainframe").attr("height", "" + height + "px;");
        </script>

        <div class="ace-settings-container" id="ace-settings-container">
            <div class="btn btn-app btn-xs btn-warning ace-settings-btn"
                 id="ace-settings-btn">
                <i class="icon-cog bigger-150"></i>
            </div>

            <div class="ace-settings-box" id="ace-settings-box">
                <div>
                    <div class="pull-left">
                        <select id="skin-colorpicker" class="hide">
                            <option data-skin="default" value="#438EB9">#438EB9</option>
                            <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                            <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                            <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                        </select>
                    </div>
                    <span>&nbsp; 选择皮肤</span>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2"
                           id="ace-settings-navbar"/> <label class="lbl"
                                                             for="ace-settings-navbar"> 固定导航条</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2"
                           id="ace-settings-sidebar"/> <label class="lbl"
                                                              for="ace-settings-sidebar"> 固定滑动条</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2"
                           id="ace-settings-breadcrumbs"/> <label class="lbl"
                                                                  for="ace-settings-breadcrumbs">固定面包屑</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2"
                           id="ace-settings-rtl"/> <label class="lbl"
                                                          for="ace-settings-rtl">切换到左边</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2"
                           id="ace-settings-add-container"/> <label class="lbl"
                                                                    for="ace-settings-add-container"> 切换窄屏 <b></b>
                </label>
                </div>
            </div>
        </div>
    </div>

    <a href="#" id="btn-scroll-up"
       class="btn-scroll-up btn btn-sm btn-inverse"> <i
            class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>

<script type="text/javascript">
    if ("ontouchend" in document)
        document
            .write("<script src='${pageContext.request.contextPath}/resource/assets/js/jquery.mobile.custom.min.js'>"
                + "<" + "script>");
</script>
<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/assets/js/typeahead-bs2.min.js"></script>


<script src="${pageContext.request.contextPath}/resource/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/assets/js/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/assets/js/jquery.sparkline.min.js"></script>


<script src="${pageContext.request.contextPath}/resource/assets/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/assets/js/ace.min.js"></script>


<script type="text/javascript">
    jQuery(function ($) {
        $('.easy-pie-chart.percentage')
            .each(
                function () {
                    var $box = $(this).closest('.infobox');
                    var barColor = $(this).data('color')
                        || (!$box.hasClass('infobox-dark') ? $box
                                .css('color')
                            : 'rgba(255,255,255,0.95)');
                    var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)'
                        : '#E2E2E2';
                    var size = parseInt($(this).data('size')) || 50;
                    $(this)
                        .easyPieChart(
                            {
                                barColor: barColor,
                                trackColor: trackColor,
                                scaleColor: false,
                                lineCap: 'butt',
                                lineWidth: parseInt(size / 10),
                                animate: /msie\s*(8|7|6)/
                                    .test(navigator.userAgent
                                        .toLowerCase()) ? false
                                    : 1000,
                                size: size
                            });
                })

        $('.sparkline').each(
            function () {
                var $box = $(this).closest('.infobox');
                var barColor = !$box.hasClass('infobox-dark') ? $box
                    .css('color') : '#FFF';
                $(this).sparkline('html', {
                    tagValuesAttribute: 'data-values',
                    type: 'bar',
                    barColor: barColor,
                    chartRangeMin: $(this).data('min') || 0
                });
            });

        var placeholder = $('#piechart-placeholder').css({
            'width': '90%',
            'min-height': '150px'
        });
        var data = [{
            label: "social networks",
            data: 38.7,
            color: "#68BC31"
        }, {
            label: "search engines",
            data: 24.5,
            color: "#2091CF"
        }, {
            label: "ad campaigns",
            data: 8.2,
            color: "#AF4E96"
        }, {
            label: "direct traffic",
            data: 18.6,
            color: "#DA5430"
        }, {
            label: "other",
            data: 10,
            color: "#FEE074"
        }]
    })
</script>


</body>
</html>

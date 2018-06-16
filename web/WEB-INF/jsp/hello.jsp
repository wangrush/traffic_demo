<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page
        import="com.zw.Node" %><%-- Created by IntelliJ IDEA. User: ZhangWang Date: 2018/6/14 Time: 16:19 To change this template use File | Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>交通数据网络展示</title> <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/echarts/4.1.0/echarts.min.js"></script>--%>
    <script type="text/javascript"
            src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
    <script type="text/javascript"
            src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
    <script type="text/javascript"
            src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
    <script type="text/javascript"
            src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
    <script type="text/javascript"
            src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
    <script type="text/javascript"
            src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
    <script type="text/javascript"
            src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
    <script type="text/javascript"
            src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
</head>
<body>
<div class="panel">
    <div class="jumbotron">
        <h2 style="text-align: center">交通数据网络展示</h2>
    </div>
    <div style="height: 600px;width: 100%">
        <div id="data" style="width: 50%; height: 100%; float: left;overflow:
         auto">
            <table class="table col-md-7">
                <tr>
                    <td>案件名称</td>
                    <td>处罚部门</td>
                    <td>处罚结果</td>
                    <td>记分</td>
                    <td>驾驶人姓名</td>
                    <td>车牌号码</td>
                    <td>处罚类别</td>
                </tr>
                <c:forEach var="node" items="${nodesList}">
                <tr>
                        <td>${node.name}</td>
                        <td>${node.gscfbm}</td>
                        <td>${node.gscfjg}</td>
                        <td>${node.gsjf}</td>
                        <td>${node.gsjsrxm}</td>
                        <td>${node.gshphm}</td>
                        <td>${node.gscflb}</td>
                </tr>
                </c:forEach>
            </table>
        </div>
        <div id="main" style="width: 50%; height: 100%; float: left"></div>
    </div>
</div>
<script>
    var color = [];
    color['gscflb'] = '#000';
    color['gscfbm'] = '#0f0';
    color['gscfjg'] = '#00f';
    color['gsjf'] = '#f00';
    var myChart = echarts.init(document.getElementById("main"));
    var nodes = [];
    var links = [];
    var i = 0;
    <c:forEach items="${nodesList}" var="node">
    nodes[i++] = {
        name: "${node.name}",
        value: 10
    };
    </c:forEach>


    i = 0;
    <c:forEach var="edge" items="${gscfbm}">
    links[i++] = {
        source: "${edge.nodeA.name}",
        target: "${edge.nodeB.name}",
        lineStyle: {
            width: 2,
            color: color['gscfbm']
        }
    };
    </c:forEach>

    <c:forEach var="edge" items="${gscfjg}">
    links[i++] = {
        source: "${edge.nodeA.name}",
        target: "${edge.nodeB.name}",
        lineStyle: {
            width: 2,
            color: color['gscfjg']
        }
    };
    </c:forEach>

    <c:forEach var="edge" items="${gscflb}">
    links[i++] = {
        source: "${edge.nodeA.name}",
        target: "${edge.nodeB.name}",
        lineStyle: {
            width: 2,
            color: color['gscflb']
        }
    };
    </c:forEach>

    <c:forEach var="edge" items="${gsjf}">
    links[i++] = {
        source: "${edge.nodeA.name}",
        target: "${edge.nodeB.name}",
        lineStyle: {
            width: 2,
            color: color['gsjc']
        }
    };
    </c:forEach>


    var option = {
        series: [
            {
                type: 'graph',
                layout: 'force',
                draggable: true,
                focusNodeAdjacency: true,
                force: {
                    repulsion: 200
                },
                label: {
                    normal: {
                        position: 'right'
                    }
                },
                roam: true,
                data: nodes,
                links: links
            }
        ]
    };

    myChart.setOption(option);
</script>
</body>
</html>
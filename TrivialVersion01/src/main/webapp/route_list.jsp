<%--
  Created by IntelliJ IDEA.
  User: wzx
  Date: 2020/10/5
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>途牛旅游-搜索</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/search.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/getParameter.js"></script>
    <script src="js/myonlyfunction.js"></script>
    <script type="text/javascript">
        //页面加载完成
        $(function () {
            //http://localhost:8080/lvyou_war_exploded/route_list.jsp?cid=1
            var cid = getParameter('cid')
            //加载数据
            //alert(cid)
            load(cid,1,20)
        })

        function load( cid, currentPage, pageSize) {
            //route2Servlet?cid=5&currentPage=1&pageSize=20
            $.get('routeServlet2', {cid: cid, currentPage: currentPage, pageSize: pageSize},
                function (data) {
                    if (data.code == 200) {
                        var pb = data.data;
                        //显示列表数据
                        showList(pb)
                        //显示分页工具条数据
                        showTool2(pb, cid, currentPage, pageSize)
                    }
                }, "json");
        }

    </script>
</head>
<body>
<!--引入头部-->
<div id="header">
    <%@ include file="header.jsp"%>
</div>
<div class="page_one">
    <div class="contant">
        <div class="crumbs">
            <img src="images/search.png" alt="">
            <p>途牛旅行><span>搜索结果</span></p>
        </div>
        <div class="xinxi clearfix">
            <div class="left">
                <div class="header">
                    <span>商品信息</span>
                    <span class="jg">价格</span>
                </div>
                <ul id="route_list">


                </ul>
                <div class="page_num_inf">
                    <i></i> 共
                    <span id="totalPage">12</span>页<span id="totalCount">132</span>条
                </div>
                <div class="pageNum">
                    <ul id="pageNum">

                    </ul>
                </div>
            </div>
            <div class="right">
                <div class="top">
                    <div class="hot">HOT</div>
                    <span>热门推荐</span>
                </div>
                <ul>
                    <li>
                        <div class="left"><img src="images/04-search_09.jpg" alt=""></div>
                        <div class="right">
                            <p>清远新银盏温泉度假村酒店/自由行套...</p>
                            <p>网付价<span>&yen;<span>899</span>起</span>
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="left"><img src="images/04-search_09.jpg" alt=""></div>
                        <div class="right">
                            <p>清远新银盏温泉度假村酒店/自由行套...</p>
                            <p>网付价<span>&yen;<span>899</span>起</span>
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="left"><img src="images/04-search_09.jpg" alt=""></div>
                        <div class="right">
                            <p>清远新银盏温泉度假村酒店/自由行套...</p>
                            <p>网付价<span>&yen;<span>899</span>起</span>
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="left"><img src="images/04-search_09.jpg" alt=""></div>
                        <div class="right">
                            <p>清远新银盏温泉度假村酒店/自由行套...</p>
                            <p>网付价<span>&yen;<span>899</span>起</span>
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="left"><img src="images/04-search_09.jpg" alt=""></div>
                        <div class="right">
                            <p>清远新银盏温泉度假村酒店/自由行套...</p>
                            <p>网付价<span>&yen;<span>899</span>起</span>
                            </p>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<!--引入头部-->
<div id="footer">
    <%@ include file="footer.jsp"%>
</div>
<!--导入布局js，共享header和footer-->

</body>

</html>
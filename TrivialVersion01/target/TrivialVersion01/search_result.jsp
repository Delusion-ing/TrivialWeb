<%--
  Created by IntelliJ IDEA.
  User: wzx
  Date: 2020/10/5
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>途牛旅游-搜索</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/search.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        //页面加载完成
        $(function () {
            //http://localhost:8080/lvyou_war_exploded/search_result.jsp?keyword=长沙
            //location表示整个地址  location.search指 ?keyword=长沙
            //?keyword=%E9%95%BF%E6%B2%99  [?keyword,%E9%95%BF%E6%B2%99]
            var array = location.search.split('=')
            //长沙  -》 %E9%95%BF%E6%B2%99
            var keyword = array[1]
            //解码
            keyword = decodeURI(keyword)
            //加载数据
            load(keyword,1,20)
        })

        function load( keyword, currentPage, pageSize) {
            //http://localhost:8080/lvyou_war_exploded/routeServlet?keyword=%E9%95%BF%E6%B2%99&currentPage=1&pageSize=20
            $.get('routeServlet', {keyword: keyword, currentPage: currentPage, pageSize: pageSize},
                function (data) {
                    if (data.code == 200) {
                        var pb = data.data;
                        //显示列表数据
                        showList(pb)
                        //显示分页工具条数据
                        showTool(pb, keyword, currentPage, pageSize)
                    }
                }, "json");
        }

        function showList(pb) {
            //在哪里显示
            //$("#route_list")
            var lis = ''
            for (var i = 0; i < pb.list.length; i++) {
                var route = pb.list[i]
                //显示成什么样 '+变量名+'
                var li = ' <li>\n' +
                    '                        <div class="img" ><img style="width: 299px;height: 169px" src="' + route.rimage + '" alt=""></div>\n' +
                    '                        <div class="text1">\n' +
                    '                            <p>' + route.rname + '</p>\n' +
                    '                            <br/>\n' +
                    '                            <p>' + route.routeIntroduce + '</p>\n' +
                    '                        </div>\n' +
                    '                        <div class="price">\n' +
                    '                            <p class="price_num">\n' +
                    '                                <span>&yen;</span>\n' +
                    '                                <span>' + route.price + '</span>\n' +
                    '                                <span>起</span>\n' +
                    '                            </p>\n' +
                    '                            <p><a href="route_detail.html">查看详情</a></p>\n' +
                    '                        </div>\n' +
                    '                    </li>'
                //拼接
                lis += li
            }
            $("#route_list").html(lis)
        }

        function showTool(pb, keyword, currentPage, pageSize) {
            //只需要将pageBean里面的3个整数
            $("#totalPage").html(pb.totalPage)
            $("#totalCount").html(pb.totalCount)

            var lis = ''
            //拼接第一，二按钮
            lis += '<li><a href="javascript:load(\'' + keyword + '\',1,' + pageSize + ')">首页</a></li>'

            var min = currentPage - 1;
            if (min < 1) {
                min = 1
            }

            lis += '<li class="threeword"><a href="javascript:load(\'' + keyword + '\',' + min + ',' + pageSize + ')">上一页</a></li>'

            var start = 0
            var end = 0
            //判断当前的页数是否有10页
            if (pb.totalPage < 10) {
                start = 1;
                end = pb.totalPage
            }else{
                //当前有10页
                //如果当前页号是8 ，开始是3，结束是12
                start = currentPage - 5;
                end = currentPage + 4
                //越界判断  start有可能出现小于，end有可能出现大于pb.totalPage
                if(start < 1){
                    start = 1;  //1
                    end = start + 9; //10
                }

                if(end > pb.totalPage){
                    end = pb.totalPage  //26
                    start = end - 9 //17
                }
            }

            for (var i = start; i <= end; i++) {
                if (i == pb.currentPage) { //高亮
                    lis += ' <li class="curPage"><a href="javascript:load(\'' + keyword + '\',' + i + ',' + pageSize + ')">' + i + '</a></li>'
                } else {
                    lis += ' <li><a href="javascript:load(\'' + keyword + '\',' + i + ',' + pageSize + ')">' + i + '</a></li>'
                }

            }
            //拼接倒数一，二按钮
            var max = currentPage + 1;
            if (max > pb.totalPage) {
                max = pb.totalPage
            }

            lis += ' <li class="threeword"><a href="javascript:load(\'' + keyword + '\',' + max + ',' + pageSize + ')">下一页</a></li>'
            lis += '<li class="threeword"><a href="javascript:load(\'' + keyword + '\',' + pb.totalPage + ',' + pageSize + ')">末页</a></li>'

            //显示在pageNum
            $("#pageNum").html(lis)

            //模仿百度，将页面滚动到顶部
            window.scrollTo(0,0)
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
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
            '                               \n' +
            '                            </p>\n' +
            '                            <p><a href="route_detail.html">more  </a></p>\n' +
            '                        </div>\n' +
            '                    </li>'
        //拼接
        lis += li
    }
    $("#route_list").html(lis)
}

function showTool2(pb, cid, currentPage, pageSize) {
    //只需要将pageBean里面的3个整数
    $("#totalPage").html(pb.totalPage)
    $("#totalCount").html(pb.totalCount)

    var lis = ''
    //拼接第一，二按钮
    lis += '<li><a href="javascript:load(\'' + cid + '\',1,' + pageSize + ')">首页</a></li>'

    var min = currentPage - 1;
    if (min < 1) {
        min = 1
    }

    lis += '<li class="threeword"><a href="javascript:load(\'' + cid + '\',' + min + ',' + pageSize + ')">上一页</a></li>'

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
            lis += ' <li class="curPage"><a href="javascript:load(\'' + cid + '\',' + i + ',' + pageSize + ')">' + i + '</a></li>'
        } else {
            lis += ' <li><a href="javascript:load(\'' + cid + '\',' + i + ',' + pageSize + ')">' + i + '</a></li>'
        }

    }
    //拼接倒数一，二按钮
    var max = currentPage + 1;
    if (max > pb.totalPage) {
        max = pb.totalPage
    }

    lis += ' <li class="threeword"><a href="javascript:load(\'' + cid + '\',' + max + ',' + pageSize + ')">下一页</a></li>'
    lis += '<li class="threeword"><a href="javascript:load(\'' + cid + '\',' + pb.totalPage + ',' + pageSize + ')">末页</a></li>'

    //显示在pageNum
    $("#pageNum").html(lis)

    //模仿百度，将页面滚动到顶部
    window.scrollTo(0,0)
}
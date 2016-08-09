<%--
  Created by IntelliJ IDEA.
  User: cyr37179
  Date: 2016/8/4
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test ajax</title>
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.json-2.2.min.js"></script>
    <script type="text/javascript">

        $(document).ready(
                function(){
                    alert("加载页面");
                    jQuery.ajax({
                        type : 'GET',
                        contentType : "application/json",
                        url : 'book/list2',
                        dataType : 'json',
                        success : function(data){
                            alert("success");
                            if(data && data.sucess == "true"){
                                $('#info').html("共" + data.total + "条数据。<br/>");
                                $.each(data.data,function(i,item){
                                    $('#info').append(
                                            "id : " + item.book_id + ",姓名：" + item.name
                                            + ",数量："+item.number);
                                });
                            }
                        },
                        error : function(){
                            alert("error");
                        }
                    });

                    $("#submit").click(function(){
                        var jsonbookinfo = $.toJSON($('#form').serializeObject());
                        alert(jsonbookinfo);
                        jQuery.ajax({
                            type : 'POST',
                            contentType : 'application/json',
                            url : 'book/add',
                            data : jsonbookinfo,
                            dataType : 'json',
                            success : function(data){
                                alert("新增成功");
                            },
                            error : function(data){
                                alert("error");
                            }
                        });
                    });
                }
        );

        function xxxx(){
            alert("xxxxx");
            var jsonbookinfo = $.toJSON($('#form').serializeObject());
            alert(jsonbookinfo);
            jQuery.ajax({
                type : 'POST',
                contentType : 'application/json',
                url : 'book/add',
                data : jsonbookinfo,
                dataType : 'json',
                success : function(data){
                    alert("新增成功");
                },
                error : function(data){
                    alert("error");
                }
            });
        }
        function clickAjax(){
            alert(1111);
            $ajax({
                type : GET,
                url : "http://localhost:8080/book/1000/detail",
                data : {},
                dataType : json,
                sucess : function(data){
                    alert("返回值："+data);
                },
                error :function(){
                    alert("网络连接出错！");
                }

            });
        }

    </script>
</head>
<body>
<div id="info"></div>
<form action="addBook" method="post" id="form">
    图书名称：<input type="text" name="name">
    馆藏数量：<input type="text" name="number">
    <input type="button" value="提交" id="submit"/>
</form>


<h1>-------------------------------------------------------------</h1>
${list[1]}
<a href="http://localhost:8080/book/1000/detail">ajax json </a>

<button>aaaa</button>
<input type="button" onclick="clickAjax()"  value="确定"/>

<h1>--------------------------------------------------------------</h1>


</body>
</html>

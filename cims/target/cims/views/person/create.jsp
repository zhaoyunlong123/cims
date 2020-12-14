<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <title>人员添加</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap-reset.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/css/datepicker.css"
          rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    <%-- 解决页面登陆保留时间的问题 --%>
<%--    <base target="_top"/>--%>
<body>
<section id="container" class="">

    <div class="row">
        <div class="col-lg-12">
            <section class="panel">
                <header class="panel-heading">
                    人员添加
                </header>
                <div class="panel-body">
                    <form class="form-horizontal tasi-form" action="${pageContext.request.contextPath}/person.do"
                          method="post">
                        <input type="hidden" name="kc" value="create"/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="name" placeholder="输入姓名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">身份证号</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="personCard" placeholder="输入姓名证件编号">
                                <span class="help-block">请输入正确的证件编号, 并确认该编号是唯一的!</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">状态</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="state">
                                    <option value="0">请选择</option>
                                    <option value="1">离休</option>
                                    <option value="2">退休</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">职级</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="grade">
                                    <option value="0">请选择</option>
                                    <c:forEach items="${applicationScope.date}" var="date">
                                        <option value="${date.key}">${date.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">起薪日期</label>
                            <div class="col-sm-10">
                                <input class="form-control" type="text" name="startedDate" placeholder="点击这里选择日期..."
                                       id="date" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-2">补贴项目</label>
                            <div class="col-lg-10">
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="hotting" value="1"> 供暖
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="property" value="1"> 物业
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">更改原因</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="reasons" placeholder="请输入更改原因">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label"></label>
                            <div class="col-lg-10">
                                <button id="create" type="button" class="btn btn-success">添加</button>
                                <button type="submit" class="btn btn-success">重置</button>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </div>
</section>

<script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
    $(document).ready(function ()
    {
        $('[name = personCard]').blur(function ()
        {
            //对身份证号的校验
            let card = $('[name=personCard]').val();
            //去除两边的空格后的位数判定，因为直接得到的对象，所以不会为null，只会是空或者是值
            if ($.trim(card).length === 18)
            {

                //1. 构建ajax异步引擎对象
                let request = new XMLHttpRequest();
                //2. 指定请求过程的响应函数
                request.onreadystatechange = function ()
                {
                    /**
                     * 2.1 请求执行状态码
                     * 0：ajax引擎实例化
                     * 1：请求参数已经初始化完毕--如果没有open语句，本条及以后都不会执行
                     * 2：准备调用初始化参数
                     * 3：请求已经发出，但服务器未返回响应--如果没有send语句，本条及以后都不会执行
                     * 4：服务器已经返回了响应，但不代表返回的响应是对的，但一定执行了前三条
                     */
                    if (request.readyState === 4)
                    {
                        /**
                         * 2.2 响应状态码 status
                         */
                        if (request.status === 200)
                        {
                            /**
                             * 2.3 引擎只能识别两种格式的数据
                             * 2.3.1 String类型-->字符串（json格式的字符串）
                             * request.responseText
                             * 2.3.2 xml文档
                             * request.responseXML
                             * @type {string}
                             */
                            let text = request.responseText;
                            let json = JSON.parse(text);
                            alert(json.message);
                            if (!json.success())
                                //提交按钮禁用
                                $('#create').disable();
                        }
                    }
                };
                //3. 配置请求的相关的参数--请求发送的位置   open指的是给到的方式
                request.open('post','${pageContext.request.contextPath}/person.do');
                request.setRequestHeader('content-type','application/x-www-form-urlencoded');
                //4. 发出请求并携带参数
                request.send('kc=card&personCard='+card);
            }
        });

        //绑定提交按钮
        $('#create').click(function ()
        {
            $('form:eq(0)').submit();
        });

        $('#date').datepicker({
            format: 'yyyy-mm-dd'
        });
    });
</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="javakc-zhg">
    <link rel="shortcut icon" href="img/favicon.html">
    <title>人员管理</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap-reset.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/css/datepicker.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet"/>
    <%-- 解决页面登陆保留时间的问题 --%>
<%--    <base target="_top"/>--%>
</head>
<body>
<section id="container" class="">
    <div class="row">
        <div class="col-lg-12">
            <section class="panel">
                <header class="panel-heading">
                    人员管理
                </header>
                <form class="form-inline" role="form" id="personForm"
                      action="${pageContext.request.contextPath}/person.do" method="post">
                    <input type="hidden" id="thisPage" name="thisPage" value="${thisPage}"/>
                    <input type="hidden" id="maxPage" name="maxPage" value="${maxPage}"/>
                    <input type="hidden" id="startNumber" name="startNumber" value="${startNumber}"/>
                    <div class="row">
                        <div class="col-lg-12">
                            <section class="panel">
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label class="">姓名</label>
                                        <input type="text" class="form-control" name="name" value="${params.name}" placeholder="输入姓名">
                                    </div>
                                    <div class="form-group">
                                        <label class="">身份证号</label>
                                        <input type="text" class="form-control" name="card" value="${params.card}" placeholder="请输入身份证号">
                                    </div>
                                    <div class="form-group">
                                        <label class="">状态</label>
                                        <select class="form-control" name="state">
                                            <option value="0">请选择</option>
                                            <option value="1" ${params.state==1?'selected':''}>离休</option>
                                            <option value="2" ${params.state==2?'selected':''}>退休</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="">起薪开始日期</label>
                                        <input type="text" id="sdate" class="form-control" name="startDate" value='<fmt:formatDate value="${params.startDate}" pattern="yyyy-MM-dd"/>' placeholder="请输入开始日期" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label class="">起薪结束日期</label>
                                        <input type="text" id="edate" class="form-control" name="endDate" value='<fmt:formatDate value="${params.endDate}" pattern="yyyy-MM-dd"/>' placeholder="请输入截止日期" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label class="">供暖补贴</label>
                                        <input type="checkbox" name="hotting" value="1" ${params.hotting == 1?'checked':''} class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label class="">物业补贴</label>
                                        <input type="checkbox" name="property" value="1" ${params.property == 1?'checked':''} class="form-control">
                                    </div>
                                    <button type="button" id="search" class="btn btn-success">搜索</button>
                                    <button type="button" id="create" class="btn btn-info">添加</button>
                                    <button type="button" id="batch" class="btn btn-danger">批量删除</button>
                                </div>
                            </section>
                        </div>
                    </div>
                    <table class="table table-striped table-advance table-hover">
                        <thead>
                        <tr>
                            <th><input type="checkbox" id="checkAll"/></th>
                            <th><i class="icon-bullhorn"></i>序号</th>
                            <th><i class="icon-male"></i>姓名</th>
                            <th><i class="icon-bookmark"></i>身份证号</th>
                            <th><i class="icon-edit"></i>状态</th>
                            <th><i class="icon-tags"></i>职级</th>
                            <th><i class="icon-jpy"></i>起薪日期</th>
                            <th><i class="icon-sun"></i>供暖</th>
                            <th><i class="icon-briefcase"></i>物业</th>
                            <th><i class="icon-home"></i>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="entity" varStatus="n">
                            <tr>
                                <th><input type="checkbox" name="ids" value="${entity.id}" /></th>
                                <td>${n.count+params.start}</td>
                                <td>${entity.name}</td>
                                <td>${entity.personCard}</td>
                                <td>
                                    <c:if test="${entity.state == 1}">
                                        <span class="label label-danger label-mini">离休</span>
                                    </c:if>
                                    <c:if test="${entity.state == 2}">
                                        <span class="label label-success label-mini">退休</span>
                                    </c:if>
                                </td>
                                <td>
                                    <c:forEach items="${applicationScope.date}" var="date">
                                        <c:if test="${date.key==entity.grade}">
                                            ${date.value}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <fmt:formatDate value="${entity.startedDate}" pattern="yyyy-MM-dd"></fmt:formatDate>
                                </td>
                                <td>${entity.hotting == 1 ? "是" : "否"}</td>
                                <td>${entity.property == 1 ? "是" : "否"}</td>
                                <td id="${entity.id}">
                                    <button class="btn btn-primary btn-xs" name="pencil" type="button"><i class="icon-pencil"></i></button>
                                    <button class="btn btn-danger btn-xs" name="trashs" type="button"><i class="icon-trash"></i></button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="btn-row">
                        <div style="text-align: center">
                            <div class="btn-group">
                                <c:if test="${thisPage != 1}">
                                    <button class="btn btn-primary" type="button" id="first">首页</button>
                                    <button class="btn btn-primary" type="button" id="prev">上页</button>
                                </c:if>
                                <c:if test="${thisPage != maxPage}">
                                    <button class="btn btn-primary" type="button" id="next">下页</button>
                                    <button class="btn btn-primary" type="button" id="last">末页</button>
                                </c:if>
                                <span class="btn btn-primary">
                                    ${thisPage}/${maxPage}
                                </span>
                                <span class="btn btn-primary">
                                    ${count}
                                </span>
                            </div>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</section>
<script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"
        type="text/javascript"></script>
<script>
    $(document).ready(function () {

        $('#search').click(function ()
        {
            $('#thisPage').val(1);
            $('#personForm').submit();
        });

        /**
         * 全选事件 全选1.1
         */
        $('#checkAll').click(function ()
        {
            $('[name=ids]').prop('checked',this.checked)
        });

        /**
         * 全选事件1.2
         */
        $('[name=ids]').click(function ()
        {
            let c1 = $('[name=ids]').length;
            let c2 = $('[name=ids]:checked').length;
            $('#checkAll').prop('checked', c1===c2);
        });

        /**
         * 提交
         */
        $('#batch').click(function ()
        {
            let thisPage = $('#thisPage').val();
            let maxPage = $('#maxPage').val();
            if (thisPage === maxPage && thisPage != 1  )
            {
                thisPage = thisPage-1;
            }
            $('#personForm').attr("action",'${pageContext.request.contextPath}/person.do?kc=batch&thisPage='+thisPage).submit();
        });


        /**
         * 绑定单独删除事件
         */
        $('[name=trashs]').bind('click',function ()
        {
            let id = $(this).parent().attr('id');
            if (confirm('是否要删除数据呢？'))
            {
                let thisPage = $('#thisPage').val();
                let maxPage = $('#maxPage').val();
                let lastPageNumber = "${lastPageNumber}";
                if (thisPage === maxPage && thisPage != 1 && lastPageNumber == 1 )
                {
                    thisPage = thisPage-1;
                }
                window.location.href='${pageContext.request.contextPath}/person.do?kc=delete&id='+id+'&thisPage='+thisPage
            }

        });

        //修改单条数据
        $('[name=pencil]').bind('click',function ()
        {
            let id = $(this).parent().attr('id');
            window.location.href='${pageContext.request.contextPath}/person.do?kc=load&id='+id+'&thisPage='+${thisPage}

        });

        /**
         * 分页查询事件
         */
        $('.btn-group>button').bind('click', function () {
            let id = $(this).attr('id');
            let thisPage = $('#thisPage').val();
            let maxPage = $('#maxPage').val();

            if (id === 'first') {
                thisPage = 1;
            } else if (id === 'prev') {
                thisPage = parseInt(thisPage) - 1;
            } else if (id === 'next') {
                thisPage = parseInt(thisPage) + 1;
            } else if (id === 'last') {
                thisPage = maxPage;
            }

            $('#thisPage').val(thisPage);
            $('#personForm').submit();
        });

        //添加事件
        $('#create').bind('click', function () {
            window.location.href = '${pageContext.request.contextPath}/views/person/create.jsp';
        });

        $('#sdate').datepicker({
            format: 'yyyy-mm-dd'
        });
        $('#edate').datepicker({
            format: 'yyyy-mm-dd'
        });
    });
</script>
</body>
</html>
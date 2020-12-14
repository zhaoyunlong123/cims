<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="javakc-zhg">
    <link rel="shortcut icon" href="img/favicon.html">
    <title>${type=="1"?"供暖":"物业"}补贴管理</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap-reset.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/css/datepicker.css"/>
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    <%-- 解决页面登陆保留时间的问题 --%>
<%--    <base target="_top"/>--%>
</head>
<body>
<section id="container" class="">
    <div class="row">
        <div class="col-lg-12">
            <section class="panel">
                <header class="panel-heading">
                    ${type=="1"?"供暖":"物业"}补贴
                </header>
                <form class="form-inline" id="subsidyForm" role="form"
                      action="${pageContext.request.contextPath}/subsidy.do" method="post">
                    <input type="hidden" id="thisPage" name="thisPage" value="${params.thisPage}"/>
                    <input type="hidden" id="maxPage" name="maxPage" value="${params.maxPage}"/>
                    <input type="hidden" id="type" name="type" value="${type}"/>
                    <div class="row">
                        <div class="col-lg-12">
                            <section class="panel">
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label class="">姓名</label>
                                        <input type="text" class="form-control" id="name" name="name" value="${params.name}" placeholder="输入姓名">
                                    </div>
                                    <div class="form-group">
                                        <label class="">身份证号</label>
                                        <input type="text" class="form-control" id="subsidyCard" name="subsidyCard" value="${params.subsidyCard}" placeholder="请输入身份证号">
                                    </div>
                                    <div class="form-group">
                                        <label class="">月份</label>
                                        <input type="text" id="sdate" name="month" value="${params.month}" class="form-control" placeholder="请输入开始日期"
                                               readonly>
                                    </div>
                                    <button type="submit" class="btn btn-success">搜索</button>
                                    <button type="button" id="create" class="btn btn-info">添加</button>
                                    <button type="button" id="batch" class="btn btn-danger">批量删除</button>
                                </div>
                            </section>
                        </div>
                    </div>
                    <table class="table table-striped table-advance table-hover">
                        <thead>
                        <tr>
                            <th><input type="checkbox"  id="checkAll"/></th>
                            <th><i class="icon-bullhorn"></i>序号</th>
                            <th><i class="icon-male"></i>月份</th>
                            <th><i class="icon-bookmark"></i>姓名</th>
                            <th><i class="icon-edit"></i>身份证</th>
                            <th><i class="icon-tags"></i>职级</th>
                            <th><i class="icon-jpy"></i>金额</th>
                            <th><i class="icon-home"></i>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="entity" varStatus="n">

                            <tr>
                                <th><input type="checkbox" name="ids" value="${entity.id}"/></th>
                                <td>${n.count+params.startNumber}</td>
                                <td>${entity.month}</td>
                                <td>${entity.person.name}</td>
                                <td>${entity.person.personCard}</td>
                                <td>
                                    <c:forEach items="${applicationScope.date}" var="date">
                                        <c:if test="${date.key==entity.person.grade}">
                                            ${date.value}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>${entity.money}</td>
                                <td id="${entity.id}">
                                    <button class="btn btn-primary btn-xs" type="button" name="pencil"><i class="icon-pencil"></i></button>
                                    <button class="btn btn-danger btn-xs" type="button" name="trashs"><i class="icon-trash "></i></button>
                                </td>
                            </tr>

                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="btn-row">
                        <div style="text-align: center">
                            <div class="btn-group">
                                <c:if test="${params.thisPage!=1}">
                                    <button class="btn btn-primary" id="first" type="button">首页</button>
                                    <button class="btn btn-primary" id="prev" type="button">上页</button>
                                </c:if>
                                <c:if test="${params.thisPage!=params.maxPage}">
                                    <button class="btn btn-primary" id="next" type="button">下页</button>
                                    <button class="btn btn-primary" id="last" type="button">末页</button>
                                </c:if>
								<span class="btn btn-primary">
									${params.thisPage}/${params.maxPage}页
								</span>
								<span class="btn btn-primary">
									共${params.count}条
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script>
    $(document).ready(function () {

        $('[name=pencil]').click(function ()
        {
            let thisPage = $('#thisPage').val();
            let maxPage = $('#maxPage').val();
            let lastPageNumber = ${params.lastPageNumber};
            if (thisPage!=1 && thisPage===maxPage && lastPageNumber===1)
            {
                thisPage = thisPage - 1;
            }
            let id = $(this).parent().attr("id");
            window.location.href='${pageContext.request.contextPath}/subsidy.do?kc=load&type=${type}&id='+id+'&thisPage='+thisPage;
        });

        //全删
        $('#checkAll').click(function ()
        {
            $('[name=ids]').prop('checked',this.checked);
        });
        $('[name=ids]').click(function ()
        {
            let c1 = $('[name=ids]').length;
            let c2 = $('[name=ids]:checked').length;
            $('#checkAll').prop('checked',c1 === c2);
        });
        $('#batch').click(function ()
        {
            if (confirm("是否全部删除"))
            {

                let thisPage = $('#thisPage').val();
                let maxPage = $('#maxPage').val();
                let lastPageNumber = ${params.lastPageNumber};
                if (thisPage!=1 && thisPage===maxPage && lastPageNumber===1)
                {
                    thisPage = thisPage - 1;
                }
                let type = ${type};
                $('#subsidyForm').attr('action','${pageContext.request.contextPath}/subsidy.do?kc=batch&type='+type+'&thisPage='+thisPage).submit()
            }
        });

        //单删
        $('[name=trashs]').click(function ()
        {
            let thisPage = $('#thisPage').val();
            let maxPage = $('#maxPage').val();
            let lastPageNumber = ${params.lastPageNumber};
            if (thisPage!=1 && thisPage===maxPage && lastPageNumber===1)
            {
                thisPage = thisPage - 1;
            }
            let type = ${type};
            let id=$(this).parent().attr('id');
            if (confirm("是否删除数据"))
            {
                window.location.href='${pageContext.request.contextPath}/subsidy.do?kc=delete&id='+id+'&type='+type+'&thisPage='+thisPage;
            }
        });

        //分页
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
			$('#subsidyForm').submit();
        });


        $('#create').bind('click', function () {
            window.location.href = '${pageContext.request.contextPath}/views/subsidy/create.jsp?type=${type}';
        });
        $('#sdate').datepicker({
            format: 'yyyy-mm'
        });
    });
</script>
</body>
</html>
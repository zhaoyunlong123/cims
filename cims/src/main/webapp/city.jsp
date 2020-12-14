<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>城市</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap-reset.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">

</head>
<body>
<%-- 按需取数据 --%>
<section id="container" class="">
    <div class="row">
        <div class="col-lg-12">
            <section class="panel">
                <header class="panel-heading">
                    城市联动菜单
                </header>
                <div class="panel-body">
                    <form class="form-horizontal tasi-form">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">一级城市</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="grade" id="city1"/>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">二级城市</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="grade" id="city2"/>
                                </select>
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
<script type="text/javascript">
    $(document).ready(function ()
    {
        $.get('${pageContext.request.contextPath}/city.do?pcode=0',function (data)
        {
            let html = '<option value="0">请选择</option>';
            $.each(data,function (i,o)
            {
                 html += '<option value="'+o.code+'">'+o.name+'</option>';
            });
            $('#city1').html(html);
        });
        $('#city1').change(function ()
        {
            let code = $(this).val();
            $.get('${pageContext.request.contextPath}/city.do?pcode='+code,function (data)
            {
                let html = '<option value="0">请选择</option>';
                $.each(data,function (i,o)
                {
                    html += '<option value="'+o.code+'">'+o.name+'</option>';
                });
                $('#city2').html(html);
            })

        })

    })
</script>
</body>
</html>

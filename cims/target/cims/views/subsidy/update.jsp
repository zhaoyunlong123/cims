<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 收集请求的数据 --%>
<%--<%--%>
<%--    String type = request.getParameter("type");--%>
<%--//    System.out.println(type);--%>
<%--%>--%>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <link rel="shortcut icon" href="img/favicon.html">
    <title>${subsidy.type}补贴修改</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap-reset.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/css/datepicker.css" />
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    <%-- 解决页面登陆保留时间的问题 --%>
<%--    <base target="_top"/>--%>
  <body>
  <section id="container" class="">

    <div class="row">
    <div class="col-lg-12">
      <section class="panel">
          <header class="panel-heading">
             ${subsidy.type.equals("1")?"供暖":"物业"}补贴修改
          </header>
          <div class="panel-body">
              <form class="form-horizontal tasi-form" id="subsidyForm" action="${pageContext.request.contextPath}/subsidy.do" method="post">
                  <input type="hidden" id="id" name="id" value="${subsidy.id}"/>
                  <input type="hidden" id="thisPage" name="thisPage" value="${thisPage}"/>
                  <input type="hidden" name="kc" value="update"/>
                  <input type="hidden" name="type" value="${subsidy.type}"/>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">姓名</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="personName" name="name" value="${subsidy.person.name}" placeholder="输选择姓名" readonly required>
                          <span class="help-block">请选择人员!</span>
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">身份证号</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="personCard" name="card" value="${subsidy.person.personCard}" placeholder="请选择姓名证件编号"  readonly required>
                          <span class="help-block">根据选择的人员自动显示该人员的证件编号!</span>
                      </div>
                  </div>
                 <div class="form-group">
                      <label class="col-sm-2 control-label">补贴金额</label>
                      <div class="col-sm-10">
                          <input type="text" id="money" name="money" value="${subsidy.money}" class="form-control" placeholder="请输入补贴金额">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-2 control-label">更改原因</label>
                      <div class="col-sm-10">
                          <input type="text"  id="reasons" name="reasons" value="${subsidy.person.reasons}" class="form-control" placeholder="请输入更改原因">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-lg-2 control-label"></label>
                      <div class="col-lg-10">
                         <button type="submit" class="btn btn-success">修改</button>
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
    <script src="${pageContext.request.contextPath}/static/js/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript">
      $(document).ready(function() 
      {

        $('#date').datepicker({
                format: 'yyyy-mm-dd'
        });
      });
    </script>
  </body>
</html>

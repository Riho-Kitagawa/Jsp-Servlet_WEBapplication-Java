<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/layout.css"
	rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">

<title>社員管理システム</title>

</head>
<body>
	<%@include file="/jsp/common/header.jsp"%>

	<div class="login">
		<h3>ログイン画面</h3>
		<div class="form">
			<p>社員IDとパスワードを入力してください。<span style="color: red">${error}</span></p>

			<form action="<%=request.getContextPath()%>/LoginCheck" method="post"
				name="login" >
				<div class="login_label">社員ID</div>
				<div class="login_input">
					<input type="text" name="empId" />
				</div>
				<div class="login_label">パスワード</div>
				<div class="login_input">
					<input type="password" name="empPass" />
				</div>
				<div class="login_label"></div>
				<div class="login_input">
					<input type="submit" value="ログイン" />
				</div>
			</form>
		</div>
	</div>

	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
<%@ page language="java" pageEncoding="GBK"%>
<html>
	<head>
	<title>户口本信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<script>
	</script>
	</head>
	<body>
	<div>
		<form action="http://localhost:8080/spring/rcp/getHKBDetail" method="post">
		<div>
			<div style="float:left;">
				<div >身份证号：</div>
					<div><textarea name="paras" style="width:600px;height:300px;" id="name">${paras }</textarea></div>
				</div>
			<div style="float:right;">
				<div>响应报文：</div>
				<div><textarea id="showInfo" style="width:600px;height:300px;" id="name">${result }</textarea></div>
			</div>
		</div>
		<div>
			<input type="submit" value="发送springmvc请求">
		</div>
		</form>
	</div>
	</body>
</html>

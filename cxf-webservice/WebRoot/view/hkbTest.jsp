<%@ page language="java" pageEncoding="GBK"%>
<html>
	<head>
	<title>���ڱ���Ϣ</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<script>
	</script>
	</head>
	<body>
	<div>
		<form action="http://localhost:8080/spring/rcp/getHKBDetail" method="post">
		<div>
			<div style="float:left;">
				<div >���֤�ţ�</div>
					<div><textarea name="paras" style="width:600px;height:300px;" id="name">${paras }</textarea></div>
				</div>
			<div style="float:right;">
				<div>��Ӧ���ģ�</div>
				<div><textarea id="showInfo" style="width:600px;height:300px;" id="name">${result }</textarea></div>
			</div>
		</div>
		<div>
			<input type="submit" value="����springmvc����">
		</div>
		</form>
	</div>
	</body>
</html>

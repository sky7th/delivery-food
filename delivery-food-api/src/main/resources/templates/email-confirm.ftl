<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <title>딜리버리 푸드 이메일 인증</title>
</head>
<body>
<input type="hidden" value="${message}" />
<script>
  var message = "${message?js_string}";
  alert(message);
  history.back();
</script>
</body>
</html>
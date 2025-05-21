<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>Kết quả</title></head>
<body>
  <h2>Chuyển đổi thành công!</h2>
 <%@ page isELIgnored="false" %>
<a href="<%= request.getAttribute("filePath") %>" download>Tải file Docx tại đây</a>

</body>
</html>

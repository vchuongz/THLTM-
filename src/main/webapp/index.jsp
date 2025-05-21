<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>Upload PDF</title></head>
<body>
  <h2>Tải lên file PDF để chuyển sang Docx</h2>
  <form method="post" action="upload" enctype="multipart/form-data">
    <input type="file" name="pdfFile" accept=".pdf" required />
    <button type="submit">Dịch & Tạo Docx</button>
  </form>

  <c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
  </c:if>
</body>
</html>

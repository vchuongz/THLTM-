package controller;

import util.DocxUtil;
import util.PdfUtil;
import dao.JobDAO;
import model.Job;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

@WebServlet("/upload")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 10 * 1024 * 1024,
    maxRequestSize = 20 * 1024 * 1024
)
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            Part filePart = request.getPart("pdfFile");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            Path tempPdf = Files.createTempFile("upload-", ".pdf");
            try (InputStream in = filePart.getInputStream()) {
                Files.copy(in, tempPdf, StandardCopyOption.REPLACE_EXISTING);
            }

            String text = PdfUtil.extractText(tempPdf);

            String docxName = fileName.replace(".pdf", ".docx");
            Path outputPath = Paths.get(getServletContext().getRealPath("/webapp/output"));
            Files.createDirectories(outputPath);
            Path docxFile = DocxUtil.createDocx(text, outputPath.resolve(docxName));

            Job job = new Job(fileName, docxFile.toString());
            JobDAO.save(job);

            request.setAttribute("filePath", "output/" + docxFile.getFileName().toString());
            request.getRequestDispatcher("result.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Xử lý thất bại: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
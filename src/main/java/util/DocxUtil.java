package util;

import org.apache.poi.xwpf.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocxUtil {
    public static Path createDocx(String text, Path outputPath) throws IOException {
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.createRun().setText(text);

        try (OutputStream out = Files.newOutputStream(outputPath)) {
            doc.write(out);
        }
        return outputPath;
    }
}
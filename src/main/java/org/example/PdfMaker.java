package org.example;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.nio.file.Paths;

public class PdfMaker {

    public String makePdf(String text, String companyName) {
        try {
            String userHome = System.getProperty("user.home");
            String desktopPath = Paths.get(userHome, "Desktop", "August Rydnell Personligt Brev Till " + companyName + ".pdf").toString();
            PdfWriter writer = new PdfWriter(desktopPath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            document.add(new Paragraph(text));
            document.close();
            return desktopPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

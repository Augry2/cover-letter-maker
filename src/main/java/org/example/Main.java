package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        savePdfToDesktop("softwareDeveloper");

    }

    private static void savePdfToDesktop(String file) {
        try {
            String filePath = "src/main/resources/" + file;
            String text = new String(Files.readAllBytes(Paths.get(filePath)));
            PdfMaker pdfMaker = new PdfMaker();
            pdfMaker.makePdf(text, "test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
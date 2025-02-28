package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String companyName = enterCompanyName();
        String templateToEdit = fileEditor(companyName);

        savePdfToDesktop(templateToEdit, companyName);
    }

    private static String fileEditor(String companyName) {
        String pathToTemplate = selectTemplate();
        String templateToEdit = getTemplate(pathToTemplate);

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        templateToEdit = templateToEdit.replace("[Datum]", date);

        String jobTitle = enterJobTitle();
        templateToEdit = templateToEdit.replace("[Tjänstens namn]", jobTitle);

        String articleNumber = enterArticleNumber();
        templateToEdit = templateToEdit.replace("[XXXXXX]", articleNumber);

        templateToEdit = templateToEdit.replace("[CompanyName]", companyName);


        return templateToEdit;
    }

    private static String enterCompanyName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter company name: ");
        return scanner.nextLine();
    }

    private static String enterArticleNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter article number: ");
        return scanner.nextLine();
    }

    private static String enterJobTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter job title: ");
        return scanner.nextLine();
    }

    private static String selectTemplate() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("select which CV to print:");
            System.out.println("1. software developer");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    return "softwareDeveloper";
                case 2:

                    break;
                case 3:

                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static String getTemplate(String fileToSelect) {
        try {
            String filePath = "src/main/resources/" + fileToSelect;
            return Files.readString(Paths.get(filePath));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void savePdfToDesktop(String modifiedText, String companyName) {
        try {
            PdfMaker pdfMaker = new PdfMaker();
            pdfMaker.makePdf(modifiedText, companyName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
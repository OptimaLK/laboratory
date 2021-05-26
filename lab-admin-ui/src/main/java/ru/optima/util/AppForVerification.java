package ru.optima.util;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import ru.optima.repr.VerificationRepr;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AppForVerification {
    Path pathIn = Path.of("lab-admin-ui/src/main/resources/templates/doc/maket/Forma_zayavki_na_provedenie_poverki_i_kalibrovki_SI_0.docx").toAbsolutePath();
    Path pathOut = Path.of("lab-admin-ui/src/main/resources/templates/doc/outDOCX").toAbsolutePath();
    String pathFile = pathOut + "\\" + new SimpleDateFormat("dd.MMMM.yy").format(Calendar.getInstance().getTime()) + ".docx";
    public AppForVerification(ArrayList <VerificationRepr> data) throws IOException, XmlException {
        FileInputStream fileInputStream = new FileInputStream(String.valueOf(pathIn));
        XWPFDocument document = new XWPFDocument(fileInputStream);

        /**
         *  Заполнение даты формирования заявки,
         *  т. е. в шапке документа строка "от «___» ___________ 20___ г."
         *  перезаписывается текущей датой в виде "от « dd »   MMMM   20yy г."
         */
        XWPFParagraph xwpfParagraph = document.getTables().get(0).getRows().get(0).getCell(0).getParagraphs().get(5);
        String text = xwpfParagraph.getRuns().get(0).text();
        String[] textSplit;
        String[] textSplitLine;
        textSplit = new SimpleDateFormat("dd.MMMM.yy").format(Calendar.getInstance().getTime()).split("\\.");
        textSplitLine = text.split(" ");
        textSplit[1] = "   " + textSplit[1].replaceAll("_", " ") + "   ";
        text = textSplitLine[0] + " " + textSplitLine[1].replace("___", " " + textSplit[0] + " ") +
                textSplitLine[2].replace("___________", textSplit[1]) +
                textSplitLine[3].replace("___", textSplit[2] + " ") + textSplitLine[4];
        xwpfParagraph.getRuns().get(0).setText(text, 0);

        /**
         *  Заполнение строки "Наименование организации (Заказчика)"
         */
        fillingInLines(document, data.get(0).getCustomer(), 0, 23);

        /**
         *  Заполнение строки "Контактное лицо"
         */
        fillingInLines(document, data.get(0).getPerson(), 4, 1);

        /**
         *  Заполнение строки "Телефон"
         */
        fillingInLines(document, data.get(0).getPhone(), 4, 3);

        /**
         *  Заполнение строки "Эл. почта"
         */
        xwpfParagraph = document.getParagraphs().get(4);
        text = xwpfParagraph.getRuns().get(10).text();
        xwpfParagraph.getRuns().get(9).setText(xwpfParagraph.getRuns().get(9).text() + text, 0);
        xwpfParagraph.removeRun(10);

        fillingInLines(document, data.get(0).getEmail(), 4, 9);

        /**
         *  Заполнение строки "По графику и договору"
         *  (№ и дата договора)
         */
        xwpfParagraph = document.getParagraphs().get(6);
        text = xwpfParagraph.getRuns().get(1).text();
        text = text.substring(data.get(0).getNumber().length() + data.get(0).getDate().length() + 6);
        text = text.replaceAll("_", "  ");
        text = text.replace(text, "   №" + data.get(0).getNumber() + "  от  " + data.get(0).getDate() + text + " ");
        xwpfParagraph.getRuns().get(1).setText(text, 0);
        xwpfParagraph.getRuns().get(1).setUnderline(UnderlinePatterns.SINGLE);

        /**
         *  Заполнение строки "Наименование владельца СИ, на которое должно выписываться Свидетельство о поверке"
         *  (наименование юридического лица, ИНН)
         */
        fillingInLines(document, data.get(0).getEnterprise(), 8, 2);

        /**
         *  Заполнение таблицы перечня СИ и ИО
         */
        int i = 3;
        for(VerificationRepr s: data) {
            if(i > 5) {
                XWPFTable table = document.getTableArray(1);
                XWPFTableRow oldRow = document.getTables().get(1).getRows().get(i - 1);
                CTRow ctrow = CTRow.Factory.parse(oldRow.getCtRow().newInputStream());
                XWPFTableRow newRow = new XWPFTableRow(ctrow, table);

                xwpfParagraph = newRow.getTableCells().get(0).getParagraphs().get(0);
                text = xwpfParagraph.getRuns().get(0).text();
                text = text.replace(text,  String.valueOf(i - 2));
                xwpfParagraph.getRuns().get(0).setText(text, 0);
                fillingInTableNewRow(newRow, s.getName_eq(), 1);
                fillingInTableNewRow(newRow, s.getType_eq(), 2);
                fillingInTableNewRow(newRow, s.getSum(), 3);
                fillingInTableNewRow(newRow, s.getNumber_reg(), 4);
                fillingInTableNewRow(newRow, s.getFactory_num(), 5);
                fillingInTableNewRowBool(newRow, s.isUrgency(), 6);
                fillingInTableNewRowBool(newRow, s.isProtocol(), 7);
                fillingInTableNewRowBool(newRow, s.isCertificate(), 8);
                fillingInTableNewRowBool(newRow, s.isCal_uncertainty(), 9);
                fillingInTableNewRow(newRow, s.getNote(), 10);

                table.addRow(newRow, i);
            } else {
                xwpfParagraph = document.getTables().get(1).getRows().get(i).getCell(0).getParagraphs().get(0);
                xwpfParagraph.createRun().setText(String.valueOf(i - 2));
                fillingInTable(document, s.getName_eq(), 1, i);
                fillingInTable(document, s.getType_eq(), 2, i);
                fillingInTable(document, s.getSum(), 3, i);
                fillingInTable(document, s.getNumber_reg(), 4, i);
                fillingInTable(document, s.getFactory_num(), 5, i);
                fillingInTableBool(document, s.isUrgency(), 6, i);
                fillingInTableBool(document, s.isProtocol(), 7, i);
                fillingInTableBool(document, s.isCertificate(), 8, i);
                fillingInTableBool(document, s.isCal_uncertainty(), 9, i);
                fillingInTable(document, s.getNote(), 10, i);
            }
            i++;
        }
        rec(document);
    }

    private void fillingInLines(XWPFDocument document, String write, int paragraph, int run) {
        String text;
        XWPFParagraph xwpfParagraph = document.getParagraphs().get(paragraph);
        text = xwpfParagraph.getRuns().get(run).text();
        if(text.length() > write.length() + 1)
            text = text.substring(write.length() + 2);
        text = text.replaceAll("_", "  ");
        text = text.replace(text, "  " + write + text + " ");
        xwpfParagraph.getRuns().get(run).setText(text, 0);
        xwpfParagraph.getRuns().get(run).setUnderline(UnderlinePatterns.SINGLE);
    }

    private void fillingInTable(XWPFDocument document, String write, int cell, int i) {
        XWPFParagraph xwpfParagraph = document.getTables().get(1).getRows().get(i).getCell(cell).getParagraphs().get(0);
        xwpfParagraph.createRun().setText(write);
    }

    private void fillingInTableBool(XWPFDocument document, boolean write, int cell, int i) {
        XWPFParagraph xwpfParagraph = document.getTables().get(1).getRows().get(i).getCell(cell).getParagraphs().get(0);
        if(write) xwpfParagraph.createRun().setText("+");
        else xwpfParagraph.createRun().setText("-");
    }

    private void fillingInTableNewRow(XWPFTableRow newRow, String write, int cell) {
        String text;
        XWPFParagraph xwpfParagraph = newRow.getTableCells().get(cell).getParagraphs().get(0);
        text = xwpfParagraph.getRuns().get(0).text();
        text = text.replace(text,  write);
        xwpfParagraph.getRuns().get(0).setText(text, 0);
    }

    private void fillingInTableNewRowBool(XWPFTableRow newRow, boolean write, int cell) {
        String text;
        XWPFParagraph xwpfParagraph = newRow.getTableCells().get(cell).getParagraphs().get(0);
        text = xwpfParagraph.getRuns().get(0).text();
        if(write)
            text = text.replace(text,  "+");
        else
            text = text.replace(text,  "-");
        xwpfParagraph.getRuns().get(0).setText(text, 0);
    }

    private void rec(XWPFDocument document) throws IOException {
        if (!Files.exists(pathOut))
            Files.createDirectories(pathOut);
        FileOutputStream outputStream = new FileOutputStream(String.valueOf(pathFile));
        document.write(outputStream);
        outputStream.close();
        openWord();
    }

    private void openWord() throws IOException {
        File file = new File(String.valueOf(pathFile));
        if(Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        } else {
            Runtime runtime = Runtime.getRuntime();
            if(System.getenv("OS") != null && System.getenv("OS").contains("Windows"))
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + file);
            else runtime.exec("xdg-open " + file);
        }
    }
}

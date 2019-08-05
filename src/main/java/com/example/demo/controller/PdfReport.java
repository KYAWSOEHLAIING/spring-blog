package com.example.demo.controller;

import com.example.demo.model.Author;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfReport {
    public static ByteArrayInputStream authorPdfView(List<Author> authorList){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document doc = new Document();

        try{
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{1,3,3,3,3});


            PdfPCell hcell;
            Font font= FontFactory.getFont(FontFactory.HELVETICA);

            hcell=new PdfPCell(new Phrase("Id",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("Name",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);


            hcell=new PdfPCell(new Phrase("Date OF Birth",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("Interested",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("Gender",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for(Author author:authorList){
                PdfPCell cell;

                cell=new PdfPCell(new Phrase(author.getId().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell=new PdfPCell(new Phrase((author.getName().toString())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);


                cell=new PdfPCell(new Phrase(author.getDateOfBirth().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);


                cell=new PdfPCell(new Phrase(author.getInterested().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);


                cell=new PdfPCell(new Phrase((author.getGender()).toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);


            }

            PdfWriter.getInstance(doc,out);
            doc.open();

            doc.add(table);

            doc.close();



        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return  new ByteArrayInputStream(out.toByteArray());
    }
}

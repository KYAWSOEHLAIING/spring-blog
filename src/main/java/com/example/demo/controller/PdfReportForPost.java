package com.example.demo.controller;

import com.example.demo.model.Post;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfReportForPost {
    public static ByteArrayInputStream postPdfView(List<Post> postList){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document doc = new Document();

        try{
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{1,3,3,3});


            PdfPCell hcell;
            Font font= FontFactory.getFont(FontFactory.HELVETICA);

            hcell=new PdfPCell(new Phrase("Id",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("Tag",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);


//            hcell=new PdfPCell(new Phrase("Body",font));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("LastUpdate",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("Author",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for(Post post:postList){
                PdfPCell cell;

                cell=new PdfPCell(new Phrase(post.getId().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell=new PdfPCell(new Phrase((post.getTag().toString())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);


//                cell=new PdfPCell(new Phrase(post.getBody().toString()));
//                cell.setPaddingLeft(5);
//                cell.setVerticalAlignment(Element.ALIGN_TOP);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                table.addCell(cell);


                cell=new PdfPCell(new Phrase(post.getLastUpdated().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);


                cell=new PdfPCell(new Phrase((post.getAuthor().getName()).toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
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

package com.example.demo.handler;

import com.example.demo.model.Book;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;

public class Handler {

    public Book getBook(File file) {
        Book book = new Book();
        try {
            PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
            parser.parse();
            String text = getText(parser);
            book.setText(text);

            PDDocument pdf = parser.getPDDocument();
            PDDocumentInformation info = pdf.getDocumentInformation();

            String title = "" + info.getTitle();
            book.setTitle(title);

            String keywords = "" + info.getKeywords();
            book.setKeywords(keywords);

            book.setFilename(file.getCanonicalPath());

            //TODO: file date

            pdf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return book;
    }

    public String getText(File file) {
        try {
            PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
            parser.parse();
            PDFTextStripper textStripper = new PDFTextStripper();
            String text = textStripper.getText(parser.getPDDocument());
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getText(PDFParser parser) {
        try {
            PDFTextStripper textStripper = new PDFTextStripper();
            String text = textStripper.getText(parser.getPDDocument());
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

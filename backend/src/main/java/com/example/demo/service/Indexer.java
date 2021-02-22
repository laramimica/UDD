package com.example.demo.service;

import com.example.demo.handler.Handler;
import com.example.demo.model.Book;
import com.example.demo.repository.IBookRepository;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Indexer {

    @Autowired
    private IBookRepository bookRepository;

    public boolean delete(String filename) {
        if (bookRepository.findById("C:\\Users\\Lara\\Desktop\\Lara\\demo\\target\\classes\\files\\" + filename).isPresent()) {
            bookRepository.delete(bookRepository.findById("C:\\Users\\Lara\\Desktop\\Lara\\demo\\target\\classes\\files\\" + filename).get());
            File file = new File("src\\main\\resources\\files\\" + filename);
            File file2 = new File("target\\classes\\files\\" + filename);
            if (file.exists()) {
                file.delete();
            }
            if (file2.exists()) {
                file2.delete();
            }
            return true;
        } else {
            File file = new File("src\\main\\resources\\files\\" + filename);
            File file2 = new File("target\\classes\\files\\" + filename);
            if (file.exists()) {
                file.delete();
            }
            if (file2.exists()) {
                file2.delete();
            }
            return false;
        }
    }

    public boolean update(Book unit) {
        unit = bookRepository.save(unit);
        if (unit != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean add(Book unit) {
        unit = bookRepository.index(unit);
        if (unit != null) {
            bookRepository.save(unit); //?
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param file Direktorijum u kojem se nalaze dokumenti koje treba indeksirati
     */
    public int index(File file) {
        Handler handler = new Handler();
        int retVal = 0;
        try {
            File[] files;
            if (file.isDirectory()) {
                files = file.listFiles();
            } else {
                files = new File[1];
                files[0] = file;
            }
            for (File newFile : files) {
                if (newFile.isFile()) {
                    Book book = handler.getBook(newFile);
                    book.setTitle(handler.getBook(newFile).getTitle());
                    book.setKeywords(handler.getBook(newFile).getKeywords());
                    book.setFilename(handler.getBook(newFile).getFilename());
                    book.setWriter(handler.getBook(newFile).getWriter());
                    book.setGenres(handler.getBook(newFile).getGenres());
                    book.setText(handler.getBook(newFile).getText());
                    if (add(book)) {
                        retVal++;
                    }
                } else if (newFile.isDirectory()) {
                    retVal += index(newFile);
                }
            }
            System.out.println("indexing done");
        } catch (Exception e) {
            System.out.println("indexing NOT done");
        }
        return retVal;
    }
}

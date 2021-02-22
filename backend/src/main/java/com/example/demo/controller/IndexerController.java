package com.example.demo.controller;

import com.example.demo.handler.Handler;
import com.example.demo.model.Book;
import com.example.demo.model.BookResponse;
import com.example.demo.model.UploadModel;
import com.example.demo.model.Writer;
import com.example.demo.repository.IBookRepository;
import com.example.demo.repository.IWriterRepository;
import com.example.demo.service.Indexer;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class IndexerController {

    @Autowired
    private Indexer indexer;

    @Autowired
    private IWriterRepository writerRepository;

    @Autowired
    private IBookRepository bookRepository;

    @GetMapping("/reindex")
    public ResponseEntity<String> index() throws IOException {
        File dataDir = getResourceFilePath("files");
        long start = new Date().getTime();
        int numIndexed = indexer.index(dataDir);
        long end = new Date().getTime();
        String text = "Indexing " + numIndexed + " files took "
            + (end - start) + " milliseconds";
        return new ResponseEntity<String>(text, HttpStatus.OK);
    }

    private File getResourceFilePath(String path) {
        URL url = this.getClass().getClassLoader().getResource(path);
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }
        return file;
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        List<BookResponse> bookResponses = new ArrayList<>();
        for(Book book: bookRepository.findAll()){
            BookResponse bookResponse = new BookResponse();
            bookResponse.setFilename(book.getFilename());
            bookResponse.setTitle(book.getTitle());
            bookResponse.setKeywords(book.getKeywords());
            bookResponse.setWriter(book.getWriter());
            bookResponse.setGenres(book.getGenres());
//            bookResponse.setText(book.getText());
            bookResponses.add(bookResponse);
        }
        return bookResponses;
    }

    @DeleteMapping("/{filename}")
    public void delete(@PathVariable String filename) {
        indexer.delete(filename);
    }

    @PostMapping("/index/add")
    public ResponseEntity<String> multiUploadFileModel(@RequestParam String title, @RequestParam String keywords,
        @RequestParam String username, @RequestParam String genres, @RequestParam MultipartFile file) throws Exception {

        try {

            indexUploadedFile(title, keywords, username, genres, file);

            try {
                byte[] bytes = file.getBytes();

                File newFile = new File("src\\main\\resources\\files\\");
                if (!newFile.exists()) {
                    newFile.mkdir();
                }

                File check = new File("src\\main\\resources\\files\\" + file.getOriginalFilename());

                if (check.exists()) {
                    throw new Exception("Already exists.");
                }

                if (!file.getOriginalFilename().contains(".pdf")) {
                    //TODO: Handle exception
                }

                Path path = Paths.get(newFile + "\\" + file.getOriginalFilename());
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Successfully uploaded!", HttpStatus.OK);
    }

    //save file
    private String saveUploadedFile(MultipartFile file) throws IOException {
        String retVal = null;
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths
                .get(getResourceFilePath("files").getAbsolutePath() + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);
            retVal = path.toString();
        }
        return retVal;
    }

    private void indexUploadedFile(String title, String keywords, String username, String genres, MultipartFile file)
        throws IOException {
        if (file.isEmpty()) {
            return;
        }

        Writer writer = writerRepository.findById(username).get();
        String fileName = saveUploadedFile(file);
        if (fileName != null) {
            Handler handler = new Handler();
            Book book = handler.getBook(new File(fileName));
            book.setTitle(title);
            book.setKeywords(keywords);
            book.setGenres(genres);
            book.setWriter(writer.getName());
            indexer.add(book);
        }
    }
}

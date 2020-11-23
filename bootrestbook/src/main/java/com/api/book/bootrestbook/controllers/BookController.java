package com.api.book.bootrestbook.controllers;


import java.util.List;
import java.util.Optional;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    
   
    @Autowired
    private BookService bookService;


     // @RequestMapping(value="/books", method = RequestMethod.GET)
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
       
        ResponseEntity.status(HttpStatus.OK);
        
        List<Book> l =  this.bookService.getAllBooks();
        
        if(l.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(l));
    }


    
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){

        Book book =  this.bookService.getBookByID(id);
        if(book ==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.of(Optional.of(book));
        }
    }





    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book b = null;
       try{
         b =  this.bookService.addBook(book);
        System.out.println(book);
        return ResponseEntity.of(Optional.of(b));
       }catch(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        
       }
       
    }





    @DeleteMapping("/books")
    public ResponseEntity<Void> deleteAllBooks(){

        try{
         this.bookService.deleteAllBooks();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }






    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteSingleBook(@PathVariable("id") int id){
        try{
         this.bookService.deleteSingleBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
         
    }



    

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int id){
        try{
        this.bookService.updateBook(book, id);
        return ResponseEntity.ok().body(book);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


}

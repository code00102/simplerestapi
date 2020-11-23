package com.api.book.bootrestbook.services;

import java.util.List;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // private static List<Book> list = new ArrayList<>();


    // static{

    //     list.add(new Book(2, "Lol", "Ram"));
    //     list.add(new Book(1, "Pol", "Shyam"));
    //     list.add(new Book(3, "Mol", "Hari"));


    // }

    public List<Book> getAllBooks(){

      List<Book> list = (List<Book>)this.bookRepository.findAll();
        return list;
    }

    //get single book by id
    public Book getBookByID(int id){
        Book myBook = null;
        try{
        //    myBook = list.stream().filter(e->e.getId()==id).findFirst().get();
        myBook = this.bookRepository.findById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return myBook;
    }


    // adding the book
    public Book addBook(Book book){

    //    list.add(book);
    this.bookRepository.save(book);
       return book;
    }

    public void deleteAllBooks(){

    //    boolean b =  list.removeAll(list);

        this.bookRepository.deleteAll();
    }

    public void deleteSingleBook(int id){
        // Book book = this.bookRepository.findById(id);
        // this.bookRepository.delete(book);
        this.bookRepository.deleteById(id);

        // Book b = list.stream().filter(e->e.getId()==id).findFirst().get();

        // boolean isDeleted = list.remove(b);
        // list =  list.stream().filter(book->{
        //     if(book.getId()!=id){
        //         return true;
        //     }
        //     else{
        //         return false;
        //     }
        // }).collect(Collectors.toList());
        

       
    }

    public void updateBook(Book b, int id){


        // list = list.stream().map(e->{

        //     if(e.getId()==id){
        //         e.setAuthor(b.getAuthor());
        //         e.setTitle(b.getTitle());
                
        //     }
        //     return e;

        // }).collect(Collectors.toList());
        b.setId(id);

        this.bookRepository.save(b); //save ra update le eutai kaam garcha !!
    }


}

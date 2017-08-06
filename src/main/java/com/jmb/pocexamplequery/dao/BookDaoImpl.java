package com.jmb.pocexamplequery.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.stereotype.Service;

import com.jmb.pocexamplequery.model.Book;
import com.jmb.pocexamplequery.model.Editorial;

@Service
public class BookDaoImpl implements BookDao {
  
  @Autowired
  private BookRepository bookRepository;

  @Override
  public Book save(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Optional<Book> findOne(String id) {
    return Optional.ofNullable(bookRepository.findOne(id));
  }

  @Override
  public Optional<Book> findByName(String name) {
    return Optional.ofNullable(this.createBook(null, null, name, null, null))
        .map(Example::of)
        .map(bookRepository::findOne);
  }

  @Override
  public Stream<Book> findByNameAndEditorial(String name, Editorial editorial) {
    final Example<Book> queryObject = Example.of(this.createBook(null, null, name, null, editorial));
    return bookRepository.findAll(queryObject).stream();
  }

  @Override
  public Stream<Book> findByContainName(String name) {
    final Book bookExample = this.createBook(null, null, name, null, null);
    final ExampleMatcher bookMatcher = ExampleMatcher.matching()
        .withMatcher("name", GenericPropertyMatcher::contains);
    final Example<Book> queryObjectWithMatcher = Example.of(bookExample, bookMatcher);

    return bookRepository.findAll(queryObjectWithMatcher).stream();
  }
  
  private Book createBook(String id, String isbn, String name, String description,
      Editorial editorial) {
    
    final Book book = new Book();
    
    book.setId(id);
    book.setIsbn(isbn);
    book.setName(name);
    book.setDescription(description);
    book.setEditorial(editorial);

    return book;
  }

}

package com.jmb.pocexamplequery.dao;

import java.util.Optional;
import java.util.stream.Stream;

import com.jmb.pocexamplequery.model.Book;
import com.jmb.pocexamplequery.model.Editorial;

public interface BookDao {

  public Book save(Book book);
  
  public Optional<Book> findOne(String id);
  
  public Optional<Book> findByName(String name);
  
  public Stream<Book> findByNameAndEditorial(String name, Editorial editorial);
  
  public Stream<Book> findByContainName(String name);
}

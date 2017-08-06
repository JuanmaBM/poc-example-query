package com.jmb.pocexamplequery.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.jmb.pocexamplequery.model.Book;
import com.jmb.pocexamplequery.model.Editorial;

public interface BookService {

  public Book save(Book book);
  
  public Book findOne(String id) throws NotFoundException;
  
  public Book findByName(String name) throws NotFoundException;
  
  public List<Book> findByNameAndEditorial(String name, Editorial editorial);
  
  public List<Book> findByContainName(String name);

}

package com.jmb.pocexamplequery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.jmb.pocexamplequery.dao.BookDao;
import com.jmb.pocexamplequery.model.Book;
import com.jmb.pocexamplequery.model.Editorial;

@Service
public class BookServiceImpl implements BookService {
  
  @Autowired
  private BookDao bookDao;

  @Override
  public Book save(Book book) {
    return bookDao.save(book);
  }

  @Override
  public Book findOne(String id) throws NotFoundException {
    return bookDao.findOne(id)
         .orElseThrow(NotFoundException::new);
  }

  @Override
  public Book findByName(String name) throws NotFoundException {
    return bookDao.findByName(name)
        .orElseThrow(NotFoundException::new);
  }

  @Override
  public List<Book> findByNameAndEditorial(String name, Editorial editorial) {
    return bookDao.findByNameAndEditorial(name, editorial)
        .collect(Collectors.toList());
  }

  @Override
  public List<Book> findByContainName(String name) {
    return bookDao.findByContainName(name)
        .collect(Collectors.toList());
  }

}

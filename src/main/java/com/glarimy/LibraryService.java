package com.glarimy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableTransactionManagement
public class LibraryService {
	@Autowired
	private LibraryRepository repo;
	
	public Book find(int isbn) {
		return repo.findOne(isbn);
	}
	
	@Transactional
	public void add(Book book) {
		repo.save(book);
	}
}

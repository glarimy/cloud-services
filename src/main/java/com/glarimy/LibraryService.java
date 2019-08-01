package com.glarimy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
	@Autowired
	private LibraryRepository repo;
	
	public Book find(int isbn) {
		return repo.findOne(isbn);
	}
	
	public void add(Book book) {
		repo.save(book);
	}
}

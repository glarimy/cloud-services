package com.glarimy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class LibraryController {
	@Autowired
	private LibraryService service;

	@RequestMapping(method = RequestMethod.GET, path = "/library/book/{isbn}")
	public ResponseEntity<Book> find(@PathVariable("isbn") int isbn) {
		return new ResponseEntity<Book>(service.find(isbn), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/library/book")
	public ResponseEntity<Book> add(@RequestBody Book book, UriComponentsBuilder builder) {
		service.add(book);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/library/book/{isbn}").buildAndExpand(book.getIsbn()).toUri());
		return new ResponseEntity<Book>(book, headers, HttpStatus.OK);
	}
}
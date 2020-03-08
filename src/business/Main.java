package business;

import java.util.*;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import model.Address;
import model.Author;
import model.Book;
import model.LibraryMember;

public class Main {

	public static void main(String[] args) {
		/*System.out.println(allWhoseZipContains3());
		System.out.println(allHavingOverdueBook());
		System.out.println(allHavingMultipleAuthors());*/
		saveAndReturnBooks();
		//allWhoseZipContains3();
	}

	public static List<Book> saveAndReturnBooks() {
		DataAccess da = new DataAccessFacade();
		
		List<Author> authors = new ArrayList<>();
		authors.add(new Author("Elly", "Kane", "Mr", new Address("Kampala", "Kampala", "UG", "256"), "XXXxx"));
		Book book = new Book("378483", "It works", 30, authors);
		da.saveNewBook(book);
		Collection<Book> books = da.loadBookMap().values();
		List<Book> mems = new ArrayList<>();
		mems.addAll(books);
		return mems;
	}

	//Returns a list of all ids of LibraryMembers whose zipcode contains the digit 3
	public static List<String> allWhoseZipContains3() {
		DataAccess da = new DataAccessFacade();
		//Collection<LibraryMember> members = da.loadMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		//mems.addAll(members);
		//implement
		return null;

	}
	//Returns a list of all ids of  LibraryMembers that have an overdue book
	public static List<String> allHavingOverdueBook() {
		DataAccess da = new DataAccessFacade();
		//Collection<LibraryMember> members = da.loadMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		//mems.addAll(members);
		//implement
		return null;

	}

	//Returns a list of all isbns of  Books that have multiple authors
	public static List<String> allHavingMultipleAuthors() {
		DataAccess da = new DataAccessFacade();
		Collection<Book> books = da.loadBookMap().values();
		List<Book> bs = new ArrayList<>();
		bs.addAll(books);
		//implement
		return null;

	}

}

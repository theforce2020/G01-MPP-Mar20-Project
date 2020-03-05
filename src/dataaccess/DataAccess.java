package dataaccess;

import java.util.HashMap;

import dataaccess.DataAccessFacade.StorageType;
import model.Book;
import model.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member); 
}

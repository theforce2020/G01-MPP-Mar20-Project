package library.dataaccess;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import model.Admin;
import model.Book;
import model.CheckoutRecord;
import model.Librarian;
import model.LibraryMember;
import model.SystemUser;

public class DataAccessFacade implements DataAccess {

	enum StorageType {
		BOOKS, MEMBERS, USERS, CHECKOUTRECORDS, LIBRARIANS, ADMINS;
	}

	public static final String OUTPUT_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "dataaccess" + File.separator + "storage";
	
	public static final String DATE_PATTERN = "MM/dd/yyyy";

	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = loadMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);
	}
	
	public void updateLibraryMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = loadMemberMap();
		if(!mems.containsKey(member.getMemberId())) {
			mems.put(member.getMemberId(), member);
			saveToStorage(StorageType.MEMBERS, mems);
		}
	}
	
	public int saveSystemUser(SystemUser systemUser) {
		int id = 0;
		HashMap<String, SystemUser> libs = loadUserMap();
		if (systemUser.getId() == 0) {
			if (libs.containsKey(systemUser.getUsername())) 
				id = -1;
			else {
				id = libs.size() + 1;
				systemUser.setId(id);
				libs.put(systemUser.getUsername(), systemUser);
				saveToStorage(StorageType.USERS, libs);
			}
		}
		return id;
	}

	public void saveLibrarian(Librarian librarian) {
		HashMap<Integer, Librarian> libs = loadLibrarianMap();
		libs.put(librarian.getId(), librarian);
		saveToStorage(StorageType.LIBRARIANS, libs);
	}
	
	public void saveAdmin(Admin admin) {
		HashMap<Integer, Admin> libs = loadAdminMap();
		libs.put(admin.getId(), admin);		
		saveToStorage(StorageType.ADMINS, libs);
	}
	
	@Override
	public Librarian getLibrarianById(int librarianId) {
		@SuppressWarnings("unchecked")
		HashMap<Integer, Librarian> result = (HashMap<Integer, Librarian>) readFromStorage(StorageType.LIBRARIANS);
		return result.get(librarianId);
	}

	public SystemUser getSystemUser(String username) {
		@SuppressWarnings("unchecked")
		HashMap<String, SystemUser> result = (HashMap<String, SystemUser>) readFromStorage(StorageType.USERS);
		return result.get(username);
	}
	
	@Override
	public Admin getAdminById(int adminId) {
		@SuppressWarnings("unchecked")
		HashMap<Integer, Admin> result = (HashMap<Integer, Admin>) readFromStorage(StorageType.LIBRARIANS);
		return result.get(adminId);
	}
	
	public void updateLibrarian(Librarian librarian) {
		HashMap<Integer, Librarian> librarians = loadLibrarianMap();
		if(!librarians.containsKey(librarian.getId())) {
			librarians.put(librarian.getId(), librarian);
			saveToStorage(StorageType.LIBRARIANS, librarians);
		}
	}
	
	public void updateAdmin(Admin admin) {
		HashMap<Integer, Admin> admins = loadAdminMap();
		if(!admins.containsKey(admin.getId())) {
			admins.put(admin.getId(), admin);
			saveToStorage(StorageType.ADMINS, admins);
		}
	}

	public void updateBook(Book bk) {
		HashMap<String, Book> books = loadBookMap();
		if(!books.containsKey(bk.getIsbn())) {
			books.put(bk.getIsbn(), bk);
			saveToStorage(StorageType.BOOKS, books);
		}
	}

	public void saveNewBook(Book bk) {
		HashMap<String, Book> books = loadBookMap();
		if (!books.containsKey(bk.getIsbn())) {
			books.put(bk.getIsbn(), bk);
			saveToStorage(StorageType.BOOKS, books);
		}
	}
	
	public boolean isBookAvailable(String isbn) {
		boolean result = false;
		HashMap<String, Book> books = loadBookMap();
		if (books.containsKey(isbn)) {
			result = true;
		}
		return result;
	}

	public Book getBook(String isbn) {
		HashMap<String, Book> books = loadBookMap();
		return books.get(isbn);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> loadMemberMap() {
		Object object = readFromStorage(StorageType.MEMBERS);
		if (object == null)
			return new HashMap<String, LibraryMember>();
		return (HashMap<String, LibraryMember>)object;
	}

	@SuppressWarnings("unchecked")
	public HashMap<Integer, Librarian> loadLibrarianMap() {
		Object object = readFromStorage(StorageType.LIBRARIANS);
		if (object == null)
			return new HashMap<Integer, Librarian>();
		return (HashMap<Integer, Librarian>)object;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<Integer, Admin> loadAdminMap() {
		Object object = readFromStorage(StorageType.ADMINS);
		if (object == null)
			return new HashMap<Integer, Admin>();
		return (HashMap<Integer, Admin>)object;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, SystemUser> loadUserMap() {
		Object object = readFromStorage(StorageType.USERS);
		if (object == null)
			return new HashMap<String, SystemUser>();
		return (HashMap<String, SystemUser>)object;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Book> loadBookMap() {
		Object object = readFromStorage(StorageType.BOOKS);
		if (object == null)
			return new HashMap<String, Book>();
		return (HashMap<String, Book>)object;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutRecord> loadCheckoutRecordMap() {
		Object object = readFromStorage(StorageType.CHECKOUTRECORDS);
		if (object == null)
			return new HashMap<String, CheckoutRecord>();
		return (HashMap<String, CheckoutRecord>)object;
	}

	@Override
	public void saveNewCheckoutRecord(CheckoutRecord checkoutRecord) {
		HashMap<String, CheckoutRecord> cr = loadCheckoutRecordMap();
		String mbrId = checkoutRecord.getMember().getMemberId();
		cr.put(mbrId, checkoutRecord);
		saveToStorage(StorageType.CHECKOUTRECORDS, cr);

	}

	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	static HashMap<String, SystemUser> readUsersMap() {
		return (HashMap<String, SystemUser>) readFromStorage(StorageType.USERS);
	}

	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			InputStream stream = Files.newInputStream(path);
			if (stream.available() > 0) { 
				in = new ObjectInputStream(stream);
				retVal = in.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		return retVal;
	}

	final static class Pair<S, T> implements Serializable {

		S first;

		T second;

		Pair(S s, T t) {
			first = s;
			second = t;
		}

		@Override
		public boolean equals(Object ob) {
			if (ob == null)
				return false;
			if (this == ob)
				return true;
			if (ob.getClass() != getClass())
				return false;
			@SuppressWarnings("unchecked")
			Pair<S, T> p = (Pair<S, T>) ob;
			return p.first.equals(first) && p.second.equals(second);
		}

		@Override
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}

		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}

		private static final long serialVersionUID = 5399827794066637059L;
	}
}
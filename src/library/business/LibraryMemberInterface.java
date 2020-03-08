package library.business;

import java.util.List;

import model.LibraryMember;

public interface LibraryMemberInterface {
	
	public void saveLibraryMember(String firstName, String lastName, String telephone, String street, String city, String state, String zip);
	
	public void updateLibraryMember(String firstName, String lastName, String telephone, String street, String city, String state, String zip);
	
	public List<LibraryMember> getAllLibraryMembers();
}
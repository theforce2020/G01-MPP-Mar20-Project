package library.assistant.business;


import library.assistant.data.model.LibraryMember;

import java.util.List;

public interface LibraryMemberInterface {
	
	public void saveLibraryMember(String firstName, String lastName, String telephone, String street, String city, String state, String zip);
	
	public void updateLibraryMember(String firstName, String lastName, String telephone, String street, String city, String state, String zip);

	public List<LibraryMember> getAllLibraryMembers();
}
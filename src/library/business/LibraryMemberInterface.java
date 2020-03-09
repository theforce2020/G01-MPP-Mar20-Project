package library.business;

import java.util.List;

import library.model.LibraryMember;

public interface LibraryMemberInterface {
	
	public void saveLibraryMember(String memberId, String firstName, String lastName, String telephone, String street, String city, String state, String zip);
	
	public void updateLibraryMember(String memberId, String firstName, String lastName, String telephone, String street, String city, String state, String zip);
	
	public List<LibraryMember> getAllLibraryMembers();
	
	public boolean doesMemberExist(String memberId);
}
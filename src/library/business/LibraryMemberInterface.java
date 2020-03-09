package library.business;

import library.exceptions.LibrarySystemException;
import library.model.LibraryMember;

import java.util.List;

public interface LibraryMemberInterface {

    void saveLibraryMember(String memberId, String firstName, String lastName, String telephone, String street, String city, String state, String zip);

    void updateLibraryMember(String memberId, String firstName, String lastName, String telephone, String street, String city, String state, String zip);

    List<LibraryMember> getAllLibraryMembers();

    boolean doesMemberExist(String memberId);
    
    LibraryMember getMember(String memberId);

    void deleteMember(String memberId) throws LibrarySystemException;
}
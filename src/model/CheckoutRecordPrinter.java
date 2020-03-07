package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.SystemController;

public class CheckoutRecordPrinter {

	
	public void printCheckoutRecords(String memID){
		System.out.println(memID);
		List<CheckoutRecord> l=new ArrayList<CheckoutRecord>();
		SystemController ctl=new SystemController();
		HashMap<String, CheckoutRecord> map = ctl.getCheckoutRecords();
	    System.out.printf("%-10s %-10s %-20s %-10s %-20s %-10s\n", "MemberID", "ISBN", "Book Title","Copies","Checkout Date","Due Date");
	    System.out.println("____________________________________________________________________________________________");
	    for(CheckoutRecord m: map.values()) {
	    	
			if(m.getMember().getMemberId().equals(memID)||m.getCheckoutRecordEntry().getBookCopy().getBook().getIsbn().equals(memID)) {
				System.out.printf("%-10s %-10s %-20s %-10s %-20s %-10s\n", m.getMember().getMemberId(), m.getCheckoutRecordEntry().getBookCopy().getBook().getIsbn(), m.getCheckoutRecordEntry().getBookCopy().getBook().getTitle(),m.getCheckoutRecordEntry().getBookCopy().getCopyNum(),m.getCheckoutRecordEntry().getCheckoutDate(),m.getCheckoutRecordEntry().getDueDate());   
    		}
    		
    	}
	    System.out.println("____________________________________________________________________________________________");
	}
	
	
	
}

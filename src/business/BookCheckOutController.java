package business;

//import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import model.Author;
import model.Book;
import model.BookCopy;
import model.LibraryMember;
import business.SystemController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.TestData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.Messages;

public class BookCheckOutController implements Initializable{
	
	@FXML
	private TextField tfmemberID;
	@FXML
	private TextField tfBookTitle;
    @FXML
    private TextField tfBookIsbn;
    @FXML
    private TextField tfBookCopies;
    @FXML
    private TextField tfBookMax;
    @FXML
    private ListView<String> lvBookAuthors;
    //private ListView<Author> lvBookAuthors;
    
    @FXML
    private Button btnBookFormCancel;
    @FXML
    private Button btnBookFormSave;
    @FXML
    private Text txtBookForm;
    @FXML 
    private ComboBox<String> comboMaxLength;
    @FXML
    private TextArea txtCopiesList;
    
    private SystemController ctl=new SystemController();
   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tfBookCopies.setText("1");
		tfBookCopies.setDisable(true);
		lvBookAuthors.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
		final ObservableList<String> strings = FXCollections.observableArrayList();
    	List<Author> allAuthors = ctl.getAuthors();            	
    	for(Author a: allAuthors) {
    		 //strings.add(a);   
    		 strings.add(a.toString() + " Address: " + a.getAddress().toString());
 		}                                    	
    	lvBookAuthors.setItems(strings);
    	
    	tfBookCopies.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	        	tfBookCopies.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    	
    	comboMaxLength.getItems().add("21");
    	comboMaxLength.getItems().add("7");
   }
	
	public void initData(Book b) {
		tfBookTitle.setText(b.getTitle());
		tfBookIsbn.setText(b.getIsbn());
		comboMaxLength.setValue(String.valueOf(b.getMaxCheckoutLength()));
		int numCopies = b.getNumCopies()>0 ? b.getNumCopies() : 1;
		tfBookCopies.setText(String.valueOf(numCopies));
		
    	List<Author> allAuthors = ctl.getAuthors();
    	int i = 0; 
    	List<Author> alist = b.getAuthors();
		for(Author a: allAuthors) {                                   		 
      		 for(Author a2: alist) {
      			 if(a.getFirstName().equals(a2.getFirstName())) {
      				lvBookAuthors.getSelectionModel().select(i);
      			 }
      		 }
      		 i+=1;
   		}
		
		BookCopy[] bCopies = b.getCopies();
		StringBuilder copiesInfo = new StringBuilder();
		for(BookCopy bc: bCopies) {book
			
			
			
			
			String tmp = bc.isAvailable() ? "Available" : "Not available"; 
			copiesInfo.append("Copy num: " + bc.getCopyNum() + " - " + tmp + " \n");
		}
		txtCopiesList.setText(copiesInfo.toString());
	}
	
	public void addCheckoutRecord(ActionEvent actionEvent) {
		try {
			String errmsg="Error Detail:";
			boolean isValid = true;
			if(tfmemberID.getText()==null || tfmemberID.getText().isEmpty()) {
				isValid=false;
				errmsg+="\n Please enter memberID";
			}
			int found=0;
			SystemController ctl=new SystemController();
			List<String> memID=ctl.allMemberIds();
			for (String string : memID) {
				if(string.equals(tfmemberID.getText())) {
					found=1;
					break;
				}
			}
			if(found==0) {
				isValid=false;
				errmsg+="\n memberID not Found";
			}
			Book bk=ctl.getBook(tfBookIsbn.getText());
			if(!bk.isAvailable()) {
				isValid=false;
				errmsg+="\n Book is not Available";
			}
			if(isValid==true) {
				Book book = ctl.getBook(tfBookIsbn.getText());
				BookCopy[] copies = book.getCopies();
				int copyNum=0;
				for(BookCopy c: copies) {
					if(c.isAvailable()) {
						copyNum = c.getCopyNum();
						c.changeAvailability();
						book.updateCopies(c);
						break;
					}
				}				
				
				LocalDate checkoutDate = LocalDate.now();
				LocalDate dueDate = LocalDate.now().plusDays(Integer.parseInt(comboMaxLength.getSelectionModel().getSelectedItem()));
				String memberId = tfmemberID.getText();
				LibraryMember member = ctl.getMember(memberId);
				String checkoutRecordId = memberId + tfBookIsbn.getText() + copyNum;
				ctl.addCheckoutRecord(checkoutRecordId, book, copyNum, checkoutDate, dueDate, member);
				ctl.updateBook(book);
				txtBookForm.setStyle("-fx-background-color: #E1ECF4;");
				txtBookForm.setStyle("-fx-fill: #0f9d58;");
				txtBookForm.setText("Successfully saved!");
				StringBuilder checkoutInfo = new StringBuilder();
				checkoutInfo.append("Book: " + book.getIsbn() + " (" + book.getTitle()+ ")");
				checkoutInfo.append("\nCopy Num: " + copyNum);
				checkoutInfo.append("\nDue date: " + dueDate);
				Messages.showAlertDialog(Alert.AlertType.CONFIRMATION, "Checkout Information", "Successfully saved!", checkoutInfo.toString());
				
			}
			else {
				Messages.showAlertDialog(Alert.AlertType.ERROR, "Error", "ERROR!", errmsg);
				txtBookForm.setStyle("-fx-fill: red;");
				txtBookForm.setText("Not saved!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeWindow(ActionEvent actionEvent) {
		Stage stage = (Stage) btnBookFormCancel.getScene().getWindow();
        stage.close();
	}

}

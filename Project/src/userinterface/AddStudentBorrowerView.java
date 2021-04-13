// specify the package
package userinterface;

// system imports
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Properties;

// project imports
import impresario.IModel;
import model.Patron;

/** The class containing the Account View  for the ATM application */
//==============================================================
public class AddStudentBorrowerView extends View
{

    // GUI components
    protected  TextField bannerId;
    protected TextField firstName;
    protected TextField lastName;
    protected TextField contactPhone;
    protected TextField email;
    protected ComboBox borrowerStatus;
    protected TextField dateOfLatestBorrowerStatus;
    protected TextField dateOfRegistration;
    protected TextField notes;
    protected ComboBox status;


    protected Button cancelButton;
    protected Button submitButton;

    // For showing error message
    protected MessageView statusLog;

    // constructor for this class -- takes a model object
    //----------------------------------------------------------
    public PatronView(IModel studentBorrower)
    {
        super(StudentBorrower, "AddStudentBorrowerView");

        // create a container for showing the contents
        VBox container = new VBox(10);
        container.setPadding(new Insets(15, 5, 5, 5));

        // Add a title for this panel
        container.getChildren().add(createTitle());

        // create our GUI components, add them to this Container
        container.getChildren().add(createFormContent());

        container.getChildren().add(createStatusLog("             "));

        getChildren().add(container);

        populateFields();

        // myModel.subscribe("ServiceCharge", this);
        //myModel.subscribe("UpdateStatusMessage", this);
    }


    // Create the title container
    //-------------------------------------------------------------
    private Node createTitle()
    {
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER);

        Text titleText = new Text(" New Student Borrower ");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setWrappingWidth(300);
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setFill(Color.DARKGREEN);
        container.getChildren().add(titleText);

        return container;
    }

    // Create the main form content
    //-------------------------------------------------------------
    private VBox createFormContent()
    {
        VBox vbox = new VBox(10);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text prompt = new Text("Student Borrower INFORMATION");
        prompt.setWrappingWidth(400);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);


        Text banID = new Text("Students Banner ID : ");
        Font myFont = Font.font("Helvetica", FontWeight.BOLD, 12);
        banID.setFont(myFont);
        banID.setWrappingWidth(150);
        banID.setTextAlignment(TextAlignment.RIGHT);
        grid.add(banID, 0, 1);

        firstName = new TextField();
        firsName.setEditable(true);
        grid.add(firsName, 1, 1);

        Text firsName = new Text("Students First Name : ");
        Font myFont = Font.font("Helvetica", FontWeight.BOLD, 12);
        firsName.setFont(myFont);
        firsName.setWrappingWidth(150);
        firsName.setTextAlignment(TextAlignment.RIGHT);
        grid.add(firsName, 0, 2);

        firstName = new TextField();
        firsName.setEditable(true);
        grid.add(firsName, 1, 2);

        Text lasName = new Text(" Students Last Name: ");
        Font myFont = Font.font("Helvetica", FontWeight.BOLD, 12);
        lasName.setFont(myFont);
        lasName.setTextAlignment(TextAlignment.RIGHT);
        grid.add(lasName,0,3)

        lasName = new TextField();
        lasName.setEditable(true);
        grid.add(lasName,1,3);


        Text patAddress = new Text(" Patron's Address : ");
        patAddress.setFont(myFont);
        patAddress.setWrappingWidth(150);
        patAddress.setTextAlignment(TextAlignment.RIGHT);
        grid.add(patAddress, 0, 2);

        address = new TextField();
        address.setEditable(true);
        grid.add(address, 1, 2);

        Text patCity = new Text(" Patron's City : ");
        patCity.setFont(myFont);
        patCity.setWrappingWidth(150);
        patCity.setTextAlignment(TextAlignment.RIGHT);
        grid.add(patCity, 0, 3);

        city = new TextField();
        city.setEditable(true);
        grid.add(city, 1, 3);

        Text patState = new Text(" Patron's State : ");
        patState.setFont(myFont);
        patState.setWrappingWidth(150);
        patState.setTextAlignment(TextAlignment.RIGHT);
        grid.add(patState, 0, 4);

        state= new TextField();
        state.setEditable(true);
        grid.add(state, 1, 4);

        Text patZip = new Text(" Patron's Zipcode : ");
        patZip.setFont(myFont);
        patZip.setWrappingWidth(150);
        patZip.setTextAlignment(TextAlignment.RIGHT);
        grid.add(patZip, 0, 5);

        zipcode = new TextField();
        zipcode.setEditable(true);
        grid.add(zipcode, 1, 5);

        Text patEmail = new Text(" Patron's Email : ");
        patEmail.setFont(myFont);
        patEmail.setWrappingWidth(150);
        patEmail.setTextAlignment(TextAlignment.RIGHT);
        grid.add(patEmail, 0, 6);

        email = new TextField();
        email.setEditable(true);
        grid.add(email, 1, 6);

        Text patDOB = new Text(" Patron's Date of Birth : ");
        patDOB.setFont(myFont);
        patDOB.setWrappingWidth(150);
        patDOB.setTextAlignment(TextAlignment.RIGHT);
        grid.add(patDOB, 0, 7);

        dob = new TextField();
        dob.setEditable(true);
        grid.add(dob, 1, 7);


        submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                processAction(e);
            }
        });

        cancelButton = new Button("Back");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                myModel.stateChangeRequest("CancelTransaction", null);
            }
        });

        HBox buttonCont = new HBox(10);
        buttonCont.setAlignment(Pos.CENTER);
        buttonCont.getChildren().add(submitButton);
        Label space = new Label("               ");
        buttonCont.setAlignment(Pos.CENTER);
        buttonCont.getChildren().add(space);
        buttonCont.setAlignment(Pos.CENTER);
        buttonCont.getChildren().add(cancelButton);
        vbox.getChildren().add(grid);
        vbox.getChildren().add(buttonCont);

        return vbox;
    }

    private void processAction(ActionEvent e) {

        clearErrorMessage();

        String banid = bannerId.getText();
        String first = firstName.getText();
        String last = lastName.getText();
        String phone = contactPhone.getText();
        String eml = email.getText();
        String borrowerStat = (String)borrowerStatus.getvalue();
        String dateOfLatest = dateOfLatestBorrowerStatus.getText();
        String dateOfReg = dateOfRegistration.getText();
        String note = notes.getText();
        String stat = (String)status.getvalue();



        String patron = name.getText();
        String pAddress = address.getText();
        String cty = city.getText();
        String zip = zipcode.getText();
        String st = state.getText();
        String eml = email.getText();
        String date = dob.getText();

        Properties p2 = new Properties();
        p2.setProperty("name", patron);
        p2.setProperty("address", pAddress);
        p2.setProperty("city", cty);
        p2.setProperty("stateCode", st);
        p2.setProperty("zip", zip);
        p2.setProperty("email", eml);
        p2.setProperty("dateOfBirth", date);

        Patron pat1 = new Patron(p1);
        pat1.update();
    }


    // Create the status log field
    //-------------------------------------------------------------
    protected MessageView createStatusLog(String initialMessage)
    {
        statusLog = new MessageView(initialMessage);

        return statusLog;
    }

    //-------------------------------------------------------------
    public void populateFields()
    {
       /* accountNumber.setText((String)myModel.getState("AccountNumber"));
        acctType.setText((String)myModel.getState("Type"));
        balance.setText((String)myModel.getState("Balance"));
        serviceCharge.setText((String)myModel.getState("ServiceCharge"));

        */
    }

    /**
     * Update method
     */
    //---------------------------------------------------------
    public void updateState(String key, Object value)
    {
        clearErrorMessage();

        if (key.equals("AddStudentBorrowerMessage") == true)
        {
            displayMessage((String)value);
        }
    }

    /**
     * Display error message
     */
    //----------------------------------------------------------
    public void displayErrorMessage(String message)
    {
        statusLog.displayErrorMessage(message);
    }

    /**
     * Display info message
     */
    //----------------------------------------------------------
    public void displayMessage(String message)
    {
        statusLog.displayMessage(message);
    }

    /**
     * Clear error message
     */
    //----------------------------------------------------------
    public void clearErrorMessage()
    {
        statusLog.clearErrorMessage();
    }

}

//---------------------------------------------------------------
//	Revision History:
//
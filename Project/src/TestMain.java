import java.util.Properties;
import java.util.Scanner;
/*
public class TestMain {

        public static void main(String [] args){

            Scanner input = new Scanner(System.in);
            Properties prop = new Properties();
            BookCollection bookList = new BookCollection();
            PatronCollection patronList = new PatronCollection();

            //Variables for Books
            String bTitle,bAuthor, bYear;

            //variables for Patrons
            String pName,pAddress, pCity, pState,pZip, pEmail, pDOB;



            System.out.println("Entering three books...");
            for(int i = 0; i <= 2; i++) {
                //prop = new Properties();
                System.out.println("Please enter Book's Title");
                bTitle = input.nextLine();
                System.out.println("Please enter Book's Author");
                bAuthor = input.nextLine();
                System.out.println("Please enter Book's year of publication");
                bYear = input.nextLine();

                prop.setProperty("bookTitle", bTitle);
                prop.setProperty("author", bAuthor);
                prop.setProperty("pubYear", bYear);

                Book book = new Book(prop);
                book.update();
            }
            System.out.println("Entering three patrons...");
            for(int index = 0; index <= 2; index++) {
                //prop = new Properties();
                System.out.println("Please enter Patron's Name");
                pName = input.nextLine();
                System.out.println("Please enter Patron's Address");
                pAddress = input.nextLine();
                System.out.println("Please enter Patron's City");
                pCity = input.nextLine();
                System.out.println("Please enter Patron's State Code");
                pState = input.nextLine();
                System.out.println("Please enter Patron's Zipcode");
                pZip = input.nextLine();
                System.out.println("Please enter Patron's Email");
                pEmail = input.nextLine();
                System.out.println("Please enter Patron's Date of Birth");
                pEmail = input.nextLine();

                prop.setProperty("name", pName);
                prop.setProperty("address", pAddress);
                prop.setProperty("city", pCity);
                prop.setProperty("stateCode", pState);
                prop.setProperty("zip", pZip);
                prop.setProperty("address", pAddress);
                prop.setProperty("pubYear", pCity);

                Patron patron = new Patron(prop);
                patron.update();
            }
            //test data for part of Books title
            System.out.println("Please enter a part of a Book's title");
            bTitle = input.nextLine();
            bookList.findBooksWithTitleLike(bTitle);
            System.out.println(bookList.toString());

            //test data for books pub year
            System.out.println("Please enter Book's year of publication");
            bYear = input.nextLine();
            bookList.findBooksOlderThan(bYear);
            System.out.println(bookList.toString());

            //test data for patrons younger than
            System.out.println("Please enter Patron's Date of Birth");
            pDOB = input.nextLine();
            patronList.findPatronsYoungerThan(pDOB);
            System.out.println(patronList.toString());

            //test data for Patron Zip
            System.out.println("Please enter Patron's zipcode");
            pZip = input.nextLine();
            patronList.findPatronsAtZipCode(pZip);
            System.out.println(patronList.toString());

        }
}
 */
public class TestMain{
    public static void main (String [] args){
        try {
            Properties p = new Properties();
            p.setProperty("bannerId", "800732338");
            p.setProperty("password", "Hgt998557");
            p.setProperty("firstName", "Hunter");
            p.setProperty("lastName", "Thomas");
            p.setProperty("contactPhone", "5857389454");
            p.setProperty("email", "huntergthomas1998@gmail.com");
            p.setProperty("credentials", "Administrator");
            p.setProperty("dateOfLatestCredentialStatus", "2021-04-10");
            p.setProperty("dateOfHire", "2020-04-10");
            p.setProperty("status", "Active");

            Worker w = new Worker(p);
            w.update();

            }
            catch (Exception ex) {
                System.out.println("Error in accessing database: " + ex.toString());
            }
        }

}


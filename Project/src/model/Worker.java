package model;

import exception.InvalidPrimaryKeyException;
import java.util.Properties;
import java.sql.SQLException;
import java.util.*;

public class Worker extends EntityBase{
    private static final String myTableName = "Worker";
    protected Properties dependencies;
    private String updateStatusMessage = "Table was updated successfully";

    public Worker(String bannerId) throws InvalidPrimaryKeyException {
        super(myTableName);
        this.setDependencies();
        String query = "SELECT * FROM " + myTableName + " WHERE (bannerId = " + bannerId + ")";
        Vector allDataFromDB = this.getSelectQueryResult(query);
        if (allDataFromDB != null) {
            int dataLen = allDataFromDB.size();
            if (dataLen != 1) {
                throw new InvalidPrimaryKeyException("Multiple Workers matching id : " + bannerId + " found.");
            } else {
                Properties workerIdData = (Properties)allDataFromDB.elementAt(0);
                this.persistentState = new Properties();
                Enumeration workerKeys = workerIdData.propertyNames();

                while(workerKeys.hasMoreElements()) {
                    String nextKey = (String)workerKeys.nextElement();
                    String nextValue = workerIdData.getProperty(nextKey);
                    if (nextValue != null) {
                        this.persistentState.setProperty(nextKey, nextValue);
                    }
                }

            }
        } else {
            throw new InvalidPrimaryKeyException("No Worker matching id : " + bannerId + " found.");
        }
    }

    public Worker(Properties props) {
        super(myTableName);
        this.setDependencies();
        this.persistentState = new Properties();
        Enumeration allKeys = props.propertyNames();

        while(allKeys.hasMoreElements()) {
            String one = (String)allKeys.nextElement();
            String two = props.getProperty(one);
            if (two != null) {
                this.persistentState.setProperty(one, two);
            }
        }
    }

    public Worker() {
    }

    private void setDependencies(){
        this.dependencies = new Properties();
        this.myRegistry.setDependencies(this.dependencies);
    }

    @Override
    public Object getState(String key) {
        return persistentState.getProperty(key);
    }

    @Override
    public void stateChangeRequest(String key, Object value) {

    }

    public String toString()
    {
        return "StudentBorrower: ID: " + getState("bannerId") + " name: " + getState("name") + " email: " + getState("email");
    }

    public static int compare(Worker a, Worker b) {
        String ba = (String)a.getState("name");
        String bb = (String)b.getState("name");
        return ba.compareTo(bb);
    }

    //-----------------------------------------------------------------------------------
    public void update()
    {
        updateStateInDatabase();
    }

    //-----------------------------------------------------------------------------------
    private void updateStateInDatabase()
    {
        try
        {
            if (persistentState.getProperty("bannerId") != null)
            {

                Properties whereClause = new Properties();
                whereClause.setProperty("bannerId", persistentState.getProperty("bannerId"));
                updatePersistentState(mySchema, persistentState, whereClause);
                updateStatusMessage = "BannerId data for worker number : " + persistentState.getProperty("bannerId") + " updated successfully in database!";
            }
            else
            {
                Integer workerId = insertAutoIncrementalPersistentState(mySchema, persistentState);
                persistentState.setProperty("bannerId", "" + workerId);
                updateStatusMessage = "Worker data for new Worker : " +  persistentState.getProperty("bannerId")
                        + "installed successfully in database!";
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Error: " + ex.toString());
            updateStatusMessage = "Error in installing Patron data in database!";
        }
        //DEBUG System.out.println("updateStateInDatabase " + updateStatusMessage);
    }

    @Override
    //------------------------------------------------------------------------------------
    protected void initializeSchema(String tableName) {

        if (mySchema == null)
        {
            mySchema = getSchemaInfo(tableName);
        }
    }

    public Vector<String> getEntryListView()
    {
        Vector<String> v = new Vector<String>();

        v.addElement(persistentState.getProperty("bannerId"));
        v.addElement(persistentState.getProperty("password"));
        v.addElement(persistentState.getProperty("firstName"));
        v.addElement(persistentState.getProperty("lastName"));
        v.addElement(persistentState.getProperty("contactPhone"));
        v.addElement(persistentState.getProperty("email"));
        v.addElement(persistentState.getProperty("credentials"));
        v.addElement(persistentState.getProperty("dateOfLatestCredentialStatus"));
        v.addElement(persistentState.getProperty("dateOfHire"));
        v.addElement(persistentState.getProperty("status"));

        return v;
    }
}

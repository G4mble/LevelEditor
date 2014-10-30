package Controller;

import Model.MaterialModel;

import javax.swing.*;
import java.sql.*;

public class DBController
{
    private Connection connection = null;

    public DBController()
    {
        try
        {
            Class.forName("org.hsqldb.jdbcDriver");
            System.out.println("Treiberklasse geladen!");
        }
        catch(ClassNotFoundException cnfE)
        {
            JOptionPane.showMessageDialog(null, "ErrorMessage: " + cnfE.getMessage() + "\nExceptionType: ClassNotFoundException" +
                    "\nTreiberklasse konnte nicht geladen werden!", "Fehler beim Laden der Datenbank", JOptionPane.ERROR_MESSAGE);
            this.closeConnection();
        }
        try
        {
            this.connection = DriverManager.getConnection("jdbc:hsqldb:file:data\\hsql\\level\\levelDB;ifexists=true;shutdown=true", "root", "");
            System.out.println("HSQLDB verbunden.");
        }
        catch(SQLException sqlE)
        {
            JOptionPane.showMessageDialog(null, "SQLState: " + sqlE.getSQLState() + "\nErrorCode: " + sqlE.getErrorCode() +
                    "\nErrorMessage: " + sqlE.getMessage() + "\nExceptionType: SQLException" +
                    "\nVerbindung zur Datenbank konnte nicht hergestellt werden!", "Fehler beim Laden der Datenbank", JOptionPane.ERROR_MESSAGE);
            this.closeConnection();
        }
    }

    public MaterialModel[][] loadLevel(String paramName)
    {
        MaterialModel[][] tmpMatModelArray = new MaterialModel[21][39];
        try(Statement stmt = this.connection.createStatement(); ResultSet levelResult = stmt.executeQuery("SELECT * FROM " + paramName))
        {
            int j = 0;
            while(levelResult.next())
            {
                for(int i = 0; i < 39; i++)
                {
                    try(ResultSet materialResult = stmt.executeQuery("SELECT materialName FROM materialAllocation WHERE materialID = " + levelResult.getInt(i +1)))
                    {
                        materialResult.next();
                        if(materialResult.getString(1).equals("eraser"))
                            tmpMatModelArray[j][i] = null;
                        else
                            tmpMatModelArray[j][i] = new MaterialModel(materialResult.getString(1), levelResult.getInt(i + 1));
                    }
                }
                j++;
            }
            return tmpMatModelArray;
        }
        catch(SQLException sqlE)
        {
            JOptionPane.showMessageDialog(null, "SQLException\nFehler beim Laden des Levels!\nDBController.loadLevel()" +
                    "\nMessage: " + sqlE.getMessage() + "\nErrorCode: " + sqlE.getErrorCode() + "\nSQLState: " + sqlE.getSQLState(),
                    "Fehler beim Laden des Levels!", JOptionPane.ERROR_MESSAGE);
            this.closeConnection();
            return null;
        }
    }

    public void saveLevel(MaterialModel[][] paramMatModelArray, String paramName)
    {
        StringBuilder createQuery = new StringBuilder("CREATE TABLE " + paramName + "(");
        StringBuilder insertQuery = new StringBuilder("INSERT INTO " + paramName + "(");
        for(int i = 0; i < 39; i++)
        {
            createQuery.append("spalte");
            createQuery.append(i);
            createQuery.append(" INT,");
            insertQuery.append("spalte");
            insertQuery.append(i);
            insertQuery.append(",");
        }
        createQuery.deleteCharAt(createQuery.length() - 1);
        createQuery.append(")");
        insertQuery.deleteCharAt(insertQuery.length() - 1);
        insertQuery.append(") VALUES(");
        String tmpQuery = insertQuery.toString();

        try(Statement stmt = this.connection.createStatement())
        {
            if(this.levelIsExisting(paramName))
                stmt.executeQuery("DROP TABLE " + paramName);

            stmt.executeQuery(createQuery.toString());

            for(int i = 0; i < 21; i++)
            {
                for(int j = 0; j < 39; j++)
                {
                    MaterialModel tmpModel = paramMatModelArray[i][j];
                    if(tmpModel != null)
                        insertQuery.append(tmpModel.getMaterialID());
                    else
                        insertQuery.append(200);
                    insertQuery.append(",");
                }
                insertQuery.deleteCharAt(insertQuery.length() - 1);
                insertQuery.append(")");
                stmt.executeQuery(insertQuery.toString());
                insertQuery = new StringBuilder();
                insertQuery.append(tmpQuery);
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "SQLException\nFehler beim Erstellen von Datenbanktabelle!\nDBController.saveLevel()" +
                    "\nMessage: " + e.getMessage() + "\nErrorCode: " + e.getErrorCode() + "\nSQLState: " + e.getSQLState(),
                    "Fehler beim Speichern des Levels!", JOptionPane.ERROR_MESSAGE);
            this.closeConnection();
        }
    }

    public boolean levelIsExisting(String paramName)
    {
        try
        {
            DatabaseMetaData metaData = this.connection.getMetaData();
            try(ResultSet metaResult = metaData.getTables(null, null, null, new String[]{"TABLE"}))
            {
                while(metaResult.next())
                {
                    if(metaResult.getString("TABLE_NAME").equalsIgnoreCase(paramName))
                        return true;
                }
            }
        }
        catch(SQLException sqlE)
        {
            JOptionPane.showMessageDialog(null, "SQLException\nFehler beim Lesen/Bearbeiten der Metadaten!\n DBController.saveLevel()"+
            "\nMessage: " + sqlE.getMessage() + "\nErrorCode: " + sqlE.getErrorCode() + "\nSQLState: " + sqlE.getSQLState(),
            "Fehler beim Überprüfen des Levels!", JOptionPane.ERROR_MESSAGE);
            this.closeConnection();
        }
        return false;
    }

    public void closeConnection()
    {
        try
        {
            if(this.connection != null)
                this.connection.close();
            System.exit(0);

        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "SQLException\nFehler beim Schließen der connection\nDBController.closeConnection()" +
            "\nMessage: " + e.getMessage() + "\nErrorCode: " + e.getErrorCode() + "\nSQLState: " + e.getSQLState(),
            "Fehler beim Schließen der Verbindung", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}

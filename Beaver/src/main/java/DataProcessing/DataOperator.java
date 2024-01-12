package DataProcessing;

import Controllers.MainMenuController;
import Main.Main;
import ToolPackage.JdbcUtil;
import com.csvreader.CsvReader;
import javafx.concurrent.Task;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * <p> This class store the variables of sql information and read-in csv data list.
 * Methods are provided to read in data from csv, create table in database and insert data.
 * This class also provide a demo method main to call functions to read csv, create table, execute sql sentences.
 *
 * @author Ruibin CHEN
 * @version 1.2
 * @date 2021/1/24-22:10
 * @since 1.0
 */
public class DataOperator extends Task {
    /**
     * the list that store the headers of csv data, which can be seen as attributes of dataset
     */
    private ArrayList<String> headers;
    /**
     * the list that store the values of related attributes
     */
    private ArrayList<String[]> csvList;

    /**
     * the list that store the types of related attributes
     */
    private ArrayList<String> typeList;
    /**
     * the name of the selected file
     */
    private String fileName = null;

    /** 
     * setFileName
     * <p>
     * This method can be used to set the file name of dataset
     *
     * @param fileName user input name for a selected file
     * @author Ruibin CHEN
     * @date   2021/2/12-15:22
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * getFileName
     * <p>
     * This method can be used to get the name of data set file
     *
     * @return A String of file name
     * @author Ruibin CHEN
     * @date   2021/2/12-15:28
     */
    public String getFileName() {
        return fileName;
    }


    /**
     * readCsvFile
     * <p>
     * This method can be used to read data from a selected csv file.
     * The first line of the csv file is treated as headers and store in list headers.
     * The rest lines are values and stored in the list csvList.
     * And finally call checkType function check the attributes type in database, store in the list typeList
     *
     * @param filePath The selected csv file location on the computer.
     * @author Ruibin CHEN
     * @date 2021/1/24-22:31
     */
    public void readCsvFile(String filePath) {
        try {
            csvList = new ArrayList<>();
            headers = new ArrayList<>();
            CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
            reader.readHeaders();
            int i = 0;
            for (i = 0; i < reader.getHeaderCount(); i++) {
                headers.add(reader.getHeader(i));
            }

            for (i = 0; i < headers.size(); i++) {
                System.out.println(headers.get(i));
                if (headers.get(i) == "") {
                    headers.set(i, "Time");
                }
            }


            while (reader.readRecord()) {
                csvList.add(reader.getValues());
            }
            reader.close();
            System.out.println("Line size:" + csvList.size());

//            translateBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkType();

    }

    /**
     * translateBoolean
     * <p>
     * Translate the value which is boolean into "1" as ture and "0" false;
     *
     * @param value the input value in string format
     * @return java.lang.String the string after translation
     * @author Ruibin CHEN
     * @date 2021/2/11-0:17
     */
    private String translateBoolean(String value) {
        if (value.compareToIgnoreCase("True") == 0) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * checkInt
     * <p>
     * Check whether the input string is an Integer.
     * Use Integer.parseInt(), if not in integer format,
     * catch the exception to detect that.
     *
     * @param checkString The input string
     * @return java.lang.Boolean boolean value whether the string is integer
     * @author Ruibin CHEN
     * @date 2021/2/11-0:23
     */
    private Boolean checkInt(String checkString) {
        boolean exception = false;
        try {
            Integer.parseInt(checkString);
        } catch (NumberFormatException ne) {
            exception = true;
        }
        return !exception;
    }

    /**
     * checkDouble
     * <p>
     * Check whether the input string is an Integer.
     * Use Double.pareDouble(), if not in double format,
     * catch the exception to detect that.
     *
     * @param checkString The input string
     * @return java.lang.Boolean boolean value whether the string is double
     * @author Ruibin CHEN
     * @date 2021/2/11-0:29
     */
    private Boolean checkDouble(String checkString) {
        boolean exception = false;
        try {
            Double.parseDouble(checkString);
        } catch (NumberFormatException ne) {
            exception = true;
        }
        return !exception;
    }

    /**
     * checkType
     * <p>
     * Method that check the type of each attribute and store the related type into a list typeList
     *
     * @author Ruibin CHEN
     * @date 2021/2/11-0:30
     */
    private void checkType() {
        typeList = new ArrayList<>();
        for (int i = 0; i < headers.size(); i++) {
            if (csvList.get(0)[i].compareToIgnoreCase("True") == 0 || csvList.get(0)[i].compareToIgnoreCase("False") == 0) {
                typeList.add(i, "tinyint(1)");
                for (int j = 0; j < csvList.size(); j++) {
                    csvList.get(j)[i] = translateBoolean(csvList.get(j)[i]);
                }
            } else if (checkInt(csvList.get(0)[i])) {
                typeList.add(i, "int(11)");
            } else if (checkDouble(csvList.get(0)[i])) {
                typeList.add(i, "double");
            } else {
                typeList.add(i, "varchar(255)");
            }
        }
    }


    /**
     * createDTSTable
     * <p>
     * Create the user specified data table according to the selected dataset
     *
     * @author Ruibin CHEN
     * @date 2021/2/11-0:31
     */
    public void createTable() {
        String tableName = Main.username + "_" + fileName;
        String sql = "select * from TableInfo where UID=" + Main.UID +" AND TableName='" + tableName + "'";


        String sql1 = "DROP TABLE IF EXISTS " + tableName;
        String sql2 = "CREATE TABLE " + tableName + "(\n";
        for (int i = 0; i < typeList.size(); i++) {
            sql2 += "`" + headers.get(i) + "` " + typeList.get(i) + " NOT NULL,\n";
        }
        sql2 += "PRIMARY KEY (`" + headers.get(0) + "`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

        System.out.println(sql2);


        System.out.println("Connecting to a selected database...");
        ResultSet rs;
        try {

            JdbcUtil.connectDatabase();

            rs = JdbcUtil.stmt.executeQuery(sql);
            ArrayList<String> TID = new ArrayList();
            while (rs.next()){
                TID.add(rs.getString(1));
            }

            System.out.println("Deleting existing records");
            for (int i = 0; i < TID.size(); i++){
                sql = "DELETE FROM `TableInfo` WHERE `TableID`=" + TID.get(i);
                System.out.println(sql);
                JdbcUtil.stmt.executeUpdate(sql);
            }
            System.out.println("Creating a table...");

            JdbcUtil.stmt.executeUpdate(sql1);
            JdbcUtil.stmt.executeUpdate(sql2);
            System.out.println("Complete creating...");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeConnection();
        }
    }



    /**
     * call
     * <p>
     * Override the function call of Task.
     * call function to create table;
     * And then inset data into the table;
     * Upgrade the progress property
     *
     * @author Ruibin CHEN
     * @date 2021/2/11-0:33
     */
    @Override
    protected Object call() {
        createTable();

        PreparedStatement st = null;
        Statement st2 = null;
        String tableName = Main.username + "_" + fileName;

        try {
            JdbcUtil.connectDatabase();
            JdbcUtil.con.setAutoCommit(false);
            // prepare the sql temperate
            String sql1 = "insert into " + tableName + " values(";
            for (int i = 0; i < headers.size(); i++) {
                sql1 += "?";
                if (i < headers.size() - 1) {
                    sql1 += ",";
                }
            }
            sql1 += ")";

            System.out.println(sql1);

            long start = System.currentTimeMillis();

            st = JdbcUtil.con.prepareStatement(sql1);

            int n = csvList.size();
            int i = 0;
            for (i = 0; i < n; i++) {

                if (isCancelled()) {
                    System.out.println("Cancelling...");
                    break;
                }

                String[] tempString = csvList.get(i);
                for (int j = 0; j < headers.size(); j++) {
                    st.setString(j + 1, tempString[j]);
                }


                st.execute();
//                conn.commit();
//                st.addBatch();
//                if (i % 1000 == 0) {
//                    st.executeBatch();
//                    st.clearBatch();
//                    System.out.println("Batch " + (i / 1000));
//                    conn.commit();
//                }
                updateProgress(i, n);
                updateMessage(Double.parseDouble(new DecimalFormat("#.##").format(((i*1.0)/(n+1))*100))  + "%");
            }
            //            st.executeBatch();

            System.out.println("Total time is: " + (System.currentTimeMillis() - start));
            updateProgress(i+1,n+1);
            updateMessage("100%");


            String sql2;
            if (isCancelled()){
                sql2 = "DROP TABLE IF EXISTS " + tableName;
                System.out.println(sql2);
                JdbcUtil.stmt.executeUpdate(sql2);
                updateMessage("canceled");
            }else {
                sql2 = "INSERT INTO `TableInfo` (`UID`, `TableName`) VALUES (" + Main.UID + ", '"+ tableName + "')";
                System.out.println(sql2);
                JdbcUtil.stmt.executeUpdate(sql2);
                updateMessage("success");
            }

            JdbcUtil.con.commit();



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            finally block used to close resources
            try {
                if (st != null || st2 != null)
                    JdbcUtil.closeConnect();
            } catch (SQLException ignored) {
            }// do nothing
            try {
                if (JdbcUtil.con != null)
                    JdbcUtil.closeConnect();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }

        System.out.println("Finish");
        return "finish";
    }
}

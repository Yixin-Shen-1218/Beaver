package Controllers;

import ToolPackage.JdbcUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>The ShowDataController is used to show dataset on a stage
 *
 * @author Donglin Jiang
 * @version 1.0
 * @since 1.0
 */
public class ShowDataController {
    /**
     * map list that store the data
     */
    private List<Map<String, String>> dataMapList = new ArrayList<>();
    /**
     * current page index
     */
    private int curPageIndex = 1;
    /**
     * data table
     */
    @FXML
    public TableView<Map<String, String>> dataTable;
    /**
     * label that show the total count of data
     */
    @FXML
    public Label totalCount;
    /**
     * Textfield that allow to input page number
     */
    @FXML
    public TextField curPageInput;
    /**
     * Label that show the current page
     */
    @FXML
    public Label curPageShow;
    /**
     * Button that used to jump to last page
     */
    @FXML
    public Button lastPageBt;
    /**
     * Button that used to jump to next page
     */
    @FXML
    public Button nextPageBt;

    /**
     * The method aims to initialize the first page
     *
     * @throws SQLException sql statement execution exception
     * @throws IOException input output exception
     * @throws ClassNotFoundException not find the class
     * @author Donglin Jiang
     * @date 03/02/2021-13:55
     */
    @FXML
    public void initialize() throws SQLException, IOException, ClassNotFoundException {
        List<String> columnNames = getColumnNames();
        System.out.println(columnNames);
        for (String columnName : columnNames) {
            TableColumn<Map<String, String>, String> tableColumn = new TableColumn<>(columnName);
            tableColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().get(columnName)));
            dataTable.getColumns().add(tableColumn);
        }
        initializeData();
        populateData();
    }

    /**
     * The method aims to get the dataset fields
     *
     * @return a list of dataset fields
     * @throws SQLException           throws exception if sql operations failed
     * @throws ClassNotFoundException throws exception if cannot find the target class
     * @author Donglin Jiang
     * @date 04/02/2021-00:24
     */
    public static List<String> getColumnNames() throws SQLException, ClassNotFoundException {
        List<String> columnNames = new ArrayList<>();

        JdbcUtil.connectDatabase();

        PreparedStatement pStemt = null;

        String sqlStatement = MainMenuController.sqlStatement;
        try {
            pStemt = JdbcUtil.con.prepareStatement(sqlStatement);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    JdbcUtil.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return columnNames;
    }

    /**
     * The method aims to get all dataset values and store them in a list
     *
     * @throws SQLException           throws exception if sql operations failed
     * @throws ClassNotFoundException throws exception if cannot find the target class
     * @author Donglin Jiang
     * @date 04/02/2021-01:16
     */
    private void initializeData() throws SQLException, ClassNotFoundException {
        int rows = getPageRows();
        dataMapList.clear();
        List<String> columnNames = getColumnNames();
        JdbcUtil.connectDatabase();
        PreparedStatement pStemt = null;

        String sqlStatement = MainMenuController.sqlStatement;
        String sqlStatementUse = sqlStatement + " limit " + Integer.toString((curPageIndex - 1) * rows) + ", " + Integer.toString(rows);
        try {
            pStemt = JdbcUtil.con.prepareStatement(sqlStatementUse);
            ResultSet resultSet = pStemt.executeQuery();
            while (resultSet.next()) {
                HashMap<String, String> map = new HashMap<>();
                for (String columnName : columnNames) {
                    String string = resultSet.getString(columnName);
                    map.put(columnName, string);
                }
                dataMapList.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    JdbcUtil.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        totalCount.setText("Total: " + getTotalData());
    }

    /**
     * The method aims to populate page with data in dataset
     *
     * @throws SQLException           throws exception if sql operations failed
     * @throws ClassNotFoundException throws exception if cannot find the target class
     * @author Donglin Jiang
     * @date 04/02/2021-02:46
     */
    private void populateData() throws SQLException, ClassNotFoundException {
        int rows = getPageRows();
        int totalPages = getMaxPage();
        curPageInput.setText(String.valueOf(curPageIndex));
        curPageShow.setText(curPageIndex + "/" + totalPages);
//        List<Map<String, String>> maps = dataMapList.subList((curPageIndex - 1) * rows, Math.min(curPageIndex * rows, dataMapList.size()));
        List<Map<String, String>> maps = dataMapList;
        ObservableList<Map<String, String>> items = dataTable.getItems();
        items.clear();
        items.addAll(maps);
//        curPageInput.setText(String.valueOf(curPageIndex));
//        curPageShow.setText(curPageIndex + "/" + totalPages);
    }

    /**
     * The method aims to calculate the number of rows per page
     * <p> Calculate the number of rows per page.
     *
     * @return int num
     * @author Donglin Jiang
     * @date   16/03/2021-21:05
     */
    private int getPageRows() {
        double num = dataTable.getPrefHeight() / dataTable.getFixedCellSize() - 1;
        return (int) num;
    }

    /**
     * The method aims to calculate how many pages are needed to hold the whole dataset
     *
     * @throws SQLException           throws exception if sql operations failed
     * @throws ClassNotFoundException throws exception if cannot find the target class
     * @return int max page
     * @author Donglin Jiang
     * @date 04/02/2021-01:57
     */
    private int getMaxPage() throws SQLException, ClassNotFoundException {
        int count = getTotalData();
        int i = count / getPageRows();
        if (count % getPageRows() > 0) {
            i = i + 1;
        }
        return i;
    }

    /**
     * The method aims to get the total amount of data in database table
     *
     * @return the total amount of data
     * @throws SQLException           throws exception if sql operations failed
     * @throws ClassNotFoundException throws exception if cannot find the target class
     * @author Donglin Jiang
     * @date 24/02/2021-22:47
     */
    private int getTotalData() throws SQLException, ClassNotFoundException {
        JdbcUtil.connectDatabase();
        PreparedStatement pStemt = null;
        String sqlStatement = MainMenuController.sqlStatement;
        String lastWord = sqlStatement.substring(sqlStatement.indexOf("FROM") + 5);
        String sqlTotal = "select count(*) from " + lastWord;
        pStemt = JdbcUtil.con.prepareStatement(sqlTotal);
        ResultSet rs = pStemt.executeQuery();
        int count = 0;
        while (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }

    /**
     * The method aims to jump to the specific page based on user input
     *
     * @param actionEvent Mouse Click Event
     * @throws SQLException           throws exception if sql operations failed
     * @throws ClassNotFoundException throws exception if cannot find the target class
     * @author Donglin Jiang
     * @date 04/02/2021-03:18
     */
    public void onEnter(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int totalPages = getMaxPage();
        curPageIndex = Integer.parseInt(curPageInput.getText());
        if (curPageIndex <= 0) {
            curPageIndex = 1;
        }
        if (curPageIndex > totalPages) {
            curPageIndex = totalPages;
        }
        initializeData();
        populateData();
    }

    /**
     * The method aims to populate the next page
     *
     * @param actionEvent Mouse Click Event
     * @throws SQLException           throws exception if sql operations failed
     * @throws ClassNotFoundException throws exception if cannot find the target class
     * @author Donglin Jiang
     * @date 04/02/2021-02:44
     */
    public void nextPage(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int totalPages = getMaxPage();
        if (curPageIndex == totalPages) {
            return;
        } else {
            curPageIndex = curPageIndex + 1;
            initializeData();
            populateData();
        }
    }

    /**
     * The method aims to populate the previous page
     *
     * @param actionEvent Mouse Click Event
     * @throws SQLException           throws exception if sql operations failed
     * @throws ClassNotFoundException throws exception if cannot find the target class
     * @author Donglin Jiang
     * @date 04/02/2021-02:44
     */
    public void lastPage(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (curPageIndex == 1) {
            return;
        } else {
            curPageIndex = curPageIndex - 1;
            initializeData();
            populateData();
        }
    }

}
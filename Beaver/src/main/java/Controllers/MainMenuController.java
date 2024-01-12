package Controllers;

import Charts.LineChartUtil;
import DataProcessing.ScenarioAnalysis;
import Main.Main;
import Music.ConfirmSound;
import Music.ErrorSound;
import ToolPackage.JdbcUtil;
import TreeControl.TreeItemCollection;
import TreeControl.TreeItemObject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * <p>
 * The main menu controller class.
 * Two button events have been completed: import mev data and import dts data
 *
 * @author Yixin SHEN
 * @version 1.2
 * @date 2021/1/24-23:03
 * @since 1.0
 */
public class MainMenuController {
    /**
     * Button that used to run model
     */
    public Button runBtn;
    /**
     * DR_chart Container
     */
    public AnchorPane Shot;
    /**
     * Loglike_chart Container
     */
    public AnchorPane Shot2;
    /**
     * Default Rate Chart
     */
    public LineChart<String, Number> DR_Chart;
    /**
     * Log like Chart
     */
    public LineChart<String, Number> LogLike_Chart;
    /**
     * Scenario Chart
     */
    public LineChart<String, Number> ScenarioChart;
    /**
     * Menu item that used to export Default Rate
     */
    public MenuItem ExportDR;
    /**
     * Menu item that used to contain different Export functions
     */
    public MenuItem Export;
    /**
     * Menu item that show the user guide
     */
    public MenuItem userGuide;
    /**
     * Tree view that show the user's data set, model, records, scenarios...
     */
    public TreeView<TreeItemObject> treeView;
    /**
     * TableView that show the statistic value of scenario
     */
    public TableView StatisticTable;
    /**
     * ComboBox that allow user to select the data set
     */
    public ComboBox<TreeItemObject> DataSetsBox;
    /**
     * ComboBox that allow user to select the model
     */
    public ComboBox<TreeItemObject> ModelBox;
    /**
     * ComboBox that allow user to select the scenario
     */
    public ComboBox<TreeItemObject> ScenarioBox;
    /**
     * Label that used to show the user name
     */
    public Label UserLabel;
    /**
     * ProgressBar that used to show the progress of the model run
     */
    public ProgressBar bar;
    /**
     * Object that created to calculate and deal with the charts and table
     */
    public LineChartUtil LineChartUtil;
    /**
     * User created thread to execute the task
     */
    public Thread task;
    /**
     * Stage that used to import data
     */
    public static Stage ImportDataStage;
    /**
     * Stage that used to merge records
     */
    public static Stage MergeStage;
    /**
     * HBox that used to contain the tools
     */
    public HBox toolbar;
    /**
     * Tree item list
     */
    public static TreeItemCollection treeItemCollection;
    /**
     * Defines the sqlStatement when open data sets, store the corresponding sqlStatement
     */
    public static String sqlStatement;
    /**
     * separator 1
     */
    public Separator Separator1;
    /**
     * separator 2
     */
    public Separator Separator2;
    /**
     * Chats Container tab pane
     */
    public TabPane ChartsContainer;
    /**
     * data set label
     */
    public Label DataSetsLabel;
    /**
     * model label
     */
    public Label ModelLabel;
    /**
     * scenario lable
     */
    public Label ScenarioLabel;
    /**
     * desktop
     */
    public Desktop desk = Desktop.getDesktop();
    /**
     * check to show tool bar
     */
    public CheckMenuItem ShowToolBarCheck;
    /**
     * check to show tree view
     */
    public CheckMenuItem ShowTreeviewCheck;
    /**
     * check to show symbols
     */
    public CheckMenuItem ShowSymbolCheck;
    /**
     * Button that used to save results
     */
    public Button saveBtn;
    /**
     * file menu
     */
    public Menu file;
    /**
     * database menu item that open stage to import database
     */
    public MenuItem database;
    /**
     * Menu that open recent records
     */
    public Menu OpenRecent;
    /**
     * Button that used to show the information of user
     */
    public Button userInfo;
    /**
     * Button that used to export the calculated results
     */
    public Button ExportResults;
    /**
     * Button that used to export the Log_Like chart
     */
    public Button ExportLogChart;
    /**
     * Button that used to export the DR chart
     */
    public Button ExportDRChart;
    /**
     * table column name
     */
    public TableColumn name;
    /**
     * table column min
     */
    public TableColumn min;
    /**
     * table column mean
     */
    public TableColumn mean;
    /**
     * table column max
     */
    public TableColumn max;
    /**
     * table column sd (standard)
     */
    public TableColumn sd;

    /**
     * initialize
     * <p>
     * Initialize the the controller, set greeting
     *
     * @author Ruibin CHEN
     * @date 2021/3/10-20:18
     */
    public void initialize() throws IOException {
        Main.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                String fileName = "TreeSource/" + Main.username + "Source.txt";
                File file = new File(fileName);
                System.out.println("SAVE: " + file.getAbsolutePath());
                SaveSource(fileName);

                System.exit(0);
                Platform.exit();
            }
        });

        buildComboBox();
        buildTree();
        SetExportListener();
        InitializeTable();
        UserLabel.setText("Hello, " + Main.username);
    }

    /**
     * readScenario
     * <p>
     * Read scenario value from the comboBox
     *
     * @return the value of the scenario
     * @author Ruibin CHEN
     * @date 2021/3/14-20:24
     */
    private String readScenario() {
        String scenarioFile = ScenarioBox.getValue().getItemValue();
        String scenario = "0";
        if (!scenarioFile.equals("no-stress test")) {
            scenario = scenarioFile;
        }
        System.out.println(scenario);
        return scenario;
    }

    /**
     * readDataSets
     * <p>
     * Read readDataSets statement from the comboBox
     *
     * @return the value of the DataSets statement
     * @author Donglin Jiang
     * @date 2021/3/14-23:04
     */
    private String readDataSets() {
        String dataSetsFile = DataSetsBox.getValue().getItemValue();
        String dataSets = "0";
        if (!dataSetsFile.equals("use local data")) {
            dataSets = dataSetsFile;
        }
        System.out.println(dataSets);
        return dataSets;
    }

    /**
     * InitializeTable
     * <p>
     * Initialize the table column
     *
     * @author Ruibin CHEN
     * @date 2021/3/14-20:25
     */
    private void InitializeTable() {
        name.setCellValueFactory(new PropertyValueFactory("name"));
        min.setCellValueFactory(new PropertyValueFactory("min"));
        mean.setCellValueFactory(new PropertyValueFactory("mean"));
        max.setCellValueFactory(new PropertyValueFactory("max"));
        sd.setCellValueFactory(new PropertyValueFactory("sd"));
    }

    /**
     * RunModel
     * <p>
     * Once model application button is clicked, run model to calculate the data
     *
     * @param actionEvent button click event
     * @author Ruibin CHEN
     * @date 2021/3/14-20:25
     */
    public void RunModel(ActionEvent actionEvent) {
        String model = ModelBox.getValue().getItemValue();
        System.out.println(model);

        //DR line chart.....
        LineChartUtil = new LineChartUtil(DR_Chart, LogLike_Chart, ScenarioChart, StatisticTable, readDataSets(), readScenario(), model);
        LineChartUtil.OperateLineChart();
        task = new Thread(LineChartUtil);
        task.start();

        bar.progressProperty().bind(LineChartUtil.progressProperty());
    }

    /**
     * Build the combo boxes.
     * <p> Build the combo boxes, set the options in the combo boxes.
     *
     * @author Yixin SHEN
     * @date 24/01/2021-19:33
     */
    public void buildComboBox() {
        // Build DataSetBox
        TreeItemObject LocalData = new TreeItemObject(2, false, "use local data", "use local data");
        DataSetsBox.getItems().add(LocalData);
        DataSetsBox.getItems().addAll(treeItemCollection.getDataSetsList());
        DataSetsBox.getSelectionModel().selectFirst();

        DataSetsBox.setConverter(new StringConverter<TreeItemObject>() {
            @Override
            public String toString(TreeItemObject object) {
                String value = null;
                if (object != null) {
                    value = object.getItemName();
                }
                return value;
            }

            @Override
            public TreeItemObject fromString(String s) {
                return null;
            }
        });

        // Build ModelBox
        ModelBox.getItems().addAll(treeItemCollection.getModelsList());
        ModelBox.getSelectionModel().selectFirst();

        ModelBox.setConverter(new StringConverter<TreeItemObject>() {
            @Override
            public String toString(TreeItemObject object) {
                String value = null;
                if (object != null) {
                    value = object.getItemName();
                }
                return value;
            }

            @Override
            public TreeItemObject fromString(String s) {
                return null;
            }
        });

        // Build ScenarioBox
        TreeItemObject noStreeTest = new TreeItemObject(3, false, "no-stress test", "no-stress test");
        ScenarioBox.getItems().add(noStreeTest);
        ScenarioBox.getItems().addAll(treeItemCollection.getScenariosList());
        ScenarioBox.getSelectionModel().selectFirst();

        ScenarioBox.setConverter(new StringConverter<TreeItemObject>() {
            @Override
            public String toString(TreeItemObject object) {
                String value = null;
                if (object != null) {
                    value = object.getItemName();
                }
                return value;
            }

            @Override
            public TreeItemObject fromString(String s) {
                return null;
            }
        });
    }

    /**
     * Build the TreeView.
     * <p> Build the combo boxes, set the tree items in the tree view and use the cellFactory to override the tree view.
     *
     * @author Yixin SHEN
     * @date 24/01/2021-19:35
     */
    public void buildTree() {
        TreeItem<TreeItemObject> root, Models, DataSets, Scenarios, Records;
        root = new TreeItem<TreeItemObject>(new TreeItemObject(0, false, "root", "root"));
        root.setExpanded(true);

        TreeItemObject ModelsObject, DataSetsObject, ScenariosObject, RecordsObject;
        ModelsObject = new TreeItemObject(0, false, "My Models", "Models");
        DataSetsObject = new TreeItemObject(0, false, "My Data Sets", "DataSets");
        ScenariosObject = new TreeItemObject(0, false, "My Scenarios", "Scenarios");
        RecordsObject = new TreeItemObject(0, false, "History Records", "Records");

        // Set the tree branches
        Models = makeBranch(ModelsObject, root);
        for (int i = 0; i < treeItemCollection.getModelsList().size(); i++) {
            makeBranch(treeItemCollection.getModelsList().get(i), Models);
        }

        DataSets = makeBranch(DataSetsObject, root);
        for (int i = 0; i < treeItemCollection.getDataSetsList().size(); i++) {
            makeBranch(treeItemCollection.getDataSetsList().get(i), DataSets);
        }

        Scenarios = makeBranch(ScenariosObject, root);
        for (int i = 0; i < treeItemCollection.getScenariosList().size(); i++) {
            makeBranch(treeItemCollection.getScenariosList().get(i), Scenarios);
        }

        Records = makeBranch(RecordsObject, root);
        for (int i = 0; i < treeItemCollection.getRecordsList().size(); i++) {
            makeBranch(treeItemCollection.getRecordsList().get(i), Records);
        }

        // Set the CellFactory of the tree view
        treeView.setCellFactory(new Callback<TreeView<TreeItemObject>, TreeCell<TreeItemObject>>() {
            @Override
            public TreeCell<TreeItemObject> call(TreeView<TreeItemObject> stringTreeView) {
                TreeCell<TreeItemObject> treeCell = new TreeCell<TreeItemObject>() {
                    @Override
                    protected void updateItem(TreeItemObject s, boolean b) {
                        super.updateItem(s, b);

                        TreeCell<TreeItemObject> cell = this;

                        if (!b) {
                            ContextMenu contextMenu = new ContextMenu();
                            MenuItem menuItem1 = new MenuItem("Open");
                            MenuItem menuItem2 = new MenuItem("Rename");
                            MenuItem menuItem3 = new MenuItem("Delete");
                            MenuItem menuItem4 = new MenuItem("Favour");

                            menuItem1.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    try {
                                        Open(s);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                            menuItem2.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    Rename(s);
                                }
                            });


                            menuItem3.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    Delete(s);
                                }
                            });


                            menuItem4.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    System.out.println(s.getItemName() + " " + s.getItemFavorite());
                                    s.setItemFavorite(!s.getItemFavorite());
                                    reload();
                                }
                            });

                            contextMenu.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4);

                            HBox hBox = new HBox();
                            Node Icon = null;

                            switch (this.getTreeItem().getParent().getValue().getItemName()) {
                                case "My Models":
                                    Icon = new ImageView(new Image(getClass().getResourceAsStream("/Image/TreeIcon/python.png"), 16, 16, false, false));
                                    break;
                                case "My Data Sets":
                                    Icon = new ImageView(new Image(getClass().getResourceAsStream("/Image/TreeIcon/data.png"), 16, 16, false, false));
                                    break;
                                case "My Scenarios":
                                    Icon = new ImageView(new Image(getClass().getResourceAsStream("/Image/TreeIcon/scenario.png"), 16, 16, false, false));
                                    break;
                                case "History Records":
                                    Icon = new ImageView(new Image(getClass().getResourceAsStream("/Image/TreeIcon/chart.png"), 16, 16, false, false));
                                    break;
                                default:
                                    Icon = new ImageView(new Image(getClass().getResourceAsStream("/Image/TreeIcon/list.png"), 16, 16, false, false));
                                    break;
                            }

                            Label label = new Label(s.getItemName());
                            Region region = new Region();
                            HBox.setHgrow(region, Priority.ALWAYS);

                            if (this.getTreeView().getTreeItemLevel(this.getTreeItem()) == 1) {
                                hBox.getChildren().addAll(Icon, label, region);
                            } else {
                                if (s.getItemFavorite()) {
                                    ImageView favor = new ImageView(new Image(getClass().getResourceAsStream("/Image/TreeIcon/favor.png")));
                                    favor.setFitHeight(16);
                                    favor.setFitWidth(16);
                                    hBox.getChildren().addAll(Icon, label, region, favor);
                                } else {
                                    ImageView unfavor = new ImageView(new Image(getClass().getResourceAsStream("/Image/TreeIcon/unfavor.png")));
                                    unfavor.setFitWidth(16);
                                    unfavor.setFitHeight(16);
                                    hBox.getChildren().addAll(Icon, label, region, unfavor);
                                }
                                this.setContextMenu(contextMenu);

                                // Double click the TreeItem, open the corresponding treeObject
                                this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent mouseEvent) {
                                        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                                            if (mouseEvent.getClickCount() == 2) {
                                                try {
                                                    Open(s);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                            this.setGraphic(hBox);
                        } else if (b) {
                            this.setGraphic(null);
                        }
                    }
                };

                return treeCell;
            }
        });

        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }

    /**
     * Make the tree view branch.
     * <p> Utilize two parameters, one is object, another is parent. Set the object as the child of parent.
     *
     * @param object The child tree item.
     * @param parent The parent tree item.
     * @return The tree child item in the tree view.
     * @author Yixin SHEN
     * @date 24/01/2021-19:39
     */
    public TreeItem<TreeItemObject> makeBranch(TreeItemObject object, TreeItem<TreeItemObject> parent) {
        TreeItem<TreeItemObject> item = new TreeItem<TreeItemObject>(object);
        item.setExpanded(true);

        parent.getChildren().add(item);

        return item;
    }

    /**
     * Reload the tree view and combo boxes.
     * <p> Clear the combo boxes, rebuild the combo boxes and tree view.
     *
     * @author Yixin SHEN
     * @date 24/01/2021-19:42
     */
    public void reload() {
        DataSetsBox.getItems().clear();
        ScenarioBox.getItems().clear();
        ModelBox.getItems().clear();
        buildComboBox();
        buildTree();
    }

    /**
     * Open click action.
     * <p> When type == 1 or 3, open the file (.py, .txt) by software registered on the desktop.
     *
     * @param s The tree item object. (Which tree item is clicked)
     * @throws IOException the input output exception
     * @author Yixin SHEN
     * @date 24/01/2021-19:43
     */
    public void Open(TreeItemObject s) throws IOException {
        int type = s.getItemType();
        if (type == 1) {
            File file = new File(s.getItemValue());
            desk.open(file);
        } else if (type == 2) {
            sqlStatement = s.getItemValue();
            int status = JdbcUtil.checkSQL(sqlStatement);
            if (status == 0) {
                try {
                    String lastWord = sqlStatement.substring(sqlStatement.indexOf("FROM") + 5);
                    String sqlTotal = "select count(*) from " + lastWord;
                    System.out.println(sqlTotal);

                    Stage dataStage = new Stage();
                    dataStage.setTitle("Dataset");
                    Scene ShowDataScene = new Scene(Main.loadFXML("/FXML/ShowData"));
                    dataStage.setScene(ShowDataScene);
                    Main.setlogo(dataStage);
                    dataStage.setResizable(false);
                    dataStage.show();
                } catch (javafx.fxml.LoadException e) {
                    InvalidSQLWarning();
                }
            } else if (status == 1) {
                InvalidSQLWarning();
            } else if (status == 2) {
                ConnectWarning();
            }


        } else if (type == 3) {
            File file = new File(s.getItemValue());
            desk.open(file);
        } else {
            LoadRecord(s);
        }
    }

    /**
     * Load the history record.
     * <p> Load the history record to the UI, get and process the record value, then load the value to show the history record.
     *
     * @param s TreeItem Object
     * @author Yixin SHEN
     * @date 28/02/2021-12:58
     */
    private void LoadRecord(TreeItemObject s) {
        DR_Chart.getData().clear();
        LogLike_Chart.getData().clear();
        ScenarioChart.getData().clear();

        String[] recordValue = s.getItemValue().split("//");

        System.out.println(recordValue.length);

        DR_Chart.setTitle(recordValue[0]);
        DR_Chart.getXAxis().setLabel(recordValue[1]);
        DR_Chart.getYAxis().setLabel(recordValue[2]);

        for (int j = 1; j < recordValue.length; j++) {
            System.out.println(recordValue[j]);
        }

        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.setName(recordValue[3]);
        String[] data1 = recordValue[4].split(",");
        for (String datum : data1) {
            String[] XYValue = datum.split("--");
            BigDecimal bd = new BigDecimal(XYValue[1]);
            series1.getData().add(new XYChart.Data<String, Number>(XYValue[0], bd));
            System.out.println(Double.parseDouble(bd.toPlainString()));
        }

        XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
        series2.setName(recordValue[5]);
        String[] data2 = recordValue[6].split(",");
        for (String datum : data2) {
            String[] XYValue = datum.split("--");
            BigDecimal bd = new BigDecimal(XYValue[1]);
            series2.getData().add(new XYChart.Data<String, Number>(XYValue[0], bd));
            System.out.println(Double.parseDouble(bd.toPlainString()));
        }

        DR_Chart.getData().addAll(series1, series2);

        // Add tooltips to the chart if setCreateSymbols set true
        addToolTip(series1);
        addToolTip(series2);

        LogLike_Chart.setTitle(recordValue[7]);
        LogLike_Chart.getXAxis().setLabel(recordValue[8]);
        LogLike_Chart.getYAxis().setLabel(recordValue[9]);

        XYChart.Series<String, Number> seriesLoss = new XYChart.Series<String, Number>();
        seriesLoss.setName(recordValue[10]);
        String[] LossData = recordValue[11].split(",");
        for (String datum : LossData) {
            String[] XYValue = datum.split("--");
            BigDecimal bd = new BigDecimal(XYValue[1]);
            seriesLoss.getData().add(new XYChart.Data<String, Number>(XYValue[0], bd));
            System.out.println(Double.parseDouble(bd.toPlainString()));
        }

        LogLike_Chart.getData().add(seriesLoss);
        addToolTip(seriesLoss);

        ScenarioChart.setTitle(recordValue[12]);
        ScenarioChart.getXAxis().setLabel(recordValue[13]);
        ScenarioChart.getYAxis().setLabel(recordValue[14]);

        XYChart.Series<String, Number> seriesScenario = new XYChart.Series<String, Number>();
        seriesScenario.setName(recordValue[15]);
        String[] ScenarioData = recordValue[16].split(",");
        for (String datum : ScenarioData) {
            String[] XYValue = datum.split("--");
            BigDecimal bd = new BigDecimal(XYValue[1]);
            seriesScenario.getData().add(new XYChart.Data<String, Number>(XYValue[0], bd));
            System.out.println(Double.parseDouble(bd.toPlainString()));
        }

        ScenarioChart.getData().add(seriesScenario);
        addToolTip(seriesScenario);

        ObservableList<ScenarioAnalysis> list = FXCollections.observableArrayList();
        list.add(new ScenarioAnalysis(recordValue[17], recordValue[18], recordValue[19], recordValue[20], recordValue[21]));
        StatisticTable.setItems(list);

        final String liveScatterChartCss = getClass().getResource("/CSS/LineChart.css").toExternalForm();
        DR_Chart.getStylesheets().add(liveScatterChartCss);
        LogLike_Chart.getStylesheets().add(liveScatterChartCss);
        ScenarioChart.getStylesheets().add(liveScatterChartCss);
    }

    /**
     * Delete click action.
     * <p> Prompt a delete dialog and delete the tree item and item in combo box if choose yes.
     *
     * @param s The tree item object. (Which tree item is clicked)
     * @author Yixin SHEN
     * @date 24/01/2021-19:46
     */
    public void Delete(TreeItemObject s) {
        int type = s.getItemType();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(treeView.getScene().getWindow());
        alert.setTitle("Confirmation Dialog");
        if (type == 1) {
            alert.setHeaderText("DELETE THE MODEL");
        } else if (type == 2) {
            alert.setHeaderText("DELETE THE DATA SETS");
        } else if (type == 3) {
            alert.setHeaderText("DELETE THE SCENARIO");
        } else {
            alert.setHeaderText("DELETE THE RECORDS");
        }
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
            if (result.get() == ButtonType.OK) {
                if (type == 1) {
                    treeItemCollection.getModelsList().remove(s);
                    reload();
                } else if (type == 2) {
                    treeItemCollection.getDataSetsList().remove(s);
                    reload();
                } else if (type == 3) {
                    treeItemCollection.getScenariosList().remove(s);
                    reload();
                } else {
                    treeItemCollection.getRecordsList().remove(s);
                    reload();

                    OpenRecent.getItems().removeIf(menuItem -> s.getItemName().equals(menuItem.getText()));
                    if (OpenRecent.getItems().size() == 0) {
                        OpenRecent.setDisable(true);
                    }
                }
            } else {
                System.out.println("delete cancel");
            }
    }

    /**
     * Rename click action.
     * <p> Prompt a rename dialog, allowing user enter a new name.
     * If choose yes, set the item as new name; else set unsuccessfully;
     *
     * @param s The tree item object. (Which tree item is clicked)
     * @author Yixin SHEN
     * @date 24/01/2021-19:47
     */
    public void Rename(TreeItemObject s) {
        TextInputDialog dialog = new TextInputDialog(s.getItemName());
        dialog.setTitle("Rename");
        dialog.setHeaderText("Set a new name for the item");
        dialog.setContentText("Please enter a new name:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            // If the new name is equal to the old name, return
            if (s.getItemName().equals(result.get())) {
                return;
            }

            // If the new name is duplicate in the same type, prompt a warning dialog
            if (s.getItemType() == 1) {
                for (int i = 0; i < treeItemCollection.getModelsList().size(); i++) {
                    if (treeItemCollection.getModelsList().get(i).getItemName().equals(result.get())) {
                        DuplicateWarning();
                        return;
                    }
                }
            } else if (s.getItemType() == 2) {
                for (int i = 0; i < treeItemCollection.getDataSetsList().size(); i++) {
                    if (treeItemCollection.getDataSetsList().get(i).getItemName().equals(result.get())) {
                        DuplicateWarning();
                        return;
                    }
                }
            } else if (s.getItemType() == 3) {
                for (int i = 0; i < treeItemCollection.getScenariosList().size(); i++) {
                    if (treeItemCollection.getScenariosList().get(i).getItemName().equals(result.get())) {
                        DuplicateWarning();
                        return;
                    }
                }
            } else if (s.getItemType() == 4) {
                for (int i = 0; i < treeItemCollection.getRecordsList().size(); i++) {
                    if (treeItemCollection.getRecordsList().get(i).getItemName().equals(result.get())) {
                        DuplicateWarning();
                        return;
                    }
                }
            }

            // Set the MenuItem name in the open recent menu and Reset the onAction method
            int n = OpenRecent.getItems().size();

            for (int i = 0; i < n; i++) {
                MenuItem menuItem = OpenRecent.getItems().get(i);
                if (menuItem.getText().equals(s.getItemName())) {
                    menuItem.setText(result.get());
                    menuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            List<TreeItemObject> RecordsList = treeItemCollection.getRecordsList();
                            for (TreeItemObject treeItemObject : RecordsList) {
                                if (treeItemObject.getItemName().equals(result.get())) {
                                    LoadRecord(treeItemObject);
                                }
                            }
                        }
                    });
                }
            }

            s.setItemName(result.get());
            System.out.println("New name: " + result.get());
            reload();
        } else {
            System.out.println("Rename cancel");
        }
    }

    /**
     * Import a new model.
     * <p> Prompt a file chooser which only show the .py file. If import successfully, set the item name as file name, value as file path,
     * type as 1 and favor as false.
     *
     * @param actionEvent Click action.
     * @author Yixin SHEN
     * @date 24/01/2021-19:50
     */
    public void ImportModel(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Model");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Dynamic Model in Python", "*.py"));
        File file = fileChooser.showOpenDialog(Main.primaryStage);
        if (file != null) {
            System.out.println("path: " + file.getAbsolutePath() + " Name: " + file.getName());
            TreeItemObject NewModel = new TreeItemObject(1, false, file.getName(), file.getAbsolutePath());
            treeItemCollection.getModelsList().add(NewModel);
            reload();
        }
    }

    /**
     * Import a new DataSet.
     * <p> Prompt a new stage to set the filter condition. Generate a new sqlStatement as a new data set.
     *
     * @param actionEvent Click action.
     * @throws IOException the input output exception
     * @author Yixin SHEN
     * @date 24/01/2021-19:50
     */
    public void ImportDataSet(ActionEvent actionEvent) throws IOException {
        Stage FilterStage = new Stage();
        FilterStage.setTitle("Import Data Sets");
        Scene FilterScene = new Scene(Main.loadFXML("/FXML/Filter"));
        FilterStage.setScene(FilterScene);
        Main.setlogo(FilterStage);
        FilterStage.show();
    }

    /**
     * Import a new Scenario.
     * <p> Prompt a file chooser which only show the .txt file. If import successfully, set the item name as file name, value as file path,
     * type as 1 and favor as false.
     *
     * @param actionEvent Click action.
     * @author Yixin SHEN
     * @date 24/01/2021-19:50
     */
    public void ImportScenario(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Scenario");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Stress Test Scenario", "*.txt"));
        File file = fileChooser.showOpenDialog(Main.primaryStage);
        if (file != null) {
            System.out.println("path: " + file.getAbsolutePath() + " Name: " + file.getName());
            TreeItemObject NewScenario = new TreeItemObject(3, false, file.getName(), file.getAbsolutePath());
            treeItemCollection.getScenariosList().add(NewScenario);
            reload();
        }
    }


    /**
     * Set a listener for menu item "export".
     * If clicked "model fit", the chart will be loaded and the menu item will be set as false, otherwise true.
     *
     * @author Yuxin SHI
     * @date 05/02/2021-20:03
     */
    public void SetExportListener() {
        DR_Chart.titleProperty().addListener((observableValue, s, t1) -> {
            if (DR_Chart.getTitle() != null) {
                Export.setDisable(false);
                ExportDRChart.setDisable(false);
                ExportLogChart.setDisable(false);
                ExportResults.setDisable(false);
            }
        });
    }

    /**
     * Export the chart to local
     * Export DR chart to local in PNG format if user click "export" in menu
     *
     * @param actionEvent click action
     * @author Yuxin SHI
     * @date 05/02/2021-20:03
     */
    public void ExportDRChart(ActionEvent actionEvent) {
        File file = null;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG File(.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save As");
        file = fileChooser.showSaveDialog(Main.primaryStage);

        if (file != null) {
            WritableImage image = Shot.snapshot(new SnapshotParameters(), null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Export the chart to local
     * Export LogLikelihood chart to local in PNG format if user click "export" in menu
     *
     * @param actionEvent click action
     * @author Yuxin SHI
     * @date 05/02/2021-20:03
     */
    public void ExportLogLikeChart(ActionEvent actionEvent) {
        File file = null;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG File(.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save As");
        file = fileChooser.showSaveDialog(Main.primaryStage);

        if (file != null) {
            WritableImage image = Shot2.snapshot(new SnapshotParameters(), null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Exit the software.
     * <p> Exit the software after save the user status by invoke SaveSource function.
     *
     * @param actionEvent Click action.
     * @author Yixin SHEN
     * @date 24/01/2021-19:53
     */
    public void ExitSoftware(ActionEvent actionEvent) {
        String fileName = "TreeSource/" + Main.username + "Source.txt";
        File file = new File(fileName);
        System.out.println("SAVE: " + file.getAbsolutePath());
        SaveSource(fileName);

        System.exit(0);
    }

    /**
     * Save the user status source file.
     * <p> Save the models, data sets, scenarios and records to the corresponding user status file.
     *
     * @param fileName The user status file name.
     * @author Yixin SHEN
     * @date 24/01/2021-19:54
     */
    public void SaveSource(String fileName) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < treeItemCollection.getModelsList().size(); i++) {
                TreeItemObject model = treeItemCollection.getModelsList().get(i);
                out.write("Name: " + model.getItemName() + "\n");
                out.write("Value: " + model.getItemValue() + "\n");
                out.write("Favor: " + model.getItemFavorite() + "\n");
                out.write("Type: " + model.getItemType() + "\n\n");
            }

            for (int i = 0; i < treeItemCollection.getDataSetsList().size(); i++) {
                TreeItemObject data_set = treeItemCollection.getDataSetsList().get(i);
                out.write("Name: " + data_set.getItemName() + "\n");
                out.write("Value: " + data_set.getItemValue() + "\n");
                out.write("Favor: " + data_set.getItemFavorite() + "\n");
                out.write("Type: " + data_set.getItemType() + "\n\n");
            }

            for (int i = 0; i < treeItemCollection.getScenariosList().size(); i++) {
                TreeItemObject scenario = treeItemCollection.getScenariosList().get(i);
                out.write("Name: " + scenario.getItemName() + "\n");
                out.write("Value: " + scenario.getItemValue() + "\n");
                out.write("Favor: " + scenario.getItemFavorite() + "\n");
                out.write("Type: " + scenario.getItemType() + "\n\n");
            }

            for (int i = 0; i < treeItemCollection.getRecordsList().size(); i++) {
                TreeItemObject record = treeItemCollection.getRecordsList().get(i);
                out.write("Name: " + record.getItemName() + "\n");
                out.write("Value: " + record.getItemValue() + "\n");
                out.write("Favor: " + record.getItemFavorite() + "\n");
                out.write("Type: " + record.getItemType() + "\n\n");
            }

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * databaseOnClick
     * <p>
     * Button action.
     * Call the databaseDialog function in Main.
     *
     * @param actionEvent 1 Button onCLick event
     * @throws IOException the input output exception
     * @author Ruibin CHEN
     * @date 2021/2/11-0:39
     */
    public void databaseOnClick(ActionEvent actionEvent) throws IOException {
        ImportDataStage = new Stage();
        ImportDataStage.setTitle("File to database");
        Scene ImportDataScene = new Scene(Main.loadFXML("/FXML/FileToDatabase"));
        ImportDataStage.setScene(ImportDataScene);
        Main.setlogo(ImportDataStage);
        ImportDataStage.show();
    }

    /**
     * SignOut
     * <p> Sign out the account, set the scene to sign in page.
     *
     * @param actionEvent 1 Button onCLick event
     * @throws IOException Input output exception
     * @author Yixin SHEN
     * @date 24/02/2021-20:37
     */
    public void SignOut(ActionEvent actionEvent) throws IOException {
        ConfirmSound.getInstance().playMusic();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(treeView.getScene().getWindow());
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("SIGN OUT");
        alert.setContentText("Are you sure you want to sign out?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
            if (result.get() == ButtonType.OK) {
                String fileName = "TreeSource/" + Main.username + "Source.txt";
                File file = new File(fileName);
                System.out.println("SAVE: " + file.getAbsolutePath());
                SaveSource(fileName);

                Main.toSignIn();
            } else {
                System.out.println("sigh out cancel");
            }
    }

    /**
     * SaveRecords method is to save the records according to the chart and table value.
     * <p> Save the records according to the chart and table value. Prompt warning if the chart data is empty/record name is empty or duplicate.
     *
     * @param actionEvent Mouse Click Event
     * @author Yixin SHEN
     * @date 10/03/2021-15:43
     */
    public void SaveRecords(ActionEvent actionEvent) {
        // If the chart title is empty, prompt a warning dialog and return.
        if (DR_Chart.getData().size() == 0) {
            EmptyChartWarning();
            return;
        }

        TextInputDialog dialog = new TextInputDialog("My Chart");
        dialog.setTitle("Save the line chart");
        dialog.setHeaderText("LINE CHART SAVING!");
        dialog.setContentText("Please enter the chart name:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String setName = result.get();
            // Check if the record name is empty.
            if (setName.equals("")) {
                EmptyNameWarning();
                return;
            }

            // Check if the record name is duplicate.
            for (TreeItemObject record : treeItemCollection.getRecordsList()) {
                if (record.getItemName().equals(setName)) {
                    DuplicateWarning();
                    return;
                }
            }

            String chartValue = getRecordsValue().toString();

            // Add new history records
            TreeItemObject NewRecord = new TreeItemObject(4, false, setName, chartValue);
            treeItemCollection.getRecordsList().add(NewRecord);

            // Rebuild the tree
            buildTree();

            // Add a menu item to open recent submenu
            OpenRecent.setDisable(false);
            MenuItem menuItem = new MenuItem(setName);
            OpenRecent.getItems().add(menuItem);
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    List<TreeItemObject> RecordsList = treeItemCollection.getRecordsList();
                    for (TreeItemObject treeItemObject : RecordsList) {
                        if (treeItemObject.getItemName().equals(setName)) {
                            LoadRecord(treeItemObject);
                        }
                    }
                }
            });

            System.out.println(chartValue);
        } else {
            System.out.println("Save cancel!");
        }
    }


    /**
     * According to the line chart, return the line chart data value.
     * <p> According to the line chart, return the line chart data value in stringBuilder format.
     *
     * @return The line chart data value in stringBuilder format.
     * @author Yixin SHEN
     * @date 27/02/2021-16:18
     */
    private StringBuilder getRecordsValue() {
        StringBuilder RecordsValue = new StringBuilder();

        getChartValue(RecordsValue, DR_Chart);
        getChartValue(RecordsValue, LogLike_Chart);
        getChartValue(RecordsValue, ScenarioChart);

        ObservableList list = StatisticTable.getItems();
        for (int i = 0; i < StatisticTable.getItems().size(); i++) {
            ScenarioAnalysis rowData = (ScenarioAnalysis) list.get(i);

            RecordsValue.append(rowData.mNameProperty.getValue()).append("//");
            RecordsValue.append(rowData.mMinProperty.getValue()).append("//");
            RecordsValue.append(rowData.mMeanProperty.getValue()).append("//");
            RecordsValue.append(rowData.mSDProperty.getValue()).append("//");
            RecordsValue.append(rowData.mMaxProperty.getValue()).append("//");
        }

        return RecordsValue;
    }

    /**
     * Input a record value and corresponding line chart to append the stringBuilder.
     * <p> Input a record value and corresponding line chart to append the stringBuilder, including line chart title, XYAxis label name and data.
     *
     * @param RecordsValue The Record value.
     * @param lineChart    One of the line chart.
     * @author Yixin SHEN
     * @date 10/03/2021-17:04
     */
    private void getChartValue(StringBuilder RecordsValue, LineChart<String, Number> lineChart) {
        RecordsValue.append(lineChart.getTitle()).append("//");

        RecordsValue.append(lineChart.getXAxis().getLabel()).append("//");
        RecordsValue.append(lineChart.getYAxis().getLabel()).append("//");

        for (int i = 0; i < lineChart.getData().size(); i++) {
            XYChart.Series<String, Number> series = (XYChart.Series<String, Number>) lineChart.getData().get(i);
            RecordsValue.append(series.getName()).append("//");

            int DataSize = series.getData().size();

            for (int j = 0; j < DataSize; j++) {
                XYChart.Data<String, Number> seriesData = series.getData().get(j);
                if (j != DataSize - 1) {
                    RecordsValue.append(seriesData.getXValue()).append("--").append(seriesData.getYValue()).append(",");
                } else {
                    RecordsValue.append(seriesData.getXValue()).append("--").append(seriesData.getYValue()).append("//");
                }
            }
        }
    }

    /**
     * Generate empty chart warning dialog.
     * <p> if the chart data is empty, pop up a warning message.
     *
     * @author Yixin SHEN
     * @date 27/02/2021-15:43
     */
    private void EmptyChartWarning() {
        ErrorSound.getInstance().playMusic();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(treeView.getScene().getWindow());
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("There is no valid chart!");

        alert.showAndWait();
    }


    /**
     * Generate duplicate name warning dialog.
     * <p> if the record name is exist, pop up a warning message.
     *
     * @author Yixin SHEN
     * @date 27/02/2021-15:43
     */
    private void DuplicateWarning() {
        ErrorSound.getInstance().playMusic();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(treeView.getScene().getWindow());
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("Name cannot be duplicate for the same type!");

        alert.showAndWait();
    }

    /**
     * Generate empty name warning dialog.
     * <p> if the name is empty, pop up a warning message.
     *
     * @author Yixin SHEN
     * @date 27/02/2021-15:43
     */
    private void EmptyNameWarning() {
        ErrorSound.getInstance().playMusic();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(treeView.getScene().getWindow());
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("Record name cannot be empty!");

        alert.showAndWait();
    }

    /**
     * open User Inform page.
     * <p> open User Guide page.
     *
     * @throws IOException IOException
     * @author Yuan DAI
     * @date 02/03/2021-14:32
     */
    public void seeUserInform() throws IOException {
        Stage UserInformStage = new Stage();
        UserInformStage.setTitle("User Inform");
        Scene UserInformScene = new Scene(Main.loadFXML("/FXML/UserInform"));
        UserInformStage.setScene(UserInformScene);
        Main.setlogo(UserInformStage);
        UserInformStage.setResizable(false);
        UserInformStage.show();
    }

    /**
     * open User Guide page.
     * <p> open User Guide page.
     *
     * @throws IOException IOException
     * @author Yuan DAI
     * @date 02/03/2021-14:34
     */
    public void seeUserGuide() throws IOException {
        Stage UserGuideStage = new Stage();
        UserGuideStage.setTitle("User Guide");
        Scene UserGuideScene = new Scene(Main.loadFXML("/FXML/UserGuide"));
        UserGuideStage.setScene(UserGuideScene);
        Main.setlogo(UserGuideStage);
        UserGuideStage.setResizable(false);
        UserGuideStage.show();
    }

    /**
     * Generate invalid warning dialog.
     * <p> if the sql statement is invalid, pop up a warning message.
     *
     * @author Ruibin CHEN
     * @date 27/02/2021-15:43
     */
    public void InvalidSQLWarning() {
        ErrorSound.getInstance().playMusic();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(treeView.getScene().getWindow());
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Invalid sql statement!");
        alert.setContentText(sqlStatement);

        alert.showAndWait();
    }

    /**
     * Generate connect warning dialog.
     * <p> if the sql statement is invalid, pop up a warning message.
     *
     * @author Ruibin CHEN
     * @date 27/02/2021-15:43
     */
    public void ConnectWarning() {
        ErrorSound.getInstance().playMusic();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(treeView.getScene().getWindow());
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("Can't connect with the database!");

        alert.showAndWait();
    }


    /**
     * Show the line chart symbols in the charts.
     * <p> When Chart is not empty and the checkMenu item is false, add line chart symbols and tooltips to the chart.
     * Otherwise close the symbols and tooltips.
     *
     * @param actionEvent Mouse click event
     * @author Yixin SHEN
     * @date 05/03/2021-18:10
     */
    public void ShowSymbols(ActionEvent actionEvent) {
        if (ShowSymbolCheck.isSelected() && DR_Chart.getData().size() != 0) {
            DR_Chart.setCreateSymbols(true);
            LogLike_Chart.setCreateSymbols(true);
            ScenarioChart.setCreateSymbols(true);

            for (int i = 0; i < DR_Chart.getData().size(); i++) {
                XYChart.Series<String, Number> series = (XYChart.Series<String, Number>) DR_Chart.getData().get(i);

                // Add tooltips to the chart if setCreateSymbols set true
                addToolTip(series);
            }

            XYChart.Series<String, Number> LossSeries = (XYChart.Series<String, Number>) LogLike_Chart.getData().get(0);

            // Add tooltips to the chart if setCreateSymbols set true
            addToolTip(LossSeries);

            XYChart.Series<String, Number> seriesScenario = (XYChart.Series<String, Number>) ScenarioChart.getData().get(0);

            // Add tooltips to the chart if setCreateSymbols set true
            addToolTip(seriesScenario);

        } else if (ShowSymbolCheck.isSelected()) {
            DR_Chart.setCreateSymbols(true);
            LogLike_Chart.setCreateSymbols(true);
            ScenarioChart.setCreateSymbols(true);
        } else {
            DR_Chart.setCreateSymbols(false);
            LogLike_Chart.setCreateSymbols(false);
            ScenarioChart.setCreateSymbols(false);
        }
    }

    /**
     * addToolTip to the chart series
     * <p> For each point in the chart, add a tool tip the point to make the chart more representative.
     *
     * @param series The chart series
     * @author Yixin SHEN
     * @date 10/03/2021-17:26
     */
    private void addToolTip(XYChart.Series<String, Number> series) {
        series.getData().forEach(new Consumer<XYChart.Data<String, Number>>() {
            @Override
            public void accept(XYChart.Data<String, Number> stringNumberData) {
                Tooltip tooltip = new Tooltip(stringNumberData.getXValue() + "-" + stringNumberData.getYValue());

                Tooltip.install(stringNumberData.getNode(), tooltip);
            }
        });
    }

    /**
     * ToggleToolBar to control the tool bar is visible or not.
     * <p> ToggleToolBar to control the tool bar is visible or not.
     *
     * @author Yuxin SHI
     * @date 10/03/2021-21:07
     */
    public void ToggleToolBar() {
        if (!ShowToolBarCheck.isSelected()) {
            toolbar.setVisible(false);
            toolbar.setManaged(false);
            Separator1.setVisible(false);
            Separator1.setManaged(false);
        } else {
            Separator1.setVisible(true);
            Separator1.setManaged(true);
            toolbar.setManaged(true);
            toolbar.setVisible(true);
        }
    }

    /**
     * ToggleTree to control the TreeView is visible or not.
     * <p> ToggleTree to control the TreeView is visible or not.
     *
     * @author Yuxin SHI
     * @date 10/03/2021-21:07
     */
    public void ToggleTree() {
        if (!ShowTreeviewCheck.isSelected()) {
            treeView.setPrefWidth(0);
            treeView.setVisible(false);
        } else {
            treeView.setPrefWidth(220);
            treeView.setVisible(true);
        }
    }

    /**
     * Show the webpage of AboutUS
     *
     * @throws URISyntaxException URISyntaxException
     * @throws IOException        IOException
     * @author Yichen Zhang
     * @date 06/03/2021-19:59
     */
    public void seeWebPage() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("http://cslinux.nottingham.edu.cn/~Team202001/"));
    }

    /**
     * Show the about beaver stage.
     *
     * @throws IOException IOException
     * @author Yichen Zhang
     * @date 06/03/2021-20:02
     */
    public void aboutBeaver() throws IOException {
        Stage BeaverStage = new Stage();
        BeaverStage.setTitle("About beaver");
        Scene BeaverScene = new Scene(Main.loadFXML("/FXML/AboutBeaver"));
        BeaverStage.setScene(BeaverScene);
        Main.setlogo(BeaverStage);
        BeaverStage.setResizable(false);
        BeaverStage.show();
    }

    /**
     * Export the result as txt file.
     * <p> Export the result as txt file, user can choose where to save the file and set the file name by themselves.
     *
     * @param actionEvent Mouse Click Event
     * @throws FileNotFoundException FileNotFoundException
     * @author Yixin SHEN
     * @date 10/03/2021-20:34
     */
    public void ExportResultAsTXT(ActionEvent actionEvent) throws FileNotFoundException {
        File file = null;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT File(.png)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save As");
        file = fileChooser.showSaveDialog(Main.primaryStage);

        if (file != null) {
            StringBuilder RecordsValue = new StringBuilder();

            // Store the DR_Chart value
            RecordsValue.append(DR_Chart.getTitle()).append("\n");
            RecordsValue.append(DR_Chart.getXAxis().getLabel()).append("--");
            RecordsValue.append(DR_Chart.getYAxis().getLabel()).append("\n");

            for (int i = 0; i < DR_Chart.getData().size(); i++) {
                XYChart.Series<String, Number> series = (XYChart.Series<String, Number>) DR_Chart.getData().get(i);
                RecordsValue.append(series.getName()).append(":\n");

                int DataSize = series.getData().size();

                for (int j = 0; j < DataSize; j++) {
                    XYChart.Data<String, Number> seriesData = series.getData().get(j);
                    if (j != DataSize - 1) {
                        RecordsValue.append(seriesData.getXValue()).append("--").append(seriesData.getYValue()).append("\n");
                    } else {
                        RecordsValue.append(seriesData.getXValue()).append("--").append(seriesData.getYValue()).append("\n\n");
                    }
                }
            }

            // Store the LogLike_Chart value
            RecordsValue.append(LogLike_Chart.getTitle()).append("\n");
            RecordsValue.append(LogLike_Chart.getXAxis().getLabel()).append("--");
            RecordsValue.append(LogLike_Chart.getYAxis().getLabel()).append("\n");

            for (int i = 0; i < LogLike_Chart.getData().size(); i++) {
                XYChart.Series<String, Number> series = (XYChart.Series<String, Number>) LogLike_Chart.getData().get(i);
                RecordsValue.append(series.getName()).append(":\n");

                int DataSize = series.getData().size();

                for (int j = 0; j < DataSize; j++) {
                    XYChart.Data<String, Number> seriesData = series.getData().get(j);
                    if (j != DataSize - 1) {
                        RecordsValue.append(seriesData.getXValue()).append("--").append(seriesData.getYValue()).append("\n");
                    } else {
                        RecordsValue.append(seriesData.getXValue()).append("--").append(seriesData.getYValue()).append("\n\n");
                    }
                }
            }

            // Save result to TXT file
            saveTextToFile(RecordsValue.toString(), file);
        }
    }

    /**
     * Write the string to the target file.
     * <p> Write the string to the target file.
     *
     * @param content file content
     * @param file    target file
     * @throws FileNotFoundException FileNotFoundException
     * @author Yixin SHEN
     * @date 10/03/2021-21:00
     */
    private void saveTextToFile(String content, File file) throws FileNotFoundException {
        PrintWriter writer;
        writer = new PrintWriter(file);
        writer.println(content);
        writer.close();
    }

    /**
     * Open the merge stage.
     * <p> Open the merge stage.
     *
     * @param actionEvent Mouse Click Event
     * @throws IOException IOException
     * @author Yixin SHEN
     * @date 16/03/2021-21:04
     */
    public void Merge(ActionEvent actionEvent) throws IOException {
        MergeStage = new Stage();
        MergeStage.setTitle("Merge Result");
        Scene MergeScene = new Scene(Main.loadFXML("/FXML/Merge"));
        MergeStage.setScene(MergeScene);
        Main.setlogo(MergeStage);
        MergeStage.setResizable(false);
        MergeStage.show();
    }
}

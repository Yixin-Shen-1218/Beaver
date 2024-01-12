package Controllers;

import DataProcessing.ScenarioAnalysis;
import Main.Main;
import Music.ErrorSound;
import ToolPackage.JdbcUtil;
import TreeControl.TreeItemObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 * <p> The Class MergeController is the controller class of Merge.fxml.
 * Initialize Merge scene.
 * @author Yixin SHEN
 * @version 1.0
 * @date 16/03/2021-13:36
 * @since 1.0
 */
public class MergeController {
    /**
     * TabPane contain charts
     */
    public TabPane ChartsContainer;
    /**
     * Screen shot1
     */
    public AnchorPane Shot;
    /**
     * DR line chart
     */
    public LineChart DR_Chart;
    /**
     * Screen shot2
     */
    public AnchorPane Shot2;
    /**
     * LogLikelyHood chart
     */
    public LineChart LogLike_Chart;
    /**
     * Original Chart comboBox
     */
    public ComboBox<TreeItemObject> RecordsBox;
    /**
     * Comparison Container
     */
    public VBox ComparisonBox;
    /**
     * Add comparison button
     */
    public Button AddBtn;
    /**
     * Merge button
     */
    public Button MergeBtn;
    /**
     * Show symbol check box
     */
    public CheckBox ShowSymbolCheck;
    /**
     * Set the new series name
     */
    public TextField SeriesName;
    /**
     * Export DR line chart button
     */
    public Button ExportDRChart;
    /**
     * Export logLikeLiHood line chart button
     */
    public Button ExportLogChart;

    /**
     * Define the index of LogLike line
     */
    private int LogLikeNum = 1;

    /**
     * Initialize the merge scene.
     * <p> Build the records box in the scene.
     *
     * @author Yixin SHEN
     * @date 16/03/2021-14:24
     */
    public void initialize() {
        SetExportListener();
        buildRecordsBox();
    }

    /**
     * Build RecordsBox
     * <p> Build the Records box
     *
     * @author Yixin SHEN
     * @date 16/03/2021-20:19
     */
    private void buildRecordsBox() {
        RecordsBox.getItems().addAll(MainMenuController.treeItemCollection.getRecordsList());
        RecordsBox.setConverter(new StringConverter<TreeItemObject>() {
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

        RecordsBox.getSelectionModel().selectFirst();
    }


    /**
     * Add Comparison to the ComparisonBox.
     * <p> If there is no valid record, prompt a warning, else, add a new comparison to the comparison box.
     *
     * @param actionEvent Mouse Click Event
     * @author Yixin SHEN
     * @date 16/03/2021-20:25
     */
    public void AddComparison(ActionEvent actionEvent) {
        if (RecordsBox.getValue() == null) {
            EmptyWarning();
            return;
        }

        HBox hBox = new HBox();

        // Initialize records box
        ComboBox<TreeItemObject> recordsBox = new ComboBox<>();
        recordsBox.setPrefWidth(150);

        recordsBox.getItems().addAll(MainMenuController.treeItemCollection.getRecordsList());

        recordsBox.setConverter(new StringConverter<TreeItemObject>() {
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

        Insets insets = new Insets(8);
        HBox.setMargin(recordsBox, insets);


        TextField textField = new TextField();
        HBox.setMargin(textField, insets);

        ImageView TrashBin = new ImageView(new Image(getClass().getResourceAsStream("/Image/MainMenuButton/TrashBin.png")));
        Button button = new Button("", TrashBin);
        HBox.setMargin(button, insets);

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        recordsBox.getSelectionModel().selectFirst();

        hBox.getChildren().addAll(recordsBox, textField, region, button);

        ComparisonBox.getChildren().add(hBox);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ComparisonBox.getChildren().remove(hBox);
            }
        });
    }

    /**
     * Merge button event to merge the selected chart.
     * <p> Merge the selected charts to one single chart. If new series name is empty, prompt a warning.
     *
     * @param actionEvent Mouse Click Event
     * @author Yixin SHEN
     * @date 16/03/2021-20:26
     */
    public void Merge(ActionEvent actionEvent) {
        // If there is no valid record, prompt a warning dialog
        if (SeriesName.getText().equals("")) {
            EmptyNameWarning();
            return;
        }

        // Check all the series' names, the name should not be empty
        for (int i = 0; i < ComparisonBox.getChildren().size(); i++) {
            HBox hBox = (HBox) ComparisonBox.getChildren().get(i);
            TextField textField = (TextField) hBox.getChildren().get(1);
            if (textField.getText().equals("")) {
                EmptyNameWarning();
                return;
            }
        }

        LogLikeNum = 1;
        DR_Chart.getData().clear();
        LogLike_Chart.getData().clear();

        LoadOriginalRecord(RecordsBox.getValue());

        LoadComparisonRecord();

        // Set the line chart css
        final String liveScatterChartCss = getClass().getResource("/CSS/LineChart.css").toExternalForm();
        DR_Chart.getStylesheets().add(liveScatterChartCss);
        LogLike_Chart.getStylesheets().add(liveScatterChartCss);

    }

    /**
     * Load Comparison Record to the chart.
     * <p> Load Comparison Record to the chart.
     *
     * @author Yixin SHEN
     * @date 16/03/2021-20:28
     */
    private void LoadComparisonRecord() {
        for (int i = 0; i < ComparisonBox.getChildren().size(); i++) {
            HBox hBox = (HBox) ComparisonBox.getChildren().get(i);
            ComboBox recordsBox = (ComboBox) hBox.getChildren().get(0);
            TextField textField = (TextField) hBox.getChildren().get(1);

            covertValueToChart((TreeItemObject) recordsBox.getValue(), textField.getText());
        }
    }

    /**
     * Convert comparison value to chart series and add them to chart
     * <p>
     *
     * @param s          TreeItemObject in the comboBox
     * @param seriesName New series name set in the text field
     * @author Yixin SHEN
     * @date 16/03/2021-20:28
     */
    private void covertValueToChart(TreeItemObject s, String seriesName) {
        String[] recordValue = s.getItemValue().split("//");

        for (int j = 1; j < recordValue.length; j++) {
            System.out.println(recordValue[j]);
        }

        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

        series.setName(seriesName);

        String[] data = recordValue[6].split(",");
        for (String datum : data) {
            String[] XYValue = datum.split("--");
            BigDecimal bd = new BigDecimal(XYValue[1]);
            series.getData().add(new XYChart.Data<String, Number>(XYValue[0], bd));
            System.out.println(Double.parseDouble(bd.toPlainString()));
        }

        DR_Chart.getData().add(series);

        // Add tooltips to the chart if setCreateSymbols set true
        addToolTip(series);

        XYChart.Series<String, Number> seriesLoss = new XYChart.Series<String, Number>();

        seriesLoss.setName(recordValue[10] + LogLikeNum);
        LogLikeNum += 1;

        String[] LossData = recordValue[11].split(",");
        for (String datum : LossData) {
            String[] XYValue = datum.split("--");
            BigDecimal bd = new BigDecimal(XYValue[1]);
            seriesLoss.getData().add(new XYChart.Data<String, Number>(XYValue[0], bd));
            System.out.println(Double.parseDouble(bd.toPlainString()));
        }

        LogLike_Chart.getData().add(seriesLoss);
        addToolTip(seriesLoss);
    }

    /**
     * Load Original Record to the chart.
     * <p> Convert original record to the series and add to the line chart.
     *
     * @param s TreeItemObject in the original record comboBox
     * @author Yixin SHEN
     * @date   16/03/2021-20:30
     */
    private void LoadOriginalRecord(TreeItemObject s) {
        String[] recordValue = s.getItemValue().split("//");

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

        series2.setName(SeriesName.getText());

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

        seriesLoss.setName(recordValue[10] + LogLikeNum);
        LogLikeNum += 1;

        String[] LossData = recordValue[11].split(",");
        for (String datum : LossData) {
            String[] XYValue = datum.split("--");
            BigDecimal bd = new BigDecimal(XYValue[1]);
            seriesLoss.getData().add(new XYChart.Data<String, Number>(XYValue[0], bd));
            System.out.println(Double.parseDouble(bd.toPlainString()));
        }

        LogLike_Chart.getData().add(seriesLoss);
        addToolTip(seriesLoss);
    }

    /**
     * Add tool tip to the line chart series.
     * <p> Add tool tip to the line chart series.
     *
     * @param series Line chart series
     * @author Yixin SHEN
     * @date   16/03/2021-20:31
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
     * Control whether show the line chart symbols or not.
     * <p> Control whether show the line chart symbols or not.
     *
     * @param actionEvent Mouse Click Event
     * @author Yixin SHEN
     * @date   16/03/2021-20:31
     */
    public void ShowSymbols(ActionEvent actionEvent) {
        if (ShowSymbolCheck.isSelected() && DR_Chart.getData().size() != 0) {
            DR_Chart.setCreateSymbols(true);
            LogLike_Chart.setCreateSymbols(true);

            for (int i = 0; i < DR_Chart.getData().size(); i++) {
                XYChart.Series<String, Number> series = (XYChart.Series<String, Number>) DR_Chart.getData().get(i);

                // Add tooltips to the chart if setCreateSymbols set true
                addToolTip(series);
            }

            for (int i = 0; i < LogLike_Chart.getData().size(); i++) {
                XYChart.Series<String, Number> series = (XYChart.Series<String, Number>) LogLike_Chart.getData().get(i);

                // Add tooltips to the chart if setCreateSymbols set true
                addToolTip(series);
            }
        } else if (ShowSymbolCheck.isSelected()) {
            DR_Chart.setCreateSymbols(true);
            LogLike_Chart.setCreateSymbols(true);
        } else {
            DR_Chart.setCreateSymbols(false);
            LogLike_Chart.setCreateSymbols(false);
        }
    }


    /**
     * Export the chart to local
     * Export DR chart to local in PNG format if user click "export" in menu
     *
     * @param actionEvent click action
     * @author Yuxin SHI
     * @author Yixn SHEN
     * @date 05/02/2021-20:03
     */
    public void ExportDRChart(ActionEvent actionEvent) {
        File file = null;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG File(.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save As");
        file = fileChooser.showSaveDialog(MainMenuController.MergeStage);

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
     * @author Yixn SHEN
     * @date 05/02/2021-20:03
     */
    public void ExportLogChart(ActionEvent actionEvent) {
        File file = null;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG File(.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save As");
        file = fileChooser.showSaveDialog(MainMenuController.MergeStage);

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
     * Set a listener for menu item "export".
     * If clicked "model fit", the chart will be loaded and the menu item will be set as false, otherwise true.
     *
     * @author Yuxin SHI
     * @date 05/02/2021-20:03
     */
    public void SetExportListener() {
        DR_Chart.titleProperty().addListener((observableValue, s, t1) -> {
            if (DR_Chart.getTitle() != null) {
                ExportDRChart.setDisable(false);
                ExportLogChart.setDisable(false);
            }
        });
    }

    /**
     * Empty Records Warning
     * <p> Prompt to remind that there is no valid record.
     *
     * @author Yixin SHEN
     * @date   16/03/2021-20:32
     */
    private void EmptyWarning() {
        ErrorSound.getInstance().playMusic();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(RecordsBox.getScene().getWindow());
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("There is no valid record!");

        alert.showAndWait();
    }

    /**
     * Empty Name Warning
     * <p> Prompt to remind that the new series name is empty, which is illegal.
     *
     * @author Yixin SHEN
     * @date   16/03/2021-20:32
     */
    private void EmptyNameWarning() {
        ErrorSound.getInstance().playMusic();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(RecordsBox.getScene().getWindow());
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("The series name can not be empty!");

        alert.showAndWait();
    }
}

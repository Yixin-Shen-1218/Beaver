package Charts;

import DataProcessing.ScenarioAnalysis;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * The class LineChartUtils is used to generate Default Rate (DR) graph
 *
 * @author Donglin Jiang
 * @version 1.0
 * @date 25/02/2021-22:30
 * @since 1.0
 */
public class LineChartUtil extends Task {
    /**
     * The Line Chart filed
     */
    private final LineChart<String, Number> DR_Chart;
    /**
     * The LogLike Chart field
     */
    private final LineChart<String, Number> LogLike_Chart;
    /**
     * The Scenario Chart filed
     */
    private final LineChart<String, Number> ScenarioChart;
    /**
     * Observed DR data
     */
    XYChart.Series<String, Number> series1;
    /**
     * Model calculated DR data
     */
    XYChart.Series<String, Number> series2;
    /**
     * Log-Likelihood Value
     */
    XYChart.Series<String, Number> seriesLoss;
    /**
     * Scenario Value
     */
    XYChart.Series<String, Number> seriesScenario;
    /**
     * Observable scenario analysis value list
     */
    ObservableList<ScenarioAnalysis> list = FXCollections.observableArrayList();
    /**
     * User chosen scenario value
     */
    private String scenario;
    /**
     * User chosen model name
     */
    private String model;
    /**
     * User chosen dataSet name
     */
    private String dataSet;
    /**
     * TableView field that display the analysis of scenario
     */
    private TableView StatisticTable;

    /**
     * LineChartUtil
     * <p>
     * Constructor that initialize the the Line Chart Unit
     *
     * @param DR_Chart       UI DR chart
     * @param LogLike_Chart  UI Log Like chart
     * @param ScenarioChart  UI Scenario chart
     * @param StatisticTable UI Statistic table
     * @param dataSet        selected SQL statement/path
     * @param scenario       selected scenario
     * @param model          selected model path
     * @author Donglin Jiang
     * @date 25/02/2021-22:30
     */
    public LineChartUtil(LineChart<String, Number> DR_Chart, LineChart<String, Number> LogLike_Chart, LineChart<String, Number> ScenarioChart, TableView StatisticTable, String dataSet, String scenario, String model) {
        this.DR_Chart = DR_Chart;
        this.LogLike_Chart = LogLike_Chart;
        this.scenario = scenario;
        this.ScenarioChart = ScenarioChart;
        this.StatisticTable = StatisticTable;
        this.dataSet = dataSet;
        this.model = model;

        DR_Chart.getData().clear();
        LogLike_Chart.getData().clear();
        ScenarioChart.getData().clear();
        StatisticTable.getItems().clear();
    }

    /**
     * Call process.py script to calculate the Default Rate (DR) and Log-Likelihood
     *
     * @author Donglin Jiang
     * @date 25/02/2021-22:30
     */
    public void OperateLineChart() {
        updateProgress(0, 100);
        // Default Rate Graph
        final CategoryAxis xAxis = (CategoryAxis) DR_Chart.getXAxis();
        xAxis.setLabel("Month");

        final NumberAxis yAxis = (NumberAxis) DR_Chart.getYAxis();
        yAxis.setLabel("Default Rate(%)");

        DR_Chart.setTitle("Default Rate Graph");

        series1 = new XYChart.Series<String, Number>();
        series1.setName("Observed DR");

        series2 = new XYChart.Series<String, Number>();
        series2.setName("Model DR");

        // Log-Likelihood Graph
        final CategoryAxis xLossAxis = (CategoryAxis) LogLike_Chart.getXAxis();
        xLossAxis.setLabel("Month");

        final NumberAxis yLossAxis = (NumberAxis) LogLike_Chart.getYAxis();
        yLossAxis.setLabel("Log-Likelihood");

        LogLike_Chart.setTitle("Log-Likelihood Graph");

        seriesLoss = new XYChart.Series<String, Number>();
        seriesLoss.setName("Log-Likelihood Value");

        // Scenario Graph
        final CategoryAxis xScenarioAxis = (CategoryAxis) ScenarioChart.getXAxis();
        xScenarioAxis.setLabel("Month");

        final NumberAxis yScenarioAxis = (NumberAxis) ScenarioChart.getYAxis();
        yScenarioAxis.setLabel("Scenario Value");

        ScenarioChart.setTitle("Scenario Value Graph");

        seriesScenario = new XYChart.Series<String, Number>();
        seriesScenario.setName("Scenario Value");

        final String liveScatterChartCss = getClass().getResource("/CSS/LineChart.css").toExternalForm();
        DR_Chart.getStylesheets().add(liveScatterChartCss);
        LogLike_Chart.getStylesheets().add(liveScatterChartCss);
        ScenarioChart.getStylesheets().add(liveScatterChartCss);

    }

    /**
     * CalculateLineChart
     * <p>
     * Call process.py script to calculate the Default Rate (DR) and Log-Likelihood
     *
     * @author Ruibin CHEN
     * @date 2021/3/10-19:28
     */
    public void CalculateLineChart() {
        Process proc;
        try {
            updateProgress(10, 100);
            Thread.sleep(1000);
            String[] args = new String[]{"python", model, dataSet, scenario};
//            String[] args = new String[]{"python", "src/main/resources/Models/DTSM_model.py", dataSet, scenario};
//            String[] args = new String[]{"python3", "src/main/resources/Models/process.py", dataSet, scenario};
            proc = Runtime.getRuntime().exec(args);
            updateProgress(20, 100);
            Thread.sleep(2000);


            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            ArrayList predictArray = new ArrayList<Double>();
            ArrayList trueArray = new ArrayList<Double>();
            updateProgress(50, 100);
            Thread.sleep(2000);


            String line = in.readLine();
            int totalCalendar = Integer.parseInt(line);
            updateProgress(70, 100);
            Thread.sleep(1000);


            String line2 = in.readLine();
            String line2Use = line2.substring(1, line2.length() - 1);
            for (int i = 0; i < totalCalendar; i++) {
                double predictValue = Double.parseDouble(line2Use.replace(" ", "").split(",")[i]);
                predictArray.add(predictValue);
            }

            String line3 = in.readLine();
            String line3Use = line3.substring(1, line3.length() - 1);
            for (int i = 0; i < totalCalendar; i++) {
                double trueValue = Double.parseDouble(line3Use.replace(" ", "").split(",")[i]);
                trueArray.add(trueValue);
            }
            System.out.println(predictArray);
            System.out.println(trueArray);
            updateProgress(80, 100);
            Thread.sleep(1000);


            String line4 = in.readLine();
            String line4Use = line4.substring(1, line4.length() - 1);
            String line5 = in.readLine();
            String line5Use = line5.substring(1, line5.length() - 1);
            System.out.println(line5);
            String line6 = in.readLine();
            String line6Use = line6.substring(1, line6.length() - 1);
            String line7 = in.readLine();
            String line7Use = line7.substring(1, line7.length() - 1);

            for (int i = 0; i < totalCalendar; i++) {
                String calendar = line4Use.replace(" ", "").split(",")[i];
                String loss = line5Use.replace(" ", "").split(",")[i];
                String calendarScenario = line6Use.replace(" ", "").split(",")[i];
                String valueScenario = line7Use.replace(" ", "").split(",")[i];
                seriesLoss.getData().add(new XYChart.Data<String, Number>(calendar, Double.valueOf(loss)));
                series1.getData().add(new XYChart.Data<String, Number>(calendar, (Number) trueArray.get(i)));
                series2.getData().add(new XYChart.Data<String, Number>(calendar, (Number) predictArray.get(i)));
                seriesScenario.getData().add(new XYChart.Data<String, Number>(calendarScenario, Double.valueOf(valueScenario)));
            }

            String min = in.readLine();
            System.out.println(min);
            String max = in.readLine();
            System.out.println(max);
            String mean = in.readLine();
            System.out.println(mean);
            String sd = in.readLine();
            System.out.println(sd);


            list.add(new ScenarioAnalysis("x", min, mean, sd, max));

            updateProgress(100, 100);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    DrawChart();
                }
            });

            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * DrawChart
     * <p> Function that visualize the DR, LogLike, Scenario, Statistic areas.
     *
     * @author Donglin Jiang
     * @author Ruibin CHEN
     * @author Yixin SHEN
     * @date 2021/3/10-19:30
     */
    public void DrawChart() {
        DR_Chart.getData().clear();
        LogLike_Chart.getData().clear();
        ScenarioChart.getData().clear();
        StatisticTable.getItems().clear();

        StatisticTable.setItems(list);


        DR_Chart.getData().addAll(series1, series2);

        // Add tooltips to the chart if setCreateSymbols set true
        series1.getData().forEach(new Consumer<XYChart.Data<String, Number>>() {
            @Override
            public void accept(XYChart.Data<String, Number> stringNumberData) {
                Tooltip tooltip = new Tooltip(stringNumberData.getXValue() + "-" + stringNumberData.getYValue());

                Tooltip.install(stringNumberData.getNode(), tooltip);
            }
        });

        series2.getData().forEach(new Consumer<XYChart.Data<String, Number>>() {
            @Override
            public void accept(XYChart.Data<String, Number> stringNumberData) {
                Tooltip tooltip = new Tooltip(stringNumberData.getXValue() + "-" + stringNumberData.getYValue());

                Tooltip.install(stringNumberData.getNode(), tooltip);
            }
        });

        LogLike_Chart.getData().addAll(seriesLoss);

        seriesLoss.getData().forEach(new Consumer<XYChart.Data<String, Number>>() {
            @Override
            public void accept(XYChart.Data<String, Number> stringNumberData) {
                Tooltip tooltip = new Tooltip(stringNumberData.getXValue() + "-" + stringNumberData.getYValue());

                Tooltip.install(stringNumberData.getNode(), tooltip);
            }
        });

        ScenarioChart.getData().addAll(seriesScenario);

        seriesScenario.getData().forEach(new Consumer<XYChart.Data<String, Number>>() {
            @Override
            public void accept(XYChart.Data<String, Number> stringNumberData) {
                Tooltip tooltip = new Tooltip(stringNumberData.getXValue() + "-" + stringNumberData.getYValue());

                Tooltip.install(stringNumberData.getNode(), tooltip);
            }
        });
    }

    /**
     * call
     * <p>
     * Function used to execute the task.
     *
     * @author Donglin Jiang
     * @date 2021/3/10-19:32
     */
    @Override
    protected Void call() {
        CalculateLineChart();
        return null;
    }
}
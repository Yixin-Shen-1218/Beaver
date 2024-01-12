package DataProcessing;

import javafx.beans.property.SimpleStringProperty;

/**
 * <p>
 * this class is a observed data property class to display in the table view
 * @author   Ruibin CHEN
 * @version  1.0
 * @since    1.0
 * @date     2021/3/10-16:54
 */
public class ScenarioAnalysis {
    /**
     * name property
     */
    public SimpleStringProperty mNameProperty = new SimpleStringProperty();
    /**
     * min property
     */
    public SimpleStringProperty mMinProperty = new SimpleStringProperty();
    /**
     * mean property
     */
    public SimpleStringProperty mMeanProperty = new SimpleStringProperty();
    /**
     * standard property
     */
    public SimpleStringProperty mSDProperty = new SimpleStringProperty();
    /**
     * max property
     */
    public SimpleStringProperty mMaxProperty = new SimpleStringProperty();

    /**
     * ScenarioAnalysis
     * <p>
     * Construct that initialize the properties
     * @param name name value
     * @param min min value
     * @param mean mean value
     * @param sd standard value
     * @param max max value
     * @author Ruibin CHEN
     * @date   2021/3/14-20:34
     */
    public ScenarioAnalysis(String name, String min, String mean, String sd, String max) {
        mNameProperty.set(name);
        mMinProperty.set(min);
        mMeanProperty.set(mean);
        mSDProperty.set(sd);
        mMaxProperty.set(max);
    }
    /**
     * setMin
     * <p>
     * min value setter
     * @param min min value
     * @author Ruibin CHEN
     * @date   2021/3/14-20:35
     */
    public void setMin(String min) {
        this.mMinProperty.set(min);
    }
    /**
     * setName
     * <p>
     * name value setter
     * @param name name value
     * @author Ruibin CHEN
     * @date   2021/3/14-20:35
     */
    public void setName(String name) {
        this.mNameProperty.set(name);
    }
    /**
     * setMax
     * <p>
     * max value setter
     * @param max max value
     * @author Ruibin CHEN
     * @date   2021/3/14-20:36
     */
    public void setMax(String max) {
        this.mMaxProperty.set(max);
    }
    /**
     * setSD
     * <p>
     * standard value setter
     * @param sd standard value
     * @author Ruibin CHEN
     * @date   2021/3/14-20:36
     */
    public void setSD(String sd) {
        this.mSDProperty.set(sd);
    }
    /**
     * setMean
     * <p>
     * mean value setter
     * @param mean mean value
     * @author Ruibin CHEN
     * @date   2021/3/14-20:37
     */
    public void setMean(String mean) {
        this.mMeanProperty.set(mean);
    }
    /**
     * getMax
     * <p>
     * max value getter
     * @return max value
     * @author Ruibin CHEN
     * @date   2021/3/14-20:37
     */
    public String getMax() {
        return mMaxProperty.get();
    }
    /**
     * getName
     * <p>
     * name value getter
     * @return name value
     * @author Ruibin CHEN
     * @date   2021/3/14-20:38
     */
    public String getName() {
        return mNameProperty.get();
    }
    /**
     * getMean
     * <p>
     * mean value getter
     * @return mean value
     * @author Ruibin CHEN
     * @date   2021/3/14-20:38
     */
    public String getMean() {
        return mMeanProperty.get();
    }
    /**
     * getMin
     * <p>
     * min value getter
     * @return min value
     * @author Ruibin CHEN
     * @date   2021/3/14-20:39
     */
    public String getMin() {
        return mMinProperty.get();
    }
    /**
     * getSd
     * <p>
     * standard value getter
     * @return standard value
     * @author Ruibin CHEN
     * @date   2021/3/14-20:39
     */
    public String getSd() {
        return mSDProperty.get();
    }
}

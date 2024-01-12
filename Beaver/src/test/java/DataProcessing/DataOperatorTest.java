package DataProcessing;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <p> The class DataOperatorTest is to test DataOperator class.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 07/03/2021-16:28
 * @since 1.0
 */
public class DataOperatorTest {
    /**
     * Define a new dataOperator for testing below
     */
    private final DataOperator dataOperator = new DataOperator();

    /**
     * setFileName Test.
     * <p> Test the setFileName method.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void setFileName() {
        dataOperator.setFileName("New File");
        assertEquals("New File", dataOperator.getFileName());
    }

    /**
     * getFileName Test.
     * <p> Test the getFileName method.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void getFileName() {
        dataOperator.setFileName("Import");
        String name = dataOperator.getFileName();
        assertEquals("Import", name);
    }
}
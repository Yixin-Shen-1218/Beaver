package TreeControl;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * <p> The class TreeItemColletionTest is to test TreeItemColletion class.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 07/03/2021-16:28
 * @since 1.0
 */
public class TreeItemCollectionTest {
    /**
     * Instantiate a new TreeItemCollection for testing below
     */
    TreeItemCollection treeItemCollection = new TreeItemCollection();

    /**
     * TreeItemCollectionTest
     * <p> Throws the IOException.
     *
     * @throws IOException IOException
     * @author Yixin SHEN
     * @date   16/03/2021-21:12
     */
    public TreeItemCollectionTest() throws IOException {
    }

    /**
     * getModelsList Test.
     * <p> Test the modelList size in default file.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void getModelsList() {
        assertEquals(2, treeItemCollection.getModelsList().size());
    }

    /**
     * getDataSetsList Test.
     * <p> Test the DataSetsList size in default file.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void getDataSetsList() {
        assertEquals(2, treeItemCollection.getDataSetsList().size());
    }

    /**
     * getScenariosList Test.
     * <p> Test the ScenariosList size in default file.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void getScenariosList() {
        assertEquals(3, treeItemCollection.getScenariosList().size());
    }

    /**
     * getRecordsList Test.
     * <p> Test the RecordsList size in default file.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void getRecordsList() {
        assertEquals(3, treeItemCollection.getRecordsList().size());
    }
}
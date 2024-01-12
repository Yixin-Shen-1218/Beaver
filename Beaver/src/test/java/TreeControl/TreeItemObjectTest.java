package TreeControl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <p> The class TreeItemObjectTest is to test TreeItemObjectTest class.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 07/03/2021-16:28
 * @since 1.0
 */
public class TreeItemObjectTest {
    /**
     * Instantiate a new treeItemObject for testing below
     */
    TreeItemObject treeItemObject = new TreeItemObject(1, false, "ABC", "DEF");

    /**
     * getItemType Test.
     * <p> Test the TreeItem type returned.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void getItemType() {
        int i = treeItemObject.getItemType();
        assertEquals(1, i);
    }

    /**
     * setItemType Test.
     * <p> Set the TreeItem type to 2, then test the TreeItem type.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void setItemType() {
        treeItemObject.setItemType(2);
        assertEquals(2, treeItemObject.getItemType());
    }

    /**
     * getItemFavorite Test.
     * <p> Test the TreeItem favor returned.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void getItemFavorite() {
        boolean b = treeItemObject.getItemFavorite();
        assertFalse(b);
    }

    /**
     * setItemFavorite Test.
     * <p> Set the TreeItem favor to true, then test the TreeItem favor.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void setItemFavorite() {
        treeItemObject.setItemFavorite(true);
        assertTrue(treeItemObject.getItemFavorite());
    }

    /**
     * getItemName Test.
     * <p> Test the TreeItem name returned.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void getItemName() {
        String name = treeItemObject.getItemName();
        assertEquals("ABC", name);
    }

    /**
     * setItemName Test.
     * <p> Set the TreeItem name to "AAA", then test the TreeItem name.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void setItemName() {
        treeItemObject.setItemName("AAA");
        assertEquals("AAA", treeItemObject.getItemName());
    }

    /**
     * getItemValue Test.
     * <p> Test the TreeItem value returned.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void getItemValue() {
        String value = treeItemObject.getItemValue();
        assertEquals("DEF", value);
    }

    /**
     * setItemValue Test.
     * <p> Set the TreeItem value to "QWE", then test the TreeItem value.
     *
     * @author Yixin SHEN
     * @date 07/03/2021-16:28
     */
    @Test
    public void setItemValue() {
        treeItemObject.setItemValue("QWE");
        assertEquals("QWE", treeItemObject.getItemValue());
    }
}
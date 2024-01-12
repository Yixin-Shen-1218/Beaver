package TreeControl;

/**
 * <p> Tree Item Object class to define the tree item properties.
 * Tree Item Object class to define the tree item properties.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 13/01/2021-19:06
 * @since 1.0
 */
public class TreeItemObject {
    /**
     * item type
     */
    private int ItemType;
    /**
     * whether item is favorite
     */
    private Boolean ItemFavorite;
    /**
     * item name
     */
    private String ItemName;
    /**
     * item value
     */
    private String ItemValue;

    /**
     * TreeItemObject
     * <p>
     * Constructor that initialize the tree item
     *
     * @param type  item type
     * @param favor whether favorite
     * @param name  item name
     * @param value item value
     * @author Ruibin CHEN
     * @date 2021/3/14-20:51
     */
    public TreeItemObject(int type, Boolean favor, String name, String value) {
        /* type 1 = models
           type 2 = datasets
           type 3 = scenarios
           type 4 = records
        */
        ItemType = type;
        ItemFavorite = favor;
        ItemName = name;

        /*
            type1,3 value = file path
            type2 = SQL statement
            type4 = records value
         */
        ItemValue = value;
    }

    /**
     * getItemType
     * <p>
     * item type getter
     *
     * @return item type
     * @author Ruibin CHEN
     * @date 2021/3/14-20:52
     */
    public int getItemType() {
        return ItemType;
    }

    /**
     * setItemType
     * <p>
     * item type setter
     *
     * @param itemType item type
     * @author Ruibin CHEN
     * @date 2021/3/14-20:53
     */
    public void setItemType(int itemType) {
        ItemType = itemType;
    }

    /**
     * getItemFavorite
     * <p>
     * item favorite getter
     *
     * @return item favorite
     * @author Ruibin CHEN
     * @date 2021/3/14-20:53
     */
    public Boolean getItemFavorite() {
        return ItemFavorite;
    }

    /**
     * setItemFavorite
     * <p>
     * item favorite setter
     *
     * @param itemFavorite item favorite
     * @author Ruibin CHEN
     * @date 2021/3/14-20:54
     */
    public void setItemFavorite(Boolean itemFavorite) {
        ItemFavorite = itemFavorite;
    }

    /**
     * getItemName
     * <p>
     * item name getter
     *
     * @return item name
     * @author Ruibin CHEN
     * @date 2021/3/14-20:54
     */
    public String getItemName() {
        return ItemName;
    }

    /**
     * setItemName
     * <p>
     * item name setter
     *
     * @param itemName item name
     * @author Ruibin CHEN
     * @date 2021/3/14-20:54
     */
    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    /**
     * getItemValue
     * <p>
     * item value getter
     *
     * @return item value
     * @author Ruibin CHEN
     * @date 2021/3/14-20:55
     */
    public String getItemValue() {
        return ItemValue;
    }

    /**
     * setItemValue
     * <p>
     * item value setter
     *
     * @param itemValue item value
     * @author Ruibin CHEN
     * @date 2021/3/14-20:56
     */
    public void setItemValue(String itemValue) {
        ItemValue = itemValue;
    }
}

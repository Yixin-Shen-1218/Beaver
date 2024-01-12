package InputField;

/**
 * <p> Define a new class called input field.
 * Define a new class called input field to check the input content in the sign in/sign up/reset password.
 *
 * @author Yuan DAI
 * @version 1.0
 * @date 18/01/2021-17:32
 * @since 1.0
 */
public class InputField {

    /**
     * Relative regex for validation
     */
    private String regex;
    /**
     * Additional regex for validation
     */
    private String regex2 = "";
    /**
     * Input type
     */
    private String inputType;
    /**
     * Indicating if the input is invalid
     */
    private Boolean isInvalid;
    /**
     * Indicationg if the error css is added
     */
    private Boolean isAdded;


    /**
     * Constructor for input field
     *
     * @param regex     regex
     * @param inputType inputType
     * @param isInvalid isInvalid
     * @param isAdded   isAdded
     * @author Yuan DAI
     * @date 18/01/2021-17:44
     */
    public InputField(String regex, String inputType, Boolean isInvalid, Boolean isAdded) {
        this.regex = regex;
        this.inputType = inputType;
        this.isInvalid = isInvalid;
        this.isAdded = isAdded;
    }

    /**
     * Constructor for input field
     *
     * @param regex     regex
     * @param regex2    regex2
     * @param inputType inputType
     * @param isInvalid isInvalid
     * @param isAdded   isAdded
     * @author Yuan DAI
     * @date 20/01/2021-20:08
     */
    public InputField(String regex, String regex2, String inputType, Boolean isInvalid, Boolean isAdded) {
        this.regex = regex;
        this.regex2 = regex2;
        this.inputType = inputType;
        this.isInvalid = isInvalid;
        this.isAdded = isAdded;
    }

    /**
     * Overload of the constructor
     *
     * @param inputType inputType
     * @param isInvalid isInvalid
     * @param isAdded   isAdded
     * @author Yuan DAI
     * @date 18/01/2021-17:44
     */
    public InputField(String inputType, Boolean isInvalid, Boolean isAdded) {
        this.inputType = inputType;
        this.isInvalid = isInvalid;
        this.isAdded = isAdded;
    }

    /**
     * get the regex
     *
     * @return regex
     * @author Yuan DAI
     * @date 18/01/2021-17:44
     */
    public String getRegex() {
        return regex;
    }

    /**
     * get the regex2
     *
     * @return regex2
     * @author Yuan DAI
     * @date 20/01/2021-20:09
     */
    public String getRegex2() {
        return regex2;
    }

    /**
     * get the input type
     *
     * @return inputType
     * @author Yuan DAI
     * @date 18/01/2021-17:44
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * get the isInvalid
     *
     * @return isInvalid
     * @author Yuan DAI
     * @date 18/01/2021-17:44
     */
    public Boolean getIsInvalid() {
        return isInvalid;
    }

    /**
     * set the isInvalid
     *
     * @param isInvalid isInvalid
     * @author Yuan DAI
     * @date 18/01/2021-17:44
     */
    public void setIsInvalid(Boolean isInvalid) {
        this.isInvalid = isInvalid;
    }

    /**
     * set the isAdded
     *
     * @param isAdded isAdded
     * @author Yuan DAI
     * @date 24/01/2021-22:34
     */
    public void setIsAdded(Boolean isAdded) {
        this.isAdded = isAdded;
    }

    /**
     * get the isAdded
     *
     * @return isAdded
     * @author Yuan DAI
     * @date 24/01/2021-22:36
     */
    public Boolean getIsAdded() {
        return isAdded;
    }

}

package InputField;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * <p> The class InputFieldTest is to test InputField class.
 *
 * @author Yuan DAI
 * @version 1.0
 * @date 09/03/2021-15:40
 * @since 1.0
 */
public class InputFieldTest {
    /**
     * New InputField object including all the parameters.
     */
    InputField inputField = new InputField("^[a-zA-Z]{2,15}$", "^\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+$", "Username or Email address", true, false);

    /**
     * Test the getRegex method.
     *
     * @author Yuan DAI
     * @date 09/03/2021-15:42
     */
    @Test
    public void testGetRegex() {
        assertEquals("^[a-zA-Z]{2,15}$", inputField.getRegex());
    }
    /**
     * Test the getRegex2 method.
     *
     * @author Yuan DAI
     * @date 09/03/2021-15:48
     */
    @Test
    public void testGetRegex2() {
        assertEquals("^\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+$", inputField.getRegex2());
    }
    /**
     * Test the getInputType method.
     *
     * @author Yuan DAI
     * @date 09/03/2021-15:50
     */
    @Test
    public void testGetInputType() {
        assertEquals("Username or Email address", inputField.getInputType());
    }
    /**
     * Test the getIsInvalid method.
     *
     * @author Yuan DAI
     * @date 09/03/2021-15:56
     */
    @Test
    public void testGetIsInvalid() {
        assertEquals(true, inputField.getIsInvalid());
    }
    /**
     * Test the getIsAdded method.
     *
     * @author Yuan DAI
     * @date 09/03/2021-15:59
     */
    @Test
    public void testGetIsAdded() {
        assertEquals(false, inputField.getIsAdded());
    }
    /**
     * Test the setIsAdded method.
     *
     * @author Yuan DAI
     * @date 09/03/2021-16:06
     */
    @Test
    public void testSetIsAdded() {
        inputField.setIsAdded(true);
        assertEquals(true, inputField.getIsAdded());
    }
    /**
     * Test the setIsInvalid method.
     *
     * @author Yuan DAI
     * @date 09/03/2021-16:11
     */
    @Test
    public void testSetIsInvalid() {
        inputField.setIsInvalid(false);
        assertEquals(false, inputField.getIsInvalid());
    }
}

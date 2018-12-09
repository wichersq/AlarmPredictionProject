import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Method;

public class UserInputFrameTest {
    @Test
    public void testUserInputTest() {
        UserInputFrame userInput = new UserInputFrame(500);

        try {
            Method privateMethod = userInput.getClass().getDeclaredMethod("createDateTime", String.class, String.class);
            privateMethod.setAccessible(true);
            privateMethod.invoke(userInput, "__/__/____", "__:__");
            thrown.expect(NumberFormatException.class);
            thrown.expectMessage("Invalid input");
        } catch (ReflectiveOperationException x) {
        }

    }
    @Rule
    public ExpectedException thrown= ExpectedException.none();
}



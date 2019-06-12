package com.bahrcode.ship.mainshipview;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void check_usernameIsEmail_accurately_returns_bool() {
        Login usernameTests = new Login();
        assertEquals(true, usernameTests.usernameIsEmail("user@gmail.com"));
        assertEquals(false, usernameTests.usernameIsEmail("notAnEmail"));
        assertEquals(false, usernameTests.usernameIsEmail(""));
        assertEquals(false, usernameTests.usernameIsEmail("blah@nana"));
        assertEquals(false, usernameTests.userNameIsEmail(".+=))("));
    }

}
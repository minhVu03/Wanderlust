package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//tests for Profile class
class ProfileTest {
    private Profile minh;

    @BeforeEach
    void setUp() {
        minh = new Profile();
    }

    @Test
    void testSetUserName(){
        minh.setUserName("Minh");
        assertEquals("Minh", minh.getUserName());
    }

    @Test
    void testSetHomeCity(){
        minh.setHomeCity("Ottawa");
        assertEquals("Ottawa", minh.getHomeCity());
    }

}
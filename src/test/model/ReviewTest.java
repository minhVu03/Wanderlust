package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Tests for Review class
public class ReviewTest {
    private Review hanoi1;
    private List<String> testTagsList = new ArrayList<>();
    private List<String> testRecsList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        hanoi1 = new Review("Minh","Hanoi", 8, "antique city, very cool!");
        testTagsList.add("tag1");
        testTagsList.add("tag2");
        testRecsList.add("rec1");
        testRecsList.add("rec2");
    }

    @Test
    void testConstructor() {
        assertEquals("Hanoi", hanoi1.getCityName());
        assertEquals(8, hanoi1.getScore());
        assertEquals("antique city, very cool!", hanoi1.getComment());
    }

    @Test
    void testSetTagList() {
        hanoi1.setTagList(testTagsList);
        assertEquals(2, hanoi1.getTags().size());
        assertTrue(hanoi1.getTags().contains("tag1"));
    }

    @Test
    void testSetRecList() {
        hanoi1.setRecList(testRecsList);
        assertEquals(2, hanoi1.getRecs().size());
        assertTrue(hanoi1.getRecs().contains("rec1"));
    }

    @Test
    void testAddTag(){
        hanoi1.addTag("food");
        hanoi1.addTag("antique");
        assertEquals(2, hanoi1.getTags().size());
        assertEquals("food", hanoi1.getTags().get(0));
        assertEquals("antique", hanoi1.getTags().get(1));
    }

    @Test
    void testAddRec(){
        hanoi1.addRec("WestLake");
        assertEquals(1, hanoi1.getRecs().size());
        assertEquals("WestLake", hanoi1.getRecs().get(0));
    }


    @Test
    void findRecTest(){
        hanoi1.addRec("WestLake");
        assertTrue(hanoi1.findRec("WestLake"));
        assertFalse(hanoi1.findRec("VinMart"));
    }

    @Test
    void findTagTest() {
        hanoi1.addTag("food");
        hanoi1.addTag("antique");
        assertTrue(hanoi1.findTag("food"));
        assertFalse(hanoi1.findTag("crowded"));
    }


}



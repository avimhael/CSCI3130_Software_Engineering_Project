package com.example.a3130_group17_project;

import org.junit.Test;

import static org.junit.Assert.*;

public class CannabisProductTest {

    @Test
    public void getCannabisTest(){
        CannabisProduct cp = new CannabisProduct("Weed1","123","456");
        assertEquals("Weed1",cp.getName());
        assertEquals("123", cp.getPercTHC());
        assertEquals("456", cp.getPercCBD());
    }
}
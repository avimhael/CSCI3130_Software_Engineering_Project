package com.example.a3130_group17_project;

import org.junit.Test;

import static org.junit.Assert.*;

public class AlcoholProductTest {

    @Test
    public void getAlcoholTest(){
        AlcoholProduct ap = new AlcoholProduct("Beer1","123");
        assertEquals("Beer1",ap.getName());
        assertEquals("123", ap.getAPV());
    }
}
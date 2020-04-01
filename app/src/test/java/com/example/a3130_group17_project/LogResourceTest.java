package com.example.a3130_group17_project;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class LogResourceTest {

    @Test
    public void LogResourceConstructorTest() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(2020, 01, 25);
        long date1 = calendar.getTimeInMillis();
        AlcoholProduct p = new AlcoholProduct("Beer1", "5%");
        LogResource lr1 = new LogResource(p.getName(), p.getPercentage(), 2020, 01, 25, calendar.getTimeInMillis());

        calendar.set(2019, 01, 25);
        long date2 = calendar.getTimeInMillis();
        LogResource lr2 = new LogResource(p.getName(), p.getPercentage(), 2019, 01, 25, calendar.getTimeInMillis());

        calendar.set(2018, 01, 25);
        long date3 = calendar.getTimeInMillis();
        LogResource lr3 = new LogResource(p.getName(), p.getPercentage(), 2018, 01, 25, calendar.getTimeInMillis());

        assertEquals(lr1.getProductName(), "Beer1");
        assertEquals(lr2.getProductName(), "Beer1");
        assertEquals(lr3.getProductName(), "Beer1");

        assertEquals(lr1.getProductPercentage(), "APV: 5%");
        assertEquals(lr2.getProductPercentage(), "APV: 5%");
        assertEquals(lr3.getProductPercentage(), "APV: 5%");

        assertEquals(date1, lr1.getTime());
        assertEquals(date2, lr2.getTime());
        assertEquals(date3, lr3.getTime());
    }

    @Test
    public void LogResourceCompareTest () {
        Calendar calendar = new GregorianCalendar();
        calendar.set(2020, 01, 25);
        long date1 = calendar.getTimeInMillis();
        AlcoholProduct p = new AlcoholProduct("Beer1", "5%");
        LogResource lr1 = new LogResource(p.getName(), p.getPercentage(), 2020, 01, 25, calendar.getTimeInMillis());

        calendar.set(2019, 01, 25);
        long date2 = calendar.getTimeInMillis();
        LogResource lr2 = new LogResource(p.getName(), p.getPercentage(), 2019, 01, 25, calendar.getTimeInMillis());

        calendar.set(2018, 01, 25);
        long date3 = calendar.getTimeInMillis();
        LogResource lr3 = new LogResource(p.getName(), p.getPercentage(), 2018, 01, 25, calendar.getTimeInMillis());

        assertTrue(lr1.compareTo(lr2) == 1);
        assertTrue(lr1.compareTo(lr3) == 1);
        assertTrue(lr1.compareTo(lr1) == 0);

        assertTrue(lr2.compareTo(lr1) == -1);
        assertTrue(lr2.compareTo(lr3) == 1);
        assertTrue(lr2.compareTo(lr2) == 0);


        assertTrue(lr3.compareTo(lr1) == -1);
        assertTrue(lr3.compareTo(lr2) == -1);
        assertTrue(lr3.compareTo(lr3) == 0);

    }
}
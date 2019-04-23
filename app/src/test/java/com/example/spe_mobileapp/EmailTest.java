package com.example.spe_mobileapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailTest {

    public EmailTest obj;
    @Before
    public void setUp() throws Exception {
        MainActivity obj= new MainActivity();
    }

    @Test
    public void match_emailTest() {
        MainActivity obj= new MainActivity();
        boolean check = obj.match_email("akshat.agrawal@iiitb.org");
        if(check == true)
            assertTrue(true);
        else
            assertTrue(false);

    }
    @Test
    public void match_rollTest(){
        MainActivity obj= new MainActivity();
        boolean check = obj.match_roll("2018007");
        if(check == false)
            assertTrue(true);
        else
            assertTrue(false);
    }

    @After
    public void tearDown() throws Exception {
        obj=null;
    }


}
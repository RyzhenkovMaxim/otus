package test;

import annotations.After;
import annotations.Before;
import annotations.Test;

public class TestClass {

    private int a;
    private int b;

    @Before
    public void before(){
        a = 10;
        b = 0;
    }

    @Test
    public int sum(){
       return a + b;
    }
    @Test
    public int difference(){
        return a - b;
    }
    @Test
    public int multiply(){
        return a * b;
    }

    @Test
    public int divide(){
        return a / b;
    }

    @After
    public void afterError(){
        a = 0;
        b = 0;
    }
}

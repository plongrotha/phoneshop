package com.phoneshop.org.util;


import com.phoneshop.utils.GeneralUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GeneralUtilTest {

    @Test
    public void testToIntegerList(){

        // Give
        List<String> list = List.of("Hello", "World","rotha","apple","banana");

        //When
        List<Integer> length = GeneralUtil.toLength(list);

        //Then
        assertEquals(5 ,length.size());
        assertEquals(5 ,length.get(0));
        assertEquals(5 ,length.get(1));
        assertEquals(5 ,length.get(2));
        assertEquals(5 ,length.get(3));
        assertEquals(6 ,length.get(4));
    }

//    private void assertEquals(int i, int size) {}

}

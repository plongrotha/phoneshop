package com.phoneshop.utils;

import java.util.List;

public class GeneralUtil {

    public static List<Integer> toLength (List<String> list) {
        return list
                .stream()
                .map(l -> l.length()).toList();
    }
}

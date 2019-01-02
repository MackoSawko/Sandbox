package com.inhome.SandBox.utils;

import java.util.Set;

public class Ids {

    public static int getNewId(Set<Integer> existingIds) {
        if(existingIds.isEmpty()) {
            return 0;
        }
        else {
            Integer integer = existingIds.stream().max((o1, o2) -> o1.compareTo(o2)).get();
            return integer+1;
        }
    }
}

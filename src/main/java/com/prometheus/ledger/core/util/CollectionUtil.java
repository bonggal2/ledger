package com.prometheus.ledger.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionUtil {
    public static boolean isEmpty(Collection collection){
        if (null == collection){
            return true;
        }

        if(collection.isEmpty()){
            return true;
        }

        return false;
    }

    public static boolean isNotEmpty(Collection collection){
        return !isEmpty(collection);
    }
}

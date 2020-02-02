package com.java.utils;



import org.springframework.util.StringUtils;

import java.util.UUID;

public class AppUtility {

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());

        String randomUUid = StringUtils.delete(uuid.toString(), "-");
        System.out.println("random-uuid" + randomUUid);
        return randomUUid;
    }


}

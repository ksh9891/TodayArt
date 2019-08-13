package com.artfactory.project01.todayart.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashingUtil {
    public String sha256Encoding(String original) {
        return Hashing.sha256().hashString(original, StandardCharsets.UTF_8).toString();
    }
}

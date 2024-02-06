package kz.kbtu.algoapp.util;

import java.util.Base64;

public class Utils {
    public static boolean validateBase64(String img) {
        if (img == null || img.length() % 4 != 0) {
            return false;
        }
        try {
            Base64.getDecoder().decode(img);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

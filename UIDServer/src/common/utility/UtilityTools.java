/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Duy Buffet
 */
public class UtilityTools {
    public static String generateUID(int numOfRecord, String areaCode) {
        int count = 0;
        String numRecord = String.valueOf(numOfRecord);
        StringBuilder uid = new StringBuilder(areaCode);
        while (count < (10 - numRecord.length())) {
            uid.append('0');
            count++;
        }
        uid.append(numRecord);
        return uid.toString();
    }
}

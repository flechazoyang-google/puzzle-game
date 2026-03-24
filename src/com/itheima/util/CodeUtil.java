package com.itheima.util;

import java.util.ArrayList;
import java.util.Random;

public class CodeUtil {

    public static String getCode(){

        StringBuilder code = new StringBuilder();
        ArrayList<Character> arrayList = new ArrayList<>();
        Random r = new Random();
        int a = 0;
        for (int i = 0; i < 5; i++) {
            a = r.nextInt(3);
            if(a == 0){
                arrayList.add((char)('a'+r.nextInt(26)));
            }else if(a == 1){
                arrayList.add((char)('A'+r.nextInt(26)));
            }else{
                arrayList.add((char)('0'+r.nextInt(10)));
            }
        }
        for (Character character : arrayList) {
            code.append(character);
        }
        return code.toString();
    }
}

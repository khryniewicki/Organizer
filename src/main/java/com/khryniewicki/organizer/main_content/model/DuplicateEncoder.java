package com.khryniewicki.organizer.main_content.model;

import java.util.Arrays;
import java.util.HashMap;

public class DuplicateEncoder {
    public static void main(String[] args) {
        encode("sds");
    }
    static String encode(String word){
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        String [] s= {"a","bc","dsd"};
        word.chars().forEach(letter -> hm.put(letter, (hm.containsKey(letter) ? hm.get(letter) : 0) + 1));
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        hm.forEach((c,i)->characterIntegerHashMap.put((char)c.intValue() , i));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
             if (characterIntegerHashMap.get(word.charAt(i))>1){ sb.append('(');} else { sb.append(')');}
            }
        System.out.println(Arrays.asList(s).toString());
        return sb.toString();
    }
}
package com.example.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import java.util.List;


public class URL {

    public static String decodeParam(String s){
        try{
            return URLDecoder.decode(s, "UTF-8");
        }catch (UnsupportedClassVersionError | UnsupportedEncodingException e){
            return " ";
        }
    }

    public static List<Integer> decodeIntList(String s){
        String[] vet = s.split(",");
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 0; i++){
            list.add(Integer.parseInt(vet[i]));
        }
        return list;
        //Expressao Lambda
        //return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }
}

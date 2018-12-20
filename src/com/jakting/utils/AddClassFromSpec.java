package com.jakting.utils;

/*
从填写的专业中获取班级
 */

public class AddClassFromSpec {
    public static String AddClassFromSpec(String sSpecialty){
        int endindex = sSpecialty.indexOf("班");
        System.out.println("endindex="+endindex);
        int beginindex = endindex-1;
        String sClass = sSpecialty.substring(beginindex,endindex) + "班";
        //String sClass="";
        return sClass;
    }
}

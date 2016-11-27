package com.apc.autotest.service;

import java.util.Map;


public class MapToXML {
    public static String toXML(Map<String, String> map, String root) {
        StringBuilder sb = new StringBuilder("<");
        sb.append(root);
        sb.append(">");

        for (Map.Entry<String, String> e : map.entrySet()) {
            sb.append("<");
            sb.append(e.getKey());
            sb.append(">");

            sb.append(e.getValue());

            sb.append("</");
            sb.append(e.getKey());
            sb.append(">");
        }

        sb.append("</");
        sb.append(root);
        sb.append(">");
        System.out.println(sb.toString());
        return sb.toString();
       
    }
}
package com.itaytas.securityServer.config;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface AppConstants {
    String DEFAULT_PAGE_NUMBER = "0";
    String DEFAULT_PAGE_SIZE = "10";

    int MAX_PAGE_SIZE = 50;
    
    ArrayList<String> DEFAULT_SNIFFER_CONFIG_APPS = 
    		Stream.of("FireFox", "Google Chrome").collect(Collectors.toCollection(ArrayList::new));

}

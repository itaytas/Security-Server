package com.itaytas.securityServer.config;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.itaytas.securityServer.exception.BadRequestException;

public class AppUtilsAndConstants {

	public static final String DEFAULT_PAGE_NUMBER = "0";
	public static final String DEFAULT_PAGE_SIZE = "10";

	public static final int MAX_PAGE_SIZE = 30;

	public static final ArrayList<String> DEFAULT_SNIFFER_CONFIG_APPS = 
			Stream.of("FireFox", "Google Chrome")
			.collect(Collectors.toCollection(ArrayList::new));

	public AppUtilsAndConstants() {
	}

	public static void validatePageNumberAndSize(int page, int size) {
		if (page < 0) {
			throw new BadRequestException("Page number cannot be less than zero.");
		}

		if (size > MAX_PAGE_SIZE || size <= 0) {
			throw new BadRequestException("Page size must be greater than 0 and less than " + MAX_PAGE_SIZE);
		}
	}

}

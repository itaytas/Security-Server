package com.itaytas.securityServer.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.itaytas.securityServer.exception.BadRequestException;

public class AppUtilsAndConstants {

	public static final String DEFAULT_PAGE_NUMBER = "0";
	public static final String DEFAULT_PAGE_SIZE = "10";

	public static final int MAX_PAGE_SIZE = 30;

	public static final ArrayList<String> DEFAULT_SNIFFER_CONFIG_APPS = Stream.of("Mozilla", "Google Chrome")
			.collect(Collectors.toCollection(ArrayList::new));

	public static final String INDEX_HELLO_WORLD = "Welcome to Moblie Protection For iOS final project!\n"
			+ "For registering the system please navigate to /api/auth/signup\n"
			+ "For loging in please navigate to /api/auth/signin\n" + "\nHave a great day!";

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

	public static <T extends Comparable<T>> boolean isListsEqualIgnoreOrder(List<T> list1, List<T> list2) {
		if (list1 == null && list2 == null) {
			return true;
		}
		// Only one of them is null
		else if (list1 == null || list2 == null) {
			return false;
		} else if (list1.size() != list2.size()) {
			return false;
		}

		// copying to avoid rearranging original lists
		list1 = new ArrayList<T>(list1);
		list2 = new ArrayList<T>(list2);

		Collections.sort(list1);
		Collections.sort(list2);

		return list1.equals(list2);
	}
}

package com.card.validation.cardValid.Config;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.card.validation.cardValid.DTO.CardDetailDTO;
import com.card.validation.cardValid.Model.CardDetails;

public class CardUtil {

	public static List<CardDetails> convertDTOtoModel(List<CardDetailDTO> cardDetails, String userName, String userId) {
		List<CardDetails> modelList = cardDetails.stream().map(i -> {
			return CardDetails.builder().userId(Integer.parseInt(userId)).userName(userName).cardBank(i.getCardBank())
					.cardNum(i.getCardNum()).cardType(i.getCardType()).expiryDate(i.getExpiryDate())
					.nameOnCard(i.getNameOnCard()).build();
		}).collect(Collectors.toList());

		return modelList;
	}

	public static boolean isCardNumberValid(long number) {
		return (getSize(number) >= 13 && getSize(number) <= 16)
				&& (prefixMatched(number, 4) || prefixMatched(number, 5) || prefixMatched(number, 37)
						|| prefixMatched(number, 6))
				&& ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0);
	}

	public static boolean isNameValid(String name) {
		return name.matches("^([^0-9]*)$");
	}

	public static boolean isExpiryValid(String expiryDate) {
		boolean flag = true;
		String[] dateArr = expiryDate.split("/");
		int month = LocalDateTime.now().getMonthValue();
		int year = LocalDateTime.now().getYear();
		if (Integer.valueOf(dateArr[1]) >= Integer.valueOf(String.valueOf(year).substring(2))) {
			flag = Integer.valueOf(dateArr[0]) >= month ? true : false;
		} else {
			flag = false;
		}
		return flag;
	}

	public static boolean isCVVValid() {
		return true;
	}

	public static boolean typeOfCardValid(String cardType) {
		return true;
	}

	public static boolean isValid(long number) {
		return (getSize(number) >= 13 && getSize(number) <= 16)
				&& (prefixMatched(number, 4) || prefixMatched(number, 5) || prefixMatched(number, 37)
						|| prefixMatched(number, 6))
				&& ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0);
	}

	public static int sumOfDoubleEvenPlace(long number) {
		int sum = 0;
		String num = number + "";
		for (int i = getSize(number) - 2; i >= 0; i -= 2)
			sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

		return sum;
	}

	// Return this number if it is a single digit, otherwise,
	// return the sum of the two digits
	public static int getDigit(int number) {
		if (number < 9)
			return number;
		return number / 10 + number % 10;
	}

	// Return sum of odd-place digits in number
	public static int sumOfOddPlace(long number) {
		int sum = 0;
		String num = number + "";
		for (int i = getSize(number) - 1; i >= 0; i -= 2)
			sum += Integer.parseInt(num.charAt(i) + "");
		return sum;
	}

	// Return true if the digit d is a prefix for number
	public static boolean prefixMatched(long number, int d) {
		return getPrefix(number, getSize(d)) == d;
	}

	// Return the number of digits in d
	public static int getSize(long d) {
		String num = d + "";
		return num.length();
	}

	// Return the first k number of digits from
	// number. If the number of digits in number
	// is less than k, return number.
	public static long getPrefix(long number, int k) {
		if (getSize(number) > k) {
			String num = number + "";
			return Long.parseLong(num.substring(0, k));
		}
		return number;
	}

}

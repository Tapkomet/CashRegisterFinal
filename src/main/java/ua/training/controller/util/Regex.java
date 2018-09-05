package ua.training.controller.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for Regex and methods to compare with regex
 */
public class Regex {

	private static final Logger logger = LogManager.getLogger(Regex.class.getName());

	private static final String SURNAME_REGEX = "^[a-zA-Z\\\\s]+";
	private static final String PASSWORD_REGEX = "^(?=\\S+$).{5,}$";
	private static final String EMAIL_REGEX = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
	private static final String NUMBER_REGEX = "[\\d]+";

	public static boolean isNumberCorrect(String numberString) {
		if (numberString == null) {
			return false;
		}
		logger.debug("number.matches({}) : {}", numberString, numberString.matches(NUMBER_REGEX));
		return numberString.matches(NUMBER_REGEX);
	}

	public static boolean isSurnameCorrect(String surname) {
		if (surname == null) {
			return false;
		}
		logger.debug("surname.matches({}) : {}", surname, surname.matches(SURNAME_REGEX));
		return surname.matches(SURNAME_REGEX);
	}

	public static boolean isEmailCorrect(String email) {
		if (email == null) {
			return false;
		}
		logger.debug("email.matches({}) : {}", email, email.matches(EMAIL_REGEX));
		return email.matches(EMAIL_REGEX);
	}

	public static boolean isPasswordCorrect(String password) {
		logger.debug("password : {}", password);
		if (password == null || password.isEmpty()) {
			return false;
		}
		return password.matches(PASSWORD_REGEX);
	}
}

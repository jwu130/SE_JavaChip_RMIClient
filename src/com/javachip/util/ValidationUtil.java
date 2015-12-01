package com.javachip.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

	private static final String IP_PATTERN = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$";
	private static final int SOCKETMAX = 65535;
	private static final int SOCKETMIN = 1;
	private static final String FILENAME_PATTERN = "^[^*&?%\\/\\s]+\\.txt$";
	private static final String FILECONTENTS_PATTERN = "^\\s*[0-9\\s]*\\s*$";

	public static boolean validIP(String ip) {
		boolean valid = false;
		Pattern pattern = Pattern.compile(IP_PATTERN);
		Matcher matcher = pattern.matcher(ip);
		valid = matcher.matches();
		if (!valid)
			return false;
		String[] ipAdd = ip.split(".");
		for (int i = 0; i < ipAdd.length; i++) {
			try {
				int j = Integer.parseInt(ipAdd[i]);
				if (!(j < 256))
					return false;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	public static boolean validSocketPort(String socketPort) {
		int socket;
		try {
			socket = Integer.parseInt(socketPort);
		} catch (Exception e) {
			return false;
		}
		if (socket > SOCKETMAX || socket < SOCKETMIN)
			return false;
		return true;
	}

	public static boolean validFileName(String fileName) {
		Pattern pattern = Pattern.compile(FILENAME_PATTERN);
		Matcher matcher = pattern.matcher(fileName);
		return matcher.matches();
	}

	public static boolean validFileContents(String fileContents) {
		Pattern pattern = Pattern.compile(FILECONTENTS_PATTERN);
		Matcher matcher = pattern.matcher(fileContents);
		return matcher.matches();
	}

}

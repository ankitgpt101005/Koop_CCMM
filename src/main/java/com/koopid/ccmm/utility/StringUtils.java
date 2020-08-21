package com.koopid.ccmm.utility;

public final class StringUtils
{
	/**
	 * Utility method for string to check if it is Null/Empty
	 * @param arg
	 *            input string
	 * @return true if the given string is either (null || empty || empty after
	 *         trimming)
	 */
	public static boolean isNullOrEmpty(String arg)
	{
		return arg == null || arg.trim().isEmpty();
	}
}

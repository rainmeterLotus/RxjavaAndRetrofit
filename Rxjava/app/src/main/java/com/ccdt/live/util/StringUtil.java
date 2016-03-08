/*
 * Title: hotshop2
 * Description: HotShop
 * Author: Alex Lee
 * Email: liyao20060101@gmail.com
 * Version: 2.0
 * Created on: 2011-4-21下午03:00:50
 *
 * Copyright (c) 2011 vHotShop.com
 */
package com.ccdt.live.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author alex.lee at 2011-4-21 下午03:00:50
 * 
 */
public class StringUtil {

	/**
	 * Converts a byte array to hex string.
	 * 
	 * @param b
	 *            - the input byte array
	 * @return hex string representation of b.
	 */

	public static String toHexString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_CHARS.charAt(b[i] >>> 4 & 0x0F));
			sb.append(HEX_CHARS.charAt(b[i] & 0x0F));
		}
		return sb.toString();
	}

	/**
	 * Converts a hex string into a byte array.
	 * 
	 * @param s
	 *            - string to be converted
	 * @return byte array converted from s
	 */
	public static byte[] toByteArray(String s) {
		byte[] buf = new byte[s.length() / 2];
		int j = 0;
		for (int i = 0; i < buf.length; i++) {
			buf[i] = (byte) ((Character.digit(s.charAt(j++), 16) << 4) | Character
					.digit(s.charAt(j++), 16));
		}
		return buf;
	}

	private static final String HEX_CHARS = "0123456789abcdef";

	/**
	 * 删除input字符串中的html格式
	 * 
	 * @param input
	 * @param length
	 * @return
	 */
	public static String splitAndFilterString(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "")
				.replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "")
				.replaceAll("\r\n", "").replaceAll(" ", "").trim();
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "......";
		}
		return str;
	}

	/**
	 * 删除字符串中html
	 * 
	 * @param input
	 * @return
	 */
	public static String splitAndFilterString(String input) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "")
				.replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "")
				.replaceAll("\r\n", "").replaceAll(" ", "").trim();
		return str;
	}

	/**
	 * 过滤
	 * 
	 * @param input
	 * @return
	 */
	public static String filter(String input) {
		if (!hasSpecialChars(input)) {
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i <= input.length() - 1; i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			default:
				filtered.append(c);
			}
		}
		return (filtered.toString());
	}

	public static boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0; i <= input.length() - 1; i++) {
				c = input.charAt(i);
				switch (c) {
				case '>':
					flag = true;
					break;
				case '<':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;

				}
			}
		}
		return flag;
	}

	public static String getGBK(String s) {
		if (s == null || s.equals(""))
			return s;
		String ss = s;
		try {
			ss = new String(s.getBytes(), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ss;
	}

	public static String getGBK(String s, String code) {
		if (s == null || s.equals(""))
			return s;
		String ss = s;
		try {
			ss = new String(s.getBytes(code), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ss;
	}

	public static String getISO(String s) {
		if (s == null || s.equals(""))
			return s;
		String ss = s;
		try {
			ss = new String(s.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ss;
	}

	public static String getISO(String s, String code) {
		if (s == null || s.equals(""))
			return s;
		String ss = s;
		try {
			ss = new String(s.getBytes(code), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ss;
	}

	/**
	 * 获得任意字符的索引
	 * 
	 * @param str
	 *            查找串
	 * @param sub
	 *            子串
	 * @param num
	 *            第几个
	 * @return
	 */
	public static int findStringInStringIndex(String str, String sub, int num) {
		int i = 1;
		int index = str.indexOf(sub);
		while (index != -1 && i < num) {
			index = str.indexOf(sub, index + 1);
			i++;
		}
		return index;
	}

	/**
	 * 截取内容简介
	 * 
	 * @param content
	 * @param length
	 * @param StripHTML
	 * @return
	 */
	public static String getContentSummary(String content, int length,
			boolean StripHTML) {
		if ("".equals(content) || length == 0)
			return "";
		if (StripHTML) {
			String reg = "<[^>]*>";

			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(content);
			content = m.replaceAll("");
			// content = re.Replace(content, "");
			content = content.replace("　", "").replace(" ", "");
			if (content.length() <= length)
				return content;
			else
				return content.substring(0, length) + "……";
		} else {
			if (content.length() <= length)
				return content;

			int pos = 0, npos = 0, size = 0;
			boolean firststop = false, notr = false, noli = false;
			StringBuilder sb = new StringBuilder();
			while (true) {
				if (pos >= content.length())
					break;
				String cur = content.substring(pos, 1);
				if (cur == "<") {
					String next = content.substring(pos + 1, 3).toLowerCase();
					if (next.indexOf("p") == 0 && next.indexOf("pre") != 0) {
						npos = content.indexOf(">", pos) + 1;
					} else if (next.indexOf("/p") == 0
							&& next.indexOf("/pr") != 0) {
						npos = content.indexOf(">", pos) + 1;
						if (size < length)
							sb.append("<br/>");
					} else if (next.indexOf("br") == 0) {
						npos = content.indexOf(">", pos) + 1;
						if (size < length)
							sb.append("<br/>");
					} else if (next.indexOf("img") == 0) {
						npos = content.indexOf(">", pos) + 1;
						if (size < length) {
							sb.append(content.substring(pos, npos - pos));
							size += npos - pos + 1;
						}
					} else if (next.indexOf("li") == 0
							|| next.indexOf("/li") == 0) {
						npos = content.indexOf(">", pos) + 1;
						if (size < length) {
							sb.append(content.substring(pos, npos - pos));
						} else {
							if (!noli && next.indexOf("/li") == 0) {
								sb.append(content.substring(pos, npos - pos));
								noli = true;
							}
						}
					} else if (next.indexOf("tr") == 0
							|| next.indexOf("/tr") == 0) {
						npos = content.indexOf(">", pos) + 1;
						if (size < length) {
							sb.append(content.substring(pos, npos - pos));
						} else {
							if (!notr && next.indexOf("/tr") == 0) {
								sb.append(content.substring(pos, npos - pos));
								notr = true;
							}
						}
					} else if (next.indexOf("td") == 0
							|| next.indexOf("/td") == 0) {
						npos = content.indexOf(">", pos) + 1;
						if (size < length) {
							sb.append(content.substring(pos, npos - pos));
						} else {
							if (!notr) {
								sb.append(content.substring(pos, npos - pos));
							}
						}
					} else {
						npos = content.indexOf(">", pos) + 1;
						sb.append(content.substring(pos, npos - pos));
					}
					if (npos <= pos)
						npos = pos + 1;
					pos = npos;
				} else {
					if (size < length) {
						sb.append(cur);
						size++;
					} else {
						if (!firststop) {
							firststop = true;
						}
					}
					pos++;
				}

			}
			return sb.toString();
		}
	}

	public String replaceNullOrEmpty(String str, String sign) {
		if (null == str || str.equals("")) {
			return sign;
		}
		return str;
	}

	public boolean notNullOrEmpty(String str) {
		return (null != str && !str.equals("")) ? true : false;
	}

	/**
	 * 是否有sql关键字
	 * 
	 * @param p
	 * @return
	 */
	public static boolean isValid(String p) {
		p = p.toUpperCase();

		if (p.indexOf("DELETE") >= 0 || p.indexOf("ASCII") >= 0
				|| p.indexOf("UPDATE") >= 0 || p.indexOf("SELECT") >= 0
				|| p.indexOf("'") >= 0 || p.indexOf("SUBSTR(") >= 0
				|| p.indexOf("COUNT(") >= 0 || p.indexOf(" OR ") >= 0
				|| p.indexOf(" AND ") >= 0 || p.indexOf("DROP") >= 0
				|| p.indexOf("EXECUTE") >= 0 || p.indexOf("EXEC") >= 0
				|| p.indexOf("TRUNCATE") >= 0 || p.indexOf("INTO") >= 0
				|| p.indexOf("DECLARE") >= 0 || p.indexOf("MASTER") >= 0) {
			return false;
		}
		return true;
	}

	// 过滤 ‘
	// ORACLE 注解 -- /**/
	// 关键字过滤 update ,delete

	static String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
			+ "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";

	static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

	/***************************************************************************
	 * 参数校验
	 * 
	 * @param str
	 */
	public static boolean isValidByReg(String str) {
		if (sqlPattern.matcher(str).find()) {
			return false;
		}
		return true;
	}

	/**
	 * 通过正则查找匹配
	 * 
	 * @param reg
	 * @return
	 */
	public static String findRegStr(String html, String reg) {
		String result = null;
		Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(html);
		while (matcher.find()) {
			try {
				result = matcher.group(1);
				if (result != null)
					break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String filterSQLKeyword(String name) {
		name = name.replaceAll("delete", "").replaceAll("insert", "")
				.replaceAll("drop", "").replaceAll("select", "")
				.replaceAll("update", "").replaceAll("create", "")
				.replaceAll("where", "").replaceAll("tables", "")
				.replaceAll("and", "").replaceAll("or", "")
				.replaceAll("alter", "");
		return name;
	}

	public static boolean filterCodeSQL(String keywords) {
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		return Pattern.matches(regEx, keywords)
				|| (keywords.indexOf("�") != -1);
	}

	public static int toInt(String value) {
		return Integer.valueOf(value).intValue();
	}

	/**
	 * 是否为乱码
	 * 
	 * @param strName
	 * @return
	 */
	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
					count = count + 1;
					// System.out.print(c);
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public static boolean filterCodeSQL1(String keywords) {
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）�——+|{}【】‘；：”“’。，、？]";
		return Pattern.compile(regEx).matcher(keywords).find()
				|| isMessyCode(keywords);
	}

	public static void filterHtmlTag(String html) {
		html = html.replaceAll("\"", "&amp;quot;");
		html = html.replaceAll("\'", "&amp;apos;");
		html = html.replaceAll("<", "&amp;lt;");
		html = html.replaceAll(">", "&amp;gt;");
	}

	public static void main(String[] args) {
		String encoding = gbEncoding("功能介绍:将unicode字符串转为汉字 输入参数:源unicode字符串 输出参数:转换后的字符串");
		System.out.println(encoding);
		// String decoeing = decodeUnicode(encoding);
		// System.out.println(decoeing);
	}

	public static String gbEncoding(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
		StringBuffer buffer = new StringBuffer();
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			buffer.append("" + hexB);
		}
		return buffer.substring(0);
	}
}

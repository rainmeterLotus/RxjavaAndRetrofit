package com.ccdt.live.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class PaySignUtil {
	private static final String HEX_CHARS = "0123456789abcdef";

	/**
	 * 生成签名
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static String sign(Map<String, String> map, String key) {
		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getValue() != "") {
				list.add(entry.getKey() + "=" + entry.getValue() + "&");
			}
		}
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		sb.append("key=");
		sb.append(key);
		String result = MD5Util.md5Hex(sb.toString()).toUpperCase();
		return result;
	}
}

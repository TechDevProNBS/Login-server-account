package com.nationwide.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Hashing {

	public String hash(String text) {
		return DigestUtils.sha256Hex(text);
	}
}

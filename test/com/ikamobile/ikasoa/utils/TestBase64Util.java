package com.ikamobile.ikasoa.utils;

import org.junit.Test;

import junit.framework.TestCase;

public class TestBase64Util extends TestCase {

	@Test
	public void testBase64Util() {
		String str = "测试字符串";
		assertEquals(str, new String(Base64Util.decode(Base64Util.encode(str.getBytes()))));
	}

}

package com.owl.jdk8.base64;

import org.junit.Test;

public class Base64 {

    @Test
    public void testEncode(){
        String encode123 = java.util.Base64.getEncoder().encodeToString("123".getBytes());
        System.out.println(encode123);
        //[B@32a1bec0

    }

    @Test
    public void testDecode(){
        String decode = new String(java.util.Base64.getDecoder().decode("MTIz"));
        System.out.println(decode);
    }
}

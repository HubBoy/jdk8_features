package com.owl.jdk8.optional;

import org.junit.Test;

public class Optional {

    @Test
    public void testOptional (){
        User user = null;
        User user2 = new User();
        user2.setName("user2");
        user =  java.util.Optional.ofNullable(user).orElse(user2);
        System.out.println(user.getName());
        //result user2
        boolean isNuLL = java.util.Optional.ofNullable(user).isPresent();
        System.out.println(isNuLL);
        //result true
    }

}

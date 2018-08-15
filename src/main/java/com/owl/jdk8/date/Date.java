package com.owl.jdk8.date;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Date {

    @Test
    public void testLocalDate(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        //2018-03-25
        System.out.println(localDate.getMonthValue());
        //3
    }

    @Test
    public void testLocalTime(){
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        //14:11:54.795
    }

    @Test
    public void testLocalDateTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(localDateTime));
        //2018-03-25 14:14:44
    }
}

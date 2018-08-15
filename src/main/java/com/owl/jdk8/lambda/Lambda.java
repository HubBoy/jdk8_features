package com.owl.jdk8.lambda;

import com.owl.jdk8.functionalinterface.IOperation;
import com.owl.jdk8.functionalinterface.Operation;
import org.junit.Test;

import java.util.*;


/**
 * lambda表达式(λ表达式)
 * 组成：参数列表，箭头（->），以及一个表达式或语句块
 * @author ZeroTo
 */
public class Lambda {

	@Test
	public void lambdaForEach(){
		String[] strArray = new String[]{"1","2","3","4"};
		List<String> strList = Arrays.asList(strArray);
		strList.forEach(System.out :: printf);
	}
	
	@Test
	public void lambdaComparatorSort(){
		Integer[] strArray = new Integer[]{4,1,3,2};
		List<Integer> numList = Arrays.asList(strArray);
		numList.sort(Comparator.comparing(Integer :: intValue));
		numList.forEach(num -> System.out.println(num));
	}
	
	@Test
	public void lambdaThread(){
		int num1 = 1;
		int num2 = 2;
		new Thread(()->System.out.println(num1+num2)).start();
	}

	/**
	 * 函数式编程搭配lambda
	 */
	@Test
	public void lambdaFunctionInterface(){
		IOperation iOperation = (num1, num2) -> num1 - num2;
		int num1 = 1;
		int num2 = 2;
		System.out.format(">>>>>>user-defined implements function interface abstract method lambda add: %d - %d = %d",num2 ,num1, iOperation.add(num2,num1));

		System.out.println();
		Operation operation = new Operation();
		System.out.format(">>>>>>implements class add: %d + %d = %d",num2 ,num1, operation.add(num2,num1));

		System.out.println();
		int mulValue = IOperation.multiply(num1, num2);
		System.out.format(">>>>>>function interface  static method multiply: %d * %d = %d", num1, num2, mulValue);

		System.out.println();

		int subValue = operation.subtract(num1, num2);
		System.out.format(">>>>>>function interface  default method subtract: %d - %d = %d", num1, num2, subValue);


	}

}

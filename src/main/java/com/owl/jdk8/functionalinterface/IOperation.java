package com.owl.jdk8.functionalinterface;

import  java.lang.Object;

/**
 * @FunctionalInterface 注解 声明 函数式接口
 * 特点：可结合lambda表达式使用
 * 要求：仅允许有且只有一个抽象方法
 * 		java8新增default 默认方法 及 接口实现
 * 		default方法 及 static方法 、 java.lang.Object 中的方法 不违反函数式接口
 * 备注：当仅存在一个抽象方法时 没有 @FunctionalInterface 注解 也符合函数式接口 并 拥有其特性
 * 		当存在一个抽象方法以上时 及 不符合 函数式接口 若接口有 @FunctionalInterface注解 则报错
 *
 * @author ZeroTo
 */
@FunctionalInterface
public interface IOperation{

	/**
	 * 抽象方法
	 * @param num1
	 * @param num2
	 * @return
	 */
	int add(int num1, int num2);


	/**
	 * 默认方法
	 * @param num1
	 * @param num2
	 * @return
	 */
	default  int subtract(int num1, int num2){
		return num1 - num2;
	}

	/**
	 * 静态方法
	 * @param num1
	 * @param num2
	 * @return
	 */
	static  int multiply(int num1, int num2){
		return  num1 * num2;
	}

	/**
	 * java.lang.Object equals 方法
	 * @param object
	 * @return
	 */
	boolean equals(Object object);
}

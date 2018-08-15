package com.owl.jdk8.functionalinterface;

/**
 * 接口函数 实现
 * @author ZeroTo
 */
public class Operation implements IOperation{

    @Override
    public int add(int num1, int num2) {
        return num1 + num2;
    }
}

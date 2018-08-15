package com.owl.jdk8.stream;

import org.junit.Test;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 *Collection接口中新增默认方法 stream 与 parallelStream(并行)
 *Arrays类中static静态方法 stream
 *BitSet中stream方法返回IntStream
 *Random 方法返回 IntStream、LongStream、DoubleStream
 *stream::filter  过滤
 *stream::forEach 循环函数
 *stream::map 循环执行Function<? super T, ? extends R>方法参数R 构建新的Stream
 *stream::mapToInt
 *stream::mapToLong
 *stream::mapToDouble
 *stream::flatMap 循环执行Function<? super T, ? extends Stream<? extends R>>方法参数Stream<R> 构建新的Stream 相当于两层for嵌套
 *stream::flatMapToInt
 *stream::flatMapToLong
 *stream::flatMapToDouble
 *stream::distinct 去重
 *stream::sorted   排序
 *stream::peek     生成一个包含原Stream的所有元素的新Stream,新Stream每个元素被消费时都会执行给定的消费函数
 *stream::limit    截取 截取原Stream的前N个元素 返回前n的新的stream
 *stream::skip     跳过 丢弃原Stream的前N个元素 返回后面元素的stream 元素不够 返回null
 *stream::reduce   汇聚,具体查看Stream中reduce
 *stream::collect  可变汇聚
 *stream::min      Stream<R>根据比较器返回第1个R
 *stream::max      Stream<R>根据比较器返回最后一个R
 *stream::count    数量  stream中元素数量
 *stream::anyMatch stream中是否存在满足条件的元素
 *stream::allMatch stream中是否所有元素都满足条件
 *stream::noneMatch stream中是否所有元素不满足条件
 *stream::findFirst 返回stream中的第一个元素
 *stream::findAny   返回stream中的第一个元素,单在并行流(parallelStream)中返回不确定
 *Stream::builder   使用Builder方法构建stream
 *Stream::empty     构建空stream
 *Stream::of  构建新的stream
 *Stream::iterate  创建无限流
 *Stream::generate 创建无规律无限流(稳定的流，随机元素的流)
 *Stream::concat   合并两个流
 *
 *
 * stream方法用到的方法参数：
 * void  Consumer<? super T>
 * boolean Predicate<? super T>
 * R Function<? super T, ? extends R>
 *
 * @author ZeroTo
 */
public class Stream {

    @Test
    public void testList(){//Set  Map ...
        Integer[] numberArray = new Integer[]{1,2,3,4,5,6,7};
        List<Integer> numbers = Arrays.asList(numberArray);
        numbers.stream().forEach(number-> System.out.println("|"+number));
        //result  -> |1|2|3|4|5|6|7
    }

    @Test
    public void testFilter(){
        Integer[] numberArray = new Integer[]{1,2,3,4,5,6,7};
        List<Integer> numbers = Arrays.asList(numberArray);
        numbers.stream().filter(number->number%2==0)
                        .forEach(number->System.out.print("|"+number));
        // result  -> |2|4|6
    }

    @Test
    public void testMap(){
        Integer[] numberArray = new Integer[]{1,2,3,4,5,6,7};
        List<Integer> numbers = Arrays.asList(numberArray);
        numbers.stream().map(number->number * 2)
                        .forEach(number->System.out.print("|"+number));
        // result  -> |2|4|6|8|10|12|14
        System.out.println();
        numbers.stream().forEach(number->System.out.print("|"+number));
        // result  -> |1|2|3|4|5|6|7
    }

    @Test
    public void testMapToDouble(){//同理mapToInt mapToLong
        String[] stringArray = new String[]{"1","2","3","4","5","6","7"};
        List<String> numbers = Arrays.asList(stringArray);
        numbers.stream().mapToDouble(number->Integer.valueOf(number))
                .forEach(number->System.out.print("|"+number));
        // result  -> |1.0|2.0|3.0|4.0|5.0|6.0|7.0
        System.out.println();
        numbers.stream().forEach(number->System.out.print("|"+number));
        // result  -> |1|2|3|4|5|6|7
    }

    @Test
    public void testFlatMap(){
        Integer[] numberArray = new Integer[]{1,2,3,4,5,6,7};
        String[] stringArray = new String[]{"7","8"};
        List<String> stings = Arrays.asList(stringArray);
        List<Integer> numbers = Arrays.asList(numberArray);
        numbers.stream().flatMap(string->stings.stream().map(string2->","+string2))
                .forEach(number->System.out.print("|"+number));
        // result  -> |,7|,8|,7|,8|,7|,8|,7|,8|,7|,8|,7|,8|,7|,8  ==>   |7|8==|1  |7|8==|2
        System.out.println();
        numbers.stream().forEach(number->System.out.print("|"+number));
        // result  -> |1|2|3|4|5|6|7
    }

    @Test
    public void testDistinct(){
        Integer[] numberArray = new Integer[]{2,3,2,3,4,4,5};
        List<Integer> numbers = Arrays.asList(numberArray);
        numbers.stream().distinct()
                .forEach(number->System.out.print("|"+number));
        // result  -> |2|3|4|5
        System.out.println();
        numbers.stream().forEach(number->System.out.print("|"+number));
        // result  -> |2|3|2|3|4|4|5
    }

    @Test
    public void testSorted(){
        Integer[] numberArray = new Integer[]{2,3,2,3,4,4,5};
        List<Integer> numbers = Arrays.asList(numberArray);
        numbers.stream().sorted()
                .forEach(number->System.out.print("|"+number));
        // result  -> |2|2|3|3|4|4|5
        System.out.println();
        numbers.stream().sorted(Comparator.reverseOrder())
                .forEach(number->System.out.print("|"+number));
        // result  -> |5|4|4|3|3|2|2
        System.out.println();
        numbers.stream().forEach(number->System.out.print("|"+number));
        // result  -> |2|3|2|3|4|4|5
    }

    @Test
    public void testPeek(){
        Integer[] numberArray = new Integer[]{2,3,2,3,4,4,5};
        List<Integer> numbers = Arrays.asList(numberArray);
        numbers.stream().peek(number->System.out.print("peek:"+number+"|"))
                .forEach(number->System.out.print("for:"+number+"|"));
        // result  -> peek:2|for:2|peek:3|for:3|peek:2|for:2|peek:3|for:3|peek:4|for:4|peek:4|for:4|peek:5|for:5|
        System.out.println();
        numbers.stream().forEach(number->System.out.print("|"+number));
        // result  -> |2|3|2|3|4|4|5
    }

    @Test
    public void testLimit(){
        Integer[] numberArray = new Integer[]{2,3,2,3,4,4,5};
        List<Integer> numbers = Arrays.asList(numberArray);
        numbers.stream().limit(3)
                .forEach(number->System.out.print("|"+number));
        // result  -> |2|3|2
        System.out.println();
        numbers.stream().forEach(number->System.out.print("|"+number));
        // result  -> |2|3|2|3|4|4|5
    }

    @Test
    public void testSkip(){
        Integer[] numberArray = new Integer[]{2,3,2,3,4,4,5};
        List<Integer> numbers = Arrays.asList(numberArray);
        numbers.stream().skip(3)
                .forEach(number->System.out.print("|"+number));
        // result  -> |3|4|4|5
        System.out.println();
        numbers.stream().forEach(number->System.out.print("|"+number));
        // result  -> |2|3|2|3|4|4|5
    }

    @Test
    public void testReduce(){
        Integer[] numberArray = new Integer[]{2,3,2,3,4,4,5};
        List<Integer> numbers = Arrays.asList(numberArray);
        int sum = numbers.stream().reduce((nowSum,number)->Integer.sum(nowSum,number)).get();
        System.out.println(sum);
        // result  -> 23
    }

    @Test
    public void testReduce1() {
        Integer[] numberArray = new Integer[]{2, 3, 2, 3, 4, 4, 5};
        List<Integer> numbers = Arrays.asList(numberArray);
        int sum = numbers.stream().reduce(5, (nowSum, number) -> Integer.sum(nowSum, number));
        System.out.println(sum);
        // result  -> 28  5+23
    }

    @Test
    public void testCollect() {
        Integer[] numberArray = new Integer[]{2, 3, 2, 3, 4, 4, 5};
        List<Integer> numbers = Arrays.asList(numberArray);
        List<Integer> newNumbers = numbers.stream().filter(number->number%2==0).collect(() -> new ArrayList<Integer>(),
                (list, item) -> list.add(item),(list1, list2) -> list1.addAll(list2));
        newNumbers.stream().forEach(number-> System.out.print("|"+number));
        // result  -> |2|2|4|4
    }

    @Test
    public void testCollect1() {
        Integer[] numberArray = new Integer[]{2, 3, 2, 3, 4, 4, 5};
        List<Integer> numbers = Arrays.asList(numberArray);
        List<Integer> newNumbers = numbers.stream().collect(Collectors.toList());
        newNumbers.stream().forEach(number-> System.out.print("|"+number));
        // result  -> |2|3|2|3|4|4|5
    }


    @Test
    public void testMin() {
        Integer[] numberArray = new Integer[]{2, 3, 2, 3, 4, 4, 5};
        List<Integer> numbers = Arrays.asList(numberArray);
        int num = numbers.stream().min(Comparator.naturalOrder()).get();
        System.out.println(num);
        // result  -> 2
    }

    @Test
    public void testMax() {
        Integer[] numberArray = new Integer[]{2, 3, 2, 3, 4, 4, 5};
        List<Integer> numbers = Arrays.asList(numberArray);
        int num = numbers.stream().max(Comparator.naturalOrder()).get();
        System.out.println(num);
        // result  -> 5
    }


    @Test
    public void testCount() {
        Integer[] numberArray = new Integer[]{2, 3, 2, 3, 4, 4, 5};
        List<Integer> numbers = Arrays.asList(numberArray);
        long count = numbers.stream().count();
        System.out.println(count);
        // result  -> 7
    }

    @Test
    public void testAnyMatch() {
        Integer[] numberArray = new Integer[]{2, 3, 2, 3, 4, 4, 4};
        List<Integer> numbers = Arrays.asList(numberArray);
        boolean  result = numbers.stream().anyMatch(number->number>4);
        System.out.println(result);
        // result  -> false
    }

    @Test
    public void testAllMatch() {
        Integer[] numberArray = new Integer[]{2, 3, 2, 3, 4, 4, 5};
        List<Integer> numbers = Arrays.asList(numberArray);
        boolean  result = numbers.stream().allMatch(number->number>0);
        System.out.println(result);
        // result  -> false
    }

    @Test
    public void testNoneMatch() {
        Integer[] numberArray = new Integer[]{2, 3, 2, 3, 4, 4, 4};
        List<Integer> numbers = Arrays.asList(numberArray);
        boolean  result = numbers.stream().noneMatch(number->number>4);
        System.out.println(result);
        // result  -> true
    }

    @Test
    public void testFindFirst() {
        Integer[] numberArray = new Integer[]{2, 3, 2, 3, 4, 4, 5};
        List<Integer> numbers = Arrays.asList(numberArray);
        Integer number = numbers.stream().findFirst().get();
        System.out.println(number);
        // result  -> 2
    }

    @Test
    public void testFindAny() {
        Integer[] numberArray = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        List<Integer> numbers = Arrays.asList(numberArray);
        int number = numbers.parallelStream().findAny().get();
        System.out.println(number);
        // result  -> 5
        int firstNumber = numbers.parallelStream().findFirst().get();
        System.out.println(firstNumber);
        // result  -> 1
    }

    @Test
    public void testBuilder() {
        long count = java.util.stream.Stream.builder().add(1).add(2).build().count();
        System.out.println(count);
        // result  -> 2
    }

    @Test
    public void testEmpty() {
        long count = java.util.stream.Stream.empty().count();
        System.out.println(count);
        // result  -> 0
    }

    @Test
    public void testOf() {
        java.util.stream.Stream.of(1,2,3,4,5)
                .forEach(number-> System.out.print("|"+number));
        // result  -> |1|2|3|4|5
    }

    @Test
    public void testIterate() {
        java.util.stream.Stream.iterate(0, UnaryOperator.identity())
                .limit(10)
                .forEach(System.out::print);
        // result  -> 0000000000
    }

    @Test
    public void testGenerate() {
        java.util.stream.Stream.generate(()->"|"+new Random().nextInt(5))
                .limit(10)
                .forEach(System.out::print);
        // result  -> |3|0|4|1|4|2|0|4|1|0
    }

    @Test
    public void testConcat() {
        java.util.stream.Stream.concat(java.util.stream.Stream.of(1,2,3,4,5),java.util.stream.Stream.of(2,3,4,5,6))
                .forEach(number-> System.out.print("|"+number));
        // result  -> |1|2|3|4|5|2|3|4|5|6
    }
}

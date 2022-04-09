package com.geekbrains;

public class ClassTest {

@BeforeSuite
    public  static void first(){
        System.out.println("Начинаем тест ");
    }
//  //  @BeforeSuite
//    public  static void first1(){
//        System.out.println("Начинаем тест ");
//    }

    @Test(priority = 1)
    public static void method1(){
        System.out.println(1);
    }
    @Test(priority= 2)
    public static void method2(){
        System.out.println(2);
    }


    @AfterSuite
    public static void end(){
        System.out.println("Закончили тест");
    }
}

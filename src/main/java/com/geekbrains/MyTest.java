package com.geekbrains;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MyTest {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        testclass(ClassTest.class);
    }


    public static void testclass(Class<?> testClass) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Map<Integer, ArrayList<Method>> mapPriority = new TreeMap<>();
        //  conditionСheck1(testClass);
        //   conditionСheck2(testClass);
        //получаем массив методов класса который передали в метод
        Method[] methods = testClass.getDeclaredMethods();
        // проходимся по методам и находим соответствующие анатации указанные в классе
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                // если ключ 0 не занят то добавляем в 0 ключ этот метод
                if (!mapPriority.containsKey(0)) {
                    ArrayList<Method> listMethodBeforeSuite = new ArrayList<>();
                    listMethodBeforeSuite.add(m);
                    mapPriority.put(0, listMethodBeforeSuite);
                } else {
                    // если аннатаций больше чем 1 бросаем искдючение
                    throw new RuntimeException("Метод с аннатацией BeforeSuite уже существует ");
                }

            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (!mapPriority.containsKey(11)) {
                    ArrayList<Method> listMethodAfterSuite = new ArrayList<>();
                    listMethodAfterSuite.add(m);
                    mapPriority.put(11, listMethodAfterSuite);
                } else {
                    throw new RuntimeException("Метод с аннатацией AfterSuite уже существует");
                }

            }
            if (m.isAnnotationPresent(Test.class)) {
                // полцчаем значение приоритета указанного в методе с аннотацией Test
                int priority = m.getAnnotation(Test.class).priority();
                ArrayList<Method> listMethodTest = new ArrayList<>();
                listMethodTest.add(m);
                mapPriority.put(priority, listMethodTest);
            }
            else {
                System.out.println("Метод без указанной аннатации " + m.invoke(null));

            }

        }
        // создаем обект
        Object o = testClass.getDeclaredConstructor().newInstance();
        // for (Integer key: mapPriority.keySet())     {
        for (int i = 0; i <=11; i++) {
            if (mapPriority.containsKey(i)) {
                List<Method> list = mapPriority.get(i);
                for (Method m : list) {
                    m.invoke(o);
                }

            }
        }
    }
}











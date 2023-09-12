import annotations.After;
import annotations.Before;
import annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestExecutor {

    public static void test(Class<?> testClass) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Statistics statistics = new Statistics();
        Class<?> clazz = Class.forName(testClass.getName());
        var beforeMethods = getAnnotatedMethod(clazz, Before.class);
        var afterMethods = getAnnotatedMethod(clazz, After.class);
        var testMethods = getAnnotatedMethod(clazz, Test.class);

        for(var testMethod : testMethods){
            var testObject = clazz.getDeclaredConstructor().newInstance();

            runMethods(testObject, beforeMethods);

            try {
                testMethod.invoke(testObject);
                statistics.add(testMethod.getName(), TestResult.OK);
            } catch (Exception e) {
                statistics.add(testMethod.getName(), TestResult.ERROR);
            } finally {
                runMethods(testObject, afterMethods);
            }
        }
        statistics.printStatistics();
    }

    private static List<Method> getAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotation) {
        List<Method> annotatedMethods  = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(annotation)) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods ;
    }

    private static void runMethods(Object object,  List<Method> methods){
        for(Method method : methods){
            try {
                method.invoke(object);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

import test.TestClass;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("--- Start program ---");
        TestExecutor.test(TestClass.class);
        System.out.println("\n--- Finish program ---");
    }
}
import annotations.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class Ioc {
    private Ioc(){}
    static LogInterface createLogInterface(){
        InvocationHandler handler = new DemoInvocationHandler(new LogInterfaceImpl());
        return (LogInterface)
                Proxy.newProxyInstance(
                        Ioc.class.getClassLoader(),
                        new Class<?>[] {LogInterface.class},
                        handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final LogInterface logInterfaceImpl;
        private final Set<String> annotatedMethods;

        DemoInvocationHandler(LogInterface logInterfaceImpl) {
            this.logInterfaceImpl = logInterfaceImpl;
            annotatedMethods = getAnnotatedMethod(logInterfaceImpl.getClass());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (annotatedMethods.contains(method.getName() + Arrays.toString(method.getParameters()))){
                System.out.println("\nexecuted method: " + method.getName() + ", param: " +  Arrays.toString(args).replaceAll("[\\[\\]]", ""));
            }
            return method.invoke(logInterfaceImpl, args);
        }

        private static Set<String> getAnnotatedMethod(Class<?> clazz) {
            Set<String> annotatedMethods  = new HashSet<>();
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Log.class)) {
                    annotatedMethods.add(method.getName() + Arrays.toString(method.getParameters()));
                }
            }
            return annotatedMethods ;
        }
    }
}

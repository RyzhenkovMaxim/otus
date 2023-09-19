import annotations.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class Ioc {
    private Ioc() {
    }

    @SuppressWarnings("unchecked")
    static <T> T createLogInterface(Object object) {
        InvocationHandler handler = new DemoInvocationHandler(object);
        return (T)
                Proxy.newProxyInstance(
                        Ioc.class.getClassLoader(),
                        object.getClass().getInterfaces(),
                        handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final Object object;
        private final Set<String> annotatedMethods;

        DemoInvocationHandler(Object object) {
            this.object = object;
            annotatedMethods = getAnnotatedMethod(object.getClass(), Log.class);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (annotatedMethods.contains(method.getName() + Arrays.toString(method.getParameters()))) {
                System.out.println("\nexecuted method: " + method.getName() + ", param: " + Arrays.toString(args).replaceAll("[\\[\\]]", ""));
            }
            return method.invoke(object, args);
        }

        private static Set<String> getAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotation) {
            Set<String> annotatedMethods = new HashSet<>();
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(annotation)) {
                    annotatedMethods.add(method.getName() + Arrays.toString(method.getParameters()));
                }
            }
            return annotatedMethods;
        }
    }
}



public class Main {
    public static void main(String[] args) {
        System.out.println("--Start--");

        LogInterface logInterface = Ioc.createLogInterface(new LogInterfaceImpl());
        logInterface.calc("String Param 123");
        logInterface.calc();
        logInterface.calc(1);
        logInterface.calc(2, 3);
        logInterface.calc(4, 5, 6);

        System.out.println("--Finish--");
    }
}
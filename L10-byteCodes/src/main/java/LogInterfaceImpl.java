import annotations.Log;

public class LogInterfaceImpl implements LogInterface{

    @Override
    public void calc() {
        System.out.println("Method No parameters");
    }

    @Log
    @Override
    public void calc(int x) {
        System.out.println("Method with 1 parameter");
    }

    @Log
    @Override
    public void calc(int x, int y) {
        System.out.println("Method with 2 parameter");
    }

    @Log
    @Override
    public void calc(int x, int y, int z) {
        System.out.println("Method with 3 parameter");
    }
    @Log
    @Override
    public void calc(String x) {
        System.out.println("Method with 1 string parameter");
    }
}

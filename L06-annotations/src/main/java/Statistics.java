import java.util.HashMap;
import java.util.Map;

public class Statistics {
    Map<String, TestResult> resultMap = new HashMap<>();

    public void printStatistics(){
        int totalTest = 0;
        int errorTest = 0;
        int successfulTest = 0;

        System.out.println("\nStatistics:");
        for (Map.Entry<String, TestResult> entry : resultMap.entrySet()) {
            totalTest++;
            switch (entry.getValue()){
                case OK -> successfulTest++;
                case ERROR -> errorTest++;
            }
            System.out.println("Method " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("\nSUMMARY:");
        System.out.println("Total tests: " + totalTest);
        System.out.println("Error tests: " + errorTest);
        System.out.println("Successful tests: " + successfulTest);
    }

    public void add(String method, TestResult testResult) {
        resultMap.put(method, testResult);
    }
}

package homework;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final TreeMap<Customer, String> customerStringMap = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return entryClone(customerStringMap.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Customer nextKey = customerStringMap.higherKey(customer);
        if(nextKey == null){
            return null;
        }
        return entryClone(customerStringMap.ceilingEntry(nextKey));
    }

    public void add(Customer customer, String data) {
        customerStringMap.put(customer, data);
    }

    private Map.Entry<Customer, String> entryClone (Map.Entry<Customer, String> entry){
        return Map.entry(new Customer(entry.getKey()), entry.getValue());
    }
}

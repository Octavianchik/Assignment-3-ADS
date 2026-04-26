import java.util.Random;

public class MainPart1 {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int randId = random.nextInt(100000);
            MyTestingClass key = new MyTestingClass(randId, "Student" + randId);
            table.put(key, "Value" + i);
        }

        table.printBucketSizes();
    }
}
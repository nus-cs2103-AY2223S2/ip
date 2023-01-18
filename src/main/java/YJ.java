import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class YJ {
    public static void main(String[] args) {

        System.out.println("Hello! I'm YJ. What can I do for you?");

        // ArrayList of String
        List<String> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if(input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(input);
                System.out.println("Added: " + input);
            }
            input = sc.nextLine();
        }

        System.out.println("Byebye, YJ will miss you :(");
        sc.close();
    }
}

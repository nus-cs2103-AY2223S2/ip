import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hey! This is Duke at your service!");
        while(myObj.hasNext()) {
            String input = myObj.nextLine();
            if (input.equals("list")) {
                tasks.forEach(x -> System.out.println(x));
                continue;
            }
            tasks.add(input);
            if (input.equals("bye")) {
                System.out.println("See you again, thanks for visiting!");
                break;
            }
            System.out.println(input);
        }
    }
}

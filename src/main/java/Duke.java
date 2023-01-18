import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String start_message = "Hello! I'm Duke\n" +  "What can I do for you?";
        System.out.println(start_message);
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<String>();
        String s = scanner.nextLine();
        int i = 1;
        while (!s.equals("bye")) {
            if (s.equals("list")){
                i = 1;
                for (String str: list){
                    System.out.println(String.valueOf(i) + ". " + str);
                    i++;
                }
            } else {
                list.add(s);
                System.out.println("added: " + s);
            }
            s = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
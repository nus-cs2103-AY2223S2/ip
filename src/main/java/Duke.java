import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---\n hi i'm Duke! what's up? \n---");

        List<String> list = new ArrayList<>();
        while(sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("---\n bye! see u soon! :-) \n---");
                System.exit(0);
            } else if (command.equals("list")) {
                int index = 1;
                System.out.println("---");
                for (String a : list) {
                    System.out.println(index + ". " + a);
                    index++;
                }
                System.out.println("---");
            } else {
                list.add(command);
                System.out.println("---\n added: " + command + "\n---");
            }
        }
    }
}
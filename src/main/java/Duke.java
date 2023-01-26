import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static final String LINE = "----------------------------------------------";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>(100);
        System.out.println(LINE);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println(LINE+"\n");
        while (true){
            String command = scanner.nextLine();
            if (command.equals("bye")){
                System.out.println(LINE);
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(LINE+"\n");
                break;
            }
            else if (command.equals("list")) {
                System.out.println(LINE);
                for (int i = 0; i < list.size(); i++){
                    System.out.println("\t" + (i + 1) + ". "+ list.get(i));
                }
                System.out.println(LINE+"\n");
            }
            else {
                System.out.println(LINE);
                System.out.println("\t" + "added: " + command);
                System.out.println(LINE + "\n");
                list.add(command);
            }
        }
    }
}
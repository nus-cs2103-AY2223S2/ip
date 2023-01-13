import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static Scanner instr = new Scanner(System.in);
    public static List<Tasks> todoList = new ArrayList<>(100);
    public static void Greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you? "+
                "\n('bye' to terminate)" +
                "\n('list to access list of tasks)");
    }
    public static void Echo(String str) {
        if(str.equals("bye")){
            Exit();
        }
        else {
            if (str.equals("list")){
                int n = 1;
                for(Tasks t: todoList) {
                    System.out.println(n + ". " + t.getDesc());
                    n++;
                }
            }
            else {
                todoList.add(new Tasks(str));
                System.out.println("added: " + str);
            }
        }
    }
    public static void Exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet();
        while (instr.hasNextLine()) {
            String str = instr.nextLine();
            Echo(str);
        }

    }

}

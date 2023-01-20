import java.util.*;
import leo.task.*;

public class Leo {
    public static void main(String[] args) throws LeoTaskException {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from Argentina, I'm Leo!\n" + "What can I do for you?");
        new Leo().start();
    }

    public void start() throws LeoTaskException {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine(); // reads in command fed by user
        TaskList taskList = new TaskList();
        while (!cmd.equals("bye")) {
            taskList.processRequest(cmd);
            cmd = sc.nextLine();
        }
        System.out.println("It was nice talking, see you soon!");
        sc.close();
    }
}

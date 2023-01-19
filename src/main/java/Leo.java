import java.util.*;

public class Leo {

    private ArrayList<String> taskList = new ArrayList<>();

    public static void main(String[] args) {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from Argentina, I'm Leo!\n" + "What can I do for you?");
        new Leo().start();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine(); // reads in command fed by user

        while (!cmd.equals("bye")) {
            processRequest(cmd);
            cmd = sc.nextLine();
        }
        processRequest(cmd);
        sc.close();
    }

    private void addTask(String task) {
        taskList.add(task);
    }

    private void processRequest(String cmd) {
        for (int i = 0; i < 25; i++) {
            System.out.print("*");
        }
        System.out.println();
        if (cmd.equals("bye")) {
            System.out.println("It was nice talking, see you soon!");
        } else if (cmd.equals("list")){
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("%d) %s\n", i + 1, taskList.get(i));
            }
        } else {
            addTask(cmd);
            System.out.println("added: " + cmd);
        }
        for (int i = 0; i < 25; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

}

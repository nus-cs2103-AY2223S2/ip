import java.util.*;

public class Leo {

    private ArrayList<Task> taskList = new ArrayList<>();

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

    private void addTask(String taskName) {
        taskList.add(new Task(taskName));
    }

    private void processRequest(String cmd) {
        for (int i = 0; i < 25; i++) {
            System.out.print("*");
        }
        System.out.println();

        // if (cmd.equals("bye")) {
        // System.out.println("It was nice talking, see you soon!");
        // } else if (cmd.equals("list")){
        // for (int i = 0; i < taskList.size(); i++) {
        // System.out.printf("%d) %s\n", i + 1, taskList.get(i));
        // }
        // } else {
        // addTask(cmd);
        // System.out.println("added: " + cmd);
        // }

        if (cmd.equals("bye")) {
            System.out.println("It was nice talking, see you soon!");
        } else if (cmd.equals("list")) {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("%d) %s\n", i + 1, taskList.get(i));
            }
        } else if (cmd.contains("mark")) {
            if (taskList.isEmpty()) {
                System.out.println("You have no tasks to mark!");
            } else {
                String[] cmdArr = cmd.split(" ");
                int cmdIdx = Integer.parseInt(cmdArr[1]) - 1;
                if (cmdArr[0].equals("mark")) {
                    taskList.get(cmdIdx).setDone();
                    System.out.println("Well done on completing the task! Let me mark that as done!");
                } else {
                    taskList.get(cmdIdx).resetDone();
                    System.out.println("Ok, I've marked that as not done! Please get to it :(");
                }
                System.out.printf("  %s\n", taskList.get(cmdIdx));
            }
        } else {
            addTask(cmd);
            System.out.printf("added: %s\n", cmd);
        }
        for (int i = 0; i < 25; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}

class Task {
    private String taskName;
    private char isDone = ' ';

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setDone() {
        isDone = 'X';
    }

    public void resetDone() {
        isDone = ' ';
    }

    public String toString() {
        return String.format("[%c] %s", isDone, taskName);
    }
}

import java.util.*;
public class Duke {
    private static void printDashes() {
        System.out.println ("****************************************");
    }

    private static void printList(ArrayList<Task> items) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= items.size(); i++) {
            System.out.println(i + ".[" + items.get(i - 1).getStatusIcon() + "] " + items.get(i - 1).getTaskDes());
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        String request = "";
        int chosenTask = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);

        printDashes();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! This is Esther!");
        System.out.println("How can I help you today? ^_^");
        printDashes();

        while(!request.equals("bye")) {
            String userInput = sc.nextLine();
            request = userInput.split("\\s+")[0];

            switch(request) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon! ^_^");
                    break;
                case "list":
                    printList(list);
                    break;
                case "mark":
                    chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
                    list.get(chosenTask - 1).setIsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + list.get(chosenTask - 1).getStatusIcon() + "] " + list.get(chosenTask - 1).getTaskDes());
                    break;
                case "unmark":
                    chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
                    list.get(chosenTask - 1).revertIsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + list.get(chosenTask - 1).getStatusIcon() + "] " + list.get(chosenTask - 1).getTaskDes());
                    break;
                default:
                    System.out.println("*added: " + userInput);
                    Task userTask = new Task(userInput);
                    list.add(userTask);
            }
            printDashes();
        }
    }
}

class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setIsDone() {
        isDone = true;
    }

    public void revertIsDone() {
        isDone = false;
    }

    public String getTaskDes() {
        return taskDescription;
    }
}


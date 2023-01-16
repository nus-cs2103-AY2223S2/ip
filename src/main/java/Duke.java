import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> dukeList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
                
        Scanner sc = new Scanner(System.in);
        String line = "init";
        // System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        while (!line.equals("bye")) {
            if (!line.equals("init")) {
                if (line.equals("list")) {
                    displayMsg(outputList());
                } else if (line.startsWith("mark ") || line.startsWith("unmark ")){
                    int listIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                    Task targetTask = dukeList.get(listIndex);
                    String output; 
                    if (line.startsWith("mark")){
                        targetTask.markDone();
                        output = "Nice! I've marked this task as done:";
                    } else {
                        targetTask.unmarkDone();
                        output = "Ok, I've marked this task as not done yet:";
                    }
                    displayMsg(output + "\n" + targetTask.toString());
                } else {
                    dukeList.add(new Task(line));
                    displayMsg("added: " + line);
                }   
            }
            line = sc.nextLine();
        }
        sc.close();
        displayMsg("Bye. Hope to see you again soon!");
    }


    public static String outputList() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:");
        for (int index = 0; index < dukeList.size(); index++) {
            result.append("\n" + (index + 1) + ". " + dukeList.get(index).toString());
        }
        return result.toString();
    }

    public static void displayMsg(String msg) {
        System.out.println(wrapMessageBorder(msg));
    }

    public static String wrapMessageBorder(String msg) {
        String border = "____________________________________________________________";
        return border + "\n" + msg + "\n" + border;
    }
}

class Task {
    String taskName;
    boolean completed = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void markDone() {
        this.completed = true;
    } 
    public void unmarkDone() {
        this.completed = false;
    }

    @Override
    public String toString() {
        return "[" + (this.completed ? "x" : " ") + "] " + this.taskName;
    }
}
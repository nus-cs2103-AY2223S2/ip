import java.util.*;
import java.io.*;
public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringBuilder sb = new StringBuilder();
        String text = "";
        sb.append("    ____________________________________________________________\n")
                .append("    Hello! I'm Duke\n")
                .append("    What can I do for you?\n")
                .append("    ____________________________________________________________\n");
        pw.println(sb.toString());  // Welcome Message bye Duke
        pw.flush();
        sb.setLength(0);
        ArrayList<Task> storage2 = new ArrayList<Task>();
        while (true) {  // Echoing
            text = br.readLine();
            String[] tempText = text.split(" ");
            switch(tempText[0].toLowerCase()) {
                case "bye":
                    sb.append("    ____________________________________________________________\n")
                            .append("    Bye. Hope to see you again soon!\n")
                            .append("    ____________________________________________________________\n");
                    break;
                case "list":
                    sb.append("    ____________________________________________________________\n")
                            .append("    Here are the tasks in your list:\n");
                    for (int i = 0; i < storage2.size(); i++) {
                        sb.append("    ").append(i + 1).append(". ").append(storage2.get(i).getTaskInfo());
                    }
                    sb.append("    ____________________________________________________________\n");
                    break;
                case "mark":
                    int taskNumber = Integer.parseInt(tempText[1]);
                    // need to take care of this error where there isnt a tempText[1]
                    if (taskNumber <= storage2.size()) {    // need to add in an else print an error statement
                        // If the task number given is within the range of tasks in the list
                        Task tempTask = storage2.get(taskNumber - 1);
                        sb.append("    ____________________________________________________________\n")
                                .append(tempTask.markAsDone())
                                .append("    ____________________________________________________________\n");
                        storage2.set(taskNumber -1, tempTask);
                    }
                    break;
                case "unmark":
                    int taskNumber2 = Integer.parseInt(tempText[1]);
                    // need to take care of this error where there isnt a tempText[1]
                    if (taskNumber2 <= storage2.size()) {    // need to add in an else print an error statement
                        // If the task number given is within the range of tasks in the list
                        Task tempTask = storage2.get(taskNumber2 - 1);
                        sb.append("    ____________________________________________________________\n")
                                .append(tempTask.markAsIncomplete())
                                .append("    ____________________________________________________________\n");
                        storage2.set(taskNumber2 -1, tempTask);
                    }
                    break;
                default:
                    Task newTask = new Task(text);
                    storage2.add(newTask);
                    sb.append("    ____________________________________________________________\n")
                            .append("    added: ").append(newTask.getTaskInfo())
                            .append("    ____________________________________________________________\n");
            }
            pw.println(sb.toString());
            pw.flush();
            sb.setLength(0);
            if (tempText[0].equalsIgnoreCase("bye")) {
                break;
            }
        }
        br.close();
        pw.close();
    }
}

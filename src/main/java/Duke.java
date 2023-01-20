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
                .append("    Hello! I'm Duke.\n")
                .append("    What can I do for you?\n")
                .append("    ____________________________________________________________\n");
        pw.println(sb.toString());  // Welcome Message bye Duke
        pw.flush();
        sb.setLength(0);
        ArrayList<Task> storage2 = new ArrayList<Task>();
        while (true) {
            boolean goNext = false;
            boolean secondaryCheck = false;
            text = br.readLine();
            String[] tempText = text.split(" ", 2);
            String tempCmd = tempText[0].toLowerCase();
            if ( (tempCmd.equals("mark")) || (tempCmd.equals("unmark")) || (tempCmd.equals("delete")) ||
                    (tempCmd.equals("todo")) || (tempCmd.equals("deadline")) || (tempCmd.equals("event")) ) {
                secondaryCheck = true;
            }
            try {   // Primary Level of Checking to ensure User's Input has no issue!
                DukeException.validate(secondaryCheck, tempCmd, tempText);
            } catch (IncorrectNoOfArgumentException ex) {
                System.out.println(ex);
                continue;
            }
            switch (tempCmd) {
                case "bye":
                    sb.append("    ____________________________________________________________\n")
                            .append("    Bye. Hope to see you again soon!\n")
                            .append("    ____________________________________________________________\n");
                    break;
                case "list":
                    sb.append("    ____________________________________________________________\n")
                            .append("    Here are the tasks in your list:\n");
                    for (int i = 0; i < storage2.size(); i++) {
                        sb.append("    ").append(i + 1).append(".").append(storage2.get(i).getTaskInfo()).append("\n");
                    }
                    sb.append("    ____________________________________________________________\n");
                    break;
                case "mark":
                    int taskNumber = Integer.parseInt(tempText[1]);
                    if ((taskNumber <= storage2.size()) && (taskNumber > 0)) {
                        Task tempTask = storage2.get(taskNumber - 1);
                        sb.append("    ____________________________________________________________\n")
                                .append(tempTask.markAsDone())
                                .append("\n    ____________________________________________________________\n");
                        storage2.set(taskNumber - 1, tempTask);
                    } else {
                        sb.append("    ____________________________________________________________\n")
                                .append("    The task you are trying to mark is out of range! Try again!\n")
                                .append("    ____________________________________________________________\n");
                    }
                    break;
                case "unmark":
                    int taskNumber2 = Integer.parseInt(tempText[1]);
                    if ((taskNumber2 <= storage2.size()) && (taskNumber2 > 0)) {
                        Task tempTask = storage2.get(taskNumber2 - 1);
                        sb.append("    ____________________________________________________________\n")
                                .append(tempTask.markAsIncomplete())
                                .append("\n    ____________________________________________________________\n");
                        storage2.set(taskNumber2 - 1, tempTask);
                    } else {
                        sb.append("    ____________________________________________________________\n")
                                .append("    The task you are trying to unmark is out of range! Try again!\n")
                                .append("    ____________________________________________________________\n");
                    }
                    break;
                case "delete":
                    int taskNumber3 = Integer.parseInt(tempText[1]);
                    if ((taskNumber3 <= storage2.size()) && (taskNumber3 > 0)) {
                        Task tempTask = storage2.remove(taskNumber3 - 1);
                        sb.append("    ____________________________________________________________\n")
                                .append("    Noted. I've removed this task:\n")
                                .append("      ").append(tempTask.getTaskInfo())
                                .append("\n    Now you have ").append(storage2.size()).append(" tasks in the list.\n")
                                .append("    ____________________________________________________________\n");
                    } else {
                        sb.append("    ____________________________________________________________\n")
                                .append("    The task you are trying to delete is out of range! Try again!\n")
                                .append("    ____________________________________________________________\n");
                    }
                    break;
                default:    // add new Task into the Reminder list
                    Task newTask = new Task(text);
                    switch (tempCmd) {
                        case "todo":
                            newTask = new ToDos(tempText[1]);
                            break;
                        case "deadline":
                            String[] tempText2 = tempText[1].split("/by", 2);
                            try {
                                DukeException.validate(true, tempCmd, tempText2);
                            } catch (IncorrectNoOfArgumentException ex) {
                                System.out.println(ex);
                                goNext = true;
                                break;
                            }
                            newTask = new Deadline(tempText2[0], tempText2[1]);
                            break;
                        case "event":
                            String[] tempText3 = tempText[1].split("/from", 2);
                            try {
                                DukeException.validate(true, tempCmd, tempText3);
                            } catch (IncorrectNoOfArgumentException ex) {
                                System.out.println(ex);
                                goNext = true;
                                break;
                            }
                            String[] tempText4 = tempText3[1].split("/to", 2);
                            try {
                                DukeException.validate(true, tempCmd, tempText4);
                            } catch (IncorrectNoOfArgumentException ex) {
                                System.out.println(ex);
                                goNext = true;
                                break;
                            }
                            newTask = new Event(tempText3[0], tempText4[0], tempText4[1]);
                            break;
                        default:    // throw an error as the user is trying to call a function that does not exist
                            try {
                                DukeException.validate2();
                            } catch (InvalidCommandException ex) {
                                System.out.println(ex);
                                goNext = true;
                                break;
                            }
                    }
                    if (!goNext) {
                        storage2.add(newTask);
                    }
                    sb.append("    ____________________________________________________________\n")
                            .append("    Got it. I've added this task to the list:\n")
                            .append("      ").append(newTask.getTaskInfo())
                            .append("\n    Now you have ").append(storage2.size()).append(" tasks in the list.\n")
                            .append("    ____________________________________________________________\n");
            }
            if (goNext) {
                sb.setLength(0);
                continue;
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

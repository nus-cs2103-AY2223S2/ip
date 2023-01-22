import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;

import java.lang.StringBuilder;

import java.util.ArrayList;

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
        boolean hasFile = true;
        boolean isStorageData = true;
        ArrayList<String> storageElements = new ArrayList<String>();
        ArrayList<Task> storage2 = new ArrayList<Task>();
        String text = "";
        sb.append("    ____________________________________________________________\n")
                .append("    Hello! I'm Duke.\n")
                .append("    What can I do for you?\n")
                .append("    ____________________________________________________________\n");
        pw.println(sb.toString());  // Welcome Message by Duke
        pw.flush(); // To force the PrintWriter to print
        sb.setLength(0);    // Clear strings stored in StringBuilder after use
        try {
            DukeException.folderCheck("data");  // Checks if the folder exists
            BufferedReader fr = new BufferedReader(new FileReader("data/storage.txt"));
            // Checks if the storage file is in the right folder
            String currLine;
            while ( (currLine = fr.readLine()) != null) {
                storageElements.add(currLine);  // Copy storage.txt elements over
            }
            fr.close();
        } catch (FolderNotFoundException ex) {
            System.out.println(ex);
            hasFile = false;
        } catch (FileNotFoundException ex) {
            sb.append("    ____________________________________________________________\n")
                    .append("    File 'storage' cannot be found.\n")
                    .append("    Please download the latest version of Duke or create")
                    .append("\n    'storage.txt' under the folder 'data'.\n")
                    .append("    ____________________________________________________________\n");
            pw.println(sb.toString());
            pw.flush();
            sb.setLength(0);
            hasFile = false;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        while (hasFile) {
            boolean hasIssue = false;   // check if there's insufficient arguments provided by user
            boolean isAvailable = false;    // check if user is calling a supported function provided by Duke
            if (storageElements.size() != 0) {
                text = storageElements.remove(0);
            } else {
                isStorageData = false;
                text = br.readLine();
            }
            String[] tempText = text.split(" ", 2);
            String tempCmd = tempText[0].toLowerCase();
            if ( (tempCmd.equals("mark")) || (tempCmd.equals("unmark")) || (tempCmd.equals("delete")) ||
                    (tempCmd.equals("todo")) || (tempCmd.equals("deadline")) || (tempCmd.equals("event")) ) {
                isAvailable = true;
            }
            try {   // Primary Level Check to predetermine function called by the user and if there's required arguments
                DukeException.validate(isAvailable, tempCmd, tempText);
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
                        hasIssue = true;
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
                        hasIssue = true;
                        break;
                    }
                    String[] tempText4 = tempText3[1].split("/to", 2);
                    try {
                        DukeException.validate(true, tempCmd, tempText4);
                    } catch (IncorrectNoOfArgumentException ex) {
                        System.out.println(ex);
                        hasIssue = true;
                        break;
                    }
                    newTask = new Event(tempText3[0], tempText4[0], tempText4[1]);
                    break;
                default:    // throw an error as the user is trying to call a function that does not exist
                    try {
                        DukeException.validate2();
                    } catch (InvalidCommandException ex) {
                        System.out.println(ex);
                        hasIssue = true;
                        break;
                    }
                }
                if (!hasIssue) {
                    storage2.add(newTask);
                }
                if (!isStorageData) {
                    sb.append("    ____________________________________________________________\n")
                            .append("    Got it. I've added this task to the list:\n")
                            .append("      ").append(newTask.getTaskInfo())
                            .append("\n    Now you have ").append(storage2.size()).append(" tasks in the list.\n")
                            .append("    ____________________________________________________________\n");
                }
            }
            if (hasIssue) {
                sb.setLength(0);
                continue;
            }
            pw.println(sb.toString());
            pw.flush();
            sb.setLength(0);
            if (tempCmd.equals("bye")) {    // Terminate the program as desired by user
                break;
            }
        }
        br.close();
        pw.close();
    }
}

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.lang.StringBuilder;

import java.util.ArrayList;

import java.time.format.DateTimeParseException;

public class Duke {

    private static ArrayList<String> getFileContents(String filePath, String folderPath) throws IOException,
            FolderNotFoundException {
        ArrayList<String> fileElements = new ArrayList<>();
        DukeException.folderCheck(folderPath);  // Checks if the folder exists
        BufferedReader fr = new BufferedReader(new FileReader(filePath));
        // Checks if the storage file is in the right folder
        String currLine;
        while ( (currLine = fr.readLine()) != null) {
            fileElements.add(currLine);  // Copy storage.txt elements over
        }
        fr.close();
        return fileElements;
    }

    private static void printMessage(PrintWriter pw, StringBuilder sb) {
        pw.println(sb.toString());
        pw.flush(); // Flush the message out and print to user
        sb.setLength(0);    // Clear string stored in StringBuilder
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void writeToFile(String oldText, String newText, int oldTextIndex, PrintWriter pw,
                                    StringBuilder sb) throws IOException {
        ArrayList<String> fileTasks = new ArrayList<>();
        try {
            fileTasks = getFileContents("data/storage.txt", "data");
        } catch (FolderNotFoundException e) {
            System.out.println(e);
        } catch (FileNotFoundException e) {
            sb.append("    ____________________________________________________________\n")
                    .append("    File 'storage' cannot be found.\n")
                    .append("    Please download the latest version of Duke or create")
                    .append("\n    'storage.txt' under the folder 'data'.\n")
                    .append("    ____________________________________________________________\n");
            printMessage(pw, sb);
        } catch (IOException e) {
            System.out.println("An unexpected error has occurred: " + e.getMessage());
        }

        if (fileTasks.size() != 0) {
            FileWriter fw = new FileWriter("data/storage.txt");
            for (int i = 0; i < fileTasks.size(); i++) {
                if ( (fileTasks.get(i).equals(oldText)) && (i == oldTextIndex) ) {
                    if (newText.equals("")) {
                        continue;
                    }
                    fw.write(newText + "\n");
                    continue;
                }
                fw.write(fileTasks.get(i) + "\n");
            }
            fw.close();
        }
    }

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
        boolean hasFile = true; // determine if the file exists
        boolean isFileData = true;  // whether to retrieve tasks from the saved file
        ArrayList<String> fileTasks = new ArrayList<>();
        ArrayList<Task> taskList = new ArrayList<>();
        String text;

        sb.append("    ____________________________________________________________\n")
                .append("    Hello! I'm Duke.\n")
                .append("    What can I do for you?\n")
                .append("    ____________________________________________________________\n");
        printMessage(pw, sb);
        try {
            fileTasks = getFileContents("data/storage.txt", "data");
        } catch (FolderNotFoundException e) {
            System.out.println(e);
            hasFile = false;
        } catch (FileNotFoundException e) {
            sb.append("    ____________________________________________________________\n")
                    .append("    File 'storage' cannot be found.\n")
                    .append("    Please download the latest version of Duke or create")
                    .append("\n    'storage.txt' under the folder 'data'.\n")
                    .append("    ____________________________________________________________\n");
            printMessage(pw, sb);
            hasFile = false;
        } catch (IOException e) {
            System.out.println("An unexpected error has occurred: " + e.getMessage());
        }
        while (hasFile) {
            boolean hasIssue = false;   // check if there's insufficient arguments provided by user
            boolean isAvailable = false;    // check if user is calling a supported function provided by Duke
            boolean isTaskCompleted = false;    // indicates if the task is completed
            int taskNumber; // stores the task index in the list
            String tempCmd; // stores function call by user (eg todo, mark, etc)
            String[] tempText;  // stores the command called by user in CLI
            if (fileTasks.size() != 0) {
                text = fileTasks.remove(0);
            } else {
                isFileData = false;
                text = br.readLine();
            }
            if (isFileData) {
                tempText = text.split("] ");
                switch (tempText[0]) {
                case "[D][ ":
                    tempCmd = "deadline";
                    break;
                case "[D][X":
                    tempCmd = "deadline";
                    isTaskCompleted = true;
                    break;
                case "[T][ ":
                    tempCmd = "todo";
                    break;
                case "[T][X":
                    tempCmd = "todo";
                    isTaskCompleted = true;
                    break;
                case "[E][ ":
                    tempCmd = "event";
                    break;
                case "[E][X":
                    tempCmd = "event";
                    isTaskCompleted = true;
                    break;
                default:
                    tempCmd = "";
                    isTaskCompleted = false;
                    break;
                }
            } else {
                tempText = text.split(" ", 2);
                tempCmd = tempText[0].toLowerCase();
            }
            if ( (tempCmd.equals("mark")) || (tempCmd.equals("unmark")) || (tempCmd.equals("delete")) ||
                    (tempCmd.equals("todo")) || (tempCmd.equals("deadline")) || (tempCmd.equals("event")) ) {
                isAvailable = true;
            }
            try {   // determine function called by the user has required arguments and if its valid
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
                for (int i = 0; i < taskList.size(); i++) {
                    sb.append("    ").append(i + 1).append(".").append(taskList.get(i).getTaskInfoStatus())
                            .append("\n");
                }
                sb.append("    ____________________________________________________________\n");
                break;
            case "mark":
                taskNumber = Integer.parseInt(tempText[1]);
                if ((taskNumber <= taskList.size()) && (taskNumber > 0)) {
                    Task tempTask = taskList.get(taskNumber - 1);
                    String oldTaskInfo = tempTask.getTaskInfo();
                    sb.append("    ____________________________________________________________\n")
                            .append(tempTask.markAsDone())
                            .append("\n    ____________________________________________________________\n");
                    taskList.set(taskNumber - 1, tempTask);
                    try {
                        writeToFile(oldTaskInfo, tempTask.getTaskInfo(), taskNumber - 1, pw, sb);
                    } catch (IOException e) {
                        System.out.println("An unexpected error has occurred: " + e.getMessage());
                    }
                } else {
                    sb.append("    ____________________________________________________________\n")
                            .append("    The task you are trying to mark is out of range! Try again!\n")
                            .append("    ____________________________________________________________\n");
                }
                break;
            case "unmark":
                taskNumber = Integer.parseInt(tempText[1]);
                if ((taskNumber <= taskList.size()) && (taskNumber > 0)) {
                    Task tempTask = taskList.get(taskNumber - 1);
                    String oldTaskInfo = tempTask.getTaskInfo();
                    sb.append("    ____________________________________________________________\n")
                            .append(tempTask.markAsIncomplete())
                            .append("\n    ____________________________________________________________\n");
                    taskList.set(taskNumber - 1, tempTask);
                    try {
                        writeToFile(oldTaskInfo, tempTask.getTaskInfo(), taskNumber - 1, pw, sb);
                    } catch (IOException e) {
                        System.out.println("An unexpected error has occurred: " + e.getMessage());
                    }
                } else {
                    sb.append("    ____________________________________________________________\n")
                            .append("    The task you are trying to unmark is out of range! Try again!\n")
                            .append("    ____________________________________________________________\n");
                }
                break;
            case "delete":
                taskNumber = Integer.parseInt(tempText[1]);
                if ((taskNumber <= taskList.size()) && (taskNumber > 0)) {
                    Task tempTask = taskList.remove(taskNumber - 1);
                    sb.append("    ____________________________________________________________\n")
                            .append("    Noted. I've removed this task:\n")
                            .append("      ").append(tempTask.getTaskInfoStatus())
                            .append("\n    Now you have ").append(taskList.size()).append(" tasks in the list.\n")
                            .append("    ____________________________________________________________\n");
                    try {
                        writeToFile(tempTask.getTaskInfo(), "", taskNumber - 1, pw, sb);
                    } catch (IOException e) {
                        System.out.println("An unexpected error has occurred: " + e.getMessage());
                    }
                } else {
                    sb.append("    ____________________________________________________________\n")
                            .append("    The task you are trying to delete is out of range! Try again!\n")
                            .append("    ____________________________________________________________\n");
                }
                break;
            default:    // add new Task into the Reminder list
                Task newTask = null;
                switch (tempCmd) {
                case "todo":
                    newTask = new ToDos(tempText[1]);
                    if (isTaskCompleted) {
                        newTask.markAsDone();
                    }
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

                    String time;
                    String[] tempDateTime = tempText2[1].split(" ");
                    if (tempDateTime.length != 3) {
                        time = "";
                    } else {
                        time = tempDateTime[2];
                    }

                    try {
                        newTask = new Deadline(tempText2[0], tempDateTime[1], time);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid inputs!\n");
                        System.out.println("Please enter your date & time in the format: YYYY-MM-DD HH:MM \n");
                        System.out.println("Please also ensure they are valid values!\n");
                        hasIssue = true;
                        break;
                    }

                    if (isTaskCompleted) {
                        newTask.markAsDone();
                    }
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

                    String startTime;
                    String[] tempStartDateTime = tempText3[1].split(" ");
                    if (tempStartDateTime[2].equals("/to")) {
                        startTime = "";
                    } else {
                        startTime = tempStartDateTime[2];
                    }

                    String[] tempText4 = tempText3[1].split("/to", 2);
                    try {
                        DukeException.validate(true, tempCmd, tempText4);
                    } catch (IncorrectNoOfArgumentException ex) {
                        System.out.println(ex);
                        hasIssue = true;
                        break;
                    }

                    String endTime;
                    String[] tempEndDateTime = tempText4[1].split(" ");
                    if (tempEndDateTime.length != 3) {
                        endTime = "";
                    } else {
                        endTime = tempEndDateTime[2];
                    }

                    try {
                        newTask = new Event(tempText3[0], tempStartDateTime[1], tempEndDateTime[1],
                                startTime, endTime);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid inputs!\n");
                        System.out.println("Please enter your date & time in the format: YYYY-MM-DD HH:MM \n");
                        System.out.println("Please also ensure they are valid values!\n");
                        hasIssue = true;
                        break;
                    }

                    if (isTaskCompleted) {
                        newTask.markAsDone();
                    }
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
                    taskList.add(newTask);
                    if (!isFileData) {  // if current task added to taskList is not from the saved file
                        try {
                            writeToFile("data/storage.txt", newTask.getTaskInfo() + "\n");
                        } catch (IOException e) {
                            System.out.println("An unexpected error has occurred: " + e.getMessage());
                        }
                        sb.append("    ____________________________________________________________\n")
                                .append("    Got it. I've added this task to the list:\n")
                                .append("      ").append(newTask.getTaskInfoStatus())
                                .append("\n    Now you have ").append(taskList.size()).append(" tasks in the list.\n")
                                .append("    ____________________________________________________________\n");
                    }
                }
            }
            if (hasIssue) {
                sb.setLength(0);
                continue;
            }
            if (!isFileData) {  // Prints a message pertaining to the function called by the user
                printMessage(pw, sb);
            }
            if (tempCmd.equals("bye")) {    // Terminate the program as desired by user
                break;
            }
        }
        br.close();
        pw.close();
    }
}

import java.util.Arrays;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Duke {

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static void writeMarkingToFile(String filePath, String mark, int line) throws IOException {
        ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
        String s = fileContent.get(line);
        ArrayList<String> taskDetails = new ArrayList<>(Arrays.asList(s.split(" \\| ")));
        taskDetails.set(1, mark);
        String task = "";
        for (int i = 0; i < taskDetails.size() - 1; i++) {
            task += taskDetails.get(i) + " | ";
        }
        task += taskDetails.get(taskDetails.size() - 1);
        fileContent.set(line, task);
        Files.write(Paths.get(filePath), fileContent, StandardCharsets.UTF_8);
    }

    private static void deleteTaskInFile(String filePath, int line) throws IOException {
        ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
        fileContent.remove(line);
        Files.write(Paths.get(filePath), fileContent, StandardCharsets.UTF_8);
    }

    private static ArrayList<Task>readTaskList(String filePath) throws IOException {
        //Files.createDirectories(Paths.get(filePath));
        File directory = new File("./data");
        if (! directory.exists()){
            directory.mkdir();
        }
        File f = new File(filePath); // create a File for the given file path
        f.createNewFile();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> userTasks = new ArrayList<>();
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] taskDetails = task.split(" \\| ");
            Task loadTask = new Task("err");
            switch(taskDetails[0]){
                case "T": {
                    loadTask = new ToDo(taskDetails[2]);
                    break;
                }
                case "D": {
                    loadTask = new Deadline(taskDetails[1], taskDetails[2]);
                    break;
                }
                case "E": {
                    loadTask = new Event(taskDetails[1], taskDetails[2], taskDetails[3]);
                    break;
                }
            }
            if (taskDetails[1].equals("1")) {
                loadTask.markTask();
            }
            userTasks.add(loadTask);
        }
        return userTasks;
    }

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        ArrayList<Task> userTasks = new ArrayList<>();

        try {
           userTasks = readTaskList("./data/Duke.txt");
        } catch (IOException e) {
            System.out.println("Error reading task list");
        }

        String userInput = input.nextLine();
        while (!userInput.equals("bye"))
        {
            Pattern mark = Pattern.compile("mark [0-9]+");
            Pattern unmark = Pattern.compile("unmark [0-9]+");
            Pattern delete = Pattern.compile("delete [0-9]+");
            Matcher matchMark = mark.matcher(userInput);
            Matcher matchUnmark = unmark.matcher(userInput);
            Matcher matchDelete = delete.matcher(userInput);

            if (userInput.equals("list"))
            {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= userTasks.size(); i++)
                {
                    System.out.println(i + "." + userTasks.get(i - 1));
                }
            }
            else if (matchMark.matches())
            {
                System.out.println("Nice! I've marked this task as done:");
                int idx = Integer.parseInt(userInput.split(" ")[1]);
                userTasks.get(idx - 1).markTask();
                try {
                    writeMarkingToFile("./data/Duke.txt", "1", idx - 1);
                } catch(IOException e) {
                    System.out.println("Error overwriting");
                }
                System.out.println("    " + idx + "." + userTasks.get(idx - 1));
            }
            else if (matchUnmark.matches())
            {
                System.out.println("OK, I've marked this task as not done yet:");
                int idx = Integer.parseInt(userInput.split(" ")[1]);
                userTasks.get(idx - 1).unmarkTask();
                try {
                    writeMarkingToFile("./data/Duke.txt", "0", idx - 1);
                } catch(IOException e) {
                    System.out.println("Error overwriting");
                }
                System.out.println("    " + idx + "." + userTasks.get(idx - 1));
            }
            else if (matchDelete.matches())
            {
                System.out.println("Noted. I've removed this task:");
                int idx = Integer.parseInt(userInput.split(" ")[1]);
                System.out.println("    " + idx + "." + userTasks.get(idx - 1));
                userTasks.remove(idx - 1);
                try {
                    deleteTaskInFile("./data/Duke.txt", idx - 1);
                } catch(IOException e) {
                    System.out.println("Error deleting");
                }
                System.out.println("Now you have " + userTasks.size() + " tasks in the list.");
            }
            else
            {
                Task userTask;
                String[] inputs = userInput.split(" ");
                String taskType = inputs[0];
                switch (taskType) {
                    case "todo": {

                        if (inputs.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        String taskName = userInput.split(" ", 2)[1];
                        userTask = new ToDo(taskName);
                        try {
                            appendToFile("./data/Duke.txt", "T | 0 | " + taskName + "\n");
                        } catch(IOException e) {
                            System.out.println("Error writing to file");
                        }
                        break;
                    }
                    case "deadline": {
                        if (inputs.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] taskNameAndDeadline = userInput.split(" ", 2)[1].split(" /by ");
                        if (taskNameAndDeadline.length < 2) {
                            throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
                        }
                        String taskName = taskNameAndDeadline[0];
                        String deadline = taskNameAndDeadline[1];
                        userTask = new Deadline(taskName, LocalDate.parse(deadline));
                        try {
                            appendToFile("./data/Duke.txt", "D | 0 | " + taskName + " | " + deadline + "\n");
                        } catch(IOException e) {
                            System.out.println("Error writing to file");
                        }
                        break;
                    }
                    case "event": {
                        if (inputs.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        String[] taskNameAndDate = userInput.split(" ", 2)[1].split(" /from ");
                        if (taskNameAndDate.length < 2) {
                            throw new DukeException("☹ OOPS!!! The from date of an event cannot be empty.");
                        }
                        String taskName = taskNameAndDate[0];
                        String[] toAndFrom = taskNameAndDate[1].split(" /to ");
                        if (toAndFrom.length < 2) {
                            throw new DukeException("☹ OOPS!!! The to date of an event cannot be empty.");
                        }
                        String from = toAndFrom[0];
                        String to = toAndFrom[1];
                        userTask = new Event(taskName, LocalDate.parse(from), LocalDate.parse(to));
                        try {
                            appendToFile("./data/Duke.txt", "E | 0 | " + taskName + " | " + from + " | " + to + "\n");
                        } catch(IOException e) {
                            System.out.println("Error writing to file");
                        }
                        break;
                    }
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                userTasks.add(userTask);
                System.out.println("Got it. I've added this task: \n    " + userTask + "\nNow you have " + userTasks.size() + " tasks in the list.");
            }
            userInput = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
    }
}

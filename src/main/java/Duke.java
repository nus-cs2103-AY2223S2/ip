import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;

public class Duke {
    public static void readStorage(String filePath, ArrayList<Task> toDoList) {
        try {
            (new File("./data")).mkdir();
            File savedFile = new File("./data/duke.txt");
            savedFile.createNewFile();
            Scanner scanner = new Scanner(savedFile);
            while (scanner.hasNextLine()) {
                String currentTask = scanner.nextLine();
                String typeOfTask = currentTask.substring(0, 3);
                boolean isMarked = currentTask.substring(3, 6).equals("[X]");
                switch (typeOfTask) {
                    case "[E]":
                        Task newEvent = new Event(currentTask.substring(7));
                        toDoList.add(newEvent);
                        if (isMarked) {
                            newEvent.mark();
                        }
                        break;
                    case "[T]":
                        Task newTodo = new Todo(currentTask.substring(7));
                        toDoList.add(newTodo);
                        if (isMarked) {
                            newTodo.mark();
                        }
                        break;
                    case "[D]":
                        Task newDeadline = new Deadline(currentTask.substring(7));
                        toDoList.add(newDeadline);
                        if (isMarked) {
                            newDeadline.mark();
                        }

                        break;
                }
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("cannot create new file");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("file missing unmarked/marked");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");

        Scanner reader = new Scanner(System.in);
        ArrayList<Task> toDoList = new ArrayList<Task>();

        // store file content in programme first

        readStorage("./data/duke.txt", toDoList);

        while (true) {
            try {
                String input = reader.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("________________________________");
                    break;
                }
                boolean writeToFile = run(input, toDoList);

                if (writeToFile) {
                    FileWriter saveFileWriter = new FileWriter("./data/duke.txt", false);

                    for (int i = 0; i < toDoList.size(); i++) {
                        saveFileWriter.write(toDoList.get(i).toString() + "\n");

                    }

                    saveFileWriter.close();
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println("________________________________");
            } catch (IOException e) {
                System.out.println("failed to store to file");
            }
        }

        reader.close();

    }

    public static boolean checkDescription(String[] splitInput) {

        return splitInput.length < 2;
    }

    public static LocalDate getDate(String[] splitInput) {
        if (splitInput.length >= 4) {
            if (splitInput[splitInput.length - 2].equals("/by")) {
                
                try {
                    // in form yyyy-mm-dd
                    LocalDate taskDateTime = LocalDate
                            .parse(splitInput[splitInput.length - 1]);
                    return taskDateTime;
                } catch (Exception e) {
                    System.out.println("failed to read date");
                }
            }
        }
        return null;
    }

    public static void run(String input, ArrayList<Task> toDoList) throws DukeException {

        String[] splitInput = input.split(" ");
        String command = splitInput[0];

        boolean writeToFile = false;

        System.out.println("________________________________");
        LocalDate taskDate;
        switch (command) {

            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < toDoList.size(); i++) {

                    System.out.println(i + 1 + "." + toDoList.get(i).toString());
                }
                break;

            case "mark":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The value cannot be empty.");
                }

                String taskNumMark = splitInput[1];
                Task taskToMark;
                try {
                    taskToMark = toDoList.get(Integer.parseInt(taskNumMark) - 1);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + taskToMark.mark());

                } catch (NumberFormatException e) {
                    throw new DukeException("Please input an integer");

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please input a valid integer");
                }

                writeToFile = true;
                break;
            case "unmark":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The value cannot be empty.");
                }

                try {
                    String taskNumUnmark = splitInput[1];

                    Task taskToUnmark = toDoList.get(Integer.parseInt(taskNumUnmark) - 1);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(" " + taskToUnmark.unMark());

                } catch (NumberFormatException e) {
                    throw new DukeException("Please input an integer");

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please input a valid integer");
                }
                writeToFile = true;
                break;

            case "event":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }

                taskDate = getDate(splitInput);
                String eventDescription;
                System.out.println(splitInput[1]);

                if (taskDate == null) {
                    eventDescription = input.substring(("event").length() + 1);
                } else {
                    
                    eventDescription = String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length - 2));
                }

                Task newEvent = new Event(eventDescription, taskDate);
                toDoList.add(newEvent);
                System.out.println(" Got it. I've added this task:");
                System.out.println("  " + newEvent.toString());
                System.out.println("Now you have " + toDoList.size() + " tasks on the list.");
                writeToFile = true;
                break;

            case "deadline":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                taskDate = getDate(splitInput);
                String deadlineDescription;
                if (taskDate == null) {
                    deadlineDescription = input.substring(("deadline").length() + 1);
                } else {
                    deadlineDescription = String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length - 2));
                }

                Task newDeadline = new Deadline(deadlineDescription, taskDate);
                toDoList.add(newDeadline);

                System.out.println(" Got it. I've added this task:");
                System.out.println("  " + newDeadline.toString());
                System.out.println("Now you have " + toDoList.size() + " tasks on the list.");
                writeToFile = true;
                break;

            case "todo":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                taskDate = getDate(splitInput);

                String todoDescription;
                if (taskDate == null) {
                    todoDescription = input.substring(("todo").length() + 1);
                } else {
                    todoDescription = String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length - 2));
                }
                Task newTodo = new Todo(todoDescription, taskDate);
                toDoList.add(newTodo);
                System.out.println(" Got it. I've added this task:");
                System.out.println("  " + newTodo.toString());
                System.out.println("Now you have " + toDoList.size() + " tasks on the list.");
                writeToFile = true;
                break;

            case "delete":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                String taskDelete = splitInput[1];

                try {
                    Task taskToDelete = toDoList.get(Integer.parseInt(taskDelete) - 1);
                    toDoList.remove(Integer.parseInt(taskDelete) - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + taskToDelete.toString());
                    System.out.println("Now you have " + toDoList.size() + " tasks on the list.");

                } catch (NumberFormatException e) {
                    throw new DukeException("Please input an integer");

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please input a valid integer");
                }
                writeToFile = true;
                break;

            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

        }

        System.out.println("________________________________");
        return writeToFile;
    }

}

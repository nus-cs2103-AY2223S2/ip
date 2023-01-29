import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String BANNER_LINE = "_".repeat(30);
    private final List<Task> taskList = new ArrayList<>();
    private static final String SAVE_PATH = "./tasklist.txt";

    public static void main(String[] args) {
        String logo = " ,ggg, ,ggggggg,                                                               ,ggg,\n"
                    + "dP\"\"Y8,8P\"\"\"\"\"Y8b              ,dPYb,                                         dP\"\"8I\n"
                    + "Yb, `8dP'     `88              IP'`Yb                                        dP   8\n"
                    + " `\"  88'       88              I8  8I      gg               gg              dP    88\n"
                    + "     88        88              I8  8bgg,   \"\"               \"\"             ,8'    88\n"
                    + "     88        88    ,gggg,gg  I8 dP\" \"8   gg    ,gggggg,   gg             d88888888   gg     gg    ,gggg,gg   ,ggg,,ggg,,ggg,    ,ggg,\n"
                    + "     88        88   dP\"  \"Y8I  I8d8bggP\"   88    dP\"\"\"\"8I   88       __   ,8\"     88   I8     8I   dP\"  \"Y8I  ,8\" \"8P\" \"8P\" \"8,  i8\" \"8i\n"
                    + "     88        88  i8'    ,8I  I8P' \"Yb,   88   ,8'    8I   88      dP\"  ,8P      Y8   I8,   ,8I  i8'    ,8I  I8   8I   8I   8I  I8, ,8I\n"
                    + "     88        Y8,,d8,   ,d8b,,d8    `Yb,_,88,_,dP     Y8,_,88,_    Yb,_,dP       `8b,,d8b, ,d8I ,d8,   ,d8b,,dP   8I   8I   Yb, `YbadP'\n"
                    + "     88        `Y8P\"Y8888P\"`Y888P      Y88P\"\"Y88P      `Y88P\"\"Y8     \"Y8P\"         `Y8P\"\"Y88P\"888P\"Y8888P\"`Y88P'   8I   8I   `Y8888P\"Y888\n"
                    + "                                                                                            ,d8I'\n"
                    + "                                                                                          ,dP'8I\n"
                    + "                                                                                         ,8\"  8I\n"
                    + "                                                                                         I8   8I\n"
                    + "                                                                                         `8, ,8I\n"
                    + "                                                                                          `Y8P\"\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        greet();
        loadTasklistFromFile();
        commandLoop();
    }

    public void greet() {
        printInBanner("Greetings humans~\n'Tis I! Nakiri Ayame!\nWhat can I do for you?");
    }

    public void commandLoop() {
        Scanner input = new Scanner(System.in);
        String cmd;
        boolean acceptingInputs = true;
        while (acceptingInputs) {
            cmd = input.nextLine();
            List<String> args = List.of(cmd.split(" "));
            if (args.size() == 0) {
                continue;
            }

            String cmdVerb = args.get(0);
            Task newTask = null;
            switch (cmdVerb) {
                case "bye":
                    acceptingInputs = false;
                    break;
                case "list":
                    printTasks();
                    break;
                case "mark":
                    try {
                        changeTaskStatus(args, true);
                        saveTasklistToFile();
                    } catch (InvalidIndexException e) {
                        printErrorMessage(ErrorEnum.INVALID_INDEX);
                    } catch (IllegalArgumentException e) {
                        printErrorMessage(ErrorEnum.ILLEGAL_ARGUMENT);
                    }
                    break;
                case "unmark":
                    try {
                        changeTaskStatus(args, false);
                        saveTasklistToFile();
                    } catch (InvalidIndexException e) {
                        printErrorMessage(ErrorEnum.INVALID_INDEX);
                    } catch (IllegalArgumentException e) {
                        printErrorMessage(ErrorEnum.ILLEGAL_ARGUMENT);
                    }
                    break;
                case "todo":
                    try {
                        newTask = addTodo(args.subList(1, args.size()));
                        saveTasklistToFile();
                    } catch (EmptyBodyException e) {
                        printErrorMessage(ErrorEnum.EMPTY_BODY);
                    }

                    break;
                case "deadline":
                    try {
                        newTask = addDeadline(args.subList(1, args.size()));
                        saveTasklistToFile();
                    } catch (EmptyBodyException e) {
                        printErrorMessage(ErrorEnum.EMPTY_BODY);
                    } catch (IllegalArgumentException e) {
                        printErrorMessage(ErrorEnum.ILLEGAL_ARGUMENT);
                    }

                    break;
                case "event":
                    try {
                        newTask = addEvent(args.subList(1, args.size()));
                        saveTasklistToFile();
                    } catch (EmptyBodyException e) {
                        printErrorMessage(ErrorEnum.EMPTY_BODY);
                    } catch (IllegalArgumentException e) {
                        printErrorMessage(ErrorEnum.ILLEGAL_ARGUMENT);
                    }
                    break;
                case "delete":
                    try {
                        deleteTask(args);
                        saveTasklistToFile();
                    } catch (InvalidIndexException e) {
                        printErrorMessage(ErrorEnum.INVALID_INDEX);
                    } catch (IllegalArgumentException e) {
                        printErrorMessage(ErrorEnum.ILLEGAL_ARGUMENT);
                    }
                    break;
                default:
                    printErrorMessage(ErrorEnum.UNKNOWN_INPUT);
                    break;
            }
            if (newTask != null) {
                printInBanner("Don't forget to do this task yo~\n  " + newTask + getTasklistSize());
            }
        }
        printInBanner("Otsunakiri~\nByebye!~");
    }

    private void printInBanner(String message) {
        System.out.println(BANNER_LINE);
        System.out.println(message);
        System.out.println(BANNER_LINE);
    }

    private void printTasks() {
        StringBuilder toPrint = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (i != 0) {
                toPrint.append("\n");
            }
            toPrint.append(i + 1).append(": ").append(taskList.get(i));
        }
        printInBanner(toPrint.toString());
    }

    private Task addTodo(List<? extends String> args) throws EmptyBodyException {
        if (args.size() == 0) {
            throw new EmptyBodyException();
        }

        String cmd = String.join(" ", args);
        Todo newTask = new Todo(cmd);
        taskList.add(newTask);
        return newTask;
    }

    private Task addDeadline(List<? extends String> args) throws IllegalArgumentException {
        int byIndex = args.indexOf("/by");
        if (byIndex == -1 || byIndex == args.size() - 1) {
            throw new IllegalArgumentException();
        }

        if (byIndex == 0) {
            throw new EmptyBodyException();
        }

        @SuppressWarnings("unchecked")  // args already takes in objects that are subclasses of String, so it is a safe typecast
        List<String> description = (List<String>) args.subList(0, byIndex);
        @SuppressWarnings("unchecked")
        List<String> byDate = (List<String>) args.subList(byIndex + 1, args.size());
        Deadline newTask = new Deadline(String.join(" ", description), String.join(" ", byDate));
        taskList.add(newTask);
        return newTask;
    }

    private Task addEvent(List<? extends String> args) throws IllegalArgumentException {
        int fromIndex = args.indexOf("/from");
        int toIndex = args.indexOf("/to");
        if (fromIndex == -1 || fromIndex == args.size() - 1 || toIndex == -1 || toIndex == args.size() - 1) {
            throw new IllegalArgumentException();
        }

        if (fromIndex == 0 || toIndex == 0) {
            throw new EmptyBodyException();
        }

        Event newTask;
        if (fromIndex < toIndex) {
            if (fromIndex + 1 == toIndex) {
                throw new EmptyBodyException();
            }
            @SuppressWarnings("unchecked")  // args already takes in objects that are subclasses of String so it is a safe typecast
            List<String> description = (List<String>) args.subList(0, fromIndex);
            @SuppressWarnings("unchecked")
            List<String> fromDate = (List<String>) args.subList(fromIndex + 1, toIndex);
            @SuppressWarnings("unchecked")
            List<String> toDate = (List<String>) args.subList(toIndex + 1, args.size());

            newTask = new Event(String.join(" ", description), String.join(" ", fromDate), String.join(" ", toDate));
        } else {
            if (toIndex + 1 == fromIndex) {
                throw new EmptyBodyException();
            }
            @SuppressWarnings("unchecked")
            List<String> description = (List<String>) args.subList(0, toIndex);
            @SuppressWarnings("unchecked")
            List<String> toDate = (List<String>) args.subList(toIndex + 1, fromIndex);
            @SuppressWarnings("unchecked")
            List<String> fromDate = (List<String>) args.subList(fromIndex + 1, args.size());

            newTask = new Event(String.join(" ", description), String.join(" ", fromDate), String.join(" ", toDate));
        }

        taskList.add(newTask);
        return newTask;
    }

    private void changeTaskStatus(List<? extends String> args, boolean done) throws InvalidIndexException {
        if (args.size() != 2) {
            throw new IllegalArgumentException();
        }

        int idx;
        try {
            idx = Integer.parseInt(args.get(1)) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }

        if (idx < 0 || idx >= taskList.size()) {
            throw new InvalidIndexException();
        }

        if (done) {
            taskList.get(idx).markAsDone();
            printInBanner("Yatta! You have done this task!\n" + taskList.get(idx));
        } else {
            taskList.get(idx).unmarkAsDone();
            printInBanner("Neee! Are you kidding me?\n" + taskList.get(idx));
        }
    }

    private void deleteTask(List<? extends String> args) {
        if (args.size() != 2) {
            throw new IllegalArgumentException();
        }

        int idx;
        try {
            idx = Integer.parseInt(args.get(1)) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }

        if (idx < 0 || idx >= taskList.size()) {
            throw new InvalidIndexException();
        }

        Task task = taskList.remove(idx);
        printInBanner("Don't need this trash anymore yo~\n" + task + getTasklistSize());
    }

    private void printErrorMessage(ErrorEnum e) {
        switch (e) {
            case INVALID_INDEX:
                printInBanner("Can't do that yo~\nInvalid index supplied~");
                break;
            case EMPTY_BODY:
                printInBanner("What do you wanna do yo~");
                break;
            case FILE_SAVE_ERROR:
                printInBanner("Yabai! Could not save to file dayo...");
                break;
            case FILE_LOAD_ERROR:
                printInBanner("Yabai! Could not load from file dayo... dousyou...");
            case ILLEGAL_ARGUMENT:
            default:
                printInBanner("Wakandeyo!!! >:(");
                break;
        }
    }

    private String getTasklistSize() {
        return "\nNow you have " + taskList.size() + " items on your list.";
    }

    private void saveTasklistToFile() {
        Path file = Paths.get(SAVE_PATH);
        try {
            Files.deleteIfExists(file);
            Files.createFile(file);
        } catch (IOException e) {
            printErrorMessage(ErrorEnum.FILE_SAVE_ERROR);
        }

        for (Task task : taskList) {
            try {
                Files.writeString(file, task.getFileRepresentation() + System.lineSeparator(),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("Error when writing task " + task + " to file");
            }
        }
    }

    private void loadTasklistFromFile() {
        Path file = Paths.get(SAVE_PATH);
        if (!Files.exists(file)) {
            return;
        }

        List<String> tasks;
        try {
            tasks = Files.readAllLines(file);
        } catch (IOException e) {
            printErrorMessage(ErrorEnum.FILE_LOAD_ERROR);
            return;
        }

        for (String str: tasks) {
            List<String> args = List.of(str.split(" "));
            if (args.size() < 2) {
                continue;
            }

            String taskType = args.get(0);
            String isDone = args.get(1);
            Task task = null;
            switch (taskType) {
                case "todo":
                    try {
                        task = addTodo(args.subList(2, args.size()));
                    } catch (EmptyBodyException e) {
                        printErrorMessage(ErrorEnum.EMPTY_BODY);
                    }

                    break;
                case "deadline":
                    try {
                        task = addDeadline(args.subList(2, args.size()));
                    } catch (EmptyBodyException e) {
                        printErrorMessage(ErrorEnum.EMPTY_BODY);
                    } catch (IllegalArgumentException e) {
                        printErrorMessage(ErrorEnum.ILLEGAL_ARGUMENT);
                    }

                    break;
                case "event":
                    try {
                        task = addEvent(args.subList(2, args.size()));
                    } catch (EmptyBodyException e) {
                        printErrorMessage(ErrorEnum.EMPTY_BODY);
                    } catch (IllegalArgumentException e) {
                        printErrorMessage(ErrorEnum.ILLEGAL_ARGUMENT);
                    }
                    break;
                default:
                    printErrorMessage(ErrorEnum.UNKNOWN_INPUT);
            }

            if (isDone.equals("true") && task != null) {
                task.markAsDone();
            }
        }
    }
}

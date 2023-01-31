import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;
import util.Either;
import util.Pair;
import util.Util;

public class Engine {
    private final Scanner scanner;
    private final TaskList taskList;
    private static final String DIRECTORY = "./data";
    private static final String FILE_NAME = "taskList.dat";
    private static final String FILE_PATH = DIRECTORY + "/" + FILE_NAME;


    private enum Command {
        EXIT,
        LIST,
        ADD,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND_DATE,

        // errors
        ERROR
    }

    Engine() {
        this.scanner = new Scanner(System.in);
        Either<TaskList, String> temp = this.getLocalData();
        if (temp.isRight()) {
            System.out.println(temp.fromRight("Default error.\n"));
            this.taskList = new TaskList();
        } else {
            this.taskList = temp.fromLeft(new TaskList());
        }
    }

    private Either<TaskList, String> getLocalData() {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            try {
                TaskList taskList = (TaskList) inputStream.readObject();
                inputStream.close();
                return Either.left(taskList);
            } catch (ClassNotFoundException ex) {
                inputStream.close();
                return Either.right("Error reading data, tasks has been reset.\n");
            }
        } catch (FileNotFoundException ex) {
            return Either.left(new TaskList());
        } catch (IOException ex) {
            return Either.right("Error reading data, tasks has been reset.\n");
        }
    }

    private String getOutput(Command command, String args) {
        switch (command) {
            case ERROR: {
                return args;
            }
            case EXIT: {
                ObjectOutputStream outputStream = null;
                while (true) {
                    try {
                        outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
                        outputStream.writeObject(this.taskList);
                        outputStream.close();
                        return "Tasks saved successfully. Bye.\n";
                    } catch (FileNotFoundException ex) {
                        new File(DIRECTORY).mkdir();
                    } catch (IOException ex) {
                        return "Error saving tasks. Bye.\n";
                    }
                }
            }
            case LIST: {
                if (this.taskList.isEmpty()) {
                    return "You have no tasks.\n";
                }
                return "You have these tasks:" + this.taskList.toString();
            }
            case TODO: {
                String task = Util.cleanup(args);
                if (task.isEmpty()) {
                    return "Task input required.\n";
                }
                this.taskList.addTask(new Todo(Util.cleanup(args)));
                return "added-> " + this.taskList.getLast();
            }
            case DEADLINE: {
                String flag = "/by";
                int start = args.indexOf(flag);
                if (start == -1) {
                    return "/by flag required for deadlines.\n";
                }
                String desc = Util.cleanup(args.substring(0, start));
                String dl = Util.cleanup(args.substring(start + flag.length(), args.length()));

                if (desc.isEmpty() || dl.isEmpty()) {
                    return "Some inputs are blank.\n";
                }
                LocalDate deadline = null;
                try {
                    deadline = LocalDate.parse(dl);
                } catch (DateTimeParseException ex) {
                    return "Please input date in the following format: YYYY-MM-DD\n";
                }

                this.taskList.addTask(new Deadline(desc, deadline));
                return "added-> " + this.taskList.getLast();
            }
            case EVENT: {
                String flag1 = "/from";
                String flag2 = "/to";
                int s1 = args.indexOf(flag1);
                int s2 = args.indexOf(flag2);
                if (s1 == -1 || s2 == -1) {
                    return "/from and /to flag required for events.\n";
                }
                String event = Util.cleanup(args.substring(0, s1));
                String from = Util.cleanup(args.substring(s1 + flag1.length(), s2));
                String to = Util.cleanup(args.substring(s2 + flag2.length(), args.length()));

                if (event.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    return "Some inputs are blank.\n";
                }
            
                LocalDate start = null;
                LocalDate end = null;
                try {
                    start = LocalDate.parse(from);
                    end = LocalDate.parse(to);
                } catch (DateTimeParseException ex) {
                    return "Please input date in the following format: YYYY-MM-DD\n";
                }

                this.taskList.addTask(new Event(event, start, end));
                return "added-> " + this.taskList.getLast();
            }
            case MARK: {
                try {
                    int num = Integer.parseInt(Util.parseNextString(args).first());
                    if (num > this.taskList.size()) {
                        return "Task not in list.\n";
                    }
                    this.taskList.markTask(num);
                    return "Task marked completed:" +
                            this.taskList.get(num);
                } catch (NumberFormatException ex) {
                    return "Incorrect input.\n";
                }
            }
            case UNMARK: {
                try {
                    int num = Integer.parseInt(Util.parseNextString(args).first());
                    if (num > this.taskList.size()) {
                        return "Task not in list.\n";
                    }
                    this.taskList.unmarkTask(num);
                    return "Task marked incomplete:" +
                            this.taskList.get(num);
                } catch (NumberFormatException ex) {
                    return "Incorrect input.\n";
                }
            }
            case DELETE: {
                try {
                    int num = Integer.parseInt(Util.parseNextString(args).first());
                    if (num > this.taskList.size()) {
                        return "Task not in list.\n";
                    }
                    String out = "Task has been deleted:" +
                            this.taskList.get(num) +
                            "\nTasks remaining: ";
                    this.taskList.deleteTask(num);
                    return out + this.taskList.toString();
                } catch (NumberFormatException ex) {
                    return "Incorrect input.\n";
                }

            }
            case FIND_DATE: {
                try {
                    LocalDate date = LocalDate.parse(Util.cleanup(args));
                    return this.taskList.findByDate(date);
                } catch (DateTimeParseException ex) {
                    return "Please input date in the following format: YYYY-MM-DD\n";
                }
            }

            default:
                return "Case not accounted for, review code\n";
            // for debugging
        }
    }

    private Pair<Command, String> parseCommand(String line) {
        Pair<String, String> pr = Util.parseNextString(line);
        String command = pr.first().toLowerCase();
        String rest = pr.second();

        if (command.isEmpty()) {
            return new Pair<>(Command.ERROR, "No input detected.\n");
        }
        if (command.equals("bye")) {
            return new Pair<>(Command.EXIT, "");
        }
        if (command.equals("list")) {
            return new Pair<>(Command.LIST, "");
        }
        if (command.equals("mark")) {
            return new Pair<>(Command.MARK, rest);
        }
        if (command.equals("unmark")) {
            return new Pair<>(Command.UNMARK, rest);
        }
        if (command.equals("todo")) {
            return new Pair<>(Command.TODO, rest);
        }
        if (command.equals("event")) {
            return new Pair<>(Command.EVENT, rest);
        }
        if (command.equals("deadline")) {
            return new Pair<>(Command.DEADLINE, rest);
        }
        if (command.equals("delete")) {
            return new Pair<>(Command.DELETE, rest);
        }
        if (command.equals("findbydate")) {
            return new Pair<>(Command.FIND_DATE, rest);
        }
        return new Pair<>(Command.ERROR, "Unknown command.\n");
    }

    public boolean run() {
        String line = this.scanner.nextLine();
        Pair<Command, String> pr = this.parseCommand(line);
        System.out.println("\nD: " + this.getOutput(pr.first(), pr.second()));
        if (pr.first().equals(Command.EXIT)) {
            return false;
        }
        return true;
    }
}

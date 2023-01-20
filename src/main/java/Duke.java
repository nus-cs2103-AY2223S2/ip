
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ / _   _| | _____ \n"
                + "| | | | | | | |/ / _ /\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ /__,_|_|/_/___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);

        printer(Views.WELCOME_STRING.eng());

        ArrayList<Task> tasksList = load();

        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                boolean end = false;
                switch (input) {
                    case "list":
                        if (tasksList.size() == 0) {
                            printer(Views.EMPTY_LIST_STRING.eng());
                        } else {
                            String toPrint = "";
                            for (int i = 0; i < tasksList.size(); i++) {
                                toPrint += ((i + 1) + "." + tasksList.get(i)) + "\n      ";
                            }
                            printer(toPrint.substring(0, toPrint.length() - 7));
                        }
                        break;
                    case "clear":
                        tasksList.clear();
                        printer(Views.CLEAR_LIST_STRING.eng());
                        break;
                    case "bye":
                        end = true;
                        printer(Views.END_STRING.eng());
                        break;
                    case "exit":
                        end = true;
                        printer(Views.END_STRING.eng());
                        break;
                    default:
                        // Have to do it at the starts with because what if "todo mark this as done"
                        if (input.startsWith(Commands.MARK.cmd())
                                || input.startsWith(Commands.UNMARK.cmd())) {
                            int taskNo = getNumbers(input) - 1;
                            if (input.startsWith(Commands.UNMARK.cmd())) {
                                tasksList.get(taskNo).unmarkAsDone();
                                printer(Views.UNMARK_DONE_STRING.eng() + tasksList.get(taskNo));
                            } else {
                                tasksList.get(taskNo).markAsDone();
                                printer(Views.MARK_DONE_STRING.eng() + tasksList.get(taskNo));
                            }
                        } else if (input.startsWith(Commands.TODO.cmd())) {
                            String title = input.substring(Commands.TODO.cmd().length());
                            Task newTask = new Todo(title);
                            tasksList.add(newTask);
                            printer("added: " + newTask);
                        } else if (input.startsWith(Commands.DEADLINE.cmd())) {
                            if (input.indexOf(Commands.BY.cmd()) == -1) {
                                throw new DukeException(Views.MISSING_ARGS_ERR_STRING.eng());
                            }
                            String title = input.substring(Commands.DEADLINE.cmd().length(),
                                    input.indexOf(Commands.BY.cmd()));
                            Task newTask = new Deadline(title,
                                    input.substring(input.indexOf(Commands.BY.cmd())));
                            tasksList.add(newTask);
                            printer("added: " + newTask);
                        } else if (input.startsWith(Commands.EVENT.cmd())) {
                            if (input.indexOf(Commands.FROM.cmd()) == -1 || input.indexOf(Commands.TO.cmd()) == -1) {
                                throw new DukeException(Views.MISSING_ARGS_ERR_STRING.eng());
                            }
                            String title = input.substring(Commands.EVENT.cmd().length(),
                                    input.indexOf(Commands.FROM.cmd()));
                            Task newTask = new Event(title,
                                    input.substring(input.indexOf(Commands.FROM.cmd()),
                                            input.indexOf(Commands.TO.cmd())),
                                    input.substring(input.indexOf(Commands.TO.cmd())));
                            tasksList.add(newTask);
                            printer("added: " + newTask);
                        } else if (input.startsWith(Commands.DELETE.cmd())
                                || input.startsWith(Commands.DEL.cmd())) {
                            int taskNo = getNumbers(input) - 1;
                            String returnString = Views.DELETE_DONE_STRING.eng();
                            returnString += tasksList.get(taskNo);
                            tasksList.remove(taskNo);
                            returnString += "\n      ";
                            returnString += Views.TASK_COUNT_1_STRING.eng();
                            returnString += tasksList.size();
                            returnString += Views.TASK_COUNT_2_STRING.eng();
                            printer(returnString);
                        } else {
                            throw new DukeException(Views.UNKNOWN_CMD_ERR_STRING.eng());
                        }
                        break;
                }
                if (end) {
                    break;
                }
                save(tasksList);
            } catch (Exception e) {
                // System.out.println(e);
                if (e instanceof IndexOutOfBoundsException) {
                    printer(Views.OUT_RANGE_ERR_STRING.eng());
                } else if (e instanceof java.time.format.DateTimeParseException) {
                    printer(Views.DATE_PARSE_ERR_STRING.eng());
                } else if (e instanceof DukeException) {
                    printer(e.getMessage());
                } else {
                    printer(Views.UNKNOWN_ERR_STRING.eng());
                }
            }
        }
        sc.close();
    }

    private static void printer(String toPrint) {
        System.out.println("    " + Views.LINE_STRING.eng());
        System.out.println("      " + toPrint);
        System.out.println("    " + Views.LINE_STRING.eng());
        System.out.println();
    }

    private static int getNumbers(String input) throws DukeException {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String numberString = matcher.group();
            int number = Integer.parseInt(numberString);
            return number;
        } else {
            throw new DukeException(Views.NO_INT_ERR_STRING.eng());
        }
    }

    private static void save(ArrayList<Task> tasksList) {
        File dukeData = new File("duke_data.txt");
        try (PrintWriter csvWriter = new PrintWriter(new FileWriter(dukeData));) {
            for (Task item : tasksList) {
                csvWriter.println(item.toString()
                        .replace("[T]", "todo")
                        .replace("[D]", "deadline")
                        .replace("(by:", "/by")
                        .replace("[E]", "event")
                        .replace("(from:", "/from")
                        .replace("to:", "/to")
                        .replace(")", ""));
            }
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    private static ArrayList<Task> load() {
        ArrayList<Task> tasksList = new ArrayList<Task>(100);
        try {
            Scanner scanner = new Scanner(new File("duke_data.txt"));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                boolean done = line.indexOf("[X]") == -1 ? false : true;
                line.replace("[ ]", "");
                line.replace("[X]", "");
                if (line.startsWith(Commands.TODO.cmd())) {
                    String title = line.substring(Commands.TODO.cmd().length());
                    Task newTask = new Todo(title, done);
                    tasksList.add(newTask);
                } else if (line.startsWith(Commands.DEADLINE.cmd())) {
                    if (line.indexOf(Commands.BY.cmd()) == -1) {
                        throw new DukeException(Views.MISSING_ARGS_ERR_STRING.eng());
                    }
                    String title = line.substring(Commands.DEADLINE.cmd().length(),
                            line.indexOf(Commands.BY.cmd()));
                    Task newTask = new Deadline(title,
                            line.substring(line.indexOf(Commands.BY.cmd())), done);
                    tasksList.add(newTask);
                } else if (line.startsWith(Commands.EVENT.cmd())) {
                    if (line.indexOf(Commands.FROM.cmd()) == -1 || line.indexOf(Commands.TO.cmd()) == -1) {
                        throw new DukeException(Views.MISSING_ARGS_ERR_STRING.eng());
                    }
                    String title = line.substring(Commands.EVENT.cmd().length(),
                            line.indexOf(Commands.FROM.cmd()));
                    Task newTask = new Event(title,
                            line.substring(line.indexOf(Commands.FROM.cmd()),
                                    line.indexOf(Commands.TO.cmd())),
                            line.substring(line.indexOf(Commands.TO.cmd())), done);
                    tasksList.add(newTask);
                }
            }
            scanner.close();
        } catch (Exception e) {
            // System.out.println(e);
        }
        return tasksList;
    }
}

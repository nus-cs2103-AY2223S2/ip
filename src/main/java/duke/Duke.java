package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Represents Duke itself, the Chat bot.
 */
public class Duke {

    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath File path to store/retrieve previous task list.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        ui = new Ui();
    }

    /**
     * Adds the current TaskList into a specified file for storage purposes.
     *
     * @param taskList List of Tasks to be saved.
     * @throws IOException If specified file does not exist.
     */
    public void saveTaskList(List<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(storage.getFilePath());
        for (Task a : taskList) {
            fw.write(a.getDataToSave() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Returns the response from Duke based on user input.
     *
     * @param userInput User input.
     * @return Response from Duke.
     */
    public String getResponse(String userInput) {
        Parser parser = new Parser();
        String command = parser.getCommand(userInput);
        StringBuilder response = new StringBuilder();
        switch (command) {
        case "mark":
            try {
                int index = Integer.parseInt(parser.getCommandDetails(userInput)) - 1;
                tasks = tasks.set(index, tasks.get(index).markAsDone());
                response.append("nice! i've marked this task as done:\n").append(tasks.get(index));
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                response.append(new DukeException("pls specify an existing task number in the task list!\n"
                        + "use the format: mark [task number]"));
            }
            break;
        case "unmark":
            try {
                int index = Integer.parseInt(parser.getCommandDetails(userInput)) - 1;
                tasks = tasks.set(index, tasks.get(index).unmarkAsDone());
                response.append("ok, i've marked this task as not done yet:\n").append(tasks.get(index));
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                response.append(new DukeException("pls specify an existing task number in the task list!\n"
                        + "use the format: unmark [task number]"));
            }
            break;
        case "bye":
            response.append(ui.bye());
            break;
        case "list":
            // Fallthrough
        case "l":
            response.append(tasks);
            break;
        case "todo":
            // Fallthrough
        case "t":
            try {
                Todo newTodo = new Todo(parser.getCommandDetails(userInput));
                tasks = tasks.add(newTodo);
                response.append("i've added this task:\n").append(newTodo).append("\n");
                if (tasks.size() == 1) {
                    response.append("you have 1 task in the list");
                } else {
                    response.append("you have ").append(tasks.size()).append(" tasks in the list");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                response.append(new DukeException("pls add a description for this task!"));
            }
            break;
        case "deadline":
            // Fallthrough
        case "d":
            try {
                String deadlineStr = parser.getDeadline(userInput);
                String[] split = deadlineStr.split(" ");
                LocalDate deadlineDate = LocalDate.parse(split[0]);
                Optional<LocalTime> deadlineTime;
                if (split.length > 1) {
                    // Time is specified by user
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
                    deadlineTime = Optional.of(LocalTime.parse(split[1], formatter));
                } else {
                    deadlineTime = Optional.empty();
                }
                Deadline newDeadline = new Deadline(parser.getDeadlineDesc(userInput),
                        parser.getDeadline(userInput));
                tasks = tasks.add(newDeadline);
                response.append("i've added this task with deadline:\n").append(newDeadline).append("\n");
                if (tasks.size() == 1) {
                    response.append("you have 1 task in the list");
                } else {
                    response.append("you have ").append(tasks.size()).append(" tasks in the list");
                }
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException ex) {
                response.append(new DukeException("pls specify the description and deadline for this task!\n"
                        + "use the format: deadline [description] /by yyyy-MM-dd HHmm"));
            }
            break;
        case "event":
            // Fallthrough
        case "e":
            try {
                String eventFromStr = parser.getEventFrom(userInput);
                String[] splitFrom = eventFromStr.split(" ");
                LocalDate eventFromDate = LocalDate.parse(splitFrom[0]);
                Optional<LocalTime> eventFromTime;
                if (splitFrom.length > 1) {
                    // Time is specified by user
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
                    eventFromTime = Optional.of(LocalTime.parse(splitFrom[1], formatter));
                } else {
                    eventFromTime = Optional.empty();
                }

                String eventToStr = parser.getEventTo(userInput);
                String[] splitTo = eventToStr.split(" ");
                LocalDate eventToDate = LocalDate.parse(splitTo[0]);
                Optional<LocalTime> eventToTime;
                if (splitTo.length > 1) {
                    // Time is specified by user
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
                    eventToTime = Optional.of(LocalTime.parse(splitTo[1], formatter));
                } else {
                    eventToTime = Optional.empty();
                }

                Event newEvent = new Event(parser.getEventDesc(userInput),
                        parser.getEventFrom(userInput), parser.getEventTo(userInput));
                tasks = tasks.add(newEvent);
                response.append("i've added this event:\n").append(newEvent).append("\n");
                if (tasks.size() == 1) {
                    response.append("you have 1 task in the list");
                } else {
                    response.append("you have ").append(tasks.size()).append(" tasks in the list");
                }
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException ex) {
                response.append(new DukeException("pls specify the description and duration for this event!\n"
                        + "use the format: event [description] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm"));
            }
            break;
        case "delete":
            try {
                int index = Integer.parseInt(parser.getCommandDetails(userInput)) - 1;
                response.append("i've removed this task:\n").append(tasks.get(index)).append("\n");
                tasks = tasks.remove(index);
                if (tasks.size() == 1) {
                    response.append("you have 1 task in the list");
                } else {
                    response.append("you have ").append(tasks.size()).append(" tasks in the list");
                }
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                response.append(new DukeException("pls specify an existing task number in the task list!\n"
                        + "use the format: delete [task number]"));
            }
            break;
        case "find":
            // Fallthrough
        case "f":
            try {
                TaskList taskListKeyword = tasks.findKeyword(parser.getCommandDetails(userInput));
                if (taskListKeyword.size() == 0) {
                    response.append("there are no matching tasks in your list");
                } else {
                    response.append("here are the matching items in your list:\n").append(taskListKeyword);
                }
            } catch (IndexOutOfBoundsException ex) {
                response.append(new DukeException("pls specify a keyword to find!\n"
                        + "use the format: find [keyword]"));
            }
            break;
        default:
            response.append(new DukeException("invalid command"));
        }
        try {
            saveTaskList(tasks.getTasks());
        } catch (IOException e) {
            response.append("Something went wrong!");
        }
        return response.toString();
    }

    /**
     * Takes in user input and addresses it accordingly. Uses CLI.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        ui.showWelcome();

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String command = parser.getCommand(userInput);
            switch (command) {
            case "mark":
                try {
                    int index = Integer.parseInt(parser.getCommandDetails(userInput)) - 1;
                    tasks = tasks.set(index, tasks.get(index).markAsDone());
                    ui.showLine();
                    System.out.println("nice! i've marked this task as done:\n"
                            + tasks.get(index));
                    ui.showLine();
                } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                    ui.showLine();
                    ui.showException(new DukeException("pls specify an existing task number in the task list!\n"
                            + "use the format: mark [task number]"));
                    ui.showLine();
                }
                break;
            case "unmark":
                try {
                    int index = Integer.parseInt(parser.getCommandDetails(userInput)) - 1;
                    tasks = tasks.set(index, tasks.get(index).unmarkAsDone());
                    ui.showLine();
                    System.out.println("ok, i've marked this task as not done yet:\n"
                            + tasks.get(index));
                    ui.showLine();
                } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                    ui.showLine();
                    ui.showException(new DukeException("pls specify an existing task number in the task list!\n"
                            + "use the format: unmark [task number]"));
                    ui.showLine();
                }
                break;
            case "bye":
                ui.showBye();
                System.exit(0);
                break;
            case "list":
                // Fallthrough
            case "l":
                ui.showLine();
                System.out.println(tasks);
                ui.showLine();
                break;
            case "todo":
                // Fallthrough
            case "t":
                try {
                    Todo newTodo = new Todo(parser.getCommandDetails(userInput));
                    tasks = tasks.add(newTodo);
                    ui.showLine();
                    System.out.println("i've added this task:\n" + newTodo);
                    if (tasks.size() == 1) {
                        System.out.println("you have 1 task in the list");
                    } else {
                        System.out.println("you have " + tasks.size() + " tasks in the list");
                    }
                    ui.showLine();
                } catch (ArrayIndexOutOfBoundsException ex) {
                    ui.showLine();
                    ui.showException(new DukeException("pls add a description for this task!"));
                    ui.showLine();
                }
                break;
            case "deadline":
                // Fallthrough
            case "d":
                try {
                    String deadlineStr = parser.getDeadline(userInput);
                    String[] split = deadlineStr.split(" ");
                    LocalDate deadlineDate = LocalDate.parse(split[0]);
                    Optional<LocalTime> deadlineTime;
                    if (split.length > 1) {
                        // Time is specified by user
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
                        deadlineTime = Optional.of(LocalTime.parse(split[1], formatter));
                    } else {
                        deadlineTime = Optional.empty();
                    }
                    Deadline newDeadline = new Deadline(parser.getDeadlineDesc(userInput),
                            parser.getDeadline(userInput));
                    tasks = tasks.add(newDeadline);
                    ui.showLine();
                    System.out.println("i've added this task with deadline:\n" + newDeadline);
                    if (tasks.size() == 1) {
                        System.out.println("you have 1 task in the list");
                    } else {
                        System.out.println("you have " + tasks.size() + " tasks in the list");
                    }
                    ui.showLine();
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException ex) {
                    ui.showLine();
                    ui.showException(new DukeException("pls specify the description and deadline for this task!\n"
                            + "use the format: deadline [description] /by yyyy-MM-dd HHmm"));
                    ui.showLine();
                }
                break;
            case "event":
                // Fallthrough
            case "e":
                try {
                    String eventFromStr = parser.getEventFrom(userInput);
                    String[] splitFrom = eventFromStr.split(" ");
                    LocalDate eventFromDate = LocalDate.parse(splitFrom[0]);
                    Optional<LocalTime> eventFromTime;
                    if (splitFrom.length > 1) {
                        // Time is specified by user
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
                        eventFromTime = Optional.of(LocalTime.parse(splitFrom[1], formatter));
                    } else {
                        eventFromTime = Optional.empty();
                    }

                    String eventToStr = parser.getEventTo(userInput);
                    String[] splitTo = eventToStr.split(" ");
                    LocalDate eventToDate = LocalDate.parse(splitTo[0]);
                    Optional<LocalTime> eventToTime;
                    if (splitTo.length > 1) {
                        // Time is specified by user
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
                        eventToTime = Optional.of(LocalTime.parse(splitTo[1], formatter));
                    } else {
                        eventToTime = Optional.empty();
                    }

                    Event newEvent = new Event(parser.getEventDesc(userInput),
                            parser.getEventFrom(userInput), parser.getEventTo(userInput));
                    tasks = tasks.add(newEvent);
                    ui.showLine();
                    System.out.println("i've added this event:\n" + newEvent);
                    if (tasks.size() == 1) {
                        System.out.println("you have 1 task in the list");
                    } else {
                        System.out.println("you have " + tasks.size() + " tasks in the list");
                    }
                    ui.showLine();
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException ex) {
                    ui.showLine();
                    ui.showException(new DukeException("pls specify the description and duration for this event!\n"
                            + "use the format: event [description] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm"));
                    ui.showLine();
                }
                break;
            case "delete":
                try {
                    int index = Integer.parseInt(parser.getCommandDetails(userInput)) - 1;
                    ui.showLine();
                    System.out.println("i've removed this task:\n" + tasks.get(index));
                    tasks = tasks.remove(index);
                    if (tasks.size() == 1) {
                        System.out.println("you have 1 task in the list");
                    } else {
                        System.out.println("you have " + tasks.size() + " tasks in the list");
                    }
                    ui.showLine();
                } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                    ui.showLine();
                    ui.showException(new DukeException("pls specify an existing task number in the task list!\n"
                            + "use the format: delete [task number]"));
                    ui.showLine();
                }
                break;
            case "find":
                // Fallthrough
            case "f":
                try {
                    TaskList taskListKeyword = tasks.findKeyword(parser.getCommandDetails(userInput));
                    ui.showLine();
                    if (taskListKeyword.size() == 0) {
                        System.out.println("there are no matching tasks in your list");
                    } else {
                        System.out.println("here are the matching items in your list:\n" + taskListKeyword);
                    }
                    ui.showLine();
                } catch (IndexOutOfBoundsException ex) {
                    ui.showLine();
                    ui.showException(new DukeException("pls specify a keyword to find!\n"
                            + "use the format: find [keyword]"));
                    ui.showLine();
                }
                break;
            default:
                ui.showLine();
                ui.showException(new DukeException("invalid command"));
                ui.showLine();
            }
            try {
                saveTaskList(tasks.getTasks());
            } catch (IOException e) {
                System.out.println("Something went wrong!");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

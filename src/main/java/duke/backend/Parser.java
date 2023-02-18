package duke.backend;

import java.time.format.DateTimeParseException;
import java.util.StringTokenizer;

import duke.commands.*;
import duke.exceptions.NoArgumentException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;


class Parser {
    public static final String DIVIDER = "____________________________________________________________\n";
//    private final Ui ui;
    private final TaskList tasklist;

    public Parser(TaskList tasklist) {
//        this.ui = ui;
        this.tasklist = tasklist;
    }

    public String parse(String instr) {
        String[] tokens = instr.split(" ", 2);
        String command = tokens[0];
        Command c;
        switch (command) {
        case "list":
            c = new List(tasklist);
            break;

        case "bye":
            c = new Bye(tasklist);
            break;

        case "mark":
            int idx = Integer.parseInt(tokens[1]);
            c = new Mark(idx, tasklist);
            break;

        case "unmark":
            idx = Integer.parseInt(tokens[1]);  //TODO: make new variable of idx.
            c = new Unmark(idx, tasklist);
            break;

        case "todo":
            String description = tokens[1];
            c = new MakeTodo(description, tasklist);
            break;

        case "deadline":
            String[] moreTokens = tokens[1].split(" /by ");
            description = moreTokens[0];
            String by = moreTokens[1];
            c = new MakeDeadline(description, by, tasklist);
            break;

        case "event":
            String[] fromFinder = tokens[1].split(" /from ");
            description = fromFinder[0];
            String[] toFinder = fromFinder[1].split(" /to ");
            String from = toFinder[0];
            String to = toFinder[1];
            c = new MakeEvent(description, from, to, tasklist);
            break;

        case "delete":
            idx = Integer.parseInt(tokens[1]);
            c = new Delete(idx, tasklist);
            break;

        case "find":
            String searchKey = tokens[1];
            c = new Find(searchKey, tasklist);
            break;

        default:
            return "OOPS! I'm sorry, but I don't know what that means :-(\n";
        }

        return c.execute();

//        default:
//            //  Instructions with arguments
//            StringTokenizer tokens = new StringTokenizer(instr, " ");
//            //  Splitting the action and args.
//            final String action = tokens.nextToken();
//
//            //  keyword = argument(s) after the action.
//            StringBuilder keyword = new StringBuilder();
//            while (tokens.hasMoreTokens()) {
//                keyword.append(" ")
//                        .append(tokens.nextToken());
//            }
//            keyword = new StringBuilder(keyword.toString()
//                                                .strip());
//
//            try {
//                //  For instructions with argument(s)
//                switch (action) {
//                case "mark":
//                    try {
//                        Task changedTask = tasklist.mark(Integer.parseInt(keyword.toString()) - 1);
//                        System.out.println(DIVIDER + "Nice! I've marked this task as done:\n"
//                                                   + changedTask + "\n" + DIVIDER);
//                    } catch (IndexOutOfBoundsException ioobe) {
//                        System.out.println("Sorry, the index number you've entered does not exist.");
//                    } catch (NumberFormatException nfe) {
//                        System.out.println("Please enter the index number you wish to mark/unmark.");
//                    }
//                    break;
//                case "unmark":
//                    try {
//                        Task changedTask = tasklist.unmarkIdx(Integer.parseInt(keyword.toString()) - 1);
//                        System.out.println(DIVIDER + "OK! I've marked this task as not done yet:\n"
//                                                   + changedTask + "\n" + DIVIDER);
//                    } catch (IndexOutOfBoundsException ioobe) {
//                        System.out.println("Sorry, the index number you've entered does not exist.");
//                    } catch (NumberFormatException nfe) {
//                        System.out.println("Please enter the index number you wish to mark/unmark.");
//                    }
//                    break;
//                case "todo":
//                    if (keyword.toString().equals("")) {
//                        throw new NoArgumentException();
//                    }
//                    tasklist.add(new Todo(keyword.toString()));
//                    break;
//                case "deadline":
//                    //  Split keyword into description and date, separated by "/by".
//                    String[] deadlineFinder = keyword.toString()
//                            .split(" /by ");
//                    keyword = new StringBuilder(deadlineFinder[0]);
//                    try {
//                        String deadline = deadlineFinder[1];
//                        tasklist.add(new Deadline(keyword.toString(), deadline));
//                    } catch (IndexOutOfBoundsException ioobe) {
//                        System.out.println("Please define a name and a deadline following the keyword '/by'.");
//                    } catch (DateTimeParseException dtpe) {
//                        System.out.println("Please enter a valid date in the format YYYY-MM-DD.");
//                    }
//                    break;
//                case "event":
//                    //  Split keyword into description and start,end.
//                    String[] startFinder = keyword.toString()
//                            .split(" /from ");
//                    try {
//                        //  Split start,end into start and end.
//                        String[] endFinder = startFinder[1].split(" /to ");
//                        tasklist.add(new Event(startFinder[0], endFinder[0], endFinder[1]));
//                    } catch (IndexOutOfBoundsException ioobe) {
//                        System.out.println("Please define a name, a start time following"
//                                                   + "the keyword '/from'\nand then an end time"
//                                                   + " following the keyword '/to'.");
//                    }
//                    break;
//                case "delete":
//                    try {
//                        int deleteIdx = Integer.parseInt(keyword.toString());
//                        Task deletedTask = tasklist.delete(deleteIdx - 1);
//                        System.out.println(DIVIDER);
//                        System.out.println("OK! I've deleted the following task: " + deletedTask + "\n" + DIVIDER);
//                    } catch (NumberFormatException nfe) {
//                        System.out.println("Please enter the index number you wish to delete.");
//                    } catch (IndexOutOfBoundsException ioobe) {
//                        System.out.println("Sorry, the index number you've entered does not exist.");
//                    }
//                    break;
//                case "find":
//                    ui.find(String.valueOf(keyword));
//                    break;
//                default:
//                    // For unknown commands
//                    System.out.println(DIVIDER + "OOPS! I'm sorry, but I don't know what that means :-(\n" + DIVIDER);
//                }
//            } catch (NoArgumentException nae) {
//                System.out.println("Please enter a name for your task.");
//            }
//        }

    }
}

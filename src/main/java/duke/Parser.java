package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Represents commands from user input
 */
public class Parser {
    private static final String Indentation = " ";
    private static final String Horizontal = "____________________________________________________________";
    private Ui ui;

    /**
     * Parser constructor
     * @param ui
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * method for list, mark, unmark, delete, to do, deadline, event commands
     * @param cmd
     * @param  tasks
     * @return command
     */
    public static String parse(String cmd, TaskList tasks) {
        assert cmd == null : "Invalid cmd";
        assert tasks == null : "Invalid tasks";

        String command = cmd.trim();
        String[] words = command.split(" ");
        Task task;
        String info;
        String str = "";

        if (!command.equals("bye")) {
            if (command.equals("list")) {
                //Ui.showList(tasks);
                str += Ui.showList(tasks);

            } else if (words[0].equals("mark")) {
                try {
                   //Ui.done(words[1], tasks);
                   str += Ui.done(words[1], tasks);

                } catch (Exception e) {
                    System.out.println(Indentation + Horizontal);
                    System.out.println("  ☹ OOPS!!! The index number cannot be empty.");
                    System.out.println(Indentation + Horizontal);

                    str += "  ☹ OOPS!!! The index number cannot be empty. \n";
                }

            } else if (words[0].equals("unmark")) {
                try {
                    //Ui.undone(words[1], tasks);
                    str += Ui.undone(words[1], tasks);
                    str += "\n";
                } catch (Exception e) {
                    System.out.println(Indentation + Horizontal);
                    System.out.println("  ☹ OOPS!!! The The index number cannot be empty.");
                    System.out.println(Indentation + Horizontal);

                    str += "  ☹ OOPS!!! The The index number cannot be empty. \n";
                }

            } else if (words[0].equals("delete")) {
                try {
                    //Ui.delete(words[1], tasks);
                    str += Ui.delete(words[1], tasks);
                    //str  += "\n";
                } catch (Exception e) {
                    System.out.println(Indentation + Horizontal);
                    System.out.println("  ☹ OOPS!!! The index number cannot be empty.");
                    System.out.println(Indentation + Horizontal);

                    str += "  ☹ OOPS!!! The index number cannot be empty. \n";
                }

            } else if (words[0].equals("find")) {
                try {
                    info = command.substring(command.indexOf(" ") + 1);
                    //System.out.println(info);

                    for (int i = 0; i < tasks.size(); i++) {
                        //if the existed task name contain info
                        if (tasks.get(i).getDescription().contains(info)) {
                            System.out.println(Indentation + Horizontal);
                            System.out.println(Indentation + "Here are the matching tasks in your list:");
                            System.out.println(Indentation + (i + 1) + "." + tasks.get(i).toString());
                            System.out.println(Indentation + Horizontal);
                            str += Indentation + "Here are the matching tasks in your list:";
                            str += Indentation + (i + 1) + "." + tasks.get(i).toString();
                        }
                    }
                } catch (Exception e) {
                    System.out.println(Indentation + Horizontal);
                    System.out.println("  ☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(Indentation + Horizontal);

                    str += "  ☹ OOPS!!! The description of a todo cannot be empty. \n";
                }

            } else if (words[0].equals("todo")) {
                try {
                    if (words[1].equals(null)) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                        str += "  ☹ OOPS!!! The description of a todo cannot be empty.";
                    }
                    info = command.substring(command.indexOf(" ") + 1);
                    task = new Todo(info, false);
                    tasks.add(task);
                    str += new Todo(info, false);
                } catch (Exception e) {
                    System.out.println(Indentation + Horizontal);
                    System.out.println("  ☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(Indentation + Horizontal);
                    str += "  ☹ OOPS!!! The description of a todo cannot be empty.";
                }

            } else if (words[0].equals("deadline")) {
                try {
                    info = command.substring(command.indexOf(" ") + 1, command.indexOf(" /by"));
                    String deadline = command.substring(command.indexOf("/by") + 4);

                    try {
                        //Date and Time
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("MM/dd/yyyy HHmm"));
                        LocalDateTime datetime1 = LocalDateTime.parse(deadline, formatter);
                        //System.out.println(datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));

                        task = new Deadline(info,
                                datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")), false);
                        str += new Deadline(info,
                                datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")), false);
                        str += "/n";
                    } catch (DateTimeParseException e) {
                        //System.out.println(deadline);
                        task = new Deadline(info, deadline, false);

                        str += new Deadline(info, deadline, false);
                        str += "/n";
                    }
                    tasks.add(task);
                } catch (Exception e) {
                    System.out.println(Indentation + Horizontal);
                    System.out.println("  ☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println(Indentation + Horizontal);

                    str += "  ☹ OOPS!!! The description of a deadline cannot be empty.";
                    str += "/n";
                }

            } else if (words[0].equals("event")) {
                try {
                    info = command.substring(command.indexOf(" ") + 1, command.indexOf(" /from "));
                    String fromtime = command.substring(command.indexOf(" /from ") + 6, command.indexOf(" /to "));
                    String totime = command.substring(command.indexOf(" /to ") + 4);
                    try {

                        //Date and Time
                        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern((" MM/dd/yyyy HHmm"));
                        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern((" MM/dd/yyyy HHmm"));
                        LocalDateTime datetime1 = LocalDateTime.parse(fromtime, formatter1);
                        LocalDateTime datetime2 = LocalDateTime.parse(totime, formatter2);
                        //System.out.println(datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));
                        //System.out.println(datetime2.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));

                        task = new Event(info,
                                datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                                datetime2.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                                false);
                        str += new Event(info,
                                datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                                datetime2.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                                false);

                    } catch (DateTimeParseException e) {
                        task = new Event(info, fromtime, totime, false);
                        str += new Event(info, fromtime, totime, false);
                    }
                    tasks.add(task);
                } catch (Exception e) {
                    System.out.println(Indentation + Horizontal);
                    System.out.println("  ☹ OOPS!!! The description of a event cannot be empty.");
                    System.out.println(Indentation + Horizontal);
                    str += "  ☹ OOPS!!! The description of a event cannot be empty.";
                }

            } else {
                System.out.println(Indentation + Horizontal);
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(Indentation + Horizontal);

                str += " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        }
        return str;
    }
}



package duke;

import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private Ui ui;
    private static final String Indentation = " ";
    private static final String Horizontal = "____________________________________________________________";

    public Parser(Ui ui) {
        this.ui = ui;
    }
    public static Task parse(String cmd, TaskList tasks) {
        String command = cmd.trim();
        String[] words = command.split(" ");
        Task task;
        String info;

        //if command is equal to bye, exit()
        //if command is not equal to bye, distinguish list or normal command
        if (!command.equals("bye")) {
            if (command.equals("list")) {
                Ui.showList(tasks);
            } else if (words[0].equals("mark")) {
                try {
                    Ui.done(words[1], tasks);
                } catch (Exception e) {
                    System.out.println(Indentation + Horizontal);
                    System.out.println("  ☹ OOPS!!! The index number cannot be empty.");
                    System.out.println(Indentation + Horizontal);
                }
            } else if (words[0].equals("unmark")) {
                try {
                    Ui.undone(words[1], tasks);
                } catch (Exception e) {
                    System.out.println(Indentation + Horizontal);
                    System.out.println("  ☹ OOPS!!! The The index number cannot be empty.");
                    System.out.println(Indentation + Horizontal);
                }
            } else if (words[0].equals("delete")) {
                try {
                    Ui.delete(words[1], tasks);
                } catch (Exception e) {
                    System.out.println(Indentation + Horizontal);
                    System.out.println("  ☹ OOPS!!! The index number cannot be empty.");
                    System.out.println(Indentation + Horizontal);
                }
            } else if (words[0].equals("todo")) {
                try {
                    if (words[1].equals(null)) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                    }
                    info = command.substring(command.indexOf(" ") + 1);
                    task = new Todo(info, false);
                    tasks.add(task);
                } catch (Exception e) {
                    System.out.println(Indentation + Horizontal);
                    System.out.println("  ☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(Indentation + Horizontal);
                }

            } else if (words[0].equals("deadline")) {
                try {
                    info = command.substring(command.indexOf(" ") + 1, command.indexOf(" /by"));
                    String deadline = command.substring(command.indexOf("/by") + 4);

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("MM/dd/yyyy HHmm"));
                        LocalDateTime datetime1 = LocalDateTime.parse(deadline, formatter);
                        System.out.println(datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));

                        task = new Deadline(info,
                                datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")), false);

                    } catch (DateTimeParseException e) {
                        System.out.println(deadline);
                        task = new Deadline(info, deadline, false);
                    }
                    tasks.add(task);
                } catch (Exception e) {
                    System.out.println(Indentation + Horizontal);
                    System.out.println("  ☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println(Indentation + Horizontal);
                }

            } else if (words[0].equals("event")) {
                try {
                    info = command.substring(command.indexOf(" ") + 1, command.indexOf(" /from "));
                    String fromtime = command.substring(command.indexOf(" /from ") + 6, command.indexOf(" /to "));
                    String totime = command.substring(command.indexOf(" /to ") + 4);
                    try {
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

                    } catch (DateTimeParseException e) {
                        task = new Event(info, fromtime, totime, false);
                    }

                    tasks.add(task);
                } catch (Exception e) {
                    System.out.println(Indentation + Horizontal);
                    System.out.println("  ☹ OOPS!!! The description of a event cannot be empty.");
                    System.out.println(Indentation + Horizontal);
                }

            } else {
                System.out.println(Indentation + Horizontal);
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(Indentation + Horizontal);
            }
        }
        return null;
    }
}



package duke;

import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private FileManager fileManager;
    private TaskList taskList;

    public Parser(FileManager fileManager, TaskList taskList) {
        this.fileManager = fileManager;
        this.taskList = taskList;
    }
    public void parse(String userMessage) {
        String [] parts = userMessage.split(" ", 2);

        if (parts[0].equals("bye")) {
            this.fileManager.saveFile(this.taskList.getList());
            System.out.print("  Cya~ Till next time!");
        } else if (parts[0].equals("list")) {
            System.out.println("    Here are the tasks in your list:");
            this.taskList.list();
        } else if (parts[0].equals("mark")) {
            try {
                int taskNumber = Integer.parseInt(parts[1]);
                this.taskList.mark(taskNumber);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    Please specify which task to mark~  >:(");
            }
        } else if (parts[0].equals("unmark")) {
            try {
                int taskNumber = Integer.parseInt(parts[1]);
                this.taskList.unmark(taskNumber);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    Please specify which task to unmark~  >:(");
            }
        } else if (parts[0].equals("delete")) {
            try {
                int taskNumber = Integer.parseInt(parts[1]);
                this.taskList.remove(taskNumber);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    Please specify which task to delete~ >:(");
            }
        } else if (parts[0].equals("todo")) {
            try {
                Todo newToDo = new Todo(parts[1]);
                this.taskList.add(newToDo);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of todo cannot be empty.");
            }
        } else if (parts[0].equals("deadline")) {
            try {
                String[] deadlineParts = parts[1].split(" /");
                String[] deadline = deadlineParts[1].split(" ", 2);
                LocalDate byDate = LocalDate.parse(deadline[1]);
                Deadline newDeadline = new Deadline(deadlineParts[0], byDate);
                this.taskList.add(newDeadline);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of deadline cannot be empty.");
            } catch (DateTimeParseException e) {
                System.out.println("    Incorrect date format, try again~");
            }
        } else if (parts[0].equals("event")) {
            try {
                String[] eventParts = parts[1].split(" /");
                String[] from = eventParts[1].split(" ", 2);
                String[] to = eventParts[2].split(" ", 2);
                LocalDate fromDate = LocalDate.parse(from[1]);
                LocalDate toDate = LocalDate.parse(to[1]);
                Event newEvent = new Event(eventParts[0], fromDate, toDate);
                this.taskList.add(newEvent);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of event cannot be empty.");
            } catch (DateTimeParseException e) {
                System.out.println("    Incorrect date format, please try again~");
            }
        } else if (parts[0].equals("find")) {
            try {
                String keyword = parts[1];
                System.out.println("    MATCHY Matchyyy!~ Here are the matching tasks:");
                this.taskList.find(keyword);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! You gotta tell me what to search for :<");
            }
        }else {
            System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :<");
        }

    }
}

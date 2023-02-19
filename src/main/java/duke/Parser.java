package duke;

import duke.exceptions.DukeExceptions;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class Parser {
    public TaskList data;

    public Parser(TaskList data) {
        this.data = data;
    }

    public TaskList getTaskList() {
        return this.data;
    }
    public String parse(String input) {
        if (input.equals("bye")) {
            Storage.writeFile(data);
            return "Bye!";
        }
        if (input.equals("list")) {
            return data.printData();
        }

        if (input.contains("unmark")) {
            char query = input.charAt(input.length() - 1);
            int pos = Character.getNumericValue(query);
            //error check for pos exceeding size
            data.unmark(pos-1);
            String msg = "Unmarked:" + "\n" + data.getEntry(pos-1).toString();
            return msg;
        }

        if (input.contains("mark")) {
            char query = input.charAt(input.length() - 1);
            int pos = Character.getNumericValue(query);
            //error check for pos exceeding size
            data.mark(pos-1);
            String msg = "Marked:" + "\n" + data.getEntry(pos-1).toString();
            return msg;
        }

        if (input.contains("delete")) {
            char query = input.charAt(input.length() - 1);
            int pos = Character.getNumericValue(query);
            //error check for pos exceeding size
            Task del = data.getEntry(pos-1);
            data.removeEntry(pos-1);
            String msg = "Deleted:" + "\n" + del.toString();
            return msg;
        }

        if (input.contains("todo ")) {
            Task todo = new ToDo();
            String description = input.replace("todo ", "");
            try {
                todo.genDscp(description);
            } catch (DukeExceptions e){
                return e.getMessage();
            }
            data.addEntry(todo);
            return String.format("Now you have %d duke.tasks in the list", data.getSize());
        }

        if (input.contains("event ")) {
            Task event = new Event();
            String description = input.replace("event ", "");
            try {
                event.genDscp(description);
            } catch (DukeExceptions e) {
                return e.getMessage();
            }
            data.addEntry(event);
            return String.format("Now you have %d duke.tasks in the list", data.getSize());
        }

        if (input.contains("deadline ")) {
            Task deadline = new Deadline();
            String description = input.replace("deadline ", "");
            try {
                deadline.genDscp(description);
            } catch (DukeExceptions e) {
                return e.getMessage();
            }

            data.addEntry(deadline);
            return String.format("Now you have %d duke.tasks in the list", data.getSize());
        }
        return "I do not understand your instructions...";
    }
}

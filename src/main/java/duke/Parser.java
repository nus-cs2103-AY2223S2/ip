package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Parser {

    private TaskList taskList;
    Parser(TaskList taskList) {
        this.taskList = taskList;
    }
    String parse(String input) {
        if (input.equals("bye")) {
            return sayBye();
        } else if (input.equals("list")) {
            return returnList();
        } else if (input.startsWith("mark")) {
            return mark(input);
        } else if (input.startsWith("unmark")) {
            return unmark(input);
        } else if (input.startsWith("delete")) {
            return delete(input);
        } else {
            return addTask(input)[1];
        }
    }
    String returnList() {
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            out.append(String.format("%d. %s\n", i, taskList.get(i - 1)));
        }
        return out.toString();
    }

    String mark(String input) {
        int i = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        Task task = taskList.get(i);
        task.mark();
        return String.format("\tNice! I've marked this task as done:\n\t  %s", task);
    }

    String unmark(String input) {
        int i = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        Task task = taskList.remove(i);
        task.unmark();
        return String.format("\tNice! I've marked this task as not done yet:\n\t  %s", task);
    }

    String delete(String input) {
        int i = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        Task task = taskList.remove(i);
        String out = String.format("\tNoted. I've removed this task:\n\t  %s", task);
        out += String.format("\n\tNow you have %d tasks in the list.", taskList.size());
        return out;
    }

    String sayBye() {
        return "";
    }

    String[] addTask(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String[] split = input.split(" ", 2);
        String taskType = split[0];
        try {
            switch (taskType) {
            case "t": // fallthrough
            case "todo": {
                if (split.length == 1) {
                    DukeException.rethrow("ToDoException");
                }
                Task task = new ToDo(split[1]);
                taskList.add(task);
                return new String[]{String.valueOf(taskList.size()), String.format("added: %s", task)};
            }
            case "d": // fallthrough
            case "deadline": {
                String[] s = split[1].split(" /by ");
                System.out.println(s[0]);
                LocalDateTime byTime = LocalDateTime.parse(s[1], formatter);
                Task task = new Deadline(s[0], byTime);
                taskList.add(task);
                return new String[]{String.valueOf(taskList.size()), String.format("added: %s", task)};
            }
            case "e": // fallthrough
            case "event": {
                String[] s = split[1].split(" /by ", 2);
                String taskDescription = s[0];
                s = s[1].split(" /from ");
                LocalDateTime fromTime = LocalDateTime.parse(s[0], formatter);
                LocalDateTime toTime = LocalDateTime.parse(s[1], formatter);
                Task task = new Event(taskDescription, fromTime, toTime);
                taskList.add(task);
                return new String[]{String.valueOf(taskList.size()), String.format("added: %s", task)};
            }
            default:
                DukeException.rethrow("UnknownCommand");
            }
        } catch (DukeException.ToDoException | DukeException.UnknownCommandException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

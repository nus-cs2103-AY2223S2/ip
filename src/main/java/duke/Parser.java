package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

class Parser {

    private TaskList taskList;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private DateTimeFormatter altFormatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");

    Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    TaskList getTaskList() {
        return taskList;
    }

    String parse(String input) {
        if (input.equals("bye")) {
            return sayBye();
        } else if (input.equals("list")) {
            return returnList(taskList);
        } else if (input.startsWith("mark")) {
            return sortTaskList();
        } else if (input.startsWith("mark")) {
            return mark(input);
        } else if (input.startsWith("unmark")) {
            return unmark(input);
        } else if (input.startsWith("delete")) {
            return delete(input);
        } else if (input.startsWith("find")) {
            return find(input);
        } else {
            return addTask(input)[1];
        }
    }
    String returnList(List<Task> taskList) {
        StringBuilder out = new StringBuilder();
        int size = taskList.size();
        for (int i = 1; i < size; i++) {
            out.append(String.format("%d. %s\n", i, taskList.get(i - 1)));
        }
        out.append(String.format("%d. %s", size, taskList.get(size - 1)));
        return out.toString();
    }

    String sortTaskList() {
        Collections.sort(taskList);
        return returnList(taskList);
    }

    String setStatus(String input, Boolean isDone) {
        int i = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        Task task = taskList.get(i);
        task.setStatus(isDone);
        return String.format("\tNice! I've marked this task as done:\n\t  %s", task);
    }

    String mark(String input) {
        int i = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        Task task = taskList.get(i);
        task.mark();
        return String.format("\tNice! I've marked this task as done:\n\t  %s", task);
    }

    private String unmark(String input) {
        int i = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        Task task = taskList.get(i);
        task.unmark();
        return String.format("\tNice! I've marked this task as not done yet:\n\t  %s", task);
    }

    private String delete(String input) {
        int i = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        Task task = taskList.remove(i);
        String out = String.format("\tNoted. I've removed this task:\n\t  %s", task);
        out += String.format("\n\tNow you have %d tasks in the list.", taskList.size());
        return out;
    }

    private String find(String input) {
        String s = input.split("find ", 2)[1];
        List<Task> filteredTasks = taskList.stream().filter((t)
                -> t.getTaskDescription().contains(s))
                .collect(Collectors.toList());
        StringBuilder out = new StringBuilder("Here are the matching tasks in your list:\n");
        out.append(returnList(filteredTasks));
        return out.toString();
    }

    String sayBye() {
        return "\tBye. Hope to see you again soon!";
    }

    String[] addTask(String input) {
        String[] split = input.split(" ", 2);
        String taskType = split[0];
        try {
            switch (taskType) {
            case "t": //fallthrough
            case "todo": {
                return addToDo(split);
            }
            case "d": {
                return addDeadline(split, true);
            }
            case "deadline": {
                return addDeadline(split, false);
            }
            case "e": {
                return addEvent(split, true);
            }
            case "event": {
                return addEvent(split, false);
            }
            default:
                DukeException.rethrow("UnknownCommand");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    String[] addToDo(String[] input) throws Exception {
        if (input[1].equals("")) {
            DukeException.rethrow("ToDoException");
        }
        Task task = new ToDo(input[1]);
        taskList.add(task);
        return new String[]{String.valueOf(taskList.size()), String.format("added: %s", task)};
    }

    String[] addDeadline(String[] input, Boolean isAlt) {
        DateTimeFormatter dtFormat;
        if (isAlt) {
            dtFormat = altFormatter;
        } else {
            dtFormat = formatter;
        }
        String[] s = input[1].split(" /by ");
        LocalDateTime byTime = LocalDateTime.parse(s[1], dtFormat);
        Task task = new Deadline(s[0], byTime);
        taskList.add(task);
        return new String[]{String.valueOf(taskList.size()), String.format("added: %s", task)};
    }

    String[] addEvent(String[] input, Boolean isAlt) {
        DateTimeFormatter dtFormat;
        if (isAlt) {
            dtFormat = altFormatter;
        } else {
            dtFormat = formatter;
        }
        String[] s = input[1].split(" /from ", 2);
        String taskDescription = s[0];
        s = s[1].split(" /to ");
        LocalDateTime fromTime = LocalDateTime.parse(s[0], dtFormat);
        LocalDateTime toTime = LocalDateTime.parse(s[1], dtFormat);
        Task task = new Event(taskDescription, fromTime, toTime);
        taskList.add(task);
        return new String[]{String.valueOf(taskList.size()), String.format("added: %s", task)};
    }
}

package duke;

import java.util.Scanner;

public class Parser {
    private TaskList taskList;
    private Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    private void showList() {
        ui.showTaskList(taskList);
    }

    private void mark(int index) {
        taskList.mark(index);
        Task task = taskList.get(index);
        ui.showMarkMessage(task, index);
    }

    private void unmark(int index) {
        taskList.unmark(index);
        Task task = taskList.get(index);
        ui.showUnmarkMessage(task, index);
    }

    private void delete(int index) {
        Task deletedTask = taskList.delete(index);
        int len = taskList.size();
        ui.showDeleteMessage(deletedTask, len);
    }

    private void addTodo(String task) throws DukeException {
        String description = task.substring(5);
        if (description.trim().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        taskList.add(new Todo(description));
    }

    private void addDeadline(String task) {
        String[] arr = task.split("/");
        String description = arr[0].substring(9).trim();
        String by = arr[1].substring(3).trim();
        taskList.add(new Deadline(description, by));
    }

    private void addEvent(String task) {
        String[] arr = task.split("/");
        String description = arr[0].substring(6).trim();
        String from = arr[1].substring(5).trim();
        String to = arr[2].substring(3).trim();
        taskList.add(new Event(description, from, to));
    }

    private void find(String task) {
        String keyword = task.substring(5);
        TaskList matchingTasks = taskList.find(keyword);
        ui.showTaskList(matchingTasks);
    }

    public void parse(String task) throws DukeException {
        if (task.equals("list")) {
            showList();
        } else if (task.startsWith("mark")) {
            int index = Integer.parseInt(task.substring(5)) - 1;
            mark(index);
        } else if (task.startsWith("unmark")) {
            int index = Integer.parseInt(task.substring(7)) - 1;
            unmark(index);
        } else if (task.startsWith("delete")) {
            int index = Integer.parseInt(task.substring(7)) - 1;
            delete(index);
        } else if (task.startsWith("find")) {
            find(task);
        } else {
            if (task.startsWith("todo")) {
                addTodo(task);
            } else if (task.startsWith("deadline")) {
                addDeadline(task);
            } else if (task.startsWith("event")) {
                addEvent(task);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            int len = taskList.size();
            Task t = taskList.get(len - 1);
            ui.showAddMessage(t, len);
        }
    }

    public void parseAll() {
        Scanner sc = new Scanner(System.in);

        try {
            while (true) {
                String task = sc.nextLine();
                if (task.equals("bye")) {
                    ui.showByeMessage();
                    break;
                } else {
                    parse(task);
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}

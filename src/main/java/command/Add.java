package command;

import gui.Ui;
import runner.Riddle;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.format.DateTimeParseException;

public class Add {
    private final Riddle riddle;

    /**
     * Constructor for Add.
     * @param riddle
     */
    public Add(Riddle riddle) {
        this.riddle = riddle;
    }

    /**
     * Execute add function.
     * @param type Type of the task.
     * @param info Description of the task.
     * @return Message when adding a task.
     */
    public String execute(String type, String info) {
        switch (type) {
        case "todo":
            return addTodo(info);
        case "deadline":
            return addDeadline(info);
        case "event":
            return addEvent(info);
        }
        return "Failure with Add";
    }

    /**
     * Actions when adding a Todo.
     * @param action Description of a Todo.
     * @returns Add message.
     */
    public String addTodo(String action) {
        Task t = new Todo(action);
        riddle.taskList.add(t);
        riddle.storage.saveList();
        riddle.updateInput("todo " + action);
        return Ui.addMSG(t, riddle.taskList.size());
    }


    /**
     * Actions when adding a Deadline.
     * @param s Description of a Deadline.
     * @returns Add message.
     */
    public String addDeadline(String s) {
        try {
            String msg = s.split("/by ", 2)[0];
            String by = s.split("/by ", 2)[1];
            Task d = new Deadline(msg, by);
            riddle.taskList.add(d);
            riddle.storage.saveList();
            riddle.updateInput("deadline " + s);
            return Ui.addMSG(d, riddle.taskList.size());
        } catch (DateTimeParseException e) {
            return "Date Unacceptable (YYYY-MM-DD PLZ)";
        }
    }

    /**
     * Actions when adding an Event.
     * @param s Description of an Event.
     * @returns Add message.
     */
    public String addEvent(String s) {
        String event = s.split("/from", 2)[0];
        String time = s.split("/from", 2)[1];
        String from = time.split("/to")[0];
        String to = time.split("/to")[1];
        Task e = new Event(event, from, to);
        riddle.taskList.add(e);
        riddle.storage.saveList();
        riddle.updateInput("event " + s);
        return Ui.addMSG(e, riddle.taskList.size());
    }
}

package duke;

import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

/**
 * List to store Task objects.
 */
public class TaskList {

    private List<Task> storedInputs;
    private Ui ui = new Ui();

    public TaskList(LinkedList<Task> savedInputs) {
        this.storedInputs = new LinkedList<>(savedInputs);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= storedInputs.size(); i++) {
            s.append(i).append(". ").append(storedInputs.get(i - 1)).append("\n");
        }
        return s.toString();
    }

    public List<Task> getList() {
        return storedInputs;
    }

    public void markEvent(String userInput) {
        String[] arr = userInput.split(" ");
        int num = Integer.parseInt(arr[1]);

        Task t;
        try {
            t = storedInputs.get(num-1);
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexOutOfBoundMessage();
            return;
        }
        t.markDone();
        ui.printMarkMessage(t);
    }

    public void unmarkEvent(String userInput) {
        String[] arr = userInput.split(" ");
        int num = Integer.parseInt(arr[1]);

        Task t;
        try {
            t = storedInputs.get(num-1);
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexOutOfBoundMessage();
            return;
        }
        t.markUnDone();
        ui.printUnMarkMessage(t);
    }

    public void deleteEvent(String userInput) {
        String[] arr = userInput.split(" ");
        int num = Integer.parseInt(arr[1]);

        Task t;
        try {
            t = storedInputs.remove(num-1);
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexOutOfBoundMessage();
            return;
        }

        ui.printDeleteSuccessfulMessage(t);
        ui.printTotalTask(storedInputs);
    }

    String removeKeyword(String s) throws DukeException {
        try {
            s = s.substring(s.indexOf(" ")).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("details cannot be empty");
        }

        if (s.isEmpty()) {
            throw new DukeException("details cannot be empty");
        }

        return s;
    }

    public void addToDoEvent(String userInput) {
        try {
            userInput = removeKeyword(userInput);
        } catch (DukeException e) {
            ui.printEmptyDetailsMessage("todo");
            return;
        }

        Task newTask = new ToDo(userInput.trim());
        storedInputs.add(newTask);
        ui.printAddTaskSuccessfulMessage(newTask);
        ui.printTotalTask(storedInputs);
    }

    public void addDeadlineEvent(String userInput) {
        try {
            userInput = removeKeyword(userInput);
        } catch (DukeException e) {
            ui.printEmptyDetailsMessage("deadline");
            return;
        }

        Task newTask;
        try {
            String[] info = userInput.split("/by");
            if (info[0].trim().isEmpty()) {
                ui.printEmptyDetailsMessage("deadline");
                return;
            }
            newTask = new Deadline(info[0].trim(), info[1].trim());
            storedInputs.add(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printDeadlineFormat();
            return;
        }
        catch (DateTimeParseException e) {
            ui.printDateFormat();
            return;
        }

        ui.printAddTaskSuccessfulMessage(newTask);
        ui.printTotalTask(storedInputs);
    }

    public void addEventEvent(String userInput) {
        try {
            userInput = removeKeyword(userInput);
        } catch (DukeException e) {
            ui.printEmptyDetailsMessage("event");
            return;
        }

        Task newTask;
        try {
            String[] infoA = userInput.split("/from");
            String[] infoB = infoA[1].split("/to");
            if (infoA[0].trim().isEmpty()) {
                ui.printEmptyDetailsMessage("event");
                return;
            }
            newTask = new Event(infoA[0].trim(), infoB[0].trim(), infoB[1].trim());
            storedInputs.add(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printEventFormat();
            return;
        } catch (DateTimeParseException e) {
            ui.printDateFormat();
            return;
        }

        ui.printAddTaskSuccessfulMessage(newTask);
        ui.printTotalTask(storedInputs);
    }
}

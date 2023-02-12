package duke;

import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

/**
 * List to store Task objects.
 */
public class TaskList {

    /** Stores all task */
    private List<Task> storedInputs;

    /** Handles interaction with user */
    private UiOld uiOld = new UiOld();

    /**
     * Creates an instance of TaskList.
     *
     * @param savedInputs List of task.
     */
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

    /**
     * Mark a task a done.
     *
     * @param userInput User input.
     */
    public void markEvent(String userInput) {
        String[] arr = userInput.split(" ");
        int num = Integer.parseInt(arr[1]);

        Task t;
        try {
            t = storedInputs.get(num-1);
        } catch (IndexOutOfBoundsException e) {
            uiOld.printIndexOutOfBoundMessage();
            return;
        }
        t.markDone();
        uiOld.printMarkMessage(t);
    }

    /**
     * Mark a task as not done.
     *
     * @param userInput User input.
     */
    public void unmarkEvent(String userInput) {
        String[] arr = userInput.split(" ");
        int num = Integer.parseInt(arr[1]);

        Task t;
        try {
            t = storedInputs.get(num-1);
        } catch (IndexOutOfBoundsException e) {
            uiOld.printIndexOutOfBoundMessage();
            return;
        }
        t.markUnDone();
        uiOld.printUnMarkMessage(t);
    }

    /**
     * Delete an event.
     *
     * @param userInput User input.
     */
    public void deleteEvent(String userInput) {
        String[] arr = userInput.split(" ");
        int num = Integer.parseInt(arr[1]);

        Task t;
        try {
            t = storedInputs.remove(num-1);
        } catch (IndexOutOfBoundsException e) {
            uiOld.printIndexOutOfBoundMessage();
            return;
        }

        uiOld.printDeleteSuccessfulMessage(t);
        uiOld.printTotalTask(storedInputs);
    }

    /**
     * Filter out keywords from user input.
     * Use for processing task information.
     *
     * @param s User input.
     * @return User input without keywords.
     * @throws DukeException If keyword is the only input.
     */
    public String removeKeyword(String s) throws DukeException {
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

    /**
     * Add a ToDo task.
     *
     * @param userInput User input.
     */
    public void addToDoEvent(String userInput) {
        try {
            userInput = removeKeyword(userInput);
        } catch (DukeException e) {
            uiOld.printEmptyDetailsMessage("todo");
            return;
        }

        Task newTask = new ToDo(userInput.trim());
        storedInputs.add(newTask);
        uiOld.printAddTaskSuccessfulMessage(newTask);
        uiOld.printTotalTask(storedInputs);
    }

    /**
     * Add a Deadline task.
     *
     * @param userInput User input.
     */
    public void addDeadlineEvent(String userInput) {
        try {
            userInput = removeKeyword(userInput);
        } catch (DukeException e) {
            uiOld.printEmptyDetailsMessage("deadline");
            return;
        }

        Task newTask;
        try {
            String[] info = userInput.split("/by");
            if (info[0].trim().isEmpty()) {
                uiOld.printEmptyDetailsMessage("deadline");
                return;
            }
            newTask = new Deadline(info[0].trim(), info[1].trim());
            storedInputs.add(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            uiOld.printDeadlineFormat();
            return;
        }
        catch (DateTimeParseException e) {
            uiOld.printDateFormat();
            return;
        }

        uiOld.printAddTaskSuccessfulMessage(newTask);
        uiOld.printTotalTask(storedInputs);
    }

    /**
     * Add an Event task.
     * @param userInput User input.
     */
    public void addEventEvent(String userInput) {
        try {
            userInput = removeKeyword(userInput);
        } catch (DukeException e) {
            uiOld.printEmptyDetailsMessage("event");
            return;
        }

        Task newTask;
        try {
            String[] infoA = userInput.split("/from");
            String[] infoB = infoA[1].split("/to");
            if (infoA[0].trim().isEmpty()) {
                uiOld.printEmptyDetailsMessage("event");
                return;
            }
            newTask = new Event(infoA[0].trim(), infoB[0].trim(), infoB[1].trim());
            storedInputs.add(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            uiOld.printEventFormat();
            return;
        } catch (DateTimeParseException e) {
            uiOld.printDateFormat();
            return;
        }

        uiOld.printAddTaskSuccessfulMessage(newTask);
        uiOld.printTotalTask(storedInputs);
    }

    /**
     * Prints all task that stores the given phrase.
     *
     * @param userInputRaw Unmodified user input.
     */
    public void find(String userInputRaw) {

        String givenPhrase = userInputRaw;

        try {
            givenPhrase = removeKeyword(givenPhrase);
        } catch (DukeException e) {
            uiOld.printEmptyDetailsMessage("event");
            return;
        }

        int count = 1;
        StringBuilder s = new StringBuilder("  ");

        for (int i = 0; i < this.storedInputs.size(); i++) {
            Task currentTask = this.storedInputs.get(i);
            if (currentTask.getDetails().contains(givenPhrase)) {
                s.append(count).append(".").append(currentTask).append("\n  ");
                count += 1;
            }
        }

        uiOld.printFoundTasks(s.toString());

    }

    public String markTask(String index) throws DukeException {
        try {
            //TODO: ignore index 0 of storedInputs
            return storedInputs.get(Integer.parseInt(index)).markDone().getTaskDetails();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.toString());
        }
    }

    public String unMarkTask(String index) throws DukeException {
        try {
            //TODO: ignore index 0 of storedInputs
            return storedInputs.get(Integer.parseInt(index)).markUnDone().getTaskDetails();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.toString());
        }
    }

    public String deleteTask(String index) throws DukeException {
        try {
            return storedInputs.remove(Integer.parseInt(index)).getTaskDetails();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.toString());
        }
    }

    public String addToDoTask(String taskDetails) throws DukeException {
        if (taskDetails.isBlank()) {
            throw new DukeException(Ui.emptyDetailsForToDoMessage);
        }

        Task newTask = new ToDo(taskDetails.trim());
        storedInputs.add(newTask);
        return newTask.getTaskDetails();
    }

    public Task addDeadlineTask(String taskDetails) throws DukeException {
        try {
            String[] s = taskDetails.split("/by");
            String taskInfo = s[0].trim();
            String taskDateLine = s[1].trim();

            Task newTask = new Deadline(taskInfo, taskDateLine);
            storedInputs.add(newTask);
            return newTask;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Ui.emptyDetailsForDeadlineMessage);
        }
    }

    public String addEventTask(String taskDetails) throws DukeException {

        if (taskDetails.isBlank()) {
            throw new DukeException(Ui.emptyDetailsForEventMessage);
        }

        try {
            String[] s = taskDetails.split("/from");
            String[] ss = s[1].split("/to");

            String taskInfo = s[0].trim();
            String taskStartDate = ss[0].trim();
            String taskEndDate = ss[1].trim();

            Task newTask = new Event(taskInfo, taskStartDate, taskEndDate);
            storedInputs.add(newTask);
            return newTask.getTaskDetails();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Ui.eventTaskFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException(Ui.supportedDateFormat);
        }
    }

    public String findTaskWith(String phrase) {

        int taskIndex = 1;
        StringBuilder s = new StringBuilder("  ");

        for (Task currentTask : this.storedInputs) {
            if (currentTask.getDetails().contains(phrase)) {
                s.append(taskIndex).append(".").append(currentTask).append("\n  ");
                taskIndex += 1;
            }
        }

        return s.toString();
    }
}

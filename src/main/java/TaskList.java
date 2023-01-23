import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> listOfThings;

    public TaskList(ArrayList<Task> loadedTasks) {
        this.listOfThings = loadedTasks;
    }

    public TaskList() {
        this.listOfThings = new ArrayList<Task>();
    }

    public ArrayList<Task> getList() {
        return this.listOfThings;
    }


    /**
     * marks the task inside the list as done
     * @param index the index in the array of the task we want marked
     * @return the task that is marked
     */
    public Task markTaskInListDone(int index) {
        this.listOfThings.get(index).markDone();
        return this.listOfThings.get(index);
    }


    /**
     * marks the task inside the list as undone
     * @param index the index in the array of the task we want unmarked
     * @return the task we just unmarked
     */
    public Task markTaskInListUndone(int index) {
        this.listOfThings.get(index).markUndone();
        return this.listOfThings.get(index);
    }



    /**
     *
     * @param text the text containing the information of the command
     * @param add type of add command use
     * @throws DukeException when the format is wrong
     */
    public Task addItem(String text, Command add) throws DukeException {
        Task addedItem = null;
        if (add.equals(Command.TODO)) {
            String contents = Parser.parseTodo(text);
            if (contents.length() == 0) {
                throw new DukeException("The description of a todo cannot be empty");
            }
            addedItem = new Todo(contents, false);

        } else if (add.equals(Command.DEADLINE)) {
            String[] arr = Parser.parseDeadline(text);
            if (arr.length != 2) {
                throw new DukeException("I don't know what that means. Format it as 'deadline [do something] /by [date]");
            }
            LocalDateTime end = Duke.createLocalDateTime(arr[1]);
            if (end != null) {
                addedItem = new Deadline(arr[0], false, end);
            } else {
                throw new DukeException("Format date as YYYY-MM-DD HH:mm");
            }
        } else {
            String[] arr = Parser.parseEvent(text);
            if (arr.length != 3) {
                throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");
            }
            LocalDateTime start = Duke.createLocalDateTime(arr[1]);
            LocalDateTime end = Duke.createLocalDateTime(arr[2]);
            if (start != null && end != null) {
                addedItem = new Event(arr[0], false, start, end);
            } else {
                throw new DukeException("Format date as YYYY-MM-DD HH:mm");
            }
        }
        this.listOfThings.add(addedItem);
        return addedItem;
    }



    /**
     * removes the item in the list
     * @param index the index of the item to be removed
     */
    public Task removeItem(int index) {
        return this.listOfThings.remove(index);
    }

}

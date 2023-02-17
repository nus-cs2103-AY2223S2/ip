package duke.tasklist;

import duke.exceptions.TaskException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Contains task actions
 */
public class TaskList {
    private Storage storage;

    /**
     * Contains sets of task
     *
     * @param storage stores data
     */
    public TaskList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Prints list
     *
     * @return print items in the list
     */
    public String printList() {
        return this.storage.displayList();
    }

    /**
     * Marks list item
     *
     * @param index items index in array list
     * @return indicate item is marked
     */
    public String markItem(int index) {
        this.storage.markListItem(index);
        return "Task has been marked";
    }

    /**
     * Unmarks list item
     *
     * @param index items index in array list
     * @return indicate item is unmarked
     */
    public String unmarkItem(int index) {
        this.storage.unmarkListItem(index);
        return "Task has been unmarked";
    }

    /**
     * Deletes list item
     *
     * @param index items index in array list
     * @return indicate item has been deleted
     */
    public String deleteTask(int index) {
        this.storage.deleteListItem(index);
        return "Task has been deleted";
    }

    /**
     * Updates item in the list of the same task type
     *
     * @param index location of the item is stored in the list
     * @param input string contains item information
     * @return message about updated item
     * @throws TaskException return a exception with a custom message
     */
    public String updateTask(int index, String input) throws TaskException {
        String newInput = input.substring((index < 10 ? 9 : 10), input.length());
        return this.storage.updateItem(index, newInput);
    }

    /**
     * Adds list item
     *
     * @param instruction command entered by user
     * @param input       name of the three task types
     */
    public String addItem(String instruction, String input) {
        try {
            switch (instruction) {
            case "TODO":
                if (input.length() <= 5) {
                    Ui.error("todo");
                }
                this.storage.addTodoItem(input);
                break;

            case "DEADLINE":
                if (!input.contains("-by")) {
                    Ui.error("deadline");
                }
                final String[] deadline_part = input.substring(9, input.length()).split("-by ");
                this.storage.addDeadlineItem(deadline_part[0], deadline_part[1]);
                break;

            case "EVENT":
                boolean from = input.contains("-from");
                boolean to = input.contains("-to");

                if ((!from || !to) || !(from && to)) {
                    Ui.error("event");
                }
                final String[] event_part = input.substring(6, input.length()).split("-from ");
                String[] periodRange = event_part[1].split("-to ");
                this.storage.addEventItem(event_part[0], periodRange[0], periodRange[1]);
                break;

            default:
                return "Should not print anything";
            }

        } catch (TaskException e) {
            return e.getMessage();
        } catch (NullPointerException e) {
            System.out.println("Object pointing to null, please check code");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Check if the index is within the size of the array");
        }
        return "Task has been added";
    }

    /**
     * Finds items that contains word enter by user
     *
     * @param input to be entered by the users
     * @return show result co-relates to the search input
     */
    public String findItem(String input) {
        return this.storage.findListItem(input);
    }
}

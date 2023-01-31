package duke.tasklist;

import duke.exceptions.TaskException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Contains task actions
 */
public class TaskList {

    private Storage storage;
    private Ui ui;

    /**
     * Contains sets of task
     *
     * @param storage stores data
     * @param ui      responds to user inputs
     */
    public TaskList(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Prints list
     */
    public String printList() {
        return this.storage.displayList();
    }

    /**
     * Marks list item
     *
     * @param index items index in array list
     */
    public String markItem(int index) {
        this.storage.markListItem(index);
        return "Task has been marked";
    }

    /**
     * Unmarks list item
     *
     * @param index items index in array list
     */
    public String unmarkItem(int index) {
        this.storage.unmarkListItem(index);
        return "Task has been unmarked";
    }

    /**
     * Deletes list item
     *
     * @param index items index in array list
     */
    public String deleteTask(int index) {
        this.storage.deleteListItem(index);
        return "Task has been deleted";
    }

    /**
     * Adds list item
     *
     * @param instruction command entered by user
     * @param input       name of the three task types
     */
    public void addItem(String instruction, String input) {
        try {
            switch (instruction) {
            case "TODO":
                if (input.length() < 5) {
                    this.ui.error("todo");
                }
                this.storage.addTodoItem(input);
                break;

            case "DEADLINE":
                if (!input.contains("/by")) {
                    this.ui.error("deadline");
                }
                final String[] deadline_part = input.substring(9, input.length()).split("/by ");
                this.storage.addDeadlineItem(deadline_part[0], deadline_part[1]);
                break;

            case "EVENT":
                boolean from = input.contains("/from");
                boolean to = input.contains("/to");

                if ((!from || !to) || !(from && to)) {
                    this.ui.error("event");
                }
                final String[] event_part = input.substring(6, input.length()).split("/from ");
                String[] range = event_part[1].split("/to ");
                this.storage.addEventItem(event_part[0], range[0], range[1]);
                break;

            default:
                System.out.println("Should not print anything");
            }

        } catch (TaskException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Object pointing to null, please check code");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Check if the index is within the size of the array");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Nothing to mark/unmark!");
        }
    }

    /**
     * Finds items that contains word enter by user
     *
     * @param input to be entered by the users
     */
    public String findItem(String input) {
        return this.storage.findListItem(input);
    }
}

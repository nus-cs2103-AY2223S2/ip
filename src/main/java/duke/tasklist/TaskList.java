package duke.tasklist;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;
import duke.exceptions.TaskException;
import duke.storage.Storage;
import duke.ui.Ui;


public class TaskList {

    private Storage storage;
    private Ui ui;

    public TaskList(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
    }

    public void printList() {
        this.ui.listMessage();
        this.storage.displayList();
    }

    public void markItem(int index) {
        this.storage.markListItem(index);
    }

    public void unmarkItem(int index) {
        this.storage.unmarkListItem(index);
    }

    public void deleteTask(int index) {
        this.storage.deleteListItem(index);
    }

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
                    String[] deadline_part = input.substring(9, input.length()).split("/by ");
                    this.storage.addDeadlineItem(deadline_part[0], deadline_part[1]);
                    break;

                case "EVENT":
                    boolean from = input.contains("/from");
                    boolean to = input.contains("/to");

                    if ((!from || !to) || !(from && to)) {
                        this.ui.error("event");
                    }
                    String[] event_part = input.substring(6, input.length()).split("/from ");
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

}

import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    // Enum list to contain instructions to be entered by user to control system
    public enum Instructions {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    private Scanner sc = new Scanner(System.in);
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, this.ui);
        this.tasks = new TaskList(this.storage, this.ui);
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").userInputs();
    }

    // Allow users to add, mark and un-mark, delete, add tasks (to-do, deadline,
    // event) or show items in a list
    public void userInputs() {

        this.ui.welcomeMessage();
        
        this.storage.loadFileData();

        try {
            while (true) {
                String input = sc.nextLine();
                String[] part = input.split(" ");
                int index = 0;
                String first_word = part[0].toUpperCase();

                if (part[0].equals("mark") || part[0].equals("unmark") || part[0].equals("delete")) {
                    index = Integer.parseInt(part[1]) - 1;
                }

                if (!Arrays.stream(Instructions.values()).anyMatch(x -> (x.toString()).equals(first_word))) {
                    this.ui.error("default");
                }

                Instructions instruction = Instructions.valueOf(first_word);

                switch (instruction) {
                    // Exit the system upon entering
                    case BYE:
                        this.ui.byeMessage();
                        break;

                    // Display a list of tasks that shows its completion and types
                    case LIST:
                        tasks.printList();
                        break;

                    // Mark to complete the task, the second bracket will show a cross
                    case MARK:
                        tasks.markItem(index);
                        break;

                    // Un-mark to redo the completion of the task, the cross will be
                    // removed from the second bracket
                    case UNMARK:
                        tasks.unmarkItem(index);
                        break;

                    // Add task of type (To do/ deadline/ event)
                    case TODO:
                    case DEADLINE:
                    case EVENT: {
                        tasks.addItem(instruction.toString() ,input);
                        break;
                    }

                    // Delete task from the list according to its numbering on the list
                    case DELETE:
                        tasks.deleteTask(index);
                        break;

                    // default will throw an exception in case switch-case is unable to find instruction
                    default:
                    this.ui.error("default");
                }

                this.storage.writeToFile();

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

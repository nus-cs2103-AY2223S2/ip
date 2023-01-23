import java.util.Scanner;


public class Duke {

//    private final ArrayList<Task> storage = new ArrayList<>();
    private TaskList tasklist;

    private Storage storage;

    private final Scanner sc = new Scanner(System.in);

    public void greet() {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\nWhat can I do for you?");
        separator();
    }

    public void exit() {
        separator();
        System.out.println("\tBye. Hope to see you again soon!");
        separator();
        System.exit(0);
    }

    public static void separator() {
        System.out.println("---------------------------------------------------------------");
    }




    public static void main(String[] args) throws DukeException {
        Duke dukeList = new Duke();
        dukeList.greet();

        Storage storage = new Storage("data/duke.txt");
        storage.checkFileExists();
        TaskList tasklist = storage.loadFile();

        while (dukeList.sc.hasNextLine()) {
            try {

                String str = dukeList.sc.nextLine();
                String[] arr = str.split(" ", 2);
                boolean details = arr.length != 1;
                switch (arr[0]) {
                    case "bye":
                        dukeList.exit();
                        break;
                    case "list":
                        tasklist.list();
                        break;
                    case "mark":
                        if (!details) {
                            throw new DukeException("Please include the task index to mark");
                        } else {
                            tasklist.setTaskStatus(Integer.parseInt(arr[1]), true);
                            break;
                        }
                    case "unmark":
                        if (!details) {
                            throw new DukeException("Please include the task index to unmark.");
                        }
                        tasklist.setTaskStatus(Integer.parseInt(arr[1]), false);
                        break;
                    case "delete":
                        if (!details) {
                            throw new DukeException("Please include the task index to delete.");
                        }
                        tasklist.delete(Integer.parseInt(arr[1]));
                        break;
                    case "todo":
                        if (!details) {
                            throw new DukeException("Please include the todo details.");
                        }
                        tasklist.addToDo(arr[1]);
                        break;
                    case "deadline":
                        if (!details) {
                            throw new DukeException("Please include the deadline details.");
                        }
                        String[] descriptionBy = arr[1].split("/by", 2);
                            if (descriptionBy.length == 1) {
                                throw new DukeException("Please insert deadline date after /by");
                            }

                        tasklist.addDeadline(descriptionBy[0], descriptionBy[1]);
                        break;
                    case "event":
                        if (!details) {
                            throw new DukeException("Please include the event details.");
                        }
                        String[] descriptionOthers = arr[1].split("/from", 2);
                        if (descriptionOthers.length == 1) {
                            throw new DukeException("Please insert the date the event takes place from, after /from ");
                        }
                        String[] fromTo = descriptionOthers[1].split("/to", 2);
                        if (fromTo.length == 1) {
                            throw new DukeException("Please insert the date the event takes place until, after /to ");
                        }
                        tasklist.addEvent(descriptionOthers[0], fromTo[0], fromTo[1]);
                        break;
                    default:
                        throw new DukeException("Sorry, I don't know what that means.");
                }
            } catch (DukeException e) {
                separator();
                System.out.println("\t" + e);
                separator();
            }

            // handle changes to arraylist
            storage.writeFile(tasklist);

        }
    }
}

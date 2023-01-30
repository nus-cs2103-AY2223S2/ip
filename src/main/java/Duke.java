import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Parser;
import duke.DukeException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String fileName){
        ui = new Ui("Duke!");
        storage = new Storage(fileName);
        parser = new Parser();
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            //UI can show loading error
            e.getMessage();
            tasks = new TaskList();
        }
    }

    public void runDuke() {
        ui.printGreetings();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            //parser takes the command here and does what it needs to?
            //Let parser add task to tasklist?
            try {
                parser.executeCommand(fullCommand, tasks, storage, ui);
            } catch (DukeException e) {
                e.getMessage();
            }
            isExit = parser.isExit(fullCommand);
        }
        ui.closeScanner();
        ui.printExit();
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").runDuke();
//        Scanner sc = new Scanner(System.in);
//        ArrayList<Task> tasks = new ArrayList<>();
//        Storage s = new Storage("test.txt");
//        Duke.TaskList tasksL = new Duke.TaskList();
//        try {
//            tasksL = s.load();
//        } catch (DukeException e) {
//            e.printStackTrace();
//        }
//
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Welcome to: \n" + logo);
//        System.out.println(
//                "    ____________________________________________________________" +
//                "\n     Hello! I'm Duke" +
//                "\n     What can I do for you?" +
//                "\n    ____________________________________________________________");
//
//        String input = sc.nextLine();
//        String[] inputArr = input.split(" ");
//        int selectedNum;
//        int numTasks = 0;
//
//        while (!inputArr[0].equalsIgnoreCase("bye")) {
//            try {
//                switch (inputArr[0]) {
//                    case "list":
//                        tasksL.printTask();
//                        break;
//                    case "todo":
//                        String todoDesc;
//                        try {
//                            todoDesc = input.split(" ", 2)[1];
//                        } catch (Exception e) {
//                            throw new DukeException("Description of todo cannot be empty!!");
//                        }
//                        tasksL.addTask(todoDesc, false);
//                        tasksL.printNewestTask();
//                        s.save(tasksL);
//                        break;
//                    case "deadline":
//                        String deadlineInput;
//                        try {
//                            deadlineInput = input.split(" ", 2)[1];
//                        } catch (Exception e) {
//                            throw new DukeException("Description of deadline cannot be empty!!");
//                        }
//                        String[] deadlineDesc = deadlineInput.split(" /by ");
//                        tasksL.addTask(deadlineDesc[0], deadlineDesc[1], false);
//                        tasksL.printNewestTask();
//                        s.save(tasksL);
//                        break;
//                    case "event":
//                        String eventInput;
//                        try {
//                            eventInput = input.split(" ", 2)[1];
//                        } catch (Exception e) {
//                            throw new DukeException("Description of event cannot be empty!!");
//                        }
//                        String[] eventDescArr = eventInput.split(" /from ");
//                        String eventDesc = eventDescArr[0];
//                        String[] eventTimeArr = eventDescArr[1].split(" /to ");
//                        String eventFrom = eventTimeArr[0];
//                        String eventTo = eventTimeArr[1];
//                        tasksL.addTask(eventDesc, eventFrom, eventTo, false);
//                        tasksL.printNewestTask();
//                        s.save(tasksL);
//                        break;
//                    case "mark":
//                        selectedNum = Integer.parseInt(inputArr[1]);
//                        tasksL.markTask(selectedNum);
//                        s.save(tasksL);
//                        break;
//                    case "unmark":
//                        selectedNum = Integer.parseInt(inputArr[1]);
//                        tasksL.unMarkTask(selectedNum);
//                        s.save(tasksL);
//                        break;
//                    case "delete":
//                        int numToDelete;
//                        try {
//                            numToDelete = Integer.parseInt(input.split(" ", 2)[1]);
//                        } catch (Exception e) {
//                            throw new DukeException("Please enter a valid number to delete!");
//                        }
//                        tasksL.deleteTask(numToDelete);
//                        s.save(tasksL);
//                        break;
//                    case "Storage":
//                        //System.out.println("I RAN HERE!");
//                        tasksL = s.load();
//                        break;
//                    case "Save":
//                        s.save(tasksL);
//                        break;
//                    default:
//                        throw new DukeException("I don't get it!");
//                }
//            } catch (DukeException d) {
//                System.out.println(d.getMessage());
//            }
//
//            input = sc.nextLine();
//            inputArr = input.split(" ");
//        }
//
//        System.out.println("    ____________________________________________________________\n" +
//                "     Bye. Hope to see you again soon!\n" +
//                "    ____________________________________________________________");
//
//        sc.close();

    }
}

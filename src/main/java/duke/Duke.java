package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.sayHello();
        try {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String command = scanner.nextLine();
                String typeOfCommand = command.split(" ")[0];
                switch (typeOfCommand) {
                case "bye":
                    ui.sayBye();
                    break;
                case "list":
                    tasks.printTaskList();
                    break;
                case "mark":
                    int indexOfTaskToMarkDone = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task markedTask = tasks.markTaskAsDone(indexOfTaskToMarkDone);
                    ui.showMarkingTaskDone(markedTask);
                    break;
                case "unmark":
                    int indexOfTaskToMarkUndone = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task unmarkedTask = tasks.markTaskAsUndone(indexOfTaskToMarkUndone);
                    ui.showMarkingTaskUndone(unmarkedTask);
                    break;
                case "todo":
                    Task newTodo = Parser.makeTodoFromCommand(command);
                    tasks.addTask(newTodo);
                    ui.showAddingNewTask(newTodo, tasks);
                    break;
                case "deadline":
                    Task newDeadline = Parser.makeDeadlineFromCommand(command);
                    tasks.addTask(newDeadline);
                    ui.showAddingNewTask(newDeadline, tasks);
                    break;
                case "event":
                    Task newEvent = Parser.makeEventFromCommand(command);
                    tasks.addTask(newEvent);
                    ui.showAddingNewTask(newEvent, tasks);
                    break;
                case "delete":
                    int indexOfTaskToDelete = Integer.parseInt(command.split(" ")[1]);
                    Task taskToDelete = tasks.deleteTask(indexOfTaskToDelete);
                    ui.showDeletingTask(taskToDelete, tasks);
                    break;
                default:
                    throw new DukeException("Command not recognised.");
                }
                if (typeOfCommand.equals("bye")) {
                    break;
                }
                storage.updateTaskList(tasks);
                ui.askForNextCommand();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Something went wrong while handling this task.");
        } catch (IOException e) {
            System.out.println("Something went wrong. ):");
        }
    }

    public static void main(String[] args){
        new Duke("data/tasks.txt").run();
//        try {
//            File data = new File("data");
//            if (!data.exists()) {
//                data.mkdir();
//                System.out.printf("Create folder: %s\n", data.getName());
//            }
//
//            File dukeTxt = new File("data/Duke.txt");
//            if (dukeTxt.createNewFile()) {
//                System.out.printf("Create file: %s\n", dukeTxt.getName());
//            }
//        } catch (IOException e) {
//            System.out.printf("Something went wrong ): %s", e);
//        }
//
//        System.out.println("Hello! I'm Dupe\nWhat can I do for you?");
//
//        try {
//            InputStreamReader isr = new InputStreamReader(System.in);
//            BufferedReader br = new BufferedReader(isr);
//            ArrayList<Task> tasks = new ArrayList<>();
//            String line = br.readLine();
//            while (!line.equals("bye")) {
//                String firstWord = line.split(" ")[0];
//                if (line.equals("list")) {
//                    printInListFormat(tasks);
//                } else if (firstWord.equals("mark")) {
//
//
//
//                } else if (firstWord.equals("unmark")) {
//                    Task toMarkUndone = tasks.get();
//                    toMarkUndone.markUndone();
//                    System.out.printf("OK, I've marked this task as not done yet:\n %s\n", toMarkUndone.description());
//                } else if (firstWord.equals("todo")) {
//                    Task newTask =
//                    tasks.add(newTask);
//                    System.out.printf("Got it. I've added this task:\n %s \n Now you have %d tasks in the list.\n",
//                            newTask.description(), tasks.size());
//                } else if (firstWord.equals("deadline")) {
//
//                    Task newTask = new Deadlines(taskName, taskDeadline);
//                    tasks.add(newTask);
//                    System.out.printf("Got it. I've added this task:\n %s \n Now you have %d tasks in the list.\n",
//                            newTask.description(), tasks.size());
//                } else if (firstWord.equals("event")) {
//
//                    Task newTask =
//                    tasks.add(newTask);
//                    System.out.printf("Got it. I've added this task:\n %s \n Now you have %d tasks in the list.\n",
//                            newTask.description(), tasks.size());
//                } else if (firstWord.equals("delete")) {
//                    Task toDelete = tasks.get(Integer.parseInt(line.split(" ")[1]) - 1);
//                    tasks.remove(Integer.parseInt(line.split(" ")[1]) - 1);
//                    System.out.printf("Noted. I've removed this task:\n %s \n Now you have %d tasks in the list.\n",
//                            toDelete.description(), tasks.size());
//                } else {
//                    throw new DukeException("unknown problem");
//                }
//                updateDukeTxt(tasks);
//                line = br.readLine();
//            }
//            System.out.println("Bye. Hope to see you again soon!");
//        }
//        catch (IOException ioe) {
//            System.out.println("IO Exception raised");
//        } catch (DukeException e) {
//            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//        } catch (StringIndexOutOfBoundsException oobe) {
//            System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
//        }
    }
}

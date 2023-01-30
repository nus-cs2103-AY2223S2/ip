package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;

public class Duke {

    private TaskManagement taskManager; // to manage saved data
    private TaskStorage taskStorage;
    private Ui ui;
    public Duke(String filepath) {

        taskStorage = new TaskStorage();
        ui = new Ui();
        try {
            taskManager = new TaskManagement(filepath);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskManager = new TaskManagement();
        }
    }
    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringBuilder sb = new StringBuilder();

        ui.showWelcome();
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "-------------------------------";
//        System.out.println(line);
//        System.out.println("Hiii Im\n" + logo);
//        System.out.println("What can I do for you hmm?");
//        System.out.println(line);
        String inp;

        /**
         * Simply echoes commands entered by the user,
         * and exits when the user types "bye".
         */
        while (true) {
            inp = br.readLine();
            String[] input = inp.split(" ");
            ui.showLine();
            switch(input[0]) {
                case "list":
                    System.out.println("Tasks:");
                    //System.out.println(taskstorage);
                    for (int i = 0; i < taskStorage.noTasks(); i++) {
                        System.out.print(i + 1 + ".");
                        System.out.println(taskStorage.getTask(i));
                    }
                    break;

                case "bye":
                    System.out.println("Byeee! Hope to see you again! Signing off, duke.Duke.");
                    break;

                case "mark":
                    //System.out.println("Nice! I've marked this task as done:");
                    try {
                        int taskNo = Integer.parseInt(input[1]);
                        if (taskNo > taskStorage.noTasks() || taskNo <= 0) {
                            throw new DukeException("Give a vaild number");
                        }
                        taskStorage.getTask(taskNo - 1).markAsDone();
                    } catch (NumberFormatException e) {
                        System.out.println("Number should be typed in");
                    } catch (DukeException e){
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }

                case "unmark":
                    try {
                        int taskNoUnmark = Integer.parseInt(input[1]);
                        if (taskNoUnmark > taskStorage.noTasks() || taskNoUnmark <= 0) {
                            throw new DukeException("Give a valid number");
                        }
                        taskStorage.getTask(taskNoUnmark - 1).markAsUnDone();
                    } catch (NumberFormatException e) {
                        System.out.println("Number should be typed in");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }

                case "todo":
                    try {
                        String[] inpTodo = inp.split(" ");
                        if (inpTodo.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        String todoTask = inp.substring(5);
                        Task todo = new Todo(todoTask);
                        taskStorage.addTask(todo);
                        break;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }


                case "deadline":
                    try {
                        if (inp.length() == 8) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline must have a date.");
                        }
                        String deadlineStr = inp.substring(9);
                        String[] inputDeadline = deadlineStr.split("/");
                        if (inputDeadline.length != 2) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline must have a date.");
                        }
                        String deadLineTaskStr = inputDeadline[0];
                        String end = inputDeadline[1].substring(3);
                        Task deadLineTask = new Deadline(deadLineTaskStr, end);
                        //System.out.println(end);
                        taskStorage.addTask(deadLineTask);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }


                case "event":
                    try {
                        if (inp.length() == 6) {
                            throw new DukeException("☹ OOPS!!! The description of an event must have a start and end time.");
                        }
                        String eventStr = inp.substring(6);

                        String[] eventStrsplit = eventStr.split("/");
                        if (eventStrsplit.length != 3) {
                            throw new DukeException("☹ OOPS!!! The description of an event must have a start and end time.");
                        }
                        String eventTaskStr = eventStrsplit[0];
                        String eventBegin = eventStrsplit[1].substring(5);
                        eventBegin = eventBegin.substring(0, eventBegin.length() - 1);
                        String eventEnd = eventStrsplit[2].substring(3);
                        Task eventTask = new Event(eventTaskStr, eventBegin, eventEnd);
                        taskStorage.addTask(eventTask);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }

                case "delete":
                    try {
                        int taskNo = Integer.parseInt(input[1]);
                        if (taskNo > taskStorage.noTasks() || taskNo <= 0) {
                            throw new DukeException("Give a vaild number");
                        }
                        Task eventTask = taskStorage.getTask(taskNo - 1);
                        taskStorage.deleteTask(eventTask);
                    } catch (NumberFormatException e) {
                        System.out.println("Number should be typed in");
                    } catch (DukeException e){
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }

                default:
                    DukeException dukeException = new DukeException();
                    System.out.println(dukeException.getMessage());


            }
            taskManager.save(taskStorage);
            ui.showLine();
            if (inp.equals("bye")) {
                break;
            }

        }
    }
    public static void main(String[] args) throws IOException, DukeException {
        new Duke("./data/tasks.txt").run();
    }
}

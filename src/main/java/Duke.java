import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
public class Duke {

    //private static Task[] taskstorage = new Task[101];
    private static ArrayList<Task> taskstorage = new ArrayList<Task>(); // Create an ArrayList object
    private static int ind = 0;
    public static void addTask(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        taskstorage.add(t);
        ind++;
        System.out.println("Now you have " + ind + " task(s) in the list.");
    }

    public static void deleteTask(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        taskstorage.remove(t);
        ind--;
        System.out.println("Now you have " + ind + " task(s) in the list.");
    }

    public static void main(String[] args) throws IOException, DukeException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringBuilder sb = new StringBuilder();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "-------------------------------";
        System.out.println(line);
        System.out.println("Hiii Im\n" + logo);
        System.out.println("What can I do for you hmm?");
        System.out.println(line);
        String inp;

        /**
         * Simply echoes commands entered by the user,
         * and exits when the user types "bye".
         */
        //int ind = 1;
        while (true) {
            inp = br.readLine();
            String[] input = inp.split(" ");
            System.out.println(line);
            switch(input[0]) {
                case "list":
                    System.out.println("Tasks:");
                    //System.out.println(taskstorage);
                    for (int i = 0; i < ind; i++) {
                        System.out.print(i + 1 + ".");
                        System.out.println(taskstorage.get(i));
                    }
                    break;

                case "bye":
                    System.out.println("Byeee! Hope to see you again! Signing off, Duke.");
                    break;

                case "mark":
                    //System.out.println("Nice! I've marked this task as done:");
                    try {
                        int taskNo = Integer.parseInt(input[1]);
                        if (taskNo >= ind || taskNo <= 0) {
                            throw new DukeException("Give a vaild number");
                        }
                        taskstorage.get(taskNo - 1).markasDone();
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
                        if (taskNoUnmark >= ind || taskNoUnmark <= 0) {
                            throw new DukeException("Give a valid number");
                        }
                        taskstorage.get(taskNoUnmark - 1).markasUnDone();
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
                        addTask(todo);
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
                        addTask(deadLineTask);
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
                        String eventEnd = eventStrsplit[2].substring(3);
                        Task eventTask = new Event(eventTaskStr, eventBegin, eventEnd);
                        addTask(eventTask);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }

                case "delete":
                    try {
                        int taskNo = Integer.parseInt(input[1]);
                        if (taskNo > ind || taskNo <= 0) {
                            throw new DukeException("Give a vaild number");
                        }
                        Task eventTask = taskstorage.get(taskNo - 1);
                        deleteTask(eventTask);
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
            System.out.println(line);
            if (inp.equals("bye")) {
                break;
            }

        }
    }
}

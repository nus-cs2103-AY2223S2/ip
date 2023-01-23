import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) throws DukeException {
        // store: storing text entered by user
        TaskList store;
        Storage hardDrive = new Storage("data/file.txt");
        // create store and load initial data
        try {
            store = new TaskList(hardDrive.loadData());
        } catch (DukeException e) {
            store = new TaskList(new ArrayList<>());
        }

        // greetings
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");

        // obtaining first input by user
        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();

        // exit loop when user input is bye
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < store.getSize(); i ++) {
                    System.out.print(i + 1);
                    System.out.println(". " + store.getTask(i).toString());
                }
            }
            else if (userInput.split(" ", 2)[0].equals("todo")) {
                try {
                    store.addTask(new ToDo(userInput.split(" ", 2)[1]));
                } catch(Exception e) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
            }
            else if (userInput.split(" ", 2)[0].equals("deadline")) {
                try {
                    String[] input = userInput.split(" ", 2)[1].split(" /by ", 2);
                    // parser technically here, do a try catch and if error returns formatting issue
                    // default format of yyyy-mm-dd
                    // If you want to change how it takes input, add a formatter to the parse function
                    LocalDate deadline = LocalDate.parse(input[1]);
                    store.addTask(new Deadline(input[0], deadline));
                } catch(Exception e) {
                    throw new DukeException("The description and date of a deadline cannot be empty. " +
                            "Make sure dates are in this format yyyy-mm-dd.");
                }
            }
            else if (userInput.split(" ", 2)[0].equals("event")) {
                try {
                    String[] input = userInput.split(" ", 2)[1].split(" /", 3);
                    LocalDate startDate = LocalDate.parse(input[1].split("from ", 2)[1]);
                    LocalDate endDate = LocalDate.parse(input[2].split("to ", 2)[1]);
                    store.addTask(new Event(input[0], startDate, endDate));
                } catch (Exception e) {
                    throw new DukeException("The description, start time, and end time of a event cannot be empty. " +
                            "Make sure dates are in this format yyyy-mm-dd.");
                }
            } else if (userInput.split(" ", 2)[0].equals("mark")) {
                store.markTask(Integer.parseInt(userInput.split(" ", 2)[1]) - 1);
            } else if (userInput.split(" ", 2)[0].equals("unmark")) {
                store.unmarkTask(Integer.parseInt(userInput.split(" ", 2)[1]) - 1);
            } else if (userInput.split(" ", 2)[0].equals("delete")) {
                int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                store.removeTask(index);
            }
            else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            myObj = new Scanner(System.in);
            userInput = myObj.nextLine();
        }
        // prints exit statement
        // saves task list data
        hardDrive.saveData(store);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
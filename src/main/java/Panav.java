import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

/*
public class Panav {
    public static void main(String[] args) throws InvalidInputException, ToDoDescriptionException {

        Scanner sc = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello hello! I'm Panav");
        System.out.println("What's up bro");
        System.out.println("____________________________________________________________");

        ArrayList<Task> list = null;
        String filePath = "C:\\Users\\panav\\OneDrive\\Desktop\\CS2103T\\ip\\src\\main\\java\\data\\panav.txt";
        try {
            list = readFromFile(filePath);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ToDoDescriptionException e) {
            System.out.println(e.getMessage());
        }

        String command = sc.nextLine();
        int counter = list.size();
        while(true) {
            try {
                String[] temp = command.split(" ");
                String first = temp[0];
                int len = command.length();
                switch (first) {
                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println((i + 1) + ". " + list.get(i));
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "todo":
                    String todomessage = command.replace("todo", "").trim();
                    Task todo = new ToDo(todomessage);
                    list.add(todo);
                    counter++;
                    writeToFile(list, filePath);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                    break;
                case "deadline":
                    int index_by = command.indexOf("/by");
                    String by = command.substring(index_by + 4);
                    by = formatDate(by);
                    String deadlinemessage = command.substring(9, index_by - 1);
                    Task deadline = new Deadline(deadlinemessage, by);
                    list.add(deadline);
                    counter++;
                    writeToFile(list, filePath);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case "event":
                    int fromindex = 0, toindex = 0;
                    String eventmessage = "", from = "", to = "";
                    for (int j = 0; j < temp.length; j++) {
                        if (temp[j].compareTo("/from") == 0) {
                            fromindex = j;
                        }
                        if (temp[j].compareTo("/to") == 0) {
                            toindex = j;
                        }
                    }
                    for (int j = 0; j < temp.length; j++) {
                        if (j < fromindex && j > 0) {
                            eventmessage += temp[j] + " ";
                        }
                        if (j > fromindex && j < toindex) {
                            from += temp[j] + " ";
                        }
                        if (j > toindex) {
                            to += temp[j];
                        }
                    }

                    Task event = new Event(eventmessage, from, to);
                    list.add(event);
                    counter++;
                    writeToFile(list, filePath);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case "mark":
                    //Fallthrough
                case "unmark":
                    int num = readNumber(command, counter);
                    Task cur = list.get(num - 1);
                    System.out.println("____________________________________________________________");
                    if (first.compareTo("mark") == 0) {
                        System.out.println("Nice! I've marked this task as done:");
                        cur.markAsDone();
                    } else {
                        System.out.println("OK, I've marked this task as not done yet:");
                        cur.markAsNotDone();
                    }
                    System.out.println(cur);
                    System.out.println("____________________________________________________________");
                    writeToFile(list, filePath);
                    break;
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    System.exit(0);
                    break;
                case "delete":
                    int deleteIndex = readNumber(command, counter);
                    Task removed = list.remove(deleteIndex - 1);
                    counter--;
                    writeToFile(list, filePath);
                    System.out.println("____________________________________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removed);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                default:

                    throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (InvalidInputException e) {
                System.out.println(e);
            } catch (ToDoDescriptionException e) {
                System.out.println(e);
            } catch(InvalidNumberException e) {
                System.out.println(e);
            } catch(IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

            command = sc.nextLine();
        }

    }

    */
/**
     * Returns the index number for commands which manipulate the list.
     *
     * @param command The command which is manipulating list.
     * @param counter The number of elements in the list.
     * @return Index number in command.
     * @throws InvalidNumberException If the index doesn't exist.
     *//*

    public static int readNumber(String command, int counter) throws InvalidNumberException{
        int number = Integer.parseInt(String.valueOf(command.charAt(command.length() - 1)));
        if (number > counter || number < 1) {
            throw new InvalidNumberException("Oops!! There is no such index number in your list");
        } else {
            return number;
        }
    }

    */
/**
     * Reads the existing list of tasks from text file.
     *
     * @param filePath path of file to be read from
     * @throws FileNotFoundException if text file doesn't exist.
     * @throws ToDoDescriptionException if todo is missing description.
     *//*

    public static ArrayList<Task> readFromFile(String filePath) throws FileNotFoundException ,
            ToDoDescriptionException {
        ArrayList<Task> list = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String command = s.nextLine();
            String[] arr = command.split(" ~ ");
            Task curr;
            if (arr[0].compareTo("T") == 0) {
                curr = new ToDo(arr[2]);
                if (arr[1].compareTo("1") == 0) {
                    curr.markAsDone();
                }
            } else if (arr[0].compareTo("D") == 0) {
                curr = new Deadline(arr[2], arr[3]);
                if (arr[1].compareTo("1") == 0) {
                    curr.markAsDone();
                }
            } else {
                curr = new Event(arr[2], arr[3], arr[4]);
                if (arr[1].compareTo("1") == 0) {
                    curr.markAsDone();
                }
            }
            list.add(curr);

        }
        return list;
    }

    */
/**
     * Writes the changes to the list to the file.
     * @param list the list containing the tasks.
     * @param filePath the path of the file to be written to.
     * @throws IOException in case if folder is not found or some other exception.
     *//*

    public static void writeToFile(ArrayList<Task> list, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String textToAdd = "";
        for (Task task : list) {
            String str = task.toString();
            int indexBy = str.indexOf("by:");
            int indexFrom = str.indexOf("from:");
            int indexTo = str.indexOf("to:");
            int check = task.isDone ? 1 : 0;
            int length = str.length();
            if (indexBy != -1) {
                textToAdd += String.format("%c ~ %d ~ %s ~ %s %n", str.charAt(1), check,
                        str.substring(7, indexBy - 2), str.substring(indexBy + 4, length - 1));
            } else if (indexFrom != -1) {
                textToAdd += String.format("%c ~ %d ~ %s ~ %s ~ %s %n", str.charAt(1), check,
                        str.substring(7, indexFrom - 1), str.substring(indexFrom + 6, indexTo - 1),
                        str.substring(indexTo + 4, length - 1));
            } else {
                textToAdd += String.format("%c ~ %d ~ %s %n", str.charAt(1), check, str.substring(7));
            }

        }
        fw.write(textToAdd);
        fw.close();
    }

    */
/**
     * Formats the date according to 'MMM dd yyyy' if it's given in the correct format of
     * 'yyyy-mm-dd', otherwise it just returns the string.
     * @param dateString the string to be formatted.
     * @return either the formatted date or the original string itself.
     *//*

    public static String formatDate(String dateString) {
        LocalDate d = null;
        String result = dateString;
        try {
            d = LocalDate.parse(dateString);
            result = d.format(DateTimeFormatter.ofPattern("MMM d yyyy")).toString();
            System.out.println("hiu");
        } catch (DateTimeParseException e){
            return result;
        }
        return result;
    }
}
*/
public class Panav {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Panav(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }


    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                if(c.toString().compareTo(ListCommand.COMMAND_WORD) != 0 ||
                        c.toString().compareTo(ExitCommand.COMMAND_WORD) != 0) {
                    storage.write(tasks);
                }
                isExit = c.isExit();
            } catch (DukeException e) {
               System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NullPointerException e) {
                continue;
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Panav("C:\\Users\\panav\\OneDrive\\Desktop\\CS2103T\\ip\\src\\main\\java\\data\\panav.txt").run();
    }
}


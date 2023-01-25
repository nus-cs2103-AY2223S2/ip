package duke;

import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String Indentation = " ";
    private static final String Horizontal = "____________________________________________________________";
    private static int count = 0;
    private static ArrayList<Task> listname;
    //private static Task[] listname;
    private static final String filePath = "data/duke.txt";

    public static void main(String[] args) throws IOException {
        //show logo
        //logo();
        //greeting
        greet();
        //input command
        Scanner str = new Scanner(System.in);
        //no more than 100 tasks
        //listname = new Task[100];
        listname = new ArrayList<>();
        String command;
        String[] words;
        String info;
        Task task = null;

        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                Task tasks;
                String data = sc.nextLine();
                String[] commandInFile = data.split(" \\| ");
                boolean isDoneInFile = commandInFile[1].equals("1");
                if (commandInFile[0].equals("T")) {
                    tasks = new Todo(commandInFile[2], true);
                    tasks.isDone = isDoneInFile;
                    listname.add(tasks);
                    count++;
                } else if (commandInFile[0].equals("D")) {
                    tasks = new Deadline(commandInFile[2], commandInFile[3], true);
                    tasks.isDone = isDoneInFile;
                    listname.add(tasks);
                    count++;
                } else if (commandInFile[0].equals("E")) {
                    tasks = new Event(commandInFile[2], commandInFile[3], commandInFile[4], true);
                    tasks.isDone = isDoneInFile;
                    listname.add(tasks);
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            new File(filePath).createNewFile();
        }

        do {
            command = str.nextLine().trim();
            words = command.split(" ");

            //if command is equal to bye, exit()
            //if command is not equal to bye, distinguish list or normal command
            if (!command.equals("bye")) {
                if (command.equals("list")) {
                    list();
                } else if (words[0].equals("mark")) {
                    try {
                        done(words[1]);
                        updateFile();
                    } catch (Exception e) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The index number cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                    }
                } else if (words[0].equals("unmark")) {
                    try {
                        undone(words[1]);
                        updateFile();
                    } catch (Exception e) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The The index number cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                    }
                } else if (words[0].equals("delete")) {
                    try{
                        delete(words[1]);
                        updateFile();
                    } catch (Exception e) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The index number cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                    }
                } else if (words[0].equals("todo")) {
                    try {
                        if (words[1].equals(null)) {
                            System.out.println(Indentation + Horizontal);
                            System.out.println("  ☹ OOPS!!! The description of a todo cannot be empty.");
                            System.out.println(Indentation + Horizontal);
                        }
                        info = command.substring(command.indexOf(" ") + 1);
                        //listname[count] = new Todo(info);
                        task = new Todo(info, false);
                        listname.add(task);
                        count++;
                    } catch (Exception e) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                    }

                } else if (words[0].equals("deadline")) {
                    try {
                        info = command.substring(command.indexOf(" ") + 1, command.indexOf(" /by"));
                        String deadline = command.substring(command.indexOf("/by") + 4);
                        //listname[count] = new Deadline(info, deadline);

                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("MM/dd/yyyy HHmm"));
                            LocalDateTime datetime1 = LocalDateTime.parse(deadline, formatter);
                            System.out.println(datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));

                            task = new Deadline(info,
                                    datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")), false);

                        } catch(DateTimeParseException e) {
                            System.out.println(deadline);
                            task = new Deadline(info, deadline, false);
                        }
                        listname.add(task);
                        count++;
                    } catch (Exception e) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                    }

                } else if (words[0].equals("event")) {
                    try {
                        info = command.substring(command.indexOf(" ") + 1, command.indexOf(" /from "));
                        String fromtime = command.substring(command.indexOf(" /from ") + 6, command.indexOf(" /to "));
                        String totime = command.substring(command.indexOf(" /to ")  + 4);
                        //listname[count] = new Event(info, fromtime, totime);
                        //task = new Event(info, fromtime, totime, false);
                        try {
                            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern((" MM/dd/yyyy HHmm"));
                            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern((" MM/dd/yyyy HHmm"));
                            LocalDateTime datetime1 = LocalDateTime.parse(fromtime, formatter1);
                            LocalDateTime datetime2 = LocalDateTime.parse(totime, formatter2);
                            //System.out.println(datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));
                            //System.out.println(datetime2.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));

                            task = new Event(info,
                                    datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                                    datetime2.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                                    false);

                        } catch(DateTimeParseException e) {
                            System.out.println(fromtime + 11);
                            task = new Event(info, fromtime, totime, false);
                        }

                        listname.add(task);
                        count++;
                    } catch (Exception e) {
                        System.out.println(Indentation + Horizontal);
                        System.out.println("  ☹ OOPS!!! The description of a event cannot be empty.");
                        System.out.println(Indentation + Horizontal);
                    }

                } else {
                    System.out.println(Indentation + Horizontal);
                    System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(Indentation + Horizontal);
                }
                if(task != null){
                    appendToFile(task.file());
                    task = null;
                }
            }
        } while (!command.equals("bye"));

        exit();
    }

    public static void logo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void greet() {
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Hello! I'm Duke");
        System.out.println(Indentation + "What can I do for you?");
        System.out.println(Indentation + Horizontal);
    }

    public static void exit() {
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Bye. Hope to see you again soon!");
        System.out.println(Indentation + Horizontal);
    }

    public static void list() {
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Here are the tasks in your list:");

        for (int i = 0; i < count; i++) {
            System.out.println(Indentation + (i + 1) + "." + listname.get(i).toString());
        }

        System.out.println(Indentation + Horizontal);
    }

    public static void done (String num) {
        int number = Integer.parseInt(num) - 1;
        //listname[number].isDone = true;
        listname.get(number).isDone = true;

        System.out.println(Indentation + Horizontal);
        System.out.println("Nice! I've marked this task as done:");

        System.out.println(Indentation + listname.get(number).toString());
        System.out.println(Indentation + Horizontal);
    }

    public static void undone(String num) {
        int number = Integer.parseInt(num) - 1;
        listname.get(number).isDone = false;

        System.out.println(Indentation + Horizontal);
        System.out.println("OK, I've marked this task as not done yet:");

        System.out.println(Indentation + listname.get(number).toString());
        System.out.println(Indentation + Horizontal);
    }

    public static void delete(String num) {
        int index = Integer.parseInt(num) - 1;
        try{
            if (!(listname.get(index)).equals(null)) {
                System.out.println(Indentation + Horizontal);
                System.out.println(Indentation + "Noted. I've removed this task:");

                System.out.println(Indentation + listname.get(index).toString());
                listname.remove(index);
                Task.taskNum--;
                count--;
                System.out.println(" Now you have " + Task.taskNum + " tasks in the list.");
                System.out.println(Indentation + Horizontal);
            }
        } catch (Exception e) {
            System.out.println(Indentation + Horizontal);
            System.out.println(" ☹ OOPS!!! I'm sorry, but the list is empty :-(");
            System.out.println(Indentation + Horizontal);
        }
    }

    private static void updateFile() throws IOException {
        FileWriter fw = new FileWriter(Duke.filePath);
        fw.write("");
        fw.close();
        if(count > 0) {
            for (int i = 0; i < count; i++) {
                appendToFile(listname.get(i).file());
            }
        }

    }
    //Java append to file using FileWriter
    private static void appendToFile(String info) throws IOException {
        //Constructs a FileWriter object given a file name
        // with a boolean indicating whether to append the data written
        FileWriter fw = new FileWriter(Duke.filePath, true);
        fw.write(info + "\n");
        fw.close();
    }
}

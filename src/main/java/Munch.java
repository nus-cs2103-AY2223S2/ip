import AddTasks.Deadlines;
import AddTasks.Events;
import AddTasks.Task;
import AddTasks.Todo;
import Exceptions.IncompleteInputException;
import Exceptions.InvalidInputException;
import Exceptions.MunchException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Munch {

    public static void welcomeMessage() {
        System.out.println("Hello! I am Munch! :)");
        System.out.println("How may I help you?");
        System.out.println("__________________________");
    }

    public static void wrongDateFormatMessage() {
        System.out.println("Wrong format for date! [Format: dd/MM/yyyy]");
    }

    public static LocalDate convert(String date) {
        DateTimeFormatter sf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.from(sf.parse(date));
        return localDate;
    }

    public static void main(String[] args) throws MunchException {

        welcomeMessage();
        ArrayList<Task> tasks = new ArrayList<>();
        // Loading file into UI
        tasks = FileSave.load(tasks);
        Boolean exit = true;
        Scanner text = new Scanner(System.in);
        while (exit) {
            try {
                String word = text.nextLine();
                // check for specific words : "mark" and "unmark"
                String[] words = word.split(" ");

                // exit program
                if (word.equals("bye")) {
                    System.out.println("See ya champ! Enjoy your day!");
                    FileSave.save(tasks);
                    exit = false;

                    // generate list of tasks
                } else if (word.equals("list")) {
                    System.out.println("Here are the task(s) in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                } else if (words[0].equals("mark") || words[0].equals("unmark")) {
                    int i = Integer.parseInt(words[1]) - 1;
                    tasks.get(i).wording(words[0]);

                } else if (words[0].equals("delete")) {
                    int i = Integer.parseInt(words[1]) - 1;
                    System.out.println("I've deleted this task for you!");
                    System.out.println(tasks.get(i));
                    tasks.remove(i);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list ~~");

                } else if (words[0].equals("todo")) {
                    String separator = "todo";
                    int sepPos = word.indexOf(separator);
                    String str = word.substring(sepPos + separator.length());
                    if (str.length() != 0) {
                        Todo todos = new Todo(str);
                        tasks.add(todos);
                        System.out.println("I've added this task for you!");
                        System.out.println(todos);
                        System.out.println("Now you have " + tasks.size() + " task(s) in the list ~~");
                    } else {
                        throw new IncompleteInputException();
                    }

                } else if (words[0].equals("deadline")) {
                    if (word.contains("deadline") && word.contains("/by")){
                        String separator1 = "deadline";
                        String separator2 = "/by";
                        int sepPos1 = word.indexOf(separator1);
                        int sepPos2 = word.indexOf(separator2);
                        String str = word.substring(sepPos1 + separator1.length(), sepPos2);
                        String date = word.substring(sepPos2 + 1 + separator2.length());
                        try {
                            LocalDate convertDate = convert(date);
                            Deadlines deadline = new Deadlines(str, convertDate);
                            tasks.add(deadline);
                            System.out.println("I've added this task for you!");
                            System.out.println(deadline);
                            System.out.println("Now you have " + tasks.size() + " task(s) in the list ~~");
                        } catch (DateTimeParseException e) {
                            wrongDateFormatMessage();
                        }

                    } else {
                        throw new IncompleteInputException();
                    }

                } else if (words[0].equals("event")) {
                    if (word.contains("event") && word.contains("/from") && word.contains("/to")){
                        String separator1 = "event";
                        String separator2 = "/from";
                        String separator3 = "/to";
                        int sepPos1 = word.indexOf(separator1);
                        int sepPos2 = word.indexOf(separator2);
                        int sepPos3 = word.indexOf(separator3);
                        String str = word.substring(sepPos1 + separator1.length(), sepPos2);
                        String from = word.substring(sepPos2 + 1 + separator2.length(), sepPos3 - 1);
                        String to = word.substring(sepPos3 + 1 + separator3.length());
                        try {
                            LocalDate convertFrom = convert(from);
                            LocalDate convertTo = convert(to);
                            Events event = new Events(str, convertFrom, convertTo);
                            tasks.add(event);
                            System.out.println("I've added this task for you :)");
                            System.out.println(event);
                            System.out.println("Now you have " + tasks.size() + " task(s) in the list ~~");
                        } catch (DateTimeParseException e) {
                            wrongDateFormatMessage();
                        }

                    } else {
                        throw new IncompleteInputException();
                    }

                } else {
                    throw new InvalidInputException();
                }
                System.out.println("__________________________");
                // Saving file into hard disk after every command
                FileSave.save(tasks);
            } catch (IncompleteInputException | InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        text.close();
    }
}

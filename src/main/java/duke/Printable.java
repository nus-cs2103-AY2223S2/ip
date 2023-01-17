package duke;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

class Printable {

    static String INITIAL_GREETING = "Hello";
    static String FINAL_GREETING = "Bye";
    static String SHOW_TASKS = "list";
    static String TERMINATE = "exit";
    static String MARK = "mark";
    static String UNMARK = "unmark";
    static String TODO = "todo";
    static String DEADLINE = "deadline";
    static String EVENT = "event";
    static String ILLEGAL_COMMAND = "command not recognised";
    static String DELETE = "delete";
    static int DECREMENT = 1;
    
    static List<String> INVALID_COMMANDS = Arrays.asList("blah","todo","deadline","event");

    static void greet() {
        System.out.println(INITIAL_GREETING);
    }

    static void exit() {
        System.out.println(FINAL_GREETING);
        System.exit(0);
    }

    static Tasks<Task> mark(Scanner sc, Tasks<Task> tasks) {
        int taskPosition = sc.nextInt() - DECREMENT;
        return tasks.set(taskPosition, tasks.get(taskPosition).markAsDone());
    }

    static Tasks<Task> unmark(Scanner sc, Tasks<Task> tasks) {
        int taskPosition = sc.nextInt() - DECREMENT;
        return tasks.set(taskPosition, tasks.get(taskPosition).markAsUndone());
    }

    static Tasks<Task> delete(Scanner sc, Tasks<Task> tasks) {
        int taskPosition = sc.nextInt() - DECREMENT;
        return tasks.removeTask(taskPosition);
    }

    static Tasks<Task> toDo(Scanner sc, Tasks<Task> tasks) {
       String description = sc.nextLine();
       //Carl Smotricz
       if (description.trim().length() == 0) {
           throw new DukeException("Todo must not be empty");
       }
       Task newTask = new Todos(description);
       return tasks.add(newTask);
    }

    static Tasks<Task> deadline(Scanner sc, Tasks<Task> tasks) {
        String description = sc.nextLine();
        //Carl Smotricz
        if (description.trim().length() == 0) {
            throw new DukeException("Deadline must not be empty");
        }
        try {
            String[] dateRange = description.split("/");
            Task newTask = new Deadline(dateRange[0],dateRange[1].replaceFirst("by",""));
            return tasks.add(newTask);
        } catch (DukeException e) {
            System.out.println("Todo must not be empty");
        }
        return tasks;
    }

    static Tasks<Task> events(Scanner sc, Tasks<Task> tasks) {
        String description = sc.nextLine();
        //Carl Smotricz
        if (description.trim().length() == 0) {
            throw new DukeException("Event must not be empty");
        }
        String[] dateRange = description.split("/");
        Task newTask = new Events(dateRange[0],dateRange[1].replaceFirst("from",""),dateRange[2].replaceFirst("to",""));
        return tasks.add(newTask);
    }

}

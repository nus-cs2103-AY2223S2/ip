package duke;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

class Parser {

    static String INITIAL_GREETING = "Welcome to Manchester United. I am Alex Ferguson. How may I help you";
    static String FINAL_GREETING = "SUIII, Bye";
    
    static String ILLEGAL_COMMAND = "Alex Ferguson does not recognise this command. Perhaps you could try valid command init?";
    static String MARK_COMMAND = "SUI, I have marked this task from the training room: ";
    static String UNMARK_COMMAND = "SUI, I have unmarked this task from the training room: ";
    static String DELETE_COMMAND = "Tasks successfully deleted. SUI.";

    static String SHOW_TASKS = "list";
    static String TERMINATE = "exit";
    static String MARK = "mark";
    static String UNMARK = "unmark";
    static String TODO = "todo";
    static String DEADLINE = "deadline";
    static String EVENT = "event";
    static String DELETE = "delete";

    static int DECREMENT = 1;
    
    static String MARK_SYMBOL = "X";
    static String TODO_SYMBOL = "T";
    static String DEADLINE_SYMBOL = "D";
    static String EVENT_SYMBOL = "E";

    static List<String> INVALID_COMMANDS = Arrays.asList("blah","todo","deadline","event");

    static void greet() {
        System.out.println(INITIAL_GREETING);
    }

    static void exit() {
        System.out.println(FINAL_GREETING);
        System.exit(0);
    }
    
    static TaskList<Task> mark(int taskPosition, TaskList<Task> tasks) {
        System.out.println(MARK_COMMAND + tasks.get(taskPosition));
        return tasks.set(taskPosition, tasks.get(taskPosition).markAsDone());
    }

    static TaskList<Task> mark(Scanner sc, TaskList<Task> tasks) {
        int taskPosition = sc.nextInt() - DECREMENT;
        System.out.println(MARK_COMMAND + tasks.get(taskPosition));
        return tasks.set(taskPosition, tasks.get(taskPosition).markAsDone());
    }
    
    static TaskList<Task> unmark(int taskPosition, TaskList<Task> tasks) {
        System.out.println(UNMARK_COMMAND + tasks.get(taskPosition));
        return tasks.set(taskPosition, tasks.get(taskPosition).markAsUndone());
    }

    static TaskList<Task> unmark(Scanner sc, TaskList<Task> tasks) {
        int taskPosition = sc.nextInt() - DECREMENT;
        System.out.println(UNMARK_COMMAND + tasks.get(taskPosition));
        return tasks.set(taskPosition, tasks.get(taskPosition).markAsUndone());
    }
    
    static TaskList<Task> delete(int taskPosition, TaskList<Task> tasks) {
        System.out.println(DELETE_COMMAND + tasks.get(taskPosition));
        return tasks.removeTask(taskPosition);
    }

    static TaskList<Task> delete(Scanner sc, TaskList<Task> tasks) {
        int taskPosition = sc.nextInt() - DECREMENT;
        System.out.println(DELETE_COMMAND + tasks.get(taskPosition));
        return tasks.removeTask(taskPosition);
    }
    
    static TaskList<Task> toDo(String description, TaskList<Task> tasks) {
       //Carl Smotricz
       if (description.trim().length() == 0) {
           throw new DukeException("Todo must not be empty");
       }
       Task newTask = new Todos(description);
       return tasks.add(newTask);
    }

    static TaskList<Task> toDo(Scanner sc, TaskList<Task> tasks) {
       String description = sc.nextLine();
       //Carl Smotricz
       if (description.trim().length() == 0) {
           throw new DukeException("Todo must not be empty");
       }
       Task newTask = new Todos(description);
       return tasks.add(newTask);
    }
    
    static TaskList<Task> deadline(String description, String date, TaskList<Task> tasks) {
        //Carl Smotricz
        if (description.trim().length() == 0) {
            throw new DukeException("Deadline must not be empty");
        }
        try {
            //String[] dateRange = description.split("/");
            Task newTask = new Deadline(description,date);
            return tasks.add(newTask);
        } catch (DukeException e) {
            System.out.println("Todo must not be empty");
        }
        return tasks;
    }

    static TaskList<Task> deadline(Scanner sc, TaskList<Task> tasks) {
        String description = sc.nextLine();
        //Carl Smotricz
        if (description.trim().length() == 0) {
            throw new DukeException("Deadline must not be empty");
        }
        try {
            String[] dateRange = description.split("/by");
            Task newTask = new Deadline(dateRange[0],dateRange[1].replaceFirst("by",""));
            return tasks.add(newTask);
        } catch (DukeException e) {
            System.out.println("Todo must not be empty");
        }
        return tasks;
    }
    
    static TaskList<Task> events(String description, String from, String to, TaskList<Task> tasks) {
        //Carl Smotricz
        if (description.trim().length() == 0) {
            throw new DukeException("Event must not be empty");
        }
        Task newTask = new Events(description, from, to);
        return tasks.add(newTask);
    }

    static TaskList<Task> events(Scanner sc, TaskList<Task> tasks) {
        String description = sc.nextLine();
        //Carl Smotricz
        if (description.trim().length() == 0) {
            throw new DukeException("Event must not be empty");
        }
        String[] dateRange = description.split("/from");
        Task newTask = new Events(dateRange[0],dateRange[1].split("/to")[0],dateRange[1].split("/to")[1]);
        return tasks.add(newTask);
    }

}

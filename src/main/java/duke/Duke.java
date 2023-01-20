package duke;

import java.util.Scanner;
/**
 * Duke is the main class that directly handles the user input, and abstract
 * / encapsulates various information that is needed to store and display
 * results to the user. This includes: 
 * <ul>
 * <li> The list of Tasks that the user keys in 
 * <li> Reading and writing information from / to the hardisk of the user's
 * machine
 * <li> Various responses by Duke bot customised according to a football
 * character
 * </ul>
 * It has methods for different levels to satsify the iterative
 * implementation. Jar file executes the main class from here, as well as
 * Gradle
 * 
 * @author Muhammad Reyaaz
 * @version %I% %G%
 * @since 11
 * @see Scanner  
 * @see TaskList 
 * @see Storage
 * @see Ui
 */
public class Duke {
        
    private Tasks<Task> tasks;
    private TaskList<Task> taskList;
    
    private Storage storage; 
    
    private Ui ui; 
    /**
     * Default constructor that is made explicit. Tasks and TaskList have
     * essentially the same type of characteristics, but it is to satisfy
     * different levels in the iterative approach
     */
    Duke() {
        this.tasks = new Tasks<Task>();
        this.taskList = new TaskList<Task>();
    }
    /**
     * Satisfies Level 1. The user is welcomed with a custom message when the user
     * runs Duke. The custom message is encapsulated in a Printable class.
     * Thereafter, a Scanner object is instantiated that reads from the
     * user machine's keyboard. As long as the user does not terminate the
     * program, it will be continuously running and scanning for the next
     * full line the user keys in. It will just display the exact same line
     * the user keys in. When the user keys in exit, the program terminates.
     */
    void greetEcho() {

        Printable.greet();
        //Scanner object to read from the user machine's keyboard
        Scanner sc = new Scanner(System.in);
                
        while (true) {
            String echoWord = sc.next();    
            if (!echoWord.equals(Printable.TERMINATE)) {
                System.out.println(echoWord);
            } else {
                Printable.exit();
            }
        }
    }
    /**
     * Satisfies Level 2. The user is welcomed with a custom message when the user
     * runs Duke. The custom message is encapsulated in a Printable class.
     * Thereafter, a Scanner object is instantiated that reads from the
     * user machine's keyboard. As long as the user does not terminate the
     * program, it will be continuously running and scanning for the next
     * full line the user keys in. Unlike level 1, the user input will be stored 
     * during the session, and the user can list all the lines keyed in. When 
     * the user keys in exit, the program terminates.
     */
    void addAndList() {

        Printable.greet();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String description = sc.next();
            if (description.equals(Printable.SHOW_TASKS)) {
                this.tasks.listAllTasks();
            } else if (description.equals(Printable.TERMINATE)) {
                Printable.exit();
            } else {
                Task newTask = new Task(description);
                this.tasks = tasks.add(newTask);
            }
        }
    }
    /**
     * Satisfies Level 3. The user is welcomed with a custom message when the user
     * runs Duke. The custom message is encapsulated in a Printable class.
     * Thereafter, a Scanner object is instantiated that reads from the
     * user machine's keyboard. As long as the user does not terminate the
     * program, it will be continuously running and scanning for the next
     * full line the user keys in. On top of level 2 which the user input will be 
     * stored during the session, and the user can list all the lines keyed in 
     * ,this function contains additional features. The user will be able to
     * mark the Task as done or undone. When the user keys in exit, the program 
     * terminates.
     */
    void markAsDone() {

        Printable.greet();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String description = sc.next();
            if (description.equals(Printable.SHOW_TASKS)) {
                this.tasks.listAllTasks();
            } else if (description.equals(Printable.TERMINATE)) {
                Printable.exit();
            } else if (description.equals(Printable.MARK)) {
                int taskPosition = sc.nextInt() - Printable.DECREMENT;
                this.tasks = this.tasks.set(taskPosition, this.tasks.get(taskPosition).markAsDone());
            }  else if (description.equals(Printable.UNMARK)) {
                int taskPosition = sc.nextInt() - Printable.DECREMENT;
                this.tasks = this.tasks.set(taskPosition, this.tasks.get(taskPosition).markAsUndone());
            }  else {
                Task newTask = new Task(description);
                this.tasks = tasks.add(newTask);
            }
        }
    }
    /* Satisfies Level 4. On top of the features implemented in level 3,
     * which is mark as done or undone, the user will be able to create
     * different type of Tasks. This include Todo, Deadline and Events. Todo
     * will mark the type of Task with a [T]. As its name suggest, it is to
     * remind the user that this Task needs to be done. Deadline will mark
     * the type of Task with a [D]. This will allow the user to key in
     * additonal data, which is the stipulated completion or due date for
     * the Task. Lastly, Events will mark the Task with a [E]. This will
     * allow the user to key in two addtional inputs. One of them is the
     * beginning date, and the other is the end date. Naturally, this makes
     * it a more specific type of Task as compared to Deadline
     */
    void trackingEvents() {

        Printable.greet();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String description = sc.next();
            if (description.equals(Printable.SHOW_TASKS)) {
                this.tasks.listAllTasks();
            } else if (description.equals(Printable.TERMINATE)) {
                Printable.exit();
            } else if (description.equals(Printable.MARK)) {
                int taskPosition = sc.nextInt() - Printable.DECREMENT;
                this.tasks = this.tasks.set(taskPosition, this.tasks.get(taskPosition).markAsDone());
            }  else if (description.equals(Printable.UNMARK)) {
                int taskPosition = sc.nextInt() - Printable.DECREMENT;
                this.tasks = this.tasks.set(taskPosition, this.tasks.get(taskPosition).markAsUndone());
            } else if (description.equals(Printable.TODO)) {
                description = sc.nextLine();
                Task newTask = new Todos(description);
                this.tasks = tasks.add(newTask);
            } else if (description.equals(Printable.DEADLINE)) {
                description = sc.nextLine();
                String[] dateRange = description.split("/");
                Task newTask = new Deadline(dateRange[0],dateRange[1].replaceFirst("by",""));
                this.tasks = tasks.add(newTask);
            } else if (description.equals(Printable.EVENT)) {
                description = sc.nextLine();
                String[] dateRange = description.split("/");
                Task newTask = new Events(dateRange[0],dateRange[1].replaceFirst("from",""),dateRange[2].replaceFirst("to",""));
                this.tasks = tasks.add(newTask);
            } else {
                Task newTask = new Task(description + sc.nextLine());
                this.tasks = tasks.add(newTask);
            }
        }
    }
    /**
     * Satisfies Level 5. On top of the functionalities implemented in Level
     * 4, error handling is done. Two custom classes: dukeExceptionWarning
     * and dukeUnknownExceptionWarning are created. These two custom classes
     * are more specific versions of the IllegalArgumentException. For
     * example, if the user keys in 'blah', which is an unknown command, the
     * exception will be thrown. 
     *
     * @exception dukeUnknownException if the command is not legal
     * @see IllegalArgumentException
     */
    void errorHandling() {
        
        Printable.greet();
        Scanner sc = new Scanner(System.in);
    
        while (true) {
            String description = sc.next();
            if (description.equals(Printable.SHOW_TASKS)) {
                this.tasks.listAllTasks();
            } else if (description.equals(Printable.TERMINATE)) {
                Printable.exit();
            } else if (description.equals(Printable.MARK)) {
                tasks = Printable.mark(sc, this.tasks);
            }  else if (description.equals(Printable.UNMARK)) {
                tasks = Printable.unmark(sc, this.tasks);
            } else if (description.equals(Printable.TODO)) {
                tasks = Printable.toDo(sc, this.tasks);
            } else if (description.equals(Printable.DEADLINE)) {
                tasks = Printable.deadline(sc, this.tasks);
            } else if (description.equals(Printable.EVENT)) {
                tasks = Printable.events(sc, this.tasks);
            } else {
                dukeExceptionWarning(description);
            }
        }
    }
    /** Checks the user input against a list of invalid commands
     * (blacklist). If the input is blacklisted, a new DukeUnknownException
     * is thrown. Otherwise, a new Task will be created and added to the lit
     * of previously keyed in Task. 
     *
     * @param description The user's task title in String 
     * @throws IllegalArgumentException
     * @throws DukeUnknownException
     *
     *
     */
    void dukeExceptionWarning(String description) {
        try {
            if (Printable.INVALID_COMMANDS.contains(description)) {
                throw new DukeUnknownException("Illegal command");
            } else {
                Task newTask = new Task(description);
                this.tasks = tasks.add(newTask);
            }
        } catch (DukeUnknownException e) {
            System.out.println(Printable.ILLEGAL_COMMAND);
        }
    }
    /** Satisfies Level 6. On top of satisfying Level 5, it allows the user
     * to delete Task that was previosuly keyed in. Task are deleted by
     * specifying the index of the list of Tasks (starting from 1). 
     *
     * @exception DukeException
     *
     */ 
    void deleteHandling() {
        
        Printable.greet();
        Scanner sc = new Scanner(System.in);
    
        while (true) {
            String description = sc.next();
            if (description.equals(Printable.SHOW_TASKS)) {
                this.tasks.listAllTasks();
            } else if (description.equals(Printable.TERMINATE)) {
                Printable.exit();
            } else if (description.equals(Printable.MARK)) {
                tasks = Printable.mark(sc, this.tasks);
            }  else if (description.equals(Printable.UNMARK)) {
                tasks = Printable.unmark(sc, this.tasks);
            } else if (description.equals(Printable.TODO)) {
                tasks = Printable.toDo(sc, this.tasks);
            } else if (description.equals(Printable.DEADLINE)) {
                tasks = Printable.deadline(sc, this.tasks);
            } else if (description.equals(Printable.EVENT)) {
                tasks = Printable.events(sc, this.tasks);
            } else if (description.equals(Printable.DELETE)) {
                tasks = Printable.delete(sc, this.tasks);
            }  else {
                dukeExceptionWarning(description);
            }
        }
    }
    /** Satisfies Level 7. On top of Level 6 which allows user to delete
     * previously keyed in Task, whenever the user changes the list of
     * tasks, the new list will be saved into a file in the hardisk.
     * Firstly, if the file in the dynamic directory which is OS
     * independent, it will create the file. Then, it will save the new list
     * into the file. The latest list overwrites the old list to save space.
     * Once the user exits gracefully, using the 'exit' command, the new
     * list of tasks will be stored in the file.
     * 
     * @exception DukeException 
     * @exception IllegalArgumentException
     */
    void saveFiles() {
        
        Printable.greet();
        Scanner sc = new Scanner(System.in);
        Save saver = new Save();
        saver.readFromFile();
        this.tasks = saver.getTasks();
        saver.createDirectory();        
            
        while (true) {
            String description = sc.next();
            if (description.equals(Printable.SHOW_TASKS)) {
                this.tasks.listAllTasks();
            } else if (description.equals(Printable.TERMINATE)) {
                Printable.exit();
            } else if (description.equals(Printable.MARK)) {
                tasks = Printable.mark(sc, this.tasks);
            }  else if (description.equals(Printable.UNMARK)) {
                tasks = Printable.unmark(sc, this.tasks);
            } else if (description.equals(Printable.TODO)) {
                tasks = Printable.toDo(sc, this.tasks);
            } else if (description.equals(Printable.DEADLINE)) {
                tasks = Printable.deadline(sc, this.tasks);
            } else if (description.equals(Printable.EVENT)) {
                tasks = Printable.events(sc, this.tasks);
            } else if (description.equals(Printable.DELETE)) {
                tasks = Printable.delete(sc, this.tasks);
            }  else {
                dukeExceptionWarning(description);
            }
            saver.writeToFile(tasks.toString());
        }
    }
    /**
     * Satisfies Level 9, which is encapsulating and abstracting every class
     */
    void moreOop() {
        
        storage = new Storage();
        storage.readFromFile();
        this.taskList = storage.getTasks();
        storage.createDirectory();        
        
        ui = new Ui();
        ui.showWelcome();
        
        boolean isExit = false;
        
        while (!isExit) {
            ui.readCommand();
            taskList = ui.execute(taskList);
            storage.writeToFile(taskList.toString());
        }
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.moreOop();
    }
}

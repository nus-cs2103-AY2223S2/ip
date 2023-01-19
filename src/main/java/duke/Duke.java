package duke;

import java.util.Scanner;

public class Duke {
        
    private Tasks<Task> tasks;
    private TaskList<Task> taskList;
    private Storage storage; 
    private Ui ui;
    

    Duke() {
        this.tasks = new Tasks<Task>();
        this.taskList = new TaskList<Task>();
    }
    
    //Level 1
    void greetEcho() {

        Printable.greet();
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

    //Level 2
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

    //Level 3
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
    
    //Level 4
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

    //Level 5
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
    
    //Level 6
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
    
    //Level 7
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
    
    //Level 9
    void moreOop() {
        
        //Printable.greet();
        //Scanner sc = new Scanner(System.in);
        storage = new Storage();
        storage.readFromFile();
        this.taskList = storage.getTasks();
        storage.createDirectory();        
        ui = new Ui();

        ui.showWelcome();
        boolean isExit = false;
        boolean isFirst = true;

        while (!isExit) {
            ui.readCommand();
            this.taskList = ui.execute(this.taskList);
            
            //if (!isFirst) {
            //}
            //isFirst = false;
            storage.writeToFile(taskList.toString());
        }

        /*
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
            storage.writeToFile(tasks.toString());
        }
        */
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.moreOop();
    }
}

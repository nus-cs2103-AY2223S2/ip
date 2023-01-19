package duke; 

import java.util.Scanner;

class Ui {
    
    private Scanner scanner;
    private String description;
    private TaskList<Task> tasks;

    Ui() {
        this.scanner = new Scanner(System.in);    
    }

    void showWelcome() {
        Parser.greet();
    }

    void readCommand() {
       this.description = scanner.next();
    }

    TaskList<Task> execute(TaskList<Task> tasks) {        
        if (description.equals(Parser.SHOW_TASKS)) {
            this.tasks.listAllTasks();
        } else if (description.equals(Parser.TERMINATE)) {
            Parser.exit();
        } else if (description.equals(Parser.MARK)) {
            this.tasks = Parser.mark(scanner, tasks);
        }  else if (description.equals(Parser.UNMARK)) {
            this.tasks = Parser.unmark(scanner, tasks);
        } else if (description.equals(Parser.TODO)) {
            this.tasks = Parser.toDo(scanner, tasks);
        } else if (description.equals(Parser.DEADLINE)) {
            this.tasks = Parser.deadline(scanner, tasks);
        } else if (description.equals(Printable.EVENT)) {
            this.tasks = Parser.events(scanner, tasks);
        } else if (description.equals(Printable.DELETE)) {
            this.tasks = Parser.delete(scanner, tasks);
        }  else {
            dukeExceptionWarning(description, tasks);
        }
        return this.tasks;
        //storage.writeToFile(tasks.toString());
    }
    
    TaskList<Task> dukeExceptionWarning(String description, TaskList<Task> tasks) {
        try {
            if (Parser.INVALID_COMMANDS.contains(description)) {
                throw new DukeUnknownException("Illegal command");
            } else {
                System.out.println("test");
                Task newTask = new Task(description);
                this.tasks = tasks.add(newTask);
                return this.tasks;
            }
        } catch (DukeUnknownException e) {
            System.out.println(Printable.ILLEGAL_COMMAND);
        }
        return tasks;
    }
 
    /*
    void moreOop() {  
        Printable.greet();
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage();
        storage.readFromFile();
        this.tasks = storage.getTasks();
        storage.createDirectory();        
    }
    */

}


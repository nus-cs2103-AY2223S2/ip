package duke;

import java.util.Scanner;

public class Duke {
        
    private Tasks<Task> tasks;

    Duke() {
        this.tasks = new Tasks<Task>();
    }

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
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.addAndList();
    }
}

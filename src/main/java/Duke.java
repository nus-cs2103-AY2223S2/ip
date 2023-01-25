import java.util.ArrayList;


public class Duke {

    private UI ui;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    public Duke(){
        this.ui = new UI();
        this.parser = new Parser();
        this.storage = new Storage();
        ArrayList<Task> taskListArray  = new ArrayList<>();
        this.storage.readStorage(taskListArray);
        this.taskList = new TaskList(taskListArray);
    }

    public void run(){
        this.ui.run(parser, taskList, storage);
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");
        
        Duke dukeInstance = new Duke();
        
        dukeInstance.run();
        // store file content in programme first
    }

}


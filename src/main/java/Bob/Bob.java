package Bob

public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bob(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui(5, "~", 30);
        tasks = new TaskList();
    }

    private void readTasks() {
        String command = ui.readCommand();
        while (!command.equals("bye")) {
            try {
                int index;
                Task t;

                if (command.equals("list")) {
                    ui.printList(tasks);
                } else if (command.startsWith("todo")) {
                    t = Parser.parseTodo(command);
                    tasks.add(t);
                    ui.printTaskAdded(t);
                } else if (command.startsWith("event")) {
                    t = Parser.parseEvent(command);
                    tasks.add(t);
                    ui.printTaskAdded(t);
                } else if (command.startsWith("deadline")) {
                    t = Parser.parseDeadline(command);
                    tasks.add(t);
                    ui.printTaskAdded(t);
                } else if (command.startsWith("mark")) {
                    index = Parser.parseIndex(command);
                    ui.printMarkTask(tasks.get(index));
                } else if (command.startsWith("unmark")) {
                    index = Parser.parseIndex(command);
                    tasks.unmark(index);
                    ui.printUnmarkTask(tasks.get(index));
                } else if (command.startsWith("delete")) {
                    index = Parser.parseIndex(command);
                    ui.printDeleteTask(tasks.delete(index));
                } else { // Invalid command
                    throw new BobException("Sorry :( no valid command was entered");
                }
            } catch (BobException e) {
                ui.errorPrint(e);
            } finally {
                command = ui.readCommand();
            }
        }
    }

    private void loadTasks() {
        try {
            storage.load(tasks);
        } catch (BobException e) {
            ui.errorPrint(e);
        } 
    }
    
    private void saveTasks() {
        try {
            storage.save(tasks);
        } catch (BobException e) {
            ui.errorPrint(e);
        }

    }

    /**
     * Main program for Bob, our chat-bot
     */
    public void start() {
        //Introduction message
        ui.printIntroduction();

        // Load task list from file
        loadTasks();

        // Read tasks from user
        readTasks();

        // Save task list to file
        saveTasks();

        // Goodbye message
        ui.printGoodbye();
    }

    public static void main(String[] args) {
        Bob bob = new Bob("data/taskList.txt");
        bob.start();
    }
}

import java.time.DateTimeException;

public class Bro {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bro(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        // if there is a tasklist, load it
        // else create empty
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /***
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
     **/

    public static void main(String[] args) {
        /**
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         **/



        String input;

        while(true) {
            if (inputScanner.hasNextLine()) {
                input = inputScanner.nextLine();
                try {

                    validateInput(input, storage);
                } catch(DukeException x) {

                    // showLoading error
                    System.out.println(x.getMessage());
                    continue;
                }

                // level-1 feature: exit when user types "bye"
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("ok see you brother all love no cringe!");
                    break;
                }

                // PARSER -> PARSE()

                // level-3 feature: use input to construct Task object and add to array + display array when required + mark Task as done
                if (input.equalsIgnoreCase("display")) {
                    System.out.println("Here you go my brother!" );
                    int i = 0;
                    for(Task task: storage) {
                        System.out.println((i + 1) + ". " + task.toString());
                        i++;
                    }
                    System.out.println("Anything else I can do for you top G" );
                } else if (input.length() >= 6 && input.substring(0,4).equalsIgnoreCase("mark")) {
                    int i = Integer.parseInt(input.substring(5)) - 1;
                    storage.get(i).markAsDone();
                    System.out.println("Marked as you wish my brother:");
                    System.out.println((i + 1) + ". " + storage.get(i).toString());
                    System.out.println(" Whats next?");
                } else if (input.length() > 7 && input.substring(0,6).equalsIgnoreCase("delete")) {
                    int i = Integer.parseInt(input.substring(7)) - 1;
                    Task removed = storage.get(i);
                    storage.remove(i);
                    System.out.println("Deleted this task for you my brother:\n" + removed.toString());
                    System.out.println("Now you have " + storage.size() + " tasks left");
                } else {
                    // Level-4 feature: Todo, Deadline, Event
                    // note existing exception: indexOf() is case sensitive so /by etc must be in right caps
                    if (input.substring(0, 4).equalsIgnoreCase("todo")) {
                        storage.add(new Todo(input.substring(5)));
                    } else if (input.substring(0, 5).equalsIgnoreCase("event")) {
                        int startIndex = input.indexOf("/from ");
                        int x = input.indexOf("/to ");
                        int endIndex = x + 4;
                        String description = input.substring(6, startIndex - 1);
                        String start = input.substring(startIndex + 6, x - 1);
                        String end = input.substring(endIndex);
                        try {
                            storage.add(new Event(description, start, end));
                        } catch (DateTimeException e) {
                            System.out.println("Invalid Date and Time input brother. Here's the correct format:\ndd/MM/yyyy HHmm");
                            continue;
                        }

                    } else if (input.substring(0, 8).equalsIgnoreCase("deadline")) {
                        int startIndex = input.indexOf("/by "); // exception
                        String description = input.substring(9, startIndex - 1);
                        String deadline = input.substring(startIndex + 4);
                        try {
                            storage.add(new Deadline(description, deadline));
                        } catch (DateTimeException e) {
                            System.out.println("Invalid Date and Time input brother. Here's the correct format:\ndd/MM/yyyy HHmm");
                            continue;
                        }
                    } else {
                        storage.add(new Task(input));
                    }
                    int x = storage.size();
                    System.out.println("added to list my brother: \n" + x + "." + storage.get(x - 1).toString() + "\nNow you have " + x + " tasks!");
                }


                // STORAGE CLASS
                // if changes made to task list, rewrite task list in data.txt
                if (!input.contains("display") && !input.contains("bye")) {
                    try {
                        FileWriter writer = new FileWriter(hardDisk, false);
                        PrintWriter printWriter = new PrintWriter(writer);
                        int i = 0;
                        for(Task task: storage) {
                            printWriter.println((i + 1) + ". " + task.toString());
                            i++;
                        }
                        printWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }




}


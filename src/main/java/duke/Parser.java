package duke;
public class Parser {
    public static void parse(Ui ui, TaskList tasks, Storage storage, String userInput) {
        try {
            String firstWord = userInput.split(" ", 2)[0];
            if (userInput.equals("bye")) {
                ui.respond("Goodbye! Have a nice day ahead.\n");
            } else if (userInput.equals("list")) {
                tasks.listTasks();
            } else if (userInput.equals("help")) {
                ui.showHelpMessage();
            } else if (firstWord.equals("mark")) {
                //Second word should be an integer dictating which Task to mark.
                String secondWord = userInput.split(" ", 2)[1];
                int taskNumber = Integer.parseInt(secondWord);
                Task task = tasks.getTask(taskNumber);
                storage.changeTaskStatus(task.getStorageLine());
                tasks.mark(taskNumber);
                ui.respond("I have marked this task as done! \n" + task.provideDetails());
            } else if (firstWord.equals("unmark")) {
                //Second word should be an integer dictating which Task to unmark.
                String secondWord = userInput.split(" ", 2)[1];
                int taskNumber = Integer.parseInt(secondWord);
                Task task = tasks.getTask(taskNumber);
                storage.changeTaskStatus(task.getStorageLine());
                tasks.unmark(taskNumber);
                ui.respond("I have marked this task as undone! \n" + task.provideDetails());
            } else if (firstWord.equals("todo")) {
                //Rest of message describes the Task.
                String body = userInput.split(" ", 2)[1];
                ToDo task = new ToDo("todo", body, false);
                tasks.addTask(task);
                storage.addTask(task.getStorageLine());
                ui.respond("I have added this new task:\n" + task.provideDetails()
                        + "\nYou now currently have "
                        + tasks.getTaskCount() + " tasks.");
            } else if (firstWord.equals("deadline")) {
                //Rest of message describes the Task.
                String body = userInput.split(" ", 2)[1];
                DeadLine newTask = new DeadLine("deadline", body, false);
                tasks.addTask(newTask);
                storage.addTask(newTask.getStorageLine());
                ui.respond("I have added this new task:\n" + newTask.provideDetails()
                        + "\nYou now currently have "
                        + tasks.getTaskCount() + " tasks.");
            } else if (firstWord.equals("event")) {
                //Rest of message describes the Task.
                String body = userInput.split(" ", 2)[1];
                Event newTask = new Event("event", body, false);
                tasks.addTask(newTask);
                storage.addTask(newTask.getStorageLine());
                ui.respond("I have added this new task:\n" + newTask.provideDetails()
                        + "\nYou now currently have "
                        + tasks.getTaskCount() + " tasks.");
            } else if (firstWord.equals("delete")) {
                //second word should be an integer
                String secondWord = userInput.split(" ", 2)[1];
                int taskNumber = Integer.parseInt(secondWord);
                Task task = tasks.getTask(taskNumber);
                tasks.deleteTask(taskNumber);
                storage.deleteTask(task.getStorageLine());
                ui.respond("We have removed this task: " + task.provideDetails() + "\nYou now have "
                        + tasks.getTaskCount() + " tasks remaining");
            } else {
                ui.respond("Oops! I don't know what this means.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //only one word was given when two words were expected.
            ui.showCommandError();
        } catch (NumberFormatException e) {
            //Second word was not a number.
            ui.showCommandError();
        } catch (IndexOutOfBoundsException e) {
            //Attempted to unmark/mark a task that does not exist.
            ui.showCommandError();
        } catch (DukeException e) {
            ui.showCommandError();
        } catch (Exception e) {
            ui.showCommandError();
        }
    }
}

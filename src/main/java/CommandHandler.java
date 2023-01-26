import java.util.List;

public class CommandHandler {
    CommandHandler() {}
    public String handleCommand(Command command, TaskList tasks) {
        switch (command.getDescription()) {
        case "bye":
            return endDuke();
            //Fallthrough (java doesn't let me compile if I add a break)
        case "list":
            return showTasks(tasks);
            //Fallthrough
        case "mark":
            return markTask(command.getArguments().get(0), tasks);
            //Fallthrough 
        case "unmark":
            return unmarkTask(command.getArguments().get(0), tasks);
            //Fallthrough 
        case "todo":
            return addTodo(command.getArguments().get(0), tasks);
            //Fallthrough 
        case "deadline":
            return addDeadline(command.getArguments().get(0), command.getArguments().get(1), tasks);
            //Fallthrough 
        case "event":
            return addEvent(command.getArguments().get(0), command.getArguments().get(1), command.getArguments().get(2), tasks);
            //Fallthrough 
        case "delete":
            return deleteEvent(command.getArguments().get(0), tasks);
            //Fallthrough 
        case "noMatch":
            return noMatch();
            //Fallthrough 
        case "invalid":
            return invalid(command.getArguments().get(0));
            //Fallthrough 
        }
        return "";
    }
    
    public boolean isByeCommand(Command command) {
        return command.getDescription().equals("bye");
    }
    private String endDuke() {
        return "Bye! Hope to see you again soon!\n";
    }
    private String showTasks(TaskList tasks) {
        String response = "";
        if (!tasks.isEmpty()) {
            for (int i = 1; i <= tasks.size(); i++) {
                response += String.format("%d.%s", i, tasks.get(i - 1).printTask()) + "\n";
            }
        } else {
            response = "You currently have no items in your to-do list!\n";
        }
        return response;
    }
    private String markTask(String index, TaskList tasks) {
        String response = "";
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            tasks.get(taskIndex).setDone(true);
            response += "Okay! I've marked this task as done!\n";
            response += tasks.get(taskIndex).printTask() + "\n";
        } catch (IndexOutOfBoundsException e) {
            response = "You don't have that many tasks!\n";
        }
        return response;
    }
    private String unmarkTask(String index, TaskList tasks) {
        String response = "";
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            tasks.get(taskIndex).setDone(false);
            response += "Okay! I've marked this task as not done yet!\n";
            response += tasks.get(taskIndex).printTask() + "\n";
        } catch (IndexOutOfBoundsException e) {
            response = "You don't have that many tasks!\n";
        }
        return response;
    }
    private String addTodo(String description, TaskList tasks) {
        String response = "";
        Task newTask = new Todo(description);
        tasks.add(newTask);
        response = String.format("Added: %s\n", newTask.printTask());
        return response;
    }
    private String addDeadline(String description, String by, TaskList tasks) {
        String response = "";
        Task newTask = new Deadline(description, by);
        tasks.add(newTask);
        response = String.format("Added: %s\n", newTask.printTask());
        return response;
    }
    private String addEvent(String description, String from, String to, TaskList tasks) {
        String response = "";
        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        response = String.format("Added: %s\n", newTask.printTask());
        return response;
    }
    private String noMatch() {
        return "Sorry, I didn't understand that, please ask again.\n";
    }

    private String deleteEvent(String index, TaskList tasks) {
        String response = "";
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            response = String.format("Okay! I deleted task %s\n", tasks.get(taskIndex).printTask());
            tasks.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            response = "You don't have that many tasks!\n";
        }
        return response;
    }
    
    private String invalid(String reply) {
        return reply;
    }
}

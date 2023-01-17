public class TaskHandler {
    private List taskList;
    private Task[] content;
    public TaskHandler(List taskList) {
        this.taskList = taskList;
        this.content = taskList.getContent();
    }
    private int counter = 0;
    private static final String HEADER = "Got it. I've added this task:";
    private String FOOTER = String.format("Now you have %d tasks in the list.", counter);

    public String add(String input) {
        if (input.isEmpty()) {
            return "Please enter a command.";
        } else {
            Task item = new Task(input);
            content[counter] = item;
            counter++;
            return HEADER + "\n" + item + "\n" + FOOTER;

        }
    }

    public String display() {
        String allElements = "";
        for (int i = 0; i < 101; i++) {
            if (content[i] != null) {
                allElements = allElements + (i + 1) + ". " + content[i].toString();
            } else {
                break;
            }
        }
        if (allElements == "") {
            return "No items in list!";
        } else {
            return "Here are the tasks in your list: " + "\n" + allElements;
        }
    }

    public String markAsDone(String input) {
        String num = input.split(" ")[1];
        int index = Integer.parseInt(num) - 1;
        int listIndex = index + 1;
        if (content[index] != null) {
            content[index].setDone();
            return "Nice! I've marked this task as done: " + "\n" + listIndex + ". " + content[index].toString();
        } else {
            return "No such task.";
        }
    }

    public String markAsUndone(String input) {
        String num = input.split(" ")[1];
        int index = Integer.parseInt(num) - 1;
        int listIndex = index + 1;
        if (content[index] == null) {
            return "No such task.";
        } else if (content[index] != null && content[index].isDone) {
            content[index].setUndone();
            return "Nice! I've marked this task as undone: " + "\n" + listIndex + 1 + ". " + content[index].toString();
        } else if(!content[index].isDone) {
            return "This task is already marked undone. ";
        } else {
            return "No such task.";
        }
    }

    public String eventHandler(String input) {
        if (input.length() < 7) {
            return "Please enter event body.";
        }
        String[] splitCommand = input.split(" ", 2);
        String[] segments = splitCommand[1].split("/");
        String item = segments[0];
        String startTime = segments[1];
        String endTime = segments[2];

        if (item.isEmpty()) {
            return "Please enter event body.";
        }
        Event newEvent = new Event(item, startTime, endTime);
        content[counter] = newEvent;
        counter++;
        return HEADER + "\n" + newEvent + "\n" + String.format("Now you have %d tasks in the list.", counter);
    }

    public String todoHandler(String input) {
        if (input.length() < 5) {
            return "Please enter event body.";
        }
        String[] splitCommand = input.split(" ", 2);
        String item = splitCommand[1];

        if (item.isEmpty()) {
            return "Please enter event body.";
        }

        Todo newTodo = new Todo(item);
        content[counter] = newTodo;
        counter++;
        return HEADER + "\n" + newTodo + "\n" + String.format("Now you have %d tasks in the list.", counter);

    }
    public String deadlineHandler(String input) {
        if (input.length() < 9) {
            return "Please enter event body.";
        }
        String[] splitCommand = input.split(" ", 2);
        String[] segments = splitCommand[1].split("/");
        String item = segments[0];
        String deadline = segments[1];

        if (item.isEmpty()) {
            return "Please enter event body.";
        }

        Deadline newDeadline = new Deadline(item, deadline);
        content[counter] = newDeadline;
        counter++;
        return HEADER + "\n" + newDeadline + "\n" + String.format("Now you have %d tasks in the list.", counter);

    }


}

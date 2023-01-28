package duke;
public class Parser {

    int counter;


    enum Types {LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE}

    Parser() {
    }

    boolean parseCommand(String originalString, TaskList<Task> taskList) throws NeroException {
        try {
            String[] input = originalString.split(" ");
            switch (Enum.valueOf(Types.class, input[0].toUpperCase())) {
            case BYE:
                System.out.println("Bye. Hope to see you again soon!" + "\n");
                return true;
            case LIST:
                System.out.println("Here are all your tasks: ");
                taskList.printTasks();
                return false;
            case MARK: {
                try {
                    int taskToMark = Integer.parseInt(input[1]) - 1;
                    Task currTask = taskList.get(taskToMark);
                    currTask.markAsDone();
                    System.out.println("Great job on completing this task! " + "\n" + currTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    throw new NeroException("Please add the correct index from 0 to " + taskList.getSize());
                }
                return false;
            }
            case UNMARK: {
                try {
                    int taskToUnmark = Integer.parseInt(input[1]) - 1;
                    Task currTask = taskList.get(taskToUnmark);
                    currTask.markAsUndone();
                    System.out.println("Remember to complete this task!! " + "\n" + currTask.toString());

                } catch (IndexOutOfBoundsException e) {
                    throw new NeroException("Please add the correct index from 0 to " + taskList.getSize());
                }
                return false;
            }
            case TODO:
                int index = originalString.indexOf("todo");
                try {
                    String toAdd = originalString.substring(index + 5);
                    Task newTask = new ToDo(toAdd);
                    taskList.addTask(newTask);
                    System.out.println("Got it! I've added this task to the list!" + "\n" + newTask.toString() + "\n" + "Now you have " + taskList.getSize() +
                            " tasks in the list!" + "\n");
                } catch (IndexOutOfBoundsException e) {
                    throw new NeroException("Description of a todo cannot be empty!!!");
                }
                return false;
            case DEADLINE:
                try {
                    String[] splitString = originalString.split("/");
                    String description = splitString[0].replace("deadline", "");
                    String duration = splitString[1].replace("by", "");
                    Task newTask = new Deadline(description, duration);
                    taskList.addTask(newTask);
                    System.out.println("Got it! I've added this task to the list!" + "\n" + newTask.toString() + "\n" + "Now you have " + taskList.getSize() +
                            " tasks in the list!" + "\n");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please add a task description and deadline!!!");
                }
                return false;
            case EVENT:
                try {
                    String[] splitString = originalString.split("/");
                    String description = splitString[0].replace("event", "");
                    String startDate = splitString[1].replace("from", "") ;
                    String endDate = splitString[2].replace("to", "");
                    Task newTask = new Event(description, startDate, endDate);
                    taskList.addTask(newTask);
                    System.out.println("Got it! I've added this task to the list!" + "\n" + newTask.toString() + "\n" + "Now you have " + taskList.getSize() +
                            " tasks in the list!" + "\n");
                } catch (IndexOutOfBoundsException e) {
                    throw new NeroException("Please add a task description, from and to date!!!");
                }
                return false;
            case DELETE:
                try {
                    int toDelete = Integer.parseInt(input[1]) - 1;
                    Task removedTask = taskList.get(toDelete);
                    taskList.removeTask(toDelete);
                    System.out.println("Alright, let me remove this task..." + "\n" + removedTask.toString() + "\n" + "Now you have " + taskList.getSize() +
                            " tasks in the list!" + "\n");
                } catch (IndexOutOfBoundsException e) {
                    throw new NeroException("Please add a correct task number");
                }
                return false;
            default:
                throw new NeroException("This is an incorrect input!!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong input!");
            return false;
        }
    }
}

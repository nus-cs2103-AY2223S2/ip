public class Parser {

    public TaskList<Task> taskList;
    public int counter;

    public enum Types {LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE}

    Parser() {
        this.taskList = new TaskList();
        this.counter = 0;
    }

    void parseCommand(String originalString) throws NeroException {
        String[] input = originalString.split(" ");
        switch (Enum.valueOf(Types.class, input[0])) {
            case BYE:
                System.out.println("Bye. Hope to see you again soon!" + "\n");
                break;
            case LIST:
                System.out.println("Here are all your tasks: ");
                taskList.printTasks();
                break;
            case MARK: {
                int taskToMark = Integer.parseInt(input[1]) - 1;
                Task currTask = taskList.get(taskToMark);
                currTask.markAsDone();
                System.out.println("Great job on completing this task! " + "\n" + currTask.currToPrint());
                break;
            }
            case UNMARK: {
                int taskToUnmark = Integer.parseInt(input[1]) - 1;
                Task currTask = taskList.get(taskToUnmark);
                currTask.markAsUndone();
                System.out.println("Remember to complete this task!! " + "\n" + currTask.currToPrint());
                break;
            }
            case TODO:
                int index = originalString.indexOf("todo");
                try {
                    String toAdd = originalString.substring(index + 5);
                    Task newTask = new ToDo(toAdd);
                    taskList.addTask(newTask);
                    counter++;
                    System.out.println("Got it! I've added this task to the list!" + "\n" + newTask.currToPrint() + "\n" + "Now you have " + counter +
                            " tasks in the list!" + "\n");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Description of a todo cannot be empty!!!");
                }
                break;
            case DEADLINE:
                try {
                    String[] splitString = originalString.split("/");
                    String description = splitString[0].replace("deadline", "");
                    String duration = splitString[1].replace("by", "by:");
                    Task newTask = new Deadline(description, duration);
                    taskList.addTask(newTask);
                    counter++;
                    System.out.println("Got it! I've added this task to the list!" + "\n" + newTask.currToPrint() + "\n" + "Now you have " + counter +
                            " tasks in the list!" + "\n");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please add a task description and deadline!!!");
                }
                break;
            case EVENT:
                try {
                    String[] splitString = originalString.split("/");
                    String description = splitString[0].replace("event", "");
                    String duration = splitString[1].replace("from", "from:") + splitString[2].replace("to", "to:");
                    Task newTask = new Event(description, duration);
                    taskList.addTask(newTask);
                    counter++;
                    System.out.println("Got it! I've added this task to the list!" + "\n" + newTask.currToPrint() + "\n" + "Now you have " + counter +
                            " tasks in the list!" + "\n");
                } catch (IndexOutOfBoundsException e) {
                    throw new NeroException("Please add a task description, from and to date!!!");
                }
                break;
            case DELETE:
                try {
                    int toDelete = Integer.parseInt(input[1]) - 1;
                    Task removedTask = taskList.get(toDelete);
                    taskList.removeTask(toDelete);
                    counter--;
                    System.out.println("Alright, let me remove this task..." + "\n" + removedTask.currToPrint() + "\n" + "Now you have " + counter +
                            " tasks in the list!" + "\n");
                } catch (IndexOutOfBoundsException e) {
                    throw new NeroException("Please add a correct task number");
                }
                break;
            default:
                throw new NeroException("This is an incorrect input!!");
        }
    }
}

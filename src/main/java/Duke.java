import java.util.Scanner;

public class Duke {
    public String greetUser() {
        return "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }

    public String farewellUser() {
        return "Bye. Hope to see you again soon!";
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        TaskList tl = new TaskList();
        String greeting = duke.greetUser();
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while(!userInput.equals("bye")) {
            try {
                if (!userInput.equals("list")) {
                    if (userInput.startsWith("mark")) {
                        String[] strArr = userInput.split(" ", 2);
                        int taskNumber = Integer.parseInt(strArr[1]);
                        if (taskNumber < 1 || taskNumber > tl.items) {
                            throw new InvalidTaskException(taskNumber);
                        } else {
                            Task taskToMark = tl.list.get(taskNumber - 1);
                            String markMessage = taskToMark.markAsDone();
                            System.out.println(markMessage);
                        }
                        userInput = sc.nextLine();
                    } else if (userInput.startsWith("unmark")) {
                        String[] strArr = userInput.split(" ", 2);
                        int taskNumber = Integer.parseInt(strArr[1]);
                        if (taskNumber < 1 || taskNumber > tl.items) {
                            throw new InvalidTaskException(taskNumber);
                        } else {
                            Task taskToUnmark = tl.list.get(taskNumber - 1);
                            String unmarkMessage = taskToUnmark.markAsUndone();
                            System.out.println(unmarkMessage);
                        }
                        userInput = sc.nextLine();
                    } else if (userInput.startsWith("todo")) {
                        String[] strArr = userInput.split(" ", 2);
                        String taskName = strArr[1];
                        ToDo toDoTask = new ToDo(taskName);
                        String addConfirmation = tl.addTask(toDoTask);
                        System.out.println(addConfirmation);
                        userInput = sc.nextLine();
                    } else if (userInput.startsWith("deadline")) {
                        String[] strArr = userInput.split(" ", 2);
                        String[] strArr2 = strArr[1].split("/");
                        if (strArr2.length != 2) {
                            throw new IncorrectArgumentsException(userInput, 2, strArr2.length);
                        }
                        String taskName = strArr2[0];
                        String dueTime = strArr2[1];
                        Deadline deadlineTask = new Deadline(taskName, dueTime);
                        String addConfirmation = tl.addTask(deadlineTask);
                        System.out.println(addConfirmation);
                        userInput = sc.nextLine();
                    } else if (userInput.startsWith("event")) {
                        String[] strArr = userInput.split(" ", 2);
                        String[] strArr2 = strArr[1].split("/");
                        if (strArr2.length != 3) {
                            throw new IncorrectArgumentsException(userInput, 3, strArr2.length);
                        }
                        String taskName = strArr2[0];
                        String startTime = strArr2[1];
                        String endTime = strArr2[2];
                        Event eventTask = new Event(taskName, startTime, endTime);
                        String addConfirmation = tl.addTask(eventTask);
                        System.out.println(addConfirmation);
                        userInput = sc.nextLine();
                    } else {
                        throw new InvalidInputException(userInput);
                    }
                } else {
                    String output = tl.printTaskList();
                    System.out.println(output);
                    userInput = sc.nextLine();
                }
            } catch (DukeException exception) {
                System.out.println(exception);
                userInput = sc.nextLine();
            }
        }

        String farewell = duke.farewellUser();
        System.out.println(farewell);
    }
}

import java.util.Scanner;
import java.util.ArrayList;

class Duke {
    enum Action {
        bye, list, unmark, mark, todo, deadline, event, delete;

    }
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

            Scanner myObj = new Scanner(System.in);
            String input = myObj.nextLine();
            String[] s = input.split(" ");
            ArrayList<Task> list = new ArrayList<>();
            int currentIndex = 0;
            while (!input.equals(Action.bye.name())) {
                try {
                if (input.equals(Action.list.name())) {
                    System.out.println("Here are the tasks in your list");
                    for (int i = 0; i < currentIndex; i++) {
                        System.out.print(i + 1 + ".");
                        System.out.println(list.get(i).toString());
                    }
                    input = myObj.nextLine();
                    s = input.split(" ");
                } else if (s[0].equals(Action.unmark.name())) {
                    Task taskName = list.get(Integer.parseInt(s[1]) - 1);
                    taskName.unMark();
                    System.out.println("OK, I've marked this task as not done yet");
                    System.out.println(taskName.toString());
                    input = myObj.nextLine();
                    s = input.split(" ");
                } else if (s[0].equals(Action.mark.name())) {
                    Task taskName = list.get(Integer.parseInt(s[1]) - 1);
                    taskName.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskName.toString());
                    input = myObj.nextLine();
                    s = input.split(" ");
                } else if (s[0].equals(Action.todo.name())) {
                    String taskDescription = "";
                    if (s.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    for (int j = 1; j < s.length; j++) {
                        taskDescription += s[j];
                        taskDescription += " ";
                    }
                    ToDos taskName = new ToDos(taskDescription);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskName.toString());
                    list.add(taskName);
                    currentIndex++;
                    System.out.println("Now you have " + currentIndex + " tasks in the list.");
                    input = myObj.nextLine();
                    s = input.split(" ");
                } else if (s[0].equals(Action.deadline.name())) {
                    String taskDescription = "";
                    boolean isTime = false;
                    String time = "";
                    if (s.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    for (int j = 1; j < s.length; j++) {
                        if (s[j].equals("/by")) {
                            isTime = true;
                        } else if (isTime) {
                            if (j + 1 == s.length) {
                                time += s[j];
                            } else {
                                time += s[j];
                                time += " ";
                            }
                        } else {
                            taskDescription += s[j];
                            taskDescription += " ";
                        }
                    }
                    Deadlines taskName = new Deadlines(taskDescription, time);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskName.toString());
                    list.add(taskName);
                    currentIndex++;
                    System.out.println("Now you have " + currentIndex + " tasks in the list.");
                    input = myObj.nextLine();
                    s = input.split(" ");
                } else if (s[0].equals(Action.event.name())) {
                    String taskDescription = "";
                    boolean isStartTime = false;
                    boolean isEndTime = false;
                    String startTime = "";
                    String endTime = "";
                    if (s.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    for (int j = 1; j < s.length; j++) {
                        if (s[j].equals("/from")) {
                            isStartTime = true;
                        } else if (isStartTime && !s[j].equals("/to") && !isEndTime) {
                            startTime += s[j];
                            startTime += " ";
                        } else if (s[j].equals("/to")) {
                            isEndTime = true;
                        } else if (isEndTime) {
                            if (j + 1 == s.length) {
                                endTime += s[j];
                            } else {
                                endTime += s[j];
                                endTime += " ";
                            }
                        } else {
                            taskDescription += s[j];
                            taskDescription += " ";
                        }
                    }
                    Events taskName = new Events(taskDescription, startTime, endTime);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskName.toString());
                    list.add(taskName);
                    currentIndex++;
                    System.out.println("Now you have " + currentIndex + " tasks in the list.");
                    input = myObj.nextLine();
                    s = input.split(" ");
                } else if (s[0].equals(Action.delete.name())) {
                    Task currentTask = list.get(Integer.parseInt(s[1]) - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(currentTask.toString());
                    currentIndex --;
                    System.out.println("Now you have " + currentIndex + " tasks in the list");
                    list.remove(Integer.parseInt(s[1]) - 1);
                    input = myObj.nextLine();
                    s = input.split(" ");
                }
                else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            }
                catch (Exception e) {
                    System.out.println(e);
                    input = myObj.nextLine();
                    s = input.split(" ");

                }



        }
        System.out.println("Bye. Hope to see you again soon!");
        }

}

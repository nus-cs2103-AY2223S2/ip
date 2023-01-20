import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String greeting = formatStr("Hello! I'm Muse\n"
                + "What can I do for you?");
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>();


        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String[] splitArr = input.split(" ");
            try {
                if (input.equals("list")) {
                    System.out.println(formatStr(listThings(tasks)));
                } else if (splitArr[0].equals("mark") || splitArr[0].equals("unmark")) {
                    if((Integer.parseInt(splitArr[1])) > tasks.size()) {
                        throw new OutOfIndexException("Help! \n" +
                                "The number has to be within range of our task-list!\n" +
                                "try again.");
                    }
                    mark(splitArr[0], Integer.parseInt(splitArr[1]) - 1, tasks);
                } else if (splitArr[0].equals("delete")) {
                    if((Integer.parseInt(splitArr[1])) > tasks.size()) {
                        throw new OutOfIndexException("Help! \n" +
                                "The number has to be within range of our task-list!\n" +
                                "Please try again!");
                    }
                    Task newTask = tasks.get(Integer.parseInt(splitArr[1]) - 1);
                    tasks.remove(Integer.parseInt(splitArr[1]));
                    System.out.println(formatStr(deleteReport(newTask, tasks)));
                } else if (splitArr[0].equals("todo")) {
                    Todo newTodo = new Todo(input);
                    tasks.add(newTodo);
                    System.out.println(formatStr(addReport(newTodo, tasks)));
                } else if (splitArr[0].equals("deadline")) {
                    Deadline newDead = new Deadline(input);
                    tasks.add(newDead);
                    System.out.println(formatStr(addReport(newDead, tasks)));
                } else if (splitArr[0].equals("event")) {
                    Event newEvent = new Event(input);
                    tasks.add(newEvent);
                    System.out.println(formatStr(addReport(newEvent, tasks)));
                } else {
                    throw new VagueInputException("Oh no! What do you mean? \n" +
                            "I'm confused. Please specify... @.@");
                }
            } catch (VagueInputException ex) {
                System.out.println(formatStr(ex.getMessage()));
                input = sc.nextLine();
                continue;
            } catch (OutOfIndexException ex) {
                System.out.println(formatStr(ex.getMessage()));
                input = sc.nextLine();
                continue;
            }
            input = sc.nextLine();
        }
            String goodbyeMessage = formatStr("Bye. Come back again!");
            System.out.println(goodbyeMessage);
        }

    public static String formatStr(String str) {
        String returnstr =  ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
                            + str + "\n"
                            + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
        return returnstr;
    }

    public static class Task {
        protected boolean mark;
        protected String content;

        public Task(String content) {
            this.content = content;
            this.mark = false;
        }

        public void setMark() {
            this.mark = !this.mark;
            String outputStr;
            if (mark == true) {
                outputStr = "NICE! You finished this: \n"
                        + "[" + markSign(this.mark) + "] " + this.content;
            } else {
                outputStr = "Ok, you have undone this: \n"
                        + "[" + markSign(this.mark) + "] " + this.content;
            }
            System.out.println(formatStr(outputStr));
        }

        public String markSign(boolean markBool) {
            if(markBool == true) return "X";
            else return " ";
        }

        public String toString() {
            return ". [" + markSign(this.mark) + "] " + this.content;
        }
    }

    public static void mark(String marked, int index, List<Task> arrTasks) {
        arrTasks.get(index).setMark();
    }

    public static String addReport(Task task, List<Task> taskList) {
        String returnStr = "gotcha.\nyou added: " + task.toString().substring(2) + "\n"
                + numberOfTasks(taskList);
        return returnStr;
    }

    public static String deleteReport(Task task, List<Task> taskList) {
        String returnStr = "gotcha.\nyou you have deleted: " + task.toString().substring(2) + "\n"
                + numberOfTasks(taskList);
        return returnStr;
    }

    public static String numberOfTasks(List<Task> taskList) {
        return "You have " + taskList.size() + " tasks in this list!";
    }

    public static String listThings(List<Task> arrList) {
        String returnstr = "Alright, here are the things: \n";
        for (int i = 0; i < arrList.size(); i++) {
            if (i == arrList.size() - 1) {
                returnstr += Integer.toString(i+1) + arrList.get(i).toString();
            } else {
                returnstr += Integer.toString(i+1) + arrList.get(i).toString() + "\n";
            }
        } return returnstr;
    }
}


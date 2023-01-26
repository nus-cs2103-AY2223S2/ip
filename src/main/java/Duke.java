import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();

        try {
            File file = new File("duke.txt");
            if (!file.exists()) {
                System.out.println(formatStr("Oh dear! There is no save file. Let me create one for you."));
                System.out.println("........CREATING.......");
                file.createNewFile();
            } else {
                System.out.println(file);
            }
            PrintWriter pw = new PrintWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String greeting = formatStr("Hello! I'm Muse\n"
                + "What can I do for you?");
        System.out.println(greeting);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String[] splitArr = input.split(" ");
            try {
                if (input.equals("list")) {
                    System.out.println(formatStr(tasks.listThings()));
                } else if (splitArr[0].equals("mark") || splitArr[0].equals("unmark")) {
                    if((Integer.parseInt(splitArr[1])) > tasks.getSize()) {
                        throw new OutOfIndexException("Help! \n" +
                                "The number has to be within range of our task-list!\n" +
                                "try again.");
                    }
                    tasks.mark(splitArr[0], Integer.parseInt(splitArr[1]) - 1);
                } else if (splitArr[0].equals("delete")) {
                    if((Integer.parseInt(splitArr[1])) > tasks.getSize()) {
                        throw new OutOfIndexException("Help! \n" +
                                "The number has to be within range of our task-list!\n" +
                                "Please try again!");
                    }
                    Task newTask = tasks.getTask(Integer.parseInt(splitArr[1]) - 1);
                    tasks.removeTask(Integer.parseInt(splitArr[1]));
                    System.out.println(formatStr(tasks.deleteReport(newTask)));
                } else if (splitArr[0].equals("todo")) {
                    Todo newTodo = new Todo(input);
                    tasks.addTask(newTodo);
                    System.out.println(formatStr(tasks.addReport(newTodo)));
                } else if (splitArr[0].equals("deadline")) {
                    Deadline newDead = new Deadline(input);
                    tasks.addTask(newDead);
                    System.out.println(formatStr(tasks.addReport(newDead)));
                } else if (splitArr[0].equals("event")) {
                    Event newEvent = new Event(input);
                    tasks.addTask(newEvent);
                    System.out.println(formatStr(tasks.addReport(newEvent)));
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

}


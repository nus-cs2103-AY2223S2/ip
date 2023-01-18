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
            if (input.equals("list")) {
                System.out.println(formatStr(listThings(tasks)));
            } else if (splitArr[0].equals("mark") || splitArr[0].equals("unmark")) {
                mark(splitArr[0], Integer.parseInt(splitArr[1]) - 1, tasks);
            } else {
                tasks.add(new Task(input));
                System.out.println(formatStr(addReport(input)));
            }
            input = sc.nextLine();
        }
        String goodbyeMessage = formatStr("Bye. Come back again!");
        System.out.println(goodbyeMessage);
    }

    public static String formatStr(String str) {
        String returnstr =  "############################\n"
                            + str + "\n"
                            + "############################";
        return returnstr;
    }

    public static class Task {
        private boolean mark;
        private String content;

        public Task(String content) {
            this.content = content;
            this.mark = false;
        }

        public void setMark() {
            this.mark = !this.mark;
            if (mark == true) {
                System.out.println("NICE! The task is completed.");
            } else {
                System.out.println("Ok, the task is not done.");
            }
        }

        public String toString() {
            String sign = "";
            if (this.mark == false) {
                sign = " ";
            } else {
                sign = "X";
            }
            return ". [" + sign + "] " + this.content;
        }
    }

    public static void mark(String marked, int index, List<Task> arrTasks) {
        arrTasks.get(index).setMark();
    }

    public static String addReport(String str) {
        String returnStr = "added: " + str;
        return returnStr;
    }

    public static String listThings(List<Task> arrList) {
        String returnstr = "";
        for (int i = 0; i < arrList.size(); i++) {
            if (i == arrList.size() - 1) {
                returnstr += Integer.toString(i+1) + arrList.get(i).toString();
            } else {
                returnstr += Integer.toString(i+1) + arrList.get(i).toString() + "\n";
            }
        } return returnstr;
    }
}


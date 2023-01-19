import java.util.Scanner;

public class Jane {
    public static class Task {
        protected String description;
        protected boolean isDone;
        protected int num;


        public Task(int num, String description) {
            this.num = num;
            this.description = description;
            this.isDone = false;
        }
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void changeState(boolean stat) {
            this.isDone = stat;
        }

        @Override
        public String toString() {
            return String.format("%d. [%s] %s", this.num, this.getStatusIcon(), this.description);

        }

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        int count = 1;
        Task[] tasks = new Task[101];
        while (in.hasNext()) {
            String output = in.nextLine();
            Task t = new Task(count, output);
            tasks[count] =t;
            if (output.equals("bye")) {
                break;
            }
            if(output.startsWith("mark")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                if (num < count) {
                    System.out.println("Nice! I've marked this task as done");
                    tasks[num].changeState(true);
                    Task n = tasks[num];
                    System.out.println(n.toString());
                } else {
                    System.out.println("Number out of index");
                    continue;
                }

            }
            else if(output.startsWith("unmark")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                if (num < count) {
                    System.out.println("OK, I've marked this task as not done yet");
                    tasks[num].changeState(false);
                    Task n = tasks[num];
                    System.out.println(n.toString());
                } else {
                    System.out.println("Number out of index");
                    continue;
                }
            }
            else if (!output.equals("list") ) {
                count+=1;
                System.out.println("added: "+ t.description);
            } else {
                for (int i = 1; i < count; i++) {
                    System.out.println(tasks[i].toString());
                }
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
    }


}
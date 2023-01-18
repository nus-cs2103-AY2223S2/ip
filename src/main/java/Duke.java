import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static class Task {
        protected int id;
        protected String desc;
        protected boolean isDone;

        public Task(int id, String description) {
            this.id = id;
            this.desc = description;
            this.isDone = false;
        }
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
        public void setIsDone(boolean status) {
            this.isDone = status;
        }
        @Override
        public String toString() {
            String statusIcon = this.getStatusIcon();
            return id + ". [" + statusIcon + "] " + this.desc;
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hey! This is Duke at your service!");
        while(myObj.hasNext()) {
            String input = myObj.nextLine();
            Task tsk = new Task(tasks.size() + 1,input);
            if (input.equals("list")) {
                tasks.forEach(x -> {
                    System.out.println(x);
                });
                continue;
            }
            if (input.equals("bye")) {
                System.out.println("See you again, thanks for visiting!");
                break;
            }
            if(input.startsWith("mark ")) {
                String[] inp = input.split(" ");
                int id = Integer.parseInt(inp[1]);
                if(id > tasks.size()){
                    System.out.println("No such task found!");
                    continue;
                }
                Task marked = tasks.get(id - 1);
                marked.setIsDone(true);
                System.out.println("Good job! This task is now marked done!\n" + marked);
                continue;
            }
            if(input.startsWith("unmark ")) {
                String[] inp = input.split(" ");
                int id = Integer.parseInt(inp[1]);
                if(id > tasks.size()){
                    System.out.println("No such task found!");
                    continue;
                }
                Task unmarked = tasks.get(id - 1);
                unmarked.setIsDone(false);
                System.out.println("What a bummer! This task is now unmarked\n" + unmarked);
                continue;
            }
            tasks.add(tsk);
            System.out.println(input);
        }
    }
}

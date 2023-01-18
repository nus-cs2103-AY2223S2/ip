import java.util.*;

public class Duke {
    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void setDone(){
            this.isDone = true;
        }

        public void setNotDone(){
            this.isDone = false;
        }

        public String getName(){
            return this.description;
        }

        @Override
        public String toString (){
            return "[" + getStatusIcon() + "] " + this.description +"\n";
        }

    }


    public static void main(String[] args) {
        Task[] list = new Task[100];
        int listLen = 0;

        String intro = "  ________________________________\n"
                + "  Hello! I'm Mark\n"
                + "  What can I do for you?\n"
                + "  ________________________________\n";
        System.out.println(intro);

        Scanner sc= new Scanner(System.in);

        //start of bot
        while(true){
            String str= sc.nextLine();
            String[] splitStr = str.split(" ");

            //END
            if(splitStr[0].equals("bye")) {
                break;
            }

            //List Command
            if(splitStr[0].equals("list")) {
                System.out.print("  ________________________________\n");
                System.out.print("  Here are the tasks in tour list:\n");
                for(int i = 0; i < listLen ; i++){
                    int index = i + 1;
                    String item = "  " + index + ". " + list[i].toString();
                    System.out.print(item);
                }
                System.out.print("  ________________________________\n");
                continue;
            }

            if(splitStr[0].equals("mark")) {
                int index = Integer.parseInt(splitStr[1]) - 1;
                list[index].setDone();
                String reply = "  ________________________________\n"
                        + "  Nice! I've marked this task as done:\n"
                        + "    " + list[index].toString()
                        + "  ________________________________\n";

                System.out.print(reply);
                continue;
            }

            if(splitStr[0].equals("unmark")) {
                int index = Integer.parseInt(splitStr[1]) - 1;
                list[index].setNotDone();
                String reply = "  ________________________________\n"
                        + "  OK, I've marked this task as not done:\n"
                        + "    " + list[index].toString()
                        + "  ________________________________\n";

                System.out.print(reply);
                continue;
            }

            Task curr = new Duke().new Task(str);
            list[listLen] = curr;
            listLen++;

            String reply = "  ________________________________\n"
                    + "  added: " + str + "\n"
                    + "  ________________________________\n";
            System.out.print(reply);


        }

        String bye = "  ________________________________\n"
                + "  Bye! have a great day\n"
                + "  ________________________________\n";
        System.out.println(bye);

    }
}

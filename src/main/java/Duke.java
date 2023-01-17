import java.util.Scanner;

class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();
        String[] s = input.split(" ");
        Task[] list = new Task[100];
        int currentIndex = 0;
        while(!input.equals("bye")) {
           if (input.equals("list")) {
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < currentIndex; i++) {
                    System.out.print(i + 1 + ".");
                    System.out.println(list[i].toString());
                }
                input = myObj.nextLine();
                s = input.split(" ");
            } else if (s[0].equals("unmark")) {
                    Task taskName = list[Integer.parseInt(s[1]) - 1];
                    taskName.unMark();
                    System.out.println("OK, I've marked this task as not done yet");
                        System.out.println(taskName.toString());
                    input = myObj.nextLine();
                    s = input.split(" ");
                }
            else if (s[0].equals("mark")) {
                Task taskName = list[Integer.parseInt(s[1]) - 1];
                taskName.markAsDone();
               System.out.println("Nice! I've marked this task as done:");
               System.out.println(taskName.toString());
                input = myObj.nextLine();
                s = input.split(" ");
            } else if (s[0].equals("todo")){
                String taskDescription = "";
                for (int j = 1; j < s.length; j++) {
                    taskDescription += s[j];
                }
                ToDos taskName = new ToDos(taskDescription);
               System.out.println("Got it. I've added this task:");
               System.out.println(taskName.toString());
               list[currentIndex] = taskName;
               currentIndex++;
               System.out.println("Now you have " + currentIndex + " tasks in the list.");
               input = myObj.nextLine();
               s = input.split(" ");
           }
           else if (s[0].equals("deadline")){
               String taskDescription = "";
               boolean isTime = false;
               String time = "";
               for (int j = 1; j < s.length; j++) {
                   if(s[j].equals("/by")) {
                       isTime = true;
                   } else if(isTime) {
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
               list[currentIndex] = taskName;
               currentIndex++;
               System.out.println("Now you have " + currentIndex + " tasks in the list.");
               input = myObj.nextLine();
               s = input.split(" ");
           }
           else if (s[0].equals("event")){
               String taskDescription = "";
               boolean isStartTime = false;
               boolean isEndTime = false;
               String startTime = "";
               String endTime = "";
               for (int j = 1; j < s.length; j++) {
                   if(s[j].equals("/from")) {
                       isStartTime = true;
                   } else if(isStartTime && !s[j].equals("/to") && !isEndTime) {
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
                   }
                    else {
                       taskDescription += s[j];
                       taskDescription += " ";
                   }
               }
               Events taskName = new Events(taskDescription, startTime, endTime);
               System.out.println("Got it. I've added this task:");
               System.out.println(taskName.toString());
               list[currentIndex] = taskName;
               currentIndex++;
               System.out.println("Now you have " + currentIndex + " tasks in the list.");
               input = myObj.nextLine();
               s = input.split(" ");
           }

            }


        System.out.println("Bye. Hope to see you again soon!");
    }
}

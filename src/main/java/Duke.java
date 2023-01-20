import java.util.Scanner;
public class Duke {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        private static String greetingsFromSkittles = "Hello I'm Skittles\nWhat can I do for you?\n";
        private static String adiosFromSkittles = "Bye. Hope to see you again soon!";

        //assume no more than 100 tasks
        static Task[] lstOfTasks = new Task[100];

        //keeping track of number of things in list
        static int numOfThings = 0;

        //method to greet
        public static void hello() {
            System.out.print(greetingsFromSkittles);
        }

        public static void adios() {
            System.out.print(adiosFromSkittles);
        }

        public static void addStufftoLst(String xx) {
            lstOfTasks[numOfThings] = new Task(xx);
            numOfThings += 1;
            System.out.println("added: " + xx);
        }

        //basically print out the list. must be numbered.
        public static void displayLst() {
            boolean isItMT = false;
            StringBuilder txtToDisplay = new StringBuilder("");
            for (Task thingInList : lstOfTasks) {
                if (thingInList != null) {
                    isItMT = true;
                    txtToDisplay.append("\n").append(thingInList.getRank()).append(".").append(thingInList.isCompleted()).
                            append(thingInList.getName());
                }
            }
            if (!isItMT) {
                System.out.println("Your list is empty!");
            } else {
                System.out.println(txtToDisplay);
            }
        }


        public static void completeTask(String xx) {
            int taskNum = Integer.parseInt(xx);
            String txt = "Try again!";
            for (Task thingInList : lstOfTasks) {
                if (thingInList != null && (thingInList.getRank() == taskNum)) {
                    thingInList.strike();
                    txt = ("Nice! I've marked this task as done:\n" + thingInList.isCompleted()
                            + " " + thingInList.getName());
                }
            }
            System.out.println(txt);
        }
    public static void main(String[] args) {
        //start by greeting
        hello();
        while (true) {
            Scanner takingInput = new Scanner(System.in);
            String userTyped = takingInput.nextLine().toLowerCase();
                String frontWord = userTyped.split(" ")[0];
                //exit
                if (frontWord.equals("bye")) {
                    adios();
                    break;
                } else if (frontWord.equals("list")) {
                    //show user the list in this case
                    displayLst();
                } else if (frontWord.equals("mark")) {
                    completeTask(userTyped.substring(userTyped.length() - 1));

                } else {
                    //else the user is just adding another thing to list
                    addStufftoLst(userTyped);
                }
            }
        }
    }


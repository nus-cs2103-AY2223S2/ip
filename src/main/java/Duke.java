import java.util.Scanner;
public class Duke {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

    private static String greetingsFromSkittles = "Hello I'm Skittles\nWhat can I do for you?\n";
    private static String adiosFromSkittles = "Bye. Hope to see you again soon!";

    private static String gotItMessage = "Got it. I've added this task:\n";

    //keeping track of number of things in list
    static int numOfThings = 0;
    static String howLongListNowMessage = "\nNow you have " + numOfThings + " tasks in the list";

        //assume no more than 100 tasks
        static Task[] lstOfTasks = new Task[100];

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
            StringBuilder txtToDisplay = new StringBuilder("Here are the tasks in your list:");
            for (Task thingInList : lstOfTasks) {
                if (thingInList != null) {
                    isItMT = true;
                    txtToDisplay.append("\n").append(thingInList.getRank()).append(".")
                            .append(thingInList.toString());
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
                            + thingInList.getName());
                }
            }
            System.out.println(txt);
        }

        public static void undoCompleteTask(String xx) {
            int taskNum = Integer.parseInt(xx);
            String txt = "Try again!";
            for (Task thingInList : lstOfTasks) {
                if (thingInList != null && (thingInList.getRank() == taskNum)) {
                    thingInList.unstrike();
                    txt = ("Ok, I've marked this task as not done yet:\n" + thingInList.isCompleted()
                            + thingInList.getName());
                }
            }
            System.out.println(txt);
        }

    public static void addAToDo(String todo) {
        numOfThings += 1;
        ToDo mustDo = new ToDo(todo);
        lstOfTasks[numOfThings] = mustDo;
        System.out.println(gotItMessage + mustDo.toString() + howLongListNowMessage);
    }

    public static void addTimeSensitive(String name, String doByWhen) {
        Deadline dateline = new Deadline(name, doByWhen);
        lstOfTasks[numOfThings] = dateline;
        numOfThings += 1;
        System.out.println(gotItMessage + dateline.toString() + howLongListNowMessage);
    }

    public static void addAnEvent(String name, String startTime, String endTime) {
        Event suitAndTie = new Event(name, startTime, endTime);
        lstOfTasks[numOfThings] = suitAndTie;
        numOfThings += 1;
        System.out.println(gotItMessage + suitAndTie.toString() + howLongListNowMessage);
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
                } else if (frontWord.equals("unmark")) {
                    undoCompleteTask(userTyped.substring(userTyped.length() - 1));
                } else if (frontWord.equals("todo")) {
                    String actualTask = userTyped.split(" ", 2)[1];
                    addAToDo(actualTask);
                } else if (frontWord.equals("deadline")) {
                    String actualDeadlineTask = userTyped.split(" ", 2)[1].split(" /by ",2)[0];
                    String byWhen = userTyped.split(" ", 2)[1].split(" /by ",2)[1];
                    addTimeSensitive(actualDeadlineTask,byWhen);
                } else if (frontWord.equals("event")) {
                    String actualEvent = userTyped.split(" ",2)[1].split(" /from ", 2)[0];
                    String startTime =  userTyped.split(" ",2)[1].split(" /from ", 2)[1]
                                        .split(" /to ",2)[0];
                    String endTime =  userTyped.split(" ",2)[1].split(" /from ", 2)[1]
                            .split(" /to ",2)[1];
                    addAnEvent(actualEvent,startTime,endTime);
                } else {
                    System.out.println("Try again fat fingers!");
                }
            }
        }
    }


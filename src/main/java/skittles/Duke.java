package skittles;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public Duke() {}

    private static final Scanner takingInput = new Scanner(System.in);

    private static String greetingsFromSkittles = "Hello I'm Skittles\nWhat can I do for you?\n";
    private static String adiosFromSkittles = "Bye. Hope to see you again soon!";

    private static String gotItMessage = "Got it. I've added this task:\n";

    //keeping track of number of things in list
    //static int numOfThings = 0;

    private static final ListOfStuff lstOfTasks = new ListOfStuff(Data.loadUpInfo());

        //method to greet
        public void hello() {
            System.out.print(greetingsFromSkittles);
        }

        public static void adios() {
            System.out.print(adiosFromSkittles);
        }

    /**
     * Interpret what the user has entered as an input and categorises it into a Command.
     * @param userTyped User's input
     * @return The correct command that is interpreted from the user input.
     */
    public static Instruction inputToInstruction(String userTyped) {
        String[] typed = userTyped.split(" ", 2);
        return Instruction.scanUserTyped(typed[0]);
    }
/*
        public static void addStufftoLst(String xx) {
            lstOfTasks.add(new Task(xx));
            numOfThings += 1;
            System.out.println("added: " + xx);
        }
*/
/*
        //basically print out the list. must be numbered.
        public static void displayLst() {
            boolean isItMT = false;
            StringBuilder txtToDisplay = new StringBuilder("Here are the tasks in your list:");
            String nothingMessage = "Nothing in your list man!";
            for (Task thingInList : lstOfTasks) {
                if (thingInList != null) {
                    isItMT = true;
                    txtToDisplay.append("\n").append(thingInList.getRank()).append(".")
                            .append(thingInList.toString());
                }
            }
            if (!isItMT) {
                System.out.println(nothingMessage);
            } else {
                System.out.println(txtToDisplay);
            }
        }
*/
/*
        public static void completeTask(String xx) {
            int taskNum = Integer.parseInt(xx);
            String txt = "Try again! You don't have that task in your list!";
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
/*
    /**
     * Adds a Todo to all Tasks that Skittles has stored.
     * @param userTyped The entire String that the user has input i.e. "todo xxx".
     * @throws SkittlesException If an incorrect input is entered.
     */
    public void addAToDo(String userTyped) throws SkittlesException {
        //firstly we check if the user only inputted one word "todo"
        if (userTyped.split(" ",2).length == 1) {
            throw new SkittlesException("Hey you didn't include a todo!");
        }
        //otherwise if todo is inputted with other words, check the info
        String[] generalInfo = userTyped.split(" ",2);
        ToDo mustDo = new ToDo(generalInfo[1]);
        lstOfTasks.add(mustDo);
        Data.addInsideFile(mustDo);
        System.out.println("Got it. I've added this task:\n" + mustDo.toString() +
                            "\nNow you have " + ListOfStuff.numOfThings() + " tasks in the list.");
    }

    /**
     * Adds a Deadline to all Tasks that Skittles has stored.
     * @param userTyped The entire String that the user has input i.e. "deadline xxx /by yyy".
     * @throws SkittlesException If an incorrect input is entered.
     */
    public void addTimeSensitive(String userTyped) throws SkittlesException {
        // First check if the user has only input the one word "deadline".
        if (userTyped.split(" ", 2).length == 1) {
            throw new SkittlesException("Hey it looks like you are missing the description and the deadline date!");
        }
        // If "deadline" is entered with more words, check information.
        String[] information = userTyped.split(" ", 2);
        String [] description = information[1].split("/by ", 2);
        //In the case where date is not entered.
        if (description.length == 1) {
            throw new SkittlesException("Hey you are missing the deadline date! Please attempt again.");
        }
        Deadline newDeadline = new Deadline(description[0], description[1]);
        lstOfTasks.add(newDeadline);
        Data.addInsideFile(newDeadline);
        System.out.println("Got it. I've added this task:\n" + newDeadline.toString() + "\nNow you have " + ListOfStuff.numOfThings() + " tasks in the list");
    }

    /**
     * Adds an Event to all Tasks that Skittles has stored.
     * @param userTyped The entire String that the user has input i.e. "event xxx /from yyy /to zzz".
     * @throws SkittlesException If an incorrect input is entered.
     */
    public void addAnEvent(String userTyped) throws SkittlesException {
        //Start by checking if user only inputted one word "event"
        if (userTyped.split(" ",2).length == 1) {
            throw new SkittlesException("Hey you didn't type the event and the time range!");
        }

        String[] generalInfo = userTyped.split(" ",2);
        String[] eventAndEntireTimeRange= generalInfo[1].split(" /from ",2);
        String[] startAndEndTime = eventAndEntireTimeRange[1].split(" /to ",2);

        //next possibility the word "event" is typed with actual event but no time range
        if (userTyped.split(" ",2)[1].split(" /from ",2).length == 1) {
            throw new SkittlesException("Hey looks like you're missing a time range!");
        }

        String actualEvent = eventAndEntireTimeRange[0];
        String startTime = startAndEndTime[0];
        String endTime = startAndEndTime[1];
        Event suitAndTie = new Event(actualEvent, startTime, endTime);
        lstOfTasks.add(suitAndTie);
        Data.addInsideFile(suitAndTie);
        System.out.println("Got it. I've added this task:\n" + suitAndTie.toString() +
                "\nNow you have " + ListOfStuff.numOfThings() + " tasks in the list");
    }

    /* Method that saves Tasks in the hard disk whenever called
    */
    public void save() {
        try {
            FileWriter info = new FileWriter("./data/data.txt");
            for (Task task : ListOfStuff.getSkittlesList()) {
                info.write(task.toString() + "\n");
            }
            info.close();
            System.out.print("Tasks have been saved");
        } catch (IOException e) {
            System.out.println("Hey an error occurred when saving the data!");
        }
    }

    /*
    public static void delete (String userTyped) {
        try {
            String rankOfTaskToDelete = userTyped.split(" ",2)[1];
            int taskToDeleteInt = Integer.parseInt(rankOfTaskToDelete);
            Task deleted = lstOfTasks.remove(taskToDeleteInt - 1);
            numOfThings --;
            //making sure every task after point of deletion has its rank updated to -1
            /* for (int i = taskToDeleteInt; i < numOfThings; i++) {
                int updatedRank = lstOfTasks.get(i).getRank() - 1;

            }
            System.out.println("Noted. I've removed this task.\n" + deleted.toString()
                    + "\nNow you have " + numOfThings + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You didn't enter a valid task to delete man!");
        }
    }
    */
    public void begin() {

        boolean repeatStatus = true;

        //start by greeting
        hello();
        String input = takingInput.nextLine();
        Instruction instruction = inputToInstruction(input);

        while (repeatStatus) {
            //String userTyped = takingInput.nextLine().toLowerCase();
            //String frontWord = userTyped.split(" ",2)[0];
            switch(instruction) {
                case BYE:
                    repeatStatus = false;
                    adios();
                    break;
                case LIST:
                    //show user the list in this case
                    ListOfStuff.displayLst();
                    break;
                case MARK:
                    try {
                        ListOfStuff.completeTask(input);
                        ListOfStuff.refresh();
                    } catch (SkittlesException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case TODO:
                    try {
                        addAToDo(input);
                    } catch (SkittlesException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DEADLINE:
                    try {
                        addTimeSensitive(input);
                    } catch (SkittlesException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case EVENT:
                    try {
                        addAnEvent(input);
                    } catch (SkittlesException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DELETE:
                    try {
                        ListOfStuff.delete(input);
                        ListOfStuff.refresh();
                    } catch (SkittlesException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Try again fat fingers!");
                }
            if (repeatStatus) {
                input = takingInput.nextLine();
                instruction = inputToInstruction(input);
            }
            }
        }

        public static void main(String[] args) {
            Duke skittles = new Duke();
            skittles.begin();
        }
    }


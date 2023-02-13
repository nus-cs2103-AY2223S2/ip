package skittles;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import skittles.instruction.Instruction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Duke extends Application {

    private Parser parser;
    private ListOfStuff list;
    private final Scanner sc = new Scanner(System.in);
    public Duke(String filePath) {
        Data data = new Data(filePath);
        list = new ListOfStuff(data.loadUpInfo(),data);
        parser = new Parser(list,data);
    }

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private static final Scanner takingInput = new Scanner(System.in);

    private static String greetingsFromSkittles = "Hello I'm Skittles\nWhat can I do for you?\n";
    private static String adiosFromSkittles = "Bye. Hope to see you again soon!";

    private static String gotItMessage = "Got it. I've added this task:\n";

    //keeping track of number of things in list
    //static int numOfThings = 0;

    //private static final ListOfStuff lstOfTasks = new ListOfStuff(Data.loadUpInfo());

    //method to greet
    public void hello() {
        System.out.print(greetingsFromSkittles);
    }

    public String getGreetingsFromSkittles() {
        return greetingsFromSkittles;
    }

    public static void adios() {
        System.out.print(adiosFromSkittles);
    }

    /**
     * Interpret what the user has entered as an input and categorises it into a Command.
     * @param userTyped User's input
     * @return The correct command that is interpreted from the user input.
     */
    /*
    public static Instruction inputToInstruction(String userTyped) {
        String[] typed = userTyped.split(" ", 2);
        return Instruction.scanUserTyped(typed[0]);
    }
    */
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
*/
    /**
     * Adds a Todo to all Tasks that Skittles has stored.
     * @param userTyped The entire String that the user has input i.e. "todo xxx".
     * @throws SkittlesException If an incorrect input is entered.
     */
    /*
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
/*

     */
    /**
     * Adds a Deadline to all Tasks that Skittles has stored.
     * @param userTyped The entire String that the user has input i.e. "deadline xxx /by yyy".
     * @throws SkittlesException If an incorrect input is entered.
     */
    /*
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
*/
    /**
     * Adds an Event to all Tasks that Skittles has stored.
     * @param userTyped The entire String that the user has input i.e. "event xxx /from yyy /to zzz".
     * @throws SkittlesException If an incorrect input is entered.
     */
    /*
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
*/
    /**
     * Lists out all the Tasks that contains the input given by the user.
     * @param userTyped Given by the user
     * @throws SkittlesException No matches found error.
     */
    /*
    public void find(String userTyped) throws SkittlesException {
        if (userTyped.split(" ", 2).length == 1) {
            throw new SkittlesException("Hey man you're missing the keyword you wish to search!");
        }
        if (userTyped.split(" ", 2).length > 2) {
            throw new SkittlesException("Hey man don't type multiple keywords!");
        }
        String[] information = userTyped.split(" ",2);
        String output = "Here are the matching tasks in your list:\n";
        String keyword = information[1];
        ArrayList<Task> results = lstOfTasks.findInList(keyword);
        for (int i = 1; i <= results.size(); i++) {
            output = output.concat(i+ "." + results.get(i-1).toString() + "\n");
        }
        if (!results.isEmpty()) {
            System.out.println(output);
        } else {
            System.out.println("You do not have such an item in your list currently!");
        }
    }

    /*
    /* Method that saves Tasks in the hard disk whenever called
     */
    /*
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
*/
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


    /**
     * Displays a long line for visual effects.
     */
    public static String showLine() {
        String line = "-------------------------------";
        return line;
    }
    /*
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
                case FIND:
                    try {
                        find(input);
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
*/
    /*
    public static void main(String[] args) {
        Duke skittles = new Duke();
        skittles.begin();
    }
*/
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Instruction instruction = parser.inputToCommand(input);
        return instruction.getResponse(input);
    }

}
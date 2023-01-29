package dukes.engine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class DukeEngine extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
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
}

//class DukeEngine {
//
//    public static final String divisionLine = "________________________________________";
//    public static final String logo =
//            " ____        _        \n"
//            + "|  _ \\ _   _| | _____ \n"
//            + "| | | | | | | |/ / _ \\\n"
//            + "| |_| | |_| |   <  __/\n"
//            + "|____/ \\__,_|_|\\_\\___|\n";
//    public static final String greetWord = "It's a pleasure to serve you!";
//    public static final String byeWord = "Goodbye. Hope you have a nice day!";
//    public static final String markDoneWord =
//            "Nice! You have completed this task: ";
//    public static final String markUnDoneWord =
//            "Well, you have not finished this task yet: ";
//    public static final String listWord = "Here are all of your tasks: ";
//    public static final String searchWord = "Here are your tasks on the given date: ";
//    public static final String addWord = "This task is added to your list: ";
//    public static final String deleteWord = "Ok, I will remove this task for you: ";
//
//    public List<Task> taskList = new ArrayList<Task>();
//
////    /**
////     * Print the greeting line and horizontal lines for the chat engine. Returns nothing.
////     */
//    void greet() {
//        // String divisionLine = "________________________________________";
//        System.out.println(divisionLine);
//        System.out.println("Hello from\n" + logo);
//        System.out.println(greetWord);
//        System.out.println(divisionLine);
//    }
//
////    /**
////     * Print an echoing copy of the command string. Returns nothing.
////     * @command the command to be echoed.
////     */
//    void echo(String command) {
//        System.out.println(divisionLine);
//        System.out.println(command);
//        System.out.println(divisionLine);
//    }
//
////    /**
////     * (deprecated) add the command task into the taskList.
////     * @command the command to be added to the task.
////     */
//    void addTask(String command) {
//        Task theTask = new Task(command);
//        taskList.add(theTask);
//        System.out.println("added: " + command);
//    }
//
//    LocalDate validateTime(String timeString) throws DateTimeParseException {
//        // Set the time input as dd/mm/yyyy
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//        LocalDate theDate = LocalDate.parse(timeString, formatter);
//        return theDate;
//    }
//
////    /**
////     * Validate if the ToDo command is valid.
////     * If valid, continue to handle the ToDo task; otherwise, throw a DukeException
////     * of empty content of ToDo.
////     * @command the command to be validated.
////     * @throws DukeException if command.length < 2 (i.e. have no content).
////     */
//    void validateToDo(String command) throws DukeException {
//        String[] splited = command.split(" ");
//        if (splited.length < 2) {
//            throw new DukeException("OOPS!!! Description of todo not found.");
//        } else {
//            handleToDo(command);
//        }
//    }
//
////    /**
////     * Interpret the ToDo command, add the content into the taskList,
////     * and display the number of items in list.
////     * @command the command to be interpreted. Assumed to be valid.
////     */
//    void handleToDo(String command) {
//        String[] splited = command.split(" ");
//        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i < splited.length; i++) {
//            sb.append(splited[i]).append(" ");
//        }
//        sb.deleteCharAt(sb.length()-1);
//        String taskName = sb.toString();
//
//        Task theTask = new ToDo(taskName);
//        taskList.add(theTask);
//
//        System.out.println(addWord);
//        System.out.println(theTask.toString());
//        System.out.println("Now you have " + taskList.size() +
//                " tasks in the list.");
//    }
//
////    /**
////     * Validate if the DeadLine command is valid.
////     * If valid, continue to handle the DeadLine task; otherwise, throw a DukeException.
////     * @command the command to be validated.
////     * @throws DukeException if
////     * command.length < 2 (i.e. have no task content); OR
////     * the timestamp after "/by" is empty.
////     */
//    void validateDeadLine(String command) throws DukeException, DateTimeParseException {
//        String[] splited = command.split(" ");
//        if (splited.length < 2) {
//            throw new DukeException("OOPS!!! Description of deadline not found.");
//        } else if (!Arrays.asList(splited).contains("/by")) {
//            throw new DukeException("Deadline of the task not specified.");
//        } else if (splited[splited.length - 1].equals("/by")) {
//            throw new DukeException("No valid deadline specified.");
//        } else {
//            // Maybe move the part in handleDeadLine up
//            StringBuilder sb = new StringBuilder();
//            StringBuilder time = new StringBuilder();
//            boolean isTime = false;
//            for (int i = 1; i < splited.length; i++) {
//                if (splited[i].equals("/by")) {
//                    isTime = true;
//                } else if (!isTime) {
//                    sb.append(splited[i]).append(" ");
//                } else {
//                    time.append(splited[i]).append(" ");
//                }
//            }
//            sb.deleteCharAt(sb.length()-1);
//            time.deleteCharAt(time.length()-1);
//            String taskName = sb.toString();
//            String deadline = time.toString();
//
//            LocalDate theDate = validateTime(deadline);
//            handleDeadLine(taskName, theDate);
//        }
//    }
//
////    /**
////     * Interpret the DeadLine command, add the content into the taskList,
////     * and display the number of items in list.
////     * @command the command to be interpreted. Assumed to be valid.
////     */
//    void handleDeadLine(String taskName, LocalDate theDate) {
//        Task theTask = new DeadLine(taskName, theDate);
//        taskList.add(theTask);
//
//        System.out.println(addWord);
//        System.out.println(theTask.toString());
//        System.out.println("Now you have " + taskList.size() +
//                " tasks in the list.");
//    }
//
////    /**
////     * Validate if the Event command is valid.
////     * If valid, continue to handle the Event task; otherwise, throw a DukeException.
////     * @command the command to be validated.
////     * @throws DukeException if
////     * command.length < 2 (i.e. have no event content); OR
////     * the timestamp after "/by" is empty.
////     */
//    void validateEvent(String command) throws DukeException, DateTimeParseException {
//        String[] splited = command.split(" ");
//        if (splited.length < 2) {
//            throw new DukeException("OOPS!!! Description of event not found.");
//        } else if (!Arrays.asList(splited).contains("/from") ||
//                !Arrays.asList(splited).contains("/to")) {
//            throw new DukeException("Event time not specified completely.");
//        } else if (splited[splited.length - 1].equals("/to") ||
//                Arrays.asList(splited).indexOf("/from") ==
//                        Arrays.asList(splited).indexOf("/to") - 1) {
//            throw new DukeException("No valid event time specified.");
//        } else {
//            StringBuilder sb = new StringBuilder();
//            StringBuilder start = new StringBuilder();
//            StringBuilder end = new StringBuilder();
//            boolean isStart = false;
//            boolean isEnd = false;
//            for (int i = 1; i < splited.length; i++) {
//                if (splited[i].equals("/from")) {
//                    isStart = true;
//                } else if (splited[i].equals("/to")) {
//                    isStart = false;
//                    isEnd = true;
//                } else if (!isStart && !isEnd) {
//                    sb.append(splited[i]).append(" ");
//                } else if (isStart) {
//                    start.append(splited[i]).append(" ");
//                } else {
//                    end.append(splited[i]).append(" ");
//                }
//            }
//            sb.deleteCharAt(sb.length()-1);
//            start.deleteCharAt(start.length()-1);
//            end.deleteCharAt(end.length()-1);
//            String taskName = sb.toString();
//            String startTime = start.toString();
//            String endTime = end.toString();
//
//            LocalDate startDate = validateTime(startTime);
//            LocalDate endDate = validateTime(endTime);
//            handleEvent(taskName, startDate, endDate);
//        }
//    }
//
//    void handleEvent(String taskName, LocalDate startTime, LocalDate endTime) {
//        Task theTask = new Event(taskName, startTime, endTime);
//        taskList.add(theTask);
//
//        System.out.println(addWord);
//        System.out.println(theTask.toString());
//        System.out.println("Now you have " + taskList.size() +
//                " tasks in the list.");
//    }
//
//    void setTaskList(List<Task> taskList) {
//        this.taskList = taskList;
//    }
//
//    void validateSearch(String command) throws DukeException, DateTimeParseException {
//        String[] splited = command.split(" ");
//        if (splited.length < 2) {
//            throw new DukeException("OOPS! Target date not found.");
//        }
//        LocalDate theDate = validateTime(splited[1]);
//        listSearch(theDate);
//    }
//
//    void listTask() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(listWord).append("\n");
//        for (int i = 0; i < taskList.size(); i++) {
//            sb.append(i+1).append(". ");
//            sb.append(taskList.get(i).toString()).append("\n");
//        }
//        if (sb.length() != 0) {
//            sb.deleteCharAt(sb.length() - 1);
//        }
//        System.out.println(sb.toString());
//    }
//
//    void listSearch(LocalDate theDate) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(searchWord).append("\n");
//        int counter = 0;
//        for (int i = 0; i < taskList.size(); i++) {
//            Task theTask = taskList.get(i);
//            if (theTask.getTag().equals("D") &&
//                    theTask.getDeadLine().equals(theDate)) {
//                counter += 1;
//                sb.append(counter).append(". ");
//                sb.append(theTask.toString()).append("\n");
//            } else if (theTask.getTag().equals("E") &&
//                    theTask.getStart().isBefore(theDate) &&
//                    theTask.getEnd().isAfter(theDate)) {
//                // begin < theDate, end > theDate
//                counter += 1;
//                sb.append(counter).append(". ");
//                sb.append(theTask.toString()).append("\n");
//            }
//        }
//        if (sb.length() != 0) {
//            sb.deleteCharAt(sb.length() - 1);
//        }
//        System.out.println(sb.toString());
//    }
//
//    void validateMark(String command, int action) throws DukeException {
//        String[] splited = command.split(" ");
//        if (splited.length < 2) {
//            throw new DukeException("You have not specified the index of the marking.");
//        } else {
//            int index = Integer.parseInt(splited[1]);
//            if (index <= 0 || index > taskList.size()) {
//                throw new DukeException("You are referring to an invalid index.");
//            } else {
//                if (action == 0) {
//                    markDone(command);
//                } else if (action == 1) {
//                    markUnDone(command);
//                } else {
//                    delete(command);
//                }
//            }
//        }
//    }
//
//    void markDone(String command) {
//        String[] splited = command.split(" ");
//        int index = Integer.parseInt(splited[1]);
//
//        Task theTask = taskList.get(index - 1);
//        theTask.setDone();
//
//        StringBuilder sb = new StringBuilder();
//        sb.append(markDoneWord).append("\n").append(" ");
//        sb.append(theTask.toString());
//        System.out.println(sb.toString());
//    }
//
//    void markUnDone(String command) {
//        String[] splited = command.split(" ");
//        int index = Integer.parseInt(splited[1]);
//
//        Task theTask = taskList.get(index - 1);
//        theTask.setUnDone();
//
//        StringBuilder sb = new StringBuilder();
//        sb.append(markUnDoneWord).append("\n").append(" ");
//        sb.append(theTask.toString());
//        System.out.println(sb.toString());
//    }
//
//    String generateTaskList() {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < taskList.size(); i++) {
//            Task currTask = taskList.get(i);
//            sb.append(currTask.getTag()).append("@");
//            sb.append((currTask.hasDone()) ? "1" : "0").append("@");
//            sb.append(currTask.getTaskName());
//            if (currTask.getTag().equals("D")) {
//                // Here by default use pattern yyyy-MM-dd
//                sb.append("@").append(currTask.getDeadLine());
//            } else if (currTask.getTag().equals("E")) {
//                sb.append("@").append(currTask.getStart()).
//                        append("@").append(currTask.getEnd());
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
//
//    static Task fetchTask(String fileLine) {
//        // System.out.println(fileLine);
//        // Still get issues -- must be able to parse the date time!!
//        Task newTask;
//        // DO NOT USE | as separator!! You need \\| for escape. Better use @
//        String[] temp = fileLine.split("@");
//        // System.out.println(Arrays.toString(temp));
//        boolean isDone = (temp[1].equals("0")) ? false : true;
//        if (temp[0].equals("T")) {
//            newTask = new ToDo(temp[2], isDone);
//        } else if (temp[0].equals("D")) {
//            DateTimeFormatter formatter =
//                    DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("en"));
//            LocalDate deadline = LocalDate.parse(temp[3], formatter);
//            newTask = new DeadLine(temp[2], isDone, deadline);
//        } else {
//            DateTimeFormatter formatter =
//                    DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("en"));
//            LocalDate start = LocalDate.parse(temp[3], formatter);
//            LocalDate end = LocalDate.parse(temp[4], formatter);
//            newTask = new Event(temp[2], isDone, start, end);
//        }
//        return newTask;
//    }
//
//    void delete(String command) {
//        String[] splited = command.split(" ");
//        int index = Integer.parseInt(splited[1]);
//
//        Task theTask = taskList.remove(index - 1);
//
//        System.out.println(deleteWord);
//        System.out.println(theTask.toString());
//        System.out.println("Now you have " + taskList.size() +
//                " tasks in the list.");
//    }
//
//    void goodbye() {
//        System.out.println(divisionLine);
//        System.out.println(byeWord);
//        System.out.println(divisionLine);
//    }
//
//    void printLine() {
//        System.out.println(divisionLine);
//    }
//
//}

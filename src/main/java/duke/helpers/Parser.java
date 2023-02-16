package duke.helpers;

import duke.exceptions.OutOfIndexException;
import duke.exceptions.VagueInputException;
import duke.exceptions.WrongBooleanException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.visuals.DialogBox;

import duke.visuals.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Handles input cases by the user.
 */
public class Parser {

    /**
     * Handles text inputs, and manages the respective Scene elements according to what ever
     * text input from the user.
     *
     * @param dialogContainer this is an GUI entry shown on the screen can be showing
     *                        either user/muse's side of the conversation.
     * @param tasks Stores the Tasks objects into
     * @param userInput this contains the text, String info of whatever is being input from the user.
     * @param user contains the image file of the user's profile.
     * @param muse contains the image file of muse's profile.
     */
    public static void handleInputs(VBox dialogContainer, TaskList tasks, TextField userInput, Image user, Image muse) {
        String input = userInput.getText();
        Label userText = new Label(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, new ImageView(user)));
        String outputString = "";
        boolean hasHandledInput = false;
        String[] splitArr = input.split(" ");
        handleAndOutputString result = new handleAndOutputString(hasHandledInput, outputString);
        try {
            assert (!hasHandledInput) : "The program thinks it's done processing the commands!\n" +
                    "This is dangerous, please take a look @ Parser.java. :0 \n";
            result = handleOneWordCommands(input, tasks, splitArr, result);
            result = handleMark(tasks, splitArr, result);
            result = handleDelete(splitArr, tasks, result);
            result = handleFind(input, splitArr, tasks, result);
            result = handleTasks(splitArr, input, tasks, result);
            if (result.getHasHandled() == false){
                throw new VagueInputException("Oh no! What do you mean? \n" + "I'm confused. Please specify... @.@");
            }
        }
        catch (VagueInputException ex) {
            result.setOutputString(Ui.formatStr(ex.getMessage()));
        } catch (OutOfIndexException ex) {
            result.setOutputString(Ui.formatStr(ex.getMessage()));
        } catch (WrongBooleanException ex) {
            result.setOutputString(Ui.formatStr(ex.getMessage()));
        }
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(new Label(result.getOutputString()), new ImageView(muse)));
    }
    private static boolean setCaseHandled() {
        return true;
    }

    private static handleAndOutputString handleMark(TaskList tasks, String[] splitArr, handleAndOutputString result)
            throws OutOfIndexException, WrongBooleanException {
        if ((splitArr[0].equals("mark") || splitArr[0].equals("unmark")) && !result.getHasHandled()) {
            if ((Integer.parseInt(splitArr[1])) > tasks.getSize()) {
                throw new OutOfIndexException("Help! \n" +
                        "The number has to be within range of our task-list!\n" + "try again.");
            }
            if (splitArr[0].equals("mark") &&
                    tasks.getTask(Integer.parseInt(splitArr[1]) - 1).getMark() != false) {
                throw new WrongBooleanException("Hey! \n" +
                        "This is already done. You can't mark it again. :0 \n" + "try again.");
            } else if (splitArr[0].equals("unmark") &&
                    tasks.getTask(Integer.parseInt(splitArr[1]) - 1).getMark() != true) {
                throw new WrongBooleanException("Hey! \n" +
                        "This is undone. You can't mark it undone again. :0 \n" + "try again.");
            }
            result.setTrue();
            result.setOutputString(tasks.mark(splitArr[0], (Integer.parseInt(splitArr[1]) - 1)));
        } return result;
    }

    private static handleAndOutputString handleOneWordCommands(String input, TaskList tasks,
            String[] splitArr, handleAndOutputString result) throws VagueInputException {
        String outputString = "";
        if (input.equals("bye") && !result.getHasHandled()) {
            result.setOutputString(Ui.doFarewell());
            result.setTrue();
        }
        if (input.equals("help") && !result.getHasHandled()) {
            result.setOutputString(Ui.generateHelpSheet());
            result.setTrue();
        }
        if (input.equals("list") && !result.getHasHandled()) {
            result.setOutputString(Ui.formatStr(tasks.listThings()));
            result.setTrue();
        }
        if (splitArr.length == 1 && !result.getHasHandled()) {
            throw new VagueInputException("Oh no! What do you mean? \n" +
                    "I'm confused. Please specify... @.@");
        }
        return result;
    }

    private static handleAndOutputString handleDelete(String[] splitArr,
            TaskList tasks, handleAndOutputString result) throws OutOfIndexException {
        if (splitArr[0].equals("delete") && !result.getHasHandled()) {
            if ((Integer.parseInt(splitArr[1])) > tasks.getSize()) {
                throw new OutOfIndexException("Help! \n" +
                        "The number has to be within range of our task-list!\n" +
                        "Please try again!");
            }
            Task newTask = tasks.getTask(Integer.parseInt(splitArr[1]) - 1);
            tasks.removeTask(Integer.parseInt(splitArr[1]) - 1);
            result.setOutputString(Ui.formatStr(tasks.deleteReport(newTask)));
            result.setTrue();
        }
        return result;
    }

    private static handleAndOutputString handleFind(String input, String[] splitArr,
            TaskList tasks, handleAndOutputString result) throws OutOfIndexException {
        if (splitArr[0].equals("find") && ! result.getHasHandled()) {
            String searchTerm = input.substring(5);
            boolean hasFoundAnyTerms = false;
            String outputResults = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                if (!tasks.getTask(i).getContent().contains(searchTerm)) {
                    continue;
                }
                if (outputResults == "") {
                    outputResults += (i + 1) + tasks.getTask(i).toString();
                    hasFoundAnyTerms = true;
                    continue;
                }
                outputResults += "\n" + (i + 1) + tasks.getTask(i).toString();
                hasFoundAnyTerms = true;
            }
            result.setOutputString(Ui.outputSearchResults(hasFoundAnyTerms, outputResults));
            result.setTrue();
        }
        return result;
    }

    private static handleAndOutputString handleTasks(String[] splitArr, String input,
            TaskList tasks, handleAndOutputString result) {
        if (splitArr[0].equals("todo") && ! result.getHasHandled()) {
            Todo newTodo = new Todo(input);
            tasks.addTask(newTodo);
            result.setOutputString(Ui.formatStr(tasks.addReport(newTodo)));
            result.setTrue();
        }
        if (splitArr[0].equals("deadline") && !result.getHasHandled()) {
            Deadline newDead = new Deadline(input);
            tasks.addTask(newDead);
            result.setOutputString(Ui.formatStr(tasks.addReport(newDead)));
            result.setTrue();
        }
        if (splitArr[0].equals("event") && !result.getHasHandled()) {
            Event newEvent = new Event(input);
            tasks.addTask(newEvent);
            result.setOutputString(Ui.formatStr(tasks.addReport(newEvent)));
            result.setTrue();
        }
        return result;
    }
}
 class handleAndOutputString {
    private String outputString;
    private boolean hasHandledInput;

    public handleAndOutputString(boolean hasHandledInput, String outputString) {
        this.outputString = outputString;
        this.hasHandledInput = hasHandledInput;
    }

    public handleAndOutputString setTrue() {
        this.hasHandledInput = true;
        return this;
    }

    public handleAndOutputString setOutputString(String outputString) {
        this.outputString = outputString;
        return this;
    }

    public String getOutputString() {
        return this.outputString;
    }

    public boolean getHasHandled() {
        return this.hasHandledInput;
    }
}
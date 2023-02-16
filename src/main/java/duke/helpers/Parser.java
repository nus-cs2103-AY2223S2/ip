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
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userText, new ImageView(user))
        );

        String outputString = "";
        boolean hasHandledInput = false;


        String[] splitArr = input.split(" ");
        try {

            assert (!hasHandledInput) : "The program thinks it's done processing the commands!\n" +
                    "This is dangerous, please take a look @ Parser.java. :0 \n";

            if (input.equals("bye") && !hasHandledInput) {
                outputString = Ui.doFarewell();
                hasHandledInput = setCaseHandled();
            }

            //bye case is now handled, if handled a case, will skip the rest of the conditionals.

            if (input.equals("help") && !hasHandledInput) {
                outputString = Ui.formatStr(Ui.generateHelpSheet());
                hasHandledInput = setCaseHandled();
            }

            //help, in case user needs help is handled here.

            if (input.equals("list") && !hasHandledInput) {
               outputString = Ui.formatStr(tasks.listThings());
               hasHandledInput = setCaseHandled();
            }

            //list case is now handled

            if (splitArr.length == 1 && !hasHandledInput) {
                throw new VagueInputException("Oh no! What do you mean? \n" +
                        "I'm confused. Please specify... @.@");
            }

            //case where invalid input is entered is handled here.

            if ((splitArr[0].equals("mark") || splitArr[0].equals("unmark")) && !hasHandledInput) {
                if ((Integer.parseInt(splitArr[1])) > tasks.getSize()) {
                    throw new OutOfIndexException("Help! \n" +
                            "The number has to be within range of our task-list!\n" +
                            "try again.");
                }
                if (splitArr[0].equals("mark") &&
                        tasks.getTask(Integer.parseInt(splitArr[1]) - 1).
                                getMark() != false) {
                    throw new WrongBooleanException("Hey! \n" +
                            "This is already done. You can't mark it again. :0 \n" +
                            "try again.");
                } else if (splitArr[0].equals("unmark") &&
                        tasks.getTask(Integer.parseInt(splitArr[1]) - 1).
                                getMark() != true) {
                    throw new WrongBooleanException("Hey! \n" +
                            "This is undone. You can't mark it undone again. :0 \n" +
                            "try again.");
                }
                hasHandledInput = setCaseHandled();
                outputString = tasks.mark(splitArr[0], (Integer.parseInt(splitArr[1]) - 1));
            }

            if (splitArr[0].equals("delete") && !hasHandledInput) {
                if ((Integer.parseInt(splitArr[1])) > tasks.getSize()) {
                    throw new OutOfIndexException("Help! \n" +
                            "The number has to be within range of our task-list!\n" +
                            "Please try again!");
                }
                Task newTask = tasks.getTask(Integer.parseInt(splitArr[1]) - 1);
                tasks.removeTask(Integer.parseInt(splitArr[1]) - 1);
                outputString = Ui.formatStr(tasks.deleteReport(newTask));
                hasHandledInput = setCaseHandled();
            }

            if (splitArr[0].equals("find") && !hasHandledInput) {
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
                outputString = Ui.outputSearchResults(hasFoundAnyTerms, outputResults);
                hasHandledInput = setCaseHandled();
            }

            if (splitArr[0].equals("todo") && !hasHandledInput) {
                Todo newTodo = new Todo(input);
                tasks.addTask(newTodo);
                outputString = Ui.formatStr(tasks.addReport(newTodo));
                hasHandledInput = setCaseHandled();
            }
            if (splitArr[0].equals("deadline") && !hasHandledInput) {
                Deadline newDead = new Deadline(input);
                tasks.addTask(newDead);
                outputString = Ui.formatStr(tasks.addReport(newDead));
                hasHandledInput = setCaseHandled();
            }
            if (splitArr[0].equals("event") && !hasHandledInput) {
                Event newEvent = new Event(input);
                tasks.addTask(newEvent);
                outputString = Ui.formatStr(tasks.addReport(newEvent));
                hasHandledInput = setCaseHandled();
            }

            if (hasHandledInput == false){
                throw new VagueInputException("Oh no! What do you mean? \n" +
                        "I'm confused. Please specify... @.@");
            }
        }

        //end of try loop, we will now address errors and print the response of muse.

        catch (VagueInputException ex) {
            outputString = Ui.formatStr(ex.getMessage());
        } catch (OutOfIndexException ex) {
            outputString = Ui.formatStr(ex.getMessage());
        } catch (WrongBooleanException ex) {
            outputString = Ui.formatStr(ex.getMessage());
        }

        //we handle all the possible exceptions here, and catch them, updating
        //muse's input to reflect addressing the error.

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(new Label(outputString), new ImageView(muse))
        );
    }

    private static boolean setCaseHandled() {
        return true;
    }
}

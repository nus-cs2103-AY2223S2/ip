package skittles.instruction;

import skittles.SkittlesException;
import skittles.Task;
import skittles.ListOfStuff;

import java.util.List;

public class DoneInstruction extends Instruction {

    private final ListOfStuff skittlesList;

    public DoneInstruction(ListOfStuff skittlesList) {

        this.skittlesList = skittlesList;

    }

    @Override
    public String getResponse(String input) {
        int itemNumber;
        if (input.split(" ", 2).length == 1) {
            return "Oops! Looks like you are missing the task number you wish to mark as done!"
                    + " Try again :-)";
        }
        String numberInput = input.split(" ", 2)[1];
        try {
            itemNumber = Integer.parseInt(numberInput);
        } catch (NumberFormatException e) {
            return "You may have entered something incorrectly. "
                    + "Try adding a number behind 'done'!";
        }
        String message = "Oops! I cannot seem to find that task number. Try again!";
        if (skittlesList.isEmpty()) {
            message = "Oops! Your list is empty! Try adding a Task first!";
        } else if (itemNumber <= skittlesList.getSize()) {
            Task targetItem = skittlesList.getTask(itemNumber - 1);
            targetItem.strike();
            message = "Nice! I've marked this task as done:\n" + " " + targetItem.toString();
        }
        try {
            skittlesList.refresh();
        } catch (SkittlesException e) {
            return "Oops we can't update your list man! Try again!";
        }
        return message;
    }
}

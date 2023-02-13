package skittles.instruction;
import skittles.SkittlesException;
import skittles.Task;
import skittles.ListOfStuff;
public class UndoneInstruction extends Instruction {

    private final ListOfStuff skittlesList;

    public UndoneInstruction(ListOfStuff skittlesList) {
        this.skittlesList = skittlesList;
    }

    @Override
    public String getResponse(String input) {
        int itemNumber;
        if (input.split(" ", 2).length == 1) {
            return "Oops! Looks like you are missing the task number you wish to unmark!"
                    + " Try again :-)";
        }
        String numberInput = input.split(" ", 2)[1];
        try {
            itemNumber = Integer.parseInt(numberInput);
        } catch (NumberFormatException e) {
            return "You may have entered something incorrectly. "
                    + "Try adding a number behind 'undone'!";
        }
        String message = "Oops! I cannot seem to find that task number. Try again!";
        if (skittlesList.isEmpty()) {
            message = "Oops! Your list is empty! Try adding a Task first!";
        } else if (itemNumber <= skittlesList.getSize()) {
            Task targetItem = skittlesList.getTask(itemNumber - 1);
            targetItem.unstrike();
            message = "Nice! I've marked this task as not done:\n" + " " + targetItem.toString();
        }
        try {
            skittlesList.refresh();
        } catch (SkittlesException e) {
            return "Oops we can't update your list man! Try again!";
        }
        return message;
    }
}
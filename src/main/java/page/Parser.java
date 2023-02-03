package page;

import page.quest.Deadline;
import page.quest.Event;
import page.quest.Todo;

/**
 * Represents a parser that interprets inputs given to Page, then executes them.
 */
public class Parser {

    public boolean parseExecute(String input, Ui ui, Storage storage, QuestLog questLog) throws PageException {
        switch (input) {
        case "bye":
            storage.saveData(questLog);
            ui.printBye();
            return true;
        case "log":
            ui.printQuestLog(questLog);
            break;
        case "help":
            ui.printHelpMessage();
            break;
        default:
            String[] splitInput = input.split(" ", 2);
            switch (splitInput[0]) {
            case "todo":
                if (splitInput.length == 1) {
                    throw new PageException("Sorry, please include a task description!");
                } else {
                    Todo newTodo = questLog.addTodo(splitInput[1]);
                    ui.printQuestAdded(newTodo);
                }
                break;
            case "deadline":
                if (splitInput.length == 1) {
                    throw new PageException("Sorry, please include a task description!");
                } else {
                    String[] splitBy = splitInput[1].split(" /by ");
                    if (splitBy.length == 1) {
                        throw new PageException("Sorry, please include a /by time after the task description!");
                    } else {
                        Deadline newDeadline = questLog.addDeadline(splitBy[0], splitBy[1]);
                        ui.printQuestAdded(newDeadline);
                    }
                }
                break;
            case "event":
                if (splitInput.length == 1) {
                    throw new PageException("Sorry, please include a task description!");
                } else {
                    String[] splitFrom = splitInput[1].split(" /from ");
                    if (splitFrom.length == 1) {
                        throw new PageException("Sorry, please include a /from time!");
                    } else {
                        String[] splitTo = splitFrom[1].split(" /to ");
                        if (splitTo.length == 1) {
                            throw new PageException("Sorry, please include a /to time!");
                        } else {
                            Event newEvent = questLog.addEvent(splitFrom[0], splitTo[0], splitTo[1]);
                            ui.printQuestAdded(newEvent);
                        }
                    }
                }
                break;
            case "complete":
            case "incomplete":
            case "delete":
                if (splitInput.length == 1) {
                    throw new PageException("Sorry, please include the number of the quest to be marked "
                            + splitInput[0] + "!");
                } else {
                    try {
                        int questNum = Integer.parseInt(splitInput[1]);
                        if (questNum > questLog.size() || questNum <= 0) {
                            throw new PageException("Sorry, that's not a valid quest number!");
                        } else if (splitInput[0].equals("complete")) {
                            questLog.completeQuest(questNum);
                            ui.printQuestCompleted(questLog.getQuest(questNum));

                        } else if (splitInput[0].equals("incomplete")) {
                            questLog.incompleteQuest(questNum);
                            ui.printQuestIncompleted(questLog.getQuest(questNum));
                        } else {
                            ui.printQuestDeleted(questLog.getQuest(questNum));
                            questLog.deleteQuest(questNum);
                        }
                    } catch (NumberFormatException nfe) {
                        throw new PageException("Sorry, that's not a number!");
                    }
                }
                break;
            default:
                ui.printInvalidInput();
                break;
            }
            break;
        }
        return false;
    }
}

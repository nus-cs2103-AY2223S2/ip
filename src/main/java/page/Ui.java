package page;

import page.quest.Quest;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printGreeting() {
        String welcome = "Greetings! 'Tis I, Page, thy medieval assistant.\n" +
                "Type 'help' for the list of available commands.";
        System.out.println(welcome);
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void printQuestLog(QuestLog questLog) {
        System.out.println("Quest Log:\n" + questLog.toString());
    }

    public void printErrorMessage(PageException e) {
        System.out.println(e.getMessage());
    }

    public void printQuestAdded(Quest q) {
        System.out.println("Added to Quest Log:\n" + q.toString());
    }

    public void printQuestCompleted(Quest q) {
        System.out.println("Quest Complete! The bards shall sing of your victory!\n" + q.toString());
    }

    public void printQuestIncompleted(Quest q) {
        System.out.println("Quest Incomplete?? Oh no!\n" + q.toString());
    }

    public void printQuestDeleted(Quest q) {
        System.out.println("Quest Deleted... lost to the ages...\n" + q.toString());
    }

    public void printInvalidInput() {
        String text = "My apologies, I cannot decipher your arcane incantations. " +
                "Type 'help' for the list of commands I can understand.";
        System.out.println(text);
    }

    public void printHelpMessage() {
        String helpText =
                "type 'help' to show this help text!\n" +
                        "type 'todo someTask' to add the task to the Quest Log.\n" +
                        "type 'deadline someDeadline /by someTime' to add a task with deadline someTime.\n" +
                        "type 'event someEvent /from startTime /to endTime' to schedule an event lasting from startTime to endTime.\n" +
                        "type 'log' to show the current Quest Log.\n" +
                        "type 'complete 1' to mark the 1st quest as complete.\n" +
                        "type 'incomplete 2' to mark the 2nd quest as incomplete.\n" +
                        "type 'bye' to exit.";
        System.out.println(helpText);
    }

    public void printBye() {
        System.out.println("Farewell, my liege.");
    }
}

import java.io.File;
import static java.lang.Integer.parseInt;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addItem(Task item) {
        this.list.add(item);
        System.out.println();
        System.out.println(item.messageWhenAdded() + " " + item);
        printSize();
    }

    public void readList() {
        if (this.list.isEmpty()) {
            System.out.println("DukeyList: DukeyList is empty!");
        } else {
            System.out.println("DukeyList:");
            Iterator<Task> it = this.list.iterator();
            it.forEachRemaining(x -> System.out.println((list.indexOf(x) + 1) + ". " + x.toString()));
        }
    }

    public void mark(Scanner scanner) throws DukeyException {
        System.out.print("Task number: ");
        String itemNumberString = scanner.nextLine();
        int itemNumber;
        try {
            itemNumber = parseInt(itemNumberString.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeyException("Error! Invalid task number format!");
        }

        if (itemNumber < 0) {
            throw new DukeyException("Error! Invalid task number");
        } else if (itemNumber >= this.list.size()) {
            throw new DukeyException("Error! DukeyList only contains " + this.list.size() + " tasks");
        }

        Task itemToMark = this.list.get(itemNumber);
        itemToMark.markAsDone();
        System.out.println("DukeyList: Item number " + (itemNumber + 1) + " has been marked as done!");
        System.out.println((itemNumber + 1) + ". " + itemToMark);
    }

    public void unmark(Scanner scanner) throws DukeyException {
        System.out.print("Task number: ");
        String itemNumberString = scanner.nextLine();
        int itemNumber;
        try {
            itemNumber = parseInt(itemNumberString.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeyException("Error! Invalid task number format!");
        }

        if (itemNumber < 0) {
            throw new DukeyException("Error! Invalid task number");
        } else if (itemNumber >= this.list.size()) {
            throw new DukeyException("Error! DukeyList only contains " + this.list.size() + " tasks");
        }
        Task itemToUnmark = this.list.get(itemNumber);
        itemToUnmark.unmark();
        System.out.println("DukeyList: Item number " + (1 + itemNumber) + " has been unmarked!");
        System.out.println((itemNumber + 1) + ". " + itemToUnmark);
    }

    public void delete(Scanner scanner) throws DukeyException {
        System.out.print("Task number: ");
        String itemNumberString = scanner.nextLine();
        int itemNumber;
        try {
            itemNumber = parseInt(itemNumberString.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeyException("Error! Invalid task number format!");
        }

        if (itemNumber < 0) {
            throw new DukeyException("Error! Invalid task number");
        } else if (itemNumber >= this.list.size()) {
            throw new DukeyException("Error! DukeyList only contains " + this.list.size() + " tasks");
        }

        Task itemToRemove = list.get(itemNumber);
        System.out.println("DukeyList: The following item has been removed!");
        System.out.println(itemToRemove.toString());
        list.remove(itemNumber);
        this.printSize();
    }

    public int getSize() {
        return list.size();
    }

    public void printSize() {
        if (getSize() == 1){
            System.out.println("DukeyList now has 1 task.");
        } else {
            System.out.println("DukeyList now has " + getSize() + " tasks.");
        }
    }

    public void clearList() {
        this.list.clear();
        System.out.println("DukeyList cleared! DukeyList is now empty.");
    }

    public void save(File file) {
        DukeyFile.clearFile(file);
        Iterator<Task> it = this.list.iterator();
        it.forEachRemaining(x -> {
            String logString = x.getLogString();
            try {
                DukeyFile.writeToFile(file, logString);
            } catch (IOException e) {
                System.out.println("Error! Unable to save!");
            }
        });
        System.out.println("DukeList saved!");
    }

    public void initiate(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String taskLogString = fileScanner.nextLine();
            String[] logStringArray = taskLogString.split(",");
            if (logStringArray[0].equals("T")) {
                this.list.add(ToDo.createToDoFromLog(logStringArray));
            } else if (logStringArray[0].equals("D")) {
                this.list.add(Deadline.createDeadlineFromLog(logStringArray));
            } else if (logStringArray[0].equals("E")) {
                this.list.add(Event.createEventFromLog(logStringArray));
            }
        }

        if (this.list.isEmpty()) {
            System.out.println("No tasks saved, starting a new list.");
        } else {
            System.out.println("Saved list loaded:");
            this.readList();
        }

    }

    public void clearSave(File file) {
        DukeyFile.deleteFile(file);
        System.out.println("DukeyList save has been cleared.");
    }

    public static void printInstruction() {
        System.out.println("DukeyList: Welcome to DukeyList!! To use DukeyList, type the appropriate command and follow the prompts:");
        System.out.println("To list: 'list'");
        System.out.println("To exit: 'bye'");
        System.out.println("To add a todo: 'todo'");
        System.out.println("To add a deadline: 'deadline'");
        System.out.println("To add an event: 'event'");
        System.out.println("To mark a task as done: 'mark'");
        System.out.println("To unmark a task: 'unmark'");
        System.out.println("To delete a task: 'delete'");
        System.out.println("To clear the list: 'clearList");
        System.out.println("To save the list: 'save'");
        System.out.println("_________________________________________________________");
    }
}

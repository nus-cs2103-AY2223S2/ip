package page;

import java.util.ArrayList;

import page.quest.Deadline;
import page.quest.Event;
import page.quest.Quest;
import page.quest.Todo;

/**
 * Represents the Quest Log: a list of tasks managed by Page.
 */
public class QuestLog {
    /** List of quests stored in the Quest Log */
    private ArrayList<Quest> quests;

    /**
     * Constructs an empty Quest Log.
     */
    public QuestLog() {
        quests = new ArrayList<Quest>();
    }

    /**
     * Constructs a Quest Log containing the given list of quests.
     *
     * @param quests The quests to initialise the Quest Log with.
     */
    public QuestLog(ArrayList<Quest> quests) {
        this.quests = quests;
    }

    /**
     * Returns the number of quests in the Quest Log.
     *
     * @return Number of quests in the Quest Log.
     */
    public int size() {
        return quests.size();
    }

    /**
     * Adds a Todo quest with the given description to the Quest Log.
     * Returns the newly added Todo.
     *
     * @param description Description of the Todo quest.
     * @return The newly added Todo.
     */
    public Todo addTodo(String description) {
        Todo t = new Todo(description);
        quests.add(t);
        return t;
    }

    /**
     * Adds a Deadline quest with the given description and end time to the Quest Log.
     * Returns the newly added Deadline.
     *
     * @param description Description of the Deadline quest.
     * @param by End time of the Deadline.
     * @return The newly added Deadline.
     * @throws PageException If <code>by</code> is not in the HHmm dd/MM/yy format (e.g. 2359 31/12/00).
     */
    public Deadline addDeadline(String description, String by) throws PageException {
        Deadline d = new Deadline(description, by);
        quests.add(d);
        return d;
    }

    /**
     * Adds an Event quest with the given description, start time, and end time to the Quest Log.
     * Returns the newly added Event.
     *
     * @param description Description of the Event quest.
     * @param from Start time of the Event.
     * @param to End time of the Event.
     * @return The newly added Event.
     * @throws PageException If <code>from</code> or <code>to</code>
     *     are not in the HHmm dd/MM/yy format (e.g. 2359 31/12/00).
     */
    public Event addEvent(String description, String from, String to) throws PageException {
        Event e = new Event(description, from, to);
        quests.add(e);
        return e;
    }

    /**
     * Returns the i-th quest in the Quest Log.
     *
     * @param i Index of the desired quest.
     * @return i-th quest in the Quest Log.
     */
    public Quest getQuest(int i) {
        assert i <= quests.size() : "invalid index";
        return quests.get(i - 1);
    }

    /**
     * Deletes the i-th quest in the Quest Log.
     *
     * @param i Index of the quest to be deleted.
     */
    public void deleteQuest(int i) {
        assert i <= quests.size() : "invalid index";
        quests.remove(i - 1);
    }

    /**
     * Marks the i-th quest in the Quest Log as complete.
     *
     * @param i Index of the quest to be marked complete.
     */
    public void completeQuest(int i) {
        assert i <= quests.size() : "invalid index";
        quests.get(i - 1).markComplete();
    }

    /**
     * Marks the i-th quest in the Quest Log as incomplete.
     *
     * @param i Index of the quest to be marked incomplete.
     */
    public void incompleteQuest(int i) {
        assert i <= quests.size() : "invalid index";
        quests.get(i - 1).markIncomplete();
    }

    /**
     * Returns an ArrayList of quests in the Quest Log containing the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return An ArrayList of Quests whose description contains the given keyword.
     */
    public ArrayList<Quest> filterByKeyword(String keyword) {
        ArrayList<Quest> result = new ArrayList<>();
        for (Quest q : quests) {
            if (q.hasKeyword(keyword)) {
                result.add(q);
            }
        }
        return result;
    }

    /**
     * Returns the String representation of every quest in the Quest Log.
     *
     * @return String representation of the Quest Log.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < quests.size(); i++) {
            if (i != 0) {
                result.append("\n");
            }
            Quest q = quests.get(i);
            result.append(i + 1).append(": ").append(q.toString());
        }
        return result.toString();
    }
}

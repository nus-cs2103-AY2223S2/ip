import java.util.ArrayList;

public class QuestLog {
    private ArrayList<Quest> quests;

    public QuestLog() {
        this.quests = new ArrayList<Quest>();
    }

    public QuestLog(ArrayList<Quest> quests) {
        this.quests = quests;
    }

    public int size() {
        return quests.size();
    }

    public Quest getQuest(int i) {
        return quests.get(i - 1);
    }

    public Todo addTodo(String description) {
        Todo t = new Todo(description);
        quests.add(t);
        return t;
    }

    public Deadline addDeadline(String description, String by) throws PageException {
        Deadline d = new Deadline(description, by);
        quests.add(d);
        return d;
    }

    public Event addEvent(String description, String from, String to) throws PageException {
        Event e = new Event(description, from, to);
        quests.add(e);
        return e;
    }

    public void deleteQuest(int i) {
        quests.remove(i - 1);
    }

    public void completeQuest(int i) {
        quests.get(i - 1).markComplete();
    }

    public void incompleteQuest(int i) {
        quests.get(i - 1).markIncomplete();
    }

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

package duke.entities.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

import duke.entities.Version;
import duke.exceptions.DukeException;
import duke.views.UI;

/**
 * Represents the version manager that manages the application versions.
 */
public class VersionManager {
    private final Integer sizeLimit;
    private final Map<UUID, Version> verMap = new HashMap<>();
    private final LinkedList<Version> verControl = new LinkedList<>();


    public VersionManager(Integer sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    /**
     * Pushes the version into the application history.
     *
     * @param state The state to store.
     */
    public void push(Version state) {
        if (verControl.size() == sizeLimit) {
            Version eldest = verControl.removeFirst();
            verMap.remove(eldest.getUuid());
        }
        verControl.push(state);
        verMap.put(state.getUuid(), state);
    }

    /**
     * Removes the latest addition.
     *
     * @return The version that was removed.
     * @throws DukeException The exception thrown when there is an error associated with the removal.
     */
    public Version pop() throws DukeException {
        if (verControl.size() == 0) {
            throw new DukeException("There are no changes to undo!");
        }
        Version state = verControl.pop();
        verMap.remove(state.getUuid());
        return state;
    }

    /**
     * Returns the specified version.
     *
     * @param id The id of the version to checkout to.
     * @return The specified version.
     * @throws DukeException The exception thrown when there is an error associated with the checkout process.
     */
    public Version checkout(String id) throws DukeException {
        int idx = Integer.parseInt(id) - 1;
        if (idx >= 0 && idx < verControl.size()) {
            UUID uuid = (UUID) verMap.keySet().toArray()[idx];
            return verMap.get(uuid);
        } else {
            throw new DukeException("Invalid version number");
        }
    }


    @Override
    public String toString() {
        String initialMsg = "There are no changes made recently!";
        if (verMap.size() > 0) {
            initialMsg = "Here you find the 10 most recent changes that were captured.";
        }
        StringBuilder sb = new StringBuilder(initialMsg);
        ListIterator<Map.Entry<UUID, Version>> it = new ArrayList<>(verMap.entrySet()).listIterator();
        it.forEachRemaining((entry) -> {
            sb.append(UI.indentMessage(String.valueOf(it.nextIndex() + 1)))
                    .append(". ").append(entry.getKey().toString(), 0, 10).append("...")
                    .append(": ").append(entry.getValue());
            it.next();
        });
        return sb.toString();
    }
}

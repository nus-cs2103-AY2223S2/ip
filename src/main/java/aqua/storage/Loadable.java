package aqua.storage;

import aqua.exception.LoadException;


/** Represents an object whose state can be loaded. */
public interface Loadable {
    /**
     * Loads the the state of this object from a data stored in hard disk.
     *
     * @throws LoadException if an error occured while loading.
     */
    public void load() throws LoadException;
}

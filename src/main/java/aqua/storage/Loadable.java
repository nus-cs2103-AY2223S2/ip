package aqua.storage;

import aqua.exception.LoadException;


public interface Loadable {
    public void load() throws LoadException;
}

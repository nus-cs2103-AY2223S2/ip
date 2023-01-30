package domain.entities.core;

import core.exceptions.DisposableException;

/**
 * The interface for objects that can be disposed of. Dispose means cleaning
 * up the resources that the object uses. Although a bit hacky, it can also
 * be used to persist the state of the object.
 */
public interface Disposable {
    /**
     * Dispose of the object.
     */
    void dispose() throws DisposableException;
}

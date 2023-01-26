package domain.entities.core;

import core.exceptions.DisposableException;

public interface Disposable {
    /**
     * Dispose of the object.
     */
    public void dispose() throws DisposableException;
}

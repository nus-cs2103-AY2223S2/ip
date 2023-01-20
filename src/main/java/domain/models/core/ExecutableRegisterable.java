package domain.models.core;

/**
 * The interface that can registers its executables to the event loop.
 */
public interface ExecutableRegisterable {
    /**
     * Registers the executables related to this interface to the nestable.
     * @param nestable the root executable to which the executable of this
     *                 interface shall be registered.
     */
    void register(NestableExecutableObject nestable);
}

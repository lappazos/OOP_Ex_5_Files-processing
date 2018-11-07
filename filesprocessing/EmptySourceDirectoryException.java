package filesprocessing;

/**
 * Exception indicates there are no files in the directory
 */
class EmptySourceDirectoryException extends InvalidUsageException {

    private static final long serialVersionUID = 1L;
    private static final String EMPTY_SOURCE_DIR_ERROR = "No files in sourcedir";

    /**
     * default constructor
     */
    EmptySourceDirectoryException() {
        super(EMPTY_SOURCE_DIR_ERROR);
    }

    /**
     * constructor - is here for modularity purposes in case someone would like to override the default message
     *
     * @param message message to be sent to the the exception
     */
    EmptySourceDirectoryException(String message) {
        super(message);
    }
}

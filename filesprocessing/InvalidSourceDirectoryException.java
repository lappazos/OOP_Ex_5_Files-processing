package filesprocessing;

/**
 * Exception indicates the directory path is invalid
 */
class InvalidSourceDirectoryException extends InvalidUsageException {

    private static final long serialVersionUID = 1L;
    private static final String INVALID_SOURCE_DIR = "Invalid Source directory";

    /**
     * default constructor
     */
    InvalidSourceDirectoryException() {
        super(INVALID_SOURCE_DIR);
    }

    /**
     * constructor - is here for modularity purposes in case someone would like to override the default message
     *
     * @param message message to be sent to the the exception
     */
    InvalidSourceDirectoryException(String message) {
        super(message);
    }
}

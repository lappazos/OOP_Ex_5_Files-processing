package filesprocessing;

/**
 * Exception indicates the command file path is not a real file
 */
class InvalidCommandFileException extends InvalidUsageException {

    private static final long serialVersionUID = 1L;
    private static final String INVALID_COMMAND_ERROR = "Invalid Command File - Not a file";

    /**
     * Default constructor
     */
    InvalidCommandFileException() {
        super(INVALID_COMMAND_ERROR);
    }

    /**
     * constructor - is here for modularity purposes in case someone would like to override the default message
     *
     * @param message message to be sent to the the exception
     */
    InvalidCommandFileException(String message) {
        super(message);
    }
}

package filesprocessing.commandfile;

/**
 * Exception indicates the command file structure is broken
 */
class BadFormatException extends CommandFileException {

    private static final long serialVersionUID = 1L;
    private final static String BAD_FORMAT_ERROR = "Bad format of Commands File";

    /**
     * Default Constructor
     */
    BadFormatException() {
        super(BAD_FORMAT_ERROR);
    }

    /**
     * Constructor - is here for modularity purposes in case someone would like to override the default message
     *
     * @param message message to be sent to the the exception
     */
    BadFormatException(String message) {
        super(message);
    }
}

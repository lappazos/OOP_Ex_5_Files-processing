package filesprocessing;

/**
 * Exception indicates wrong number of args to the console
 */
class InvalidNumberOfArgsException extends InvalidUsageException {

    private static final long serialVersionUID = 1L;
    private static final String INVALID_NUM_OF_ARGS_ERROR = "Wrong usage. Should receive 2 arguments";

    /**
     * default constructor
     */
    InvalidNumberOfArgsException() {
        super(INVALID_NUM_OF_ARGS_ERROR);
    }


    /**
     * constructor - is here for modularity purposes in case someone would like to override the default message
     *
     * @param message message to be sent to the the exception
     */
    InvalidNumberOfArgsException(String message) {
        super(message);
    }
}

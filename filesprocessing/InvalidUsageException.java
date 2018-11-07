package filesprocessing;

/**
 * abstract exception represents Type II errors, that the run cant proceed with them
 */
abstract class InvalidUsageException extends Exception {

    /**
     * constructor - is here for modularity purposes in case someone would like to override the default message
     *
     * @param errorMessage message to be sent to the the exception
     */
    InvalidUsageException(String errorMessage) {
        super(errorMessage);
    }
}

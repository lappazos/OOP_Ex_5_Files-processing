package filesprocessing.sectionhandler.orders;

import filesprocessing.sectionhandler.InvalidSubSectionLineException;

/**
 * Factory singleton for creating orders
 */
public class OrderFactory {

    /**
     * the singleton instance
     */
    private static final OrderFactory instance = new OrderFactory();

    private static final String ABS = "abs";
    private static final String TYPE = "type";
    private static final String SIZE = "size";

    private static final String REVERSE = "REVERSE";

    private static final String SEPARATION_KEY = "#";
    private static final int DEFAULT_ORDER_ARGS_LENGTH = 1;
    private static final int ORDER_ARGS_PLUS_REVERSE_LENGTH = 2;

    /**
     * @return the default order
     */
    public Order getDefault() {
        return DEFAULT_ORDER;
    }

    /**
     * the default order abs
     */
    private static final Order DEFAULT_ORDER = new AbsOrder();

    /**
     * singleton private constructor
     */
    private OrderFactory() {
    }

    /**
     * @return singleton instance
     */
    public static OrderFactory getInstance() {
        return instance;
    }

    /**
     * The factory method
     *
     * @param input the line of the order sub-section
     * @return the created order
     * @throws InvalidSubSectionLineException case - indicates wrong parameters per sub-section
     */
    public Order createOrder(String input) throws InvalidSubSectionLineException {

        String[] parameters = input.split(SEPARATION_KEY);
        validateOrderInput(parameters);

        //we can assume that every index we'll approach in the parameters list will be exist, according
        //to the filter type
        Order order = orderChooser(parameters[0]);

        if (parameters[parameters.length - 1].equals(REVERSE)) {
            return new ReverseOrder(order);
        }

        return order;
    }

    /**
     * choose the correct order
     *
     * @param orderName the desired order
     * @return the created order
     * @throws InvalidSubSectionLineException case - indicates wrong parameters per sub-section
     */
    private Order orderChooser(String orderName) throws InvalidSubSectionLineException {

        switch (orderName) {
            case ABS:
                return DEFAULT_ORDER;

            case TYPE:
                return new TypeOrder(DEFAULT_ORDER);

            case SIZE:
                return new SizeOrder(DEFAULT_ORDER);

            default:
                throw new InvalidSubSectionLineException();
        }
    }

    /**
     * check if the number of args is correct
     *
     * @param parameters the args
     * @throws InvalidSubSectionLineException case - indicates wrong parameters per sub-section
     */
    private void validateOrderInput(String[] parameters) throws InvalidSubSectionLineException {

        if (parameters.length > DEFAULT_ORDER_ARGS_LENGTH) {
            if (parameters.length > ORDER_ARGS_PLUS_REVERSE_LENGTH) {
                throw new InvalidSubSectionLineException();
            }
            if (!parameters[1].equals(REVERSE)) {
                throw new InvalidSubSectionLineException();
            }
        }
    }

}

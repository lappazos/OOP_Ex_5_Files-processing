package filesprocessing.sectionhandler.orders;

import java.io.File;

/**
 * Order class of type type - sort the files according their extension
 */
public class TypeOrder extends OrderDecorator {

    private static final String PERIOD = ".";
    private static final String EMPTY_STRING = "";

    /**
     * Constructor
     *
     * @param decoratedOrder the decorated order
     */
    TypeOrder(Order decoratedOrder) {
        super(decoratedOrder);
    }

    @Override
    protected int compareFunction(File f1, File f2) {
        return getExtension(f1).compareTo(getExtension(f2));
    }

    /**
     * return the extension of a file
     *
     * @param file file to check
     * @return the extension/type
     */
    private String getExtension(File file) {

        int lastPeriodIndex = file.getName().lastIndexOf(PERIOD);

        if (lastPeriodIndex == -1) {
            return EMPTY_STRING;
        }

        return file.getName().substring(lastPeriodIndex + 1);
    }

}

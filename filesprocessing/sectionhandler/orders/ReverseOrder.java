package filesprocessing.sectionhandler.orders;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Order class of type reverse - return the opposite of the decorated order
 */
public class ReverseOrder extends OrderDecorator {

    /**
     * Constructor
     *
     * @param decoratedOrder the decorated order
     */
    ReverseOrder(Order decoratedOrder) {
        super(decoratedOrder);
    }

    /**
     * perform sort according to the specific order
     *
     * @param files files list to sort
     */
    public void orderFiles(List<File> files) {
        decoratedOrder.orderFiles(files);
        Collections.reverse(files);
    }

    @Override
    protected int compareFunction(File f1, File f2) {
        //will never get here
        return -1;
    }
}

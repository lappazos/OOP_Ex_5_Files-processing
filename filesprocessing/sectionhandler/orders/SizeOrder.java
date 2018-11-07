package filesprocessing.sectionhandler.orders;

import java.io.File;

/**
 * Order class of type size - sort the files according their size
 */
public class SizeOrder extends OrderDecorator {

    /**
     * Constructor
     *
     * @param decoratedOrder the decorated order
     */
    SizeOrder(Order decoratedOrder) {
        super(decoratedOrder);
    }

    @Override
    protected int compareFunction(File f1, File f2) {
        return Long.compare(f1.length(), f2.length());
    }
}

package filesprocessing.sectionhandler.orders;

import java.io.File;
import java.util.List;

/**
 * abstract order decorator class
 */
public abstract class OrderDecorator extends Order {

    //the decorated order
    Order decoratedOrder;

    /**
     * Constructor
     *
     * @param decoratedOrder the decorated order
     */
    OrderDecorator(Order decoratedOrder) {
        this.decoratedOrder = decoratedOrder;
    }

    /**
     * perform sort according to the specific order
     *
     * @param files files list to sort
     */
    public void orderFiles(List<File> files) {
        decoratedOrder.orderFiles(files);
        super.orderFiles(files);
    }
}

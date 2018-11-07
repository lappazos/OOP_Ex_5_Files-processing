package filesprocessing.sectionhandler.orders;

import java.io.File;
import java.util.List;

/**
 * abstract class Type Order
 */
public abstract class Order {

    /**
     * perform sort according to the specific order
     *
     * @param files files list to sort
     */
    public void orderFiles(List<File> files) {
        files.sort(this::compareFunction);
    }

    /**
     * the comparison order method
     *
     * @param f1 file 1 to compare
     * @param f2 file 2 to compare
     * @return comparison factor
     */
    protected abstract int compareFunction(File f1, File f2);
}

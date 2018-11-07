package filesprocessing.sectionhandler.orders;

import java.io.File;

/**
 * the default order by abc
 */
public class AbsOrder extends Order {

    @Override
    protected int compareFunction(File f1, File f2) {
        return f1.getName().compareTo(f2.getName());
    }
}

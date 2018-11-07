package filesprocessing.sectionhandler.filters;

import java.io.File;

/**
 * Filter class - default filter
 */
public class AllFilter implements Filter {


    @Override
    public boolean filter(File file) {
        return true;
    }
}

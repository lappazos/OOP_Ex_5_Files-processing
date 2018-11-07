package filesprocessing.sectionhandler.filters;

import java.io.File;

/**
 * Filter Interface
 */
public interface Filter {

    /**
     * boolean method which determines whether a certain file has passed the filtetr or not
     *
     * @param file the file to check
     * @return Boolean - passed or not
     */
    boolean filter(File file);
}

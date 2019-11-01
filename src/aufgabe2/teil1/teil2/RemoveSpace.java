package aufgabe2.teil1.teil2;

@FunctionalInterface
public interface RemoveSpace {
    /**
     * remove space at beginning and end if exist
     * @param element
     * @return
     */
    String eliminateSpaces(String element);
}

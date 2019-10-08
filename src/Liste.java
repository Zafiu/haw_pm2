public interface Liste<T> {
    /**
     * fuege ein neues Element hinzu
     * @param value
     */
    public void hinzufuegen(T value);

    /**
     * gibt den key zurueck
     * @param value
     * @return
     */
    public int gibIndex(T value);

    /**
     * fuege an der Stelle i den neuen Wert ein
     * @param i
     * @param value
     * @throws Exception
     */
    public void einfuegen(int i, T value) throws Exception;

    /**
     * gib Element an der Stelle i zurueck
     * @param i
     * @return
     * @throws Exception
     */
    public T gibElement(int i) throws Exception;

    /**
     * Anzahl der gesamten Elemente
     * @return
     */
    public int gibAnzahlElemente();

    /**
     * Rueckgabe des kleinsten Elementes
     * @return
     */
    public T gibKleinstesElement();

    /**
     * Liste leeren
     */
    public void leeren();

    /**
     * entferne Element an der Stelle i
     * @param i
     * @throws Exception
     */
    public void entferne(int i) throws Exception;

    /**
     * entferne Elemente an der Stelle (T) value
     * @param value
     */
    public void entferne(T value);
}
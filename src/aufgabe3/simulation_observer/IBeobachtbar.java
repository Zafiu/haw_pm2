package simulation_observer;

public interface IBeobachtbar {
    /**
     * observer anmelden
     * @param beobachter
     */
    public void anmelden (IBeobachter beobachter);

    /**
     * observer abmelden
     * @param beobachter
     */
    public void abmelden (IBeobachter beobachter);

    /**
     * aktuellen Zustand zurückgeben
     * @return Zug[]
     */
    public Zug[] gleisZustand();
}

import simulation_observer.Zug;

public class Bahnhof {
    public Zug[] get_gleisen() {
        return _gleisen;
    }

    private Zug[] _gleisen;
    private int _anzahlGleisen;

    public int get_anzahlGleisen() {
        return _anzahlGleisen;
    }

    public Bahnhof() {
        _anzahlGleisen = 3;
        _gleisen = new Zug[_anzahlGleisen];
    }

    public synchronized void zugEinfahren(Zug zug, int gleisNummer) {
        while (!this._checkGleisFrei(gleisNummer)) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("GLEIS: " + gleisNummer);
        _gleisen[gleisNummer] = zug;
        this.notifyAll();
    }

    public synchronized void zugAusfahren(int gleisNummer) {
        while (this._checkGleisFrei(gleisNummer)) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("GLEIS: " + gleisNummer);
        Zug zug = _gleisen[gleisNummer];
        _gleisen[gleisNummer] = null;
    }


    /**
     * ueberpruefe ob Gleis belegt ist
     * @param gleisNummer int
     * @return boolean
     */
    private boolean _checkGleisFrei(int gleisNummer) {

        try {
            if (gleisNummer > _anzahlGleisen) {
                throw new Exception("Fehler");
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return _gleisen[gleisNummer] == null;
    }
}

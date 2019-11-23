import simulation_observer.Status;
import simulation_observer.Zug;

public class Lokfuehrer extends Thread {
    private Status _rolle;
    private Bahnhof _bahnnhof;
    private int _gleisNummer;

    public Lokfuehrer(Bahnhof bahnhof, int gleisAnzahl) {
        this._bahnnhof = bahnhof;
        this.set_gleisNummerRandom(gleisAnzahl);
        this.set_rolleRandom();
    }

    /**
     * setze zufaellige Rolle (Zug ausfahren oder einfahren)
     */
    private void set_rolleRandom() {
        int range = (2 - 1) + 1;
        int randomValue = (int) (Math.random() * range) + 1;
        switch (randomValue) {
            case 2:
                _rolle = Status.AUSPARKEN;
                break;
            case 1:
                _rolle = Status.EINPARKEN;
                break;
        }
    }

    /**
     * setze zufaellige Gleisnummer
     * @param gleisAnzahl int
     */
    private void set_gleisNummerRandom(int gleisAnzahl) {
        int range = (gleisAnzahl) + 1;
        _gleisNummer = (int) (Math.random() * range);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            if (_rolle == Status.AUSPARKEN) {
                _bahnnhof.zugAusfahren(_gleisNummer);
                System.out.println("ZUG AUSGEFAHREN");
            } else {
                Zug zug = new Zug();
                _bahnnhof.zugEinfahren(zug, _gleisNummer);
                System.out.println("ZUG EINGEFAHREN");
            }
            this.interrupt();
        }
    }
}

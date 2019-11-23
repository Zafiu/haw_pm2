import simulation_observer.IBeobachtbar;
import simulation_observer.IBeobachter;
import simulation_observer.Zug;

import java.util.ArrayList;

public class Simulation implements Runnable, IBeobachtbar {
    private ArrayList<IBeobachter>  _beobachters;
    private Bahnhof _bahnhof;

    public Simulation() {
        _beobachters = new ArrayList<>();
        _bahnhof = new Bahnhof();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(2000);
                Lokfuehrer lokfuehrer = new Lokfuehrer(_bahnhof, _bahnhof.get_anzahlGleisen() - 1);
                lokfuehrer.start();
                this._updaten();
            }  catch (InterruptedException e) {
                System.err.println("Thread wurde durch Interrupt geweckt!");
                Thread.currentThread().interrupt();
            }
        }
    }

    public void anmelden (IBeobachter beobachter) {
        _beobachters.add(beobachter);
    }

    public void abmelden (IBeobachter beobachter) {
        _beobachters.remove(beobachter);
    }

    public Zug[] gleisZustand() {
        return _bahnhof.get_gleisen();
    }

    private void _updaten() {
        for (int x = 0; x <= _beobachters.size() - 1; x++) {
            _beobachters.get(x).aktualisieren(this);
        }
    }
}

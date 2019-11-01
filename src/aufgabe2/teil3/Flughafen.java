package aufgabe2.teil3;

import java.util.ArrayList;
import java.util.List;

public class Flughafen extends Thread {
    private int _anazahlFlugzeuge;
    private List<Flugzeug> _flugzeuge;

    public Flughafen(int anzahl) {
        _flugzeuge = new ArrayList<>();
        _anazahlFlugzeuge = 0;

        for (int i = 0; i <= anzahl; i++) {
            Flugzeug flugzeug = this.erzeugeFlugzeug( 0);
            flugzeug.start();
            _flugzeuge.add(flugzeug);
        }
    }

    private void landen(Flugzeug flugzeug) {
        flugzeug.interrupt();
    }

    @Override
    public void run() {
        int zeit = 0;
        while (!isInterrupted()) {
            try {
                Thread.sleep((long) 0.5);
            } catch (InterruptedException e) {
                System.err.println("Thread wurde durch Interrupt geweckt!");
                interrupt();
            }

            zeit++;
            for (int i = 0; i < _anazahlFlugzeuge; i++) {
                Flugzeug flugzeug = _flugzeuge.get(i);
                if(flugzeug.get_status() != Status.GELANDET) {
                    flugzeug.setZeit(zeit);
                } else if(flugzeug.get_status() == Status.GELANDET && !flugzeug.isInterrupted()) {
                    this.landen(flugzeug);
                    if (flugzeug.isInterrupted()) {
                        Flugzeug neuFlugzeug = this.erzeugeFlugzeug(zeit);
                        neuFlugzeug.start();
                        _flugzeuge.add(neuFlugzeug);
                    }
                }
            }
        }
    }


    private Flugzeug erzeugeFlugzeug(int zeit) {
        String flightNr= "flight_nr" + _anazahlFlugzeuge;
        Flugzeug flugzeug = new Flugzeug(flightNr,zeit,this);
        _anazahlFlugzeuge++;
        return flugzeug;
    }

    public static void main(String[] args) {
        Flughafen flughafen = new Flughafen(10);
        flughafen.start();
    }

}

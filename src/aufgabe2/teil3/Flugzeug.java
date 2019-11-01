package aufgabe2.teil3;

public class Flugzeug extends Thread {
    private Flughafen _flughafen;
    private String _id;
    private int _flugdauer;
    private int _startzeit;
    private Status _status;
    private double _landezeit;
    public int zeit;

    public Flugzeug(String id, int startzeit, Flughafen flughafen) {
        int range = (8 - 3) + 1;
        _flugdauer =(int)(Math.random() * range) + 3;
        _id = id;
        _startzeit = startzeit;
        _flughafen = flughafen;
        _status = Status.IM_FLUG;
        _landezeit = 1.5;
        this.setZeit(startzeit);
    }

    public Status get_status() {
        return _status;
    }


    public int getZeit() {
        return zeit;
    }

    public void setZeit(int zeit) {
        this.zeit = zeit;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            if (_status != Status.IM_LANDEANFLUG && ((_startzeit + _flugdauer) - this.getZeit()) <= 0) {

                _status = Status.IM_LANDEANFLUG;
            } else if (_status == Status.IM_LANDEANFLUG && ((_startzeit + _flugdauer + _landezeit) - this.getZeit()) <= 0) {
                _status = Status.GELANDET;
            }
        }

        System.out.println("Flugzeug " + _id + " ist gelandet!");
    }
}

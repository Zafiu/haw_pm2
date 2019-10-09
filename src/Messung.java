import java.time.LocalDateTime;

public class Messung implements Comparable<Messung> {
    private LocalDateTime _date;

    public LocalDateTime get_date() {
        return _date;
    }

    public int compareTo(Messung measure) {
        if(measure.get_wert() > this.get_wert()) {
            return 1;
        }

        return 0;
    }

    public void set_date(LocalDateTime _date) {
        this._date = _date;
    }

    public Double get_wert() {
        return _wert;
    }

    public void set_wert(Double _wert) {
        this._wert = _wert;
    }

    private Double _wert;
}

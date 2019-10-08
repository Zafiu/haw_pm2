import java.time.LocalDateTime;

public class Messung {
    private LocalDateTime _date;

    public LocalDateTime get_date() {
        return _date;
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

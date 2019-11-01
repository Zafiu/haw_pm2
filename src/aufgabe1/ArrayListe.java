package aufgabe1;

import java.util.Arrays;

public class ArrayListe<T extends Comparable> implements Liste<T> {
    private Object[] _elemente;
    private int _anzahlElemente;

    public ArrayListe (){
        _anzahlElemente = 0;
        this._elemente = new Object[_anzahlElemente];
    }

    public void hinzufuegen(T value) {
        this._anzahlElemente += 1;
        this._elemente = Arrays.copyOf(this._elemente, this._anzahlElemente);
        this._elemente[this._anzahlElemente - 1] = value;
    }

    public void einfuegen(int i, T value) throws Exception {
        if(i < 0 || i >= _anzahlElemente) {
            throw new Exception("Anzahl ist größer oder kleiner als die Anzahl der Elemente.");
        }

        this._elemente[i] = value;
        _anzahlElemente += 1;
    }

    public int gibIndex(T value) {
        for (int i = 0; i <= this._anzahlElemente -1; i++) {
            if(this._elemente[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    public T gibElement(int i) throws Exception {
        if(i < 0 || i > _anzahlElemente - 1) {
            throw new Exception("Anzahl ist größer oder kleiner als die Anzahl der Elemente.");
        }

        return (T) this._elemente[i];
    }

    public int gibAnzahlElemente() {
        return this._anzahlElemente;
    }

    public T gibKleinstesElement() {
        T shortestElement = null;

        for (int x = 0; x <= _anzahlElemente -1; x++) {
            T element =  (T) this._elemente[x];

            if(shortestElement == null || element.compareTo(shortestElement) == 1) {
                shortestElement = element;
            }
        }

        return shortestElement;
    }

    public void leeren() {
        _anzahlElemente = 0;
        this._elemente =  new Object[_anzahlElemente];
    }

    public void entferne(int i) throws Exception {
        if(i < 0 || i > _anzahlElemente -1) {
            System.out.println(i);
            throw new Exception("Anzahl ist größer oder kleiner als die Anzahl der Elemente.");
        }

        Object[] temp = new Object[_anzahlElemente - 1];
        for (int x = 0; x <= _anzahlElemente -1; x++) {
            if(x == i) {
                _anzahlElemente -= 1;
                continue;
            }

            temp[x] = this._elemente[x];
        }

        this._elemente = temp;
    }

    public void entferne(T value) {
        Object[] temp = new Object[_anzahlElemente - 1];
        for (int x = 0; x <= _anzahlElemente -1; x++) {
            if(this._elemente[x].equals(value)) {
                _anzahlElemente -= 1;
                continue;
            }

            temp[x] = this._elemente[x];
        }

        this._elemente = temp;
    }
}

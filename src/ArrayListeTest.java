import org.junit.Assert;
import org.junit.Test;

public class ArrayListeTest {

    @Test
    public void testHinzufuegen() {
        ArrayListe<String> liste = new ArrayListe<>();
        liste.hinzufuegen("abc0");
        liste.hinzufuegen("abc1");
        liste.hinzufuegen("abc2");

        try {
            String element = liste.gibElement(2);
            Assert.assertEquals( "abc2", element );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetIndex() {
        ArrayListe<String> liste = new ArrayListe<>();
        liste.hinzufuegen("abc0");
        liste.hinzufuegen("abc1");
        liste.hinzufuegen("abc2");

        try {
            int elementIndex = liste.gibIndex("abc2");
            Assert.assertEquals( 2, elementIndex );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEinfuegen() {
        ArrayListe<String> liste = new ArrayListe<>();
        liste.hinzufuegen("abc0");
        liste.hinzufuegen("abc1");
        liste.hinzufuegen("abc2");

        try {
            liste.einfuegen(2,"tetris");
            String element = liste.gibElement(2);
            Assert.assertEquals( "tetris", element );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGibElement() {
        ArrayListe<String> liste = new ArrayListe<>();
        liste.hinzufuegen("abc0");
        liste.hinzufuegen("abc1");
        liste.hinzufuegen("abc2");

        try {
            String element = liste.gibElement(2);
            Assert.assertEquals( "abc2", element );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAnzahllemente() {
        ArrayListe<String> liste = new ArrayListe<>();
        liste.hinzufuegen("abc0");
        liste.hinzufuegen("abc1");
        liste.hinzufuegen("abc2");

        try {
            liste.entferne(0);
            Assert.assertEquals( 2, liste.gibAnzahlElemente() );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLeereListe() {
        ArrayListe<String> liste = new ArrayListe<>();
        liste.hinzufuegen("abc0");
        liste.hinzufuegen("abc1");
        liste.hinzufuegen("abc2");

        try {
            Assert.assertEquals( 3, liste.gibAnzahlElemente() );
            liste.leeren();
            Assert.assertEquals( 0, liste.gibAnzahlElemente() );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEntferneElement() {
        ArrayListe<String> liste = new ArrayListe<>();
        liste.hinzufuegen("abc0");
        liste.hinzufuegen("abc1");
        liste.hinzufuegen("abc2");

        try {
            Assert.assertEquals( 3, liste.gibAnzahlElemente() );
            liste.entferne(2);
            Assert.assertEquals( 2, liste.gibAnzahlElemente() );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEntferneElementValue() {
        ArrayListe<String> liste = new ArrayListe<>();
        liste.hinzufuegen("abc0");
        liste.hinzufuegen("abc1");
        liste.hinzufuegen("abc2");

        try {
            Assert.assertEquals( 3, liste.gibAnzahlElemente() );
            liste.entferne("abc2");
            Assert.assertEquals( 2, liste.gibAnzahlElemente() );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
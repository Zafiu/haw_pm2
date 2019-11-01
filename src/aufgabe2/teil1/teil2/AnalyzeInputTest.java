package aufgabe2.teil1.teil2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AnalyzeInputTest {
    private String [] liste;

    @Before
    public void init() {
        this.liste = new String[]{" Eingabe ", "Äußeres ", null, "Strassen-Feger", " ein Haus", null };
    }

    @Test
    public void removeUmlaute() {
        AnalyzeInput analyzeInput = new AnalyzeInput();
        List list = analyzeInput.transform(this.liste);

        Assert.assertEquals( "AEUSSERE", list.get(1) );
    }

    @Test
    public void removeSpaceBeginAndEnd() {
        AnalyzeInput analyzeInput = new AnalyzeInput();
        List list = analyzeInput.transform(this.liste);

        Assert.assertEquals( "EINGABE", list.get(0) );
    }

    @Test
    public void removeNullElements() {
        AnalyzeInput analyzeInput = new AnalyzeInput();
        List list = analyzeInput.transform(this.liste);

        Assert.assertEquals( 3, list.size() - 1 );
    }

    @Test
    public void limitStringSize() {
        AnalyzeInput analyzeInput = new AnalyzeInput();
        List list = analyzeInput.transform(this.liste);
        String element = (String) list.get(2);
        Assert.assertEquals( 8, element.length() );
    }

    @Test
    public void failLimitSize() {
        AnalyzeInput analyzeInput = new AnalyzeInput();
        List list = analyzeInput.transform(this.liste);
        String element = (String) list.get(2);
        Assert.assertNotEquals( "Strassen-Feger", element );
    }
}

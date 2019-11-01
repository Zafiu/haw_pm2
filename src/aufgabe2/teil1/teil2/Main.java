package aufgabe2.teil1.teil2;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        String [] myArray = new String[]{"Eingabe ", "Äußeres ", null, "Strassen-Feger", " ein Haus" };

/*        Scanner s = new Scanner(System.in);
        System.out.println("Enter the length of the array:");
        int length = s.nextInt();
        String [] myArray = new String[length];
        System.out.println("Enter the elements of the array:");

        for(int i=0; i<length; i++ ) {
            myArray[i] = s.next();
        }*/

        AnalyzeInput analyzeInput = new AnalyzeInput();
        List liste = analyzeInput.transform(myArray);

        for(int i = 0; i <= liste.size() - 1; i++) {
            System.out.println(liste.get(i));
        }

    }


}
package aufgabe2.teil1.teil2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class AnalyzeInput {

    public List transform(String[] inputs) {
        RemoveNull removenull = Objects::nonNull;
        RemoveSpace removeSpace = element -> {
            if(element.charAt(0) == ' ') {
                element = element.substring(1);
            }

            if(element.charAt(element.length() - 1) == ' ') {
                element = element.substring(0, element.length() - 1);
            }

            return element;
        };
        TransformUpper transformUpper = String::toUpperCase;
        ChangeUmlaut changeUmlaut = (element -> {
            for(int i = 0; i <= element.length() - 1; i++) {
                switch(element.charAt(i)){
                    case 'Ä':
                        element = element.substring(0,i) + "AE" + element.substring(i + 1);
                        break;
                    case 'Ö':
                        element = element.substring(0,i) + "OE" + element.substring(i + 1);
                        break;
                    case 'Ü':
                        element = element.substring(0,i) + "UE" + element.substring(i + 1);
                        break;
                    case 'ß':
                        element = element.substring(0,i) + "SS" + element.substring(i + 1);
                        break;
                }
            }

            return element;
        });
        ShortLength shortLength = element -> {
            return element.length() - 1 >= 8 ?  element.substring(0,8) : element;
        };


        List<String> elementList = new ArrayList<>(Arrays.asList(inputs));
        Stream elementsStream = elementList.stream()
                .filter(removenull::removeNulls)
                .map(removeSpace::eliminateSpaces)
                .map(transformUpper::transformToUpper)
                .map(changeUmlaut::eliminateUmlauts)
                .map(shortLength::shortLength);

        return  new ArrayList(Arrays.asList(elementsStream.toArray()));
    }
}

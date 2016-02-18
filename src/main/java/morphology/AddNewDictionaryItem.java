package morphology;

import zemberek.core.turkish.PrimaryPos;
import zemberek.core.turkish.SecondaryPos;
import zemberek.morphology.lexicon.DictionaryItem;
import zemberek.morphology.parser.MorphParse;
import zemberek.morphology.parser.tr.TurkishWordParserGenerator;

import java.io.IOException;
import java.util.List;


public class AddNewDictionaryItem {

    public static void main(String[] args) throws IOException {
        TurkishWordParserGenerator parserGenerator = TurkishWordParserGenerator.createWithDefaults();
        String input = "tweetleyeyazdım";
        List<MorphParse> before = parserGenerator.parse(input);
        System.out.println("Parses for " + input + " before adding lemma `tweetlemek` = " + before);
        DictionaryItem item =
                new DictionaryItem("tweetlemek", "tweetle", "tivitle", PrimaryPos.Verb, SecondaryPos.None);
        parserGenerator.getGraph().addDictionaryItem(item);
        parserGenerator.invalidateCache(input);
        List<MorphParse> after = parserGenerator.parse(input);
        System.out.println("Parses for " + input + " after adding lemma `tweetlemek` = " + after);
    }
}

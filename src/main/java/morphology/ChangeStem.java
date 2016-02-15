package morphology;

import zemberek.morphology.apps.TurkishWordParserGenerator;
import zemberek.morphology.lexicon.DictionaryItem;
import zemberek.morphology.parser.MorphParse;

import java.io.IOException;
import java.util.List;

public class ChangeStem {

    TurkishWordParserGenerator parserGenerator;

    public ChangeStem(TurkishWordParserGenerator parserGenerator) {
        this.parserGenerator = parserGenerator;
    }

    public void regenerate(String word, DictionaryItem lemma) {
        System.out.println("Word = " + word);
        List<MorphParse> parses = parserGenerator.parse(word);
        for (MorphParse parse : parses) {
            String[] generated = parserGenerator.getGenerator().generate(lemma, parse.getSuffixes());
            for (String s : generated) {
                System.out.println("Generated for " + parse.formatLong() + " with item " + lemma + " = " + s);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        TurkishWordParserGenerator parserGenerator = TurkishWordParserGenerator.createWithDefaults();
        DictionaryItem yummy = parserGenerator.getLexicon().getMatchingItems("poğaça").get(0);
        new ChangeStem(parserGenerator).regenerate("simidime", yummy);
    }

}

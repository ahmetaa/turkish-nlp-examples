package morphology;

import zemberek.morphology.apps.TurkishWordParserGenerator;
import zemberek.morphology.parser.MorphParse;

import java.io.IOException;
import java.util.List;

public class StemmingAndLemmatization {
    TurkishWordParserGenerator parser;

    public StemmingAndLemmatization(TurkishWordParserGenerator parser) {
        this.parser = parser;
    }

    public void parse(String word) {
        System.out.println("Word = " + word);

        System.out.println("Parses: ");
        List<MorphParse> parses = parser.parse(word);
        for (MorphParse parse : parses) {
            System.out.println(parse.formatLong());
            System.out.println("\tStems = " + parse.getStems());
            System.out.println("\tLemmas = " + parse.getLemmas());
        }
    }

    public static void main(String[] args) throws IOException {
        TurkishWordParserGenerator parser = TurkishWordParserGenerator.createWithDefaults();
        new StemmingAndLemmatization(parser).parse("kitabımızsa");
    }
}

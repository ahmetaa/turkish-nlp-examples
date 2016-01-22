package morphology;

import zemberek.morphology.apps.TurkishWordParserGenerator;
import zemberek.morphology.parser.MorphParse;

import java.io.IOException;
import java.util.List;

public class ParseWords {

    TurkishWordParserGenerator parser;

    public ParseWords(TurkishWordParserGenerator parser) {
        this.parser = parser;
    }

    public void parse(String word) {
        System.out.println("Word = " + word);
        List<MorphParse> parses = parser.parseCached(word);
        for (MorphParse parse : parses) {
            System.out.println(parse.formatLong());
            System.out.println(parse.formatNoEmpty());
            System.out.println(parse.formatOflazer());
            System.out.println(parse.formatOnlyIgs());
        }
    }

    public static void main(String[] args) throws IOException {
        TurkishWordParserGenerator parser = TurkishWordParserGenerator.createWithDefaults();
        new ParseWords(parser).parse("kalemi");
    }

}

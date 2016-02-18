package morphology;

import zemberek.morphology.ambiguity.Z3MarkovModelDisambiguator;
import zemberek.morphology.parser.MorphParse;
import zemberek.morphology.parser.SentenceMorphParse;
import zemberek.morphology.parser.tr.TurkishSentenceParser;
import zemberek.morphology.parser.tr.TurkishWordParserGenerator;

import java.io.IOException;
import java.util.List;

public class DisambiguateSentences {

    TurkishSentenceParser sentenceParser;

    public DisambiguateSentences(TurkishSentenceParser sentenceParser) {
        this.sentenceParser = sentenceParser;
    }

    void parseAndDisambiguate(String sentence) {
        System.out.println("Sentence  = " + sentence);
        SentenceMorphParse sentenceParse = sentenceParser.parse(sentence);


        System.out.println("Before disambiguation.");
        writeParseResult(sentenceParse);

        System.out.println("\nAfter disambiguation.");
        sentenceParser.disambiguate(sentenceParse);

        writeParseResult(sentenceParse);

    }

    private void writeParseResult(SentenceMorphParse sentenceParse) {
        for (SentenceMorphParse.Entry entry : sentenceParse) {
            System.out.println("Word = " + entry.input);
            for (MorphParse parse : entry.parses) {
                System.out.println(parse.formatLong());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        TurkishWordParserGenerator morphParser = TurkishWordParserGenerator.createWithDefaults();
        Z3MarkovModelDisambiguator disambiguator = new Z3MarkovModelDisambiguator();
        TurkishSentenceParser sentenceParser = new TurkishSentenceParser(
                morphParser,
                disambiguator
        );
        new DisambiguateSentences(sentenceParser)
                .parseAndDisambiguate("86 lira harcardım.");

        List<MorphParse> res = sentenceParser.bestParse("Pirinç Kasım ayına göre 86, kuşbaşı et 9, beyazpeynir 16, 86, yoğurt 11, bal 10 kuruş arttı.");
        for (MorphParse re : res) {
            System.out.println(re);
        }
    }
}

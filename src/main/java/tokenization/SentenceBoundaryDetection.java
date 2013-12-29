package tokenization;

import zemberek.tokenizer.SentenceBoundaryDetector;
import zemberek.tokenizer.SimpleSentenceBoundaryDetector;

import java.util.List;

public class SentenceBoundaryDetection {

    public static void simpleSentenceBoundaryDetector() {
        String input = "Prof. Dr. Veli Davul açıklama yaptı. Kimse %6.5 lik enflasyon oranını beğenmemiş! Oysa maçta ikinci olmuştuk... Değil mi?";
        System.out.println("Paragraph = " + input);
        SentenceBoundaryDetector detector = new SimpleSentenceBoundaryDetector();
        List<String> sentences = detector.getSentences(input);
        System.out.println("Sentences:");
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    }

    public static void main(String[] args) {
        simpleSentenceBoundaryDetector();
    }
}

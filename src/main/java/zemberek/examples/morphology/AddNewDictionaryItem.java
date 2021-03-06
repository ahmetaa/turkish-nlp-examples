package zemberek.examples.morphology;

import java.io.IOException;
import zemberek.core.logging.Log;
import zemberek.core.turkish.PrimaryPos;
import zemberek.core.turkish.RootAttribute;
import zemberek.core.turkish.SecondaryPos;
import zemberek.morphology.TurkishMorphology;
import zemberek.morphology.analysis.SingleAnalysis;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.lexicon.DictionaryItem;


public class AddNewDictionaryItem {

  TurkishMorphology morphology;

  AddNewDictionaryItem(TurkishMorphology morphology) {
    this.morphology = morphology;
  }

  public static void main(String[] args) throws IOException {

    TurkishMorphology morphology = TurkishMorphology.createWithDefaults();
    AddNewDictionaryItem app = new AddNewDictionaryItem(morphology);

    Log.info("Proper Noun Test - 1 :");
    app.test("Meydan'a",
        new DictionaryItem("Meydan", "meydan", "meydan",
            PrimaryPos.Noun, SecondaryPos.ProperNoun));
    Log.info("----");

    Log.info("Proper Noun Test - 2 :");
    app.test("Meeeydan'a",
        new DictionaryItem("Meeeydan", "meeeydan", "meeeydan",
            PrimaryPos.Noun, SecondaryPos.ProperNoun));
    Log.info("----");

    Log.info("Verb Test : ");
    app.test("tweetleyeyazdım",
        new DictionaryItem("tweetlemek", "tweetle", "tivitle",
            PrimaryPos.Verb, SecondaryPos.None));

  }

  private void test(String input, DictionaryItem newItem) throws IOException {

    WordAnalysis before = morphology.analyze(input);
    Log.info("Parses for " + input + " before adding " + newItem);
    printResults(before);

    morphology.invalidateCache();

    morphology.getMorphotactics().getStemTransitions().addDictionaryItem(newItem);

    WordAnalysis after = morphology.analyze(input);
    Log.info("Parses for " + input + " after adding " + newItem);
    printResults(after);
  }

  private void printResults(WordAnalysis results) {
    int i = 1;
    if (results.analysisCount() == 0) {
      Log.info("No Analysis.");
    }
    for (SingleAnalysis result : results) {
      String str = result.formatLong();
      if (result.getDictionaryItem().attributes.contains(RootAttribute.Runtime)) {
        str = str + " (Generated by UnidentifiedTokenParser)";
      }
      Log.info(i + " - " + str);
      i++;
    }
  }
}

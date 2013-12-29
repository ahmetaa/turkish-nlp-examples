package tokenization;

import com.google.common.base.Joiner;
import org.antlr.v4.runtime.Token;
import zemberek.tokenizer.ZemberekLexer;

import java.util.Iterator;

public class TurkishTokenizationExample {

    public static void tokenIterator() {
        System.out.println("Low level tokenization iterator using Ant-lr Lexer.");
        ZemberekLexer lexer = new ZemberekLexer();
        String input = "İstanbul'a, merhaba!";
        System.out.println("Input = " + input);
        Iterator<Token> tokenIterator = lexer.getTokenIterator(input);
        while (tokenIterator.hasNext()) {
            Token token = tokenIterator.next();
            System.out.println("Token= " + token.getText() + " Type=" + token.getType());
        }
    }

    public static void simpleTokenization() {
        System.out.println("Simple tokenization returns a list of token strings.");
        ZemberekLexer lexer = new ZemberekLexer();
        String input = "İstanbul'a, merhaba!";
        System.out.println("Input = " + input);
        System.out.println("Tokenization list = " +
                Joiner.on("|").join(lexer.tokenStrings("İstanbul'a, merhaba!")));
    }

    public static void main(String[] args) {
        tokenIterator();
        System.out.println();
        simpleTokenization();
    }
}

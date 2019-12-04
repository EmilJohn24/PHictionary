import language.Trie;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TrieTest {
    private Trie testTrie;

    public TrieTest(){
        testTrie = new Trie();
    }
    @Test
    public void wordAddTest(){
        testTrie.addWord("tangina");
        assert(testTrie.searchWord("tangina"));
    }

    @Test
    public void wordFileAddTest() throws IOException {
        testTrie.addWords("dictionary.txt");
        assert(testTrie.searchWord("ulam"));
        assert(!testTrie.searchWord("bitch"));
    }

    @Test
    public void closestWordTest() throws IOException {
        testTrie.addWords("dictionary.txt");
        String match = testTrie.findClosestWord("paska");
        System.out.println(match);
        //assert(match.equals("alkohol"));
    }
}

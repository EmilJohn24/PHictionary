package language;

import com.sun.istack.internal.NotNull;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

class TrieNode {
    private Character character;
    private String word; //for quick lookup
    private Hashtable<Character, TrieNode> connectedNodes;
    static Character EMPTY = '\0';

    TrieNode(@NotNull Character character, String word){
        this(character);
        this.word = word;
    }

    TrieNode(@NotNull Character character){
        this.character = character;
        this.connectedNodes = new Hashtable<>();
        this.word = "";
    }

    TrieNode recursiveWordGetter(String word, Integer currentIndex){
        if (word.equals(this.word)) return this;
        if (currentIndex >= word.length()) return null;

        Character nextLetter = word.charAt(currentIndex);

        TrieNode nextNode = getNodeFor(nextLetter);
        if (nextNode == null) return null;
        else return nextNode.recursiveWordGetter(word, currentIndex + 1);
    }

    TrieNode getNodeFor(@NotNull Character letter){
        return connectedNodes.get(letter);
    }

    TrieNode addNextLetter(@NotNull Character letter){
        if (!connectedNodes.containsKey(letter)) {
            TrieNode newlyPlacedNode = new TrieNode(letter);
            connectedNodes.put(letter, newlyPlacedNode);
            return newlyPlacedNode;
        }
        return connectedNodes.get(letter);
    }

    //String-score pair
    Pair<String, Integer> recursiveClosestWordGetter(String searchWord, int pos, int score){
        Pair<String, Integer> lowestScorePair = new Pair<>(null, Integer.MAX_VALUE);
        if (score > searchWord.length()) return lowestScorePair;

        if (this.word.length() > 0){
            lowestScorePair = new Pair<>(this.word, score);
        }

        if (pos < searchWord.length()) score = score - 1;
        else score = score + 1;

        if (character == TrieNode.EMPTY) pos = -1;
        else if (pos >= searchWord.length() || character != searchWord.charAt(pos)) score = score + 1;

        for (TrieNode node : connectedNodes.values()) {
            Pair<String, Integer> nextLevelLowestPair = node.recursiveClosestWordGetter(searchWord, pos + 1, score);
            if (nextLevelLowestPair.getValue() < lowestScorePair.getValue() && nextLevelLowestPair.getKey() != null) {
                lowestScorePair = nextLevelLowestPair;
            }
        }

        return lowestScorePair;
    }

    void markWord(String word){
        this.word = word;
    }

    public Character getCharacter() {
        return character;
    }
}

package language;

import java.io.*;
import java.util.Hashtable;

public class Trie {
    private TrieNode root;

    public Trie(){
        root = new TrieNode(TrieNode.EMPTY);
    }
    public void addWords(String dictionary) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dictionary));
        String newWord;
        while ((newWord = reader.readLine()) != null){
            addWord(newWord);
        }
    }
    public void addWord(String word) throws NullPointerException{
        word = word.toLowerCase();
        TrieNode explorer = root;
        for (Character c : word.toCharArray()){
            explorer = explorer.addNextLetter(c);
        }
        explorer.markWord(word);
    }

    public String findClosestWord(String word){
        return root.recursiveClosestWordGetter(word, 0, 0).getKey();
    }
    public boolean searchWord(String word){
        return wordSearchHelper(word) != null;
    }

    private TrieNode wordSearchHelper(String word){
        return root.recursiveWordGetter(word, 0);
    }


}

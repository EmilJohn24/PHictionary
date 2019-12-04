package demo;

import language.Trie;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Trie trie = new Trie();
        trie.addWords("dictionary.txt");
        while(true){
            System.out.print("Word: ");
            Scanner input = new Scanner(System.in);
            String words = input.nextLine();
            String[] splitWords = words.split(" ");
            String newSentence = "";
            for (String word : splitWords) {
                if (!trie.searchWord(word)) {
                    String match = trie.findClosestWord(word);
                    newSentence = newSentence.concat(match + " ");
                } else {
                    newSentence = newSentence.concat(word + " ");
                }
            }
            System.out.println(newSentence);
        }
    }
}

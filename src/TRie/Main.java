package TRie;

import java.util.Arrays;

class TrieNode {
    boolean isTerminal;
    char data;
    TrieNode[] children = new TrieNode[26];

    TrieNode() {
        Arrays.fill(children, null);
        isTerminal = false;
    }
}

class Trie {
    static TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    static void insert(String key) {
        int level;
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }
        pCrawl.isTerminal = true;
    }

    static boolean search(String key) {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';

            if (pCrawl.children[index] == null)
                return false;

            pCrawl = pCrawl.children[index];
        }

        return (pCrawl.isTerminal);
    }
}

public class Main {
    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        Trie.insert("Hello World");
        Trie.search("Hello World");
    }
}

package com.crypto;

import java.util.ArrayList;
import java.util.List;

public class CypherMessage {

    private String originalMessage;
    private List<Digraph> digraphs;

    public CypherMessage(String originalMessage){
        // First remove the whitespace from the message
        this.originalMessage = originalMessage.replaceAll("\\s", "");
    }

    public List<Digraph> getDigraphs(){
        return this.digraphs;
    }

    public List<Digraph> toDigraphs(){
        digraphs = new ArrayList<Digraph>();
        char letters[] = originalMessage.toCharArray();
        for (int i=0;i<letters.length;i++){
            Digraph digraph = new Digraph();
            digraph.setFirstLetter(letters[i]);
            i = processSecondLetter(letters, i, digraph);
            this.digraphs.add(digraph);
        }

        return this.digraphs;
    }

    private int processSecondLetter(char[] letters, int i, Digraph digraph) {
        if ((i+1) >= letters.length) {
            digraph.setSecondLetter('Z');
        }
        else {
            digraph.setSecondLetter(letters[i + 1]);
        }
        if (digraph.getFirstLetter() == digraph.getSecondLetter()){
            digraph.setSecondLetter('X');
        } else {
            i++;
        }
        return i;
    }

    public String stripExtraX(String message) {
        char[] charsInMessage = message.toCharArray();
        String strippedMessage = "";
        for (int i=0; i<charsInMessage.length; i++) {
            boolean addChar = true;
            if (i+1 < charsInMessage.length && i-1 > 0 && charsInMessage[i] == 'X'
                    && charsInMessage[i-1] == charsInMessage[i+1]) {
                addChar = false;
            }
            if (addChar) {
                strippedMessage = strippedMessage + charsInMessage[i];
            }
        }
        return strippedMessage;
    }
}

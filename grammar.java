// Jaewon Chang, CSE 143,
// Spring 2014, Section AI
// Dian Hartono
// Programming Assignment #4, 2nd/May/2014

/* The purpose of this class to generate different
sentence/word/phrase
 * randomly by using Backus-Naur Form and using different rules that
is used in grammar.*/

import java.util.*;

public class GrammarSolver {
   private Map<String, String> rulesOfGrammar;
      
 /* Pre: parameter should not be null && parameter size should
be greater than 0
            and it should not contain same non terminal. 
            Otherwise IllegalArguentException is thrown.
 * Para: It takes String of list as parameter which contains rules of
grammar
            in a form of string.
 * Post: This constructor initialize a object over the Backus-Neur
Form
 grammar rules and puts the rules depending on its format to the
object.*/
   public GrammarSolver(List<String> rules) {
      if (rules == null || rules.size() == 0) {
         throw new IllegalArgumentException();
      }
      rulesOfGrammar = new TreeMap<String, String>();
      for (String format : rules) {
         String[] separate = format.split("::=");
         String notTerminal = separate[0];
         if (rulesOfGrammar.containsKey(notTerminal)) {
            throw new IllegalArgumentException();
         }
         rulesOfGrammar.put(notTerminal, separate[1]);
      }
   }
   
 /* Pre: The length of string parameter should be > 0 &&
string parameter
 should not be null. Otherwise throws IllegalArgumentException.
 (parameter strictly should match all letter including capital and
lowercase).
 * Para: This method takes String of symbol as a parameter (user
input).
 * Post: checks whether the symbol is non terminal in the grammar or
not.*/
   public boolean contains(String symbol) {
 if (symbol.length() == 0 || symbol ==
null) {
         throw new IllegalArgumentException();
      }
      return rulesOfGrammar.containsKey(symbol);
   }

   /* Pre:  non terminal symbol should  exist. 
    * Para: None.
    * Post: returns the non terminal symbol in the sorted fashion.*/
   public Set<String> getSymbols() {
      return rulesOfGrammar.keySet();
   }
   
 /* Pre: The length of string parameter should be > 0 &&
string parameter
 should not be null. Otherwise throws IllegalArgumentException.
 (parameter strictly should match all letter including capital and
lowercase).
    * Para: This method takes String as a parameter (user input).
 * Post: This method uses a grammar rules to make a random occurrence
of word
 and which is return a sentence/phrase/word in form of a string. */
 public String generate(String symbol) {

      String resultLine = "";
 if (!contains(symbol)) { // called the contains method in this
program.
         return symbol;
      } else {
         String[] randomlyForm = splitAndFormat(symbol);
         for (int i = 0; i < randomlyForm.length; i++) {
            resultLine += generate(randomlyForm[i]) + " ";
         }
         return resultLine.trim();
      }
   }
   
 /* Pre: The length of string parameter should be > 0 &&
string parameter
 should not be null. Otherwise throws IllegalArgumentException.
 (parameter strictly should match all letter including capital and
lowercase).
    * Para: This method takes String as a parameter (user input).
 * Post: This method returns the array of string. This method formats
the array 
 according to right section "non - terminal" or
"terminal" */ 
   private String[] splitAndFormat(String symbol) {
      Random rand = new Random();
      String ruleOther = rulesOfGrammar.get(symbol);
 String[] toSplitDash = ruleOther.split("[|]"); //split by
dash.
 int randomValue = rand.nextInt(toSplitDash.length); //amount of
random# available
 String randomWord = toSplitDash[randomValue].trim();//random index
(spacing off)
 String[] takeRandom = randomWord.split("[ \t]+"); //split
by spacings
      return takeRandom;
   }
}

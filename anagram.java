// Jaewon Chang, CSE 143,
// Spring 2014, Section AI
// Programming Assignment #6, 18th/MAY/2014

/* Anagram can be defined as a word or phrase made by rearranging
   the letter or another word. This class chooses/tracks/makes all   
   the possible anagram in the phrase. */

import java.util.*;

public class Anagrams {
   private Set<String> collectWord;
   
 /* Pre: The Set of dictionary should not be null, otherwise throw
Illegal
 ArgumentException. Dictionary should have some words inside.
 words in the parameter shoud be valid language (in english) in this
case.
 The parameter that is passed in this costructor is not modified (on
spec).
 Para: This method takes set of dictionary as a parameter, that
contains
            different types of words.
 Post: This constructor initializes object over dictionary. Adds all
the
            existing words to the object in a sorted fashion. */
   public Anagrams(Set<String> dictionary) {
      nullChecker(dictionary);
      collectWord = new TreeSet<String>();
      collectWord.addAll(dictionary);
   }
   
   /* pre:  If the parameter that passed should not a null, otherwise
            throw IllegalArgumentException.
      Para: This method takes String as a parameter that is typed by
            the user. The user input is case insensitive.
      Post: This method returns the set of words that can be generated
            by using users input in a sorted order.*/
   public Set<String> getWords(String phrase) {
 return getWordsHelper(phrase); // Exception is taken care by the
method.
   }
   
   /* pre:  If the parameter that passed should not a null, otherwise
            throw IllegalArgumentException. User should type valid
            language.
      Para: This method takes String as a parameter that is typed by
 the user. The user input is case insensitive (doesn't matter
            whether user write in capital or small letter).
 Post: This method prints out all the possible anagram that can be
made
            from the user in input. It is not sorted.*/
   public void print(String phrase) {
 print(phrase, 0); //Exception is taken care from the method that is
called
   }
   
 /* pre: If the parameter String that is passed in should not a null
and max
 should be a postive number greater or equal to 0, otherwise throw
            IllegalArgumentException. User should type valid language.
 Para: This method takes String and int type as parameter that is
typed
 by the user. The user input is case insensitive (doesn't matter
            whether user write in capital or small letter).
 Post:This method prints out the possible number of anagrams
that can be
 outputed. That possible number is determined by the user
input.*/
   public void print(String phrase, int max) {
      if (phrase == null || max < 0) {
         throw new IllegalArgumentException();
      }
 print(phrase, max, new Stack<String>(), new
LetterInventory(phrase));
   }
   
 /* pre: If the parameter String that is passed in should not a null
and
 max should be a postive number greater or equal to 0, otherwise throw
            IllegalArgumentException. User should type valid language.
 For letterInventory class = will throw illegalArgumentException if
the
 word that need to be removed from the letterinventory does not
contain
            in the inventory.
      Para: This method takes int type as parameter that is typed
 by the user. And stack, letter inventory class and the set of string
 that stores the selected words. The user input is case insensitive
 (doesn't matter whether user write in capital or small letter).
      Post: This method is private helper that shows all the ways to
 make possible anagram, given the stack of existing word anagram
 already chosen. Also this method prints the anangram in different 
            possible way. "Explore and unchooses" */
 private void print(String phrase, int max, Stack<String>
divider, LetterInventory wordBucket) {
      if(wordBucket.size() == 0) {
         System.out.println(divider);
      } else if (max == 0 || divider.size() < max) {
 for(String word : getWordsHelper(phrase)) {
 if(wordBucket.contains(word)) {
               wordBucket.subtract(word); // removes the word
 divider.push(word); // chooses the word as a part of output
               print(phrase, max, divider, wordBucket);
               wordBucket.add(word); // put the word back inside
 divider.pop(); // unchooses the word as a part of the output.
            }
         }
      }
   }
   
   /* pre:  If the parameter that passed should not a null, otherwise
            throw IllegalArgumentException.
      Para: This method takes any type as a parameter.
 Post: This method checks whether passed in parameter is null or not*/
   private void nullChecker(Object type) {
      if (type == null) {
         throw new IllegalArgumentException();
      }
   }
   
   /* pre:  If the parameter that passed should not a null, otherwise
            throw IllegalArgumentException. User should type valid
            language in english in this case.
      Para: This method takes String as a parameter that is typed by
 the user. The user input is case insensitive (doesn't matter
            whether user write in capital or small letter).
      Post: This method returns the set of words that can be generated
            by using users input in a sorted order.*/
   private Set<String> getWordsHelper(String phrase) {
      nullChecker(phrase);
      Set<String> setOfWords = new TreeSet<String>();
      LetterInventory wordBucket = new LetterInventory(phrase);    
      for(String words : collectWord) {
         if (wordBucket.contains(words)) {
            setOfWords.add(words);
         }
      }
      return setOfWords;
   }
}

// Jaewon Chang, CSE 143,
// Spring 2014, Section AI
// Programming Assignment #3, 24th/April/2014

/* This class helps program which is similar to the games played by
the students 
   in the college. This class keeps track of who has stalked whom 
   and who has killed whom.*/

import java.util.*;

public class AssassinManager {
   private AssassinNode killNode;
   private AssassinNode graveNode;
   
   /* Pre: names are non-empty, non-null string and non-duplicates.
           acceptable names should exist properly otherwise throw
           IllegalArgumentException.
      Para: Takes arraylist that contains names of player.
      Post: Adds the names to the object. */
   public AssassinManager(ArrayList<String> names) {
      if (names == null || names.size() == 0) {
         throw new IllegalArgumentException();
      }     
      killNode = new AssassinNode(names.get(0));
      AssassinNode current = killNode;
      for (int i = 1; i < names.size(); i++) {
         current.next = new AssassinNode(names.get(i));
         current = current.next;
      }
   }
   
   /* Pre: There should be atleast two person in.
      Post: prints who is stalking whom. */
   public void printKillRing() {
      AssassinNode current = killNode;
      while (current != null) {
 System.out.print(" " + current.name + " is stalking
");
         if (current.next != null) {
            System.out.println(current.next.name);
         } else {
            System.out.println(killNode.name);
         }
         current = current.next;
      }
   }
   
   /* Pre: If someone is killed.
      Post: prints who is killed by whom. */
   public void printGraveyard() {
      AssassinNode current = graveNode;
      while (current != null) {
 System.out.println(" " + current.name + " was killed
by " + current.killer);
         current = current.next;
      }
   }
   
   /* Pre: name is not null.
   Para: This method takes String name as a parameter which user typed.
      Post: checks name is dead or not. */
   public boolean graveyardContains(String name) {
      return checkContain(graveNode, name);
   }
   
   /* Pre: name is not null.
 Para: This method takes String name as a parameter which user typed.
  Post: checks name is
still alive or not or even exist*/
   public boolean killRingContains(String name) {
      return checkContain(killNode, name);
   }
   
   /* Post: returns checks of whether this game is over or not. */
   public boolean isGameOver() {
      return killNode.next == null;
   }
   
/* Post: returns the one last person left in this
game. */
   public String winner() {
      if (isGameOver()) {
         return killNode.name;
      } else {
         return null;
      }
   }
   
   /* Pre: game is still on otherwise throws IllegalStateException.
 Para: This method takes String name as a parameter which user typed.
 Post: This method keeps history of targeted person who are
assassinated. */
   public void kill(String name) {
      if (isGameOver()) {
         throw new IllegalStateException();
      } else if (!killRingContains(name)) {
         throw new IllegalArgumentException();
      }
      AssassinNode currentKill = killNode;
      AssassinNode currentGrave = graveNode;    
 if(!killNode.name.equalsIgnoreCase(name)) { // somewhere in the
middle case
 while (currentKill.next != null &&
!currentKill.next.name.equalsIgnoreCase(name)) {
            currentKill = currentKill.next;
         }  // Ignores the front name case;
         graveNode = currentKill.next;
         currentKill.next = currentKill.next.next;
      } else { // front case;
 while (currentKill.next != null) { //deals with only front case.
            currentKill = currentKill.next;
         }
         graveNode = killNode; // takes the first existing
         killNode = killNode.next; // take out the name;
      }
      graveNode.next = currentGrave; // swap order
      graveNode.killer = currentKill.name;
   }
   
   /* Pre: String is not null.
 Para: This method takes object and string of name which user typed.
      Post: checks whether user typed input exist or not */
   private boolean checkContain(AssassinNode type, String name) {
      AssassinNode current = type;
      boolean check = false;
      while (current != null) {
         if (current.name.equalsIgnoreCase(name)) {
            check = true;
         }
         current = current.next;
      }
      return check;
   }
}

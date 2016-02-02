// Jaewon Chang, CSE 143,
// Spring 2014, Section AI
// Programming Assignment #2, 17th/April/2014

/* This program goes through the HTML and checks whether the tags in 
   the HTML is reasonable sequence. */

import java.util.*;

public class HtmlValidator {
   private Queue<HtmlTag> tagQueue;
  private
Stack<HtmlTag> tagStack;
   
   // Creates empty queue.
   public HtmlValidator() {
      this(new LinkedList<HtmlTag>());
   }
   
 /* Pre: Parameter tags should not be null otherwise throws
IllegalArgumentException.
    * Parameter: This method a Html tag queue as a parameter.
    * Post: Initializes the Objects */
   public HtmlValidator(Queue<HtmlTag> tags) {
      throwNull(tags);
      tagStack = new Stack<HtmlTag>();
      tagQueue = new LinkedList<HtmlTag>(tags);
   }
   
 /* Pre: tags should not be null otherwise throws
IllegalArgumentException.
    * Parameter: tags is the each element.
    * Post: Adds each object into the queue.*/
   public void addTag(HtmlTag tag) {
      throwNull(tag);
      tagQueue.add(tag);
   }
   
   // This method returns the copy of Html queue.
 public
Queue<HtmlTag> getTags() {
      return tagQueue;
   }
   
 /* Pre: if parameter isn't null otherwise throws
IllegalArgumentException.
    * Parameter: String element is one that will be removed.
    * Post: This method removes
the particular element */
   public void removeAll(String element) {
      throwNull(element);
      int size = tagQueue.size();
      for (int i = 0; i < size; i++) {
         HtmlTag addBack = tagQueue.remove();
         if (!addBack.getElement().equals(element)) {
            tagQueue.add(addBack);
         }
      }
   }
   
   /* Pre: none.
 * Parameter: none.
    * Post: Prints the Html queue is appropriatly fashion */
   public void validate() {
      int indent = 0;
      int queueSize = tagQueue.size();
      
      for (int i = 0; i < queueSize; i++) {
         HtmlTag typeQueue = tagQueue.remove();
         tagQueue.add(typeQueue);
 if(typeQueue.isOpenTag() && typeQueue.isSelfClosing()) {
            withIndent(indent, typeQueue.toString());
 } else if (!typeQueue.isSelfClosing() &&
typeQueue.isOpenTag()) {
            withIndent(indent, typeQueue.toString());
            tagStack.push(typeQueue);
            indent++;
         } else { // closed tag
 if (tagStack.size() == 0 || !tagStack.peek().matches(typeQueue)) {
 System.out.println("ERROR unexpected tag: " + typeQueue);
 } else { // stack size is greater than 0 && tagStack tag
matches queue tag.
               tagStack.pop();
               indent--;
               withIndent(indent, typeQueue.toString());
            }
         }
      }
      unClosed(tagStack);
   }             
            
   /* Pre: tagStack shouldn't be null.
    * Parameter: This method take tags of stack as a parameter.
    * Post: This method prints error. */
   private void unClosed(Stack<HtmlTag> tagStack) {
      while (!tagStack.isEmpty()) {
         HtmlTag error = tagStack.pop();
         System.out.println("ERROR unclosed tag: " + error);
      }
   }
   
   /* Pre: The parameter should exist.
    * Parameter: This method takes any type of prameter.
    * Post: This method checks parameter is null*/
   private void throwNull(Object type) {
      if (type == null) {
         throw new IllegalArgumentException();
      }
   }
   
   /* Pre: String line is not null && indent >= 0
    * Parameter: String line is the Html tags.
    * Post: This method prints the line */
   private void withIndent(int indent, String line) {
      for (int j = 0; j < indent; j++) {
         System.out.print("    ");
      }
      System.out.println(line);
   }
}

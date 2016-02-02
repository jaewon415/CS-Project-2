// Jaewon Chang, CSE 143,
// Spring 2014, Section AI
// Programming Assignment #8, 04th/June/2014 (HuffmanTree (Part A))

/* Huffman Coding is an algorithm that compress text data to make smaller
 * Number of bytes of file. This program is used to encode and decode the files
 * that are provided by the professor. */

import java.io.*;
import java.util.*;

public class HuffmanTree {
   private HuffmanNode overAllRoot;
   
   /* Pre : The parameter should contains something inside.
    * Para: This method takes arrays of integers. 
    * Post: This constructor initializes the instance of the objects.
    *       adds based on the number of frequency. Also builds the
    *       tree to store data and orderly fashion.*/
   public HuffmanTree(int[] counts) {
      Queue<HuffmanNode> priorNode = new PriorityQueue<HuffmanNode>();
      for (int arrayIndex = 0; arrayIndex < counts.length; arrayIndex++) {
         int times = counts[arrayIndex];
         if (times >= 1) {
            HuffmanNode current = new HuffmanNode(arrayIndex, times);
            priorNode.add(current);
         }
      }
      HuffmanNode initial = new HuffmanNode(counts.length, 1);
      priorNode.add(initial);
      
      overAllRoot = priorHelper(priorNode);
  }
   
   /* Pre : The parameter should contains something inside.
    * Para: This method takes priority queue as a parameter
    * Post: This is the private helper method that returns min elements.
    *       and makes tree.*/   
   private HuffmanNode priorHelper(Queue<HuffmanNode> priorNode) {
      int total = 0;
      while (priorNode.size() > 1) {
         HuffmanNode left = priorNode.remove();
         HuffmanNode right = priorNode.remove();
         total = left.frequency + right.frequency;
         HuffmanNode topMost = new HuffmanNode(0, total, left, right);
         priorNode.add(topMost);
      }
      return priorNode.remove();
   }
   
   /* Pre : PrintStream output should not be null. 
    * Para: This method takes printstream ouput as a parameter to print on the file.
    * Post: prints to the file typed by the user. String concanated with the designated
    *       numbers making the string more like a binary format typed.  */
   public void write(PrintStream output) {
      write(output, overAllRoot, "");
   }
   
   /* Pre : PrintStream output should not be null and temp should not be null. 
    * Para: This method takes printstream ouput as a parameter to print on the file.
            HuffmanNode temp and String as a parameters.
    * Post: This method is private helper method that builds the string by
            assigning appropriate numbers. prints to the file typed by the user.*/   
   private void write(PrintStream output, HuffmanNode temp, String binaryFormat) {
      if (isLeaf(temp)) {
         output.println(temp.alphabet);
         output.println(binaryFormat);
      } else {
        write(output, temp.left, binaryFormat + "0");
         write(output, temp.right, binaryFormat + "1");
      }
   }
   
  /* Para: This method takes QuestionNode as a parameter.
   * Post: This method returns whether the temp does not have any more branch
   *       or not.*/
   private boolean isLeaf(HuffmanNode temp) {
      return temp.right == null && temp.left == null;
   }

  /* Pre : Scanner input should not be a null. Assume that Scanner contains a
   *       Tree stored in a standard format. 
   * Para: This constructor takes Scanner input as a parameter that is used for
   *       User to type.
   * Post: This method builds the tree by using the information of input. The
   *       last parameter in helpDecode method will increment. */   
   public HuffmanTree(Scanner input) {
      overAllRoot = new HuffmanNode(0, 0);
      while (input.hasNextLine()) {
         String line = input.nextLine();
         int ascii = Integer.parseInt(line);
         String next = input.nextLine();
         overAllRoot = helpDecode(overAllRoot, next, ascii, 0);
         //overAllRoot = temp;
      }
   }

  /* Pre : index should be always smaller than the parameter of String line's length.
   * Para: This method takes tree, String of line, int form of ascii value and the 
   *       int index of the line.       
   * Post: This methods index will increment. This method builds the tree by using the
   *       information of the line provided. Returns the tree*/     
   private HuffmanNode helpDecode(HuffmanNode temp, String line, int asciiNum, int index) {
      if (line.length() > index) {
         char alpha = line.charAt(index);
         if (alpha == '1') {
            if (temp.right == null) {
               temp.right = new HuffmanNode(0, 0);
            }
            temp.right = helpDecode(temp.right, line, asciiNum, index + 1);
         } else {
            if (temp.left == null) {
               temp.left = new HuffmanNode(0, 0);
            }
            temp.left = helpDecode(temp.left, line, asciiNum, index + 1);
         }
      }
      temp.alphabet = asciiNum;
      return temp;
   } 

  /* Pre : BitInputStream input and PrintStream should not be a null.       
   * Para: This constructor takes BitInputStream input , printStream output
   *       and integer of eof as a parameter. (EOF = end of file).
   * Post: This method reads the bits from the input and prints the
   *       designated charater (alphabet) to the output assigned.*/      
   public void decode(BitInputStream input, PrintStream output, int eof) {
      HuffmanNode temp = overAllRoot;
      int bit = input.readBit();
      while (bit != eof) {
         if (bit == 1) {
            temp = temp.right;
         } else {
            temp = temp.left;
         }
         if (!isLeaf(temp)) {
            bit = input.readBit();
         } else if (temp.alphabet != eof) {
            int binary = temp.alphabet;
            output.write(binary);
            bit = input.readBit();
           temp = overAllRoot;
         } else {
            bit = eof;         
         }
      }
   }
   
   // This HuffmanNode object represents an tree.
   private class HuffmanNode implements Comparable<HuffmanNode>{
      public HuffmanNode left; // pointing to the left side of the tree.
      public HuffmanNode right; // pointing to right side of the tree
      public int frequency; // frequency of the data
      public int alphabet; // data in a form of Integer
      
      // This constructor makes a leaf node with the given integer of data
      public HuffmanNode(int alphabet, int frequency) {
         this(alphabet, frequency, null, null);
     }
      
      // This constructor makes lead and branch with the given data of parameter.
      public HuffmanNode(int alphabet, int frequency, HuffmanNode left, HuffmanNode right) {
         this.left = left;
         this.right = right;
         this.alphabet = alphabet;
         this.frequency = frequency;
      }
      
      /* Para : This method takes other huffmanNode as a parameter.
       * Post : Return < 0 if this tag comes before the given other huffmanNode
       *        Return > 0 if this tag comes after the given other huffmanNode
       *        Return 0 if this tag is the same as the given other huffmanNode */
      public int compareTo(HuffmanNode other) {
         return frequency - other.frequency;
      }
   }
}

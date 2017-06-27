import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by puneet on 5/26/2017.
 */
/*
Justiﬁed TextIn text processing, one approach to making a document appealing to the eye is to justify paragraphtext.
With monospaced fonts in a terminal, as you might see in a man page, this is accomplished byinserting spaces
between words to produce a line of text where the ﬁrst character of that line beginson the left-hand margin
and the last printable character is on the right-hand margin.For this problem, we would like you to think of
a single line of text, and justify that text into a buﬀer,where the ﬁrst character of the line of text
is in the ﬁrst spot in the buﬀer and the last character of textis in the speciﬁed slot in the buﬀer.
In ruby, you might deﬁne this function as follows:
def justify(line, length)
It might be called like this:
puts justify("The quick brown fox jumps over the lazy dog.", 52)It produces a string that looks like this:
The  quick  brown  fox  jumps  over  the  lazy  dog.
12345678901234567890123456789012345678901234567890123456789
 */

public class LineJustifier {
    //Keeping things simple!: Utility class so right now no constructor
    //Assumption on input validation
    //Assumption on length > 0 -- changes lead to changes in calculate desired spacing method
    //This code works with "space" delimiter only -- changes to splitStringIntoWords needed to more richer delimiters

    public static void main(String[] args) {

        //this will be driver
        //code delivered as tests including the test in initial problem

        StringBuffer result = LineJustifier.justify("The quick brown fox jumps over  the lazy dog.", 52);
        // write your code here
        System.out.println("Result of interview problem:");
        System.out.println(result.toString());
        System.out.println("12345678901234567890123456789012345678901234567890123456789");

        System.out.println("Done.");
    }

    public static StringBuffer justify(String line, int length) throws IllegalArgumentException {
        //validate inputs
        //split line into word array with space separator
        //then calculate desired spacing
        //then compute desired justified line

        //Now validate inputs - more can be done here, if desired!!
        if(line == null || line.length()==0 || length <=0){
            throw new IllegalArgumentException("Please check input");
        }
        //so we have good inputs
        StringBuffer result = new StringBuffer();
        ArrayList<String> lineAsStringArray = splitStringIntoWords(line);

        //Lets calculate spacing
        // Attempt will be made to keep spacing uniform otherwise last word will have extra spacing.
        String [] desiredSpacingStrings = calculateDesiredSpacing(lineAsStringArray, length);

        //Now assemble final buffer
        result = produceFinalBuffer(lineAsStringArray,desiredSpacingStrings[0],desiredSpacingStrings[1]);

        return result;
    }

    private static String[] calculateDesiredSpacing(ArrayList<String> inputStringList, int finalLength){
        String[] results = new String[2];
        //System.out.println("length: " + length);
        //System.out.println("String list size: " + lineAsStringArray.size());
        int totalCharLength = 0;
        for (int i = 0; i < inputStringList.size(); i++) {
            totalCharLength += inputStringList.get(i).length();
        }
        //System.out.println("totalCharLength: " + totalCharLength);
        int whiteSpaceAvailable = finalLength - totalCharLength;
        int nonUniformSpacingResidue = whiteSpaceAvailable%(inputStringList.size()-1);
        //System.out.println("nonUniformSpacingResidue: " + nonUniformSpacingResidue);
        int desiredSpacing = (whiteSpaceAvailable-nonUniformSpacingResidue)/(inputStringList.size()-1);
        int lastElementSpacing = desiredSpacing + nonUniformSpacingResidue;
        StringBuffer desiredSpacingStringBuffer = new StringBuffer();
        for(int i=0;i<desiredSpacing;i++){
            desiredSpacingStringBuffer.append(" ");
        }
        //System.out.println("desiredSpacing: " + desiredSpacing);
        //System.out.println("desiredSpacingStringBuffer: " + desiredSpacingStringBuffer.toString());
        StringBuffer lastElementSpacingStringBuffer = new StringBuffer(desiredSpacingStringBuffer.toString());
        for(int i=0;i<lastElementSpacing;i++){
            lastElementSpacingStringBuffer.append(" ");
        }
        // System.out.println("lastElementSpacing: " + lastElementSpacing);
        // System.out.println("lastElementSpacingStringBuffer: " + lastElementSpacingStringBuffer.toString());
        results[0]= desiredSpacingStringBuffer.toString();
        results[1] = lastElementSpacingStringBuffer.toString();
        return results;
    }

    private static StringBuffer produceFinalBuffer(ArrayList<String> inputStringList, String  desiredSpacingString, String lastElementSpacingString) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < inputStringList.size()-1; i++) {
            result.append(inputStringList.get(i));
            result.append(desiredSpacingString);
        }
        result.append(inputStringList.get(inputStringList.size()-1));
        result.append(lastElementSpacingString);
        return result;
    }


    private static ArrayList<String> splitStringIntoWords(String line){
        ArrayList<String> result = new ArrayList<String>();

        StringTokenizer tokenizer = new StringTokenizer(line);

        //Split line by space
        while (tokenizer.hasMoreElements()) {
            result.add ((String)tokenizer.nextElement());
            //System.out.println(lineAsStringArray);
        }
        return result;
    }
}

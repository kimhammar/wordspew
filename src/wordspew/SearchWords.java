package wordspew;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchWords implements Comparator<String>{
  public static List<String> getWords(String letters) throws IOException{
//    Map<String,String> lol = new HashMap();
    List<String> lol = new ArrayList<>();
    List <Character> myLetters = letters.toLowerCase().chars().mapToObj(e -> (char) e).collect(Collectors.toList());
    try {
      BufferedReader treasure = new BufferedReader(new InputStreamReader(
          new FileInputStream("src/wordspew/ordlista.txt")));
      
      
      
      while(treasure.ready()) {
        String temp = treasure.readLine();
        List <Character> possibleWord = temp.toLowerCase().chars().mapToObj(e -> (char) e)
            .collect(Collectors.toList());
        
        for(int i = 0; i < myLetters.size(); i++) {
          if (possibleWord.contains(myLetters.get(i))) {
            possibleWord.remove(myLetters.get(i));
          }
        }
        if(possibleWord.size() == 0 ) lol.add(temp);
        
        
//        if(temp.equals(letters)) System.out.println(temp);
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Collections.sort(lol, new SearchWords());
    return lol;
    
  }
  
  
  public static void main(String[] args) throws IOException {
    List<String> s = getWords("ippasn");
    System.out.println(s);
  }


  @Override
  public int compare(String arg0, String arg1) {
    return arg0.length() > arg1.length() ? -1 : arg0.length() == arg1.length() ? 0 : 1;
  }

}

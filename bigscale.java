// crate dynemic List in which you add your conext name in form of String elemnt 
//secont task is you have to find pattern form whole List and give  a count how many time String meand conect have this pattern 
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class bigscale.java {
    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
        Scanner sc  = new Scanner(System.in);
        List<String> contect = new ArrayList<>();
        int count =0;
        
        System.out.print("enter number  op you want to parform : ");
        int no = sc.nextInt();
        System.out.print(no);
        
        //add contect in ArrayList 
        for (int i=0; i<=no;i++){
            String name = sc.nextLine();
            contect.add(name);
        }
        System.out.println("enter pattent that you want to count : ");
        // search opretion 
        String p=sc.nextLine();
        Pattern ptn = Pattern.compile(p);
        // Stream<MatchResult>  match = sc.findAll(ptn);
        
        for (String name: contect){
            Matcher matcher=ptn.matcher(name);
            if (matcher.find()){
                count++;
            }
        }
        //display Array and count 
        for(String name:contect){
            System.out.println(name);
           
        }
         System.out.println();
        System.out.println("count of number that your pattern is  repite in List is : "+ count );
    }
}

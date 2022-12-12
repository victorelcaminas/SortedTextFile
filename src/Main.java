import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException  {
        SortedTextFile s = new SortedTextFile("test.txt");
       /*  s.put("ggggggg");
        s.put("bbbbbbb");
        s.put("aaaaaaa");
        s.put("ccccccc");
        s.put("ddddddd"); */
        s.print();
        //s.removeElementAt(2);
        //s.removeElementAt(3);
        // s.emptyFile();
        System.out.println(s.existsElement("zzzzz"));
    }
}

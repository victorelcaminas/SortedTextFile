import java.io.*;

public class SortedTextFile {
    private String filename;

    public SortedTextFile(String filename) throws IOException {
        this.filename = filename;
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public String getFilename() {
        return filename;
    }

    public void put(String newLine) throws IOException {
        File file = new File(filename);
        File temp = new File("temp.txt");
        BufferedReader input = null;
        PrintWriter out = null;

        try {
            input = new BufferedReader(new FileReader(file));
            out = new PrintWriter(new FileWriter(temp));
            String line;
            boolean alreadyInserted = false;
            while ((line = input.readLine()) != null) {
                if (!alreadyInserted && line.compareTo(newLine) > 0) {
                    out.println(newLine);
                    alreadyInserted = true;
                }
                out.println(line);
            }
            if (!alreadyInserted) {
                out.println(newLine);
            }
            file.delete();
            temp.renameTo(file);
        } finally {
            if (input != null) {
                input.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    public int getNumElements() throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            String line;
            int counter = 0;
            while ((line = in.readLine()) != null) {
                counter++;
            }
            return counter;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public String getElementAt(int numLine) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            String line;
            int counter = 0;
            while ((line = in.readLine()) != null) {
                counter++;
                if (counter == numLine) {
                    return line;
                }
            }
            return null;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public void removeElementAt(int numLine) throws IOException {
        File file = new File(filename);
        File temp = new File("temp.txt");
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new FileReader(file));
            out = new PrintWriter(new FileWriter(temp));
            String line;
            int counter = 0;
            while ((line = in.readLine()) != null) {
                counter++;
                if (counter != numLine) {
                    out.println(line);
                }
            }
            file.delete();
            temp.renameTo(file);
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    public boolean isEmpty() throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            return in.readLine() == null;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public void emptyFile() throws IOException {
        File file = new File(filename);
        file.delete();
        file.createNewFile();
    }

    public void print() throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public boolean existsElement(String element) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.compareTo(element) > 0) {
                    return false;
                } else if (line.equals(element)) {
                    return true;
                }
            }
            return false;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void print(String filename) throws IOException {
        SortedTextFile s = new SortedTextFile(filename);
        s.print();
    }

    public static void merge(String filename1,
                             String filename2,
                             String filenameOut) throws IOException {
        BufferedReader input1 = null;
        BufferedReader input2 = null;
        PrintWriter output = null;

        try {
            input1 = new BufferedReader(new FileReader(filename1));
            input2 = new BufferedReader(new FileReader(filename2));
            output = new PrintWriter(new FileWriter(filenameOut));
            String line1 = input1.readLine();
            String line2 = input2.readLine();

            while (line1 != null || line2 != null) {
                if (line1 == null) {
                    output.println(line2);
                    line2 = input2.readLine();
                } else if (line2 == null) {
                    output.println(line1);
                    line1 = input1.readLine();
                } else if (line1.compareTo(line2) < 0) {
                    output.println(line1);
                    line1 = input1.readLine();
                } else {
                    output.println(line2);
                    line2 = input2.readLine();
                }

            }

        } finally {
            if (input1 != null) {
                input1.close();
            }
            if (input2 != null) {
                input2.close();
            }
            if (output != null) {
                output.close();
            }
        }

    }

}

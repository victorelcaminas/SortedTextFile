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
}

package History;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author dimitris
 */
public class MyFileWriter {

    public MyFileWriter() {
        initComponents();
    }

    private void initComponents() {

        file = new File("./src/History/history.txt");
        file.deleteOnExit();
        fileWriter = null;
        buff = null;
        String content = "test content";
        
        checkFileExists();

        try {
            fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            buff = new BufferedWriter(fileWriter);
            //writeFile(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //closeWriterAndBuffer();
        }
    }

    /**
     * Έλεγχος για την ύπαρξη του αρχείου
     */
    private void checkFileExists() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Γράφει στο αρχείο
     * @param content
     * @throws IOException 
     */
    public void writeFile(String content) throws IOException {
        buff.write(content);
        //buff.newLine();
    }

    /**
     * Προσθέτει κείμενο στο αρχείο
     * @param content
     * @throws IOException 
     */
    public void appendFile(String content) throws IOException {
        buff.append(content);
    }

    /**
     * Κλείνει τον BufferedWriter και τον FileWriter.
     */
    public void closeWriterAndBuffer() {
        try {
            if (buff != null) {
                buff.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    public void deleteHistoryFile() {
        file.delete();
    }

    private File file;
    private FileWriter fileWriter;
    private BufferedWriter buff;

}

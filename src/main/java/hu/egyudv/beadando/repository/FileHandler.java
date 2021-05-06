package hu.egyudv.beadando.repository;

import java.io.*;

public class FileHandler {

    private final File file;

    public FileHandler(String fileName) {
        this.file = new File(fileName);
    }

    public void writeFile(String fileContent) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.append(fileContent).append("\n");
        fw.flush();
        fw.close();
    }

    public String getFileContent() throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        StringBuilder result = new StringBuilder();
        while((line = br.readLine()) != null) {
            result.append(line).append("\n");
        }
        br.close();
        fr.close();
        return result.toString();
    }

}
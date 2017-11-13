import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReadFromFile {
    private String path;

    public ReadFromFile(String path){
        this.path = path;
    }

    public ArrayList<String> openFile() throws IOException{
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<String> textData = new ArrayList<>();

        String tempLine;
        while ( (tempLine = br.readLine()) != null){
            textData.add(tempLine);
        }

        br.close();
        return textData;
    }



}

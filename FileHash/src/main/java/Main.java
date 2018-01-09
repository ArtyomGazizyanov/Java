import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private List<File> recursiveList = new ArrayList<File>();
    private static Map<String, String> filesHashCollection = new HashMap();

    public void walkDir(String pathname) {
        File d = new File(pathname);
        recursiveList.add(d);
        if(d.isDirectory()) {
            for(String f : d.list()) {
                walkDir(pathname+'/'+f);
            }
        }
    }

    public List<File> getRecursiveList() {
        return recursiveList;
    }
    public Map<String, String> getfilesHashCollection() {
        return filesHashCollection;
    }

    public static void main(String[] args) throws Exception {
        Main dirWalker = new Main();
            if(args == null) throw (new Exception("Null parameters are not allowed"));
            if(args.length == 0) throw (new InvalidArgumentException(args));
            for(String path : reader(args[0]))
            {
                dirWalker.walkDir(path);

                for( File dir : dirWalker.getRecursiveList())
                {
                    try {
                        if(!dir.isDirectory() && !filesHashCollection.containsKey(dir.getAbsolutePath())) {
                            getFNVHash(dir.getAbsolutePath());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            PrintFilesHash();
        }

    public static ArrayList<String> reader(String sourceFile) {
        ArrayList<String> pathsToFNV = new ArrayList<String>();

        try(BufferedReader readFromFile = new BufferedReader(new FileReader( new File(sourceFile)))) {
            String line;

            while ((line = readFromFile.readLine()) != null) {
                pathsToFNV.add(line);
            }

            readFromFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pathsToFNV;
    }

    public static void getFNVHash(String pathWay) throws IOException {
        final int BUFFER_SIZE = 1024;
        int fileHashSum = FNVHash.FNV_32_INIT;
        try {
            FileInputStream fis = new FileInputStream(pathWay);
            byte[] buffer = new byte[BUFFER_SIZE];
            int read = 0;
            while( ( read = fis.read( buffer ) ) > 0 ){
                fileHashSum = FNVHash.hash32(buffer, read, fileHashSum);
                buffer = new byte[BUFFER_SIZE];
            }
            fis.close();
            if(fileHashSum == FNVHash.FNV_32_INIT)
            {
                fileHashSum = 0;
            }
            filesHashCollection.put(pathWay, Integer.toHexString(fileHashSum));
        }
        catch (Exception e)
        {
            filesHashCollection.put(pathWay, Integer.toHexString(0));
        }
    }

    public static void PrintFilesHash(){
        long i = 0;
        for (Map.Entry<String, String> pair : filesHashCollection.entrySet()) {
            System.out.format("%s : %s\n", pair.getValue(), pair.getKey());
        }
    }
}

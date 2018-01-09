package atom.volgatech;

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

    public static void main(String[] args) {
        Main dirWalker = new Main();

        for(String path : reader(args[0]))
        {
            dirWalker.walkDir(path);

            for( File dir : dirWalker.getRecursiveList())
            {
                try {
                    if(!dir.isDirectory()) {
                        getFNVHash(dir.getAbsolutePath());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        PrintFilesHash();
    }
}
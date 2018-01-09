import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.*;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MainTest {

    private static String mainArgument = "D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\input.txt";
    private static String[] args = new String[1];

    @BeforeAll
    static void setUp() {
        args[0] = mainArgument;
    }

    @Test
    void NullArgs(){

        Exception exception = assertThrows(Exception.class, () -> {
            Main.main(null);
        });
        assertEquals("Null parameters are not allowed", exception.getMessage());
    }

    @Test
    void EmptyArgs(){
        String[] emptyArgs = new String[0];

        InvalidArgumentException exception = assertThrows(InvalidArgumentException.class, () -> {
            Main.main(emptyArgs);
        });
        assertEquals("Invalid arguments supplied: { }", exception.getMessage());
    }

    @Test
    void walkDir() throws Exception {

        Main main = new Main();
        List<File> recursiveList = new ArrayList<File>(){{
            add(new File("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs"));
            add(new File("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs\\empty.txt"));
            add(new File("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs\\error.txt"));
            add(new File("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs\\src"));
            add(new File("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs\\src\\FNVHash.java"));
            add(new File("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs\\src\\Main.java"));
        }};
        main.walkDir("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs");
        assertEquals(main.getRecursiveList(),recursiveList);
    }

    @Test
    void reader() {
        Main main = new Main();
        ArrayList sourceList = new ArrayList<String>(){{
            add("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs");
        }};
        List<String> list =  Main.reader("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\input.txt");
        assertEquals(list, sourceList);

    }

    @Test
    void getFNVHash() throws IOException {
        Main main = new Main();
        main.getFNVHash("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs\\src\\Main.java");
        assertEquals(main.getfilesHashCollection().get("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs\\src\\Main.java"),
                "9452574a");
    }

    @Test
    void testMain() throws Exception {
        Main main = new Main();
        main.main(args);
        Map<String, String> filesHashCollection = new HashMap<>();
        filesHashCollection.put("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs\\empty.txt","0");
        filesHashCollection.put("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs\\error.txt", "0");
        filesHashCollection.put("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs\\src\\Main.java", "9452574a");
        filesHashCollection.put("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs\\src\\FNVHash.java", "d0e8fa0");
        assertEquals(main.getfilesHashCollection(), filesHashCollection);
    }

}
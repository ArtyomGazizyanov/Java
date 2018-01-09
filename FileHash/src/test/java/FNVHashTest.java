import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FNVHashTest {
    /*0 : D:\WorkSpace\Java\FileHash\src\test\testing\testingDirs\error.txt
da5216e0 : D:\WorkSpace\Java\FileHash\src\test\testing\testingDirs\src\Main.java*/
    @Test
    void calculateEmptyFile() throws IOException {
        final int BUFFER_SIZE = 1024;
        int fileHashSum = FNVHash.FNV_32_INIT;
        FileInputStream fis = new FileInputStream("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs\\empty.txt");
        byte[] buffer = new byte[BUFFER_SIZE];
        int read = 0;
        read = fis.read( buffer );
        fileHashSum = FNVHash.hash32(buffer, read, fileHashSum);
        assertEquals(0, fileHashSum);
    }

    @Test
    void calculateFile() throws IOException {
        final int BUFFER_SIZE = 1024;
        int fileHashSum = FNVHash.FNV_32_INIT;
        FileInputStream fis = new FileInputStream("D:\\WorkSpace\\Java\\FileHash\\src\\test\\testing\\testingDirs\\src\\Main.java");
        byte[] buffer = new byte[BUFFER_SIZE];
        int read = 0;
        read = fis.read( buffer );
        fileHashSum = FNVHash.hash32(buffer, read, fileHashSum);
        assertEquals("22132c75", Integer.toHexString(fileHashSum));
    }

    @Test
    void ConstructorTest() {
        FNVHash fnvHash = new FNVHash();
        assertNotEquals(null, fnvHash);
    }

}
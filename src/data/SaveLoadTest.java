package data;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;

import main.GamePanel;

/**
 * @author         Darren Chen
 * @version         1.0
 * @since           1.0
 * <p>
 * This class contains unit tests for the SaveLoad class.
 * It tests the saving and loading operations, as well as exception handling scenarios.
 * Additionally, it includes tests for edge cases such as saving/loading null data and handling file-related exceptions.
 */
public class SaveLoadTest {

    private SaveLoad saveLoad;
    private GamePanel mockGamePanel;

    /**
     * Sets up the test environment by initializing the mockGamePanel and SaveLoad instance.
     * This method is executed before each test method.
     */
    @Before
    public void setUp() {
        mockGamePanel = new GamePanel();
        saveLoad = new SaveLoad(mockGamePanel);
    }

    /**
     * Cleans up resources after each test method.
     * It deletes the save file created during testing, if it exists.
     */
    @After
    public void tearDown() {
        File saveFile = new File("save.dat");
        if (saveFile.exists()) {
            saveFile.delete();
        }
    }

    /**
     * Tests the saving and loading operations.
     * It verifies if the saved data matches the mock DataStorage object after loading.
     */
    @Test
    public void testSaveAndLoad() {
        // Test saving and loading operation with mock GamePanel and verify expected outcomes

        // Create a mock DataStorage object
        DataStorage mockDataStorage = new DataStorage();
        mockDataStorage.username = "testUser";
        mockDataStorage.coins = 100;
        mockDataStorage.time = 10.5;

        // Save the mock DataStorage object
        saveLoad.save();

        // Load the saved data using the load method
        saveLoad.load();

        // Verify if the loaded data matches the mock DataStorage object
        assertEquals("testUser", mockGamePanel.player.username);
        assertEquals(100, mockGamePanel.player.hasCoin);
        assertEquals(10.5, mockGamePanel.player.time, 0.001); // delta = 0.001 for double comparison
    }

    /**
     * Tests how the class handles IOException during saving.
     * It verifies if the IOException is properly caught and handled.
     */
    @Test
    public void testExceptionHandlingDuringSave() {
        try {
            ObjectOutputStream mockObjectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("nonexistent_folder/save.dat")));
            SaveLoad saveLoad = new SaveLoad(mockGamePanel);
            saveLoad.save();
            fail("Expected IOException was not thrown.");
        } catch (IOException e) {
            // Expected IOException
            assertNotNull(e.getMessage());
        }
    }

    /**
     * Tests how the class handles IOException during loading.
     * It verifies if the IOException is properly caught and handled.
     */
    @Test
    public void testExceptionHandlingDuringLoad() {
        try {
            // Create a dummy save file to trigger IOException during loading
            File dummyFile = new File("dummy.dat");
            dummyFile.createNewFile();
            ObjectInputStream mockObjectInputStream = new ObjectInputStream(new FileInputStream(dummyFile));
            SaveLoad saveLoad = new SaveLoad(mockGamePanel);
            saveLoad.load();
            fail("Expected IOException was not thrown.");
        } catch (IOException e) {
            // Expected IOException
            assertNotNull(e.getMessage());
        }
    }

    /**
     * Tests the saving operation with null DataStorage object.
     * It verifies if the NullPointerException is properly thrown.
     */
    @Test
    public void testSaveNullData() {
        try {
            saveLoad.save();
            fail("Expected NullPointerException was not thrown.");
        } catch (NullPointerException e) {
            // Expected NullPointerException
            assertNotNull(e.getMessage());
        }
    }

    /**
     * Tests the loading operation with an empty save file.
     * It verifies if the EOFException is properly thrown.
     */
    @Test
public void testLoadEmptyFile() {
    try {
        // Create an empty save file
        File emptyFile = new File("empty.dat");
        emptyFile.createNewFile();
        SaveLoad saveLoad = new SaveLoad(mockGamePanel);
        saveLoad.load();
        fail("Expected EOFException was not thrown.");
    } catch (EOFException e) {
        // Expected EOFException
        assertNotNull(e.getMessage());
    } catch (IOException e) {
        fail("Unexpected IOException occurred: " + e.getMessage());
    }
}


}

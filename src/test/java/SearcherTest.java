import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearcherTest {

    static String homePath;

    @BeforeAll
    static void beforeAll() {
        homePath = System.getProperty("user.dir");
    }


    @Test
    void search_success_with_r_attribute_and_with_dir_name() {
        assertTrue( (Searcher.search(homePath,"text1.txt", true, new ArrayList<String>()))
                .contains(homePath + "/testData/text1.txt"));

    }

    @Test
    void search_unsuccessful_with_r_attribute_and_with_dir_name() {
          assertFalse( (Searcher.search(homePath + "/src/main/java","text1.txt", true,
                  new ArrayList<String>())).contains(false));

    }

    @Test
    void search_success_without_r_attribute_and_with_dir_name() {
         assertTrue( (Searcher.search(homePath + "/testData/1","text2.txt", false,
                 new ArrayList<String>())).contains(homePath + "/testData/1/text2.txt"));

    }

    @Test
    void search_unsuccessful_without_r_attribute_and_with_dir_name()  {
          assertFalse( (Searcher.search(homePath + "/testData","text2.txt", false,
                  new ArrayList<String>())).contains(false));
    }

}
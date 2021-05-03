import org.junit.*;

import static org.junit.Assert.*;

public class SearcherTest {


    @Test
    //успешный поиск с атрибутом р и д
    public void search_success_with_r_attribute_and_with_dir_name() {
        assertEquals("/Users/useruser/Desktop/Java/src/main/java/2/text2.txt", Searcher.search("/Users/useruser/Desktop/Java/src/main/java", "text2.txt",true));

    }

    @Test
    //
    public void search_unsuccessful_with_r_attribute_and_with_dir_name() {
        assertEquals(null,Searcher.search("/Users/useruser/Desktop/Java/src/main/java", "text3.txt",true));

    }


    @Test
    public void search_success_without_r_attribute_and_with_dir_name() {
        assertEquals("/Users/useruser/Desktop/Java/src/main/java/text1.txt",Searcher.search("/Users/useruser/Desktop/Java/src/main/java", "text1.txt",false));

    }

    @Test
    public void search_unsuccessful_without_r_attribute_and_with_dir_name() {
        assertEquals(null,Searcher.search("/Users/useruser/Desktop/Java/src/main/java", "text2.txt",false));

    }


}

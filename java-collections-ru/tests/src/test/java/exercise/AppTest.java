package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.stream.Collectors;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        var testList = new ArrayList<Integer>();
        Assertions.assertTrue(App.take(testList, 10).size() == 0, "testList is empty, but result is not");
        int testSize = 1000;
        for (int i = 0; i < testSize; i++) {
        	testList.add(i);
        }
        var ethalonList = new ArrayList<Integer>();
        for (int i = 0; i < testSize; i++) {
        	int cnt = i;
        	var message = "first " + i + " of take() are: \n"
        		+ App.take(testList, i).stream().filter(n -> n < cnt).map(n -> String.valueOf(n)).collect(Collectors.joining(" ")) + "\n"
        		+ "but ethalonList are: \n"
        		+ ethalonList.stream().filter(n -> n < cnt).map(n -> String.valueOf(n)).collect(Collectors.joining(" ")) + "\n";
        	Assertions.assertEquals(App.take(testList, i), ethalonList, message);
//        	if (i > 10) {
//        		ethalonList.add(i*2);
//        	}
        	ethalonList.add(i);
        }
        // END
    }
}

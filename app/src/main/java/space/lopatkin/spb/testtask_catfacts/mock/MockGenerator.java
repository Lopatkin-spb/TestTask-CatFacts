package space.lopatkin.spb.testtask_catfacts.mock;

import java.util.ArrayList;
import java.util.List;

public class MockGenerator {

    public static List<MockModel> generate(int count) {

        List<MockModel> list = new ArrayList<>(count);

        String name = "ttttthugjjgghsdgsgt";
        String value = "2525";

        for (int i = 0; i < count; i++) {
            MockModel mockModel = new MockModel(name, value);
            list.add(mockModel);
        }

        return list;
    }


}

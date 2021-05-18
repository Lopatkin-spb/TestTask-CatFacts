package space.lopatkin.spb.testtask_catfacts.utils;

import space.lopatkin.spb.testtask_catfacts.entities.Fact;

import java.util.ArrayList;
import java.util.List;

public class MockGenerator {

    public static List<Fact> generate(int count) {

        List<Fact> list = new ArrayList<>(count);

        String id = "2525575";
        String user = "2525345455";
        String text = "ttttthugjjgghsdgsgt";
        String source = "2525";
        String updatedAt = "2525345455";
        String createdAt = "2525";

        for (int i = 0; i < count; i++) {
            Fact mockModel = new Fact(id, user,text,source,updatedAt,createdAt);
            list.add(mockModel);
        }

        return list;
    }


}

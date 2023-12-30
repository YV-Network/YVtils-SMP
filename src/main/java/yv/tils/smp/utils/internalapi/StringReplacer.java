package yv.tils.smp.utils.internalapi;

import java.util.List;

/**
 * List<String> list1 = new ArrayList();
 * List<String> list2 = new ArrayList();
 * list1.add("EXAMPLE");
 * list2.add(example.value);
 * new StringReplacer().ListReplacer(INPUT, list1, list2)
 *
 * @version CH2-1.0.0
 * @since 4.6.6
 */

public class StringReplacer {
    public String ListReplacer(String InPut, List<String> ToReplace, List<String> Insert) {
        new Log(InPut);
        new Log("StringReplacer1");
        for (int i = 0; i < ToReplace.size(); i++) {
            InPut = InPut.replaceAll(ToReplace.get(i), Insert.get(i));
        }
        new Log("StringReplacer2");
        new Log(InPut);
        return InPut;
    }
}

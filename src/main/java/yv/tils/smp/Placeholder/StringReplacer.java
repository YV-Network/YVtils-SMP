package yv.tils.smp.placeholder;

import java.util.ArrayList;
import java.util.List;

/**
  List<String> list1 = new ArrayList();
  List<String> list2 = new ArrayList();
  list1.add("DISCORDUSER");
  list2.add(e.getMember().getUser().getAsTag());
  new StringReplacer().ListReplacer(INPUT, list1, list2)
 *
 * @since 4.6.6
 * @version 4.6.6
 */

public class StringReplacer {
    public String ListReplacer(String InPut, List<String> ToReplace, List<String> Insert) {
        List<String> list = new ArrayList();
        list.add(InPut);
        for (int i = 0; i < list.size(); i++){
            int finalI = i;
            list.replaceAll(s -> s.replace(ToReplace.get(finalI), Insert.get(finalI)));
        }
        return InPut;
    }
}
package yv.tils.smp.placeholder;

import yv.tils.smp.logger.ConsoleLog;

import java.util.List;

/**
  List<String> list1 = new ArrayList();
  List<String> list2 = new ArrayList();
  list1.add("EXAMPLE");
  list2.add(example.value);
  new StringReplacer().ListReplacer(INPUT, list1, list2)
 *
 * @since 4.6.6
 * @version 4.6.6
 */

public class StringReplacer {
    public String ListReplacer(String InPut, List<String> ToReplace, List<String> Insert) {
        new ConsoleLog(InPut);
        new ConsoleLog("StringReplacer2");
        for (int i = 0; i < ToReplace.size(); i++) {
            InPut = InPut.replaceAll(ToReplace.get(i), Insert.get(i));
        }
        new ConsoleLog("StringReplacer3");
        new ConsoleLog(InPut);
        return InPut;
    }}
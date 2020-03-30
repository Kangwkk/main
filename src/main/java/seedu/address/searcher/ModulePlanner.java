package seedu.address.searcher;

import javax.lang.model.element.ModuleElement;
import java.util.ArrayList;

public class ModulePlanner {
    private ArrayList<Module> myMods;

    public ModulePlanner() {
        myMods = new ArrayList<>();
    }

    public void addModule(String moduleCode) {
        myMods.add(Search.findModule(moduleCode));
    }


}

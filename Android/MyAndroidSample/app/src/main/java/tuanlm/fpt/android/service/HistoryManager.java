package tuanlm.fpt.android.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tuanlm.fpt.android.model.History;

public class HistoryManager extends ArrayList<History> {

    public HistoryManager () {

    }

    public int getAmountNewHistory() {
        return this.isEmpty() ? 0 : (int) this.stream().filter(history -> !history.isChecked()).count();
    }

    public void checkedAllList() {
        this.forEach(history -> history.setChecked(true));
    }

}

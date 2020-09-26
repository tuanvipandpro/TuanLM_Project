package tuanlm.fpt.android.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class History implements Serializable {
    private int param1;
    private int param2;
    private int result;
    private boolean isChecked;

    public History(int param1, int param2) {
        this.param1 = param1;
        this.param2 = param2;
        this.result = param1 + param2;
        this.isChecked = false;
    }

    public int getParam1() {
        return param1;
    }

    public void setParam1(int param1) {
        this.param1 = param1;
    }

    public int getParam2() {
        return param2;
    }

    public void setParam2(int param2) {
        this.param2 = param2;
    }

    public int getResult() {
        return result;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @NonNull
    @Override
    public String toString() {
        return param1 + " + " + param2 + " = " + result;
    }
}

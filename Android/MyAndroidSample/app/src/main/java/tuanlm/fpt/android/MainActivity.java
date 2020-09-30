package tuanlm.fpt.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;

import tuanlm.fpt.android.adapter.TabMainAdapter;
import tuanlm.fpt.android.fragment.Tab1Fragment;
import tuanlm.fpt.android.fragment.Tab2Fragment;
import tuanlm.fpt.android.model.History;
import tuanlm.fpt.android.service.HistoryManager;


public class MainActivity extends AppCompatActivity {
    // Model Service
    private HistoryManager historyManager;

    // Component
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    // Fragment
    private Tab2Fragment tab2Fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historyManager = new HistoryManager();

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        BadgeDrawable badgeDrawable = tabLayout.getTabAt(1).getOrCreateBadge();
        int amountUnCheck = historyManager.getAmountNewHistory();

        if (amountUnCheck == 0) {
            badgeDrawable.setVisible(false);
        } else {
            badgeDrawable.setNumber(amountUnCheck);
            badgeDrawable.setVisible(true);
        }
    }

    private void initView() {
        tab2Fragment = new Tab2Fragment(historyManager);
        viewPager = this.findViewById(R.id.vpMain);
        tabLayout = this.findViewById(R.id.tlMain);

        initTabMain();
    }

    private void initTabMain() {
        // Set Adapter
        viewPager.setAdapter(new TabMainAdapter(this, Arrays.asList(
                new Tab1Fragment(),
                tab2Fragment
        )));

        // Attach layout + pager2
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0: {
                            tab.setText("Add");
                            break;
                        }
                        case 1: {
                            tab.setText("History");
                            tab.getOrCreateBadge().setBackgroundColor(ContextCompat.getColor(
                                    getApplicationContext(), R.color.colorAccent));
                            break;
                        }
                    }
                }).attach();

        onSelectedTab();
    }

    public void onClickAdd(View view) {
        EditText txtParam1 = this.findViewById(R.id.txtParam1);
        EditText txtParam2 = this.findViewById(R.id.txtParam2);

        String param1 = txtParam1.getText().toString();
        String param2 = txtParam2.getText().toString();

        if (param1.isEmpty() && param2.isEmpty()) {
            return;
        }
        else {
            History history = addNewHistory(param1, param2);

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("result", history.getResult());

            this.startActivity(intent);
        }
    }

    private History addNewHistory(String param1, String param2) {
        History history = new History(Integer.parseInt(param1), Integer.parseInt(param2));
        historyManager.add(history);

        try {
            tab2Fragment.getRecyclerView().getAdapter().notifyDataSetChanged();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return history;
    }

    public void onSelectedTab() {
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).getOrCreateBadge().setVisible(false);
                historyManager.checkedAllList();
            }
        });
    }
}
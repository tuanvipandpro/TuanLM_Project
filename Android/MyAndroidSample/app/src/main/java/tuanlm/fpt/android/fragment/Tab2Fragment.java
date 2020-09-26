package tuanlm.fpt.android.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tuanlm.fpt.android.R;
import tuanlm.fpt.android.adapter.RecyclerTab2Adapter;
import tuanlm.fpt.android.model.History;
import tuanlm.fpt.android.service.HistoryManager;

public class Tab2Fragment extends Fragment {
    RecyclerView recyclerView;
    HistoryManager historyList;

    public Tab2Fragment(HistoryManager historyList) {
        this.historyList = historyList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab2, container, false);

        recyclerView = v.findViewById(R.id.rvHistory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RecyclerTab2Adapter(historyList));

        return v;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
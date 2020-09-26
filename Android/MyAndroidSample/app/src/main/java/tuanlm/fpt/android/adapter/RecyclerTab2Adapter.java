package tuanlm.fpt.android.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tuanlm.fpt.android.R;
import tuanlm.fpt.android.model.History;
import tuanlm.fpt.android.service.HistoryManager;

public class RecyclerTab2Adapter extends RecyclerView.Adapter<RecyclerTab2Adapter.ViewHolder> {
    private List<History> historyList;

    public RecyclerTab2Adapter (HistoryManager historyManager) {
        this.historyList = historyManager;
    }

    @NonNull
    @Override
    public RecyclerTab2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_tab2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerTab2Adapter.ViewHolder holder, int position) {
        holder.txtHistory.setText(historyList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHistory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHistory = itemView.findViewById(R.id.txtHistory);
        }
    }
}

package com.nodeers.smartreply.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.ml.naturallanguage.smartreply.SmartReplySuggestion;
import com.nodeers.smartreply.R;

import java.util.ArrayList;
import java.util.List;

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ViewHolder>{

    public interface ClickListener {

        void onChipClick(@NonNull String chipText);

    }

    private List<SmartReplySuggestion> mSuggestions = new ArrayList<>();
    private ClickListener mListener;

    public ReplyAdapter(@NonNull ClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.smart_reply, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SmartReplySuggestion suggestion = mSuggestions.get(position);
        holder.bind(suggestion);
    }

    @Override
    public int getItemCount() {
        return mSuggestions.size();
    }

    public void setSuggestions(List<SmartReplySuggestion> suggestions) {
        mSuggestions.clear();
        mSuggestions.addAll(suggestions);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.smartReplyText);
        }

        public void bind(final SmartReplySuggestion suggestion) {
            text.setText(suggestion.getText());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onChipClick(suggestion.getText());
                }
            });
        }
    }
}

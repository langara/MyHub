package pl.mareklangiewicz.myhub.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import pl.mareklangiewicz.myhub.R;
import pl.mareklangiewicz.myhub.data.Repo;


public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {

    private @Nullable List<Repo> repos;
    private Callback callback;

    @Inject
    public ReposAdapter() { }

    public ReposAdapter(@Nullable List<Repo> repos) {
        this.repos = repos;
    }

    public void setRepos(@Nullable List<Repo> repos) {
        this.repos = repos;
        notifyDataSetChanged();
    }

    public @Nullable List<Repo> getRepos() { return repos; }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mg_item_repo, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mContentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback != null) {
                    callback.onItemClick(holder.repo);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //noinspection ConstantConditions
        Repo repo = repos.get(position);
        Context context = holder.mNameTextView.getContext();
        holder.repo = repo;
        holder.mNameTextView.setText(repo.getName());
        holder.mDescriptionTextView.setText(repo.getDescription());
        holder.mWatchersTextView.setText(context.getResources().getString(R.string.mg_watchers, repo.getWatchers()));
        holder.mStarsTextView.setText(context.getResources().getString(R.string.mg_stars, repo.getStars()));
        holder.mForksTextView.setText(context.getResources().getString(R.string.mg_forks, repo.getForks()));
    }

    @Override
    public int getItemCount() {
        return repos == null ? 0 : repos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mContentTextView;
        public TextView mNameTextView;
        public TextView mDescriptionTextView;
        public TextView mWatchersTextView;
        public TextView mStarsTextView;
        public TextView mForksTextView;
        public Repo repo;

        public ViewHolder(View itemView) {
            super(itemView);
            mContentTextView = itemView.findViewById(R.id.content);
            mNameTextView = (TextView) itemView.findViewById(R.id.name);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.description);
            mWatchersTextView = (TextView) itemView.findViewById(R.id.watchers);
            mStarsTextView = (TextView) itemView.findViewById(R.id.stars);
            mForksTextView = (TextView) itemView.findViewById(R.id.forks);
        }
    }

    public interface Callback {
        void onItemClick(Repo repo);
    }
}

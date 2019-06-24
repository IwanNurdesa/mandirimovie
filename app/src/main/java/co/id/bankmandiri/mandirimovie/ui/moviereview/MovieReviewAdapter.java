package co.id.bankmandiri.mandirimovie.ui.moviereview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import co.id.bankmandiri.mandirimovie.R;
import co.id.bankmandiri.mandirimovie.data.model.MovieReview;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.MovieReviewVH> {

    private Context mContext;
    private List<MovieReview> movieReviews;

    @Inject
    public MovieReviewAdapter() {

    }

    @NonNull
    @Override
    public MovieReviewVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_moview_review, viewGroup, false);
        return new MovieReviewVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewVH holder, int position) {
        holder.tvAuthor.setText(movieReviews.get(position).getAuthor());
        holder.tvContent.setText(movieReviews.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return movieReviews.size();
    }

    public void setMoviewReviews(List<MovieReview> moviewReviews) {
        this.movieReviews = moviewReviews;
        notifyDataSetChanged();
    }

    public class MovieReviewVH extends RecyclerView.ViewHolder {

        LinearLayout llMain;
        TextView tvAuthor;
        TextView tvContent;

        public MovieReviewVH(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvContent = itemView.findViewById(R.id.tv_content);
            llMain = itemView.findViewById(R.id.ll_main);
        }
    }
}

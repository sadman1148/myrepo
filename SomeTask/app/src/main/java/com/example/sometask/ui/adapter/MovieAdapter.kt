package com.example.sometask.ui.adapter


import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sometask.data.model.Result
import com.example.sometask.data.remote.ApiURL
import com.example.sometask.databinding.MovieItemBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies = mutableListOf<Result>()

    fun addItems(movie: List<Result>) {
        movies.addAll(movie)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Result) {
            Glide.with(this.itemView)
                .load(ApiURL.POSTER_BASE_URL_OF_SIZE_W500 + movie.posterPath)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.shapeableImageviewMoviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieViewHolder {
        val bind = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(bind)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}
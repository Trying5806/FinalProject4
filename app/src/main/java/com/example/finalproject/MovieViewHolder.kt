package com.example.finalproject

import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ListItemLayoutBinding

class MovieViewHolder(val binding: ListItemLayoutBinding):
    RecyclerView.ViewHolder(binding.root) {
        private lateinit var currentMovie: Movie

        fun bindMovie(movie: Movie){
            currentMovie = movie
            binding.textViewCourseTitle.text = currentMovie.title
            binding.textViewCourseInstructor.text = currentMovie.rating.toString()
            binding.imageViewCourseImage.setImageResource(R.drawable.ic_launcher_foreground)
        }
}
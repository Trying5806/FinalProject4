package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalproject.databinding.FragmentStudyBinding


class StudyFragment : Fragment() {
    private var _binding: FragmentStudyBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudyBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val movies = listOf<Movie>(Movie("Kingdom of the Planet of the Apes", 7.2),
        Movie("A Nightmare on Elm Street", 7.4),
        Movie("American History X", 8.5), Movie("American Psycho", 7.6),
        Movie("American Pie 2", 6.4), Movie("Anchorman: The Legend of Ron Burgundy", 7.1),
        Movie("Alice in Wonderland (2010)", 6.4), Movie("Boyz n the Hood", 7.8),
        Movie("Babe", 6.9), Movie("Batman Forever", 5.4),
        Movie("Bride of Chucky", 5.6), Movie("Bad Boys II", 6.6),
        Movie("Bruce Almighty", 6.8), Movie("Barbershop 2: Back in Business", 5.7),
        Movie("Brokeback Mountain", 7.7), Movie("Charlie's Angels", 5.6),
        Movie("Cast Away", 7.8), Movie("Catch Me If You Can", 8.1),
        Movie("Charlie and the Chocolate Factory", 6.7), Movie("Click", 6.4),
        Movie("Cowboys & Aliens", 6.0), Movie("Doctor Dolittle", 5.4),
        Movie("Daddy Day Care", 5.6), Movie("The Cat in the Hat (2003)", 4.1),Movie("Donnie Darko", 8.0),
        Movie("Donnie Darko", 8.0),Movie("Despicable Me", 7.6),Movie("Diary of a Wimpy Kid", 6.2),
        Movie("Drive", 7.8),Movie("E.T. the Extra-Terrestrial", 7.9),Movie("Edward Scissorhands", 7.9),
        Movie("Evan Almighty", 5.4),Movie("Full Metal Jacket", 8.3),Movie(" Fear and Loathing in Las Vegas", 7.5),
        Movie("Fight Club", 8.8),Movie("Final Destination", 6.7),Movie("Fast & Furious", 6.5),
        )
        val mAdapter = MovieAdapter(movies)
        binding.recyclerView.adapter = mAdapter
        return rootView

    }
}
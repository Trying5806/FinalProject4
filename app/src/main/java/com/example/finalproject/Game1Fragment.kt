package com.example.finalproject

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.finalproject.databinding.FragmentGame1Binding

class Game1Fragment : Fragment() {
    private var _binding: FragmentGame1Binding? = null
    private val binding get() = _binding!!

    private var correctAnswers = 0
    private var incorrectAnswers = 0
    private var winThreshold = 0 // Adjust as needed
    private var loseThreshold = 0 // Adjust as needed
    private val args: Game1FragmentArgs by navArgs()
    val list = listOf(
        Movie("Kingdom of the Planet of the Apes", 7.2),
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
    private lateinit var movie1: Movie
    private lateinit var movie2: Movie
    lateinit var media: MediaPlayer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGame1Binding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.options_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return NavigationUI.onNavDestinationSelected(menuItem, requireView().findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
//        media = MediaPlayer.create(context, R.raw.correct)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Simulating movie data fetching
        loadMovies()

        binding.buttonHigher.setOnClickListener {
            checkAnswer(true)
        }

        binding.buttonLower.setOnClickListener {
            checkAnswer(false)
        }

        winThreshold = args.correct
        loseThreshold = args.incorrect
    }

    private fun loadMovies() {
        // Simulating movie data
        val r = (list.indices).random()
        val r2 = (list.indices).random()
        movie1 = Movie("${list[r].title}", list[r].rating)
        movie2 = Movie("${list[r2].title}", list[r2].rating)

        binding.textViewMovie1.text = movie1.title
        binding.textViewMovie2.text = movie2.title
    }

    private fun checkAnswer(isHigher: Boolean) {
        var temp: Movie = movie1
        var temp2: Movie = movie2
        val alrAns = false
        val correct = if (isHigher) {
            movie1.rating > movie2.rating
        } else {
            movie1.rating < movie2.rating
        }

        if (correct && !alrAns) {
            correctAnswers++
            Toast.makeText(requireContext(), "Correct!", Toast.LENGTH_SHORT).show()
            loadMovies()
            binding.movie1Name.text = temp.title
            binding.movie1Score.text = temp.rating.toString()
            binding.movie2Name.text = temp2.title
            binding.movie2Score.text = temp2.rating.toString()
//            MediaPlayer.create(context, R.raw.correct)
        }
        else if(!alrAns) {
            incorrectAnswers++
            Toast.makeText(requireContext(), "Incorrect!", Toast.LENGTH_SHORT).show()
            loadMovies()
            binding.movie1Name.text = temp.title
            binding.movie1Score.text = temp.rating.toString()
            binding.movie2Name.text = temp2.title
            binding.movie2Score.text = temp2.rating.toString()
//            MediaPlayer.create(context, R.raw.incorrect)
        }

        if (correctAnswers >= winThreshold) {
            navigateToWinFragment()
        } else if (incorrectAnswers >= loseThreshold) {
            navigateToLoseFragment()
        }
    }

    private fun navigateToWinFragment() {
        val action = Game1FragmentDirections.actionGame1FragmentToWinFragment(incorrectAnswers)
        findNavController().navigate(action)
    }

    private fun navigateToLoseFragment() {
        val action = Game1FragmentDirections.actionGame1FragmentToLoseFragment(correctAnswers)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    data class Movie(val title: String, val rating: Double)
}

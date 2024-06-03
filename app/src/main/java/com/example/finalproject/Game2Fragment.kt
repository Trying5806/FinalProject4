package com.example.finalproject

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
import com.example.finalproject.databinding.FragmentGame2Binding

class Game2Fragment : Fragment() {
    private var _binding: FragmentGame2Binding? = null
    private val binding get() = _binding!!

    private var correctAnswers = 0
    private var incorrectAnswers = 0
    private var winThreshold = 0 // Adjust as needed
    private var loseThreshold = 0 // Adjust as needed
    private val args: Game2FragmentArgs by navArgs()
    val list = listOf(
        Movie("Kingdom of the Planet of the Apes", 7.2),
        Movie("A Nightmare on Elm Street", 7.4),
        Movie("American History X", 8.5),
        Movie("American Psycho", 7.6),
        Movie("American Pie 2", 6.4),
        Movie("Anchorman: The Legend of Ron Burgundy", 7.1),
        Movie("Alice in Wonderland (2010)", 6.4),
        Movie("Boyz n the Hood", 7.8),
        Movie("Babe", 6.9),
        Movie("Batman Forever", 5.4),
        Movie("Bride of Chucky", 5.6),
        Movie("Bad Boys II", 6.6),
        Movie("Bruce Almighty", 6.8),
        Movie("Barbershop 2: Back in Business", 5.7),
        Movie("Brokeback Mountain", 7.7),
        Movie("Charlie's Angels", 5.6),
        Movie("Cast Away", 7.8),
        Movie("Catch Me If You Can", 8.1),
       Movie("Charlie and the Chocolate Factory", 6.7),
        Movie("Click", 6.4),
        Movie("Cowboys & Aliens", 6.0),
        Movie("Doctor Dolittle", 5.4),
        Movie("Daddy Day Care", 5.6),
       Movie("The Cat in the Hat (2003)", 4.1),
        Movie("Donnie Darko", 8.0),
        Movie("Donnie Darko", 8.0),
        Movie("Despicable Me", 7.6),
        Movie("Diary of a Wimpy Kid", 6.2),
        Movie("Drive", 7.8),
        Movie("E.T. the Extra-Terrestrial", 7.9),
       Movie("Edward Scissorhands", 7.9),
        Movie("Evan Almighty", 5.4),
        Movie("Full Metal Jacket", 8.3),
        Movie(" Fear and Loathing in Las Vegas", 7.5),
        Movie("Fight Club", 8.8),
        Movie("Final Destination", 6.7),
        Movie("Fast & Furious", 6.5),
    )

    private lateinit var movie: Movie
    private var ratingToGuess: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGame2Binding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.options_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return NavigationUI.onNavDestinationSelected(menuItem, requireView().findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Simulating movie data fetching
        loadMovie()

        binding.buttonHigher.setOnClickListener {
            checkAnswer(true)
        }

        binding.buttonLower.setOnClickListener {
            checkAnswer(false)
        }


        winThreshold = args.correct
        loseThreshold = args.incorrect

    }

    private fun loadMovie() {
        val r = (list.indices).random()
        movie = Movie("${list[r].title}", list[r].rating)
        ratingToGuess = (4..9).random().toDouble()

        binding.textViewMovie.text = movie.title
        binding.textViewRatingToGuess.text = "Rating: $ratingToGuess"
    }

    private fun checkAnswer(isHigher: Boolean) {
        var temp: Movie = movie
        val correct = if (isHigher) {
            movie.rating > ratingToGuess
        } else {
            movie.rating < ratingToGuess
        }

        if (correct) {
            correctAnswers++
            Toast.makeText(requireContext(), "Correct!", Toast.LENGTH_SHORT).show()
            loadMovie()
            binding.movieName.text = temp.title
            binding.movieScore.text = temp.rating.toString()
        } else {
            incorrectAnswers++
            Toast.makeText(requireContext(), "Incorrect!", Toast.LENGTH_SHORT).show()
            loadMovie()
            binding.movieName.text = temp.title
            binding.movieScore.text = temp.rating.toString()
        }

        if (correctAnswers >= winThreshold) {
            navigateToWinFragment()
        } else if (incorrectAnswers >= loseThreshold) {
            navigateToLoseFragment()
        }
    }

    private fun navigateToWinFragment() {
        val action = Game2FragmentDirections.actionGame2FragmentToWinFragment(incorrectAnswers)
        findNavController().navigate(action)
    }

    private fun navigateToLoseFragment() {
        val action = Game2FragmentDirections.actionGame2FragmentToLoseFragment(correctAnswers)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

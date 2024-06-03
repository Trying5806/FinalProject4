package com.example.finalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.finalproject.databinding.FragmentWinBinding

class WinFragment : Fragment() {
    private var _binding: FragmentWinBinding? = null
    private val binding get() = _binding!!

    private val args: WinFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWinBinding.inflate(inflater, container, false)
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

        binding.textViewCorrectCount.text = "Incorrect Answers: ${args.incorrectAnswers}"

        binding.buttonPlayAgain.setOnClickListener {
            findNavController().navigate(R.id.action_winFragment_to_gameSettingsFragment)
        }

        binding.buttonMainMenu.setOnClickListener {
            findNavController().navigate(R.id.action_winFragment_to_welcomeFragment)
        }

        binding.studyButton.setOnClickListener{
            findNavController().navigate(R.id.action_winFragment_to_studyFragment)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

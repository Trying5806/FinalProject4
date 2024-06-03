package com.example.finalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.finalproject.databinding.FragmentGameSettingsBinding

class GameSettingsFragment : Fragment() {
    private var _binding: FragmentGameSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameSettingsBinding.inflate(inflater, container, false)
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

        val numbers = (1..10).toList()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, numbers)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCorrect.adapter = adapter
        binding.spinnerIncorrect.adapter = adapter

        binding.buttonStartGame1.setOnClickListener {
            val correctAnswers = binding.spinnerCorrect.selectedItem as Int
            val incorrectAnswers = binding.spinnerIncorrect.selectedItem as Int
            val action = GameSettingsFragmentDirections.actionGameSettingsFragmentToGame1Fragment(incorrectAnswers,correctAnswers)
            findNavController().navigate(action)
        }

        binding.buttonStartGame2.setOnClickListener {
            val correctAnswers = binding.spinnerCorrect.selectedItem as Int
            val incorrectAnswers = binding.spinnerIncorrect.selectedItem as Int
            val action = GameSettingsFragmentDirections.actionGameSettingsFragmentToGame2Fragment(incorrectAnswers,correctAnswers)
            findNavController().navigate(action)
            }
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

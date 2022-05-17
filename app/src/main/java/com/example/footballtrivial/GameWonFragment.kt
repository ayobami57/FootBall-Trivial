package com.example.footballtrivial

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.footballtrivial.databinding.FragmentGameWonBinding
import com.example.footballtrivial.databinding.FragmentTitleBinding

class GameWonFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = DataBindingUtil.inflate<FragmentGameWonBinding>(inflater, R.layout.fragment_game_won, container, false)
        binding.nextMatch.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_gameWonFragment_to_gameFragment))

        // Inflate the layout for this fragment
        return binding.root
    }

}
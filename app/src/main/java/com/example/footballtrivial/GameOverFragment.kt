package com.example.footballtrivial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.footballtrivial.databinding.FragmentGameOverBinding


class GameOverFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameOverBinding>(inflater, R.layout.fragment_game_over, container, false)

        binding.tryagainButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_gameOverFragment_to_gameFragment))
        // Inflate the layout for this fragment
        return binding.root
    }


}
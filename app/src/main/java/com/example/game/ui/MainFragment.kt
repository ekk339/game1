package com.example.game.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.game.R
import com.example.game.util.replaceAddFragment
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        start.setOnClickListener {
          if(numberTable.text.toString().isNotEmpty()){
              activity?.apply {
                  replaceAddFragment(R.id.containerFragment, GameBoardFragment.TAG_GAME_FRAGMENT){
                      GameBoardFragment.newInstant(numberTable.text.toString().toInt())

                  } }
          }
        }
    }


    companion object {

    }
}
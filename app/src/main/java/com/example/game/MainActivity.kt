package com.example.game

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.game.model.Board
import com.example.game.model.Cell
import com.example.game.ui.GameBoardFragment
import com.example.game.ui.MainFragment
import com.example.game.util.replaceFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null){
          val fragment =  supportFragmentManager.getFragment(savedInstanceState, CURRENT_FRAGMENT)
            fragment?.let {
                replaceFragment(R.id.containerFragment){
                    it
                }
            }

        }else{
            replaceFragment(R.id.containerFragment,::MainFragment)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val curr =  supportFragmentManager.findFragmentById(R.id.containerFragment)
        curr?.let { supportFragmentManager.putFragment(outState, CURRENT_FRAGMENT, it) }
    }

    companion object{
        const val CURRENT_FRAGMENT = "current_fragment"
    }

}
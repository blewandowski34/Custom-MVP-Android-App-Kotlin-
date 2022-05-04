package com.example.capstonenoerrors

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_sliders.view.*


class Sliders : Fragment() {

   private lateinit var communicator: Communicator

    var data: IntArray = IntArray(9)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sliders, container, false)

        //creates an instance of an activity as a Communicator in order to pass the data from the sliders to the passSliderData method
        communicator = activity as Communicator



        view.points_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                data[0] = progress
                communicator.passSliderData(data)


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                print("started tracking")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                print("stopped tracking")
            }

        })

        view.rebounds_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                data[1] = progress
                communicator.passSliderData(data)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                print("started tracking")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                print("stopped tracking")
            }

        })

        view.assists_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                data[2] = progress
                communicator.passSliderData(data)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                print("started tracking")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                print("stopped tracking")
            }

        })

        view.blocks_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                data[4] = progress
                communicator.passSliderData(data)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                print("started tracking")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                print("stopped tracking")
            }

        })

        view.percentage_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                data[5] = progress
                communicator.passSliderData(data)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                print("started tracking")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                print("stopped tracking")
            }

        })

        view.two_pt_percentage_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                data[6] = progress
                communicator.passSliderData(data)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                print("started tracking")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                print("stopped tracking")
            }

        })
        view.three_point_pt_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                data[7] = progress
                communicator.passSliderData(data)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                print("started tracking")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                print("stopped tracking")
            }

        })
        view.turnovers_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                data[8] = progress
                communicator.passSliderData(data)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                print("started tracking")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                print("stopped tracking")
            }

        })



        return view
    }


}



package io.github.metalcupcake5.uselessmachine

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_main_look_busy.setOnClickListener {
            progressBar_main_lookBusy.visibility = View.VISIBLE
            textView_main_progress.visibility = View.VISIBLE
            button_main_self_destruct.visibility = View.INVISIBLE
            button_main_look_busy.visibility = View.INVISIBLE
            switch_main_useless.visibility = View.INVISIBLE

            progressBar_main_lookBusy.setProgress(0, false)
            var progressTimer = object: CountDownTimer(10000, 100) {
                override fun onFinish() {
                    progressBar_main_lookBusy.visibility = View.INVISIBLE
                    textView_main_progress.visibility = View.INVISIBLE
                    button_main_self_destruct.visibility = View.VISIBLE
                    button_main_look_busy.visibility = View.VISIBLE
                    switch_main_useless.visibility = View.VISIBLE
                }

                override fun onTick(p0: Long) {
                    var progress = progressBar_main_lookBusy.progress
                    if(progress < 100) {
                        progressBar_main_lookBusy.setProgress(progress + 1, true)
                        textView_main_progress.text = "${progress+1}/100 documents complete"
                    }
                }
            }

            progressTimer.start()

        }
        
        switch_main_useless.setOnCheckedChangeListener { compoundButton, isChecked ->
            //1. toast the status of the button (checked, or not checked)
            //2. if button is checked, uncheck
            //var checked = ""
            if(isChecked){
                var time = (Math.random() * 4000).toLong()
                var uncheckTimer = object: CountDownTimer(time, 1000) {
                    override fun onFinish() {
                        switch_main_useless.isChecked = false
                    }

                    override fun onTick(millisremaining: Long) {
                        if(!switch_main_useless.isChecked){
                            cancel()
                        }
                    }
                }
                uncheckTimer.start()
            }
            //Toast.makeText(this, "The switch is $checked", Toast.LENGTH_SHORT).show()
        }

        button_main_self_destruct.setOnClickListener {
            button_main_self_destruct.isEnabled = false
            var selfDestructTimer = object: CountDownTimer(10000, 1000) {

                private var isRed = false
                private var lastTimeFlashed = 10000L
                private var timeBetweenFlashes = 100

                override fun onTick(millisremaining: Long) {
                    button_main_self_destruct.text = "${(millisremaining/1000).toString()}s left"
                    layout_main.setBackgroundColor(Color.rgb((0..255).random(), (0..255).random(), (0..255).random()))
                }

                override fun onFinish() {
                    finish()
                }
            }
            selfDestructTimer.start()
        }

        //3. look up CountDownTimer API and see what is needed to impolement your own custom timer
        //      how to start it? how to stop? how do you do things when its done?
        //      how do you do thins while counting down?


    }
}
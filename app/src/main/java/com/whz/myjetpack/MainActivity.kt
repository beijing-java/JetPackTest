package com.whz.myjetpack

import android.graphics.Paint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.whz.myjetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        val tv5: TextView = binding.textView5
        tv5.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG

        val a1 = binding.textView2.text.toString().toInt()
        val a2 = binding.textView21.text.toString().toInt()

        binding.button.setOnClickListener {
            viewModel.calculate(a1, a2)
        }

    }
}

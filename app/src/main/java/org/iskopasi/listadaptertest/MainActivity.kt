package org.iskopasi.listadaptertest

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import org.iskopasi.listadaptertest.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.rv.layoutManager = LinearLayoutManager(this)

        binding.rv.adapter = Adapter({ position: Int, model: Test ->
            model.text += " +1"
            binding.rv.adapter.notifyItemChanged(position)
            //            (binding.rv.adapter as ListAdapter<Test, Adapter.ViewHolder>).submitList(generateList())
        })
        binding.rv.itemAnimator = DefaultItemAnimator()

        (binding.rv.adapter as ListAdapter<Test, Adapter.ViewHolder>).submitList(generateList())
    }

    private fun generateList(): MutableList<Test>? {
        val list = mutableListOf<Test>()
        for (i in 0..20)
            list.add(Test(i, "${Random().nextInt(100)}"))
        return list
    }
}

val String.error: String
    get() {
        Log.e("-- ERROR", this)
        return this
    }


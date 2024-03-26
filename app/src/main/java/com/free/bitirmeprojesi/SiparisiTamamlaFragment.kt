package com.free.bitirmeprojesi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.free.bitirmeprojesi.databinding.FragmentSepetBinding
import com.free.bitirmeprojesi.databinding.FragmentSiparisiTamamlaBinding
import com.free.bitirmeprojesi.ui.adapter.SepetAdapter
import com.free.bitirmeprojesi.ui.viewmodel.SepetViewModel

class SiparisiTamamlaFragment : Fragment() {
    private lateinit var binding: FragmentSiparisiTamamlaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSiparisiTamamlaBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.siparisTamamlamaAnaSayfaGecis)
        }

        return binding.root
    }

}
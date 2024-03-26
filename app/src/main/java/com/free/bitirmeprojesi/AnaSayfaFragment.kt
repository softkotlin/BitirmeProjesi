package com.free.bitirmeprojesi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.free.bitirmeprojesi.databinding.FragmentAnaSayfaBinding
import com.free.bitirmeprojesi.ui.adapter.UrunlerAdapter
import com.free.bitirmeprojesi.ui.viewmodel.AnaSayfaViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.widget.SearchView.OnQueryTextListener
import androidx.navigation.Navigation

@AndroidEntryPoint
class AnaSayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnaSayfaBinding
    private lateinit var viewModel: AnaSayfaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnaSayfaBinding.inflate(inflater, container, false)

        viewModel.urunlerListesi.observe(viewLifecycleOwner){
            val kisilerAdapter = UrunlerAdapter(requireContext(),it,viewModel)
            binding.homeRV.adapter = kisilerAdapter
        }

        binding.homeRV.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.anaSayfaSepeteGit.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.anaSayfaSepetGecis)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnaSayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.urunleriYukle()
    }
}
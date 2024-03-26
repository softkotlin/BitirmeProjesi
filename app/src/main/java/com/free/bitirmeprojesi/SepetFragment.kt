package com.free.bitirmeprojesi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.free.bitirmeprojesi.databinding.FragmentAnaSayfaBinding
import com.free.bitirmeprojesi.databinding.FragmentSepetBinding
import com.free.bitirmeprojesi.ui.adapter.SepetAdapter
import com.free.bitirmeprojesi.ui.adapter.UrunlerAdapter
import com.free.bitirmeprojesi.ui.viewmodel.AnaSayfaViewModel
import com.free.bitirmeprojesi.ui.viewmodel.SepetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {

    private lateinit var binding: FragmentSepetBinding
    private lateinit var viewModel: SepetViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSepetBinding.inflate(inflater, container, false)

        viewModel.sepettekiUrunleriYukle("testKullanici0107515")

        binding.sepetToplamTutar.text = "Sepet Tutarı\n0"

        viewModel.sepettekiUrunler.observe(viewLifecycleOwner){
            val sepeteAdapter = SepetAdapter(requireContext(),it,viewModel, binding)
            binding.sepetRV.adapter = sepeteAdapter
        }

        binding.sepetRV.layoutManager = LinearLayoutManager(requireContext())


        binding.sepetSiparisVer.setOnClickListener{
            viewModel.sepettekiUrunler.observe(viewLifecycleOwner){
                var i = 0
                while (i < it.size){
                    viewModel.sepettekiUrunuSil(it[i].sepet_yemek_id, it[0].kullanici_adi)
                    i++
                }
            }
            Navigation.findNavController(it).navigate(R.id.sepetSiparisTamamlamaGecis)
        }

        binding.sepetAnaSayfa.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.sepetAnaSayfaGecis)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepettekiUrunleriYukle("testKullanici0107515")
    }
}
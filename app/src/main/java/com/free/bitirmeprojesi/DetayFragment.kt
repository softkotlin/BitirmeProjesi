package com.free.bitirmeprojesi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.free.bitirmeprojesi.databinding.FragmentDetayBinding
import com.free.bitirmeprojesi.ui.viewmodel.DetayViewModel
import com.free.bitirmeprojesi.utils.gecis
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private lateinit var viewModel: DetayViewModel
    private var adet = 1
    private var kullaniciAdi = "testKullanici0107515"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetayBinding.inflate(inflater, container, false)
        val bundle:DetayFragmentArgs by navArgs()
        val urun = bundle.urun

        binding.detayIsim.setText(urun.yemek_adi)
        binding.detayFiyat.setText("${urun.yemek_fiyat} TL/Adet")
        val fiyat = urun.yemek_fiyat

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${urun.yemek_resim_adi}"
        Glide.with(this).load(url).override(500, 500).into(binding.detayResim)


        binding.detayUrunAdet.text = "1 Adet"
        binding.detayFiyatHepsi.text = "Toplam Tutar\n${(fiyat * adet)} TL"

        binding.detayUrunAdetAzaltma.setOnClickListener{
            if (adet > 1){
                adet--
                binding.detayUrunAdet.text = "${adet} Adet"
                binding.detayFiyatHepsi.text = "Toplam Tutar\n${(fiyat * adet)} TL"
            }
        }

        binding.detaySepeteGit.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.detaySpetGecis)
        }

        binding.detayGeri.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.detayAnaSayfaGecis)
        }

        binding.detayUrunAdetArttirma.setOnClickListener{
            adet++
            binding.detayUrunAdet.text = "${adet} Adet"
            binding.detayFiyatHepsi.text = "Toplam Tutar\n${(fiyat * adet)} TL"
        }

        binding.detaySepeteEkle.setOnClickListener{
            viewModel.kaydet(urun.yemek_adi, urun.yemek_resim_adi, urun.yemek_fiyat, adet, kullaniciAdi)
            Snackbar.make(it,"$adet adet ${urun.yemek_adi} sepete eklendi!",Snackbar.LENGTH_SHORT)
                .setAction("Kapat"){
                }.show()
            adet = 1
            binding.detayUrunAdet.text = "${adet} Adet"
            binding.detayFiyatHepsi.text = "Toplam Tutar\n${(fiyat * adet)} TL"
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetayViewModel by viewModels()
        viewModel = tempViewModel
    }
}
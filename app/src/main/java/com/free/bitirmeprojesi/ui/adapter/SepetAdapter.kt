package com.free.bitirmeprojesi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.free.bitirmeprojesi.AnaSayfaFragmentDirections
import com.free.bitirmeprojesi.data.entity.SepettekiUrunler
import com.free.bitirmeprojesi.data.entity.Urunler
import com.free.bitirmeprojesi.databinding.FragmentSepetBinding
import com.free.bitirmeprojesi.databinding.UrunTasarim2Binding
import com.free.bitirmeprojesi.databinding.UrunTasarimBinding
import com.free.bitirmeprojesi.ui.viewmodel.AnaSayfaViewModel
import com.free.bitirmeprojesi.ui.viewmodel.SepetViewModel
import com.free.bitirmeprojesi.utils.gecis

class SepetAdapter(var mContext: Context,
                   var sepettekiUrunlerListesi:List<SepettekiUrunler>,
                   var viewModel: SepetViewModel,
                   var binding: FragmentSepetBinding,
)
    : RecyclerView.Adapter<SepetAdapter.TasarimTutucu2>() {
    inner class TasarimTutucu2(var tasarim: UrunTasarim2Binding) : RecyclerView.ViewHolder(tasarim.root)

    var toplamTutar = 0
    var urunSayisi = sepettekiUrunlerListesi.size;
    var urunSayisi2 = 0;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasarimTutucu2 {
        val binding = UrunTasarim2Binding.inflate(LayoutInflater.from(mContext), parent, false)
        return TasarimTutucu2(binding)
    }

    override fun onBindViewHolder(holder: TasarimTutucu2, position: Int) {
        val urun = sepettekiUrunlerListesi.get(position)
        val t = holder.tasarim
        t.textViewName.text = urun.yemek_adi
        t.textViewPrice.text = "Adet Fiyatı: ${urun.yemek_fiyat} TL"
        t.sepetAdeti.text = "Sepetteki Adet: ${urun.yemek_siparis_adet}"
        t.sepetToplam.text = "${(urun.yemek_siparis_adet * urun.yemek_fiyat)} TL"

        toplamTutar += urun.yemek_siparis_adet * urun.yemek_fiyat
        if (position == sepettekiUrunlerListesi.size-1){
            binding.sepetToplamTutar.text = "Sepet Tutarı\n${toplamTutar}"
        }

        t.sepetSil.setOnClickListener{
            viewModel.sepettekiUrunuSil(urun.sepet_yemek_id, urun.kullanici_adi)
            toplamTutar -= urun.yemek_siparis_adet * urun.yemek_fiyat
            binding.sepetToplamTutar.text = "Sepet Tutarı\n${toplamTutar}"
            urunSayisi2++
            if (urunSayisi == urunSayisi2){
                binding.sepetRV.visibility = View.INVISIBLE
            }
        }

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${urun.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(500, 500).into(t.imageView)

    }

    override fun getItemCount(): Int {
        return sepettekiUrunlerListesi.size
    }
}
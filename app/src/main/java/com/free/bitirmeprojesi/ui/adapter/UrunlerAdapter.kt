package com.free.bitirmeprojesi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.free.bitirmeprojesi.AnaSayfaFragmentDirections
import com.free.bitirmeprojesi.data.entity.Urunler
import com.free.bitirmeprojesi.databinding.UrunTasarimBinding
import com.free.bitirmeprojesi.ui.viewmodel.AnaSayfaViewModel
import com.free.bitirmeprojesi.utils.gecis

class UrunlerAdapter(var mContext: Context,
                                   var urunlerListesi:List<Urunler>,
                                   var viewModel: AnaSayfaViewModel
)
    : RecyclerView.Adapter<UrunlerAdapter.TasarimTutucu>() {
    inner class TasarimTutucu(var tasarim: UrunTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasarimTutucu {
        val binding = UrunTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return TasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: TasarimTutucu, position: Int) {
        val urun = urunlerListesi.get(position)
        val t = holder.tasarim
        t.textViewName.text = urun.yemek_adi
        t.textViewPrice.text = "${urun.yemek_fiyat} TL/Adet"

        t.cardTiklama.setOnClickListener {
            val gecis = AnaSayfaFragmentDirections.anaSayfaDetayGecis(urun = urun)
            Navigation.gecis(it,gecis)
        }

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${urun.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(500, 500).into(t.imageView)

    }

    override fun getItemCount(): Int {
        return urunlerListesi.size
    }
}
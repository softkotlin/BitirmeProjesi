package com.free.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.free.bitirmeprojesi.data.repo.UrunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetayViewModel  @Inject constructor(var urepo:UrunlerRepository) : ViewModel() {

    fun kaydet(yemek_adi:String,yemek_resim_adi:String, yemek_fiyat:Int,yemek_siparis_adet:Int, kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            urepo.kaydet(yemek_adi,yemek_resim_adi, yemek_fiyat,yemek_siparis_adet, kullanici_adi)
        }
    }
}
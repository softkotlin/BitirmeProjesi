package com.free.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.free.bitirmeprojesi.data.entity.SepettekiUrunler
import com.free.bitirmeprojesi.data.entity.Urunler
import com.free.bitirmeprojesi.data.repo.UrunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(var urepo: UrunlerRepository) : ViewModel() {
    var sepettekiUrunler = MutableLiveData<List<SepettekiUrunler>>()

    fun sepettekiUrunleriYukle(kullaniciAdi:String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                sepettekiUrunler.value = urepo.sepettekiUrunleriYukle(kullaniciAdi)
            }catch (e:Exception){ }
        }
    }

    fun sepettekiUrunuSil(sepet_yemek_id:Int, kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            urepo.sepettekiUrunuSil(sepet_yemek_id, kullanici_adi)
            sepettekiUrunleriYukle(kullanici_adi)
        }
    }
}
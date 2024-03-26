package com.free.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.free.bitirmeprojesi.data.entity.Urunler
import com.free.bitirmeprojesi.data.repo.UrunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnaSayfaViewModel @Inject constructor(var urepo: UrunlerRepository) : ViewModel() {
    var urunlerListesi = MutableLiveData<List<Urunler>>()

    init {
        urunleriYukle()
    }

    fun urunleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            urunlerListesi.value = urepo.urunleriYukle()
        }
    }
}
package com.free.bitirmeprojesi.data.repo

import com.free.bitirmeprojesi.data.datasource.UrunlerDataSource
import com.free.bitirmeprojesi.data.entity.SepettekiUrunler
import com.free.bitirmeprojesi.data.entity.Urunler

class UrunlerRepository (var uds: UrunlerDataSource) {

    suspend fun kaydet(yemek_adi:String,yemek_resim_adi:String, yemek_fiyat:Int,yemek_siparis_adet:Int, kullanici_adi:String) = uds.kaydet(yemek_adi,yemek_resim_adi, yemek_fiyat,yemek_siparis_adet, kullanici_adi)

    suspend fun sepettekiUrunuSil(sepet_yemek_id:Int, kullanici_adi:String) = uds.sepettekiUrunuSil(sepet_yemek_id, kullanici_adi)

    suspend fun urunleriYukle() : List<Urunler> = uds.urunleriYukle()

    suspend fun sepettekiUrunleriYukle(kullanciciAdi:String) : List<SepettekiUrunler> = uds.sepettekiUrunleriYukle(kullanciciAdi)

}
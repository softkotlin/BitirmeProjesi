package com.free.bitirmeprojesi.data.datasource

import com.free.bitirmeprojesi.data.entity.SepettekiUrunler
import com.free.bitirmeprojesi.data.entity.Urunler
import com.free.bitirmeprojesi.retrofit.UrunlerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UrunlerDataSource(var udao: UrunlerDao) {

    suspend fun kaydet(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) = udao.kaydet(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

    suspend fun sepettekiUrunuSil(sepet_yemek_id: Int, kullanici_adi: String) =
        udao.sepettekiUrunuSil(sepet_yemek_id, kullanici_adi)

    suspend fun urunleriYukle(): List<Urunler> =
        withContext(Dispatchers.IO) {
            return@withContext udao.urunleriYukle().yemekler
        }

    suspend fun sepettekiUrunleriYukle(kullaniciAdi: String): List<SepettekiUrunler> =
        withContext(Dispatchers.IO) {
            return@withContext udao.sepettekiUrunleriYukle(kullaniciAdi).sepet_yemekler
        }
}
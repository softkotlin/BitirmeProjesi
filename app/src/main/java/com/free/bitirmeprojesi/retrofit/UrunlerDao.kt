package com.free.bitirmeprojesi.retrofit

import com.free.bitirmeprojesi.data.entity.CRUDCevap
import com.free.bitirmeprojesi.data.entity.SepettekiUrunlerCevap
import com.free.bitirmeprojesi.data.entity.UrunlerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UrunlerDao {
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun urunleriYukle() : UrunlerCevap

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepettekiUrunleriYukle(@Field("kullanici_adi") kullanciciAdi:String) : SepettekiUrunlerCevap

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettekiUrunuSil(@Field("sepet_yemek_id") sepet_yemek_id:Int, @Field("kullanici_adi") kullanici_adi:String) : CRUDCevap

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun kaydet(@Field("yemek_adi") yemek_adi:String,
                       @Field("yemek_resim_adi") yemek_resim_adi:String,
                       @Field("yemek_fiyat") yemek_fiyat:Int,
                       @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                       @Field("kullanici_adi") kullanici_adi:String) : CRUDCevap
}
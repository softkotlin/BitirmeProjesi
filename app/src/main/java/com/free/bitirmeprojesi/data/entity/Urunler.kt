package com.free.bitirmeprojesi.data.entity

import java.io.Serializable

class Urunler(var yemek_id:Int,
              var yemek_adi:String,
              var yemek_resim_adi:String,
              var yemek_fiyat:Int) : Serializable {
}
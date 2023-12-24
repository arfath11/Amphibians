package com.example.amphibians.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class AmpData(val name: String , val type:  String ,
                   val description: String ,val  img_src: String ){



}

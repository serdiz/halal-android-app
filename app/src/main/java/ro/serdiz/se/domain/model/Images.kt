package ro.serdiz.se.domain.model

import ro.serdiz.se.R

data class Images(

          val title :String,
          val rating : Float,
          val desc :String,
          val imgUri:Int
)



val ImagesList = listOf(

    Images(
        "Childhood Superman",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.img_10
    ),
    Images(
        "Candle Night At Marina",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.img_8
    ),
    Images(
        "Girl with Beautiful smile",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.img_7
    ),
    Images(
        "Bath Time",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.img_9
    )

)
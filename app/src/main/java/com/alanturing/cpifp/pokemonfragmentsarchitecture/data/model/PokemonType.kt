package com.alanturing.cpifp.pokemonfragmentsarchitecture.data.model
import android.content.Context
import androidx.annotation.StringRes
import com.alanturing.cpifp.pokemonfragmentsarchitecture.R

enum class PokemonType(@StringRes val typeResId: Int) {
    NORMAL(R.string.type_normal),
    FIRE(R.string.type_fire),
    WATER(R.string.type_water),
    ELECTRIC(R.string.type_electric),
    GRASS(R.string.type_grass),
    ICE(R.string.type_ice),
    FIGHTING(R.string.type_fighting),
    POISON(R.string.type_poison),
    GROUND(R.string.type_ground),
    FLYING(R.string.type_flying),
    PSYCHIC(R.string.type_psychic),
    BUG(R.string.type_bug),
    ROCK(R.string.type_rock),
    GHOST(R.string.type_ghost),
    DARK(R.string.type_dark),
    STEEL(R.string.type_steel),
    FAIRY(R.string.type_fairy);

   private fun getTypeDescription(context: Context): String {
        return context.getString(typeResId)
    }
}
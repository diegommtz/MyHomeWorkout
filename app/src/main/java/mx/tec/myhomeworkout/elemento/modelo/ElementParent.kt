package mx.tec.myhomeworkout.elemento.modelo

data class ElementParent (
val nombre: String,
val sets: String,
val descanso: String,
val ejercicios: List<ElementChild>)
{}
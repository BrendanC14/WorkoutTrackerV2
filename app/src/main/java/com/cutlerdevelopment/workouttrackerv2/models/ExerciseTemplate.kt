package com.cutlerdevelopment.workouttrackerv2.models

data class ExerciseTemplate(override val id : Int, val name : String, val type : ExerciseType) : AbstractSaveable(id) {

    override fun convertToSaveableMap(): HashMap<String, Any> {
        return hashMapOf(
            ID_FIRESTORE_KEY to id,
            NAME_FIRESTORE_KEY to name,
            TYPE_FIRESTORE_KEY to type.name
        )
    }

    companion object {
        const val COLLECTION_FIRESTORE_KEY = "Exercise Templates"
        const val ID_FIRESTORE_KEY = "ID"
        const val NAME_FIRESTORE_KEY = "Name"
        const val TYPE_FIRESTORE_KEY = "Type"
    }
}
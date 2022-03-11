package com.cutlerdevelopment.workouttrackerv2.models

class WorkoutTemplate(override val id: Int, var name : String) : AbstractSaveable(id) {
    override fun convertToSaveableMap(): HashMap<String, Any> {
        return hashMapOf(
            ID_FIRESTORE_KEY to id,
            NAME_FIRESTORE_KEY to name
        )
    }

    fun changeName(newName : String) {
        name = newName
    }

    companion object {
        const val COLLECTION_FIRESTORE_KEY = "Workout Templates"
        const val ID_FIRESTORE_KEY = "ID"
        const val NAME_FIRESTORE_KEY = "Name"
    }
}
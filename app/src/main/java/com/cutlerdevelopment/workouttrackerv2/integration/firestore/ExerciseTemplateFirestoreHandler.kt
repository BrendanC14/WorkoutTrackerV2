package com.cutlerdevelopment.workouttrackerv2.integration.firestore

import com.cutlerdevelopment.workouttrackerv2.models.ExerciseTemplate
import com.cutlerdevelopment.workouttrackerv2.models.ExerciseType
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot

class ExerciseTemplateFirestoreHandler() : AbstractFirestoreHandler<ExerciseTemplate>() {

    constructor(listener: IFirestoreListener) : this() {
        addListener(listener)
    }
    private val templateCollectionReference : CollectionReference = db.collection(ExerciseTemplate.COLLECTION_FIRESTORE_KEY)

    fun save(template: ExerciseTemplate) {
        this.addDocument(templateCollectionReference, template.convertToSaveableMap())
    }

    fun getAll() {
        this.retrieveCollection(templateCollectionReference)
    }

    override fun convertDocumentToItem(documentSnapshot: DocumentSnapshot) : ExerciseTemplate? {
        val id = documentSnapshot.getLong(ExerciseTemplate.ID_FIRESTORE_KEY)?.toInt() ?: return null
        val name = documentSnapshot.getString(ExerciseTemplate.ID_FIRESTORE_KEY) ?: return null
        val type = documentSnapshot.getString(ExerciseTemplate.TYPE_FIRESTORE_KEY) ?: return null
        return ExerciseTemplate(
            id, name, ExerciseType.valueOf(type)
        )
    }

}
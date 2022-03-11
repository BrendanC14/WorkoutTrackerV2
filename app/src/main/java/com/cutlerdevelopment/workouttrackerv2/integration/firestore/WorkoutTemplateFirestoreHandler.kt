package com.cutlerdevelopment.workouttrackerv2.integration.firestore

import com.cutlerdevelopment.workouttrackerv2.models.WorkoutTemplate
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot

class WorkoutTemplateFirestoreHandler() : AbstractFirestoreHandler<WorkoutTemplate>(){

    constructor(listener: IFirestoreListener) : this() {
        addListener(listener)
    }

    private val templateCollectionReference : CollectionReference = db.collection(WorkoutTemplate.COLLECTION_FIRESTORE_KEY)

    fun save(template: WorkoutTemplate) {
        this.addDocument(templateCollectionReference, template.convertToSaveableMap())
    }

    fun getAll() {
        this.retrieveCollection(templateCollectionReference)
    }

    override fun convertDocumentToItem(documentSnapshot: DocumentSnapshot): WorkoutTemplate? {
        val id = documentSnapshot.getLong(WorkoutTemplate.ID_FIRESTORE_KEY)?.toInt() ?: return null
        val name = documentSnapshot.getString(WorkoutTemplate.NAME_FIRESTORE_KEY) ?: return null
        return WorkoutTemplate(id, name)
    }
}
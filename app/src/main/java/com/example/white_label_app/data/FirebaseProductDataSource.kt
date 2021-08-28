package com.example.white_label_app.data

import android.net.Uri
import com.example.white_label_app.BuildConfig
import com.example.white_label_app.domain.model.Product
import com.example.white_label_app.utils.COLLECTION_PRODUCTS
import com.example.white_label_app.utils.COLLECTION_ROOT
import com.example.white_label_app.utils.STORAGE_IMAGES
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.coroutines.suspendCoroutine

class FirebaseProductDataSource(
    firebaseFirestore: FirebaseFirestore,
    firebaseStorage: FirebaseStorage
) : ProductDataSource {

    private val documentReference = firebaseFirestore
        .document("$COLLECTION_ROOT/${BuildConfig.FIREBASE_FLAVOR_COLLECTION}/")

    private val storageReference = firebaseStorage.reference

    override suspend fun getProduct(): List<Product> {

        return suspendCoroutine { continuation ->
            val productReference = documentReference.collection(COLLECTION_PRODUCTS)
            productReference.get().addOnSuccessListener { documents ->
                val products = mutableListOf<Product>()
                for (document in documents) {
                    document.toObject(Product::class.java).run {
                        products.add(this)
                    }
                }
                continuation.resumeWith(Result.success(products))
            }

            productReference.get().addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
        }

    }

    override suspend fun uploadProductImage(image: Uri): String {
        return suspendCoroutine { continuation ->
            val randomKey = UUID.randomUUID()
            val childReference = storageReference.child(
                "$STORAGE_IMAGES/${BuildConfig.FIREBASE_FLAVOR_COLLECTION}/$randomKey"
            )
            childReference.putFile(image)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                        val path = uri.toString()
                        continuation.resumeWith(Result.success(path))
                    }
                }.addOnFailureListener { exception ->
                    continuation.resumeWith(Result.failure(exception))
                }
        }
    }

    override suspend fun createProduct(product: Product): Product {
        return  suspendCoroutine { continuation ->  
            documentReference
                .collection(COLLECTION_PRODUCTS)
                .document(System.currentTimeMillis().toString())
                .set(product)
                .addOnSuccessListener {
                    continuation.resumeWith(Result.success(product))
                }.addOnFailureListener { exception ->
                    continuation.resumeWith(Result.failure(exception))
                }
        }
    }
}
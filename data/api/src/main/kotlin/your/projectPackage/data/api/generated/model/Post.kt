/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package your.projectPackage.data.api.generated.model


import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Contextual

/**
 * 
 *
 * @param id 
 * @param userId 
 * @param title 
 * @param body 
 */
@Serializable

data class Post (

    @SerialName(value = "id")
    val id: kotlin.Int,

    @SerialName(value = "userId")
    val userId: kotlin.Int,

    @SerialName(value = "title")
    val title: kotlin.String,

    @SerialName(value = "body")
    val body: kotlin.String

) {


}


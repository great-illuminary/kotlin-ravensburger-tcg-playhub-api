package eu.codlab.lorcana.rph.registrations

import io.ktor.http.encodeURLParameter
import kotlinx.serialization.SerialName

data class EventRegistrationsQueryParameters(
    val page: Int = 1,
    @SerialName("page_size")
    val pageSize: Int = 25
) {
    val toUrl = listOf(
        "page_size" to "$pageSize",
        "page" to "$page",
    ).joinToString("&") { "${it.first}=${it.second.encodeURLParameter()}" }
}

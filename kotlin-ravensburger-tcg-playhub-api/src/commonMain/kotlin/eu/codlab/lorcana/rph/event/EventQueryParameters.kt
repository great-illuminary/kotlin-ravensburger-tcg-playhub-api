package eu.codlab.lorcana.rph.event

import io.ktor.http.encodeURLParameter
import kotlinx.serialization.SerialName

data class EventQueryParameters(
    val startDateAfter: String? = null,
    val startDateBefore: String? = null,
    val page: Int = 1,
    @SerialName("page_size")
    val pageSize: Int = 25
) {
    val toUrl = listOf(
        "start_date_after" to startDateAfter,
        "start_date_before" to startDateBefore,
        "page_size" to "$pageSize",
        "page" to "$page",
        "game_slug" to "disney-lorcana"
    ).filter { null != it.second }
        .joinToString("&") { "${it.first}=${it.second!!.encodeURLParameter()}" }
}

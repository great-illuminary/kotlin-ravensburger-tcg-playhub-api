package eu.codlab.lorcana.rph.utils

import eu.codlab.http.Configuration
import eu.codlab.http.createClient
import eu.codlab.lorcana.rph.FallbackRequest
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

internal class Clients(private val fallbackRequest: FallbackRequest? = null) {
    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = false
        prettyPrint = true
    }

    val client = createClient(
        Configuration(
            json = json,
            enableLogs = false,
            socketTimeoutMillis = 30000,
            connectTimeoutMillis = 30000,
            requestTimeoutMillis = 30000
        )
    ) { }

    suspend inline fun <reified T> get(url: String): T {
        // TODO manage issues which are striclty related to the data mapping
        var retry = 0
        while (retry < 3) {
            val answer = client.get(url)

            if (answer.status.isSuccess()) {
                return answer.body()
            }

            retry--
            delay(retry.seconds)
        }

        val result: T? = fallbackRequest?.invoke(url)?.let { request ->
            if (request.status.isSuccess()) {
                request.body()
            } else {
                null
            }
        }

        return result ?: throw IllegalStateException("Couldn't load $url")
    }
}

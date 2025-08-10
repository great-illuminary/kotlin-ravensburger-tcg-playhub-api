import eu.codlab.lorcana.rph.LoadRPHCall
import eu.codlab.lorcana.rph.store.StoreFullRestLine
import eu.codlab.lorcana.rph.store.StoresQueryParameters
import eu.codlab.lorcana.rph.utils.Page
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.time.Duration.Companion.hours

class DumpStores {
    @Test
    fun stores() = runTest(timeout = 2.hours) {
        val calls = LoadRPHCall()

        println("testing...")

        var page = 1
        var events: Page<StoreFullRestLine>?

        do {
            events = calls.stores(
                StoresQueryParameters(
                    pageSize = 25,
                    page = page
                )
            )

            events.results.forEach { println(it) }

            page++
        } while (events.next != null)
    }
}

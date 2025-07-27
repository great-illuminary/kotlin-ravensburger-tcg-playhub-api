import eu.codlab.lorcana.rph.LoadRPHCall
import eu.codlab.lorcana.rph.event.EventQueryParameters
import eu.codlab.lorcana.rph.registrations.EventRegistrationsQueryParameters
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.time.Duration.Companion.hours

class Test {
    @Test
    fun testEvent() = runTest {
        val calls = LoadRPHCall()

        calls.eventRegistrations(158133).results.forEach {
            println(it)
        }

        println(" ")
        calls.eventRoundsStandings(4247).results.forEach {
            println(it.userEventStatus)
        }
    }

    @Test
    fun events() = runTest(timeout = 1.hours) {
        val calls = LoadRPHCall()

        calls.events(
            EventQueryParameters(
                pageSize = 3000
            )
        ).results.forEach { event ->
            println("managing event n°${event.id}")
            println(calls.event(event.id))
            println(
                calls.eventRegistrations(
                    event.id,
                    EventRegistrationsQueryParameters(pageSize = 10000)
                )
            )
        }
    }
}

import eu.codlab.lorcana.rph.LoadRPHCall
import eu.codlab.lorcana.rph.event.EventQueryParameters
import eu.codlab.lorcana.rph.registrations.EventRegistrationsQueryParameters
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.time.Duration.Companion.hours

class DumpEvents {
    @Test
    fun events() = runTest(timeout = 2.hours) {
        val calls = LoadRPHCall()

        println("testing...")

        val events = calls.events(
            EventQueryParameters(
                pageSize = 25
            )
        ).results

        events.forEachIndexed { index, event ->
            println("#$index/${events.size} managing ${event.id}")

            val actualEvent = calls.event(event.id)
            val registrations = calls.eventRegistrations(
                event.id,
                EventRegistrationsQueryParameters(pageSize = 3000)
            )

            println("  -> registrations / ${registrations.results.size}")

            actualEvent.tournamentPhases.forEach {
                it.rounds.forEach { round ->
                    println("   -> managing round ${round.id}")
                    val standings = calls.eventRoundsStandings(
                        round.id,
                        EventRegistrationsQueryParameters(pageSize = 3000)
                    )
                    val matches = calls.eventRoundsMatches(
                        round.id,
                        EventRegistrationsQueryParameters(pageSize = 3000)
                    )
                    println("   |-> standings / ${standings.results.size}")
                    println("   |-> matches   / ${matches.results.size}")
                }
            }
        }
    }
}

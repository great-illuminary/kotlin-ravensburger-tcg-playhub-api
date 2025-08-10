import eu.codlab.lorcana.rph.LoadRPHCall
import eu.codlab.lorcana.rph.event.EventQueryParameters
import eu.codlab.lorcana.rph.registrations.EventRegistrationsQueryParameters
import korlibs.time.DateFormat
import korlibs.time.DateTime
import korlibs.time.days
import korlibs.time.months
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.time.Duration.Companion.hours

class DumpEvents {
    @Test
    fun events() = runTest(timeout = 2.hours) {
        val calls = LoadRPHCall()

        println("testing...")

        var beginBatch = DateTime.parse("2025-04-01T01:00:00.000Z").utc.startOfMonth

        do {
            val nextBatch = beginBatch.add(0.months, 7.days)
            loadForMonth(calls, beginBatch, nextBatch)
            beginBatch = nextBatch
        } while (beginBatch < DateTime.now())
    }

    private suspend fun loadForMonth(calls: LoadRPHCall, beginBatch: DateTime, nextBatch: DateTime) {
        println(
            "managing from ${beginBatch.format(DateFormat.FORMAT2)} to" +
                    nextBatch.format(DateFormat.FORMAT2)
        )
        val events = calls.events(
            EventQueryParameters(
                startDateAfter = beginBatch.format(DateFormat.FORMAT2),
                startDateBefore = nextBatch.format(DateFormat.FORMAT2),
                pageSize = 25
            ).also { println(it) }
        ).results

        events.forEachIndexed { index, event ->
            println("#$index/${events.size} managing ${event.id}")

            val actualEvent = calls.event(event.id)
            val registrations = calls.eventRegistrations(
                event.id,
                EventRegistrationsQueryParameters(pageSize = 25)
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

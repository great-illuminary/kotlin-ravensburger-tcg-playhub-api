package eu.codlab.lorcana.rph

import eu.codlab.lorcana.rph.event.Event
import eu.codlab.lorcana.rph.event.EventQueryParameters
import eu.codlab.lorcana.rph.registrations.EventRegistrationsQueryParameters
import eu.codlab.lorcana.rph.rounds.matches.EventMatch
import eu.codlab.lorcana.rph.rounds.standings.EventStanding
import eu.codlab.lorcana.rph.rounds.standings.UserEventStatus
import eu.codlab.lorcana.rph.store.StoreFullRestLine
import eu.codlab.lorcana.rph.store.StoresQueryParameters
import eu.codlab.lorcana.rph.utils.Clients
import eu.codlab.lorcana.rph.utils.Page
import io.ktor.client.statement.HttpResponse

typealias FallbackRequest = (url: String) -> HttpResponse

class LoadRPHCall(
    fallbackRequest: FallbackRequest? = null
) {
    private val clients = Clients(fallbackRequest)
    private val workersDev =
        "https://cf-worker-middleware-hydra-prod.devion-villegas-76b.workers.dev/hydraproxy"
    private val rph = "https://api.ravensburgerplay.com/api/v2"

    /**
     * Retrieve the list of events matching the criteria
     */
    suspend fun events(parameters: EventQueryParameters = EventQueryParameters()) =
        clients.get<Page<Event>>("$workersDev/api/v2/events/?${parameters.toUrl}")

    /**
     * Retrieve the public information for a specific event
     */
    suspend fun event(
        id: Long,
    ) = clients.get<Event>("$rph/events/$id/")

    /**
     * Retrieve the list of registered players for a specific event/tournament
     */
    suspend fun eventRegistrations(
        id: Long,
        parameters: EventRegistrationsQueryParameters = EventRegistrationsQueryParameters()
    ) = clients.get<Page<UserEventStatus>>("$rph/events/$id/registrations/?${parameters.toUrl}")

    /**
     * Retrieve the list of standings for a specific Round's id
     */
    suspend fun eventRoundsStandings(
        id: Long,
        parameters: EventRegistrationsQueryParameters = EventRegistrationsQueryParameters()
    ) =
        clients.get<Page<EventStanding>>("$rph/tournament-rounds/$id/standings/paginated/?${parameters.toUrl}")

    /**
     * Retrieve the list of matches for a given Round's id
     */
    suspend fun eventRoundsMatches(
        id: Long,
        parameters: EventRegistrationsQueryParameters = EventRegistrationsQueryParameters()
    ) =
        clients.get<Page<EventMatch>>("$rph/tournament-rounds/$id/matches/paginated/?${parameters.toUrl}")

    /**
     * Retrieve the list of stores matching the criteria
     */
    suspend fun stores(parameters: StoresQueryParameters = StoresQueryParameters()) =
        clients.get<Page<StoreFullRestLine>>("$workersDev/api/v2/game-stores/?${parameters.toUrl}")
}

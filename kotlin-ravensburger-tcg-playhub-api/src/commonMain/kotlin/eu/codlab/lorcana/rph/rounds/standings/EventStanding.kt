package eu.codlab.lorcana.rph.rounds.standings

import eu.codlab.lorcana.rph.user.UserCondensed
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventStanding(
    val id: Int,
    val rank: Int,
    val player: UserCondensed? = null,
    @SerialName("user_event_status")
    val userEventStatus: UserEventStatus? = null,
    val record: String,
    @SerialName("match_record")
    val matchRecord: String? = null,
    @SerialName("match_points")
    val matchPoints: Int,
    @SerialName("opponent_match_win_percentage")
    val opponentMatchWinPercentage: Double,
    @SerialName("opponent_game_win_percentage")
    val opponentGameWinPercentage: Double,
    val points: Int
)

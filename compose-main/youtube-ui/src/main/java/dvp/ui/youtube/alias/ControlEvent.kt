package dvp.ui.youtube.alias

import dvp.data.youtube.viewmodel.MainEvent
import dvp.ui.youtube.mediaplayer.models.MediaState
import dvp.ui.youtube.mediaplayer.models.PlayerEvent

typealias OnMainUiEvent = (MainEvent) -> Unit
typealias OnPlayerEvent = (PlayerEvent) -> Unit
typealias OnControllerState = (MediaState, OnPlayerEvent) -> Unit
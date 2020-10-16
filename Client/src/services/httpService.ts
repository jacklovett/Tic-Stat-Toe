import axios from 'axios'

import { GameData } from '../types'

export const saveGameData = (gameData: GameData) => {
  axios.post('/game', gameData).catch((error) => {
    console.error(error)
  })
}

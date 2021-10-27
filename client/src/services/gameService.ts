import { httpPost } from './httpService'

export type Player = 'X' | 'O'
export type Draw = 'DRAW'

export type Winner = Player | Draw

export type SquareValue = Player | null

export type Turn = {
  boardHistory: SquareValue[]
  selectedSquare?: string
}

export type GameHistory = {
  turns: Turn[]
  stepNumber: number
  winner: Winner | null
  isX: boolean
  currentBoardState: SquareValue[]
}

export type GameData = {
  turns: Turn[]
  start: Date
  end?: Date
  winner: Winner
}

export const intitialBoardState = Array(9).fill(null)

export const initialGameHistoryState: GameHistory = {
  turns: [{ boardHistory: intitialBoardState }],
  currentBoardState: intitialBoardState,
  stepNumber: 0,
  winner: null,
  isX: true,
}

export const getPlayer = (isX: boolean): Player => {
  return isX ? 'X' : 'O'
}

export const getStatus = (player: string, winner: Winner | null) => {
  switch (winner) {
    case null:
      return `Next player: ${player}`
    case 'DRAW':
      return `It's a draw!`
    default:
      return `Winner: ${winner}`
  }
}

export const saveGameData = (gameData: GameData) => {
  try {
    httpPost<GameData>('/api/game', gameData)
  } catch (error) {
    console.error(`Unable to save game data: ${error}`)
  }
}

export const calculateWinner = (squares: SquareValue[], stepNumber: number) => {
  const lines = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [0, 4, 8],
    [2, 4, 6],
  ]

  let result: Winner | null = null

  for (const line of lines) {
    const [a, b, c] = line
    if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
      result = squares[a]
      break
    }
  }

  if (!result && stepNumber > 7) {
    result = 'DRAW'
  }

  return result
}

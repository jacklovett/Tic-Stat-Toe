export type Player = 'X' | 'O'
export type Draw = 'DRAW'

export type Winner = Player | Draw

export type SquareValue = Player | null

export type BoardHistory = {
  squares: SquareValue[]
}

export type GameHistory = {
  boardHistory: BoardHistory[]
  stepNumber: number
  winner: Winner | null
  isX: boolean
  currentBoardState: BoardHistory
}

export type GameData = {
  boardHistory: BoardHistory[]
  start: Date
  end?: Date
  winner: Winner
}

export type Statistics = {
  gameCount: number
}

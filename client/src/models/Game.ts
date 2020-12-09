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

import React, { useState, useMemo } from 'react'
import { Text, Stack, IconButton } from 'office-ui-fabric-react'
import * as commonStyles from './../styles'
import { Board } from './Board'
import { Player, SquareValue, Winner } from '../types'

interface BoardHistory {
  squares: SquareValue[]
}

interface GameHistory {
  boardHistory: BoardHistory[]
  stepNumber: number
  winner: Winner | null
  isX: boolean
}

interface GameData {
  history: BoardHistory[]
  start: string
  end?: string
  winner: Winner | null
}

const getInitialBoardHistory = (): BoardHistory[] => {
  return [
    {
      squares: Array(9).fill(null),
    },
  ]
}

const getInitialGameHistoryState = (): GameHistory => {
  return {
    boardHistory: getInitialBoardHistory(),
    stepNumber: 0,
    winner: null,
    isX: true,
  }
}

const getInitialGameDataState = (): GameData => {
  return {
    history: getInitialBoardHistory(),
    start: getDateNow(),
    winner: null,
  }
}

const calculateWinner = (squares: SquareValue[], stepNumber: number) => {
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
  for (let i = 0; i < lines.length; i++) {
    const [a, b, c] = lines[i]
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

const getDateNow = () => {
  return new Date().toISOString().slice(0, 19)
}

const styles = {
  gameInfo: {
    root: {
      minWidth: 160,
    },
  },
}

export const Game = () => {
  const [gameData, setGameData] = useState<GameData>(getInitialGameDataState())
  const [gameHistory, setGameHistory] = useState<GameHistory>(
    getInitialGameHistoryState(),
  )

  const { boardHistory, stepNumber, winner, isX } = gameHistory
  const [currentTurn, setCurrentTurn] = useState(boardHistory[0])

  useMemo(() => {
    if (winner && !gameData.end) {
      setGameData({
        ...gameData,
        history: boardHistory,
        winner,
        end: getDateNow(),
      })
    }
  }, [boardHistory, gameData, winner])

  useMemo(() => {
    setCurrentTurn(boardHistory[stepNumber])
  }, [boardHistory, stepNumber])

  const handleClick = (i: number) => {
    const newStateHistory = boardHistory.slice(0, stepNumber + 1)
    const nextTurn = newStateHistory[boardHistory.length - 1]
    const squares = nextTurn.squares.slice()
    if (winner || squares[i]) {
      return
    }

    squares[i] = getPlayer()

    setGameHistory({
      boardHistory: boardHistory.concat([
        {
          squares,
        },
      ]),
      stepNumber: stepNumber + 1,
      winner: calculateWinner(squares, stepNumber),
      isX: !isX,
    })
  }

  const getPlayer = (): Player => {
    return isX ? 'X' : 'O'
  }

  const getStatus = () => {
    switch (winner) {
      case null:
        return `Next player: ${getPlayer()}`
      case 'DRAW':
        return `It's a draw!`
      default:
        return `Winner: ${winner}`
    }
  }

  const reset = () => {
    setGameHistory(getInitialGameHistoryState())
  }

  return (
    <Stack verticalAlign="center" horizontalAlign="center" grow verticalFill>
      <Stack
        horizontal
        horizontalAlign="center"
        wrap
        tokens={{ childrenGap: 20 }}
      >
        <Board
          squares={currentTurn?.squares}
          selectSquare={(i: number) => handleClick(i)}
        />
        <Stack
          styles={styles.gameInfo}
          horizontalAlign="center"
          tokens={{ childrenGap: 20 }}
        >
          <Text>{getStatus()}</Text>
          {stepNumber && (
            <IconButton
              styles={commonStyles.iconButton}
              iconProps={{ iconName: 'Refresh' }}
              title="Reset"
              ariaLabel="Reset"
              onClick={() => reset()}
            />
          )}
        </Stack>
      </Stack>
    </Stack>
  )
}

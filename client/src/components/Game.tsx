import React, { useMemo, useState } from 'react'
import { Text, Stack, IconButton } from 'office-ui-fabric-react'
import * as commonStyles from './../styles'
import { Board } from './Board'
import {
  BoardHistory,
  GameData,
  GameHistory,
  Player,
  SquareValue,
  Winner,
} from '../types'
import { httpPost } from '../services/httpService'

const styles = {
  gameInfo: {
    root: {
      minWidth: 160,
    },
  },
}

const getInitialBoardState = (): BoardHistory => {
  return {
    squares: Array(9).fill(null),
  }
}

const getInitialGameHistoryState = (): GameHistory => {
  return {
    boardHistory: [getInitialBoardState()],
    currentBoardState: getInitialBoardState(),
    stepNumber: 0,
    winner: null,
    isX: true,
  }
}

const saveGameData = (gameData: GameData) => {
  httpPost('/api/game', gameData)
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

const formatBoardHistory = (boardHistory: BoardHistory[]) => {
  // Remove empty initial board state
  return boardHistory.splice(1, boardHistory.length)
}

export const Game = () => {
  const [start, setStart] = useState<Date>(new Date())
  const [gameHistory, setGameHistory] = useState<GameHistory>(
    getInitialGameHistoryState(),
  )

  const {
    boardHistory,
    currentBoardState,
    stepNumber,
    winner,
    isX,
  } = gameHistory

  useMemo(() => {
    if (winner) {
      saveGameData({
        boardHistory: formatBoardHistory(boardHistory),
        winner,
        start,
        end: new Date(),
      })
    }
  }, [boardHistory, winner, start])

  const handleClick = (i: number) => {
    // First box has been selected, set start time
    if (stepNumber === 0) {
      setStart(new Date())
    }

    const newStateHistory = boardHistory.slice(0, stepNumber + 1)
    const nextTurn = newStateHistory[boardHistory.length - 1]
    const squares = nextTurn.squares.slice()
    if (winner || squares[i]) {
      return
    }

    squares[i] = getPlayer()

    const newBoardState = {
      squares,
    }

    setGameHistory({
      boardHistory: boardHistory.concat([newBoardState]),
      currentBoardState: newBoardState,
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
          squares={currentBoardState?.squares}
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

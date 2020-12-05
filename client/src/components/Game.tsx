import React, { useMemo, useState } from 'react'
import { Text, Stack, IconButton } from 'office-ui-fabric-react'
import * as commonStyles from './../styles'
import { Board } from './Board'
import {
  GameData,
  GameHistory,
  Player,
  SquareValue,
  Winner,
} from '../models/Game'
import { httpPost } from '../services/httpService'

const styles = {
  gameInfo: {
    root: {
      minWidth: 160,
      color: '#ffffff',
    },
  },
}

const intitialBoardState = Array(9).fill(null)

const getInitialGameHistoryState = (): GameHistory => {
  return {
    turns: [{ squares: intitialBoardState }],
    currentBoardState: intitialBoardState,
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

export const Game = () => {
  const [start, setStart] = useState<Date>(new Date())
  const [gameHistory, setGameHistory] = useState<GameHistory>(
    getInitialGameHistoryState(),
  )

  const { turns, currentBoardState, stepNumber, winner, isX } = gameHistory

  useMemo(() => {
    if (winner) {
      saveGameData({
        turns: turns.slice(0, turns.length), // Remove empty initial board state
        winner,
        start,
        end: new Date(),
      })
    }
  }, [turns, winner, start])

  const handleClick = (selectedSquare: number) => {
    // First box has been selected, set start time
    if (stepNumber === 0) {
      setStart(new Date())
    }

    const newStateHistory = turns.slice(0, stepNumber + 1)
    const nextTurn = newStateHistory[turns.length - 1]
    const squares = nextTurn.squares

    if (winner || squares[selectedSquare]) {
      return
    }

    squares[selectedSquare] = getPlayer()

    setGameHistory({
      turns: turns.concat([{ squares, selectedSquare }]),
      currentBoardState: squares,
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
          squares={currentBoardState}
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

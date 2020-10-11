import React, { useState, useMemo } from 'react'
import { Text, Stack, IconButton } from 'office-ui-fabric-react'
import * as commonStyles from './../styles'
import { Board } from './Board'
import { Player, SquareValue, Winner } from '../types'

interface History {
  squares: SquareValue[]
}

interface GameData {
  history: History[]
  start: string
  end?: string
  winner: Winner | null
}

const getInitialHistoryState = (): History[] => {
  return [
    {
      squares: Array(9).fill(null),
    },
  ]
}

const getInitialGameDataState = (): GameData => {
  return {
    history: getInitialHistoryState(),
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
  const [history, setHistory] = useState<History[]>(getInitialHistoryState())

  const [isX, setIsX] = useState(true)
  const [stepNumber, setStepNumber] = useState(0)
  const [currentTurn, setCurrentTurn] = useState(history[0])
  const [winner, setWinner] = useState<Winner | null>(null)

  useMemo(() => {
    if (winner && !gameData.end) {
      setGameData({
        ...gameData,
        history,
        winner,
        end: getDateNow(),
      })
    }
  }, [gameData, history, winner])

  useMemo(() => {
    setCurrentTurn(history[stepNumber])
  }, [history, stepNumber])

  const handleClick = (i: number) => {
    const newStateHistory = history.slice(0, stepNumber + 1)
    const nextTurn = newStateHistory[history.length - 1]
    const squares = nextTurn.squares.slice()
    if (winner || squares[i]) {
      return
    }

    squares[i] = getPlayer()

    setHistory(
      history.concat([
        {
          squares,
        },
      ]),
    )

    setStepNumber(history.length)

    const calculatedWinner = calculateWinner(squares, stepNumber)

    if (calculatedWinner) {
      return setWinner(calculatedWinner)
    }

    setIsX(!isX)
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
    setStepNumber(0)
    setIsX(true)
    setHistory(getInitialHistoryState())
    setWinner(null)
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

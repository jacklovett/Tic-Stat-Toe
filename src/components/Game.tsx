import React, { useState, useEffect } from 'react'
import { Text, Stack, IconButton } from 'office-ui-fabric-react'
import { Board } from './Board'

interface History {
  squares: string[]
}

const getInitialHistoryState = () => {
  return [
    {
      squares: Array(9).fill(null),
    },
  ]
}

const calculateWinner = (squares: string[]) => {
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

  let result = null
  lines.forEach((line) => {
    const [a, b, c] = line
    if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
      result = squares[a]
    }
  })
  return result
}

const styles = {
  gameInfo: {
    root: {
      minWidth: 150,
    },
  },
}

export const Game = () => {
  const [history, setHistory] = useState<History[]>(getInitialHistoryState())

  const [isX, setIsX] = useState(true)
  const [stepNumber, setStepNumber] = useState(0)
  const [currentTurn, setCurrentTurn] = useState(history[0])
  const [winner, setWinner] = useState(null)

  useEffect(() => {
    setCurrentTurn(history[stepNumber])
  }, [history, stepNumber])

  useEffect(() => {
    setWinner(calculateWinner(currentTurn.squares))
  }, [currentTurn])

  const handleClick = (i: number) => {
    const newStateHistory = history.slice(0, stepNumber + 1)
    const nextTurn = newStateHistory[history.length - 1]
    const squares = nextTurn.squares.slice()
    if (calculateWinner(squares) || squares[i]) {
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
    setIsX(!isX)
  }

  const getPlayer = () => {
    return isX ? 'X' : 'O'
  }

  const getStatus = () => {
    if (winner) {
      return `Winner: ${winner}`
    }

    if (!winner && stepNumber > 8) {
      return `It's a draw!`
    }

    return `Next player: ${getPlayer()}`
  }

  const reset = () => {
    setStepNumber(0)
    setIsX(true)
    setHistory(getInitialHistoryState())
  }

  return (
    <Stack horizontal horizontalAlign="center" wrap>
      <Board
        squares={currentTurn?.squares}
        selectSquare={(i: number) => handleClick(i)}
      />
      <Stack
        styles={styles.gameInfo}
        horizontalAlign="center"
        tokens={{ childrenGap: 10 }}
      >
        <Text>{getStatus()}</Text>
        <IconButton
          iconProps={{ iconName: 'Refresh' }}
          title="Reset"
          ariaLabel="Reset"
          onClick={() => reset()}
          disabled={!stepNumber}
        />
      </Stack>
    </Stack>
  )
}

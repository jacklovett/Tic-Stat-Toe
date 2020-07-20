import React from 'react'
import { Stack, IStyle } from 'office-ui-fabric-react'
import { Square } from './Square'

interface IBoardProps {
  selectSquare: (i: number) => void
  squares: string[]
}

const styles = {
  board: {
    root: {
      margin: 16,
    },
  },
  boardRow: {
    root: {
      selectors: {
        ':after': {
          clear: 'both',
          display: 'table',
          content: '',
        },
      },
    } as IStyle,
  },
}

export const Board = (props: IBoardProps) => {
  const { selectSquare, squares } = props

  function renderSquare(index: number) {
    return (
      <Square id={index} value={squares[index]} selectSquare={selectSquare} />
    )
  }

  return (
    <Stack>
      <Stack styles={styles.board}>
        <Stack horizontal styles={styles.boardRow}>
          {renderSquare(0)}
          {renderSquare(1)}
          {renderSquare(2)}
        </Stack>
        <Stack horizontal styles={styles.boardRow}>
          {renderSquare(3)}
          {renderSquare(4)}
          {renderSquare(5)}
        </Stack>
        <Stack horizontal styles={styles.boardRow}>
          {renderSquare(6)}
          {renderSquare(7)}
          {renderSquare(8)}
        </Stack>
      </Stack>
    </Stack>
  )
}

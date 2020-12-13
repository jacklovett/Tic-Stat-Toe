import React from 'react'
import { Stack, IStyle } from 'office-ui-fabric-react'

import { SquareValue } from '../models/Game'
import { Square } from './Square'

interface IBoardProps {
  selectSquare: (i: number, position: string) => void
  squares: SquareValue[]
}

const styles = {
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

  const renderSquare = (index: number, position: string) => {
    return (
      <Square
        id={index}
        position={position}
        value={squares[index]}
        selectSquare={selectSquare}
      />
    )
  }

  return (
    <Stack>
      <Stack horizontal styles={styles.boardRow}>
        {renderSquare(0, 'a1')}
        {renderSquare(1, 'b1')}
        {renderSquare(2, 'c1')}
      </Stack>
      <Stack horizontal styles={styles.boardRow}>
        {renderSquare(3, 'a2')}
        {renderSquare(4, 'b2')}
        {renderSquare(5, 'c2')}
      </Stack>
      <Stack horizontal styles={styles.boardRow}>
        {renderSquare(6, 'a3')}
        {renderSquare(7, 'b3')}
        {renderSquare(8, 'c3')}
      </Stack>
    </Stack>
  )
}

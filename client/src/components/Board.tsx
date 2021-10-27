import React from 'react'
import { IStyle, Stack } from '@fluentui/react'

import { SquareValue } from '../services/gameService'

import { Square } from './Square'

interface IBoardProps {
  selectSquare: (i: number, position: string) => void
  squares: SquareValue[]
}

const styles = {
  boardWrapper: {
    root: {
      border: 'solid 2px #09d3ac',
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

  const renderSquare = (index: number, position: string) => (
    <Square
      id={index}
      position={position}
      value={squares[index]}
      selectSquare={selectSquare}
    />
  )

  return (
    <Stack styles={styles.boardWrapper}>
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

import React from 'react'
import { DefaultButton, IStyle } from '@fluentui/react'

import { SquareValue } from '../services/gameService'

interface ISquareProps {
  id: number
  position: string
  value: SquareValue
  selectSquare: (id: number, position: string) => void
}

const styles = {
  square: {
    root: {
      width: 90,
      height: 90,
      background: 'transparent',
      border: 'solid 2px #09d3ac',
      borderRadius: 0,
      color: '#ffffff',
      padding: 0,
      fontSize: 16,
      selectors: {
        '& .ms-Button:hover, .ms-Button-flexContainer:hover': {
          background: '#09d3ac',
          color: '#000000',
        } as IStyle,
      },
    },
  },
}

export const Square = (props: ISquareProps) => {
  const { id, position, value, selectSquare } = props
  return (
    <DefaultButton
      styles={styles.square}
      id={`${id}`}
      ariaLabel={position}
      text={value ?? ''}
      onClick={() => selectSquare(id, position)}
    />
  )
}

import React from 'react'
import { DefaultButton, IStyle } from '@fluentui/react'

import { SquareValue } from '../services/gameService'

import { ScreenSize } from '../hooks/useMediaQuery'

interface ISquareProps {
  id: number
  position: string
  value: SquareValue
  screenSize: number
  selectSquare: (id: number, position: string) => void
}

const getSquareSize = (screenSize: ScreenSize): number => {
  let squareSize = 90
  if (screenSize === ScreenSize.Tablet) {
    squareSize = 135
  }

  if (screenSize === ScreenSize.Desktop) {
    squareSize = 180
  }

  return squareSize
}

const getStyles = (screenSize: ScreenSize) => {
  const squareSize = getSquareSize(screenSize)
  return {
    square: {
      root: {
        width: squareSize,
        height: squareSize,
        background: 'transparent',
        border: 'solid 2px #09d3ac',
        borderRadius: 0,
        color: '#ffffff',
        padding: 0,
        fontSize: 16,
        selectors: {
          '& .ms-Button:hover, .ms-Button-flexContainer:hover': {
            background: '#09d3ac',
            color: '#c60af5',
          } as IStyle,
        },
      },
    },
  }
}

export const Square = (props: ISquareProps) => {
  const { id, position, value, screenSize, selectSquare } = props

  return (
    <DefaultButton
      styles={getStyles(screenSize).square}
      id={`${id}`}
      ariaLabel={position}
      text={value ?? ''}
      onClick={() => selectSquare(id, position)}
    />
  )
}

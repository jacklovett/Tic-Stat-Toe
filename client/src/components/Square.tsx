import React from 'react'
import { DefaultButton } from 'office-ui-fabric-react'

import { SquareValue } from '../models/Game'

interface ISquareProps {
  id: number
  position: string
  value: SquareValue
  selectSquare: (id: number, position: string) => void
}

const styles = {
  square: {
    root: {
      width: 50,
      height: 50,
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
      text={value || ''}
      onClick={() => {
        selectSquare(id, position)
      }}
    />
  )
}

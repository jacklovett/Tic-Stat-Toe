import React from 'react'
import { DefaultButton } from 'office-ui-fabric-react'

interface ISquareProps {
  id: number
  value: string
  selectSquare: (id: number) => void
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
  const { id, value, selectSquare } = props
  return (
    <DefaultButton
      styles={styles.square}
      id={`${id}`}
      text={value}
      onClick={() => {
        selectSquare(id)
      }}
    />
  )
}

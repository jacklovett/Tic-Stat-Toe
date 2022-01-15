import React from 'react'
import { IconButton, IStyle } from '@fluentui/react'

export interface IButtonIconProps {
  text: string
  iconName: string
  onClick: () => void
}

const styles = {
  root: {
    fontSize: 24,
    color: '#09d3ac',
    margin: 0,
    padding: 0,
    selectors: {
      '& .ms-Button-icon:hover, .ms-Button-flexContainer:hover': {
        background: '#282c34',
        color: '#c60af5',
      } as IStyle,
    },
  },
}

export const ButtonIcon = (props: IButtonIconProps) => {
  const { text, iconName, onClick } = props

  return (
    <IconButton
      styles={styles}
      title={text}
      ariaLabel={text}
      iconProps={{ iconName }}
      onClick={onClick}
    />
  )
}

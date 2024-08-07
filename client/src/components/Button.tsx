import React from 'react'
import { IStyle, PrimaryButton } from '@fluentui/react'

interface IButtonIconProps {
  text: string
  onClick: () => void
}

const buttonStyles = {
  root: {
    height: 48,
    border: 'none',
    borderRadius: 25,
    background: '#9e08c4',
    fontSize: 16,
    selectors: {
      '&.ms-Button:hover, .ms-Button--primary:hover': {
        border: 'none',
        borderRadius: 25,
        background: '#09d3ac',
        color: '#9e08c4',
      } as IStyle,
    },
  },
}

export const Button = (props: IButtonIconProps) => {
  const { text, onClick } = props
  return (
    <PrimaryButton
      styles={buttonStyles}
      text={text}
      ariaLabel={text}
      onClick={onClick}
    />
  )
}

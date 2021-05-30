import React from 'react'
import { PrimaryButton } from '@fluentui/react'

interface IButtonIconProps {
  text: string
  onClick: () => void
}

export const Button = (props: IButtonIconProps) => {
  const { text, onClick } = props
  return <PrimaryButton text={text} ariaLabel={text} onClick={onClick} />
}

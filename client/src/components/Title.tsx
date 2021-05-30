import React from 'react'
import { memoizeFunction, Text } from '@fluentui/react'

interface Props {
  label: string
  color?: string
}

const getStyles = memoizeFunction((color?: string) => ({
  title: {
    root: {
      width: '100%',
      fontSize: 24,
      color,
      fontWeight: 'bold',
      textAlign: 'center',
    },
  },
}))

export const Title = (props: Props) => {
  const { color, label } = props
  return <Text styles={getStyles(color).title}>{label}</Text>
}

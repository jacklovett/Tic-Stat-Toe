import React from 'react'
import { memoizeFunction, Text } from 'office-ui-fabric-react'

const getStyles = memoizeFunction((color?: string) => {
  return {
    title: {
      root: {
        width: '100%',
        fontSize: 24,
        color: color,
        fontWeight: 'bold',
        textAlign: 'center',
      },
    },
  }
})

interface Props {
  label: string
  color?: string
}

export const Title = (props: Props) => {
  const styles = getStyles(props.color)
  return <Text styles={styles.title}>{props.label}</Text>
}

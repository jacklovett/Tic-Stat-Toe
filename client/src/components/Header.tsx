import React from 'react'
import { IconButton, Stack } from 'office-ui-fabric-react'
import * as commonStyles from './../styles'
import { Title } from './Title'

interface IProps {
  isStatsVisible: boolean
  setStatsVisible: (value: boolean) => void
}

const styles = {
  header: {
    root: {
      fontSize: 24,
      background: '#2c313a',
      color: '#09d3ac',
    },
  },
}

export const Header = (props: IProps) => {
  const { isStatsVisible, setStatsVisible } = props

  const buttonText = isStatsVisible ? 'Back To Game' : 'Show Statistics'

  return (
    <Stack
      styles={styles.header}
      horizontal
      verticalAlign="center"
      horizontalAlign="space-between"
      tokens={{ padding: 20 }}
    >
      <Title label="Tic-Stat-Toe" color={styles.header.root.color} />
      <IconButton
        styles={commonStyles.iconButton}
        title={buttonText}
        ariaLabel={buttonText}
        iconProps={{ iconName: `${isStatsVisible ? 'game' : 'chart'}` }}
        onClick={() => setStatsVisible(!isStatsVisible)}
      />
    </Stack>
  )
}

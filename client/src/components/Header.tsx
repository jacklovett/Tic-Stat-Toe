import React from 'react'
import { Stack } from '@fluentui/react'

import { Title } from './Title'
import { ButtonIcon, IButtonIconProps } from './ButtonIcon'

interface IProps {
  navButtonProps?: IButtonIconProps
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
  const { navButtonProps } = props

  return (
    <Stack
      styles={styles.header}
      horizontal
      verticalAlign="center"
      horizontalAlign="space-between"
      tokens={{ padding: 20 }}
      verticalFill
    >
      <Title label="Tic-Stat-Toe" color={styles.header.root.color} />
      {navButtonProps && <ButtonIcon {...navButtonProps} />}
    </Stack>
  )
}

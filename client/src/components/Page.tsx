import React from 'react'
import { Stack, Text } from '@fluentui/react'

import * as commonStyles from '../styles'

interface IProps {
  header: JSX.Element
  body: JSX.Element
  error?: string
}

const getStyles = () => {
  const headerHeight = 60
  return {
    ...commonStyles.basicPage,
    headerWrapper: {
      root: {
        height: headerHeight,
      },
    },
    bodyWrapper: {
      root: {
        height: `calc(100% - ${headerHeight}px)`,
        width: '100%',
        padding: 32,
        overflowY: 'auto',
      },
    },
  }
}

export const Page = (props: IProps) => {
  const { body, header, error } = props
  const styles = getStyles()
  return (
    <Stack grow verticalFill>
      <Stack styles={styles.headerWrapper}>{header}</Stack>
      <Stack styles={styles.bodyWrapper} horizontalAlign="center">
        {!error && body}
        {error && (
          <Stack horizontalAlign="center" verticalFill verticalAlign="center">
            <Text styles={styles.text}>{error}</Text>
          </Stack>
        )}
      </Stack>
    </Stack>
  )
}

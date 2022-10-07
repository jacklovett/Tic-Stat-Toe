import React from 'react'
import { IStyle, Stack, Text } from '@fluentui/react'

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
        position: 'fixed',
        top: 0,
        left: 0,
        right: 0,
        zIndex: 1,
      } as IStyle,
    },
    bodyWrapper: {
      root: {
        height: `calc(100% - ${headerHeight}px)`,
        width: '100%',
        padding: 32,
        overflowY: 'auto',
        position: 'relative',
        top: 60,
      } as IStyle,
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

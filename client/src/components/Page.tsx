import React from 'react'
import { Stack, Text } from '@fluentui/react'

interface IProps {
  header: JSX.Element
  body: JSX.Element
  error?: string
}

const getStyles = () => {
  const headerHeight = 60
  return {
    headerWrapper: {
      root: {
        height: headerHeight,
      },
    },
    bodyWrapper: {
      root: {
        height: `calc(100% - ${headerHeight}px)`,
      },
    },
    errorText: {
      root: { color: '#ffffff' },
    },
  }
}

export const Page = (props: IProps) => {
  const { body, header, error } = props
  const styles = getStyles()
  return (
    <Stack grow verticalFill>
      <Stack styles={styles.headerWrapper}>{header}</Stack>
      <Stack
        styles={styles.bodyWrapper}
        horizontalAlign="center"
        tokens={{ padding: 20 }}
      >
        {!error && body}
        {error && (
          <Stack horizontalAlign="center" verticalFill verticalAlign="center">
            <Text styles={styles.errorText}>{error}</Text>
          </Stack>
        )}
      </Stack>
    </Stack>
  )
}

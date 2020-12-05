import { Stack } from 'office-ui-fabric-react'
import React from 'react'

interface IProps {
  header: JSX.Element
  body: JSX.Element
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
  }
}

export const Page = (props: IProps) => {
  const { body, header } = props
  const styles = getStyles()
  return (
    <Stack grow verticalFill>
      <Stack styles={styles.headerWrapper}>{header}</Stack>
      <Stack
        styles={styles.bodyWrapper}
        horizontalAlign="center"
        tokens={{ padding: 20 }}
      >
        {body}
      </Stack>
    </Stack>
  )
}

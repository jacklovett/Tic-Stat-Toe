import React from 'react'
import { RouterProps } from 'react-router-dom'
import { Stack, Text } from '@fluentui/react'

import { Button } from '../components/Button'
import { Header } from '../components/Header'
import { Page } from '../components/Page'

import * as commonStyles from '../styles'

const styles = commonStyles.basicPage

export const PageNotFound = (props: RouterProps) => {
  const { history } = props
  return (
    <Page
      header={<Header />}
      body={
        <Stack horizontalAlign="center" verticalFill verticalAlign="center">
          <Stack styles={styles.contents} tokens={{ childrenGap: 36 }}>
            <Text styles={styles.text}>{'404: Page not found'}</Text>
            <Button text="Return" onClick={() => history.push('/')} />
          </Stack>
        </Stack>
      }
    />
  )
}

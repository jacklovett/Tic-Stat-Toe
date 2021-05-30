import React from 'react'
import { RouterProps } from 'react-router-dom'
import { Stack, Text } from '@fluentui/react'

import { Button } from '../components/Button'
import { Header } from '../components/Header'
import { Page } from '../components/Page'

import * as commonStyles from '../styles'

const styles = commonStyles.basicPage

export const Home = (props: RouterProps) => {
  const { history } = props
  return (
    <Page
      header={<Header />}
      body={
        <Stack horizontalAlign="center" verticalFill verticalAlign="center">
          <Stack styles={styles.contents} tokens={{ childrenGap: 36 }}>
            <Stack tokens={{ childrenGap: 16 }}>
              <Text styles={styles.text}>
                {
                  'Have you ever wondered what statistic and insights can be gathered from the classic game Tic-Tac-Toe? ("Noughts and crosses" to my fellow Brits).'
                }
              </Text>
              <Text styles={styles.text}>
                {'Yeah, probably not...Well here it is anyway, Enjoy!'}
              </Text>
            </Stack>
            <Button
              text="Start Playing"
              onClick={() => history.push('/game')}
            />
          </Stack>
        </Stack>
      }
    />
  )
}

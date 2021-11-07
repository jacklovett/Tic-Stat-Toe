import React from 'react'
import { RouterProps } from 'react-router-dom'
import { Stack, Text, Image, IImageProps, ImageFit } from '@fluentui/react'

import { Button } from '../components/Button'
import { Header } from '../components/Header'
import { Page } from '../components/Page'

import * as commonStyles from '../styles'

const styles = {
  ...commonStyles.basicPage,
}

const imageProps: Partial<IImageProps> = {
  src: '/assets/welcomeImage.png',
  imageFit: ImageFit.centerContain,
  alt: 'Welcome Image',
  width: 270,
  height: 270,
}

export const Home = (props: RouterProps) => {
  const { history } = props
  return (
    <Page
      header={<Header />}
      body={
        <Stack styles={styles.contents} tokens={{ childrenGap: 32 }}>
          <Stack horizontal horizontalAlign="center">
            <Image {...imageProps} />
          </Stack>
          <Stack tokens={{ childrenGap: 16 }}>
            <Text styles={styles.text}>
              Have you ever wondered what statistic and insights can be gathered
              from the classic game Tic-Tac-Toe ("Noughts and crosses")?
            </Text>
            <Text styles={styles.text}>No? Well here it is anyway, Enjoy!</Text>
          </Stack>
          <Button text="Start Playing" onClick={() => history.push('/game')} />
        </Stack>
      }
    />
  )
}

import React from 'react'
import { useNavigate } from 'react-router-dom'
import { Stack, Text, Image, IImageProps, ImageFit } from '@fluentui/react'

import { Button, Page, Header } from '../components'

import * as commonStyles from '../styles'

const styles = {
  ...commonStyles.basicPage,
  text: {
    root: {
      ...commonStyles.basicPage.text.root,
      textAlign: 'center',
    },
  },
}

const imageProps: Partial<IImageProps> = {
  src: '/assets/welcomeImage.png',
  imageFit: ImageFit.centerContain,
  alt: 'Welcome Image',
  width: 270,
  height: 270,
}

export const Home = () => {
  const navigate = useNavigate()
  return (
    <Page
      header={<Header />}
      body={
        <Stack
          styles={styles.contents}
          tokens={{ childrenGap: 48 }}
          verticalAlign="center"
          verticalFill
        >
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
          <Button text="Start Playing!" onClick={() => navigate('/game')} />
        </Stack>
      }
    />
  )
}

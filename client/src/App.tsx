import React, { useState } from 'react'
import { IStyle, Stack } from 'office-ui-fabric-react'
import { Game } from './components/Game'
import { Header } from './components/Header'
import { Page } from './components/Page'
import { Stats } from './components/Stats'

const styles = {
  app: {
    root: {
      background: '#282c34',
      color: '#0d0d0d',
      height: '100vh',
      overflow: 'hidden',
    } as IStyle,
  },
}

const App = () => {
  const [isStatsVisible, setStatsVisible] = useState<boolean>(false)

  return (
    <Stack styles={styles.app}>
      <Page
        header={
          <Header
            isStatsVisible={isStatsVisible}
            setStatsVisible={setStatsVisible}
          />
        }
        body={isStatsVisible ? <Stats /> : <Game />}
      />
    </Stack>
  )
}

export default App

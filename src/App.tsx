import React from 'react'
import { Stack, Text } from 'office-ui-fabric-react'
import { Game } from './components/Game'

const styles = {
  app: {
    root: {
      textAlign: 'center',
      background: '#282c34',
      minHeight: '100vh',
      fontSize: 12,
      color: '#ffffff',
    },
  },
  title: {
    root: {
      fontSize: 36,
      color: '#09d3ac',
    },
  },
}

function App() {
  return (
    <Stack styles={styles.app} verticalAlign="center">
      <Text styles={styles.title}>Tic-Stat-Toe</Text>
      <Stack horizontalAlign="center">
        <Game />
      </Stack>
    </Stack>
  )
}

export default App

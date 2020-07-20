import React from 'react'
import './App.css'
import { Stack } from 'office-ui-fabric-react'
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
}

function App() {
  return (
    <Stack styles={styles.app} verticalAlign="center">
      <h1>Tic-Stat-Toe</h1>
      <Stack horizontalAlign="center">
        <Game />
      </Stack>
    </Stack>
  )
}

export default App

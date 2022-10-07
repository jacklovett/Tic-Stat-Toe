import React from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { IStyle, Stack } from '@fluentui/react'

import { Home } from './pages/Home'
import { PageNotFound } from './pages/PageNotFound'
import { Game } from './pages/Game'
import { Stats } from './pages/Stats'

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

const App = () => (
  <Stack styles={styles.app}>
    <Router>
      <Routes>
        <Route key="home" path="/" element={<Home />} />
        <Route key="game" path="/game" element={<Game />} />
        <Route key="stats" path="/stats" element={<Stats />} />
        <Route key="404" path="*" element={<PageNotFound />} />
      </Routes>
    </Router>
  </Stack>
)

export default App
